package src.problem.sprites;

public class TowerSprite extends ACompositeSprite{

	public TowerSprite(double x, double y, double width, double height) {
		this.add(new RectangleSprite(x, y + height / 2, width, height / 2));
		this.add(new RectangleSprite(x + width / 4, y, width / 2, height / 2));
	}
}
