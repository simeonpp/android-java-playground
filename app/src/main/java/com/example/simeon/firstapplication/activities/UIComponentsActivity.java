package com.example.simeon.firstapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simeon.firstapplication.R;

public class UIComponentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uicomponents);

        Button btn = (Button) this.findViewById(R.id.my_btn);
        btn.setOnClickListener(v -> {
            String inputText = ((EditText) this.findViewById(R.id.edit_message)).getText().toString();
            TextView tvResult = (TextView) this.findViewById(R.id.tv_result);
            tvResult.setText(inputText);
            Toast.makeText(this, "asd", Toast.LENGTH_LONG).show();
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String inputText = ((EditText) UIComponentsActivity.this.findViewById(R.id.edit_message)).getText().toString();
//                TextView tvResult = (TextView) UIComponentsActivity.this.findViewById(R.id.tv_result);
//                tvResult.setText(inputText);
//            }
//        });

        // Third option with class that implements OnclickListener
    }
}
