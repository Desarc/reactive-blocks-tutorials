package no.ntnu.oyvinric.tutorialgame.core;

import level.GameLevel;
import level.Level1;
import level.Level2;
import level.Level3;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile.Direction;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public class TutorialGame implements ApplicationListener {

	final float horizontalMoveSpeed = GameBoard.tileWidth*2;
	final float verticalMoveSpeed = GameBoard.tileHeight*4;
	
	GameBoard board;
	GameLevel level;
	int levelNumber;
	CharacterTile malcolm, kaylee, wash;
	Array<CharacterTile> gameCharacters;
	
	Sound winSound;
	Music gameTheme;
	
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
		
		board = new GameBoard(level);
		
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
				board.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.EAST) {
			distanceX = horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
//			System.out.println(enteringTile.getGridPosition().getX()+","+enteringTile.getGridPosition().getY()+","+enteringTile.getGridPosition().getZ());
//			System.out.println(enteringTile.getType());
//			System.out.println(enteringTile);
			if (level.tileCanMove(character, Direction.EAST, distanceX, distanceY)) {
				board.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.SOUTH) {
			distanceY = -verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				board.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
		else if (character.getDirection() == Direction.NORTH) {
			distanceY = verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			if (level.tileCanMove(character, Direction.WEST, distanceX, distanceY)) {
				board.updateCharacterPosition(character, distanceX, distanceY);
			}
		}
	}
	
	public void moveCharacterForward(CharacterTile character) {
		character.setMoving(true);
	}
	
	public void stopCharacterMovement(CharacterTile character) {
		character.setMoving(false);
		board.adjustCharacterPosition(character);
	}

	public void turnCharacterLeft(CharacterTile character) {
		board.turnCharacterLeft(character);
		if (character.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void turnCharacterRight(CharacterTile character) {
		board.turnCharacterRight(character);
		if (character.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
		}
	}

	public void turnCharacterAround(CharacterTile character) {
		board.turnCharacterAround(character);
		if (character.getMoving()) {
			stopCharacterMovement(character);
			moveCharacterForward(character);
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

}
