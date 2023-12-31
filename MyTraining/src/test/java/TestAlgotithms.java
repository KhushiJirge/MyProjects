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

import Algorithms.*;
import java.util.ArrayList;

/**
 *
 * @author siddh
 */
public class TestAlgotithms {
    
    public TestAlgotithms() {
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
    
     @Test
    public void TestAlgorithmFibonacci() 
    {
        Fibonacci f = new Fibonacci();
        ArrayList<Integer> actualResult = f.fibonacciNum (4);
        ArrayList<Integer> expResult = new ArrayList<Integer> ();
        expResult.add(1);
        expResult.add(1);
        expResult.add(2);
        expResult.add(3);
        expResult.add(5);
        
        assertEquals(expResult, actualResult);

    }
    
   @Test
    public void TestAlgorithmBinarySearch() {
        BinarySearch b = new BinarySearch ();
        boolean actualResult = b.binarySearch(37);
        boolean expResult = true;
        
        assertEquals(expResult, actualResult);

        
    }
    
    @Test
    public void TestAlgorithmNumericSort() {
        NumericSort n = new NumericSort();
        
        ArrayList<Integer> arl = new ArrayList<Integer>();
        arl.add(12);                   
        arl.add(5);               
        arl.add(24); 
        arl.add(1);    
        arl.add(19);
        
        ArrayList<Integer> actualResult = n.sort(arl);
        ArrayList<Integer> expResult = new ArrayList<Integer> ();
        expResult.add(1);
        expResult.add(5);
        expResult.add(12);
        expResult.add(19);
        expResult.add(24);
        
        assertEquals(expResult, actualResult);

      
    }
     
    @Test
    public void TestAlgorithmAlphabetSort() {
        
        
        ArrayList<String> words = new ArrayList<String>();
        words.add("a");                
        words.add("d"); 
        words.add("c"); 
        words.add("b");    
        words.add("a"); 
        
        AlphabetSort as1 = new AlphabetSort();
        ArrayList<String> actualResult = as1.alphabetSort(words);
        
        ArrayList<String> expResult = new ArrayList<String> ();
        expResult.add("a");
        expResult.add("a");
        expResult.add("b");
        expResult.add("c");
        expResult.add("d");
        
        assertEquals(expResult, actualResult);


    }
    
    @Test
    public void TestAlgorithmFullAlphabetSort() {
        
        
        ArrayList<String> words = new ArrayList<String>();
                       
        
        words.add("aabaa"); 
            words.add("aaaab"); 
            words.add("baaaa"); 
        words.add("aaaba"); 
    words.add("abaaa"); 
        
 
        
        AlphabetSort as1 = new AlphabetSort();
        ArrayList<String> actualResult = as1.alphabeticalSort(words);
        
        ArrayList<String> expResult = new ArrayList<String> ();
        expResult.add("aaaab"); 
        expResult.add("aaaba"); 
        expResult.add("aabaa"); 
        expResult.add("abaaa"); 
        expResult.add("baaaa");                


        assertEquals(expResult, actualResult);


    }
    @Test
    public void TestAlgorithmFullFactorial() {
        Factorial f = new Factorial ();
        int actualResult = f.factorial (10,1);
        int expResult = 3628800;
        System.out.println(actualResult);
        
        assertEquals(expResult, actualResult);

    }
}
