package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dot.AdapterBuilder;
import dot.AssociationBuilder;
import dot.ExtensionBuilder;
import dot.IBuilder;
import dot.ImplementsBuilder;
import dot.UmlBuilder;
import records.IClassRecord;

public class UmlWrapper {
    public final static String fontName = "Comic Sans MS";
    private HashSet<String> classNames;
    private HashMap<String, IClassRecord> records;
    private HashMap<String, IBuilder> decorators;
    private ArrayList<Class<? extends IBuilder>> builderClasses;
    public String graph;

    public UmlWrapper() {
        this(new String[0]);
    }

    public UmlWrapper(String[] classNames) {
        this.classNames = new HashSet<String>(Arrays.asList(classNames));
        this.records = new HashMap<String, IClassRecord>();
        this.decorators = new HashMap<String, IBuilder>();
        this.builderClasses = new ArrayList<Class<? extends IBuilder>>();
    }

    public void generateGraph() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.createGraph(this.graph);
    }

    public void load() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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

    public void generateUML(){
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

        try (                             final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE);) {
            writer.write(digraph);
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "temp.dot", "-o", "out.png");
        Map<String, String> env = pb.environment();
        // pb.directory();
        // System.out.println(System.getProperty("user.dir"));
        try {
            // Process p = pb.start();
            File log = new File("log");
            pb.redirectErrorStream(true);
            pb.redirectOutput(Redirect.appendTo(log));
            Process p = pb.start();
            // Files.delete(path);//uncomment to clean up after yourself
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
