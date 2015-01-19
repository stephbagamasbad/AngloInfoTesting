package com.angloinfo.fixtures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.angloinfo.util.LogUtil;
import com.angloinfo.util.StringConstants;
import com.angloinfo.util.SikuliDriverUtility;

/**
 * Contains all add your listing test methods
 * 
 * @author Stephb
 * 
 */
public class Sales {
	SikuliDriverUtility sikuliDriver = SikuliDriverUtility.getInstance();
	LogUtil log = LogUtil.getInstance();

	private static Sales instance = new Sales();

	public static Sales getInstance() {
		return instance;
	}


	/**
	 * AI-110 Checks if user can see the main menu even when scrolling
	 * 
	 * @param driver
	 * @param screen
	 * @return
	 * @throws Exception
	 */
	public boolean isMainNavAlwaysDisplayed(WebDriver driver, Screen screen,
			String imgFolderLoc) throws Exception {

		boolean flag = false;
		By[] selectors = { By.id(Locators.UL_MAIN_MENU),
				By.id(Locators.UL_MY_ANGLOINFO) };

		sikuliDriver.scrollDown(driver);
		if (sikuliDriver.isImgInScreen(screen,Locators.IMG_NAV_BAR)
				|| sikuliDriver.areElementsDisplayed(driver, selectors)) {
			flag = true;
		}

		return flag;
	}


}
