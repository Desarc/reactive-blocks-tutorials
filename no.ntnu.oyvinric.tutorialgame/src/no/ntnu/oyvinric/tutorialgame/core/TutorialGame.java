package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile.Direction;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public class TutorialGame implements ApplicationListener {

	final float horizontalMoveSpeed = GameBoard.tileWidth*2;
	final float verticalMoveSpeed = GameBoard.tileHeight*2;
	
	GameBoard board;
	int levelNo;
	GameCharacter malcolm, kaylee, wash;
	Array<GameCharacter> gameCharacters;
	
	
	Sound winSound;
	Music gameTheme;
	
	public TutorialGame(int levelNo) {
		this.levelNo = levelNo;
	}
	
	@Override
	public void create() {
		
		winSound = Gdx.audio.newSound(Gdx.files.internal("resources/sound/drop.wav"));
		gameTheme = Gdx.audio.newMusic(Gdx.files.internal("resources/sound/rain.mp3"));
		
		board = new GameBoard(this, levelNo);
		
		gameCharacters = new Array<GameCharacter>();
		for (CharacterTile character : board.getCharacterTiles()) {
			if (character.getName() == CharacterName.MALCOLM) {
				malcolm = new GameCharacter(character);
				gameCharacters.add(malcolm);
			}
			else if (character.getName() == CharacterName.KAYLEE) {
				kaylee = new GameCharacter(character);
				gameCharacters.add(kaylee);
			}
			else if (character.getName() == CharacterName.WASH) {
				wash = new GameCharacter(character);
				gameCharacters.add(wash);
			}
		}
		
		gameTheme.setLooping(true);
		gameTheme.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		updateGame();
		board.redraw();
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
		
		board.cleanUp();
	}
	
	private void updateGame() {
		for (GameCharacter character : gameCharacters) {
			if (character.tile.getMoving()) {
				updateCharacterPosition(character);
			}
		}
	}
	
	private void updateCharacterPosition(GameCharacter character) {
		float distanceX = 0;
		float distanceY = 0;
		if (character.remainingDistanceX < 0) {
			distanceX = -horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			character.remainingDistanceX = (character.remainingDistanceX-distanceX > 0) ? 0 : character.remainingDistanceX-distanceX;
		}
		else if (character.remainingDistanceX > 0) {
			distanceX = horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			character.remainingDistanceX = (character.remainingDistanceX-distanceX < 0) ? 0 : character.remainingDistanceX-distanceX;
		}
		else if (character.remainingDistanceY < 0) {
			distanceY = -verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			character.remainingDistanceY = (character.remainingDistanceY-distanceY > 0) ? 0 : character.remainingDistanceY-distanceY;	
		}
		else if (character.remainingDistanceY > 0) {
			distanceY = verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			character.remainingDistanceY = (character.remainingDistanceY-distanceY < 0) ? 0 : character.remainingDistanceY-distanceY;	
		}
		board.updateCharacterPosition(character.tile, distanceX, distanceY);
	}
	
//	public void stepCharacterForward(GameCharacter character) {
//		if (character.tile.getDirection() == Direction.EAST) {
//			character.remainingDistanceX = GameBoard.tileWidth;
//		}
//		else if (character.tile.getDirection() == Direction.WEST) {
//			character.remainingDistanceX = -GameBoard.tileWidth;
//		}
//		else if (character.tile.getDirection() == Direction.NORTH) {
//			character.remainingDistanceX = GameBoard.tileHeight;
//		}
//		else if (character.tile.getDirection() == Direction.SOUTH) {
//			character.remainingDistanceX = -GameBoard.tileHeight;
//		}
//		character.moving = true;
//	}
	
	public void moveCharacterForward(GameCharacter character) {
		if (character.tile.getDirection() == Direction.EAST) {
			character.remainingDistanceX = Float.MAX_VALUE;
		}
		else if (character.tile.getDirection() == Direction.WEST) {
			character.remainingDistanceX = -Float.MAX_VALUE;
		}
		else if (character.tile.getDirection() == Direction.NORTH) {
			character.remainingDistanceY = Float.MAX_VALUE;
		}
		else if (character.tile.getDirection() == Direction.SOUTH) {
			character.remainingDistanceY = -Float.MAX_VALUE;
		}
		character.tile.setMoving(true);
	}
	
	public void stopCharacterMovement(GameCharacter character) {
		character.remainingDistanceX = 0;
		character.remainingDistanceY = 0;
		character.tile.setMoving(false);
		board.adjustCharacterPosition(character.tile);
	}

	public void turnCharacterLeft(GameCharacter character) {
		board.turnCharacterLeft(character.tile);
		if (character.tile.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void turnCharacterRight(GameCharacter character) {
		board.turnCharacterRight(character.tile);
		if (character.tile.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void turnCharacterAround(GameCharacter character) {
		board.turnCharacterAround(character.tile);
		if (character.tile.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void pushButton() {
		// TODO Auto-generated method stub
	}
	
	public class GameCharacter {
		
		CharacterTile tile;
		float remainingDistanceX = 0;
		float remainingDistanceY = 0;
		
		public GameCharacter(CharacterTile characterTile) {
			this.tile = characterTile;
		}
	}

	public GameCharacter getMalcolm() {
		return malcolm;
	}
	
	public GameCharacter getKaylee() {
		return kaylee;
	}
	
	public GameCharacter getWash() {
		return wash;
	}

}
