package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.adapter.RecyclerItemClickListener;
import com.example.androidroom.adapter.StudentListAdapter;
import com.example.androidroom.entity.Student;
import com.example.androidroom.utils.ApplicationStrings;
import com.example.androidroom.viewmodel.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private StudentViewModel mStudentViewModel;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StudentListAdapter adapter = new StudentListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mStudentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mStudentViewModel.getmAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable final List<Student> students) {
                // Update the cached copy of the words in the adapter.
                adapter.setmStudents(students);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewStudentActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editButton = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        deleteButton = v.findViewById(R.id.button_delete);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called"+adapter.getStudentAt(position).getStudentName() , Toast.LENGTH_SHORT).show();

                                mStudentViewModel.delete(adapter.getStudentAt(position));
                            }
                        });

                        editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called"+adapter.getStudentAt(position).getStudentName() , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, NewStudentActivity.class);
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_ID, adapter.getStudentAt(position).getStudentId());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_NAME, adapter.getStudentAt(position).getStudentName());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_NIM, adapter.getStudentAt(position).getStudentNim());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_PHONE,  Integer.toString(adapter.getStudentAt(position).getStudentPhone()));
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String studentName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_NAME);
            String studentNim = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_NIM);
            String studentPhone = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_PHONE);
            int phone = Integer.parseInt(studentPhone);

            Student student = new Student(studentName,studentNim,phone);
            mStudentViewModel.insert(student);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String studentName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_NAME);
            String studentNim = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_NIM);
            String studentPhone = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_STUDENT_PHONE);
            int phone = Integer.parseInt(studentPhone);

            Student student = new Student(studentName,studentNim, phone);
            student.setStudentId(id);
            mStudentViewModel.update(student);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }



}
