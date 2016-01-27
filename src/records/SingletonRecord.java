package records;

import java.util.HashSet;

import org.objectweb.asm.Type;

public class SingletonRecord implements IClassRecord {

	private IClassRecord innerRecord;
	private boolean isSingleton;

	public SingletonRecord(IClassRecord innerRecord) {
		this.innerRecord = innerRecord;
	}

	@Override
	public String getClassUml() {
		String innerUML = innerRecord.getClassUml();
		if (!this.isSingleton) {
			return innerUML;
		}
		ClassRecord baseRecord = this.getBaseRecord();
		String className = this.getClassName();
		HashSet<InstanceVarRecord> fieldsList = baseRecord.getFieldsList();
		HashSet<MethodRecord> methodsList = baseRecord.getMethodsList();

		StringBuilder s = new StringBuilder();
		String[] n = className.split("/");
		String name = n[n.length - 1];
		s.append(name + " [color = \"blue1\" label = \"{" + name + "\\n\\<\\<Singleton\\>\\>" + "|");
		for (InstanceVarRecord f : fieldsList) {
			s.append("+" + f.getName() + " : " + f.getType() + "\\l\n");
		}
		s.append("|");
		for (MethodRecord m : methodsList) {
			if (m.getName().replaceAll("<.*?>", "").isEmpty())
				continue;
			s.append("+ ");
			s.append(m.getName().replaceAll("<.*?>", ""));
			for (Type t : m.getArgTypes()) {
				s.append(t.getClassName() + " ");
			}
			s.append(" : ");
			s.append(m.getReturnType() + "\\l\n");
		}
		s.append("}\"]");
		s.append(name + " -> " + name + "\n");

		return s.toString();
	}

	public boolean isSingleton() {
		return this.isSingleton;
	}

	@Override
	public String getClassName() {
		return this.innerRecord.getClassName();
	}

	@Override
	public HashSet<String> getClassList() {
		return this.innerRecord.getClassList();
	}

	@Override
	public IClassRecord getInnerRecord() {
		return this.innerRecord;
	}

	@Override
	public ClassRecord getBaseRecord() {
		return this.innerRecord.getBaseRecord();
	}

	public void setIsSingleton(boolean b) {
		this.isSingleton = b;
	}
}
