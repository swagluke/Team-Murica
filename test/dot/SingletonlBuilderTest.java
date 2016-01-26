package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import records.SingletonRecord;

public class SingletonlBuilderTest {

	@Test
	public void testNotASingleton() throws IOException, ClassNotFoundException {
		String className = "dot.AssociationBuilder";
		String expectedUml = "AssociationBuilder [label = \"{AssociationBuilder|+builder : dot.IBuilder\\l\n"
				+ "+visitor : asm.ClassFieldSignatureVisitor\\l\n"
				+ "+associationRecord : records.AssociationClassRecord\\l\n" + "|+ build : records.IClassRecord\\l\n"
				+ "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" + "+ getClassUML : java.lang.String\\l\n"
				+ "+ buildorg.objectweb.asm.ClassVisitor  : records.IClassRecord\\l\n"
				+ "+ getClassList : java.util.HashSet\\l\n" + "}\"]";
		assertSingleton(className, false, expectedUml);
	}

	@Test
	public void testIsSingleton() throws IOException, ClassNotFoundException {
		String className = "singleton.Singleton";
		String expectedUml = "Singleton [color = \"blue1\" label = \"{Singleton\\n\\<\\<Singleton\\>\\>|+instance : singleton.Singleton\\l\n"
				+ "|+ getInstance : singleton.Singleton\\l\n" + "}\"]Singleton -> Singleton\n";
		assertSingleton(className, true, expectedUml);
	}

	@Test
	public void testRuntimeIsSingleton() throws IOException, ClassNotFoundException {
		String className = "java.lang.Runtime";
		String expectedUml = "Runtime [color = \"blue1\" label = \"{Runtime\\n\\<\\<Singleton\\>\\>|+currentRuntime : java.lang.Runtime\\l\n"
				+ "|+ getLocalizedOutputStreamjava.io.OutputStream  : java.io.OutputStream\\l\n"
				+ "+ addShutdownHookjava.lang.Thread  : void\\l\n" + "+ runFinalization0 : void\\l\n"
				+ "+ gc : void\\l\n"
				+ "+ execjava.lang.String java.lang.String[] java.io.File  : java.lang.Process\\l\n"
				+ "+ load0java.lang.Class java.lang.String  : void\\l\n" + "+ freeMemory : long\\l\n"
				+ "+ exitint  : void\\l\n" + "+ getRuntime : java.lang.Runtime\\l\n"
				+ "+ availableProcessors : int\\l\n"
				+ "+ execjava.lang.String[] java.lang.String[]  : java.lang.Process\\l\n"
				+ "+ loadjava.lang.String  : void\\l\n"
				+ "+ execjava.lang.String[] java.lang.String[] java.io.File  : java.lang.Process\\l\n"
				+ "+ traceInstructionsboolean  : void\\l\n" + "+ removeShutdownHookjava.lang.Thread  : boolean\\l\n"
				+ "+ loadLibrary0java.lang.Class java.lang.String  : void\\l\n" + "+ totalMemory : long\\l\n"
				+ "+ execjava.lang.String  : java.lang.Process\\l\n" + "+ loadLibraryjava.lang.String  : void\\l\n"
				+ "+ haltint  : void\\l\n" + "+ execjava.lang.String java.lang.String[]  : java.lang.Process\\l\n"
				+ "+ runFinalization : void\\l\n" + "+ traceMethodCallsboolean  : void\\l\n"
				+ "+ runFinalizersOnExitboolean  : void\\l\n" + "+ maxMemory : long\\l\n"
				+ "+ execjava.lang.String[]  : java.lang.Process\\l\n"
				+ "+ getLocalizedInputStreamjava.io.InputStream  : java.io.InputStream\\l\n"
				+ "}\"]Runtime -> Runtime\n";
		assertSingleton(className, true, expectedUml);
	}

