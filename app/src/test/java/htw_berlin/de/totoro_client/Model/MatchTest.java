package htw_berlin.de.totoro_client.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tim on 12.08.16.
 */
public class MatchTest {

    @Test
    public void testGetTeams() throws Exception {
        Player p1 = new Player("Tim", "tim@unkrig.ninja");
        Player p2 = new Player("Benny", "benny@unkrig.ninja");
        Player p3 = new Player("Sjoerd", "sjoerd@unkrig.ninja");
        Player p4 = new Player("Dennis", "dennis@unkrig.ninja");
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        Team t1 = new Team();
        t1.setPlayers(players);
        players.clear();
        players.add(p3);
        players.add(p4);
        Team t2 = new Team();
        t2.setPlayers(players);
        Match m = new Match();
        m.setTeam_a(t1);
        m.setTeam_b(t2);
        assertEquals(m.getTeam_a(), t1);
    }
}