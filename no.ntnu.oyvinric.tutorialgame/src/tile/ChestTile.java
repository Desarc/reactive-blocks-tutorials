package tile;

import items.GameObject;
import level.GameLevel.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ChestTile extends Tile {

	private boolean open;
	private GameObject content;
	private TextureRegion openImage;
	private TextureRegion closedImage;
	
	public ChestTile(GridPosition gridPosition, String type, TextureRegion openImage, TextureRegion closedImage, GameObject content) {
		super(gridPosition, type, closedImage);
		this.content = content;
		this.openImage = openImage;
		this.closedImage = closedImage;
	}

	@Override
	public GameObject interact() {
		if (open) {
			close();
			return null;
		}
		else {	
			return open();
		}
	}
	
	private GameObject open() {
		image = openImage;
		return content;
	}
	
	private void close() {
		image = closedImage;
	}

}
