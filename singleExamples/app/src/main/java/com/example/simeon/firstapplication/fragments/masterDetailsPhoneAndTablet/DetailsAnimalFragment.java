package com.example.simeon.firstapplication.fragments.masterDetailsPhoneAndTablet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.activities.fragments.StudentDetailsFragmentActivity;
import com.example.simeon.firstapplication.activities.masterDetailsPhoneAndTablet.DetailsAnimalActivity;
import com.example.simeon.firstapplication.models.Animal;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsAnimalFragment extends Fragment {
    public DetailsAnimalFragment() {
        // Required empty public constructor
    }

    public static DetailsAnimalFragment createFragment(Animal animal) {
        DetailsAnimalFragment fragment = new DetailsAnimalFragment();

        Bundle args = new Bundle();
        args.putSerializable(DetailsAnimalActivity.INTENT_DETAILS_ANIMAL, animal);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_details_animal, container, false);

        Bundle arguments = this.getArguments();
        Animal animal = (Animal) arguments.getSerializable(DetailsAnimalActivity.INTENT_DETAILS_ANIMAL);
        this.setAnimal(animal, root);

        return root;
    }

    private void setAnimal(Animal animal, View view) {
        TextView tvAnimalName = (TextView) view.findViewById(R.id.tv_animal_details_name);
        tvAnimalName.setText(animal.getName());

        TextView tvAnimalAge = (TextView) view.findViewById(R.id.tv_animal_details_age);
        String animalAge = Integer.toString(animal.getAge());
        tvAnimalAge.setText(animalAge);
    }

    /**
     * Allows to be used with fragment, not with FrameLayout
     * @param animal
     */
    public void setAnimal(Animal animal) {
        this.setAnimal(animal, this.getView());
    }

}
