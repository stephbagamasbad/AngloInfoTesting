package com.angloinfo.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.Parameters;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * Utility class
 * 
 * @author MSRBagamasbad
 * 
 */
public class Utility {

	private static Random rnd = new Random();
	private static Utility instance = new Utility();
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
	public static Utility getInstance() {
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
	 * 
	 * @param driver
	 * @return
	 */
	public String getUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public String getWindowTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public String getWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public Set<String> getWindowIDs(WebDriver driver) {
		return driver.getWindowHandles();
	}

	/**
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
	 * 
	 * @param driver
	 * @return
	 */
	public void switchToFrame(WebDriver driver, String frameLocator) {
		driver.switchTo().frame(frameLocator);
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public void selectParentWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 * @throws NumberFormatException
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
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public WebElement getElement(WebDriver driver, By by) {
		return driver.findElement(by);
	}

	/**
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public List<WebElement> getElements(WebDriver driver, By by) {
		return driver.findElements(by);
	}

	/**
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
	 * 
	 * @param element
	 */
	public String getText(WebElement element) {
		String text = "";
		if (isDisplayed(element)) {
			text = element.getText();
		}
		return text;
	}

	/**
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
	 * 
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public boolean areElementsDisplayed(WebDriver driver, By[] selectors) {
		boolean flag = false;
		log.log("Expected Image not displayed, checking for element presence in page");
		for (By selector : selectors) {
			flag = flag && driver.findElement(selector).isDisplayed();
		}

		return flag;
	}

	/**
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
	 * 
	 * @param element
	 * @return
	 */
	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	public void click(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);

		if (element.isEnabled()) {
			element.click();
		}
	}

	/**
	 * 
	 * @param element
	 * @return
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
	 * 
	 * @param element
	 * @return
	 */
	public void hover(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		Actions action = new Actions(driver);

		if (element != null) {
			action.moveToElement(element).build().perform();
		}
	}

	/**
	 * 
	 * @param element
	 * @return
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
	 * 
	 * @param length
	 * @return
	 */
	public static String generateString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyz";

		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rnd.nextInt(characters.length()));
		}
		return new String(text);
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String generateNumbers(int length) {
		String characters = "0123456789";

		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rnd.nextInt(characters.length()));
		}
		return new String(text);
	}

	/**
	 * 
	 * @param fName
	 * @param lName
	 * @param email
	 * @param phone
	 * @param pwd
	 * @throws WriteException
	 * @throws IOException
	 */
	public void saveToExcel(String fName, String lName, String email,
			String phone, String pwd) throws Exception {
		// writeExcel.saveToExcel(fName, lName, email, phone, pwd);
		log.log("Saving values to excel sheet: " + fName + ", " + lName + ", "
				+ email + ", " + phone + ", " + pwd);
	}

	public void saveToCSVFile(HashMap result, String filenameProd) {
		writeTextFile.saveToCSVFile(result, filenameProd);
		// log.log("Saving values to Text file: "+
		// userName+", "+password+", "+billingTown);
	}

	/**
	 * 
	 * @param selenium
	 * @return
	 */
	public String getCurrentURL(DefaultSelenium selenium) {
		String url = "";
		url = selenium.getLocation();
		log.log("CURRENT PAGE URL: " + url);

		return url;
	}

	/**
	 * 
	 * @param selenium
	 * @return
	 */
	public boolean isURLSecured(DefaultSelenium selenium) {
		boolean flag = false;
		String token = "";
		String url = "";

		StringTokenizer st = new StringTokenizer(getCurrentURL(selenium), ":");
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			url = st.nextToken();
			log.log("Security: " + token);
			flag = token.equalsIgnoreCase("https");
		}
		return flag;
	}

	/**
	 * 
	 * @param selenium
	 * @param fileName
	 * @return
	 */
	public boolean captureScreenshot(DefaultSelenium selenium, String fileName) {
		boolean flag = false;
		log.log("Capturing screenshot...");
		selenium.captureScreenshot(fileName);

		return flag;
	}

	/**
	 * 
	 * @param selenium
	 * @param xpath
	 * @return
	 */
	public int getXpathCount(DefaultSelenium selenium, String xpath) {
		int count = 0;

		log.log("Getting XPATH count...");
		count = new Integer(selenium.getXpathCount(xpath).toString())
				.intValue();
		log.log("XPATH COUNT: " + count);

		return count;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public List split(String text, String delimiter) {
		List tokenized = new ArrayList();

		StringTokenizer st = new StringTokenizer(text, delimiter);
		while (st.hasMoreTokens()) {
			tokenized.add(st.nextToken());
		}

		System.out.println("tokenized: " + tokenized);
		return tokenized;
	}

	/**
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public boolean compareList(List list1, List list2) {
		boolean flag = true;

		Collection<String> listOne = list1;
		Collection<String> listTwo = list2;

		Collection<String> similar = new HashSet<String>(listOne);
		Collection<String> different = new HashSet<String>();
		different.addAll(listOne);
		different.addAll(listTwo);

		similar.retainAll(listTwo);
		different.removeAll(similar);

		if (different.size() > 0) {
			flag = false;
		}

		System.out.printf("One:%s%nTwo:%s%nSimilar:%s%nDifferent:%s%n",
				listOne, listTwo, similar, different);

		return flag;
	}

	/**
	 * 
	 * @param list
	 * @param element
	 */
	public List removeElementFromList(List list, String element) {
		List newList = list;

		for (int i = 0; i < list.size(); i++) {
			if (element.equalsIgnoreCase(list.get(i).toString())) {
				list.remove(i);
			}
		}

		return newList;
	}

	/**
	 * 
	 * @param list1
	 * @return
	 */
	public StringBuffer convertListToString(List list1, String delimiter) {
		StringBuffer convertedToString = new StringBuffer();
		for (int i = 0; i < list1.size(); i++) {
			convertedToString.append(list1.get(i)).append(delimiter);
		}

		return convertedToString;
	}

	/**
	 * 
	 * @param rowNum
	 * @param cellNum
	 * @param result
	 * @throws Exception
	 */
	public void saveResultsToExcel(String rowNum, String cellNum, String result)
			throws Exception {
		System.out.println("Saving values to file: " + result);
		writeExcel.writeResultsToExcel(Integer.parseInt(rowNum),
				Integer.parseInt(cellNum), result);

	}

	public void takeScreenshot(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils
				.copyFile(
						scrFile,
						new File(
								"C:\\Users\\stephb\\workspace\\AngloInfoTesting\\reports\\screenshot.png"));
	}

}
