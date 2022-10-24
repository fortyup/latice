package latice.controller;

import java.util.List;

import latice.model.BoxType;
import latice.model.constant.Constants;
import latice.visual.BoxFX;

public class DndScoreController {

	private static int notAnyTileArround = 0;
	
	public static int countingScore(List<BoxFX>boxesArround,BoxType boxtype) {
		
	        int compteurTilesArround = notAnyTileArround;
	        for (BoxFX boxFX : boxesArround) {
	        	if (boxFX.getTile()!=null) {
	        		compteurTilesArround++;
	        	}
	        }
	        if (compteurTilesArround <= Constants.ZERO) {
	        	compteurTilesArround = Constants.ZERO;
	        } else {
	        	compteurTilesArround = compteurTilesArround-1; 
	        }
		

	        int compteurBoxType = Constants.ZERO;
	        if (boxtype == BoxType.SUN ) {
	        	compteurBoxType = compteurBoxType + 2;
	        }
	        return compteurTilesArround+compteurBoxType;
		}
	}