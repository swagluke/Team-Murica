package records;

public class ClassNameStringWrapper{
	String string;
	public ClassNameStringWrapper(String s){
		string = s;
	}
	@Override
	public int hashCode(){
		System.out.print("Hashcode String = "+string);
		if(string.charAt(0) == '/'){
//			System.out.println(string.substring(1).hashCode());
			return string.substring(1).hashCode();
		}else{
//			System.out.println(string.hashCode());
			return string.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object o){
		String s = (string.charAt(0) == '/')?string.substring(1, string.length()):string;
		String os=((o instanceof ClassNameStringWrapper)&&(((ClassNameStringWrapper)o).string.charAt(0) == '/'))?((ClassNameStringWrapper)o).string.substring(1):((ClassNameStringWrapper)o).string;
//		System.out.println("Comparing: string "+string+" and object "+((ClassNameStringWrapper)o).string+" = " +s.equals(os));
		return s.equals(os);
	}
	@Override
	public String toString(){
		return this.string;
	}
}
