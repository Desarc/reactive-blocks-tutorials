package no.ntnu.oyvinric.tutorialgame.hud;

import no.ntnu.oyvinric.tutorialgame.hud.UserInterface.UserInterfacePosition;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public abstract class HUDElement extends Actor {

	private Widget drawable;
	protected UserInterfacePosition position;
	protected boolean active = false;
	
	public HUDElement(UserInterfacePosition position) {
		this.position = position;
	}
	
	public HUDElement(UserInterfacePosition position, Widget drawable) {
		this(position);
		this.drawable = drawable;
		updatePosition();
	}
	
	public void updatePosition() {
		drawable.setPosition(position.getX(), position.getY());
	}
	
	public UserInterfacePosition getPosition() {
		return position;
	}
	
	public void setDrawable(Widget drawable) {
		this.drawable = drawable;
		updatePosition();
	}
	
	public Widget getDrawable() {
		return drawable;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		drawable.draw(batch, parentAlpha);
	}
	
	@Override
	public void setSize(float width, float height) {
		drawable.setSize(width, height);
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
