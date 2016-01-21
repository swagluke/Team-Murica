package sdedit;

public class Bar {
	private Foo foo;
	public int numb;

	public Bar(Foo foo) {
		this.foo = foo;
		this.methodA();
	}
	
	public void methodA() {
		this.numb = methodB(5);
	}
	
	public int methodB(int numb) {
		return this.foo.addOne(numb);
	}
}
