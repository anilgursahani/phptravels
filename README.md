# phptravels
This repository will contain the code to automate the travel site phptravels.com. This project is configured to run using maven.  In the pom.xml file there is a profile for the different browsers. This project has been tested primarily with the chrome browser. 

Before running the tests the chrome and gecko drivers need to be installed. To install the Chrome driver perform the following steps:

1. Create folder vendor in the phptravels directory. It will be on the same level as the folder src and pom.xml.
1. Determine the version of the Chrome browser 
1. Navigate to the [Chromium](http://chromedriver.chromium.org/home) site.
1. Read the [Version selection](http://chromedriver.chromium.org/downloads/version-selection) guide to determine the version of the chrome driver to download.
1. Download the driver into the directory vendor.
1. Rename the driver file to chromedriver.exe

To run Selenium using Firefox install the latest stable version of the gecko driver.

1. Go to the [gecko driver](https://github.com/mozilla/geckodriver/releases) page.
1. Click on the Downloads link and download the latest stable version of the gecko driver.
1. Copy the driver to the vendor folder.
1. Rename the driver file to geckodriver.exe
  
To run tests locally using Chrome issue the following command:
 - mvn -p nogrid, chrome, devhost, test

  
