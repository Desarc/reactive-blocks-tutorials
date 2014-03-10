package no.ntnu.oyvinric.tutorialgame.intro;

import com.badlogic.gdx.scenes.scene2d.Action;

public class MoreInfoAction extends Action {

	private String concept;
	
	public MoreInfoAction(String concept) {
		this.concept = concept;
	}
	
	
	@Override
	public boolean act(float delta) {
		System.out.println("pressed button "+concept);
		return false;
	}

}
