package dot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;

abstract public class AbstractBuilderDecorator implements IBuilder {
	protected IBuilder builder;
	protected IClassRecord record;

	public AbstractBuilderDecorator(String className, HashSet<String> classNames, Properties properties) {
		this(new UmlBuilder(className, classNames, properties));
	}

	public AbstractBuilderDecorator(IBuilder b) {
		this.builder = b;
	}

	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	public IClassRecord build(ClassVisitor visitor) {
		this.record = this.builder.build(visitor);
		this.record = this.applyDecoration(this.record, this.getProperties());
		return this.record;
	}

	abstract public ClassVisitor getVisitor();

	public String getClassUML() {
		return this.record.getClassUml();
	}

	public HashSet<String> getClassList() {
		return builder.getClassList();
	}

	public ClassRecord getClassRecord() {
		return this.builder.getClassRecord();
	}

	public abstract IClassRecord applyDecoration(IClassRecord record, Properties properties);

	public final void calculatePattern(IClassRecord record, HashMap<String, IClassRecord> records, Properties properties) {
		builder.calculatePattern(record,records, properties);
		if (this.isPattern(record, records, properties)) {
			this.applyPattern(record, records, properties);
		}
	}

	public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap, Properties properties) {
		return false;
	};

	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap, Properties properties) {
	};
	
	public Properties getProperties() {
		return this.builder.getProperties();
	}

}
