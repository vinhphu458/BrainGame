package vlth.myproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vlth.myproject.Adapter.ListGameAdapter;
import vlth.myproject.Model.ItemGame;
import vlth.myproject.Util.PreferenceUtil;

public class HomeActivity extends AppCompatActivity {
    public static final String[] titles = new String[] { "Higer or Lower",
            "Mix Word", "Freaking Math", "Color or Shape" };

    ListView listView;
    List<ItemGame> rowItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        int score = settings.getInt("HL",0);
        int score2 = settings.getInt("MW",1);

        int[] best_score={score,score2,30,20};

        rowItems = new ArrayList<ItemGame>();
        for (int i = 0; i < titles.length; i++) {
            ItemGame item = new ItemGame(titles[i], best_score[i]);
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.list_game);
        ListGameAdapter adapter = new ListGameAdapter(this,
                R.layout.game_row, rowItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(HomeActivity.this,HigherOrLower.class));
                        break;
                    case 1:
                        startActivity(new Intent(HomeActivity.this,MixWord.class));
                        break;
                    case 2:
                        Toast.makeText(HomeActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(HomeActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}
