package score;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoreTest {

    @Test
    void constructorTest() {
        HighScore hs = new HighScore("obj");
        assertEquals("obj", hs.fileName);
        HighScore hs2 = new HighScore();
        assertEquals("save", hs2.fileName);
    }

    @Test
    void addScore() {
        HighScore hs = new HighScore();
        Score bela = new Score("bela", 20);
        Score reka = new Score("reka", 10);
        hs.addScore(bela);
        hs.addScore(reka);
        assertEquals(hs.toplist.get(0), bela);
        assertEquals(hs.toplist.get(1), reka);
    }

    @Test
    void getTopList() throws InterruptedException {
        HighScore hs = new HighScore();
        Score bela = new Score("bela", 20);
        Thread.sleep(1100);
        Score reka = new Score("reka", 10);
        hs.addScore(bela);
        hs.addScore(reka);
        ArrayList<Score> s;
        s = hs.getTopList("Name", 2);
        assertEquals(s.get(0), bela);
        s = hs.getTopList("Point", 2);
        assertEquals(s.get(0), reka);
        s = hs.getTopList("Date", 2);
        assertEquals(s.get(0), reka);
    }

    @Test
    void save_and_load() throws IOException {
        HighScore hs = new HighScore("tmp");
        Score bela = new Score("bela", 20);
        hs.addScore(bela);
        hs.save();

        assertEquals(hs.toplist.get(0).getName(), bela.getName());
    }
}