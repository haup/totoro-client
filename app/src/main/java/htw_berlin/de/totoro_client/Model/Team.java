package htw_berlin.de.totoro_client.Model;

import java.util.List;

/**
 * Created by tim on 26.07.16.
 */
public class Team {

    int id;
    int tournament_id;
    int points;
    String history;
    List<Player> players;
    String url;
    float buchholz1;
    float buchholz2;
    int ranking;

    public float getBuchholz1() {
        return buchholz1;
    }

    public void setBuchholz1(float buchholz1) {
        this.buchholz1 = buchholz1;
    }

    public float getBuchholz2() {
        return buchholz2;
    }

    public void setBuchholz2(float buchholz2) {
        this.buchholz2 = buchholz2;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() { return players.toString(); }
}
