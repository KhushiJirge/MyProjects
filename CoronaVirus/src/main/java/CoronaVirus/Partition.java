/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoronaVirus;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author siddh
 */
public class Partition{
        private Color color;
        private Point location;
        private Dimension size;
 /*
Partition (){
    setSize(200, 200);
}
@Override
    public void paint(Graphics g) 
    {
         
        g.drawRect(1000, 300, 200, 200);

    }
*/
public Partition(Color color) { //Constructor 1
            this( new Dimension(35, 35));
            this.color = color;            
        }
        
        public Partition( Dimension dimension) { //overloaded constructor 2
            size = dimension;
            location = new Point(500, 500);
        }
        
        Dimension getSize() {
            return size;
        }

        void setColor(Color color) {
            this.color = color;
        }

        Color getColor() {
            return color;
        }

        Point getLocation() {
            return location;
        }
        void setLocation(Point location) {
            this.location = location;
        }

      

        protected void paint(Graphics2D g2d) {

            Point p = getLocation();
            if (p != null) {
                g2d.setColor(Color.red);
                Dimension size = getSize();
                g2d.fillRect(p.x, p.y, size.width, size.height);
            }

        }
}
