package enums;

import pages.locators.HomePageLocators;

public enum Port {
	AnyCity {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.anyCity.click();			
		}
	},
	Genoa {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Genoa.click();					
		}
	},
	Marseille {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Marseille.click();	
		}
	},
	Rome {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Rome.click();			
		}
	},
	Savona {
		@Override
		public void choose(HomePageLocators homeLocators) {
			homeLocators.Savona.click();			
		}
	};
	
	public abstract void choose(HomePageLocators homeLocators);
}
