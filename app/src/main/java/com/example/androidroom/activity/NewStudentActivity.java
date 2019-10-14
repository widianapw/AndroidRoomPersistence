package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidroom.R;

public class NewStudentActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_STUDENT_ID = "com.kayum.mamun.roomapp.EXTRA_ID";
    public static final String EXTRA_REPLY_STUDENT_NAME = "com.kayum.mamun.roomapp.NAME";
    public static final String EXTRA_REPLY_STUDENT_NIM = "com.kayum.mamun.roomapp.NIM";
    public static final String EXTRA_REPLY_STUDENT_PHONE = "com.kayum.mamun.roomapp.PHONE";

    private EditText mEditStudentNameView, mEditStudentNimView, mEditStudentPhoneView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        mEditStudentNameView = findViewById(R.id.edit_student_name);
        mEditStudentNimView = findViewById(R.id.edit_student_nim);
        mEditStudentPhoneView = findViewById(R.id.edit_student_phone);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_STUDENT_ID)) {
            setTitle("Edit Student");
            mEditStudentNameView.setText(intent.getStringExtra(EXTRA_REPLY_STUDENT_NAME));
            mEditStudentNimView.setText(intent.getStringExtra(EXTRA_REPLY_STUDENT_NIM));
            mEditStudentPhoneView.setText(intent.getStringExtra(EXTRA_REPLY_STUDENT_PHONE));

        } else {
            setTitle("Add Student");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditStudentNameView.getText()) || TextUtils.isEmpty(mEditStudentNimView.getText()) || TextUtils.isEmpty(mEditStudentPhoneView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String studentName = mEditStudentNameView.getText().toString();
                    String studentNim = mEditStudentNimView.getText().toString();
                    String studentPhone = mEditStudentPhoneView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_STUDENT_NAME, studentName);
                    replyIntent.putExtra(EXTRA_REPLY_STUDENT_NIM, studentNim);
                    replyIntent.putExtra(EXTRA_REPLY_STUDENT_PHONE, studentPhone);

                    int id = getIntent().getIntExtra(EXTRA_REPLY_STUDENT_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_STUDENT_ID, id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
