package snake.a;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PicnicTile {

    private Rectangle _rect;
    private TileContents _contents;
    private Food _food;
    private Color _originalColor;
    private int _row;
    private int _col;

    public PicnicTile(Pane gamePane, boolean odd, int row, int col) {
        _row = row;
        _col = col;
        _rect = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH, Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        if (odd) {
            _originalColor = Color.GREEN;
        } else {
            _originalColor = Color.GREENYELLOW;
        }
        this.reset();
        gamePane.getChildren().add(_rect);
    }

    public void reset() {
        _contents = TileContents.EMPTY;
        _rect.setFill(_originalColor);
        if (_food != null) {
            _food.eat();
        }
        _food = null;
    }

    public void addSnake() {
        _contents = TileContents.SNAKE;
        _rect.setFill(Color.BLACK);
    }

    public void addFood(Food food) {
        _food = food;
        _contents = TileContents.FOOD;
    }

    public int eatFood() {
        int score = _food.eat();
        _food = null;
        _contents = TileContents.EMPTY;
        return score;
    }

    public TileContents getContents() {
        return _contents;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }
}
