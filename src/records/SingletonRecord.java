package records;

import java.util.HashSet;

import org.objectweb.asm.Type;

public class SingletonRecord implements IClassRecord {

	private IClassRecord innerRecord;

	public SingletonRecord(IClassRecord innerRecord) {
		this.innerRecord = innerRecord;
	}

	@Override
	public String getClassUml() {
		String innerUML = innerRecord.getClassUml();
		if(!this.isSingleton()){
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
		ClassRecord baseRecord = this.getBaseRecord();
		boolean hasField = false;
		for (InstanceVarRecord field : baseRecord.getFieldsList()) {
			if (field.getAccess() == 10 && field.getType().equals(baseRecord.getClassName().replace("/", "."))) {
//				System.out.println("has singleton field");
				hasField = true;
				break;
			}
//			System.out.println(field.getAccess());
//			System.out.println(field.getType());
//			System.out.println(baseRecord.getClassName());
		}
		boolean hasPublicConstructor = false;
		boolean hasGetter = false;
		for (MethodRecord method : baseRecord.getMethodsList()) {
			if (method.getAccess() == 1 && method.getName().equals("<init>")) {
//				System.out.println("has public constructor");
				hasPublicConstructor = true;
			}
			if (method.getAccess() == 9 && method.getReturnType().equals(baseRecord.getClassName().replace("/", "."))) {
//				System.out.println("has getter");
				hasGetter = true;
			}
//			System.out.println(method.getName());
//			System.out.println(method.getAccess());
//			System.out.println(method.getReturnType());
//			System.out.println();
		}
//		System.out.println("hasField: " + hasField + ", !hasPublicConstructor: " + !hasPublicConstructor + ", hasGetter: " + hasGetter);
		return hasField && !hasPublicConstructor && hasGetter;
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
}
