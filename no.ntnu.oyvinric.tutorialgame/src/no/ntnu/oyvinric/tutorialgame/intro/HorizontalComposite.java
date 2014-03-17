package no.ntnu.oyvinric.tutorialgame.intro;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

public class HorizontalComposite extends HorizontalGroup {
	
	private float spacing;
	
	public HorizontalComposite(float spacing) {
		this.spacing = spacing;
		setSpacing(spacing);
	}
	
	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		setWidth(getWidth() + actor.getWidth() + spacing);
		setHeight(Math.max(getHeight(), actor.getHeight()));
	}

}
