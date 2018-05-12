public class Image {
	
	/**
	 * The file path for the image representing sectors which have not
	 * yet been discovered.
	 */
	public static final String UNDISCOVERED_SECTOR_IMAGE_FILEPATH = "/img/undiscovered_area.png";

	/**
	 * The file path for the home base image.
	 */
	public static final String HOME_BASE_IMAGE_FILEPATH = "/img/homebase.png";
	
	/**
	 * The file path for the mountains image used in the corners of the map.
	 */
	public static final String MOUNTAINS_IMAGE_FILEPATH = "/img/mountains.png";
	
	/**
	 * The file path for the coin image used in the shop.
	 */
	public static final String COIN_IMAGE_FILEPATH = "/img/coin_38x38.png";
	
	
	/**
	 * The file path for the unrolled dice image used in the Dice Rolls game.
	 */
	public static final String UNROLLED_DICE_IMAGE_FILEPATH = "/img/dice_unrolled.png";
	
	
	/**
	 * The file path for the image used in games of paper scissors rock,
	 * before the player has selected a move.
	 */
	public static final String PSR_UNDECIDED_FILEPATH = "/img/PSR_undecided.png";
	
	/**
	 * The file path for the image used ihe victory screen.
	 */
	public static final String FIREWORKS_IMAGE_FILEPATH = "/img/fireworks.png";
	
	public static final String GRAVESTONE_IMAGE_FILEPATH = "/img/gravestone.png";
	
	/**
	 * Takes a hero instance, and returns the file path for the portrait
	 * image of the given size for this hero type.
	 * @param hero		The hero whose image file path should be returned.
	 * @param size 		The size of the image to be returned.
	 * @return			The file path of the portrait image of this hero.
	 */
	public static String heroImageFilepath(Hero hero, int width, int height) {
		return String.format("/img/%s_%sx%s.png",
								hero.getType().toLowerCase(),
								width, height);
	}
	
	/**
	 * Takes a power up type, and returns the file path of the image  of
	 * the given size for that power up type.
	 * @param powerUpType	The type of the relevant power up.
	 * @param size			The size of the image to return.
	 * @return	The file path of the image for that power up.
	 */
	public static String powerUpImageFilepath(PowerUpType powerUpType, int size) {
		return String.format("/img/%s_%sx%s.png",
							powerUpType.toString().toLowerCase().replaceAll(" ", "_"),
							size, size);
	}
	
	/**
	 * Takes a healing item, and returns the file path of the image of the given size
	 * for that healing item.
	 * @param healingItem	The relevant healing item.
	 * @param size 			The size of the image to return.
	 * @return				The file path of the image for that healing item.
	 */
	public static String healingItemImageFilepath(HealingItem healingItem, int size) {		
		return String.format("/img/%s_%sx%s.png",
				healingItem.getName().toLowerCase().replaceAll(" ", "_"),
				size, size);
	}
	
	/**
	 * Returns the file path for the image of the map item of the given size.
	 * @param size		The size of the image to be returned.
	 * @return			The file path for the map image of the give size.
	 */
	public static String mapImageFilepath(int size) {
		return String.format("/img/map_%sx%s.png", size, size);
	}
	
	/**
	 * Given a SectorType, return the file path to the appropriate image 
	 * for that type, to be displayed on the map panel.
	 * @param type	The sector type of the sector to be displayed.
	 * @return		The file path for the image representing that sector.
	 */
	public static String sectorTypeFilepath(SectorType type) {
		String filepath = String.format("/img/%s.png", type.toString().toLowerCase());
		filepath = filepath.replaceAll("[^a-z/.]", "");
		return filepath;
	}

	/**
	 * Returns the file path for the image of a given villain.
	 * @param villain The villain for which a filepath is returned
	 * @return The filepath to an appropriate image.
	 */
	public static String villainImageFilepath(Villain villain) {
		String name = villain.getName().split(" ")[0].toLowerCase();
		String filepath = String.format("/img/%s.png", name);
		return filepath;
	}
	
	/**
	 * Takes a number between 1 and 6 and returns the filepath for
	 * the image of a dice showing that number.
	 * @param diceNum	The number showing on the dice image.
	 * @return			The filepath of the appropriate dice image.
	 */
	public static String diceImageFilepath(int diceNum) {
		String filepath = String.format("/img/dice_%d.png", diceNum);
		return filepath;
	}

	/**
	 * Returns the filepath for the image representing a hero's
	 * choice in a game of paper, scissors, rock.
	 * @param choice A string representation of the hero's choice.
	 * @return A filepath to the appropriate image.
	 */
	public static String getHeroPSRImage(String choice) {
		String filepath = String.format("/img/PSR_hero_%s.png", choice.toLowerCase());
		return filepath;
	}
	
	/**
	 * Returns the filepath for the image representing a villain's
	 * choice in a game of paper, scissors, rock.
	 * @param choice A string representation of the villain's choice.
	 * @return A filepath to the appropriate image.
	 */
	public static String getVillainPSRImage(String choice) {
		String filepath = String.format("/img/PSR_villain_%s.png", choice.toLowerCase());
		return filepath;
	}

}
