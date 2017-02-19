package com.example.simeon.firstapplication.fragments.masterDetailsPhoneAndTablet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.activities.masterDetailsPhoneAndTablet.DetailsAnimalActivity;
import com.example.simeon.firstapplication.models.Animal;

import org.w3c.dom.Text;

public class ListAnimalsFragment extends Fragment {
    public ListAnimalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_animals, container, false);;

        Animal[] animals = {
            new Animal("Kumcho Vylcho", 10),
            new Animal("Kuma Lisa", 12),
            new Animal("Zaio Baio", 16),
            new Animal("Sharo the dog", 8),
            new Animal("Mechoto Pomber", 19)
        };

        ListView lvAnimals = (ListView) root.findViewById(R.id.lv_animals);

        // Create adapter
        ArrayAdapter<Animal> animalsAdapter = new ArrayAdapter<Animal>(root.getContext(), -1, animals) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_animal, parent, false);
                }

                Animal currentAnimal = this.getItem(position);

                TextView tvAnimalName = (TextView) view.findViewById(R.id.tv_animal_list_name);
                tvAnimalName.setText(currentAnimal.getName());

                TextView tvAnimalAge = (TextView) view.findViewById(R.id.tv_animal_list_age);
                String currentAnimalAge = Integer.toString(currentAnimal.getAge());
                tvAnimalAge.setText(currentAnimalAge);

                return view;
            }
        };

        lvAnimals.setAdapter(animalsAdapter);
        lvAnimals.setOnItemClickListener((parent, view, position, id) -> {
            Animal currentAnimal = animals[position];

            Intent intent = new Intent(this.getContext(), DetailsAnimalActivity.class);
            intent.putExtra(DetailsAnimalActivity.INTENT_DETAILS_ANIMAL, currentAnimal); // animal model should implement Serializable

            this.startActivity(intent);
        });

        return root;
    }
}
