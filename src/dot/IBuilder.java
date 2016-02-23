package dot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;

public interface IBuilder {
	/**
	 * Used to get the visitor of the previous builder, used to successively load a large visitor
	 * @return
	 */
	public ClassVisitor getVisitor();
	/**
	 * called once on the outer builder, used to load all builders in the chain
	 * @return
	 */
	IClassRecord build();
	IClassRecord build(ClassVisitor visitor);
	String getClassUML();
	HashSet<String> getClassList();
	ClassRecord getClassRecord();
	public IClassRecord applyDecoration(IClassRecord record, Properties properties);
	public void calculatePattern(IClassRecord record, HashMap<String, IClassRecord> records, Properties properties);
	public Properties getProperties();
}
