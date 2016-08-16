package htw_berlin.de.totoro_client.Model;

import java.util.List;

/**
 * Created by tim on 26.07.16.
 */
public class Tournament {

    int id;
    String name;
    String modus;
    int set_count;
    List<Team> teams;
    int parent;
    int max_phase;
    boolean over;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModus() {
        return modus;
    }

    public void setModus(String modus) {
        this.modus = modus;
    }

    public int getSet_count() {
        return set_count;
    }

    public void setSet_count(int set_count) {
        this.set_count = set_count;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getMax_phase() {
        return max_phase;
    }

    public void setMax_phase(int max_phase) {
        this.max_phase = max_phase;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    @Override
    public String toString() {
        return name;
    }
}
