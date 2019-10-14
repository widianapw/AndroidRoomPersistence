package com.example.androidroom.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidroom.dao.StudentDAO;
import com.example.androidroom.entity.Student;

import java.util.List;

public class StudentRepository {
    private StudentDAO studentDAO;
    private LiveData<List<Student>> mAllStudents;

   public StudentRepository(Application application) {
        StudentAppDatabase db = StudentAppDatabase.getDatabase(application);
        studentDAO = db.studentDAO();
       mAllStudents = studentDAO.getAllStudent();
    }

    public LiveData<List<Student>> getAllStudents() {
        return mAllStudents;
    }


    public void insert (Student student) {
        new insertAsyncTask(studentDAO).execute(student);
    }

    public void delete(Student student) {
        new DeleteStudentAsyncTask(studentDAO).execute(student);
    }

    public void update(Student student) {
        new UpdateStudentAsyncTask(studentDAO).execute(student);
    }

    private static class insertAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDAO mAsyncTaskDao;

        insertAsyncTask(StudentDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDAO;

        private UpdateStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDAO.update(students[0]);
            return null;
        }
    }

    private static class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDAO;

        private DeleteStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDAO.delete(students[0]);
            return null;
        }
    }
}
