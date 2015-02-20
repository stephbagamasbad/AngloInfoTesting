/**
 * 
 */
package com.angloinfo.fixtures;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;
import org.w3c.css.sac.Locator;

import com.angloinfo.util.LogUtil;
import com.angloinfo.util.SikuliDriverUtility;
import com.angloinfo.util.StringConstants;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

/**
 * @author GrizelS
 *
 */
public class Registration {

	SikuliDriverUtility sikuliDriver = SikuliDriverUtility.getInstance();
	LogUtil log = LogUtil.getInstance();

	private static Registration instance = new Registration();

	public static Registration getInstance() {
		return instance;

	}

	public boolean isRegistrationDisplayed(WebDriver driver, Screen screen)
			throws Exception {
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));
		navigateRegistration(driver, screen);
		By[] selectors = { By.id(Locators.UL_MAIN_MENU),
				By.id(Locators.UL_MY_ANGLOINFO), By.id(Locators.DIV_REG_FORM) };
		String[] expectedText = { StringConstants.REGISTER };
		WebElement registerForm = sikuliDriver.getElement(driver,
				By.id(Locators.DIV_REG_FORM));

		return isPageDisplayed(driver, screen, selectors, registerForm,
				Locators.IMG_REGISTER, expectedText);

	}

	private void navigateRegistration(WebDriver driver, Screen screen)
			throws Exception {
		// boolean flag = false;
		// log.log("Hover and click: " + lnkElement.getText());
		log.log("Navigating...");
		WebElement myangloinfo = sikuliDriver.getElement(driver,
				By.id(Locators.UL_MY_ANGLOINFO));
		WebElement registerLink = sikuliDriver.getElement(driver,
				By.cssSelector(Locators.LNK_REGISTER));
		
		if (sikuliDriver.isDisplayed(myangloinfo)) {
			System.out.println("log!!!!!!!!!!!");
			log.log("displayed!!!");
			sikuliDriver.click(driver, myangloinfo);
			log.log("Clicked myangloinfo!");

			if (sikuliDriver.isDisplayed(registerLink)) {
				sikuliDriver.click(driver, registerLink);
				log.log("Clicked register link!");
				
				Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));
				
				WebElement registerBtn = sikuliDriver.getElement(driver,
						By.xpath(Locators.BTN_REGISTER));

				if (sikuliDriver.isDisplayed(registerBtn)) {
					sikuliDriver.click(driver, registerBtn);
					log.log("Clicked register button!");
				}
			}
		}
	}

	private boolean isPageDisplayed(WebDriver driver, Screen screen,
			By[] selectors, WebElement lnkElement, String imgLocator,
			String[] textArr) throws Exception {
		boolean flag = false;

		sikuliDriver.click(driver, lnkElement);
		if (sikuliDriver.isImgInScreen(screen, imgLocator)
				|| (sikuliDriver.areElementsDisplayed(driver, selectors)
						&& sikuliDriver.isTextInElement(driver,
								By.cssSelector(Locators.DIV_BANNER_TEXT),
								textArr[1]) && sikuliDriver.isTextInElement(
						driver, By.cssSelector(Locators.DIV_BANNER_TEXT),
						textArr[2]))) {
			flag = true;
		}

		return flag;
	}

}
