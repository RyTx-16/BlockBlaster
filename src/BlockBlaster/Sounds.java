package BlockBlaster;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
/*
    Class that is used to add in the following sound clips:
        • To start the game
    	• When a player hits the ball with the paddle.
    	• When the ball hits a block.
    	All sounds sourced from: https://freesound.org/browse/
 */
public class Sounds {
    final static String path = "sounds/";
    public final static Clip start = getClip("start");
    public final static Clip scored = getClip("score");
    public final static Clip hit = getClip("hit");

    private static Clip getClip(String filename) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(path
                    + filename + ".wav"));
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static void play(Clip clip) {
        clip.setFramePosition(0);
        clip.start();
    }

    public static void sta(){
        play(start);
    }

    public static void scr(){
        play(scored);
    }

    public static void hit(){
        play(hit);
    }

}
