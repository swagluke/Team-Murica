package dot;

import org.objectweb.asm.Type;
import records.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by MillerLJ on 2/5/2016.
 */
public class CompositeBuilder extends APatternBuilder {
	public CompositeBuilder(IBuilder b) {
		super(b);
	}

	/**
	 * return true iff this class is a COMPOSITE, not a component or leaf
	 *
	 * @param record
	 * @param recordMap
	 * @return
	 */
	@Override
	public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap) {
		try {
			boolean hasConstructorAndField = false;
			boolean extendsDecorator = false;
			Class<?> thisClass = Class
					.forName(Type.getObjectType(record.getBaseRecord().getClassName()).getClassName());

			HashSet<String> possibles = new HashSet<String>();
			if (record.canConvertRecord(ExtendedClassRecord.class)) {
				ExtendedClassRecord extendedClassRecord = (ExtendedClassRecord) record
						.tryConvertRecord(ExtendedClassRecord.class);
				possibles.add(extendedClassRecord.getExtendsName());
			}
			if (record.canConvertRecord(ImplementsClassRecord.class)) {
				ImplementsClassRecord implementsClassRecord = (ImplementsClassRecord) record
						.tryConvertRecord(ImplementsClassRecord.class);
				possibles.addAll(implementsClassRecord.getImplementsList());
			}
			possibles.removeIf(p -> !record.getClassList().contains(p.replace("/", ".")));

			for (String possible : possibles) {
				Class<?> implmentingClass = Class.forName(Type.getObjectType(possible).getClassName());

				for (InstanceVarRecord fieldRecord : record.getBaseRecord().getFieldsList()) {
					System.out.println("Checking: " + fieldRecord);
					// if
					// (recordMap.containsKey(fieldRecord.getType().replace("[]",
					// ""))) {
					try {
						if (Class.forName("java.util.Collection")
								.isAssignableFrom(Class.forName(fieldRecord.getType().replace("[]", "")))
								|| fieldRecord.getType().contains("[]")) {
							System.out.println("      Is Assignable");
							UsesClassRecord useRecord = (UsesClassRecord) record.tryConvertRecord(UsesClassRecord.class);
							System.out.println(useRecord.getUsesNamesList());
							System.out.println(useRecord.getUsesNamesList().contains(possible));
							if (implmentingClass
									.isAssignableFrom(Class.forName(fieldRecord.getType().replace("[]", ""))) || useRecord.getUsesNamesList().contains(possible)) {
								ClassRecord r = recordMap.get(possible.replace("/", ".")).getBaseRecord();
								System.out.println("      Is super-field");
								return true;
							}
						}
					} catch (ClassNotFoundException e) {
						// ignore
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap) {
		try {
			boolean hasConstructorAndField = false;
			boolean extendsDecorator = false;
			Class<?> thisClass = Class
					.forName(Type.getObjectType(record.getBaseRecord().getClassName()).getClassName());

			HashSet<String> possibles = new HashSet<String>();
			if (record.canConvertRecord(ExtendedClassRecord.class)) {
				ExtendedClassRecord extendedClassRecord = (ExtendedClassRecord) record
						.tryConvertRecord(ExtendedClassRecord.class);
				possibles.add(extendedClassRecord.getExtendsName());
			}
			if (record.canConvertRecord(ImplementsClassRecord.class)) {
				ImplementsClassRecord implementsClassRecord = (ImplementsClassRecord) record
						.tryConvertRecord(ImplementsClassRecord.class);
				possibles.addAll(implementsClassRecord.getImplementsList());
			}
			possibles.removeIf(p -> !record.getClassList().contains(p.replace("/", ".")));

			for (String possible : possibles) {
				Class<?> implementingClass = Class.forName(Type.getObjectType(possible).getClassName());

				for (InstanceVarRecord fieldRecord : record.getBaseRecord().getFieldsList()) {
					System.out.println("Checking: " + fieldRecord);
					// if
					// (recordHashMap.containsKey(fieldRecord.getType().replace("[]",
					// ""))) {
					System.out.println(fieldRecord);
					if (Class.forName("java.util.Collection")
							.isAssignableFrom(Class.forName(fieldRecord.getType().replace("[]", "")))
							|| fieldRecord.getType().contains("[]")) {
						System.out.println("      Is Assignable");
						if (implementingClass
								.isAssignableFrom(Class.forName(fieldRecord.getType().replace("[]", "")))) {
							ClassRecord implementsRecord = recordHashMap.get(possible.replace("/", "."))
									.getBaseRecord();
							System.out.println("      Is super-field");
							implementsRecord.addPattern("Component");
							implementsRecord.setBoxColor("yellow");
							record.getBaseRecord().addPattern("Composite");
							record.getBaseRecord().setBoxColor("yellow");
							// time to find leafs
							for (IClassRecord possibleLeafRecord : recordHashMap.values()) {
								if (implementingClass
										.isAssignableFrom(Class.forName(
												possibleLeafRecord.getBaseRecord().getClassName().replace("/", ".")))
										&& !record.equals(possibleLeafRecord)
										&& !implementsRecord.equals(possibleLeafRecord.getBaseRecord())) {
									possibleLeafRecord.getBaseRecord().addPattern("Leaf");
									possibleLeafRecord.getBaseRecord().setBoxColor("yellow");
								}
							}
						}
						// }
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
