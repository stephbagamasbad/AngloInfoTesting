/**
 * 
 */
package com.angloinfo.tests;

import org.testng.annotations.Test;

import junit.framework.Assert;

import com.angloinfo.interceptor.Priority;
import com.angloinfo.util.StringConstants;

/**
 * @author GrizelS
 *
 */
@Test
public class RegistrationTest extends TestBase {

	@Priority(1)
	public void testRegistration() throws Exception{
		log.log("Starting Registration test");
		Thread.sleep(Long.parseLong(StringConstants.WAIT_7_SECS));
		
		Assert.assertTrue(register.isRegistrationDisplayed(driver, screen));
		
		log.log("Exiting Registration test");
		
	}

}
