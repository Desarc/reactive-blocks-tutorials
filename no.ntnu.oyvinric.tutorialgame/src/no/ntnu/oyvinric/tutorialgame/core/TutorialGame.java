package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;

public class TutorialGame implements ApplicationListener {

	GameBoard board;
	boolean moving = false;
	
	@Override
	public void create() {
		board = new GameBoard(this, 1);
		board.show();
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
		board.cleanUp();

	}
	
	private void updateGame() {
		if (moving) {
			updatePlayerPosition(165*Gdx.graphics.getDeltaTime(), 0);
		}
	}
	
	private void updatePlayerPosition(float dx, float dy) {
		Rectangle player = board.getPlayer();
		player.setPosition(player.x+dx, player.y+dy);
	}
	
	public void movePlayerForward() {
		moving = true;
	}
	
	public void stopPlayerMovement() {
		moving = false;
	}

}
