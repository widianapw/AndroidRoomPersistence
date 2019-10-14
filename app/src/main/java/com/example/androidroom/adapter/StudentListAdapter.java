package com.example.androidroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.entity.Student;
import com.example.androidroom.viewmodel.StudentViewModel;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
private StudentViewModel studentViewModel;

    class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView studentNameView, studentNimView, studentPhoneView;
        private final Button editButton, deleteButton;
        private StudentViewHolder(final View itemView) {
            super(itemView);
            studentNameView = itemView.findViewById(R.id.textView_student_name);
            studentNimView = itemView.findViewById(R.id.textView_student_nim);
            studentPhoneView = itemView.findViewById(R.id.textView_student_phone);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);

        }
    }

    private final LayoutInflater mInflater;
    private List<Student> mStudents; // Cached copy of words

    public StudentListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, int position) {
        if (mStudents != null) {
            Student current = mStudents.get(position);
            holder.studentNimView.setText("NIM      : "+current.getStudentNim());
            holder.studentNameView.setText("Name   : "+current.getStudentName());
            holder.studentPhoneView.setText("Phone  : "+current.getStudentPhone());


        } else {
            // Covers the case of data not being ready yet.
            holder.studentNameView.setText("No Student");
        }

    }

    public void  setmStudents(List<Student> students){
        mStudents = students;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mStudents != null)
            return mStudents.size();
        else return 0;
    }

    public Student getStudentAt(int position) {
        return mStudents.get(position);
    }
}
