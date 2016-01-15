package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.objectweb.asm.Type;

import dot.records.ClassRecord;
import dot.records.InstanceVarRecord;
import dot.records.MethodRecord;

public class UmlBuilderTest {

	@Test
	public void testGettingClassUML() throws IOException, ClassNotFoundException {
		String className = "dot.AssociationBuilder";
		String expectedClassName = "dot/AssociationBuilder";
		String expectedExtendsName = "java/lang/Object";
		HashSet<MethodRecord> expectedMethods = new HashSet<MethodRecord>(Arrays.asList(new MethodRecord[] { 
//			new MethodRecord(1, "<init>", "void", new Type[0], new ArrayList<String>()),
			new MethodRecord(1, "<init>", "void", new Type[]{
					Type.getType(Class.forName("java.lang.String")), 
					Type.getType(Class.forName("java.util.HashSet")), 
					}, new ArrayList<String>(Arrays.asList(new String[] {
							"java.lang.String", "java.util.HashSet"
					}
			))),
			new MethodRecord(1, "<init>", "void", new Type[]{
					Type.getType(Class.forName("dot.IBuilder")), 
					}, new ArrayList<String>(Arrays.asList(new String[] {
							"dot.IBuilder"
					}
			))),
			new MethodRecord(1, "getClassList", "java.util.HashSet", new Type[0], new ArrayList<String>()),
			new MethodRecord(1, "build", "dot.records.IClassRecord", new Type[]{
					Type.getType(Class.forName("org.objectweb.asm.ClassVisitor")), 
					}, new ArrayList<String>(Arrays.asList(new String[] {
							"org.objectweb.asm.ClassVisitor"
					}
			))),
			new MethodRecord(1, "build", "dot.records.IClassRecord", new Type[0], new ArrayList<String>()),
			new MethodRecord(1, "getVisitor", "org.objectweb.asm.ClassVisitor", new Type[0], new ArrayList<String>()),
			new MethodRecord(1, "getClassUML", "java.lang.String", new Type[0], new ArrayList<String>()),
		}));
		HashSet<InstanceVarRecord> expectedFieldsList = new HashSet<InstanceVarRecord>(Arrays.asList(new InstanceVarRecord[] {
			new InstanceVarRecord("builder", "dot.IBuilder", 2),
			new InstanceVarRecord("visitor", "asm.ClassFieldSignatureVisitor", 2),
			new InstanceVarRecord("associationRecord", "dot.records.AssociationClassRecord", 2)
		}));
		HashSet<String> expectedImplementsList = new HashSet<String>(Arrays.asList(new String[] {"dot/IBuilder"}));
		assertBuild(className, expectedClassName, expectedExtendsName, expectedMethods, expectedFieldsList,
				expectedImplementsList);
	}

	public void assertBuild(String className, String expectedClassName, String expectedExtendsName,
			HashSet<MethodRecord> expectedMethods, HashSet<InstanceVarRecord> expectedFieldsList,
			HashSet<String> expectedImplementsList) {
		UmlBuilder builder = new UmlBuilder(className, new HashSet<String>(Arrays.asList(className)));
		ClassRecord record = (ClassRecord) builder.build();
//		for (int i=0; i<expectedFieldsList.size(); i++) {
//			System.out.println(expectedFieldsList.toArray()[i]);
//			System.out.println(record.getFieldsList().toArray()[i]);
//			System.out.println(expectedFieldsList.toArray()[i].equals(record.getFieldsList().toArray()[i]));
//			System.out.println();
//		}
//		for (MethodRecord r : record.getMethodsList()) {
//			System.out.println(r);
//		}
//		System.out.println();
//		for (MethodRecord r : expectedMethods) {
//			System.out.println(r);
//		}
		assertEquals(expectedClassName, record.getClassName());
		assertEquals(expectedExtendsName, record.getExtendsName());
		assertEquals(expectedMethods, record.getMethodsList());
		assertEquals(expectedFieldsList, record.getFieldsList());
		assertEquals(expectedImplementsList, record.getImplementsList());
	}
}
