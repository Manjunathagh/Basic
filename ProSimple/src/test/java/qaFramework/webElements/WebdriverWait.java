package qaFramework.webElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebdriverWait {
	
	public By webDriverBy;
	WebDriver driver;

	public WebdriverWait(ElementList elementList, By by) {
		this.driver = elementList.driver;
		this.webDriverBy = by;
	}
	
	public void waitForInvisibilityOfElement(){
		WebDriverWait wait = new WebDriverWait(this.driver, WaitTimeConstants.WAIT_TIME_SMALL);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(this.webDriverBy));
	}
	
	public void waitForVisibilityOfElement(){
		WebDriverWait wait = new WebDriverWait(this.driver, WaitTimeConstants.WAIT_TIME_SMALL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.webDriverBy));
	}
}