package enums;

public enum City
{
	AnyCity, Genoa, Marseille, Rome, Savona;

	public static City parseString(String option) {
		switch (option) {
		case "Genoa":
			return City.Genoa;
		case "Marseille":
			return City.Marseille;
		case "Rome":
			return City.Rome;
		case "Savona":
			return City.Savona;
		case "AnyCity":
		default:
			return City.AnyCity;
		}
	}
}
