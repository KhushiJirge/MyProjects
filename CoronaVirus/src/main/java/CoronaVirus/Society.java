/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CoronaVirus;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Society extends JPanel{

        private final ArrayList<People> groupOfPeople;
        int expectedNewSizeRequest = 15;
        int doctorSizeRequest = 15;
        boolean isMoving = true;
        Color currentParticleColor;
        private Partition rectangle;
        int width = this.getWidth();

        public Society(int intialSz) {
            groupOfPeople = new ArrayList<>(intialSz);
            //currentParticleColor = Color.white;
            
            for (int index = 0; index < intialSz; index++) {
                groupOfPeople.add(new NonInfected());//creates an array of particles
            }
            rectangle = new Partition(new Dimension (20,700));
        }
        
        public void setBackgroundColor(Color backgroundColor){
            this.setBackground(backgroundColor);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (People currentParticle : groupOfPeople) {
                currentParticle.paint(g2d);
            }
            rectangle.paint(g2d);

            g2d.dispose();
        }

        //returns the particleCollection array list because particleCollection is private           
        List<People> getParticles() {
            return groupOfPeople;
        }
        Partition getPartition (){
            return rectangle;
        }

        public double getInfectedBalls (){

            double y = groupOfPeople.size() ;
            double x = 0;
            double percent = 0;

            for (People ball : groupOfPeople){
                if (ball instanceof Infected){
                  x += 1;
                 }
            }

            percent = Math.round((x/y)*100.0);  

            return percent;
        }
        public int getInfectedSize (){
            int x = 0;  

            for (People ball : groupOfPeople){
                if (ball instanceof Infected){
                  x += 1;
                 }
            }
            return x;
        }
        public int getDoctorSize (){
            int x = 0;  

            for (People ball : groupOfPeople){
                if (ball instanceof Doctor){
                  x += 1;
                 }
            }
            return x;
        }
}
