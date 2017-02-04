package com.example.simeon.firstapplication.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Superhero;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.zip.Inflater;

public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Superhero[] superheroes = {
            new Superhero("bat-man", "Batman", "Bruce Wayne"),
            new Superhero("bl-wi", "Black Widow", "Natasha Romanoff")
        };

        ListView lvSuperheroes = (ListView) this.findViewById(R.id.lv_superheroes);
        // Create adapter
        lvSuperheroes.setAdapter(new ArrayAdapter<Superhero>(this, -1, superheroes) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Superhero currentSuperhero = this.getItem(position);

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.item_superhero, null);

                TextView tvName = (TextView) view.findViewById(R.id.tv_superhero_name);
                tvName.setText(currentSuperhero.getName());

                TextView tvIdentity = (TextView) view.findViewById(R.id.tv_superhero_identity);
                tvIdentity.setText(currentSuperhero.getSecretIdentity());

                return view;
            }
        });

        lvSuperheroes.setOnItemClickListener((parent, view, position, id) -> {
            TextView tvName = (TextView) view.findViewById(R.id.tv_superhero_name);
//            Toast
//                .makeText(this, tvName.getText().toString(), Toast.LENGTH_LONG)
//                .show();

            SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                    .setButtonText("UNDO")
                    .setProgressBarColor(Color.WHITE)
                    .setText(tvName.getText().toString())
                    .setFrame(Style.FRAME_LOLLIPOP)
                    .setGravity(Gravity.NO_GRAVITY)
                    .setDuration(Style.DURATION_LONG)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                    .setAnimations(Style.ANIMATIONS_POP)
                    .show();
        });
    }

}
