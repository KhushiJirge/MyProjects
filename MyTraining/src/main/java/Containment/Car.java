/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Containment;

/**
 *
 * @author siddh
 */
public class Car {

 private Engine eng;
 Driver dr;

 public Car(){
        eng = new Engine();
        eng.setPower(150);
    }
    public int getPower() {
        return eng.getPower();
    }


public void setDriver(Driver dri){
    dr = dri;
}

 public String getDrName() {
        return dr.getName();
    }

}
