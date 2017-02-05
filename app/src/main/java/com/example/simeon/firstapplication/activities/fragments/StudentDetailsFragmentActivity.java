package com.example.simeon.firstapplication.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.fragments.StudentDetailsFragment;
import com.example.simeon.firstapplication.models.Student;

import java.io.Serializable;

public class StudentDetailsFragmentActivity extends AppCompatActivity {
    public static final String INTENT_STUDENT_DETAILS = "intent_student_details";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_fragment);

        Intent intend = this.getIntent();

        Student student = (Student) intend.getSerializableExtra(StudentDetailsFragmentActivity.INTENT_STUDENT_DETAILS);

        StudentDetailsFragment fragment = StudentDetailsFragment.createFragment(student);

        this.getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container_fragment, fragment)
            .commit();
    }
}
