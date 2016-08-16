package htw_berlin.de.totoro_client.API;

import java.util.List;

import htw_berlin.de.totoro_client.Model.Match;
import htw_berlin.de.totoro_client.Model.Set;
import htw_berlin.de.totoro_client.Model.Tournament;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tim on 26.07.16.
 */
public interface TotoroService {


    @GET("tournaments/{tournament_id}")
    Call<Tournament> getTournament(
            @Path("tournament_id") int tournament_id
    );

    @GET("tournaments")
    Call<List<Tournament>> getTournaments();

    @GET("tournaments/{tournament_id}/matches")
    Call<List<Match>> getMatches(
            @Path("tournament_id") int tournament_id
    );

    @GET("tournaments/{tournament_id}/matches/{match_id}")
    Call<Match> getMatch (
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id
    );

    @GET("tournaments/{tournament_id}/matches/{match_id}/sets")
    Call<List<Set>> getSetsOfMatch(
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id
    );

    @GET("tournaments/{tournament_id}/matches/{match_id}/sets/{set_id}")
    Call<Set> getSetOfMatch(
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id,
            @Path("set") int set_id
    );

    @POST("tournaments/{tournament_id}/matches/{match_id}/sets")
    Call<Set> postSetOfMatch(
            @Body Set set,
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id);
}
