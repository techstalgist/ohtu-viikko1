
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void hakeePelaajan() {
        String nimi = "Kurri";
        assertEquals(nimi, stats.search(nimi).getName());
    }
    
    @Test
    public void hakuPalauttaaNullJosEiLoydy() {
        String nimi = "Laine";
        assertEquals(null, stats.search(nimi));
    }
    
    @Test
    public void hakeeTiiminPelaajat() {
        
        List<Player> pelaajat = stats.team("EDM");
        assertEquals(3, pelaajat.size());
    }
    
    @Test
    public void hakeeTopScorerit() {
        
        List<Player> scorerit = stats.topScorers(2);
        assertEquals(2, scorerit.size());
        assertEquals("Gretzky", scorerit.get(0).getName());
    }
}
