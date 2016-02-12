package src.problem.sprites;

public class BalledCross extends ACompositeSprite {

	public BalledCross(double x, double y, double width, double height) {
		this.add(new RectangleSprite(x + width / 4, y + height / 4, width / 2, height / 2));

		this.add(new CircleSprite(x, y + 3 * height / 8, width / 4, height / 4));
		this.add(new CircleSprite(x + 3 * width / 8, y, width / 4, height / 4));
		this.add(new CircleSprite(x + 3 * width / 4, y + 3 * height / 8, width / 4, height / 4));
		this.add(new CircleSprite(x + 3 * width / 8, y + 3 * height / 4, width / 4, height / 4));
	}
}
