package com.example.androidroom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidroom.entity.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("DELETE FROM students")
    void deleteAll();

    @Query("SELECT * from students ORDER BY student_name ASC")
    LiveData<List<Student>> getAllStudent();
}
