package com.angloinfo.fixtures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.angloinfo.util.LogUtil;
import com.angloinfo.util.StringConstants;
import com.angloinfo.util.Utility;

/**
 * Contains all test methods
 * 
 * @author Stephb
 * 
 */
public class NavBar {
	Utility utility = Utility.getInstance();
	LogUtil log = LogUtil.getInstance();

	private static NavBar instance = new NavBar();

	public static NavBar getInstance() {
		return instance;
	}

	/**
	 * AI-110 Checks if user can see the main menu even when I scrolling
	 * 
	 * @param driver
	 * @param screen
	 * @return
	 * @throws Exception
	 */
	public boolean isMainNavAlwaysDisplayed(WebDriver driver, Screen screen)
			throws Exception {

		boolean flag = false;
		By[] selectors = { By.id(Locators.UL_MAIN_MENU),
				By.id(Locators.UL_MY_ANGLOINFO) };

		utility.scrollDown(driver);
		if (utility.isImgInScreen(screen, Locators.IMG_NAV_BAR)
				|| utility.areElementsDisplayed(driver, selectors)) {
			flag = true;
		}

		return flag;
	}

	/**
	 * AI-81 Checks if user can view directory page
	 * 
	 * @param driver
	 * @param screen
	 * @param username
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public boolean isDirectoryDisplayed(WebDriver driver, Screen screen)
			throws Exception {

		By[] selectors = { By.id(Locators.TEXT_DIRECTORY_SEARCH),
				By.id(Locators.UL_MAIN_MENU), By.id(Locators.UL_MY_ANGLOINFO),
				By.id(Locators.NAV_TOP_BAR), By.id(Locators.DIV_BANNER),
				By.cssSelector(Locators.IMG_LOGO) };
		
		String[] expectedText = { StringConstants.DIRECTORY,
				StringConstants.DIRECTORY_VERBIAGE };

		WebElement lnkDirectory = utility.getElement(driver,
				By.xpath(Locators.LNK_NAV_DIRECTORY));

		isHoverVisible(driver, screen, lnkDirectory,
				Locators.IMG_HOVER_DIRECTORY);

		return isPageDisplayed(driver, screen, selectors, lnkDirectory,
				Locators.IMG_DIRECTORY, expectedText);
	}

	/**
	 * 
	 * 
	 * @param driver
	 * @param screen
	 * @param username
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public boolean isWhatsOnDisplayed(WebDriver driver, Screen screen)
			throws Exception {

		By[] selectors = { By.id(Locators.TEXT_WHATS_ON_SEARCH),
				By.id(Locators.UL_MAIN_MENU), By.id(Locators.UL_MY_ANGLOINFO),
				By.id(Locators.NAV_TOP_BAR), By.id(Locators.DIV_BANNER),
				By.cssSelector(Locators.IMG_LOGO) };

		String[] expectedText = { StringConstants.WHATS_ON,
				StringConstants.CLASSIFIEDS_VERBIAGE };
		
		WebElement lnkWhatsOn = utility.getElement(driver,
				By.xpath(Locators.LNK_NAV_WHATS_ON));

		isHoverVisible(driver, screen, lnkWhatsOn,
				Locators.IMG_HOVER_WHATS_ON);

		return  isPageDisplayed(driver, screen, selectors, lnkWhatsOn,
				Locators.IMG_WHATS_ON, expectedText);
	}

	/**
	 * 
	 * 
	 * @param driver
	 * @param screen
	 * @param username
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public boolean isDiscussionsDisplayed(WebDriver driver, Screen screen)
			throws Exception {

		By[] selectors = { By.id(Locators.TEXT_DISCUSSIONS_SEARCH),
				By.id(Locators.UL_MAIN_MENU), By.id(Locators.UL_MY_ANGLOINFO),
				By.id(Locators.NAV_TOP_BAR), By.id(Locators.DIV_BANNER),
				By.cssSelector(Locators.IMG_LOGO) };

		String[] expectedText = { StringConstants.DISCUSSIONS,
				StringConstants.DISCUSSION_VERBIAGE };
		
		WebElement lnkDiscussions = utility.getElement(driver,
				By.xpath(Locators.LNK_NAV_DISCUSSION));
		
		isHoverVisible(driver, screen, lnkDiscussions,
				Locators.IMG_HOVER_DISCUSSIONS);

		return isPageDisplayed(driver, screen, selectors, lnkDiscussions,
				Locators.IMG_DISCUSSIONS, expectedText);
	}

	/**
	 * AI-52 Checks if user can view classifieds page
	 * 
	 * @param driver
	 * @param screen
	 * @return
	 * @throws Exception
	 */
	public boolean isClassifiedsPageDisplayed(WebDriver driver, Screen screen)
			throws Exception {

		By[] selectors = { By.id(Locators.TEXT_CLASSIFIEDS_SEARCH),
				By.id(Locators.UL_MAIN_MENU), By.id(Locators.UL_MY_ANGLOINFO),
				By.id(Locators.NAV_TOP_BAR), By.id(Locators.DIV_BANNER),
				By.cssSelector(Locators.DIV_BANNER_TEXT),
				By.cssSelector(Locators.IMG_LOGO),
				By.cssSelector(Locators.DIV_ADD_LISTING) };

		String[] expectedText = { StringConstants.CLASSIFIEDS,
				StringConstants.CLASSIFIEDS_VERBIAGE };

		WebElement lnkClassifieds = utility.getElement(driver,
				By.xpath(Locators.LNK_NAV_CLASSIFIEDS));

		isHoverVisible(driver, screen, lnkClassifieds,
				Locators.IMG_HOVER_CLASSIFIEDS);

		return isPageDisplayed(driver, screen, selectors, lnkClassifieds,
				Locators.IMG_CLASSIFIEDS, expectedText);
	}

