package com.angloinfo.tests;

import org.testng.annotations.Test;

import com.angloinfo.interceptor.Priority;

import junit.framework.Assert;

/**
 * All tests for Directory
 * 
 * @author Stephb
 *
 */
@Test
public class NavBarTest extends TestBase {
	
	@Priority(1)
	public void testScrolling()throws Exception{
		log.log("STARTING: AI-110: Checks if user can see the main menu even when I scrolling");
		
		Assert.assertTrue(nav.isMainNavAlwaysDisplayed(driver, screen));
		
		log.log("EXITING: AI-110: Checks if user can see the main menu even when I scrolling");
	}
	
	@Priority(2)
	public void testGuidesRedirection()throws Exception{
		log.log("STARTING:  I want to be able to view guides page");
		
		Assert.assertTrue(nav.isGuidesPageDisplayed(driver, screen));
		
		log.log("EXITING: I want to be able to view guides page");
	}

	@Priority(3)
	public void testDirectoryRedirection()throws Exception{
		log.log("STARTING: AI-81: I want to be able to view directory page");
		
		Assert.assertTrue(nav.isDirectoryDisplayed(driver, screen));
		
		log.log("EXITING: AI-81: I want to be able to view directory page");
	}
	
	@Priority(4)
	public void testWhatsOnRedirection()throws Exception{
		log.log("STARTING: I want to be able to view what's on page");
		
		Assert.assertTrue(nav.isWhatsOnDisplayed(driver, screen));
		
		log.log("EXITING:I want to be able to view what's on page");
	}
	
	@Priority(5)
	public void testDiscussionRedirection()throws Exception{
		log.log("STARTING: I want to be able to view forum page");
		
		Assert.assertTrue(nav.isDiscussionsDisplayed(driver, screen));
		
		log.log("EXITING:I want to be able to view forum page");
	}
	
	@Priority(6)
	public void testClassifiedsRedirection()throws Exception{
		log.log("STARTING:  AI-52: I want to be able to view classifieds page");
		
		Assert.assertTrue(nav.isClassifiedsPageDisplayed(driver, screen));
		
		log.log("EXITING: AI-52: I want to be able to view classifieds page");
	}

}
