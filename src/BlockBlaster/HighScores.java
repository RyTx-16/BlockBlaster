package BlockBlaster;

import java.io.File;
import java.io.IOException;
import java.util.*;
/*
    Class that scans a .txt file, for each entry in the file it adds it to an array.
    The array is then sorted in ascending order, and then again sorted by reversing thr array,
    which sorts the list into descending order.
 */
public class HighScores {
    static List list = new ArrayList();
    static int counter;
    public HighScores() {
        try {
            Scanner file = new Scanner(new File("scores.txt"));
            while (file.hasNextInt()) {
                int next = file.nextInt();
                list.add(next);
            }
            Collections.sort(list);
            Collections.reverse(list);
            int count = list.lastIndexOf(Game.getScore());
            counter = count + 1;
            file.close();
        } catch(IOException | NoSuchElementException | NullPointerException e) {
            e.getMessage();
        }
    }
}
