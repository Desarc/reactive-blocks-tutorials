package no.ntnu.oyvinric.tutorialgame.gui;

import level.GameLevel;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	public static final int windowWidth = 800;
	public static final int windowHeight = 480;
	public static final float tileHeight = 13f;
	public static final float tileWidth = 32f;
	public static final float stackingHeight = 0f;
	public static final int horizontalLeftLimit = 20;
	public static final int verticalUpperLimit = windowHeight-40;
	
	private TutorialGame parent;
	private GameLevel level;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private boolean active = true;
	
	public GameBoard(TutorialGame parent, GameLevel level) {
		this.parent = parent;
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
					batch.draw(tile.getImage(), tile.getX(), tile.getY(), tile.getOriginX(), tile.getOriginY(), tile.getWidth(), tile.getHeight(), tile.getScaleFactor(), tile.getScaleFactor(), tile.getRotation());
				}
			}
		}
		
		batch.end();
	}
	
	public void redraw() {
		drawLevel();
	}
	
	public void updateCharacterPosition(CharacterTile character, float dx, float dy) {
		character.move(dx, dy);
	}
	
	public void adjustCharacterPosition(CharacterTile character) {
		//character.alignWithGrid();
	}

	public void turnCharacterLeft(CharacterTile character) {
		character.rotate(90);
	}

	public void turnCharacterRight(CharacterTile character) {
		character.rotate(-90);
	}

	public void turnCharacterAround(CharacterTile character) {
		character.rotate(180);
	}

}
