package BlockBlaster;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
    Class which defines the following keys and gives them an action:
    • Left-Arrow: Moves the paddle left.
    • Right-Arrow: Moves the paddle right.
    • Q: Quits the game.
 */
public class Keys implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Code designed to prevent the paddle from going out of bounds, by resetting it to an appropriate position.
        if (e.getKeyCode() == 37){ // L-Arrow.
            if (Game.paddle.posX <= 25){
                Game.paddle.posX = 0;
            } else{
                moveLeft();
            }
        }
        if (e.getKeyCode() == 39){ // R-Arrow.
            if (Game.paddle.posX >= 460){
                Game.paddle.posX = 495;
            } else{
                moveRight();
            }
        }
        if (e.getKeyCode() == 81 && !Game.active ){ //  Q.
            System.exit(0);
        }
    }
    /*
        Moves the paddle along the x-axis, 20 pixels for every press.
     */
    public void moveLeft() {
        Game.paddle.posX -= 20;
    }

    public void moveRight() {
        Game.paddle.posX += 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
