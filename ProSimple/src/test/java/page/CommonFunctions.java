package page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qaFramework.pageObject.PageObject;



public class CommonFunctions extends PageObject{

	
	WebDriver driver;

	public CommonFunctions(WebDriver driver) throws Exception {
		super(driver);
		this.driver = driver;
	}

	protected File getXMLFile() throws Exception {
		return new File(System.getProperty("user.dir")+"/src/test/java/locators/commonFunctions_elements.xml");
	}
	
	
	/*** Modified :Harshitha     Date:14 Nov 2018
	 */
	public String verSuccessMsg()throws Exception {
		this.page.element("success_msg","xpath").waitForElementAppearance();
		this.page.element("success_msg","xpath").waitForElementAppearance();
		this.page.element("success_msg","xpath").waitForElementAppearance();
		this.page.element("success_msg","xpath").waitForElementAppearance();
		String successMessage=this.driver.findElement(By.xpath("//div[@id='alertbox']/p")).getText();
		return successMessage.trim();
	}

	//This function is used for all Configuration creation (Announcements, Training and Skills)
	public CommonFunctions verConfigCreationSuccessMsg()throws Exception {
		assertEquals(verSuccessMsg(), "The configuration settings have been created.");
		return this;
	}
	
	/*** Modified :Harshitha     Date:14 Nov 2018
	 **/	
	//This function is used to Search Announcements, Skills and Training
	public CommonFunctions clickFilter(String status) throws Exception {
		this.verifyFilterWaterMarkIsDisplayed();
		this.page.element("filter","xpath").waitForElementAppearance();
		this.page.element("filter","xpath").click();
		this.page.element("filter","xpath").getOne().clear();
		this.page.element("filter","xpath").getOne().sendKeys(status);
		return this;
	}
	
	public String getURL() {
		return this.driver.getCurrentUrl();
	}

	//To verify URL on all pages
	public CommonFunctions verPageURLs(String url)throws Exception {
		assertEquals(getURL(), url,"Error Info: The page URL is not as expected");
		return this;
	}


	public CommonFunctions selectAnAction(String selectAction)throws Exception{
		this.page.element("selectAnAction","xpath").waitForElementAppearance();
		this.page.element("selectAnAction","xpath").webElementToSelect().selectByVisibleText(selectAction);
		return this;
	}

	public CommonFunctions clickSubmitAction()throws Exception {
		this.page.element("submitAction","xpath").waitForElementAppearance();
		this.page.element("submitAction","xpath").click();
		return this;
	}
	
	/*** Modified :Harshitha     Date:14 Nov 2018
	 */
	//This function is used get First three ConfigurationsName(Announcements, Skills and Training)
	public String[] getFirstThreeConfigNames()throws Exception{
		String[] arr = new  String[3];
		int j=0;
		for (int i=1 ;i<=arr.length;i++)
		{
			this.page.dynamicElement("announcement_name","//div[@class='grid-canvas']/div["+i+"]/div[2]/a","xpath").isElementPresent();
			this.page.dynamicElement("announcement_name","//div[@class='grid-canvas']/div["+i+"]/div[2]/a","xpath").waitForElementAppearance();
			arr[j] = this.page.dynamicElement("announcement_name","//div[@class='grid-canvas']/div["+i+"]/div[2]/a","xpath").getOne().getText();
			j++;
		}
		return arr;
	}

