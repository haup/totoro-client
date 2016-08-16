package htw_berlin.de.totoro_client.API;

import android.util.Base64;

import java.io.IOException;
import java.util.List;

import htw_berlin.de.totoro_client.Model.Match;
import htw_berlin.de.totoro_client.Model.Set;
import htw_berlin.de.totoro_client.Model.Tournament;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;


public class AuthorizedTotoroService implements TotoroService  {

    @Override
    public Call<Tournament> getTournament(@Path("tournament_id") int tournament_id) {
        return totoroService().getTournament(tournament_id);
    }

    @Override
    public Call<List<Tournament>> getTournaments() {
        return totoroService().getTournaments();
    }

    @Override
    public  Call<List<Match>> getMatches(
            @Path("tournament_id") int tournament_id) {
        return totoroService().getMatches(tournament_id);
    }

    @Override
    public Call<Match> getMatch(
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id) {
        return totoroService().getMatch(tournament_id, match_id);
    }

    @Override
    public Call<List<Set>> getSetsOfMatch(
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id) {
        return totoroService().getSetsOfMatch(tournament_id, match_id);
    }

    @Override
    public Call<Set> getSetOfMatch(
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id,
            @Path("set_id") int set_id) {
        return totoroService().getSetOfMatch(tournament_id, match_id, set_id);
    }

    @Override
    public Call<Set> postSetOfMatch(
            @Body Set set,
            @Path("tournament_id") int tournament_id,
            @Path("match_id") int match_id
            ) {
        return totoroService().postSetOfMatch(set, tournament_id, match_id);
    }

    private String baseUrl, user, password;

    public AuthorizedTotoroService() {

        this.baseUrl = "http://10.0.2.2:5000/api/v1/";
        this.user = "tunkrig@posteo.de";
        this.password = "test";
    }

    private OkHttpClient createAuth(String username, String password) {

        String credentials = username + ":" + password;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", basic)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        return client;
    }

    private TotoroService totoroService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(createAuth(user, password)).build();
        return retrofit.create(TotoroService.class);
    }
}