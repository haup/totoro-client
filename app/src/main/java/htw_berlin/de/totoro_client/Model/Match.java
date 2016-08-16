package htw_berlin.de.totoro_client.Model;

import java.util.List;

/**
 * Created by tim on 26.07.16.
 */
public class Match {

    int id;
    Team team_a;
    Team team_b;
    List<Set> sets;
    int tournament_id;
    int phase;
    boolean over;
    String winner;

    public int getId() {
        return id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam_a() {
        return team_a;
    }

    public Team getTeam_b() {
        return team_b;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setTeam_a(Team team_a) {
        this.team_a = team_a;
    }

    public void setTeam_b(Team team_b) {
        this.team_b = team_b;
    }

    @Override
    public String toString() {
        System.out.println(getTeam_a().getPlayers());
        System.out.println(getTeam_b().getPlayers());
        return "Match: " + "id=" + id + ": " + getTeam_a() + " " + getTeam_b();
    }

}
