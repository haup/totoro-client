package htw_berlin.de.totoro_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import htw_berlin.de.totoro_client.API.AuthorizedTotoroService;
import htw_berlin.de.totoro_client.API.TotoroService;
import htw_berlin.de.totoro_client.Model.Tournament;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTournamentsActivity extends AppCompatActivity {

    List tournamentArray = new ArrayList<String>();
    List<Tournament> tournaments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tournaments);
        TotoroService totoroService = new AuthorizedTotoroService();
        final Call<List<Tournament>> call = totoroService.getTournaments();
        call.enqueue(new Callback<List<Tournament>>() {
            @Override
            public void onResponse(Call<List<Tournament>> call, Response<List<Tournament>> response) {
                tournaments = response.body();
                for (Tournament t : tournaments) {
                    tournamentArray.add(t.toString());
                }

            }
            @Override
            public void onFailure(Call<List<Tournament>> call, Throwable t) {
                final TextView textView = (TextView) findViewById(R.id.blub);
                textView.setText("Something went wrong: " + t.getMessage());
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_tournaments, tournamentArray);

        ListView listView = (ListView) findViewById(R.id.tournament_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent;
                intent = new Intent(ListTournamentsActivity.this, TournamentActivity.class);
                intent.putExtra("ITEM_ID", "" + tournaments.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
