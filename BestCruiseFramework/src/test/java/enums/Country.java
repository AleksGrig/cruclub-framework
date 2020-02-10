package enums;

public enum Country
{
	AnyCountry,
	SkipCountry,
	GreatBritain,
	Israel;

	public static Country parseCountry(String option) {
		switch (option) {
		case "SkipCountry":
			return Country.SkipCountry;
		case "GreatBritain":
			return Country.GreatBritain;
		case "Israel":
			return Country.Israel;
		case "AnyCountry":
		default:
			return Country.AnyCountry;
		}
	}
}
