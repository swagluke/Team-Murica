package dot;

import java.util.HashMap;
import java.util.HashSet;

import org.objectweb.asm.Type;

import records.ExtendedClassRecord;
import records.IClassRecord;
import records.ImplementsClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

public class DecoratorBuilder extends APatternBuilder {
	public DecoratorBuilder(IBuilder b) {
		super(b);
	}

	@Override
	public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap) {
		try {
			boolean hasConstructorAndField = false;
			boolean extendsDecorator = false;
			Class<?> thisClass = Class
					.forName(Type.getObjectType(record.getBaseRecord().getClassName()).getClassName());

			// System.out.println("in is pattern");
			// System.out.println(record.getBaseRecord().getClassName());
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
			// System.out.println("possibles: " + "" + possibles);

			for (String possible : possibles) {
				Class<?> implemntingClass = Class.forName(Type.getObjectType(possible).getClassName());

				for (MethodRecord methodRecord : record.getBaseRecord().getMethodsList()) {
					if (methodRecord.getName().equals("<init>")) {
						// System.out.println(methodRecord.getName() + ": " +
						// methodRecord.getStypes());
						for (String arg : methodRecord.getStypes()) {
							try {
								if (Class.forName(arg).isAssignableFrom(thisClass)) {
									// System.out.println(arg + " is assignable from
									// " + record.getBaseRecord().getClassName());
									for (InstanceVarRecord field : record.getBaseRecord().getFieldsList()) {
										Class<?> fieldClass = Class.forName(field.getType());
										if (field.getType().equals(arg)) {
											hasConstructorAndField = true;
											// return true
											// System.out.println(true);
										}
									}
								}
							}catch(ClassNotFoundException e){
								System.err.println("Class Not Found: " + arg);
							}
						}
					}
				}
			}
			// for (InstanceVarRecord field :
			// record.getBaseRecord().getFieldsList()) {
			// String fieldName = field.getType();
			// System.out.println(fieldName);
			// }
			if (!hasConstructorAndField) {
				ExtendedClassRecord extendedClassRecord = (ExtendedClassRecord) record
						.tryConvertRecord(ExtendedClassRecord.class);
				String extendsName = extendedClassRecord.getExtendsName();
				// System.out.println(recordMap.get(extendsName.replace("/",
				// ".")));
				if (recordMap.get(extendsName.replace("/", ".")) != null) {
					// System.out.println("extends something");
					if (this.isPattern(recordMap.get(extendsName.replace("/", ".")), recordMap)) {
						extendsDecorator = true;
						// System.out.println(record.getBaseRecord().getClassName()
						// + " extends a decorator");
					}
				}
			}
			// has constructorfield or extends a decorator
			// System.out.println("name: " +
			// record.getBaseRecord().getClassName() + " hasConstructorAndField:
			// "
			// + hasConstructorAndField + " extendsDecorator: " +
			// extendsDecorator);
			return hasConstructorAndField || extendsDecorator;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("couldn't find a class");
			return false;
		}
	}

	@Override
	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap) {
		System.out.println(record.getBaseRecord().getClassName() + " is a decorator");
		record.getBaseRecord().setBoxColor("green");
		record.getBaseRecord().addPattern("Decorator");

		String extendsName = ((ExtendedClassRecord) record.tryConvertRecord(ExtendedClassRecord.class)).getExtendsName()
				.replace("/", ".");
		System.out.println(extendsName);
		if (recordHashMap.get(extendsName) != null) {
			// System.out.println("hello");
			IClassRecord extendedRecord = recordHashMap.get(extendsName);
			if (!this.isPattern(extendedRecord, recordHashMap)) {
				System.out.println(extendsName + " is the component");
			}
		}

		for (String implementsName : ((ImplementsClassRecord) record.tryConvertRecord(ImplementsClassRecord.class))
				.getImplementsList()) {
			implementsName = implementsName.replace("/", ".");
			if (recordHashMap.get(implementsName) != null) {
				IClassRecord implementsRecord = recordHashMap.get(implementsName);
				if (!this.isPattern(implementsRecord, recordHashMap)) {
					implementsRecord.getBaseRecord().addPattern("Component");
					implementsRecord.getBaseRecord().setBoxColor("green");
					record.getBaseRecord().addEdge(
			                record.getClassName().substring(record.getClassName().lastIndexOf('/') + 1)
			                        + " -> "
			                        + implementsName.substring(implementsName.lastIndexOf('.') + 1)
			                        + "[label=\"<<decorates>>\"]"
			        );
					System.out.println(implementsName + " is the component");
				}
			}
		}
	}

	// /**
	// *
	// * @param visitor
	// * @return
	// */
	// @Override
	// public ClassRecord build(ClassVisitor visitor) {
	// this.record = this.builder.build(visitor);
	// if (this.isPattern()) {
	// this.record.setBoxColor("red1");
	// this.record.addPattern("Decorator");
	//
	// }
	// for (String s : this.decoratorEdges) {
	// this.record.addEdge(s);
	// }
	//
	// return record;
	// }
	//
	// @Override
	// public boolean isPattern() {
	// ClassRecord baseRecord = this.record.getBaseRecord();
	// boolean hasConcreteImplementation = false;
	// boolean hasOtherClass = false;
	// boolean otherClassUsesConcrete = false;
	//
	// return hasConcreteImplementation && hasOtherClass &&
	// otherClassUsesConcrete;
	// }
	//
}
