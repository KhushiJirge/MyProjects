/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package MyTraining;

import Containment.*;
import Inheritence.*;
import Calculator.*;
import Association.*;
import Algorithms.*;
import java.util.ArrayList;
import java.util.Arrays;  
 




public class MainProgram {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

      MainProgram mp = new MainProgram();
      //mp.TestMyKnowledgeOnContainment();
      mp.TestMyKnowledgeOnInheritence ();
      //mp.TestMyKnowledgeOnTesting ();
      //mp.TestMyKnowledgeOnAssoc ();
      //mp.TestMyKnowledgeonAlgorithm ();


      String s1 = new String("c") , s2 = new String("c");
      String s3 = "b";
      
      System.out.println (s1.compareTo(s2));
      System.out.println (s1.compareTo(s3));
      System.out.println (s2.compareTo(s1));
      System.out.println (s2.compareTo(s3));
      




      if(s1.compareTo(s2) > s2.compareTo(s1))
          System.out.println ("equal");
      else
          System.out.println ("NOTequal");
      
      
      // TODO code application logic here

    }


void TestMyKnowledgeOnContainment()
{
        Car carrr = new  Car();
        int horsepower = carrr.getPower();
        System.out.println (horsepower);


        Driver dr = new Driver ();
        dr.setName ("Khushi");
        carrr.setDriver (dr);

        System.out.println (carrr.getDrName());
}

void TestMyKnowledgeOnInheritence() {

Animal a = new Husky ();

/*Animal pets = null ;

pets = new Dog();
pets.speak ();

pets = new Cat ();
pets.speak ();

pets = new Husky();
pets.speak ();

pets = new Samoyed();
pets.speak ();

Dog doggy = new Dog ();
doggy.eat (true);
doggy.eat (true, 8);

Living lv = new Dog ();
lv.breathing ();
*/

}

void TestMyKnowledgeOnTesting (){
Calc c = new Calc();
//System.out.println (c.sum (5,8));
//System.out.println (c.subtract (5,8));


ArrayList<Integer> arl = new ArrayList<Integer>();
arl.add(12);                   
arl.add(5);               
arl.add(24); 
arl.add(1);    
arl.add(19);               
arl.add(300);               
arl.add(1);               
arl.add(-8);               
         
 
   
//c.sort (arl);


}

void TestMyKnowledgeOnAssoc (){

Fruit f = null;
f = new Mango ();

Person k = new Person ();
k.eat (f);


}

void TestMyKnowledgeonAlgorithm (){
    
    
}



}
