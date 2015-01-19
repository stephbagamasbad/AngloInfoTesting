package com.angloinfo.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.sikuli.script.Screen;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.angloinfo.fixtures.Classifieds;
import com.angloinfo.fixtures.Directory;
import com.angloinfo.fixtures.Footer;
import com.angloinfo.fixtures.Guides;
import com.angloinfo.fixtures.NavBar;
import com.angloinfo.util.LogUtil;
import com.angloinfo.util.PropertyLoader;
import com.angloinfo.util.SikuliDriverUtility;

public class TestBase {

	WebDriver driver;
	protected Screen screen;
	protected Properties prop = new Properties();
	protected HashMap<String, String> values = new HashMap<String, String>();
	protected LogUtil log=LogUtil.getInstance();

	
	protected Directory directory= Directory.getInstance();
	protected Guides guides= Guides.getInstance();
	protected Classifieds classifieds= Classifieds.getInstance();
	protected NavBar nav= NavBar.getInstance();
	protected Footer footer= Footer.getInstance();
	
	
	SikuliDriverUtility sikuliDriver = SikuliDriverUtility.getInstance();
	PropertyLoader propLoader = PropertyLoader.getInstance();

	@BeforeTest(alwaysRun = true)
	@Parameters({ "browser.host", "browser.url", "browser.testBrowser",
			"browser.port", "ai.propertiesFile" , "ai.autoAuthFile", "ai.expectedImg"})
	public void setUP(String host, String url, String browser, String port,
			String propertiesFile, String authFile, String imgFolderLoc) throws Exception {
		
		System.setProperty(" -Dsikuli.Debug", "3");
		
		// Setup properties file
		File f = new File(propertiesFile);
		FileInputStream in = new FileInputStream(f);
		prop.load(in);
		values = propLoader.getPropFileValues(prop);
		sikuliDriver.setImageLoc(imgFolderLoc);
		
		String authUrl=sikuliDriver.getAuthUrl(url, values.get("AUTH_USER"), values.get("AUTH_PW"));
		FirefoxProfile firefoxProfile = new ProfilesIni().getProfile("default");
		File pluginAutoAuth = new File(authFile);
		firefoxProfile.addExtension(pluginAutoAuth);
		
		driver=new FirefoxDriver(firefoxProfile);
		driver.get(authUrl);
		driver.manage().window().maximize();
		
		driver.get("http://cumberbatch.me/japan/tokyo");
		
		Thread.sleep(5000);
		
		screen= new Screen();
		log.log("Screen started");
		
	

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		Reporter.log("Stopped browser");
	}

}
