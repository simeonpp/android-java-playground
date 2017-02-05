package com.example.simeon.firstapplication.activities.layouts;

import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;

public class LinearLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        LinearLayout linearLayout = new LinearLayout(this);

        for (int i = 0; i < 3; i ++) {
            TextView tv = new TextView(this);
            tv.setWidth(200);
            tv.setHeight(123);
            tv.setText("Created from Activity");

            linearLayout.addView(tv);
        }

        ((LinearLayout) this.findViewById(R.id.activity_linear_layout))
                .addView(linearLayout);
    }

}
