package browserConfiguration;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import qaFramework.support.BrowserOptions;
import qaFramework.support.ClearTemporaryFiles;
import qaFramework.support.DateAndTimeFunctions;
import qaFramework.support.ReadPropertyFiles;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.ExtentColor;

public class BrowserConfiguration {

	public static WebDriver driver;
	protected String TCID;
	protected String TC_DESCRIPTION;
	protected String Result;
	protected String Reason;
	public static String browser;
	public static String os;
	public static AppiumDriver<MobileElement> driver2;
	public static String module;
	public String platformName = "Android";
	public static String buildNumber;
	public static String siteHost;

	  public ExtentHtmlReporter htmlReporter; 
	  public ExtentReports extent; 
	  public ExtentTest test;
	 
	BrowserOptions browserOptions = new BrowserOptions();
	
	  @BeforeClass 
	  public void extentReportSetup() { 
	  // location of the extent  report 
	  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/target/ExtentReport.html"); 
	  extent = new ExtentReports(); 
	  // create   object of ExtentReports 
	  extent.attachReporter(htmlReporter);
	  
	  htmlReporter.config().setDocumentTitle("Automation Report"); 
	  // Title of Report 
	  htmlReporter.config().setReportName("Extent Report V4"); 
	  // Name of the report 
	  htmlReporter.config().setTheme(Theme.STANDARD);
	  // Default Theme of Report 
	  }
	 
	
	@BeforeMethod 
	  	public void setUp() throws Exception {
		browser = System.getProperty("browser");
		os      = System.getProperty("os");
		module  = System.getProperty("module");
		siteHost  = System.getProperty("siteHost");

		
		if ("web".equals(module)) {

			if ("chrome".equals(browser)) {
				getChromedriver();
			}
			if ("chromeHeadless".equals(browser)) {
				getChromedriver();
			}
			if ("iexplore".equals(browser)) {
				getIEdriver();
			}
			if ("firefox".equals(browser)) {
				getFirefoxdriver();
			}
		    if ("windows".equals(os)) {
		    	driver.get(siteHost);
		    	driver.manage().window().maximize();
		    		    }
		} else if ("mobile".equals(module)) {
			mobilesetUp();
		}
	}
	
	  @BeforeMethod 
	  
	  public void setResultToFalse() throws Exception {
		
	  Result = "FAIL"; }
	 
	public WebDriver getIEdriver() throws Exception {
		InternetExplorerOptions options = new InternetExplorerOptions();
		if ("windows".equals(os)) {
		browserOptions.ieOptions(options);}
		return driver = new InternetExplorerDriver(options);
		
	}

	public WebDriver getChromedriver()throws Exception{
		
		ChromeOptions options = new ChromeOptions();
		if (("windows".equals(os))& ("chrome".equals(browser))){
			browserOptions.windowsChromeOptions(options);
		}
		if (("windows".equals(os))& ("chromeHeadless".equals(browser))){
			browserOptions.windowsChromeOptionsHeadless(options);
		}
		if ("linux".equals(os)) {
			browserOptions.linuxChromeOptions(options);
		}
		return driver = new ChromeDriver( ChromeDriverService.createDefaultService(),  options);
	}

