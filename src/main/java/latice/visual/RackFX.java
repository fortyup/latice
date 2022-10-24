package latice.visual;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import latice.controller.DndImageViewController;
import latice.model.Color;
import latice.model.Shape;
import latice.model.constant.Constants;

public class RackFX {

private List<TileFX> tiles;
	
	public RackFX(List<TileFX> tiles) {
		this.tiles = tiles;
	}

	public static List<TileFX> createRackFX(List<TileFX> stack) {
		List<TileFX> rack = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			rack.add(stack.get(i));
		}
		return rack;
	}

	public List<TileFX> getTiles() {
		return tiles;
	}
	
	public static VBox createRackVbox(PlayerFX player) {
		VBox vboxrack = new VBox();
		for (int i = 0; i < Constants.FIVE; i++) {
			ImageView imgPlayer = new ImageView(player.getRack().get(i).getImg());
			Color color = player.getRack().get(i).getColor();
			Shape shape = player.getRack().get(i).getShape();
			DndImageViewController.manageSourceDragAndDrop(imgPlayer, color, shape, vboxrack,player);
		}
		return vboxrack;
	}
		
}
