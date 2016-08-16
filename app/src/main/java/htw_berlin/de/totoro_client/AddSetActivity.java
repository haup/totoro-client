package htw_berlin.de.totoro_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import htw_berlin.de.totoro_client.API.AuthorizedTotoroService;
import htw_berlin.de.totoro_client.API.TotoroService;
import htw_berlin.de.totoro_client.Model.Match;
import htw_berlin.de.totoro_client.Model.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSetActivity extends AppCompatActivity {

    private TextView team_a;
    private TextView team_b;
    private EditText score_a;
    private EditText score_b;
    private Button submitButton;
    private int score_a_int;
    private int score_b_int;
    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);
        team_a = (TextView) findViewById(R.id.team_a);
        team_b = (TextView) findViewById(R.id.team_b);
        score_a = (EditText) findViewById(R.id.score_a);
        score_b = (EditText) findViewById(R.id.score_b);
        submitButton = (Button) findViewById(R.id.submitButton);
        Intent intent = getIntent();
        final int match_id = Integer.parseInt(intent.getStringExtra("MATCH_ID"));
        final int tournament_id = Integer.parseInt(intent.getStringExtra("TOURNAMENT_ID"));
        final TotoroService totoroService = new AuthorizedTotoroService();
        Call<Match> matchCall = totoroService.getMatch(tournament_id, match_id);
        matchCall.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                match = response.body();
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Toast.makeText(AddSetActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score_a_int = Integer.parseInt(score_a.getText().toString());
                score_b_int = Integer.parseInt(score_b.getText().toString());
                final Set set = new Set(score_a_int, score_b_int, match_id);
                Call<Set> postSet = totoroService.postSetOfMatch(set, tournament_id, match_id);
                postSet.enqueue(new Callback<Set>() {
                    @Override
                    public void onResponse(Call<Set> call, Response<Set> response) {
                        System.out.println(response.body());
                    }

                    @Override
                    public void onFailure(Call<Set> call, Throwable t) {

                    }
                });
                final Intent intent = new Intent(AddSetActivity.this, ViewSetsActivity.class);
                intent.putExtra("TOURNAMENT_ID", "" + match_id);
                intent.putExtra("MATCH_ID", "" + tournament_id);
                startActivity(intent);
            }
        });
    }
}
