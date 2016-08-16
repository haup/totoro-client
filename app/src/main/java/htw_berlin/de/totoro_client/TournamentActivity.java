package htw_berlin.de.totoro_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import htw_berlin.de.totoro_client.API.AuthorizedTotoroService;
import htw_berlin.de.totoro_client.API.TotoroService;
import htw_berlin.de.totoro_client.Model.Match;
import htw_berlin.de.totoro_client.Model.Tournament;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TournamentActivity extends AppCompatActivity {

    Tournament tournament;
    List<Match> matches  = new ArrayList<>();
    List<String> matchesStringArray = new ArrayList<>();
    ListView matchesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);
        Intent intent = getIntent();
        matchesView = (ListView) findViewById(R.id.matchesView);
        String t_id = intent.getStringExtra("ITEM_ID");
        TotoroService totoroService = new AuthorizedTotoroService();
        final Call<List<Match>> matchesOfTournamentCall = totoroService.getMatches(Integer.parseInt(t_id));
        matchesOfTournamentCall.enqueue(new Callback<List<Match>>() {

            @Override
            public void onResponse(Call<List<Match>> matchesOfTournamentCall, Response<List<Match>> response) {
                matches = response.body();
                for (Match match : matches) {
                    matchesStringArray.add(match.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                Toast.makeText(TournamentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_matches, matchesStringArray);
        matchesView.setAdapter(adapter);
        matchesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Intent intent = new Intent(TournamentActivity.this, ViewSetsActivity.class);
                intent.putExtra("TOURNAMENT_ID", "" + matches.get(position).getTournament_id());
                intent.putExtra("MATCH_ID", "" + matches.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
