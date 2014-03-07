package no.ntnu.oyvinric.tutorialgame.icon;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterface.UserInterfacePosition;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public abstract class Icon {

	private Widget drawable;
	private UserInterfacePosition position;
	protected boolean active = false;
	
	public Icon(UserInterfacePosition position) {
		this.position = position;
	}
	
	public Icon(UserInterfacePosition position, Widget drawable) {
		this.position = position;
		this.drawable = drawable;
		updatePosition();
	}
	
	public void updatePosition() {
		System.out.println(position.getX()+","+position.getY());
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
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
