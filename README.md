# *cruclub-framework*
## [CruClub, search and buy cruise tours](https://www.cruclub.ru/)
### *maven framework: find cruise by parameters or monitor cruise for price drop*
### *Test is failed when cruise is found!*
`git clone https://github.com/AleksGrig/cruclub-framework.git`
### *run tests with maven*
`mvn clean test` 

## *Libraries*
- selenium - browser automation framework
- webdrivermanager - library to manage selenium browser drivers
- testng - testing framework
- extent reports - html reports
- poi - apache library to work with different microsoft files(excel, power-point, word)

## *Folder structure*
- src/test/java
  - builders - Cruise.java helper object that keeps search parameters
  - enums - search options implemented with enums
  - extentlisteners - listener that modifies extent report
  - pages - page objects of webpages
  - testcases - singlecruise test and test for parameterized search
  - utilities - dataproviders, excel manager and extent report manager
- src/test/resources - excel file with search parameters 
- pom.xml
- properties
- testng.xml