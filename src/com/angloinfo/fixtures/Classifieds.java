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
public class Classifieds {
	SikuliDriverUtility sikuliDriver = SikuliDriverUtility.getInstance();
	LogUtil log = LogUtil.getInstance();

	private static Classifieds instance = new Classifieds();

	public static Classifieds getInstance() {
		return instance;
	}





}
