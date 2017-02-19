package com.example.simeon.firstapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.simeon.firstapplication.R;

public class SimpleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplelist);

        String[] names = {"John", "Jane", "Gosho"};

        ListView listView = (ListView) this.findViewById(R.id.lv_items);
        // Create adapter
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
    }
}
