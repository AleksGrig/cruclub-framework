package builders;

import enums.City;
import enums.Country;
import enums.Region;

public class Cruise {

	private Region region;
	private Country country;
	private City departurePort;
	private int minCruiseLength;
	private String date;

	private Cruise() {
	}

	public Region getRegion() {
		return region;
	}

	public Country getCountry() {
		return country;
	}

	public City getDeparturePort() {
		return departurePort;
	}

	public int getMinCruiseLength() {
		return minCruiseLength;
	}

	public String getDate() {
		return date;
	}

	private void setRegion(Region region) {
		this.region = region;
	}

	private void setCountry(Country country) {
		this.country = country;
	}

	private void setDeparturePort(City departurePort) {
		this.departurePort = departurePort;
	}

	private void setMinCruiseLength(int minCruiseLength) {
		this.minCruiseLength = minCruiseLength;
	}

	private void setDate(String date) {
		this.date = date;
	}

	public static CruiseBuilder getBuilder() {
		return new CruiseBuilder();
	}

	public static class CruiseBuilder {

		private Region region = Region.AnyRegion;
		private Country country = Country.SkipCountry;
		private City departurePort = City.AnyCity;
		private int minCruiseLength = 7;
		private String date = "1 июнь";
		private Cruise cruise;

		public CruiseBuilder withRegion(Region region) {
			this.region = region;
			return this;
		}

		public CruiseBuilder withCountry(Country country) {
			this.country = country;
			return this;
		}

		public CruiseBuilder withCity(City departurePort) {
			this.departurePort = departurePort;
			return this;
		}

		public CruiseBuilder withMinCruiseLength(int minCruiseLength) {
			this.minCruiseLength = minCruiseLength;
			return this;
		}

		public CruiseBuilder withDate(String date) {
			this.date = date;
			return this;
		}

		public Cruise build() {
			if (this.cruise == null) {
				cruise = new Cruise();
				cruise.setRegion(region);
				cruise.setCountry(country);
				cruise.setDeparturePort(departurePort);
				cruise.setMinCruiseLength(minCruiseLength);
				cruise.setDate(date);
			}
			return cruise;
		}

		public Cruise getCruise() {
			return cruise;
		}
	}
}
