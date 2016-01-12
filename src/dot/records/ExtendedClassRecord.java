package dot.records;


public class ExtendedClassRecord implements IClassRecord {

	IClassRecord record;
	
	public ExtendedClassRecord(IClassRecord record) {
		this.record = record;
	}

	@Override
	public String getClassUml() {
		// TODO Auto-generated method stub
		return null;
	}

}
