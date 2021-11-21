package BlockBlaster;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static BlockBlaster.Sounds.sta;
/*
    Class that gives the mouse actions. It is used in this instance to start the game,
    whilst also playing a sound when pressed.
 */
public class Mouse implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        sta();
        Game.active = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