	//This function is used select three ConfigurationsName(Announcements, Skills and Training)
	public String[] selConfigurations(String[] configName)throws Exception{
//		SiteConfiguration siteConfiguration_page = new SiteConfiguration(driver);
		String[] arr = new  String[3];
		int j=0;
		for (int i=1 ;i<=configName.length;i++)
		{
			 int count = 0;
			 this.page.dynamicElement("announcement_name","//a[text()='"+configName[j]+"']//parent::div/parent::div/div/input","xpath").isElementPresent();
				while((!this.page.dynamicElement("announcement_name","//a[text()='"+configName[j]+"']//parent::div/parent::div/div/input","xpath").isElementPresent(4))&& (count<=10))
				{
				   WebElement element = driver.findElement(By.cssSelector("div.slick-viewport"));
				    element.sendKeys(Keys.PAGE_DOWN);
				    count++;
			   }
//			siteConfiguration_page.verSiteConfigPageIsDisp();
			this.page.dynamicElement("announcement_name","//a[text()='"+configName[j]+"']//parent::div/parent::div/div/input","xpath").waitForElementAppearance();
			if(!this.page.dynamicElement("announcement_name","//a[text()='"+configName[j]+"']//parent::div/parent::div/div/input","xpath").getOne().isSelected()){
				this.page.dynamicElement("announcement_name","//a[text()='"+configName[j]+"']//parent::div/parent::div/div/input","xpath").isElementPresent();
				this.page.dynamicElement("announcement_name","//a[text()='"+configName[j]+"']//parent::div/parent::div/div/input","xpath").click();
			}					
			j++;
		}
		int k = 0;
		for (int i=1 ;i<=configName.length;i++)
		{
			int count = 0;
			while((!this.page.dynamicElement("announcement_name","//a[text()='"+configName[k]+"']//parent::div/parent::div/div/input","xpath").isElementPresent(4))&& (count<=10))
			{
			WebElement element = driver.findElement(By.cssSelector("div.slick-viewport"));
			element.sendKeys(Keys.PAGE_DOWN);
		     count++;
		       }
			this.page.dynamicElement("announcement_name","//a[text()='"+configName[k]+"']//parent::div/parent::div/div/input","xpath").getDisplayedOne();
			if(!this.page.dynamicElement("announcement_name","//a[text()='"+configName[k]+"']//parent::div/parent::div/div/input","xpath").getOne().isSelected()){
				this.page.dynamicElement("announcement_name","//a[text()='"+configName[k]+"']//parent::div/parent::div/div/input","xpath").click();
			}					
			k++;
		}

		return arr;
	}

	public String verErrorMsg()throws Exception {
		this.page.element("error_msg","xpath").waitForElementAppearance();
		return this.page.element("error_msg","xpath").getOne().getText().trim();
	}

	public CommonFunctions refresh()throws Exception {
		driver.navigate().refresh();
		return this;
	}

	public CommonFunctions waitForPageLoad(int time)throws Exception {
		Thread.sleep(time);
		return this;
	}

	public String pathToUploadFile (String fileType)throws Exception {
		Properties prop = new Properties();
		InputStream input = this.getClass().getResourceAsStream(
				System.getProperty("path.file",
						"/propertiesFiles/path.properties"));
		prop.load(input);
		
		String saveImagePath = prop.getProperty(fileType);
		File strFile = new File(System.getProperty("user.dir") + saveImagePath);
		String strFilePath = strFile.toString();
		return strFilePath;
	}

	//This function is to get only date and time excluding time and date
	public String getOnlyDateAndTime() throws Exception{
		DateTimeSettings dateTimeSettings_page = new DateTimeSettings();
		Calendar cal = Calendar.getInstance();
		String onlyDate = dateTimeSettings_page.getCurrentDate("MM/dd/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		String curTime1 = sdf.format(cal.getTime());
		String strOnlyDateAndTime = onlyDate+" "+curTime1;
		return strOnlyDateAndTime;
	}

	//This function is to get time zone along with Meridian
	public String getTimeZoneWithMeridian() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("a z");
		Calendar cal = Calendar.getInstance();
		String curMeridian = sdf.format(cal.getTime());
		return curMeridian;
	}

