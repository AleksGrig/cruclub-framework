package enums;

import pages.locators.HomePageLocators;

public enum Country {
	AnyCountry {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.anyCountry.click();
		}
	},
	SkipCountry {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.anyCountry.click();
		}
	},
	GreatBritain {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.GreatBritain.click();
		}
	},
	Israel {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Israel.click();
		}
	};
	
	public abstract void choose(HomePageLocators homeLocators);
}
