package no.ntnu.oyvinric.tutorialgame.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import no.ntnu.oyvinric.tutorialgame.core.Constants.CharacterName;
import no.ntnu.oyvinric.tutorialgame.core.Constants.Direction;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.GrowingAnimation;
import no.ntnu.oyvinric.tutorialgame.hud.UserInterface;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.core.Constants.ItemType;
import no.ntnu.oyvinric.tutorialgame.item.Key;
import no.ntnu.oyvinric.tutorialgame.level.GameLevel;
import no.ntnu.oyvinric.tutorialgame.level.Level1;
import no.ntnu.oyvinric.tutorialgame.level.Level2;
import no.ntnu.oyvinric.tutorialgame.level.Level3;
import no.ntnu.oyvinric.tutorialgame.level.Level4;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class TutorialGameApplication implements ApplicationListener {

	private GameBoard board;
	private GameLevel level;
	private UserInterface userInterface;
	private int levelNumber;
	private CharacterTile malcolm, lisa, andrew;
	private Array<CharacterTile> gameCharacters;
	private TextureAtlas fullscreens;
	private GrowingAnimation winScreen;
	private SpriteBatch batch;
	
	//private Sound winSound;
	//private Music gameTheme;
	
	private ArrayMap<WinCondition, Boolean> winConditions;
	
	private int totalStars = 0;
	private int collectedStars = 0;
	
	public TutorialGameApplication(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	@Override
	public void create() {
		
		batch = new SpriteBatch();
		
		fullscreens = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"fullscreens.atlas"));
		winScreen = new GrowingAnimation(Constants.mainWindowWidth/2, Constants.mainWindowHeight/2, fullscreens.findRegion("win-screen"), 1f, Constants.mainWindowWidth/4, Constants.mainWindowHeight/4, Constants.mainWindowWidth, Constants.mainWindowHeight);
		
		switch(levelNumber) {
		case(1):
			level = new Level1();
			break;
		case(2):
			level = new Level2();
			break;
		case(3):
			level = new Level3();
			break;
		case(4):
			level = new Level4();
			break;
		}
		
		winConditions = new ArrayMap<Constants.WinCondition, Boolean>();
		Array<WinCondition> conditions = level.getWinConditions();
		for (WinCondition condition : conditions) {
			winConditions.put(condition, false);
		}
		
		totalStars = level.getNumberOfStars();
		board = new GameBoard(level);
		userInterface = new UserInterface(level.getUserInterfaceConfiguration());
		
		gameCharacters = new Array<CharacterTile>();
		for (CharacterTile character : level.getCharacterTiles()) {
			if (character.getName() == CharacterName.MALCOLM) {
				malcolm = character;
				gameCharacters.add(malcolm);
			}
			else if (character.getName() == CharacterName.LISA) {
				lisa = character;
				gameCharacters.add(lisa);
			}
			else if (character.getName() == CharacterName.ANDREW) {
				andrew = character;
				gameCharacters.add(andrew);
			}
		}
		
		//winSound = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_PATH+"drop.wav"));
		//gameTheme = Gdx.audio.newMusic(Gdx.files.internal(Constants.SOUND_PATH+"rain.mp3"));
		//gameTheme.setLooping(true);
		//gameTheme.play();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(Color.GRAY.r, Color.GRAY.g, Color.GRAY.b, Color.GRAY.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		updateGame();
		
		board.draw();
		userInterface.draw();
		if (levelCompleted()) {
			batch.begin();
			batch.draw(winScreen.getImage(), winScreen.getX(), winScreen.getY(), winScreen.getWidth(), winScreen.getHeight());
			batch.end();
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		//gameTheme.dispose();
		//winSound.dispose();
		level.cleanUp();
		board.cleanUp();
		userInterface.cleanUp();
	}
	
	private boolean levelCompleted() {
		for (int i = 0; i < winConditions.size; i++) {
			if (!winConditions.getValueAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	private void updateGame() {
		for (CharacterTile character : gameCharacters) {
			if (character.getMoving()) {
				updateCharacterPosition(character);
			}
		}
	}
	
	private void updateCharacterPosition(CharacterTile character) {
		float distanceX = 0;
		float distanceY = 0;
		if (character.getDirection() == Direction.WEST) {
			distanceX = -Constants.horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.EAST) {
			distanceX = Constants.horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.EAST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.SOUTH) {
			distanceY = -Constants.verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.NORTH) {
			distanceY = Constants.verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
	}
	
	public void moveCharacterForward(CharacterTile character) {
		character.alignWithGrid();
		character.setMoving(true);
	}
	
	public void stopCharacterMovement(CharacterTile character) {
		character.setMoving(false);
	}

	public void turnCharacterLeft(CharacterTile character) {
		character.alignWithGrid();
		character.rotate(90);
		if (character.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void turnCharacterRight(CharacterTile character) {
		character.alignWithGrid();
		character.rotate(-90);
		if (character.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void turnCharacterAround(CharacterTile character) {
		character.alignWithGrid();
		character.rotate(180);
		if (character.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}
	
	public GameObject characterInteract(CharacterTile character) {
		GameObject item = level.characterInteraction(character);
		if (item != null) {
			handleItem(item);
			board.itemFound(character.getCoordsX(), character.getCoordsY(), item);
		}
		return item;
	}
	
	public void pickUp(CharacterTile character) {
		GameObject item = level.characterPickUp(character);
		if (item != null) {
			handleItem(item);
			board.itemFound(character.getCoordsX(), character.getCoordsY(), item);
		}
	}
	
	private void handleItem(GameObject item) {
		if (item.getType() == ItemType.STAR) {
			collectedStars++;
			userInterface.updateStarCounter(collectedStars);
			if (collectedStars == totalStars) {
				Gdx.app.log("Status", "Level completed!");
				winConditions.put(WinCondition.STARS, true);
			}
		}
		else if (item.getType() == ItemType.KEY) {
			level.keyFound((Key)item);
			userInterface.keyFound((Key)item);
		}
	}

	public void pushButton() {
		// TODO Auto-generated method stub
	}

	public CharacterTile getMalcolm() {
		return malcolm;
	}
	
	public CharacterTile getLisa() {
		return lisa;
	}
	
	public CharacterTile getAndrew() {
		return andrew;
	}

	public void characterSpeak(CharacterTile character, String message) {
		character.setDirection(Direction.SOUTH);
		board.characterSpeak(character.getCoordsX(), character.getCoordsY(), message);
		winConditions.put(WinCondition.SPEAK, true);
	}
	
	public static InputStream loadFile(String filePath) throws FileNotFoundException {
		try {
			return new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			return TutorialGameApplication.class.getResourceAsStream("/"+filePath);
		}
	}

}
