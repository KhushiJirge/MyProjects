/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Association;

/**
 *
 * @author siddh
 */
public class Person {

public void eat (Fruit fr){
if (fr instanceof Apple){
System.out.println ("I am eating an apple");
}

else if(fr instanceof Mango) {
System.out.println ("I am eating mango");
}

}

}
