package no.ntnu.oyvinric.tutorialgame.icon;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterface.UserInterfacePosition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class CounterIcon extends Icon {

	private int count;
	private int totalCount;
	private Label counterLabel;
	
	public CounterIcon(UserInterfacePosition position, int initialCount, int totalCount) {
		super(position);
		LabelStyle style = new LabelStyle(new BitmapFont(), Color.LIGHT_GRAY);
		counterLabel = new Label(initialCount+"/"+totalCount, style);
		counterLabel.setWidth(32);
		counterLabel.setHeight(32);
		setDrawable(counterLabel);
		this.count = initialCount;
		this.totalCount = totalCount;
	}
	
	private void updateText() {
		counterLabel.setText(count+"/"+totalCount);
	}
	
	public void setCount(int count) {
		this.count = count;
		updateText();
	}

}
