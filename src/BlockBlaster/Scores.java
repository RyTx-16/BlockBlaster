package BlockBlaster;

import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
/*
    Class that creates or appends to a .txt file, after which it adds in the score
    of the player to the bottom of the list.
 */
public class Scores {
    public Scores(int data) {
        String str = Integer.toString(data);
        try {
            FileWriter fw = new FileWriter("scores.txt", true);
            fw.append(str + "\n");
            fw.close();
        }
        catch (IOException | NoSuchElementException e){
            e.getMessage();
        }
    }
}