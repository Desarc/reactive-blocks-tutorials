package no.ntnu.oyvinric.tutorialgame.intro;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Introduction implements ApplicationListener {
	
	private int levelNumber;
	private IntroConfiguration configuration;
	private IntroConfigurationDefault defaultValues;
	
	private Stage stage;
	private IntroAnimation animation;
	
	
	public Introduction(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	private static void adjustActorPosition(Actor actor) {
		actor.setPosition(actor.getX(), Constants.introductionWindowHeight-actor.getY());
	}

	@Override
	public void create() {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		defaultValues = new IntroConfigurationDefault();
		
		configuration = new IntroConfiguration(levelNumber, defaultValues, stage);
		
		for (Actor actor : configuration.getActors()) {
			adjustActorPosition(actor);
			stage.addActor(actor);
		}
		
		animation = configuration.getAnimation();
		animation.play();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
		stage.dispose();
		configuration.cleanUp();
		defaultValues.cleanUp();
	}

}
