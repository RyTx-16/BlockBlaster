package BlockBlaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static BlockBlaster.Sounds.*;
/*
    Class which holds the main functionality for the game, this class defines:
        • Game objects and variables.
        • Game mechanics, in regards to how objects interact.
        • Creating instances to be displayed on the screen.
*/
public class Game extends JPanel implements ActionListener {
    public static boolean active = false; // bool which sets the games status.
    public static int score = 0; // int to hold the score.
    public int totalBlocks = 45; // int to get the total amount of blocks.
    private Timer time; // Timer for the game functionality.
    private int xDir = 1; // Sets the x direction for the ball.
    private int yDir = 1; // Sets the y direction for the ball.
    private int speed = 8; // Sets the game speed.
    /*
        Calls each of the classes necessary to work within this class (these only include classes that are needed
        straight away to be able to play the game).
     */
    Blocks map = new Blocks(5, 9);
    Ball ball = new Ball();
    static Paddle paddle = new Paddle();
    Mouse mouse = new Mouse();
    Keys keys = new Keys();
    /*
        Here we add the classes that allow us to get an input from our mouse and keyboard. As well as starting the
        game.
    */
    public Game(){
        this.addKeyListener(keys);
        this.addMouseListener(mouse);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.time = new Timer(this.speed, this);
        this.time.start();
    }

    public void paint(Graphics g){
        /*
            Font sourced from: https://www.1001fonts.com/arcadeclassic-font.html
            Helped inputting font to program from: https://docs.oracle.com/javase/tutorial/2d/text/fonts.html
         */
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("font/Arcade.ttf")).deriveFont(30f);
            GraphicsEnvironment addFont = GraphicsEnvironment.getLocalGraphicsEnvironment();
            addFont.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Arcade.ttf")));
        } catch (IOException | FontFormatException e) {
            e.getMessage();
        }
        /*
            Draws different strings and objects to the JFrame, as well as setting their colours, this is where
            the staring menu is created.
         */
        g.setFont(font);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.white);
        g.drawString("BlockBlaster", 190, 60);
        g.setColor(Color.blue);
        g.drawString("Left and Right Arrows to Move", 80, 200);
        g.setColor(Color.red);
        g.drawString("Click to Play", 200, 360);
        g.setColor(Color.yellow);
        g.drawString("Press Q to Quit", 195, 520);
        /*
            When the game is active the correct objects (paddle, blocks and the ball) are drawn to the JFrame,
            as well as setting the background to black and displaying a score.
         */
        if (active) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 600, 600);
            g.setColor(Color.white);
            g.drawString(""+ score, 15, 30);
            this.map.draw((Graphics2D) g);
            this.ball.draw((Graphics2D) g);
            paddle.draw((Graphics2D) g);
        }
        /*
            When the ball goes out of bounds, or if there are no more blocks remaining then the game is ended.
            During which, the score is added to a .txt file and the .txt file is then sorted into a list for displaying
            within the application.
         */
        if (this.totalBlocks <= 0 || this.ball.posY > 570){
            active = false;
            try {
                new Scores(getScore());
                new HighScores();

                g.setColor(Color.black);
                g.fillRect(0, 0, 600, 600);
                g.setColor(Color.red);
                g.drawString("Thanks for Playing", 150, 50);
                if (getScore() >= 10) {
                    g.drawString("You Scored " + score + " Points", 140, 80);
                }
                if (getScore() <= 9) {
                    g.drawString("You Scored " + score + " Points", 150, 80);
                }
                g.drawString("You are ranked " + HighScores.counter + " overall", 110, 110);
                g.setColor(Color.white);
                /*
                    Code to return the current top 5 scores from the .txt file.
                 */
                g.drawString("Top 5 Scores", 200, 240);
                g.drawString("1     " + HighScores.list.get(0), 250, 270);
                g.drawString("2     " + HighScores.list.get(1), 250, 295);
                g.drawString("3     " + HighScores.list.get(2), 250, 320);
                g.drawString("4     " + HighScores.list.get(3), 250, 345);
                g.drawString("5     " + HighScores.list.get(4), 250, 370);

                g.setColor(Color.yellow);
                g.drawString("Press Q to Quit", 185, 520);
                this.time.stop();
            }catch (IndexOutOfBoundsException e){
                e.getMessage();
            }
        }
    }
    /*
        This method controls the behaviour of interactions for the objects.
     */
    public void actionPerformed(ActionEvent e) {
        this.time.start();
        /*
            This segment controls the behaviour between the ball and the paddle, when they intersect a sound is played
            and the ball direction is reversed.
         */
        if (active) {
            if ((new Rectangle(this.ball.posX, this.ball.posY, 15, 15)).intersects
                    (new Rectangle(paddle.posX, 540, 100, 10))) {
                hit();
                this.yDir = -this.yDir;
            }
            /*
                This segment controls the behaviour between the ball and blocks.
             */
            for(int i = 0; i < this.map.map.length; ++i) {
                for(int j = 0; j < this.map.map[0].length; ++j) {
                    if (this.map.map[i][j] > 0) {
                        int blockX = j * this.map.width + 80;
                        int blockY = i * this.map.height + 50;
                        int blockWidth = this.map.width;
                        int blockHeight = this.map.height;
                        Rectangle rectangle = new Rectangle(blockX, blockY, blockWidth, blockHeight);
                        Rectangle ballRect = new Rectangle(this.ball.posX, this.ball.posY, 20, 20);
                        if (ballRect.intersects(rectangle)) { // Controls directions of ball when hitting blocks
                            this.map.setBlockValues(0, i, j); // Removes the block from the map when it is hit.
                            scr(); // Sound is played.
                            --this.totalBlocks; // Total block count is reduced by 1.
                            score += 15; // Score is increased by 15.
                            if (this.ball.posX + 19 > rectangle.x && this.xDir + 1 < rectangle.x + rectangle.width) {
                                this.yDir = -this.yDir;
                                break;
                            }
                            this.xDir = -this.xDir;
                            break;
                        }
                    }
                }
            }
            /*
                This portion of the code, controls the frame boundaries for the ball, so if the ball
                goes outside of these stated boundaries it's direction is reversed, thus keeping
                the ball in all sides of the frame, apart from the bottom.
             */
            this.ball.posX += this.xDir;
            this.ball.posY += this.yDir;

            if (this.ball.posX < 5) {
                this.xDir = -this.xDir;
            }

            if (this.ball.posX > 575) {
                this.xDir = -this.xDir;
            }

            if (this.ball.posY < 0) {
                this.yDir = -this.yDir;
            }
        }
        this.repaint();
    }
    // Method to return the score at a given point.
    public static int getScore() {
        return score;
    }
}