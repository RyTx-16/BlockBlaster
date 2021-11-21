package BlockBlaster;

import java.awt.*;

/*
    Abstract class to define different shapes for the program, these include:
        • Ball
        • Paddle
        • Blocks
 */

public abstract class Shape {
    int height;
    int width;
    int[][] map;
    Color[] colour;
    Color color;
    int posX;
    int posY;
}
