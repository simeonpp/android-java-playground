package com.example.simeon.firstapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.activities.fragments.StudentDetailsFragmentActivity;
import com.example.simeon.firstapplication.models.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetailsFragment extends Fragment {
    public StudentDetailsFragment() {
        // Required empty public constructor
    }

    public static StudentDetailsFragment createFragment(Student student) {
        StudentDetailsFragment fragment = new StudentDetailsFragment();

        Bundle args = new Bundle();
        args.putSerializable(StudentDetailsFragmentActivity.INTENT_STUDENT_DETAILS, student);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_details, container, false);

        /*
        Option 1
        Do not user this.getActivity() -> BAD PRACTICE
        ----------------------------------------------
        Intent intent = this.getActivity().getIntent();

        Student student = (Student) intent.getSerializableExtra(StudentDetailsFragmentActivity.INTENT_STUDENT_DETAILS);

        TextView tvStudentDetailsName = (TextView) root.findViewById(R.id.tv_student_details_name);
        tvStudentDetailsName.setText(student.getName());

        TextView tvStudentDetailsSchool = (TextView) root.findViewById(R.id.tv_student_details_school);
        tvStudentDetailsSchool.setText(student.getSchool());
        */

        Bundle arguments = this.getArguments();

        Student student = (Student) arguments.getSerializable(StudentDetailsFragmentActivity.INTENT_STUDENT_DETAILS);
        this.setStudent(student, root);

        return root;
    }

    private void setStudent(Student student, View view) {
        TextView tvStudentDetailsName = (TextView) view.findViewById(R.id.tv_student_details_name);
        tvStudentDetailsName.setText(student.getName());

        TextView tvStudentDetailsSchool = (TextView) view.findViewById(R.id.tv_student_details_school);
        tvStudentDetailsSchool.setText(student.getSchool());
    }

    /**
     * Allows to be used with fragment, not with FrameLayout
     * @param student
     */
    public void setStudent(Student student) {
        this.setStudent(student, this.getView());
    }
}
