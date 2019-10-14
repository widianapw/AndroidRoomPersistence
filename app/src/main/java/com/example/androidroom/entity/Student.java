package com.example.androidroom.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int studentId;

    @NonNull
    @ColumnInfo(name = "student_name")
    private String studentName;

    @NonNull
    @ColumnInfo(name = "student_nim")
    private String studentNim;

    @NonNull
    @ColumnInfo(name = "student_phone")
    private int studentPhone;


    public Student(@NonNull String studentName, @NonNull String studentNim, int studentPhone) {
        this.studentName = studentName;
        this.studentNim = studentNim;
        this.studentPhone = studentPhone;

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @NonNull
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(@NonNull String studentName) {
        this.studentName = studentName;
    }

    @NonNull
    public String getStudentNim() {
        return studentNim;
    }

    public void setStudentNim(@NonNull String studentNim) {
        this.studentNim = studentNim;
    }

    public int getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(int studentPhone) {
        this.studentPhone = studentPhone;
    }

}
