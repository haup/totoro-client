package htw_berlin.de.totoro_client.Model;

/**
 * Created by tim on 26.07.16.
 */
public class Set {

    String id;
    int score_a;
    int score_b;
    int match_id;
    String url;

    public Set(int score_a_int, int score_b_int, int match_id) {
        this.score_a = score_a_int;
        this.score_b = score_b_int;
        this.match_id = match_id;
    }

    public String getId() {
        return id;
    }

    public Integer getScore_a() {
        return score_a;
    }

    public Integer getScore_b() {
        return score_b;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id='" + id + '\'' +
                ", score_a=" + score_a +
                ", score_b=" + score_b +
                ", match_id=" + match_id +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
