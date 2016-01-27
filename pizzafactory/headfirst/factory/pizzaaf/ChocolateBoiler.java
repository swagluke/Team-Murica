package headfirst.factory.pizzaaf;

public class ChocolateBoiler {
	private boolean empty;
	private boolean boiled;
	public ChocolateBoiler(){
		empty = true;
		boiled = false;
	}

	public void fill(){
		if(isEmpty()){
			empty =false;
			boiled =true;
		}
	}
	public void drain(){
		if(!isEmpty()&&isBoiled())
			empty =true;
	}
	public void boil(){
		if(!isEmpty()&&isBoiled()){
			empty =true;}
	}
	public boolean isEmpty(){
		return empty;

	}
	public boolean isBoiled(){
		return boiled;
	}
}
