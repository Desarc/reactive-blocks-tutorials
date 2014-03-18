package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GameLevel;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.tile.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	private GameLevel level;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private TextureAtlas miscTextures;
	private Array<GraphicsObject> miscObjects;
	
	
	public GameBoard(GameLevel level) {
		this.level = level;
		
		miscTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"misc.atlas"));
		miscObjects = new Array<GraphicsObject>();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.mainWindowWidth, Constants.mainWindowHeight);
		camera.update();
		
		batch = new SpriteBatch();
	}
	
	public void draw() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		for (Array<Array<Tile>> gridLayer : level.getLevelGrid()) {
			for (Array<Tile> gridRow : gridLayer) {
				for (Tile tile : gridRow) {
					if (tile.getType() != Constants.EMPTY_TILE) {
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
		for (GraphicsObject object : miscObjects) {
			if (object.isActive()) {
				batch.draw(object.getImage(), object.getX(), object.getY());
			}
		}
		
		batch.end();
	}
	
	public void characterSpeak(float coordsX, float coordsY, String message) {
		if (message.equals("Hello World!")) {
			GraphicsObject helloWorld = new GraphicsObject(coordsX+27, coordsY+37, miscTextures.findRegion("hello"));
			miscObjects.add(helloWorld);
		}
	}
	
	public void itemFound(float coordsX, float coordsY, GameObject item) {
		float startX = coordsX;
		float startY = coordsY+Constants.tileHeight*2;
		float endX = startX;
		float endY = startY+Constants.tileHeight*3;
		MovingGraphicsObject object = new MovingGraphicsObject(startX, startY, endX, endY, 0f, Constants.tileHeight*4, item.getImage(), true);
		miscObjects.add(object);
	}
	
	public void cleanUp() {
		miscTextures.dispose();
	}

}
