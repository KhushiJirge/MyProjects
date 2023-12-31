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
public class NumericSort {
   public ArrayList sort (ArrayList<Integer> numberList ){    
        int minimum = numberList.get(0);
        int num = 0;
        int minInx= 0;

        for (int index = 0; index < numberList.size(); index++){
            minimum = numberList.get(index);

            for (int i = index; i < numberList.size(); i++){

                if (minimum > numberList.get(i)) {
                    minimum = numberList.get(i);
                    minInx = i;
                    num = numberList.get(index);
                }      
            }           
            numberList.set (minInx, num);

            numberList.set (index, minimum);

        }
        
        return numberList;

    } 
}
