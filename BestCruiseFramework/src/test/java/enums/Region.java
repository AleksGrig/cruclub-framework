package enums;

public enum Region
{
	AnyRegion,
	Africa,
	BritishIslands,
	Mediterranean,
	NearEast;

	public static Region parseString(String option) {
		switch (option) {
		case "Africa":
			return Region.Africa;
		case "BritishIslands":
			return Region.BritishIslands;
		case "Mediterranean":
			return Region.Mediterranean;
		case "NearEast":
			return Region.NearEast;
		case "AnyRegion":
		default:
			return Region.AnyRegion;
		}
	}
}
