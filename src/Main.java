import score.HighScore;
import score.Score;

import java.util.ArrayList;

public class Main {
    private static void printTetronimo(boolean[][] t){
        for(int i=0; i<t.length; i++){
            for(int j=0; j<t.length; j++){
                System.out.print(t[i][j] ? 1 : 0);
            }
            System.out.print('\n');
        }
        System.out.println('\n');
    }

    public static void main(String[] args) {
        HighScore hs = new HighScore();
        hs.addScore(new Score("barack", 10));
        hs.addScore(new Score("alma", 3));
        hs.addScore(new Score("korte", 1));
        hs.addScore(new Score("szilva", 6));
        hs.addScore(new Score("meggy", 7));

        ArrayList<Score> t = new ArrayList<Score>();
        t = hs.getTopList("Point", 100);
        for(int i=0; i<t.size(); i++){
            System.out.println(t.get(i).getName() + " " + t.get(i).getPoint() + " " + t.get(i).getDate());
        }
    }
}
