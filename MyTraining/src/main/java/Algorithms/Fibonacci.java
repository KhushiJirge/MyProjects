/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;
import java.util.ArrayList;

/**
 *
 * @author siddh
 */
public class Fibonacci {
    
    public ArrayList<Integer> fibonacciNum (int num){
        
        ArrayList<Integer> arr = new ArrayList<Integer>();

        int y = 1;
        int z = 0;
        int x = 0;
        
        //System.out.println (1);
        arr.add (1);

        for (int i = 0; i < num; i++){
            z = x + y;

            //System.out.println (z);
            arr.add(z);

            x = y;
            y = z;
            

        }
        return arr;
    }
}
