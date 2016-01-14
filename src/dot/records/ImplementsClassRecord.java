package dot.records;

import java.util.ArrayList;

public class ImplementsClassRecord implements IClassRecord {
	
	private ArrayList<String> implementsList;
	private IClassRecord innerRecord;
	public ImplementsClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setImplementsList(ArrayList<String> list) {
		this.implementsList = list;
	}
	public ArrayList<String> getImplementsList(){
		return implementsList;
	}

}
