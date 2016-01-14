package dot.records;


public class ExtendedClassRecord implements IClassRecord {

	IClassRecord record;
	private String extendsName;
	
	public ExtendedClassRecord(IClassRecord record) {
		this.record = record;
	}

	@Override
	public String getClassUml() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getExtendsName() {
		return extendsName;
	}

	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

}
