package com.angloinfo.interceptor;

import java.util.List;

import org.testng.IMethodInstance;
import org.testng.ITestContext;
import org.testng.ITestNGListener;

/**
 * Interceptor class. Essential for Priority 
 * DO NOT EDIT.
 * 
 * @author MSRBagamasbad
 *
 */
public interface IMethodInterceptor extends ITestNGListener 
{
	  List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context);
}