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

				for (MethodRecord methodRecord : record.getBaseRecord().getMethodsList()) {
					if (methodRecord.getName().equals("<init>")) {
						for (String arg : methodRecord.getStypes()) {
							try {
								if (Class.forName(arg).isAssignableFrom(thisClass)) {
									for (InstanceVarRecord field : record.getBaseRecord().getFieldsList()) {
										Class<?> fieldClass = Class.forName(field.getType());
										if (field.getType().equals(arg)) {
											hasConstructorAndField = true;
										}
									}
								}
							} catch (ClassNotFoundException e) {
								// ignore
							}
						}
					}
				}
			}
			if (!hasConstructorAndField) {
				ExtendedClassRecord extendedClassRecord = (ExtendedClassRecord) record
						.tryConvertRecord(ExtendedClassRecord.class);
				String extendsName = extendedClassRecord.getExtendsName();
				if (recordMap.get(extendsName.replace("/", ".")) != null) {
					if (this.isPattern(recordMap.get(extendsName.replace("/", ".")), recordMap)) {
						extendsDecorator = true;
					}
				}
			}
			return hasConstructorAndField || extendsDecorator;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap) {
		record.getBaseRecord().setBoxColor("green");
		record.getBaseRecord().addPattern("Decorator");

		String extendsName = ((ExtendedClassRecord) record.tryConvertRecord(ExtendedClassRecord.class)).getExtendsName()
				.replace("/", ".");
		if (recordHashMap.get(extendsName) != null) {
			IClassRecord extendedRecord = recordHashMap.get(extendsName);
			if (!this.isPattern(extendedRecord, recordHashMap)) {
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
					record.getBaseRecord()
							.addEdge(record.getClassName().substring(record.getClassName().lastIndexOf('/') + 1)
									+ " -> " + implementsName.substring(implementsName.lastIndexOf('.') + 1)
									+ "[label=\"<<decorates>>\"]");

					implementsRecord.getBaseRecord().addPattern("Component");
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
	// public ClassRecord load(ClassVisitor visitor) {
	// this.record = this.builder.load(visitor);
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