	public WebDriver getFirefoxdriver() throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		if ("windows".equals(os)) {
		browserOptions.firefoxOptions(options);}
		return driver = new FirefoxDriver(options);
	}

	public void mobilesetUp() throws Exception {

		System.out.println(MobilePlatform.ANDROID);
		ReadPropertyFiles readPropertyFiles = new ReadPropertyFiles();
		Properties envPropertyDetails = readPropertyFiles.ReadEnvironment();

		if (MobilePlatform.IOS == platformName) {

			String appPackage = envPropertyDetails.getProperty("iOS_appPackage");
			String apkPath = System.getProperty("user.dir") + envPropertyDetails.getProperty("iOS_apkPath");
			String iOS_platformName = envPropertyDetails.getProperty("iOS_platformName");
			String platformVersion = envPropertyDetails.getProperty("iOS_platformVersion");
			String deviceName = envPropertyDetails.getProperty("iOS_deviceName");
			String udId = envPropertyDetails.getProperty("UDID");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", iOS_platformName);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("udid", udId);
			capabilities.setCapability(MobileCapabilityType.APP, apkPath);
			capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, appPackage);
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("fullReset", false);
			capabilities.setCapability("newCommandTimeout", "2000000");
			capabilities.setCapability("simpleIsVisibleCheck", true);
			driver2 = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver2.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

		 if (MobilePlatform.ANDROID == platformName) {
			String appPackage = envPropertyDetails.getProperty("appPackage");
			String apkPath = System.getProperty("user.dir") + envPropertyDetails.getProperty("apkPath");
			String android_platformName = envPropertyDetails.getProperty("platformName");
			String deviceName = envPropertyDetails.getProperty("deviceName");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability("platformName", android_platformName);
			capabilities.setCapability(MobileCapabilityType.APP, apkPath);
			capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, appPackage);
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("fullReset", false);
			capabilities.setCapability("newCommandTimeout", "2000000");
			driver2 = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver2.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		DateAndTimeFunctions dateAndTimeFunctions = new DateAndTimeFunctions();
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				Robot r = new Robot();
				// It saves screenshot to desired path
				String path = ".\\src\\test\\resources\\Screenshots\\" + result.getName() + "_time_"
						+ System.currentTimeMillis() + ".png";
				// Used to get ScreenSize and capture image
				Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage Image = r.createScreenCapture(capture);
				ImageIO.write(Image, "png", new File(path));
				System.out.println("Screenshot saved");
				
				FileWriter fw = new FileWriter(".\\src\\test\\resources\\results\\FailResults.csv", true);
				PrintWriter out = new PrintWriter(fw);
				out.println(result.getName() + "," + Result + ","
						+ dateAndTimeFunctions.getCurrentDate("MM/dd/yyyy hh:mm")+ "," + buildNumber+ ",");
				out.close();
				
				 test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
		         test.fail(result.getThrowable());
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		if (ITestResult.SKIP == result.getStatus()) {
			Result = "SKIP";
			FileWriter fw = new FileWriter(".\\src\\test\\resources\\results\\SkipResults.csv", true);
			PrintWriter out = new PrintWriter(fw);
			out.println(result.getName() + "," + Result + ","
					+ dateAndTimeFunctions.getCurrentDate("MM/dd/yyyy hh:mm")+ "," + buildNumber + ",");
			out.close();
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
 
		} // code to write results to Notepad Since QNET is not available --FIXME
		if (ITestResult.SUCCESS == result.getStatus()) {
			Result = "PASS";
			FileWriter fw = new FileWriter(".\\src\\test\\resources\\results\\PassResults.csv", true);
			PrintWriter out = new PrintWriter(fw);
			out.println(result.getName() + "," + Result + ","+ dateAndTimeFunctions.getCurrentDate("MM/dd/yyyy hh:mm") + "," + buildNumber+ ",");
			out.close();
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
			 
		}
	}
		@AfterMethod
		
		public void closeBrowser() {
			if (driver != null) {
				driver.close();
				driver.quit();
			}
		 else if ("mobile".equalsIgnoreCase(module)) {
			driver2.quit();
		}
		}

		public void killBrowser() {
		if ("web".equalsIgnoreCase(module)) {
			try {
//				browserOptions.killBrowserDriver();
//				ClearTemporaryFiles clearTemporaryFiles = new ClearTemporaryFiles();
//				clearTemporaryFiles.deleteTempFiles();
			} catch (Exception e) {
			}
		
		}

         }
		
		
		  @AfterClass public void endReport() { extent.flush(); 
		  killBrowser();}
		 
		}