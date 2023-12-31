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
import java.util.ArrayList;
import java.util.List;


public class People{
        private Color color;
        private Point location;
        private Dimension size;
        private Point speed; 



        public People(Color color) { //Constructor 1
            this(new Point(10, 10), new Dimension(35, 35));
            this.color = color;            
        }
        
        public People(Point point, Dimension dimension) { //overloaded constructor 2
            speed = point;
            size = dimension;
        }
        
        Dimension getSize() {
            return size;
        }

        void setColor(Color color) {
            this.color = color;
        }

        void setLocation(Point location) {
            this.location = location;
        }

        Color getColor() {
            return color;
        }

        Point getLocation() {
            return location;
        }

        Point getSpeed() {
            return speed;
        }

        void setSpeed(Point speed) {
            this.speed = speed;
        }

        protected void paint(Graphics2D g2d) {

            Point p = getLocation();
            if (p != null) {
                g2d.setColor(getColor());
                Dimension size = getSize();
                g2d.fillOval(p.x, p.y, size.width, size.height);
            }

        }
}
