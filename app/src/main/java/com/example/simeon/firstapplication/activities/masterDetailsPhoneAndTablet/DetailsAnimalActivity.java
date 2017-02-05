package com.example.simeon.firstapplication.activities.masterDetailsPhoneAndTablet;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.fragments.masterDetailsPhoneAndTablet.DetailsAnimalFragment;
import com.example.simeon.firstapplication.models.Animal;

public class DetailsAnimalActivity extends AppCompatActivity {
    public static final String INTENT_DETAILS_ANIMAL = "intent_details_animal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_animal);

        Intent intent = this.getIntent();

        Animal animal = (Animal) intent.getSerializableExtra(INTENT_DETAILS_ANIMAL);

        DetailsAnimalFragment fragment = DetailsAnimalFragment.createFragment(animal);

        this.getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container_fragment, fragment)
            .commit();
    }

}
