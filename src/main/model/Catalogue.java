package model;

import model.exceptions.EmptyListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Catalogue Class holds all the login details for Instructor and Student
public class Catalogue implements Writable {
    //Static Object of Instructor to be used by subclasses
    protected static Instructor instructor = null;
    //Static Object of Student to be used by subclasses
    protected static Student student = null;
    //Static ArrayList stores login details for students
    private static List<Student> studentList = new ArrayList<>();
    //Static ArrayList stores login details for instructors
    private static List<Instructor> instructorList = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS: Logs a new Signup entry into the Student List
    public void addStudent(Student student) {
        studentList.add(student);
    }

    //MODIFIES: this
    //EFFECTS: Logs a new Signup entry into the Instructor List
    public void addInstructor(Instructor instructor) {
        instructorList.add(instructor);
    }

    //MODIFIES: this
    //EFFECTS: Checks if the login credentials match in the list and copies the corresponding object for the
    // Instructor Class
    protected boolean checkInstructor(String id, String pass) {
        for (Instructor instructor : instructorList) {
            if (instructor.getId().equals(id) && instructor.getPass().equals(pass)) {
                Catalogue.instructor = instructor;
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Checks if the login credentials match in the list and copies the corresponding object for the Student
    // Class
    protected boolean checkStudent(String id, String pass) {
        for (Student student : studentList) {
            if (student.getId().equals(id) && student.getPass().equals(pass)) {
                Catalogue.student = student;
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Checks if the Instructor Signup list is empty
    public void isEmptyInstructor() throws EmptyListException {
        if (instructorList.size() == 0) {
            throw new EmptyListException();
        }
    }

    //EFFECTS: Checks if the Student Signup list is empty
    public void isEmptyStudent() throws EmptyListException {
        if (studentList.size() == 0) {
            throw new EmptyListException();
        }
    }

    //MODIFIES: this
    //EFFECTS: Copies the student list locally
    public void copyStudentList(List<Student> s) {
        studentList = s;
    }

    //MODIFIES: this
    //EFFECTS: Copies the instructor list locally
    public void copyInstructorList(List<Instructor> i) {
        instructorList = i;
    }

    public String getInstructorID() {
        return instructor.getId();
    }

    public String getStudentID() {
        return student.getId();
    }

    public String getInstructorName() {
        return instructor.getName();
    }

    public String getStudentName() {
        return student.getName();
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Student getStudent() {
        return student;
    }

    //EFFECTS: Converts this class into a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        for (Student student : studentList) {
            jsonArray1.put(student.toJson());
        }
        json.put("studentList", jsonArray1);
        for (Instructor instructor : instructorList) {
            jsonArray2.put(instructor.toJson());
        }
        json.put("instructorList", jsonArray2);
        return json;
    }
}
