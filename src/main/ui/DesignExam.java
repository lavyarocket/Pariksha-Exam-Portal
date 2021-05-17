package ui;

import model.Exam;
import model.ExamCollection;
import model.Instructor;

//DesignExam class is a backend class used by the Instructor to design a new exam
public class DesignExam extends ExamCollection {
    private final Exam exam;
    private final Instructor instructor;

    //Parameterized Constructor
    public DesignExam(Instructor instructor) {
        exam = new Exam();
        this.instructor = instructor;
    }

    //REQUIRES: Number of Questions, Questions and Answers to be entered by the user
    //MODIFIES: super
    //EFFECTS: Creates a new exam based on the information entered by the user
    public void addQuestion(String ques, String answer) {
        exam.addQuestion(ques, answer);
    }

    //REQUIRES: Title of the exam entered by the user
    //MODIFIES: thus
    //EFFECTS: Adds the title and name to an exam
    public void addExamDetails(String title) {
        String name = instructor.getName();
        exam.addTitleAndName(title, name);
    }

    //MODIFIES: super
    //EFFECTS: Adds a created exam to the ExamList
    public void addExamInList() {
        super.addExam(exam);
    }

}
