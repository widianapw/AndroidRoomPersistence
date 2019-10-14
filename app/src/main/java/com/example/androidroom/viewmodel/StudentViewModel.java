package com.example.androidroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidroom.database.StudentRepository;
import com.example.androidroom.entity.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository mRepository;

    private LiveData<List<Student>> mAllStudent;

    public StudentViewModel (Application application) {
        super(application);
        mRepository = new StudentRepository(application);
        mAllStudent = mRepository.getAllStudents();
    }

    public LiveData<List<Student>> getmAllStudent() { return mAllStudent; }

    public void insert(Student student) { mRepository.insert(student); }
    public void update(Student student) { mRepository.update(student); }
    public void delete(Student student) { mRepository.delete(student); }
}