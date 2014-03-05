package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard.Direction;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

public class TutorialGame implements ApplicationListener {

	final float horizontalMoveSpeed = GameBoard.tileWidth*2;
	final float verticalMoveSpeed = GameBoard.tileHeight*2;
	
	GameBoard board;
	int levelNo;
	float remainingDistanceX = 0;
	float remainingDistanceY = 0;
	
	
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
		updatePlayerPosition();
	}
	
	private void updatePlayerPosition() {
		float distanceX = 0;
		float distanceY = 0;
		if (remainingDistanceX < 0) {
			distanceX = -horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			remainingDistanceX = (remainingDistanceX-distanceX > 0) ? 0 : remainingDistanceX-distanceX;
		}
		else if (remainingDistanceX > 0) {
			distanceX = horizontalMoveSpeed*Gdx.graphics.getDeltaTime();
			remainingDistanceX = (remainingDistanceX-distanceX < 0) ? 0 : remainingDistanceX-distanceX;
		}
		else if (remainingDistanceY < 0) {
			distanceY = -verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			remainingDistanceY = (remainingDistanceY-distanceY > 0) ? 0 : remainingDistanceY-distanceY;	
		}
		else if (remainingDistanceY > 0) {
			distanceY = verticalMoveSpeed*Gdx.graphics.getDeltaTime();
			remainingDistanceY = (remainingDistanceY-distanceY < 0) ? 0 : remainingDistanceY-distanceY;	
		}
		board.updatePlayerPosition(distanceX, distanceY);
	}
	
	public void stepForward() {
		if (board.getPlayer().getDirection() == Direction.EAST) {
			remainingDistanceX = GameBoard.tileWidth;
		}
		else if (board.getPlayer().getDirection() == Direction.WEST) {
			remainingDistanceX = -GameBoard.tileWidth;
		}
		else if (board.getPlayer().getDirection() == Direction.NORTH) {
			remainingDistanceX = GameBoard.tileHeight;
		}
		else if (board.getPlayer().getDirection() == Direction.SOUTH) {
			remainingDistanceX = -GameBoard.tileHeight;
		}
	}
	
	public void movePlayerForward() {
		if (board.getPlayer().getDirection() == Direction.EAST) {
			remainingDistanceX = Float.MAX_VALUE;
		}
		else if (board.getPlayer().getDirection() == Direction.WEST) {
			remainingDistanceX = -Float.MAX_VALUE;
		}
		else if (board.getPlayer().getDirection() == Direction.NORTH) {
			remainingDistanceY = Float.MAX_VALUE;
		}
		else if (board.getPlayer().getDirection() == Direction.SOUTH) {
			remainingDistanceY = -Float.MAX_VALUE;
		}
	}
	
	public void stopPlayerMovement() {
		remainingDistanceX = 0;
		remainingDistanceY = 0;
		//board.adjustCharacterPosition();
	}

	public void turnLeft() {
		board.turnPlayerLeft();
	}

	public void turnRight() {
		board.turnPlayerRight();
	}

	public void turnAround() {
		board.turnPlayerAround();
	}

	public void pushButton() {
		// TODO Auto-generated method stub
		
	}

}
