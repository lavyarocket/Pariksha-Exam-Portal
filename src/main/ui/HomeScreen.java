package ui;

import model.Catalogue;
import model.ExamCollection;
import model.Instructor;
import model.Student;
import model.exceptions.EmptyListException;
import ui.exceptions.BadIdException;
import ui.exceptions.BadInstructorEmailException;
import ui.exceptions.BadLoginException;
import ui.exceptions.BadStudentEmailException;
import persistence.ReaderCatalogue;
import persistence.ReaderExamCollection;
import persistence.WriterCatalogue;
import persistence.WriterExamCollection;
import java.io.FileNotFoundException;
import java.io.IOException;

//HomeScreen class is the backend navigation page of the program. It includes helper functions to assist the GUI Class
public class HomeScreen extends Catalogue {
    ExamCollection examCollection = new ExamCollection();
    WriterCatalogue writerCatalogue = new WriterCatalogue("./data/Catalogue.json");
    ReaderCatalogue readerCatalogue = new ReaderCatalogue("./data/Catalogue.json");
    WriterExamCollection writerExamCollection = new WriterExamCollection("./data/ExamCollection.json");
    ReaderExamCollection readerExamCollection = new ReaderExamCollection("./data/ExamCollection.json");

    //Default Constructor
    public HomeScreen() {
    }

    //EFFECTS: Checks if the List of Student signups is empty
    public boolean checkEmptyStudent() {
        try {
            super.isEmptyStudent();
            return false;
        } catch (EmptyListException e) {
            return true;
        }
    }

    //EFFECTS: Checks if the List of Instructor signups is empty
    public boolean checkEmptyInstructor() {
        try {
            super.isEmptyInstructor();
            return false;
        } catch (EmptyListException e) {
            return true;
        }
    }

    //REQUIRES: ID and password for corresponding Student Credentials throws Exception if invalid ID or Password
    //EFFECTS: Checks the login credentials for Student
    public boolean checkStudentCredentials(String id, String pass) throws Exception {
        if (!super.checkStudent(id, pass)) {
            throw new BadLoginException();
        } else {
            return true;
        }
    }

    //REQUIRES: ID and password for corresponding Instructor Credentials throws Exception if invalid ID or Password
    //EFFECTS: Checks the login credentials for Instructor
    public boolean checkInstructorCredentials(String id, String pass) throws Exception {
        if (!super.checkInstructor(id, pass)) {
            throw new BadLoginException();
        } else {
            return true;
        }
    }

    //REQUIRES: Email(with "@instructor.ubc.ca"), name, 8-Digit ID and password for Instructor Signup throws
    // Exception if email or ID don't match the specifications
    //MODIFIES: super
    //EFFECTS: Creates a new Entry for Instructor with the credentials provided by user
    public boolean signUpInstructor(String email, String name, String id, String pass) throws Exception {
        if (!email.contains("@instructor.ubc.ca")) {
            throw new BadInstructorEmailException();
        }
        if (id.length() != 8) {
            throw new BadIdException();
        }
        addInCatalogue(new Instructor(name, email, id, pass));
        return true;
    }

    //REQUIRES: Email(with "@student.ubc.ca"), name, 8-Digit ID and password for Student Signup throws
    // Exception if email or ID don't match the specifications
    //MODIFIES: super
    //EFFECTS: Creates a new Entry for Student with the credentials provided by user
    public boolean signUpStudent(String email, String name, String id, String pass) throws Exception {
        if (!email.contains("@student.ubc.ca")) {
            throw new BadStudentEmailException();
        }
        if (id.length() != 8) {
            throw new BadIdException();
        }
        addInCatalogue(new Student(name, email, id, pass));
        return true;
    }

    //MODIFIES: super
    //EFFECTS: Logs in the Student credentials into the Catalogue
    public void addInCatalogue(Student student) {
        super.addStudent(student);
    }

    //MODIFIES: super
    //EFFECTS: Logs in the Instructor credentials into the Catalogue
    public void addInCatalogue(Instructor instructor) {
        super.addInstructor(instructor);
    }

    //EFFECTS: Copies the data from the Json file and loads it into the local classes
    protected void getData() {
        try {
            loadCatalogue();
            loadExamCollection();
        } catch (IOException e) {
            System.out.println("Data Load Unsuccessful!");
        }
    }

    //MODIFIES: super
    //EFFECTS: Copies the data from the Json file and loads it into the catalogue classes
    protected void loadCatalogue() throws IOException {
        super.copyInstructorList(readerCatalogue.readInstructors());
        super.copyStudentList(readerCatalogue.readStudents());
    }

    //MODIFIES: examCollection
    //EFFECTS: Copies the data from the Json file and loads it into the catalogue classes
    protected void loadExamCollection() throws IOException {
        examCollection = readerExamCollection.read();
    }

    //MODIFIES: Json file
    //EFFECTS: Saves the data from the local classes into the Json files
    protected void saveData() {
        try {
            writerCatalogue.open();
            writerCatalogue.write();
            writerCatalogue.close();
            writerExamCollection.open();
            writerExamCollection.write(examCollection);
            writerExamCollection.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }


}
