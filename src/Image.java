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
	
	public static final String COIN_IMAGE_FILEPATH = "/img/coin_38x38.png";
	
	
	
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

	public static String villainImageFilepath(Villain villain) {
		String name = villain.getName().split(" ")[0].toLowerCase();
		String filepath = String.format("/img/%s.png", name);
		return filepath;
	}
	
	public static String getHeroPSRImage(String choice) {
		String filepath = String.format("/img/PSR_hero_%s.png", choice.toLowerCase());
		return filepath;
	}
	
	public static String getVillainPSRImage(String choice) {
		String filepath = String.format("/img/PSR_villain_%s.png", choice.toLowerCase());
		return filepath;
	}

}
