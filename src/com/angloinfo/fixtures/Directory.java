package com.angloinfo.fixtures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.angloinfo.util.LogUtil;
import com.angloinfo.util.StringConstants;
import com.angloinfo.util.SikuliDriverUtility;

/**
 * Contains all directory test methods
 * 
 * @author Stephb
 * 
 */
public class Directory {
	SikuliDriverUtility sikuliDriver = SikuliDriverUtility.getInstance();
	LogUtil log = LogUtil.getInstance();

	private static Directory instance = new Directory();

	public static Directory getInstance() {
		return instance;
	}

	public boolean isCategoryDisplayed(WebDriver driver, Screen screen)
			throws Exception {
		boolean flag = false;
		By[] selectors = { By.id(Locators.DIV_MAP_CANVAS),
				By.id(Locators.UL_MAIN_MENU), By.id(Locators.UL_MY_ANGLOINFO),
				By.id(Locators.NAV_TOP_BAR),
				By.cssSelector(Locators.DIV_ADD_LISTING) };

		WebElement lnkDirectory = sikuliDriver.getElement(driver,
				By.cssSelector(Locators.LNK_HEALTH_BEAUTY));

		log.log("clicking: " + lnkDirectory.getText());

		sikuliDriver.click(driver, lnkDirectory);

		if (sikuliDriver.isImgInScreen(screen, Locators.IMG_TOKYO_MAP)
				&& sikuliDriver.isImgInScreen(screen, Locators.IMG_ADD_YOUR_LISTING)) {
			flag = true;
		} else {
			log.log("Expected Image not displayed, checking for element presence in page");
			if (sikuliDriver.areElementsDisplayed(driver, selectors)) {
				flag = true;
			}
		}
		return flag;
	}

}
