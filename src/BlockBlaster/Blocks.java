package BlockBlaster;

import java.awt.*;

/*
    Class that creates a rectangle by extending the 'Shape' abstract class.
 */

public class Blocks extends Shape {
    public Blocks(int row, int col) {
        this.map = new int[row][col];
        for(int i = 0; i < this.map.length; ++i) {
            for(int j = 0; j < this.map[0].length; ++j) {
                this.map[i][j] = 1;
            }
        }
        this.width = 400 / col;
        this.height = 150 / row;
    }

    {
        colour = new Color[]{Color.blue, Color.white, Color.red, Color.white, Color.blue};
    }

    public void draw(Graphics2D g) {
        for(int i = 0; i < this.map.length; ++i) {
            for(int j = 0; j < this.map[0].length; ++j) {
                if (this.map[i][j] > 0) {
                    g.setColor(colour[i]);
                    g.fillRect(j * this.width + 88, i * this.height + 50, this.width, this.height);
                    g.setStroke(new BasicStroke(3.5F));
                    g.setColor(Color.black); // OUTLINE OF BLOCKS
                    g.drawRect(j * this.width + 88, i * this.height + 50, this.width, this.height);
                }
            }
        }
    }

    public void setBlockValues(int value, int row, int col) {
        this.map[row][col] = value;
    }
}
