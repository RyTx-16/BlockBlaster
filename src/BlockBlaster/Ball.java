package BlockBlaster;

import java.awt.*;
/*
    Class that creates a circle by extending the 'Shape' abstract class.
 */
public class Ball extends Shape {
    public Ball(){
        this.height = 15;
        this.width = 15;
        this.color = Color.white;
        this.posX = 300;
        this.posY = 210;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(posX, posY, width, height);
    }
}
