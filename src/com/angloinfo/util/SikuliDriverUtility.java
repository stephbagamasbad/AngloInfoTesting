package com.angloinfo.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

/**
 * Utility class
 * 
 * @author MSRBagamasbad
 * 
 */
public class SikuliDriverUtility {

	private static SikuliDriverUtility instance = new SikuliDriverUtility();
	private String imageLoc;

	WriteExcel writeExcel = WriteExcel.getInstance();
	WriteTextFile writeTextFile = WriteTextFile.getInstance();
	LogUtil log = LogUtil.getInstance();
	static ReadExcel readExcel = ReadExcel.getInstance();

	/**
	 * Returns instance of the class
	 * 
	 * @return instance
	 */
	public static SikuliDriverUtility getInstance() {
		return instance;
	}

	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	/**
	 * 
	 * @param driver
	 * @param url
	 * @throws Exception
	 */
	public void open(WebDriver driver, String url) throws Exception {
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));
		driver.get(url);
	}

	/**
	 * Driver gets the url of the current window and appends authentication
	 * credentials
	 * 
	 * @param url
	 * @param username
	 * @param pw
	 * @return
	 */
	public String getAuthUrl(String url, String username, String pw) {

		StringBuilder authUrl = new StringBuilder();
		authUrl.append(url.substring(0, url.indexOf(StringConstants.WWW)))
				.append(username).append(":").append(pw).append("@")
				.append(url.substring(url.indexOf(StringConstants.WWW)));
		log.log("URL: " + authUrl.toString());
		return authUrl.toString();
	}

	/**
	 * Driver gets the url of the current window
	 * 
	 * @param driver
	 * @return
	 */
	public String getUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	/**
	 * Driver gets page source of currently focused window
	 * 
	 * @param driver
	 * @return
	 */
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	/**
	 * Driver gets title of currently focused window
	 * 
	 * @param driver
	 * @return
	 */
	public String getWindowTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * Driver gets id of currently focused window
	 * 
	 * @param driver
	 * @return
	 */
	public String getWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}

	/**
	 * Driver gets ids of all active browser windows
	 * 
	 * @param driver
	 * @return
	 */
	public Set<String> getWindowIDs(WebDriver driver) {
		return driver.getWindowHandles();
	}

	/**
	 * Driver switches to window with given title
	 * 
	 * @param driver
	 * @param windowTitle
	 */
	public void switchToWindow(WebDriver driver, String windowTitle) {
		Set<String> ids = getWindowIDs(driver);
		for (String window : ids) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				break;
			}
		}

	}

	/**
	 * Driver switches to frame
	 * 
	 * @param driver
	 * @param frameLocator
	 */
	public void switchToFrame(WebDriver driver, String frameLocator) {
		driver.switchTo().frame(frameLocator);
	}

	/**
	 * Driver selects parent window
	 * 
	 * @param driver
	 * @return
	 */
	public void selectParentWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * Driver gets alert text
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String getAlert(WebDriver driver) throws Exception {
		String text = "";
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));
		try {
			Alert alert = driver.switchTo().alert();
			alert.getText();
			alert.accept();
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		selectParentWindow(driver);

		return text;

	}

	/**
	 * Driver gets element
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public WebElement getElement(WebDriver driver, By by) {
		return driver.findElement(by);
	}

	/**
	 * Driver gets a list of elements with the same selector
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public List<WebElement> getElements(WebDriver driver, By by) {
		return driver.findElements(by);
	}

	/**
	 * Driver types in element
	 * 
	 * @param element
	 * @param text
	 */
	public void type(WebElement element, String text) {
		if (isDisplayed(element) && element.isEnabled()) {
			element.sendKeys(text);
		}

	}

	/**
	 * Driver gets text in element
	 * 
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		String text = "";
		if (isDisplayed(element)) {
			text = element.getText();
		}
		return text;
	}

	/**
	 * Driver gets element attribute
	 * 
	 * @param element
	 * @param attribute
	 * @return
	 */
	public String getAttribute(WebElement element, String attribute) {
		String text = "";
		if (isDisplayed(element)) {
			text = element.getAttribute(attribute);
		}
		return text;
	}

	/**
	 * Driver gets element tag
	 * 
	 * @param element
	 * @return
	 */
	public String getTagName(WebElement element) {
		String text = "";
		if (isDisplayed(element)) {
			text = element.getTagName();
		}
		return text;
	}

	/**
	 * Driver checks if element is displayed
	 * 
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	/**
	 * Driver checks if elements are displayed
	 * 
	 * @param driver
	 * @param selectors
	 * @return
	 */
	public boolean areElementsDisplayed(WebDriver driver, By[] selectors) {
		boolean flag = false;
		log.log("Expected Image not displayed, checking for element presence in page");
		for (By selector : selectors) {
			flag = flag && driver.findElement(selector).isDisplayed();
		}

		return flag;
	}

	/**
	 * Driver checks if text is in element
	 * 
	 * @param driver
	 * @param selector
	 * @param text
	 * @return
	 */
	public boolean isTextInElement(WebDriver driver, By selector, String text) {
		WebElement element = driver.findElement(selector);

		return isTextInElement(element, text);
	}

	/**
	 * Driver checks if text is in element
	 * 
	 * @param element
	 * @param text
	 * @return
	 */
	public boolean isTextInElement(WebElement element, String text) {
		boolean flag = false;
		if (element.getText().contains(text)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Driver checks if element is selected
	 * 
	 * @param element
	 * @return
	 */
	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}

	/**
	 * Driver clicks element
	 * 
	 * @param driver
	 * @param by
	 */
	public void click(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);

		if (element.isEnabled()) {
			element.click();
		}
	}
	
	/**
	 */
	
	public void click(Screen screen, String filename) throws FindFailed {
		
		String fileLoc = imageLoc + filename;
		log.log(fileLoc);
		Pattern pattern = new Pattern(fileLoc);
		screen.click(pattern);
	}


	/**
	 * Driver clicks element
	 * 
	 * @param driver
	 * @param element
	 * @throws Exception
	 */
	public void click(WebDriver driver, WebElement element) throws Exception {

		if (element.isEnabled()) {
			log.log("Clicking...");
			element.click();
			Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));

		}
	}

	/**
	 * Driver hovers mouse on element
	 * 
	 * @param driver
	 * @param element
	 * @throws Exception
	 */
	public void hover(WebDriver driver, WebElement element) throws Exception {
		Actions action = new Actions(driver);

		if (element != null) {
			log.log("Hovering...");
			action.moveToElement(element).build().perform();
		}
	}

	/**
	 * Driver scrolls down by 350px
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void scrollDown(WebDriver driver) throws Exception {

		log.log("Scrolling down...");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 350);");
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));

	}

	/**
	 * Driver scrolls to bottom of webpage
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void scrollToBottom(WebDriver driver) throws Exception {

		log.log("Scrolling to Bottom...");
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));
	}

	/**
	 * Driver scrolls up by 250 px
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void scrollUp(WebDriver driver) throws Exception {

		log.log("Scrolling up...");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(250, 0);");
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));

	}

	/**
	 * Sikuli checks if image is in screen
	 * 
	 * @param screen
	 * @param fileLoc
	 * @return
	 * @throws Exception
	 */
	public boolean isImgInScreen(Screen screen, String fileName)
			throws Exception {
		boolean flag = false;
		String fileLoc = imageLoc + fileName;
		log.log(fileLoc);
		Pattern pattern = new Pattern(fileLoc);
		if (screen.wait(pattern, 10) != null) {
			highlightImage(screen, pattern);
			flag = true;
		}

		return flag;
	}

	/**
	 * Sikuli highlight located image in screen
	 * 
	 * @param screen
	 * @param pattern
	 * @throws Exception
	 */
	public void highlightImage(Screen screen, Pattern pattern) throws Exception {
		Region r = new Region(screen.find(pattern).getX(), screen.find(pattern)
				.getY(), screen.find(pattern).getW(), screen.find(pattern)
				.getH());

		r.highlight(5);
	}

	/**
	 * Driver takes screenshot
	 * 
	 * @param driver
	 * @param filename
	 * @throws IOException
	 */
	public void takeScreenshot(WebDriver driver, String filename)
			throws IOException {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filename));
	}

}
