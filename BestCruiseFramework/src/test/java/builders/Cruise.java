package builders;

import enums.Country;
import enums.Port;
import enums.Region;

public class Cruise {

	private Region region;
	private Country country;
	private Port departurePort;
	private int minCruiseLength;
	private String beforeDate;

	private Cruise() {
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

	public String getDate() {
		return beforeDate;
	}

	private void setRegion(Region region) {
		this.region = region;
	}

	private void setCountry(Country country) {
		this.country = country;
	}

	private void setDeparturePort(Port departurePort) {
		this.departurePort = departurePort;
	}

	private void setMinCruiseLength(int minCruiseLength) {
		this.minCruiseLength = minCruiseLength;
	}

	private void setDate(String date) {
		this.beforeDate = date;
	}

	public static CruiseBuilder getBuilder() {
		return new CruiseBuilder();
	}

	public static class CruiseBuilder {

		private Region destinationRegion = Region.AnyRegion;
		private Country destinationCountry = Country.SkipCountry;
		private Port departurePort = Port.AnyCity;
		private int minCruiseLength = 7;
		private String beforeDate = "31 декабрь";
		private Cruise cruise;

		public CruiseBuilder withDestinationRegion(Region destinationRegion) {
			this.destinationRegion = destinationRegion;
			return this;
		}

		public CruiseBuilder withDestinationCountry(Country destinationCountry) {
			this.destinationCountry = destinationCountry;
			return this;
		}

		public CruiseBuilder withDeparturePort(Port departurePort) {
			this.departurePort = departurePort;
			return this;
		}

		public CruiseBuilder withMinCruiseLength(int minCruiseLength) {
			this.minCruiseLength = minCruiseLength;
			return this;
		}

		public CruiseBuilder withBeforeDate(String beforeDate) {
			this.beforeDate = beforeDate;
			return this;
		}

		public Cruise build() {
			if (this.cruise == null) {
				cruise = new Cruise();
				cruise.setRegion(destinationRegion);
				cruise.setCountry(destinationCountry);
				cruise.setDeparturePort(departurePort);
				cruise.setMinCruiseLength(minCruiseLength);
				cruise.setDate(beforeDate);
			}
			return cruise;
		}

		public Cruise getCruise() {
			return cruise;
		}
	}
}
