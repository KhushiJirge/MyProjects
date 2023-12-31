/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author siddh
 */
public class AlphabetSort {
    
    NumericSort ns = new NumericSort();

       
    public  ArrayList<String>  alphabetSort (ArrayList<String> wordList ){ 
            
            
            String arrWord;
            char ch ;
            ArrayList<Integer> arl = new ArrayList<Integer>();
            HashMap<Integer, String> alphabeticalOrder = new HashMap<Integer, String>();
            ArrayList<String> sortedWords = new ArrayList<String>();

             
            for (int i = 0; i < wordList.size(); i++){
                arrWord = wordList.get(i);
                ch = arrWord.charAt(0);
                int castAscii = (int) ch;
                if (castAscii < 97){
                    castAscii = castAscii + 32;
                }

                arl.add(castAscii);
                alphabeticalOrder.put(castAscii, arrWord);
                //System.out.println(castAscii);
            }

            ns.sort (arl);


            for (int i = 0; i < arl.size(); i++){
                sortedWords.add(alphabeticalOrder.get(arl.get(i)));
                
            }
            return sortedWords;
 
    }
    
    public ArrayList alphabeticalSort (ArrayList<String> wordList ){    
        String minimum = wordList.get(0);
        String num = " " ;
        int minInx= 0;

        for (int index = 0; index < wordList.size(); index++){
            minimum = wordList.get(index);

            for (int i = index; i < wordList.size(); i++){
                
                if (minimum.compareTo(wordList.get(i)) > 0) {
                    minimum = wordList.get(i);
                    minInx = i;
                    num = wordList.get(index);
                }      
            }           
            wordList.set (minInx, num);

            wordList.set (index, minimum);

        }
        System.out.println (wordList);
        
        return wordList;

    } 

}