	@Test
	public void testCalendarIsNotASingleton() throws IOException, ClassNotFoundException {
		String className = "java.util.Calendar";
		String expectedUml = "Calendar [label = \"{Calendar|+DAY_OF_WEEK : int\\l\n" + "+JULY : int\\l\n"
				+ "+PM : int\\l\n" + "+SHORT_FORMAT : int\\l\n" + "+DECEMBER : int\\l\n"
				+ "+minimalDaysInFirstWeek : int\\l\n" + "+MONTH : int\\l\n" + "+areAllFieldsSet : boolean\\l\n"
				+ "+YEAR_MASK : int\\l\n" + "+WEEK_OF_MONTH : int\\l\n" + "+MINUTE : int\\l\n"
				+ "+isTimeSet : boolean\\l\n" + "+LONG_STANDALONE : int\\l\n" + "+NARROW_STANDALONE : int\\l\n"
				+ "+HOUR_MASK : int\\l\n" + "+DATE : int\\l\n" + "+DAY_OF_WEEK_IN_MONTH : int\\l\n"
				+ "+WEDNESDAY : int\\l\n" + "+MINIMUM_USER_STAMP : int\\l\n" + "+WEEK_OF_YEAR_MASK : int\\l\n"
				+ "+LONG : int\\l\n" + "+time : long\\l\n" + "+DST_OFFSET : int\\l\n" + "+SEPTEMBER : int\\l\n"
				+ "+ERA_MASK : int\\l\n" + "+SUNDAY : int\\l\n" + "+serialVersionOnStream : int\\l\n"
				+ "+MILLISECOND_MASK : int\\l\n" + "+AUGUST : int\\l\n"
				+ "+cachedLocaleData : java.util.concurrent.ConcurrentMap\\l\n" + "+WEEK_OF_YEAR : int\\l\n"
				+ "+FIELD_COUNT : int\\l\n" + "+NARROW_FORMAT : int\\l\n" + "+lenient : boolean\\l\n" + "+AM : int\\l\n"
				+ "+HOUR_OF_DAY_MASK : int\\l\n" + "+TUESDAY : int\\l\n" + "+THURSDAY : int\\l\n"
				+ "+DAY_OF_YEAR_MASK : int\\l\n" + "+SECOND_MASK : int\\l\n" + "+MINUTE_MASK : int\\l\n"
				+ "+ERA : int\\l\n" + "+JANUARY : int\\l\n" + "+zone : java.util.TimeZone\\l\n" + "+AM_PM : int\\l\n"
				+ "+DAY_OF_MONTH : int\\l\n" + "+areFieldsSet : boolean\\l\n" + "+ALL_STYLES : int\\l\n"
				+ "+DST_OFFSET_MASK : int\\l\n" + "+$assertionsDisabled : boolean\\l\n" + "+FEBRUARY : int\\l\n"
				+ "+UNSET : int\\l\n" + "+isSet : boolean[]\\l\n" + "+HOUR_OF_DAY : int\\l\n"
				+ "+DAY_OF_YEAR : int\\l\n" + "+fields : int[]\\l\n" + "+HOUR : int\\l\n" + "+MAY : int\\l\n"
				+ "+stamp : int[]\\l\n" + "+SHORT : int\\l\n" + "+UNDECIMBER : int\\l\n" + "+APRIL : int\\l\n"
				+ "+sharedZone : boolean\\l\n" + "+ZONE_OFFSET : int\\l\n" + "+DAY_OF_WEEK_IN_MONTH_MASK : int\\l\n"
				+ "+currentSerialVersion : int\\l\n" + "+NOVEMBER : int\\l\n" + "+DATE_MASK : int\\l\n"
				+ "+AM_PM_MASK : int\\l\n" + "+SATURDAY : int\\l\n" + "+LONG_FORMAT : int\\l\n"
				+ "+ALL_FIELDS : int\\l\n" + "+nextStamp : int\\l\n" + "+COMPUTED : int\\l\n"
				+ "+FIELD_NAME : java.lang.String[]\\l\n" + "+STANDALONE_MASK : int\\l\n" + "+firstDayOfWeek : int\\l\n"
				+ "+MONTH_MASK : int\\l\n" + "+YEAR : int\\l\n" + "+JUNE : int\\l\n" + "+OCTOBER : int\\l\n"
				+ "+DAY_OF_MONTH_MASK : int\\l\n" + "+serialVersionUID : long\\l\n" + "+WEEK_OF_MONTH_MASK : int\\l\n"
				+ "+SECOND : int\\l\n" + "+MILLISECOND : int\\l\n" + "+MARCH : int\\l\n" + "+MONDAY : int\\l\n"
				+ "+SHORT_STANDALONE : int\\l\n" + "+FRIDAY : int\\l\n" + "+DAY_OF_WEEK_MASK : int\\l\n"
				+ "+ZONE_OFFSET_MASK : int\\l\n" + "|+ getDisplayNameint int java.util.Locale  : java.lang.String\\l\n"
				+ "+ toStandaloneStyleint  : int\\l\n" + "+ selectFields : int\\l\n" + "+ rollint int  : void\\l\n"
				+ "+ getWeeksInWeekYear : int\\l\n" + "+ getFirstDayOfWeek : int\\l\n"
				+ "+ toString : java.lang.String\\l\n"
				+ "+ getDisplayNamesImplint int java.util.Locale  : java.util.Map\\l\n" + "+ compareTolong  : int\\l\n"
				+ "+ createCalendarjava.util.TimeZone java.util.Locale  : java.util.Calendar\\l\n"
				+ "+ getInstance : java.util.Calendar\\l\n" + "+ getActualMinimumint  : int\\l\n"
				+ "+ afterjava.lang.Object  : boolean\\l\n" + "+ isLenient : boolean\\l\n"
				+ "+ compareTojava.lang.Object  : int\\l\n" + "+ computeFields : void\\l\n"
				+ "+ getTimeInMillis : long\\l\n" + "+ isExternallySetint  : boolean\\l\n"
				+ "+ getAvailableLocales : java.util.Locale[]\\l\n" + "+ invalidateWeekFields : void\\l\n"
				+ "+ isSetint  : boolean\\l\n" + "+ setTimeInMillislong  : void\\l\n"
				+ "+ getFieldNameint  : java.lang.String\\l\n" + "+ getMaximumint  : int\\l\n"
				+ "+ getGreatestMinimumint  : int\\l\n" + "+ getAvailableCalendarTypes : java.util.Set\\l\n"
				+ "+ setint int int  : void\\l\n"
				+ "+ appendValuejava.lang.StringBuilder java.lang.String boolean long  : void\\l\n"
				+ "+ getTime : java.util.Date\\l\n" + "+ writeObjectjava.io.ObjectOutputStream  : void\\l\n"
				+ "+ complete : void\\l\n" + "+ addint int  : void\\l\n" + "+ clear : void\\l\n"
				+ "+ toInstant : java.time.Instant\\l\n" + "+ isNarrowStyleint  : boolean\\l\n"
				+ "+ isFullyNormalized : boolean\\l\n" + "+ setMinimalDaysInFirstWeekint  : void\\l\n"
				+ "+ isNarrowFormatStyleint  : boolean\\l\n" + "+ computeTime : void\\l\n"
				+ "+ setint int int int int  : void\\l\n" + "+ setZoneSharedboolean  : void\\l\n"
				+ "+ setFieldsComputedint  : void\\l\n" + "+ getMinimumint  : int\\l\n"
				+ "+ getInstancejava.util.Locale  : java.util.Calendar\\l\n" + "+ rollint boolean  : void\\l\n"
				+ "+ beforejava.lang.Object  : boolean\\l\n" + "+ aggregateStampint int  : int\\l\n"
				+ "+ getLeastMaximumint  : int\\l\n"
				+ "+ getFieldStringsint int java.text.DateFormatSymbols  : java.lang.String[]\\l\n"
				+ "+ hashCode : int\\l\n" + "+ setWeekDateint int int  : void\\l\n"
				+ "+ checkDisplayNameParamsint int int int java.util.Locale int  : boolean\\l\n"
				+ "+ getBaseStyleint  : int\\l\n" + "+ compareTojava.util.Calendar  : int\\l\n"
				+ "+ setTimejava.util.Date  : void\\l\n" + "+ getSetStateFields : int\\l\n"
				+ "+ isFieldSetint int  : boolean\\l\n" + "+ isStandaloneStyleint  : boolean\\l\n"
				+ "+ internalSetint int  : void\\l\n" + "+ getCalendarType : java.lang.String\\l\n"
				+ "+ updateTime : void\\l\n" + "+ getTimeZone : java.util.TimeZone\\l\n"
				+ "+ setint int int int int int  : void\\l\n" + "+ isWeekDateSupported : boolean\\l\n"
				+ "+ setint int  : void\\l\n" + "+ getMinimalDaysInFirstWeek : int\\l\n"
				+ "+ getInstancejava.util.TimeZone java.util.Locale  : java.util.Calendar\\l\n"
				+ "+ setUnnormalized : void\\l\n" + "+ internalGetint  : int\\l\n"
				+ "+ setFieldsNormalizedint  : void\\l\n" + "+ getActualMaximumint  : int\\l\n"
				+ "+ getMillisOfjava.util.Calendar  : long\\l\n" + "+ getint  : int\\l\n"
				+ "+ equalsjava.lang.Object  : boolean\\l\n" + "+ setTimeZonejava.util.TimeZone  : void\\l\n"
				+ "+ getZone : java.util.TimeZone\\l\n" + "+ getWeekYear : int\\l\n"
				+ "+ setLenientboolean  : void\\l\n" + "+ readObjectjava.io.ObjectInputStream  : void\\l\n"
				+ "+ getDisplayNamesint int java.util.Locale  : java.util.Map\\l\n"
				+ "+ setFirstDayOfWeekint  : void\\l\n" + "+ isPartiallyNormalized : boolean\\l\n"
				+ "+ setWeekCountDatajava.util.Locale  : void\\l\n" + "+ clearint  : void\\l\n"
				+ "+ getInstancejava.util.TimeZone  : java.util.Calendar\\l\n" + "+ clone : java.lang.Object\\l\n"
				+ "+ adjustStamp : void\\l\n" + "}\"]";
		assertSingleton(className, false, expectedUml);
	}

	@Test
	public void testFilterInputStreamIsNotASingleton() throws IOException, ClassNotFoundException {
		String className = "java.io.FilterInputStream";
		String expectedUml = "FilterInputStream [label = \"{FilterInputStream|+in : java.io.InputStream\\l\n"
				+ "|+ read : int\\l\n" + "+ readbyte[]  : int\\l\n" + "+ close : void\\l\n"
				+ "+ readbyte[] int int  : int\\l\n" + "+ skiplong  : long\\l\n" + "+ markSupported : boolean\\l\n"
				+ "+ markint  : void\\l\n" + "+ reset : void\\l\n" + "+ available : int\\l\n" + "}\"]";
		assertSingleton(className, false, expectedUml);
	}

	public void assertSingleton(String className, boolean expectedIsSingleton, String expectedUml) {
		SingletonBuilder builder = new SingletonBuilder(className, new HashSet<String>(Arrays.asList(className)));
		SingletonRecord record = (SingletonRecord) builder.build();
		assertEquals(expectedIsSingleton, record.isSingleton());
		assertEquals(expectedUml, record.getClassUml());
	}
}
