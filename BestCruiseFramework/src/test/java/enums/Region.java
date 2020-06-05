package enums;

import pages.locators.HomePageLocators;

public enum Region {
	AnyRegion {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.anyRegion.click();
		}
	},
	Africa {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Africa.click();
		}
	},
	BritishIslands {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.BritishIslands.click();
		}
	},
	Mediterranean {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Mediterrenean.click();
		}
	},
	NearEast {
		@Override
		public	void choose(HomePageLocators homeLocators) {
			homeLocators.NearEast.click();
		}
	};
	
	public abstract void choose(HomePageLocators homeLocators);
}
