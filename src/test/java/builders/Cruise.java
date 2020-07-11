package builders;

import java.util.function.Consumer;

import enums.Country;
import enums.Port;
import enums.Region;

public class Cruise {

	private Region region = Region.AnyRegion;
	private Country country= Country.SkipCountry;
	private Port departurePort = Port.AnyCity;
	private int minCruiseLength = 7; 
	private String beforeDate = "31 декабрь";
	
	private Cruise() {}
	
	public static Cruise build(Consumer<Cruise> block) {
		Cruise cruise = new Cruise();
		block.accept(cruise);
		return cruise;
	}
	
	public static Cruise build() {
		return new Cruise();
	}
	
	public Region getRegion() {
		return region;
	}

	public Country getCountry() {
		return country;
	}

	public Port getDeparturePort() {
		return departurePort;
	}

	public int getMinCruiseLength() {
		return minCruiseLength;
	}

	public String getBeforeDate() {
		return beforeDate;
	}

	public Cruise withDestinationRegion(String destinationRegion) {
		this.region = Region.valueOf(destinationRegion);
		return this;
	}
	
	public Cruise withDestinationCountry(String destinationCountry) {
		this.country = Country.valueOf(destinationCountry);
		return this;
	}
	
	public Cruise withDeparturePort(String departurePort) {
		this.departurePort = Port.valueOf(departurePort);
		return this;
	}
	
	public Cruise withMinCruiseLength(String minCruiseLength) {
		this.minCruiseLength =(int) Double.parseDouble(minCruiseLength);
		return this;
	}

	public Cruise withBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
		return this;
	}
}
