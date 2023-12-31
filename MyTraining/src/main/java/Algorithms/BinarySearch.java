/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;
import java.util.ArrayList;
import java.lang.Math;
/**
 *
 * @author siddh
 */
public class BinarySearch {
    public boolean binarySearch (int myNumber){
        ArrayList<Integer> myList = new ArrayList<Integer>(); 
        myList.add (2);
        myList.add (24);
        myList.add (37);
        myList.add (39);
        myList.add (76);
        myList.add (134);


        boolean x = true;
        int divide = 0;
        int first = 0;
        int last = myList.size() - 1;
        int size = myList.size();
        boolean output = false;
        
        int counter=0;
        
        while (x == true){
            counter++;
            divide = Math.round(size/2);
            
            if (myList.get(divide) == myNumber){
                output = true;
                System.out.println(output);
                break;
            }

            if (myList.get(first+divide) > myNumber){
                
                last = last - divide;
                if (size <= 2){
                    if (myList.get(first) == myNumber || myList.get(last) == myNumber){
                        output = true;
                        System.out.println(output);
                    }
                    else {
                        output = false;
                        System.out.println(output);
                        break;

                    }
                }
                size = divide;
                
            }
            else {
                
                first = first + divide;
                
                if (size <= 2){
                    if (myList.get(first) == myNumber || myList.get(last) == myNumber){
                        output = true;
                        System.out.println(output);
                    }
                    else {
                        output = false;
                        System.out.println(output);
                        break;
                    }
                }
                
                size = size - divide;
            }
                                
            if (output == true){
                x = false;
                break;
            }         
        }   
        
        //System.out.println("completed in: " + counter);
        return output;

    }
    
}
