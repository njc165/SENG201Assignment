import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class City {
	
	/**
	 * One of the values of the Location enum, in which the home base is located by default.
	 * This is the default starting location when a new city is created, and is 
	 * always mapped to the home base in the sectorLocations HashMap.
	 */
	private final Location HOME_BASE_LOCATION = Location.CENTRE;
	
	/**
	 * A SectorType object representing the home base sector type.
	 */
	private final SectorType HOME_BASE_SECTOR_TYPE = SectorType.HOME_BASE;
	
	/**
	 * An array containing all the locations in the city except the HOME_BASE_LOCATION,
	 * in the order in which they should be displayed to the user.
	 */
	private final Location[] ORDERED_LOCATIONS = {Location.NORTH,
												  Location.EAST,
												  Location.SOUTH,
												  Location.WEST};

	/**
	 * The current location of the team as a Location object.
	 */
	private Location currentLocation;
	
	/**
	 * A HashMap mapping each of the locations to the sector in that location.
	 * The HOME_BASE_LOCATION is always mapped to the home base, and the other
	 * locations are randomised when the city is created.
	 */
	private HashMap<Location, Sector> sectorLocations;
	
	/**
	 * The villain to be fought in the city.
	 * Initialised to null, can be set using the setter method after a city is created.
	 */
	private Villain villain;
	
	/**
	 * Creates a new city with the given villain, the currentLocation set to CENTRE
	 * and the other locations in the sectorLocations HashMap randomised.
	 */
	public City(Villain villain) {
		currentLocation = HOME_BASE_LOCATION;
		this.villain = villain;
		randomiseSectorLocations();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return stringWithNumbers(false);
	}
	
	/**
	 * Returns a String representation of the city, with the option to number the
	 * locations from 1.
	 * Used when asking the user to select their destination by entering a number.
	 * @param withNumbers	true if the locations should be numbered, false otherwise.
	 * @return	A String representation of the city with the locations numbered.
	 */
	public String stringWithNumbers(boolean withNumbers) {
		String returnString = "";
		
		Location location = null;
		for (int i = 0; i < ORDERED_LOCATIONS.length; i++) {
			location = ORDERED_LOCATIONS[i];
			Sector sector = sectorLocations.get(location);
			
			String template = "%s: %s\n";
			if (withNumbers)
				template = (i + 1) + ". %s: %s\n";
			
			returnString += String.format(template,
										  location.toString(),
										  sector.toString());
		}
		return returnString;
	}
	
	/**
	 * Initialises the sectorLocations HashMap with the home base location
	 * mapped to the home base sector, and a random mapping between the other
	 * four locations and sector types.
	 * Sets the home base sector to discovered.
	 */
	private void randomiseSectorLocations() {
		ArrayList<SectorType> sectorTypes = new ArrayList<SectorType>(
												Arrays.asList(SectorType.values()));
		sectorTypes.remove(HOME_BASE_SECTOR_TYPE);
		Collections.shuffle(sectorTypes);
		
		Location[] locations = ORDERED_LOCATIONS;
		
		if (sectorTypes.size() != locations.length)
			throw new RuntimeException(
					"Number of sectors doesn't match number of locations");
		
		sectorLocations = new HashMap<Location, Sector>();
		
		Sector homeBaseSector  = new Sector(HOME_BASE_SECTOR_TYPE);
		homeBaseSector.setDiscovered(true);
		sectorLocations.put(HOME_BASE_LOCATION, homeBaseSector);
		
		for (int i = 0; i < sectorTypes.size(); i++) {
			Sector sector = new Sector(sectorTypes.get(i));
			Location location = locations[i];
			sectorLocations.put(location, sector);
		}
	}
	
	/**
	 * Sets the discovered attribute of each sector in the city to true.
	 */
	public void setAllDiscovered() {
		for (Sector sector: sectorLocations.values()) {
			sector.setDiscovered(true);
		}
	}
	
	/**
	 * Returns true if all the sectors in the city have been set to discovered,
	 * false otherwise.
	 * @return	true if all sectors are discovered, false otherwise.
	 */
	public boolean getAllDiscovered() {
		boolean allDiscovered = true;
		for (Sector sector: sectorLocations.values()) {
			if (!sector.getDiscovered())
				allDiscovered = false;
		}
		return allDiscovered;
	}
	
	/**
	 * Sets the current sector to discovered
	 */
	public void setCurrentSectorDiscovered() {
		Sector currentSector = sectorLocations.get(currentLocation);
		currentSector.setDiscovered(true);
	}
	
	/**
	 * Sets currentLocation to the location which will be numbered with the given
	 * number in the stringWithNumbers representation of the city.
	 * (Numbering starts from one).
	 */
	public void setCurrentLocationByNumber(int locationNumber) {
		setCurrentLocation(ORDERED_LOCATIONS[locationNumber - 1]);
	}

	/** Gets the current sector of the city as a value of SectorType.
	 * @return	The current sector.
	 */
	public SectorType getCurrentSectorType() {
		Sector currentSector = sectorLocations.get(currentLocation);
		return currentSector.getType();
	}
	
	/**
	 * Returns the number of locations in the city, not including the central location.
	 * @return	The number of locations in the city.
	 */
	public int numLocations() {
		return ORDERED_LOCATIONS.length;
	}
	
	/**
	 * Getter method for currentLocation.
	 * @return The value of currentLocation.
	 */
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	/**
	 * Setter method for currentLocation.
	 * @param currentLocation The new value of currentLocation to set.
	 */
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	/**
	 * Getter method for villain.
	 * @return The value of villain.
	 */
	public Villain getVillain() {
		return villain;
	}
	
}
