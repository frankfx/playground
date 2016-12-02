package de.spending;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.application.spending.SpendingModel;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SpendingAppDataModelTest extends TestCase{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SpendingAppDataModelTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SpendingAppDataModelTest.class );
    }

    public void testDoubleValidator()
    {
    	SpendingModel dataModel = new SpendingModel();
    	
		try {
			Method method = dataModel.getClass().getDeclaredMethod("doubleValidator", String.class);
			method.setAccessible(true);
			
			assertTrue((Boolean)method.invoke(dataModel, "0"));
			assertTrue((Boolean)method.invoke(dataModel, "0.0001"));
			assertTrue((Boolean)method.invoke(dataModel, "0.0"));
			assertTrue((Boolean)method.invoke(dataModel, "1.001"));
			assertTrue((Boolean)method.invoke(dataModel, "1.00"));
			assertTrue((Boolean)method.invoke(dataModel, "1.0000"));
			assertTrue((Boolean)method.invoke(dataModel, "112131"));
			assertTrue((Boolean)method.invoke(dataModel, "1123.1"));
			assertTrue((Boolean)method.invoke(dataModel, "991.3000123100"));
			assertFalse((Boolean)method.invoke(dataModel, ""));
			assertFalse((Boolean)method.invoke(dataModel, "00"));
			assertFalse((Boolean)method.invoke(dataModel, "asd"));
			assertFalse((Boolean)method.invoke(dataModel, "1.1.1"));
			assertFalse((Boolean)method.invoke(dataModel, "00.1"));	
			assertFalse((Boolean)method.invoke(dataModel, ".1"));
			assertFalse((Boolean)method.invoke(dataModel, "0."));	
			assertFalse((Boolean)method.invoke(dataModel, "01"));	
		} catch (NoSuchMethodException e) {
			fail();
		} catch (SecurityException e){
			fail();
		} catch (IllegalAccessException e){
			fail();
		} catch (IllegalArgumentException e){
			fail();
		} catch (InvocationTargetException e){
			fail();
		}
    }
}
