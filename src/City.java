import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class City {
	
	/**
	 * One of the values of the Location enum, in which the home base is located by default.
	 * This is the default starting location when a new city is created, and is not
	 * included in the sectorLocations HashMap, since it always contains the home base.
	 */
	private final Location HOME_BASE_LOCATION = Location.CENTRE;
	
	/**
	 * An array containing all the locations in the city except the HOME_BASE_LOCATION,
	 * in the order in which they should be displayed to the user.
	 */
	private final Location[] ORDERED_LOCATIONS = {Location.NORTH,
												  Location.EAST,
												  Location.SOUTH,
												  Location.WEST};

	/**
	 * The current location of the team as a value of the enum Location.
	 */
	private Location currentLocation;
	
	/**
	 * A HashMap mapping each of the four locations (except HOME_BASE_LOCATION) to the sector in that location.
	 * Randomised when the city is created.
	 */
	private HashMap<Location, Sector> sectorLocations;
	
	/**
	 * The villain to be fought in the city.
	 * Initialised to null, can be set using the setter method after a city is created.
	 */
	private Villain villain;
	
	/**
	 * Creates a new city with currentLocation set to CENTRE and the
	 * sectorLocations HashMap randomised.
	 */
	public City() {
		currentLocation = HOME_BASE_LOCATION;
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
			
			String sectorString = "?";
			if (sector.getDiscovered()) {
				sectorString = sector.getType().toString();
			}
			
			String template = "%s: %s\n";
			if (withNumbers)
				template = (i + 1) + ". %s: %s\n";
			
			returnString += String.format(template,
					location.toString(), sectorString);
		}
		return returnString;
	}
	
	/**
	 * Initialises the sectorLocations HashMap with a random mapping between each of the four locations
	 * and the four sector types.
	 */
	private void randomiseSectorLocations() {
		ArrayList<SectorType> sectorTypes = new ArrayList<SectorType>(Arrays.asList(SectorType.values()));
		Collections.shuffle(sectorTypes);
		
		ArrayList<Location> locations = new ArrayList<Location>(Arrays.asList(Location.values()));
		locations.remove(HOME_BASE_LOCATION);
		
		if (sectorTypes.size() != locations.size())
			throw new RuntimeException("Number of sectors doesn't match number of locations");
		
		sectorLocations = new HashMap<Location, Sector>();
		
		for (int i = 0; i < sectorTypes.size(); i++) {
			Sector sector = new Sector(sectorTypes.get(i));
			Location location = locations.get(i);
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
	 * Getter method for villain.
	 * @return The value of villain.
	 */
	public Villain getVillain() {
		return villain;
	}

	/**
	 * Setter method for villain.
	 * @param villain The new value of villain to set.
	 */
	public void setVillain(Villain villain) {
		this.villain = villain;
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
	 * Sets currentLocation to the location which will be numbered with the given
	 * number in the stringWithNumbers representation of the city.
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
		return sectorLocations.size();
	}
	
	public static void main(String[] args) {
		City c = new City();
		System.out.println(c);
		System.out.println(c.stringWithNumbers(true));
	}
	
}
