package com.example.androidroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidroom.dao.StudentDAO;
import com.example.androidroom.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract  class StudentAppDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();

    private static volatile StudentAppDatabase INSTANCE;

    static StudentAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentAppDatabase.class, "student_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
