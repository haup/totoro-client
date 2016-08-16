package htw_berlin.de.totoro_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import htw_berlin.de.totoro_client.API.AuthorizedTotoroService;
import htw_berlin.de.totoro_client.API.TotoroService;
import htw_berlin.de.totoro_client.Model.Match;
import htw_berlin.de.totoro_client.Model.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSetsActivity extends AppCompatActivity {

    private View setView;
    private List<Set> sets = new ArrayList<>();
    private Match match;
    private ListView listSets;
    private List<String> setsStringArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        listSets = (ListView) findViewById(R.id.listSets);
        setView = findViewById(R.id.matchesView);
        String match_id = intent.getStringExtra("MATCH_ID");
        String tournament_id = intent.getStringExtra("TOURNAMENT_ID");
        TotoroService totoroService = new AuthorizedTotoroService();
        ArrayAdapter adapter;
        final Call<Match> matchOfTournamentCall = totoroService.getMatch(Integer.parseInt(tournament_id), Integer.parseInt(match_id));
        matchOfTournamentCall.enqueue(new Callback<Match>() {

            @Override
            public void onResponse(Call<Match> matchesOfTournamentCall, Response<Match> response) {
                match = response.body();
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Toast.makeText(ViewSetsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        final Call<List<Set>> setsOfMatchCall = totoroService.getSetsOfMatch(Integer.parseInt(tournament_id), Integer.parseInt(match_id));
        setsOfMatchCall.enqueue(new Callback<List<Set>>() {

            @Override
            public void onResponse(Call<List<Set>> matchesOfTournamentCall, Response<List<Set>> response) {
                sets = response.body();
                for (Set set : sets) {
                    setsStringArray.add(set.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Set>> call, Throwable t) {
                Toast.makeText(ViewSetsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new ArrayAdapter<String>(this, R.layout.list_sets, setsStringArray);
        listSets.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ViewSetsActivity.this, AddSetActivity.class);
                intent.putExtra("TOURNAMENT_ID", "" + match.getTournament_id());
                intent.putExtra("MATCH_ID", "" + match.getId());
                startActivity(intent);
            }
        });
    }

}
