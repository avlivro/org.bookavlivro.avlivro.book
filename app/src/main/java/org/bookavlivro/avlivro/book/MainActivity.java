package org.bookavlivro.avlivro.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

import java.util.Arrays;



//public class MainActivity extends ActionBarActivity {
public class MainActivity extends AppCompatActivity {

    //Создаем массив разделов:
    private String head_array[] = {
            "Technical details",
            "Chapter 01 — Never Visible",
            "Chapter 02 — The Awakening",
            "Chapter 03 — Spiral of Fate",
            "Chapter 04 — Dinner Accident",
            "Chapter 05 — Grave of Sorrow",
            "Chapter 06 — Flower Shock",
            "Chapter 07 — Questions for The God's Servant",
            "Chapter 08 — Is not to easy, being a God",
            "Chapter 09 — The Book of Light",
            "Chapter 10 — Youngest Son of Noah",
            "Chapter 11 — Fairytale",
            "Chapter 12 — “Zelandyne in Seventhaven”",
            "Chapter 13 — Snow White in Wonderland...",
            "Chapter 14 — ...or Alice and the Seven Dwarfs?",
            "Chapter 15 — The Waves Extinguish the Fire",
            "Chapter 16 — Lunatic Scheme",
            "Chapter 17 — Delia is Watching",
            "Chapter 18 — Information",
            "Chapter 19 — Omen of The 4th",
            "Chapter 20 — A Dissonance Split",
            "Chapter 21 — Doppelganger",
            "Chapter 22 — More Than a Party",
            "Chapter 23 — Cryptic Passage",
            "Chapter 24 — Two Minute Warning",
            "Chapter 25 — Baby Home",
            "Chapter 26 — Portrait of Love",
            "Chapter 27 — Bonds",
            "Chapter 28 — Memorabilia",
            "Chapter 29 — Vestiges of Terror",
            "Chapter 30 — The Loop",
            "Chapter 31 — Isolation",
            "Chapter 32 — Apparition",
            "Chapter 33 — Yonce's Baby",
            "Chapter 34 — Felicity",
            "Chapter 35 — Squirming Energy of the Phantasmagorical",
            "Chapter 36 — Lunch Boxing",
            "Chapter 37 — The Flame of Love",
            "Chapter 38 — Jo & Delia",
            "Chapter 39 — Birthday",
            "Chapter 40 — Jerome's Meanness",
            "Chapter 41 — Panic",
            "Chapter 42 — A Faint Warmth",
            "Chapter 43 — Town Zero",
            "Chapter 44 — The Clowns",
            "Chapter 45 — Breath Control",
            "Chapter 46 — Dorian Red",
            "Chapter 47 — The Unfinished “Necronomicon”",
            "Chapter 48 — Tense Atomosphere",
            "Chapter 49 — Formidable Enemy",
            "Chapter 50 — “Psychic Fair”",
            "Chapter 51 — God's got a Sick Sense of Humor",
            "Chapter 52 — Out of Control",
            "Chapter 53 — “For A Middle-aged Naive”",
            "Chapter 54 — Death's Door",
            "Chapter 55 — Unsound Methods",
            "Chapter 56 — Simulation of The World",
            "Chapter 57 — D.O.O.R.",
            "Chapter 58 — Absolute Void",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получим идентификатор ListView
        final ListView listView = (ListView) findViewById(R.id.listView);
        //устанавливаем массив в ListView
        listView.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, head_array));
        listView.setTextFilterEnabled(true);

        // Locate the EditText in listview_main.xml
      //  editsearch = (SearchView) findViewById(R.id.search);
      //  editsearch.setOnQueryTextListener();



        //Обрабатываем щелчки на элементах ListView:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);

                intent.putExtra("head", position);
                String question = listView.getItemAtPosition(position).toString();

                System.out.println(Arrays.asList(head_array).indexOf(question));
                int index = Arrays.asList(head_array).indexOf(question);
               // Toast.makeText(getApplicationContext(), "Вопрос : " + index, Toast.LENGTH_SHORT).show();
               //запускаем вторую активность
                startActivity(intent);
                }
        });


   }

}

