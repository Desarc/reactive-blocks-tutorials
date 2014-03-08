package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.UserInterface;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.item.GameObject.ItemType;
import no.ntnu.oyvinric.tutorialgame.item.Key;
import no.ntnu.oyvinric.tutorialgame.level.GameLevel;
import no.ntnu.oyvinric.tutorialgame.level.Level1;
import no.ntnu.oyvinric.tutorialgame.level.Level2;
import no.ntnu.oyvinric.tutorialgame.level.Level3;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.tile.Tile.Direction;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public class TutorialGame implements ApplicationListener {

	public static final int windowWidth = 800;
	public static final int windowHeight = 600;
	
	final float horizontalMoveSpeed = GameBoard.tileWidth*2;
	final float verticalMoveSpeed = GameBoard.tileHeight*4;
	
	private GameBoard board;
	private GameLevel level;
	private UserInterface userInterface;
	private int levelNumber;
	private CharacterTile malcolm, kaylee, wash;
	private Array<CharacterTile> gameCharacters;
	
	private Sound winSound;
	private Music gameTheme;
	
	private int totalStars = 0;
	private int collectedStars = 0;
	//private boolean levelCompleted = false;
	
	public TutorialGame(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	@Override
	public void create() {
		
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
			else if (character.getName() == CharacterName.KAYLEE) {
				kaylee = character;
				gameCharacters.add(kaylee);
			}
			else if (character.getName() == CharacterName.WASH) {
				wash = character;
				gameCharacters.add(wash);
			}
		}
		
		winSound = Gdx.audio.newSound(Gdx.files.internal("resources/sound/drop.wav"));
		gameTheme = Gdx.audio.newMusic(Gdx.files.internal("resources/sound/rain.mp3"));
		gameTheme.setLooping(true);
		gameTheme.play();
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
		gameTheme.dispose();
		winSound.dispose();
		level.cleanUp();
		board.cleanUp();
		userInterface.cleanUp();
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
			distanceX = -horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.EAST) {
			distanceX = horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.EAST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.SOUTH) {
			distanceY = -verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				level.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.NORTH) {
			distanceY = verticalMoveSpeed*Gdx.graphics.getDeltaTime();
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
		}
		return item;
	}
	
	public void pickUp(CharacterTile character) {
		GameObject item = level.characterPickUp(character);
		if (item != null) {
			handleItem(item);
		}
	}
	
	private void handleItem(GameObject item) {
		if (item.getType() == ItemType.STAR) {
			System.out.println("Picked up a star!");
			collectedStars++;
			userInterface.updateStarCounter(collectedStars);
			if (collectedStars == totalStars) {
				System.out.println("Level completed!");
				//levelCompleted = true;
			}
		}
		else if (item.getType() == ItemType.KEY) {
			System.out.println("Found a "+((Key)item).getColor().value()+" key!");
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
	
	public CharacterTile getKaylee() {
		return kaylee;
	}
	
	public CharacterTile getWash() {
		return wash;
	}

	public void characterSpeak(CharacterTile character, String message) {
		character.setDirection(Direction.SOUTH);
		board.characterSpeak(character.getCoordsX(), character.getCoordsY(), message);
	}

}
