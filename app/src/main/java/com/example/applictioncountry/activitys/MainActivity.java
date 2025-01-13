package com.example.applictioncountry.activitys;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applictioncountry.R;
import com.example.applictioncountry.adapters.CharacterAdapter;
import com.example.applictioncountry.models.Character;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private ArrayList<Character> characters;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        characters = new ArrayList<>();

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CharacterAdapter(this, characters);
        recyclerView.setAdapter(adapter);

        // Setup SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }

        });
        loadCharacters();

    }

    private void loadCharacters() {
        characters.add(new Character("Ben Geller",
                "Ross and Carol's son",
                R.drawable.ex1));

        characters.add(new Character("Chandler Bing",
                "The sarcastic executive in statistical analysis and data reconfiguration",
                R.drawable.ex2));

        characters.add(new Character("Gavin Mitchell",
                "Rachel's colleague at Ralph Lauren who initially had a contentious relationship with her after her return from maternity leave",
                R.drawable.ex3));

        characters.add(new Character("Gunther",
                "The manager of Central Perk coffee house who had a long-running crush on Rachel",
                R.drawable.ex4));

        characters.add(new Character("Janice Hosenstein",
                "Known for her distinctive laugh and catchphrase 'OH. MY. GOD!' Chandler's on-and-off girlfriend throughout the series",
                R.drawable.ex5));

        characters.add(new Character("Joey Tribbiani",
                "An aspiring actor known for his role as Dr. Drake Ramoray on Days of Our Lives. Famous for his love of food and his catchphrase 'How you doin'?'",
                R.drawable.ex6));

        characters.add(new Character("Monica Geller",
                "A professional chef known for her obsessive-compulsive cleanliness, competitive nature, and being the glue that holds the group together",
                R.drawable.ex7));

        characters.add(new Character("Phoebe Buffay",
                "A free-spirited massage therapist and musician known for her quirky personality and unique outlook on life",
                R.drawable.ex8));

        characters.add(new Character("Rachel Green",
                "Started as a waitress at Central Perk and evolved into a successful fashion executive",
                R.drawable.ex9));

        characters.add(new Character("Ross Geller",
                "A paleontologist and professor at New York University",
                R.drawable.ex10));

        adapter.notifyDataSetChanged();
        adapter.setCharacters(characters);

    }
}