	/**
	 * AI-81 Checks if user can view guides page
	 * 
	 * @param driver
	 * @param screen
	 * @return
	 * @throws Exception
	 */
	public boolean isGuidesPageDisplayed(WebDriver driver, Screen screen)
			throws Exception {

		By[] selectors = { By.id(Locators.TEXT_GUIDES_SEARCH),
				By.id(Locators.UL_MAIN_MENU), By.id(Locators.UL_MY_ANGLOINFO),
				By.id(Locators.NAV_TOP_BAR), By.id(Locators.DIV_BANNER),
				By.cssSelector(Locators.IMG_LOGO),
				By.cssSelector(Locators.DIV_ADD_LISTING) };

		String[] expectedText = { StringConstants.GUIDES,
				StringConstants.GUIDES_VERBIAGE };

		WebElement lnkGuides = utility.getElement(driver,
				By.xpath(Locators.LNK_NAV_GUIDES));

		isHoverVisible(driver, screen, lnkGuides, Locators.IMG_HOVER_GUIDES);

		return isPageDisplayed(driver, screen, selectors, lnkGuides,
				Locators.IMG_GUIDES, expectedText);
	}

	private boolean isHoverVisible(WebDriver driver, Screen screen,
			WebElement lnkElement, String imgLocator) throws Exception {
		boolean flag = false;
		log.log("Hover and click: " + lnkElement.getText());

		utility.hover(driver, lnkElement);
		if (!utility.isImgInScreen(screen, imgLocator)) {

			log.log("Hover Image not displayed.");
		} else {
			flag = true;
		}

		return flag;

	}

	private boolean isPageDisplayed(WebDriver driver, Screen screen,
			By[] selectors, WebElement lnkElement, String imgLocator,
			String[] textArr) throws Exception {
		boolean flag = false;

		utility.click(driver, lnkElement);
		if (utility.isImgInScreen(screen, imgLocator)
				|| (utility.areElementsDisplayed(driver, selectors)
						&& utility.isTextInElement(driver,
								By.cssSelector(Locators.DIV_BANNER_TEXT),
								textArr[1]) && utility.isTextInElement(driver,
						By.cssSelector(Locators.DIV_BANNER_TEXT), textArr[2]))) {
			flag = true;

		}
		return flag;
	}
}
