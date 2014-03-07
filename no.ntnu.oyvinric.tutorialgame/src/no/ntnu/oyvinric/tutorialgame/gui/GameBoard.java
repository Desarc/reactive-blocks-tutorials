package no.ntnu.oyvinric.tutorialgame.gui;

import tile.CharacterTile;
import tile.Tile;
import level.GameLevel;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	public static final int windowWidth = 600;
	public static final int windowHeight = 480;
	public static final float tileHeight = 13f;
	public static final float tileWidth = 32f;
	public static final float stackingHeight = 0f;
	public static final int horizontalLeftLimit = 20;
	public static final int verticalUpperLimit = windowHeight-40;
	
	private GameLevel level;
	private UserInterface userInterface;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public GameBoard(GameLevel level) {
		this.level = level;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, windowWidth, windowHeight);
		camera.update();
		
		batch = new SpriteBatch();
	}
	
	private void drawLevel() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		for (Array<Array<Tile>> gridLayer : level.getLevelGrid()) {
			for (Array<Tile> gridRow : gridLayer) {
				for (Tile tile : gridRow) {
					if (tile.getType() != Tile.EMPTY) {
						batch.draw(tile.getImage(), tile.getCoordsX(), tile.getCoordsY(), tile.getOriginX(), tile.getOriginY(), tile.getWidth(), tile.getHeight(), tile.getScaleFactor(), tile.getScaleFactor(), tile.getRotation());
					}
				}
			}
		}
		
		batch.end();
		
		batch.begin();
		for (CharacterTile tile : level.getCharacterTiles()) {
			batch.draw(tile.getImage(), tile.getCoordsX(), tile.getCoordsY());
		}
		batch.end();
	}
	
	public void redraw() {
		drawLevel();
	}

}
