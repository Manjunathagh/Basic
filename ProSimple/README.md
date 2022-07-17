## Environment setup
Latest chrome browser

### Required tools

Technology Used : Java, TestNG, Selenium, Maven
Framework Used: Page Object Model

## Project setup

-   Clone the project
-   Update the maven project
-   You can change tests are set to run on Lab or Lab-ng environment, Change client url for environment in Configuration.
-   To run tests in headed mode, write '-Dbrowser=chrome' in a terminal and click enter
-   To run tests in headless mode, write '-Dbrowser=chromeHeadless' in a terminal and click enter

#command to run scripts using command line and IDE

In Terminal  -> mvn test -P CA_RMS_Smoke.xml -Dbrowser=chrome -DsiteHost=https://california-ng.lab.cores.juvare.com -Dos=windows -Dmodule=web -Dtestng.dtd.http=true
In IDE -> -Dbrowser=chrome -DsiteHost=ClientURL -Dos=windows -Dmodule=web -Dtestng.dtd.http=true

## Tagging tests 

-   Created separate Smoke suite for RMS and HAN client sites.
-   Tests should be tagged by their purpose: smoke, HD, etc. (any specific purpose tests)
-   To run smoke client tests suite, generate testng.xml and use above command. (IDE / Terminal)
-   To run HD client tests suite, generate testng.xml and use above command
-   To run specific features, generate testng.xml and use above command

## Notes

-   DO NOT commit 'test-output' file with actual credentials to the repository.
-   DO NOT commit "Screenshots, emailable reports, results" file with actual credentials to the repository.
-   Do NOT change admin password.


## Code formatting

-   Use Inbuilt code formatting to have consistent code through the whole repository
-   For Eclipse IDE :
    1. Go to Windows -> click Preferences.
    2. Select Java>>Editor -> click on Save Actions.
    3. In Save Actions 
    4. Select the 'Perform the selected actions on save' checkbox And Select the 'Format source code' checkbox.
    5. Select 'Format edited lines' radio button and click 'Apply and Close' button.
    6. Code will be formatted upon every save.