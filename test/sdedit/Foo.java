package sdedit;

public class Foo {
	private int numb;

	public Foo() {
		Bar bar = new Bar(this);
		this.numb = this.addOne(bar.numb);
	}
	
	public int addOne(int numb) {
		return numb + 1;
	}
}
