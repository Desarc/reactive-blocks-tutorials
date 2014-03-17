package no.ntnu.oyvinric.tutorialgame.intro;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class VerticalComposite extends VerticalGroup {

private float spacing;
	
	public VerticalComposite(float spacing) {
		this.spacing = spacing;
		setSpacing(spacing);
	}
	
	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		setWidth(Math.max(getWidth(), actor.getWidth()));
		setHeight(getHeight() + actor.getHeight() + spacing);
	}
	
}