	//This function is to get time zone 
	public String getTimeZone() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("z");
		Calendar cal = Calendar.getInstance();
		String curMeridian = sdf.format(cal.getTime());
		return curMeridian;
	}

	//This function is to get time zone along with Meridian
	public String getOnlyMerdian() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("a");
		Calendar cal = Calendar.getInstance();
		String curMeridian = sdf.format(cal.getTime());
		return curMeridian;
	}

	public boolean isFileDownloaded(String dirPath, String csv)throws Exception {
		boolean flag=false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}
		for (int i = 1; i < files.length; i++) {
			if(files[i].getName().contains(csv)) {
				flag=true;
			}
		}
		return flag;
	}

	public String getDownloadedFileName(String dirPath, String csv)throws Exception {
		String fileName = "" ;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			System.out.println("Empty, No files found");
		}
		for (int i = 1; i < files.length; i++) {
			if(files[i].getName().contains(csv)) {
				fileName = files[i].getName();
			}
		}
		return fileName;
	}

	public int getNoOfRowsInDownloadedFile(String strFilePath)throws Exception{
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new FileReader(strFilePath));
		int count = 0;
		while((bufferedReader.readLine()) != null)
		{
			count++;
		}
		return count;
	}

	public String readAndVerifyCSVFileData(String strFilePath,int expectedRows,String[] arrheaders,String[] firstRowData,String[] secondRowData) throws Exception{
		int noOfRows = this.getNoOfRowsInDownloadedFile(strFilePath);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(strFilePath));
		String input=null;
		String Line="";
		System.out.println(noOfRows);
		assertTrue(noOfRows==expectedRows,"ErrorInfo :Expected and Actual No of Rows is not matching");
		String column = "";
		for (int i=1 ; i<=noOfRows ;i++){
			if(i == 1) {
				input=bufferedReader.readLine();
				Line = input;
				String[] arrData = Line.split(",");
				column = arrData[0];
				for(int j= 0; j<arrData.length; j++)
				{
					assertTrue(arrData.length == arrheaders.length,"Error Info: Length of headers in CSV and expected array is not matching");
					String str = arrData[j].toString().replace("\"", "");
					assertTrue(str.equals(arrheaders[j]),"Error Info: Headers in CSV is not matching with the expected");
				}
			}

			if(i == 2) {
				input=bufferedReader.readLine();
				Line = input;
				String[] arrData = Line.split(",");
				if(column.contains("ID")){
					for(int j= 1; j<arrData.length; j++)

					{ 
						arrData[j] = arrData[j].trim();
						assertTrue(arrData.length==firstRowData.length,"Error Info: Length of first row data in CSV and expected array is not matching");
						String str = arrData[j].toString().replace("\"", "");
						assertTrue(str.equals(firstRowData[j]),"Error Info: First Row data in CSV is not matching with the expected");
					}
				}
				else{
					for(int j= 0; j<arrData.length; j++)

					{ 
						arrData[j] = arrData[j].trim();
						assertTrue(arrData.length==firstRowData.length,"Error Info: Length of first row data in CSV and expected array is not matching");
						String str = arrData[j].toString().replace("\"", "");
						System.out.println(firstRowData[j]);
						assertTrue(str.equals(firstRowData[j]),"Error Info: First Row data in CSV is not matching with the expected");
					}
				}
			}
			if(i == 3) {
				input=bufferedReader.readLine();
				Line = input;
				String[] arrData = Line.split(",");
				if(column.contains("ID")){
					for(int j= 1; j<arrData.length; j++)
					{
						arrData[j] = arrData[j].trim();
						assertTrue(arrData.length==secondRowData.length,"Error Info: Length of second row data in CSV and expected array is not matching");
						String str = arrData[j].toString().replace("\"", "");
						assertTrue(str.equals(secondRowData[j]),"Error Info: Second Row data in CSV is not matching with the expected");
					}
				}
				else{
					for(int j= 0; j<arrData.length; j++)
					{
						arrData[j] = arrData[j].trim();
						assertTrue(arrData.length==secondRowData.length,"Error Info: Length of second row data in CSV and expected array is not matching");
						String str = arrData[j].toString().replace("\"", "");
						assertTrue(str.equals(secondRowData[j]),"Error Info: Second Row data in CSV is not matching with the expected");
					}
				}
			}
		}
		bufferedReader.close();
		return Line;
	}

	
	public String getOnlyDateAndTimeDetails() throws Exception{
		DateTimeSettings dateTimeSettings_page = new DateTimeSettings();
		Calendar cal = Calendar.getInstance();
		String onlyDate = dateTimeSettings_page.getCurrentDate("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		String curTime1 = sdf.format(cal.getTime());
		String strOnlyDateAndTime = onlyDate+" "+curTime1;
		return strOnlyDateAndTime;
	}
	
	public String getCurrentTime(String timeFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		return sdf.format(cal.getTime());

	}
	
	public void downloadFileInIE(String fileName)throws Exception{
		copyClipBoardData(fileName);
		Robot robot = new Robot();
		Thread.sleep(4000);
				
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);
		
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		
		Thread.sleep(4000);
		
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(4000);

		robot.keyPress(KeyEvent.VK_CONTROL);    
		robot.keyPress(KeyEvent.VK_V);
		     
		robot.keyRelease(KeyEvent.VK_V);    
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(4000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(7000);
	}
	
	public void copyClipBoardData(String fileName)throws Exception{
		StringSelection ss =new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}
	
	public CommonFunctions switchToWindow()throws Exception{
		for (String winHandle : driver.getWindowHandles()) {
		    this.driver.switchTo().window(winHandle);
		}
		return this;
	}
	
	public CommonFunctions closeWindow(String parentHandle)throws Exception{
	driver.close(); 
	driver.switchTo().window(parentHandle);
	return this;
	}
	
	 public void setClipBoardData(String filePath)throws Exception{
		/*  Properties prop = new Properties();
		    InputStream inputStream;
		    inputStream = getClass().getResourceAsStream(System.getProperty("path.file",
		      "/propertiesFiles/path.properties"));
		    prop.load(inputStream);
		    String saveImagePath = prop.getProperty(filePath);
		    File strFile = new File(System.getProperty("user.dir") + saveImagePath);*/
		 /*   String strFilePath = strFile.toString();*/
		    StringSelection ss =new StringSelection(filePath);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		    Robot robot=new Robot();
		    robot.delay(500);

		    robot.keyPress(KeyEvent.VK_CONTROL);    
		    robot.keyPress(KeyEvent.VK_V);

		    robot.keyRelease(KeyEvent.VK_V);    
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.delay(500);

		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.delay(500);
		   }
	 
	 public String getOnlyDateAndTimeDetailsWithMeridian() throws Exception{
			DateTimeSettings dateTimeSettings_page = new DateTimeSettings();
			Calendar cal = Calendar.getInstance();
			String onlyDate = dateTimeSettings_page.getCurrentDate("MM/dd/YYYY");
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
			String curTime1 = sdf.format(cal.getTime());
			String strOnlyDateAndTime = onlyDate+" "+curTime1;
			return strOnlyDateAndTime;
		}
	
	 public CommonFunctions downloadFilesFromIEBrowser(String filePath) throws Exception {
			Robot robot = new Robot();
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_ALT);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			StringSelection ss =new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000); 
			return this;
		}
	
	public String getPastTimeWithPresentDate() throws Exception{
		DateTimeSettings dateTimeSettings_page = new DateTimeSettings();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		String onlyDate = dateTimeSettings_page.getCurrentDate("MM/dd/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		String curTime1 = sdf.format(cal.getTime());
		String strOnlyDateAndTime = onlyDate+" "+curTime1;
		return strOnlyDateAndTime;
	}
	
	/***Modified By:Harshitha
	 *  Date: 03/12/2018
	 */
	public CommonFunctions verifySuccessModalIsDisplayed()throws Exception {
		this.page.element("success_modal","xpath").waitForElementAppearance();
	    assertTrue(this.page.element("success_modal","xpath").isElementPresent(),"Error info: Success Name is not displayed");
		return this;
	}
	
	/***Modified By:Harshitha
	 *  Date: 03/12/2018
	 */
	public CommonFunctions verifyErrorModalIsDisplayed()throws Exception {
		this.page.element("error_modal","xpath").getDisplayedOne();
		this.page.element("error_modal","xpath").getOne().isDisplayed();
		assertTrue(this.page.element("error_modal","xpath").isElementPresent(),"Error info: Error Header is not displayed");
		return this;
	}
	
	
	 /***********************************************
	    * Date       :  28/11/2018
	    * Modifier   :   Harshitha
	   ************************************************/ 
	 public CommonFunctions verifyFilterWaterMarkIsDisplayed() throws Exception {
		 this.page.element("filter", "xpath").isElementPresent();
		 this.page.element("filter", "xpath").waitForElementAppearance();
		 this.page.element("filter", "xpath").getOne().isDisplayed();
		 assertTrue(this.page.element("filter", "xpath").isElementPresent(),"Error Info: Filter water mark is not displayed");
		 return this;
	 }
	 
	 public CommonFunctions maximizeWindow()throws Exception{
			driver.manage().window().maximize();
			return this;
		}
	 
	 public String getBuildIdNumber() throws Exception {
		 String regex = "Build\\s\\d{4}";
		 Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		 String buildIdNumber = this.page.dynamicElement("buildId", "//div[@id='site_type_warning'][text()]","xpath").getOne().getText();
		// System.out.println(buildIdNumber);
		 Matcher matcher = pattern.matcher(buildIdNumber);
		 matcher.find();
		 String buildNumber = matcher.group(0);
		 System.out.println( matcher.group(0));
		 return buildNumber;
	 }
	 public String verSecondSuccessMsg()throws Exception {
			this.page.element("success_msg","xpath").waitForElementAppearance();
			this.page.element("success_msg","xpath").waitForElementAppearance();
			this.page.element("success_msg","xpath").waitForElementAppearance();
			this.page.element("success_msg","xpath").waitForElementAppearance();
			String successMessage=this.driver.findElement(By.xpath("//div[@id='alertbox']/p[2]")).getText();
			return successMessage.trim();
		}
	 public String readAndVerifyCSVFileDataCovidVaccine(String strFilePath,int expectedRows,String[] arrheaders,String[] firstRowData) throws Exception{
			int noOfRows = this.getNoOfRowsInDownloadedFile(strFilePath);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(strFilePath));
			String input=null;
			String Line="";
			System.out.println(noOfRows);
			assertTrue(noOfRows==expectedRows,"ErrorInfo :Expected and Actual No of Rows is not matching");
			String column = "";
			for (int i=1 ; i<=noOfRows ;i++){
				if(i == 1) {
					input=bufferedReader.readLine();
					Line = input;
					String[] arrData = Line.split(",");
					column = arrData[0];
					for(int j= 0; j<arrData.length; j++)
					{
						assertTrue(arrData.length == arrheaders.length,"Error Info: Length of headers in CSV and expected array is not matching");
						String str = arrData[j].toString().replace("\"", "");
						assertTrue(str.equals(arrheaders[j]),"Error Info: Headers in CSV is not matching with the expected");
					}
				}

				if(i == 2) {
					input=bufferedReader.readLine();
					Line = input;
					String[] arrData = Line.split(",");
					if(column.contains("ID")){
						for(int j= 1; j<arrData.length; j++)

						{ 
							arrData[j] = arrData[j].trim();
							assertTrue(arrData.length==firstRowData.length,"Error Info: Length of first row data in CSV and expected array is not matching");
							String str = arrData[j].toString().replace("\"", "");
							assertTrue(str.equals(firstRowData[j]),"Error Info: First Row data in CSV is not matching with the expected");
						}
					}
					else{
						for(int j= 0; j<arrData.length; j++)

						{ 
							arrData[j] = arrData[j].trim();
							assertTrue(arrData.length==firstRowData.length,"Error Info: Length of first row data in CSV and expected array is not matching");
							String str = arrData[j].toString().replace("\"", "");
							System.out.println(firstRowData[j]);
							assertTrue(str.equals(firstRowData[j]),"Error Info: First Row data in CSV is not matching with the expected");
						}
					}
				}
			
			}
			bufferedReader.close();
			return Line;
		}

}
