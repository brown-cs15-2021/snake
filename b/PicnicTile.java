package snake.b;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PicnicTile {

    private Rectangle rect;
    private TileContents contents;
    private Food food;
    private Color originalColor;
    private int row;
    private int col;

    public PicnicTile(Pane gamePane, Color color, int row, int col) {
        this.row = row;
        this.col = col;
        this.rect = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH, Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        this.originalColor = color;
        this.reset();
        gamePane.getChildren().add(this.rect);
    }

    public void reset() {
        this.contents = TileContents.EMPTY;
        this.rect.setFill(this.originalColor);
        if (this.food != null) {
            this.food.eat();
        }
        this.food = null;
    }

    public void addSnake() {
        this.contents = TileContents.SNAKE;
    }

    public void addFood(Food food) {
        this.food = food;
        this.contents = TileContents.FOOD;
    }

    public int eatFood() {
        int score = this.food.eat();
        this.food = null;
        this.contents = TileContents.EMPTY;
        return score;
    }

    public TileContents getContents() {
        return this.contents;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
