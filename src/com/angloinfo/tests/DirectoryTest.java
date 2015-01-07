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
public class DirectoryTest extends TestBase {
	
	@Priority(1)
	public void testDirectorySelectRedirection()throws Exception{
		log.log("STARTING: AI-81: I want to be able to view directory page (Select Main Category)");
		
		Assert.assertTrue(directory.isCategoryDisplayed(driver, screen));
		
		log.log("EXITING: AI-81: I want to be able to view directory page (Select Main Category)");
	}
}
