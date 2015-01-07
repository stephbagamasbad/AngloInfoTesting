package com.angloinfo.fixtures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.angloinfo.util.LogUtil;
import com.angloinfo.util.StringConstants;
import com.angloinfo.util.Utility;

/**
 * Contains all directory test methods
 * 
 * @author Stephb
 * 
 */
public class Directory {
	Utility utility = Utility.getInstance();
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

		WebElement lnkDirectory = utility.getElement(driver,
				By.cssSelector(Locators.LNK_HEALTH_BEAUTY));

		log.log("clicking: " + lnkDirectory.getText());

		utility.click(driver, lnkDirectory);

		if (utility.isImgInScreen(screen, Locators.IMG_TOKYO_MAP)
				&& utility.isImgInScreen(screen, Locators.IMG_ADD_YOUR_LISTING)) {
			flag = true;
		} else {
			log.log("Expected Image not displayed, checking for element presence in page");
			if (utility.areElementsDisplayed(driver, selectors)) {
				flag = true;
			}
		}
		return flag;
	}

}
