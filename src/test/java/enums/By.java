package enums;

import pages.locators.SearchPageLocators;

// All links choose minimum price!!!
public enum By {
	Date {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();			
		}
	},
	Length {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();					
		}
	},
	Price {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();					
		}
	},
	Profit {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();					
		}
	},
	Discount {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();					
		}
	},
	SaleTime {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();					
		}
	},
	Recommendation {
		@Override
		public void choose(SearchPageLocators searchLocators) {
			searchLocators.minPriceDrop.click();					
		}
	};
	
	public abstract void choose(SearchPageLocators searchLocators);
}
