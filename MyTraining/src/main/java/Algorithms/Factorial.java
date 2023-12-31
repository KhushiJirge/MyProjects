/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *
 * @author siddh
 */
public class Factorial {
    
    public int factorial (int num, int product){
        int y = num * product;
        num = num -1;       
        if (num == 1 ){
            return y;            
        }
        int x = factorial (num, y);
        return x ;
    }
/*
    public int factorial(int value){
    if (value == 1){
        return 1;
    }
    int recursiveResult = factorial(value - 1);
    System.out.println (recursiveResult);
    return recursiveResult + value;
}
*/


}
