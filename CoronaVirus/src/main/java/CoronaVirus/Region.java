/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CoronaVirus;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.List;  
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;




public class Region extends JFrame{
private JButton virus;
private JButton cure;
private JButton quarantine;
private JButton partitionChange;
private JButton partitionChange2;

private JLabel count;
private Society peopleBalls;
DrawGraph graph;
private List<Integer> graphList;


   public Region (String title){
        super(title);// set Frame's title
   }

    void Initalize(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        JPanel subPanel = new JPanel();
        count = new JLabel();
 
           

        virus = new JButton ("Virus");
        quarantine = new JButton ("Quarantine On/off");
        cure = new JButton ("Vaccinated Doctor");
        partitionChange = new JButton ("Smaller partition");
        partitionChange2 = new JButton ("Larger partition");

        subPanel.add(partitionChange); 
        subPanel.add(partitionChange2); 

        subPanel.add(quarantine); 
        subPanel.add(virus); 
        subPanel.add(cure);
        subPanel.add(count); 

        int peopleSz = 15;   
        peopleBalls = new Society(peopleSz);
        
        graphList = new ArrayList<Integer>();  
        graph = new DrawGraph(graphList);
     

        this.setLayout(new BorderLayout());
        this.add(subPanel, BorderLayout.SOUTH);
        this.add(graph, BorderLayout.NORTH);

        this.add(peopleBalls);
        this.setSize(1000, 500);
        this.setVisible(true);
        peopleBalls.setBackground(new Color (0, 0, 128));
/*
        Partition rect = new Partition ();
        rect.repaint();
        this.add(rect);
*/
        virus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                peopleBalls.expectedNewSizeRequest++ ;

        }});
        cure.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                peopleBalls.doctorSizeRequest++ ;

        }});
        quarantine.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                if (peopleBalls.isMoving == true)
                    peopleBalls.isMoving = false;
                else 
                    peopleBalls.isMoving = true;
        }});

        partitionChange.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
            Partition rect = peopleBalls.getPartition ();
            Point rectLocation = rect.getLocation();
            int rectY = rectLocation.y;
            int rectX = rectLocation.x;

                 rectY = rectY + 50;
                 rect.setLocation (new Point(rectX, rectY));
        }});
partitionChange2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
            Partition rect = peopleBalls.getPartition ();
            Point rectLocation = rect.getLocation();
            int rectY = rectLocation.y;
            int rectX = rectLocation.x;

                 rectY = rectY - 50;
                 rect.setLocation (new Point(rectX, rectY));
        }});

        Timer timer = new Timer();
        
        timer.schedule(new GraphUpdate(this), 0, 3000);

        new Thread(new SimulationEngine(this, peopleBalls)).start();// NEW THREAD


    }

    public void updateCount (){
        count.setText("Percentage of People Infected = " + peopleBalls.getInfectedBalls() + " %");

    }
    public void graphTimer (){
        graphList.add(peopleBalls.getInfectedSize());
            
        graph.repaint ();

Partition rect = peopleBalls.getPartition ();
            Point rectLocation = rect.getLocation();
            int rectY = rectLocation.y;
            int rectX = rectLocation.x;

        if (peopleBalls.getInfectedSize() >= 1){
            smallerPartition();    
            peopleBalls.doctorSizeRequest ++ ;
        }
        if (peopleBalls.getInfectedSize() == 0 && peopleBalls.getDoctorSize() >= 1){
            peopleBalls.doctorSizeRequest = 0;

        }
        if (peopleBalls.getInfectedSize() == 0 && rectY < 700){
                    largerPartition();

        }
    }
    public void largerPartition(){
        Partition rect = peopleBalls.getPartition ();
            Point rectLocation = rect.getLocation();
            int rectY = rectLocation.y;
            int rectX = rectLocation.x;

                 rectY = rectY + 50;
                 if (rectY == 700){
                  rectY = rectY - 50;

                 }
                 rect.setLocation (new Point(rectX, rectY));
    }


    public void smallerPartition(){
        Partition rect = peopleBalls.getPartition ();
            Point rectLocation = rect.getLocation();
            int rectY = rectLocation.y;
            int rectX = rectLocation.x;

                 rectY = rectY - 50;
                 if (rectY == -50){
                  rectY = rectY + 50;

                 }
                 rect.setLocation (new Point(rectX, rectY));
    }

}

class SimulationEngine implements Runnable {

 
        private final Society people;
        private final Region place;
        

        public SimulationEngine(Region place, Society people) {
            this.people = people;
            this.place = place;
        }

