package latice.visual;

import javafx.scene.image.Image;
import latice.model.Box;
import latice.model.BoxType;
import latice.model.Tile;

public class BoxFX extends Box{
	
	private Image img;

	public BoxFX(Tile tile, Image img, BoxType type) {
		super(tile, type);
		this.img=img;
	}	

	public Image getImg() {
		return this.img;
	}

}
