package gui;

import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import dot.AdapterBuilder;
import dot.AssociationBuilder;
import dot.ExtensionBuilder;
import dot.IBuilder;
import dot.ImplementsBuilder;
import dot.UmlBuilder;
import phases.IPhase;
import records.IClassRecord;

public class UmlWrapper {
	public final static String fontName = "Comic Sans MS";
	private HashSet<String> classNames;
	private HashMap<String, IClassRecord> records = new HashMap<>();
	private HashMap<String, IBuilder> decorators = new HashMap<String, IBuilder>();
	private ArrayList<Class<? extends IBuilder>> builderClasses = new ArrayList<Class<? extends IBuilder>>();
	private ArrayList<IPhase> phases;
	public String graph = "";
	Properties config;

	public UmlWrapper() {
		try {
			this.setProperties(new Properties(loadDefault()));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Default properties are invalid");
			System.exit(-1);
		}
		this.classNames = new HashSet<String>();
	}


	public void reset() {
		this.classNames.clear();
		this.records.clear();
		this.decorators.clear();
		this.graph = "";
	}

	private Properties loadDefault() {
		Properties prop = new Properties();
		prop.setProperty("Input-Folder", "src");
		prop.setProperty("Input-Classes", "");
		prop.setProperty("Output-Directory", ".");
		prop.setProperty("Dot-Path", "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");
		prop.setProperty("Phases", "Load, PatternDetection, GenerateUML, Print");
		prop.setProperty("Builder-Classes", "ExtensionBuilder, ImplementsBuilder, AssociationBuilder, "
				+ "DecoratorBuilder, AdapterBuilder, SingletonBuilder, UsesBuilder, CompositeBuilder");
		return prop;
	}

	public void generateGraph() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.createGraph(this.graph);
	}

	public void load() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		for (String className : this.classNames) {
			IBuilder builder = new UmlBuilder(className, this.classNames);
			for (Class<? extends IBuilder> builderClass : this.builderClasses) {
				Constructor<? extends IBuilder> constructor = builderClass.getConstructor(IBuilder.class);
				builder = constructor.newInstance(builder); // should work
			}
			this.decorators.put(className, builder);
			this.records.put(className, builder.build());
		}
	}

	public void findPatterns() {
		for (String className : this.classNames) {
			// for (IBuilder decorator : this.decorators) {
			IBuilder decorator = this.decorators.get(className);
			IClassRecord record = this.records.get(className);
			decorator.calculatePattern(record, this.records);
		}
	}

	public void generateUML() {
		StringBuilder s = new StringBuilder();
		s.append("digraph G {fontname = \"" + fontName + "\"  fontsize = 8  node [ fontname = \"" + fontName
				+ "\" fontsize = 8 shape = \"record\"]" + " edge [ fontname = \"" + fontName + "\" fontsize = 8 ]\n");

		for (String className : this.records.keySet()) {
			s.append(this.records.get(className).getClassUml());
		}
		s.append("}");
		this.graph = s.toString();
	}

	public void createGraph(String digraph) {
		final Path path = Paths.get("temp.dot");
		try {
			final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
					StandardOpenOption.CREATE);
			writer.write(digraph);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find file at " + path.toString());
			return;
		}
		ProcessBuilder pb = new ProcessBuilder(config.getProperty("Dot-Path"), "-Tpng", "temp.dot", "-o", "out.png");
		Map<String, String> env = pb.environment();
		// pb.directory();
		// System.out.println(System.getProperty("user.dir"));
		try {
			// Process p = pb.start();
			File log = new File("log");
			pb.redirectErrorStream(true);
			pb.redirectOutput(Redirect.appendTo(log));
			Process p = pb.start();
			p.waitFor();
//			FileWriter file = new FileWriter(new File("done.temp"));
//			file.write('1');
			// Files.delete(path);//uncomment to clean up after yourself
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void execute() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (IPhase p : this.phases) {
			p.execute();
		}
		System.out.println("done");
	}

	public void addBuilderClass(Class<? extends IBuilder> newBuilder) {
		this.builderClasses.add(newBuilder);
	}

	public ArrayList<Class<? extends IBuilder>> getBuilderClasses() {
		return this.builderClasses;
	}

	public boolean removeBuilderClass(Class<? extends IBuilder> toRemove) {
		return this.builderClasses.remove(toRemove);
	}

	public HashMap<String, IClassRecord> getRecords() {
		return this.records;
	}

	public void addClass(String className) {
		this.classNames.add(className);
	}

	public boolean removeClass(String className) {
		return this.classNames.remove(className);
	}

	public boolean hasClass(String className) {
		return this.classNames.contains(className);
	}

	public HashSet<String> getClassNames() {
		return this.classNames;
	}

	public String getUmlString() {
		return this.graph;
	}

	public void setProperties(Properties p) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.builderClasses = new ArrayList<Class<? extends IBuilder>>();
		this.classNames = new HashSet<String>();
		this.phases = new ArrayList<IPhase>();
		this.config = p;
		this.readInputClasses();
		this.readPhases();
		this.readInputFolder();
		this.readBuilders();
	}

	private void readBuilders() throws ClassNotFoundException {
		String buildString = (String) this.config.get("Builder-Classes");
		if (buildString != null) {
			String[] strs = buildString.split(", ");
			for (String str : strs) {
				Class<? extends IBuilder> clazz = (Class<? extends IBuilder>) Class.forName("dot." + str);
				this.addBuilderClass(clazz);
			}
		}
	}

	private void readInputFolder() {
		String path = this.config.getProperty("Input-Folder");
		File file = new File(path);
		for (File f : file.listFiles()) {
			this.walk(f, f.getName());
		}
		// this.walk(file, path);
		System.out.println(this.classNames);
	}

	private void walk(File file, String path) {
		File[] files = file.listFiles();
		if (files == null) {
			return;
		}
		for (File f : files) {
			if (f.isDirectory()) {
				this.walk(f, path + "." + f.getName());
			} else {
				if (f.getName().endsWith(".java")) {
					this.classNames.add(path + "." + f.getName().substring(0, f.getName().length() - 5));
				}
			}
		}
	}

	private void readPhases() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String phaseString = (String) this.config.get("Phases");
		if (phaseString != null) {
			String[] strs = phaseString.split(", ");
			for (String str : strs) {
				Class<? extends IPhase> clazz = (Class<? extends IPhase>) Class.forName("phases." + str);
				Constructor<? extends IPhase> constructor = clazz.getConstructor(this.getClass());
				this.addPhase(constructor.newInstance(this));
			}
		}
	}

	private void readInputClasses() {
		String classString = (String) this.config.get("Input-Classes");
		if (classString != null && !classString.equals("")) {
			String[] strs = classString.split(", ");
			for (String str : strs) {
				this.addClass(str);
			}
		}
	}

	public Properties getProperties() {
		return this.config;
	}

	public void addPhase(IPhase phase) {
		this.phases.add(phase);
	}

	public boolean removePhase(String phase) {
		return this.phases.remove(phase);
	}

	public boolean hasPhase(IPhase phase) {
		return this.phases.contains(phase);
	}

	public ArrayList<IPhase> getPhases() {
		return this.phases;
	}

	public String getProperty(String name) {
		return (String) this.config.get(name);
	}
}
