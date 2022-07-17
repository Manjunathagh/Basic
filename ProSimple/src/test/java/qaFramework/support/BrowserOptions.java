package qaFramework.support;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import static io.github.bonigarcia.wdm.DriverManagerType.IEXPLORER;

import java.util.HashMap;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;


public class BrowserOptions {
	
	String browser = System.getProperty("browser");
	String os      = System.getProperty("os");
	
	public void ieOptions(InternetExplorerOptions options) throws Exception {
		InternetExplorerDriverManager.getInstance(IEXPLORER).arch32().setup();
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		ieOptions.setCapability("requireWindowFocus", false);
	    ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		ieOptions.setCapability("ignoreZoomSetting", true);
		ieOptions.setCapability("nativeEvents",true);
		
	}
	
	public void windowsChromeOptions(ChromeOptions options) throws Exception {
	
		  ChromeDriverManager.getInstance(CHROME).setup(); 
		  //To Download Files
		  String downloadFilepath = ".\\src\\test\\resources\\downloads\\"; 
		  HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		  chromePrefs.put("profile.default_content_settings.popups", 0);
		  chromePrefs.put("download.default_directory", downloadFilepath);
		  chromePrefs.put("download.prompt_for_download", true); HashMap<String,
		  Object> chromeOptionsMap = new HashMap<String, Object>();
		  options.setExperimentalOption("prefs", chromePrefs);
		  options.addArguments("--test-type"); 
		  options.addArguments("chrome.switches", "--disable-extensions"); 
		  options.addArguments("disable-infobars");
		  options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		  options.setCapability(ChromeOptions.CAPABILITY, options);
		 
		}
	
	public void windowsChromeOptionsHeadless(ChromeOptions options) throws Exception {
		
		  ChromeDriverManager.getInstance(CHROME).setup(); 
		  //To Download Files
		  String downloadFilepath = ".\\src\\test\\resources\\Reports\\"; 
		  HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		  chromePrefs.put("profile.default_content_settings.popups", 0);
		  chromePrefs.put("download.default_directory", downloadFilepath);
		  chromePrefs.put("download.prompt_for_download", true); HashMap<String,
		  Object> chromeOptionsMap = new HashMap<String, Object>();
		  options.setExperimentalOption("prefs", chromePrefs);
		  options.addArguments("--test-type"); 
		  options.addArguments("headless");
		  options.addArguments("chrome.switches", "--disable-extensions"); 
		  options.addArguments("disable-infobars");
		  options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		  options.setCapability(ChromeOptions.CAPABILITY, options);
		 
		}
	
	
	public void linuxChromeOptions(ChromeOptions options) throws Exception {
		  System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
		  options = new ChromeOptions();
		  options.addArguments("--headless");
		  options.addArguments("--no-sandbox");
		  options.addArguments("disable-setuid-sandbox");
		  options.addArguments("allow-insecure-localhost");
		  options.addArguments("--start-maximized");
		  options.addArguments("--disable-gpu");
		  options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
	}	
	
	public void firefoxOptions(FirefoxOptions options) throws Exception {
		FirefoxDriverManager.getInstance(FIREFOX).setup();
		options.addPreference("browser.download.dir", ".\\src\\test\\resources\\Reports\\");
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.manager.showWhenStarting", false);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,text/plain,application/octet-stream,application/pdf,application/x-pdf,application/vnd.pdf");
		options.addPreference("browser.helperApps.neverAsk.openFile", "application/excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,text/plain,application/octet-stream,application/pdf,application/x-pdf,application/vnd.pdf");
		options.addPreference("browser.helperApps.alwaysAsk.force", false);
		options.addPreference("browser.download.manager.useWindow", false);
		options.addPreference("browser.download.manager.focusWhenStarting", false);
		options.addPreference("browser.helperApps.neverAsk.openFile", "");
		options.addPreference("browser.download.manager.alertOnEXEOpen", false);
		options.addPreference("browser.download.manager.showAlertOnComplete", false);
		options.addPreference("browser.download.manager.closeWhenDone", true);
		options.addPreference("pdfjs.disabled", true);
	}	
	 public void killBrowserDriver(){
		 Runtime runTime;
			runTime = Runtime.getRuntime();
			try {
				// Execute command thru Runtime
			if ("windows".equals(os)) {	
				if ("iexplore".equals(browser)) {
					runTime.exec("taskkill /F /IM IEDriverServer.exe");
				    runTime.exec("taskkill /F /IM iexplore.exe");
				System.out.println("Killed unused ie Drivers");
				}
				
				if ("chrome".equals(browser)) {
					runTime.exec("taskkill /F /IM chromedriver.exe");
					runTime.exec("taskkill /F /IM chrome.exe");
				System.out.println("Killed unused chrome Drivers");
				}
				  }		
			
			if ("linux".equals(os)) {	
				if ("chrome".equals(browser)) {
				runTime.exec("killall -9 chrome");					
				System.out.println("Killed unused chrome Drivers");
				}
				  }			
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
}