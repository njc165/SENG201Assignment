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
	 * The current location of the team as a value of the enum Location.
	 */
	private Location currentLocation;
	
	/**
	 * A HashMap mapping each of the four locations (except HOME_BASE_LOCATION) to the sector in that location.
	 * Randomised when the city is created.
	 */
	private HashMap<Location, Sector> sectorLocations;
	
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
		String returnString = "";
		Location[] locations = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		
		for (Location location: locations) {
			Sector sector = sectorLocations.get(location);
			
			String sectorString = "?";
			if (sector.getDiscovered()) {
				sectorString = sector.getType().toString();
			}
			
			returnString += String.format("%s: %s\n",
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
	
//	public static void main(String[] args) {
//		City c = new City();
//		System.out.println(c);
//	}
	
}
