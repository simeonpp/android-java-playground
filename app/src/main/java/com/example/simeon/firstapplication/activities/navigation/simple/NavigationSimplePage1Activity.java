package com.example.simeon.firstapplication.activities.navigation.simple;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.simeon.firstapplication.R;

public class NavigationSimplePage1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_simple_page1);

        Button button = (Button) this.findViewById(R.id.first_screen_btn);
        button.setOnClickListener(view -> {
            String text = ((EditText) this.findViewById(R.id.et_first_screen_text)).getText().toString();

            Intent intent = new Intent(this, NavigationSimplePage2Activity.class);
            intent.putExtra("text", text); // Send information to the next activity
            this.startActivity(intent);
        });
    }

}
