/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import Calculator.*;

/**
 *
 * @author siddh
 */
public class MyCalJUnitTest {

    public MyCalJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        //System.out.println ("tearDown");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestCalAddition() 
    {
        Calc c = new Calc();
        int actualResult = c.sum (3,2);
        int expResult = 5;
        
        assertEquals(expResult, actualResult);

        


        //System.out.println ("hello");
    }

    @Test
    public void TestCalSubtraction (){
        Calc c = new Calc();
        int subtractionResult = c.subtract (3,2);
        int expectedResult = 1;

        assertEquals(expectedResult, subtractionResult);
    }

}

