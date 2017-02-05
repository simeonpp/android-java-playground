package com.example.simeon.firstapplication.fragments;

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
import com.example.simeon.firstapplication.activities.fragments.StudentDetailsFragmentActivity;
import com.example.simeon.firstapplication.models.Student;

import org.w3c.dom.Text;

public class ListStudentsFragment extends Fragment {

    public ListStudentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_students, container, false);

        Student[] students = {
            new Student("Ivan", "Botev"),
            new Student("Gosho", "Botev"),
            new Student("Pesho", "Smirnenski"),
            new Student("Strahil", "Yovorov"),
        };

        ListView lvStudents = (ListView) root.findViewById(R.id.lv_students);

        // Create adapter
        ArrayAdapter<Student> studentsAdapter = new ArrayAdapter<Student>(root.getContext(), -1, students) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_student, parent, false);
                }

                Student currentStudent = this.getItem(position);

                TextView tvStudentName = (TextView) view.findViewById(R.id.tv_student_name);
                tvStudentName.setText(currentStudent.getName());

                TextView tvStudentSchool = (TextView) view.findViewById(R.id.tv_student_school);
                tvStudentSchool.setText(currentStudent.getSchool());

                return view;
            }
        };

        lvStudents.setAdapter(studentsAdapter);
        lvStudents.setOnItemClickListener((parent, view, position, id) -> {
            Student currentStudent = students[position];

            Intent intent = new Intent(this.getContext(), StudentDetailsFragmentActivity.class);
            intent.putExtra(StudentDetailsFragmentActivity.INTENT_STUDENT_DETAILS, currentStudent); // student model should implement Serializable

            this.startActivity(intent);
        });
        
        return root;
    }
}