        @Override
        public void run() {
            Partition rect = people.getPartition ();
            Point rectLocation = rect.getLocation();
            Dimension rectSize = rect.getSize();

            int rectX = rectLocation.x;
            int rectY = rectLocation.y;


            int width = people.getWidth();
            int height = people.getHeight();

            // Randomizes the starting position
            //for (People ball : people.getParticles()) {
            for(int i = 0; i < people.getParticles().size(); i++){
                People ball = people.getParticles().get(i);
                
                int x = Library.random(width);
                int y = Library.random(height);
                if (rectX - rectSize.width-rectSize.width <= x && x <= rectX +50 && rectY <= y && y <= rectY + rectSize.height){
                    x = x - rectSize.width - 50;
                    y = y + rectSize.height ;


                }
/*
                if (rectY <= y && y<= rectY + rectSize.height){
                    y = y + rectSize.height ;
                }
*/
                Dimension size = ball.getSize();

                if (x + size.width > width) {
                    x = width - size.width;
                }
                if (y + size.height > height) {
                    y = height - size.height;
                }

                ball.setLocation(new Point(x, y));
            }

            while (people.isVisible()) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        people.repaint();
                    }
                });

                //for (People ball : people.getParticles()) {
                for(int i = 0; i < people.getParticles().size(); i++){
                    People ball = people.getParticles().get(i);
                    moveLinear(ball);
                }

                try {
                    Thread.sleep(100);
                } 
                catch (InterruptedException ex) {
                }

                if (people.getParticles().size() !=  people.expectedNewSizeRequest){
                    people.doctorSizeRequest ++;
                    
                    for (int index = 0; index < people.expectedNewSizeRequest - people.getParticles().size(); index++) {
                        People newBall = new Infected();
                        people.getParticles().add(newBall);//creates new array list
                        place.updateCount();
                         int x = Library.random(width);
                        int y = Library.random(height);

                        Dimension size = newBall.getSize();

                        if (x + size.width > width) {
                            x = width - size.width;
                        }
                        if (y + size.height > height) {
                            y = height - size.height;
                        }

                        newBall.setLocation(new Point(x, y));

                    }                  
                }  
                //if (people.getParticles().size() !=  people.doctorSizeRequest){
                if (people.getParticles().size() <  people.doctorSizeRequest){
                    people.expectedNewSizeRequest ++;

                    for (int index = 0; index < people.doctorSizeRequest - people.getParticles().size(); index++) {
                        People newBall = new Doctor();

                        people.getParticles().add(newBall);//creates new array list                        
                        place.updateCount();
                        int x = Library.random(width);
                        int y = Library.random(height);

                        Dimension size = newBall.getSize();

                        if (x + size.width > width) {
                            x = width - size.width;
                        }
                        if (y + size.height > height) {
                            y = height - size.height;
                        }

                        newBall.setLocation(new Point(x, y));
                    }
                  }
                    if (people.getParticles().size() >  people.doctorSizeRequest){

                        int i = people.getParticles().size();
                        while (--i >=0 && people.getDoctorSize() != 0) {
                            People ball = people.getParticles().get(i);
                            if (ball instanceof Doctor){
                                people.getParticles().remove(people.getParticles().indexOf(ball));
                            }
                            
                        }
                    people.expectedNewSizeRequest = people.getParticles().size() ;
                    people.doctorSizeRequest = people.expectedNewSizeRequest;
                  }                    
                //}              
            }
        }

        public void moveLinear(People currentParticle) {
            
            if (currentParticle instanceof Infected && people.isMoving == false){
                return;
            }
            Partition rect = people.getPartition ();
            Point p = currentParticle.getLocation();
            Point speed = currentParticle.getSpeed();
            Dimension size = currentParticle.getSize();
            Point rectLocation = rect.getLocation();
            Dimension rectSize = rect.getSize();

            int vx = speed.x;
            int vy = speed.y;

            int rectX = rectLocation.x;
            int rectY = rectLocation.y;


            int x = p.x;
            int y = p.y;

            //The if statements make the particles bounce in opposite direction from the frame walls
            if (x + vx < 0 || 
                x + size.width + vx > people.getWidth() || 

                rectX - rectSize.width - rectSize.width <= x && x <= rectX + rectSize.width && 
                rectY <= y && y <= rectY + rectSize.height) {
                vx *= -1;
            }
            if (y + vy < 0 || 
                y + size.height + vy > people.getHeight() || 

                rectY <= y && y <= rectY + rectSize.height && 
                rectX - rectSize.width- rectSize.width <= x && x <= rectX + rectSize.width) {
                vy *= -1;
            }



/* if (x + vx < 0 || 
                x + size.width + vx > people.getWidth() || 

                470 <= x && x <= 520 && 
                100 <= y && y <= 565) {
                vx *= -1;
            }
            if (y + vy < 0 || 
                y + size.height + vy > people.getHeight() || 

                100 <= y && y <= 565 && 
                470 <= x && x <= 520) {
                vy *= -1;
            }
*/
            
            x += vx;
            y += vy;
            
            currentParticle.setSpeed(new Point(vx, vy));
            currentParticle.setLocation(new Point(x, y));   
            
            //checks if a particle is being compared with itself and then calls the collide method
            //for (People nextPerson : people.getParticles()){
            for (int i = 0; i < people.getParticles().size(); i++){
                People nextPerson = people.getParticles().get(i);
//System.out.println (nextPerson.getLocation());
//System.out.println (currentParticle.getLocation());

                if(nextPerson.getLocation().x != currentParticle.getLocation().x && 
                        nextPerson.getLocation().y != currentParticle.getLocation().y){
                    collide(nextPerson, currentParticle);
                }
            }
            
        } 
        
        public void collide (People particle1, People particle2){
            Point location1 = particle1.getLocation();
            Point location2 = particle2.getLocation();
            Point speed1 = particle1.getSpeed();
            Point speed2 = particle2.getSpeed();
            Dimension size = particle1.getSize();

            
            int x1 = location1.x;
            int y1 = location1.y;
            
            int x2 = location2.x;
            int y2 = location2.y;
            
            int velX1 = speed1.x;
            int velY1 = speed1.y;
            
            int velX2 = speed2.x;
            int velY2 = speed2.y;
            
            float distance = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            
            //size.width = 2 * radius of the balls
            if (distance <= size.width){
                velX1 = velX1 * -1;
                velX2 = velX2 * -1;
                velY1 = velY1 * -1;
                velY2 = velY2 * -1;
                
                x1 += velX1;
                y1 += velY1;

                particle1.setSpeed(new Point(velX1, velY1));
                particle1.setLocation(new Point(x1, y1)); 
                
                x2 += velX2;
                y2 += velY2;

                particle2.setSpeed(new Point(velX2, velY2));
                particle2.setLocation(new Point(x2, y2)); 
                

                if (particle1 instanceof NonInfected && particle2 instanceof Infected){
               
                    People nowInfected = new Infected();
                    
                    int nonInfectedIndex = people.getParticles().indexOf (particle1);
                    if (nonInfectedIndex >= 0 )

                    people.getParticles().set(nonInfectedIndex, nowInfected);
                    nowInfected.setSpeed(new Point(velX1, velY1));

                    nowInfected.setLocation(new Point(x1, y1));
                        place.updateCount();


                }
                if (particle1 instanceof Infected && particle2 instanceof NonInfected ){
                    People nowInfected = new Infected();

                    int nonInfectedIndex = people.getParticles().indexOf (particle2);
                    
                    if (nonInfectedIndex >= 0 )
                         people.getParticles().set(nonInfectedIndex, nowInfected); 
                     nowInfected.setSpeed(new Point(velX2, velY2));

                    nowInfected.setLocation(new Point(x2, y2));
                    place.updateCount();

                 }

                    if (particle1 instanceof Infected && particle2 instanceof Doctor){
                        People nowCured = new NonInfected ();
                        int infectedIndex = people.getParticles().indexOf (particle1);
                    
                        if (infectedIndex >= 0 )
                             people.getParticles().set(infectedIndex, nowCured); 

                         nowCured.setSpeed(new Point(velX1, velY1));

                        nowCured.setLocation(new Point(x1, y1));
                        place.updateCount();
                    }

                    if (particle1 instanceof Doctor && particle2 instanceof Infected){
                        People nowCured = new NonInfected ();
                        int infectedIndex = people.getParticles().indexOf (particle2);
                    
                        if (infectedIndex >= 0 )
                             people.getParticles().set(infectedIndex, nowCured); 

                         nowCured.setSpeed(new Point(velX2, velY2));

                        nowCured.setLocation(new Point(x2, y2));
                        place.updateCount();
                    }

            } 

          
            
        }
}

class GraphUpdate extends TimerTask {

    private final Region place;

    public GraphUpdate (Region pl){
        place = pl;
    }

    public void run() {
        
       place.graphTimer ();
    }
}