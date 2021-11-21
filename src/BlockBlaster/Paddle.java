package BlockBlaster;

import java.awt.*;

/*
    Class that creates a rectangle by extending the 'Shape' abstract class.
 */

public class Paddle extends Shape {
    public Paddle(){
        this.height = 10;
        this.width = 100;
        this.color = Color.white;
        this.posX = 250;
        this.posY = 540;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(posX, posY, width, height);
    }
}
