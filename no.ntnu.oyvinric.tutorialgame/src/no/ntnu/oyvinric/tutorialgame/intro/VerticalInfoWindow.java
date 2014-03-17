package no.ntnu.oyvinric.tutorialgame.intro;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class VerticalInfoWindow extends Window {
	
	private VerticalGroup verticalGroup;
	private float spacing;

	public VerticalInfoWindow(String title, Skin skin, float spacing) {
		super(title, skin);
		padTop(getStyle().titleFont.getLineHeight());
		this.spacing = spacing;
		verticalGroup = new VerticalGroup();
		verticalGroup.setSpacing(spacing);
		add(verticalGroup);
		setWidth(Constants.introductionWindowWidth);
		setHeight(getPadTop());
	}
	
	public void addToWindow(Actor actor) {
		setHeight(getHeight() + actor.getHeight() + spacing);
		verticalGroup.addActor(actor);
	}

}
