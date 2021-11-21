package BlockBlaster;

import javax.swing.*;
/*
    Class that creates a JFrame to hold all of the components of the game.
    The JFrame has been created to:
        • Not be able to be resized.
        • Show up in the centre of the screen.
 */
public class Project extends JFrame {
    public static void main(String[] args){
        JFrame frame = new Project();
        frame.setSize(600, 600);
        frame.setTitle("BlockBlaster | 1605364");
        frame.setDefaultCloseOperation(Project.EXIT_ON_CLOSE);
        Game game = new Game();
        frame.add(game);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
