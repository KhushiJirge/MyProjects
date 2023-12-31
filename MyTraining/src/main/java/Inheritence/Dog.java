/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inheritence;

/**
 *
 * @author siddh
 */
public class Dog extends Animal implements Living{

public Dog (){
    System.out.println("Constructor of dog");
}
public void breathing (){
System.out.println ("The dog is breathing");

}

public void speak (){
System.out.println ("The dog is barking");
}
public void eat (boolean ans){
System.out.println (ans);
}

public void eat (boolean ans, int grade){
System.out.println (ans + " I am very hungry. On a scale of 1-10, I am " + grade);
}


}
