package com.example.simeon.firstapplication.activities.navigation.simple;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;

public class NavigationSimplePage2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_simple_page2);

        // Get passed data form intent
        Intent intent = this.getIntent();
        String passedText = intent.getStringExtra("text");

        TextView tv = (TextView) this.findViewById(R.id.tv_second_screen_text);
        tv.setText(passedText);
    }

}
