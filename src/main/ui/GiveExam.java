package ui;

import model.ExamCollection;
import model.Student;
import ui.exceptions.EndOfExamException;


//GiveExam Class is the class used by a logged in Student to attempt an exam created by any Instructor
public class GiveExam extends ExamCollection {
    public int grade;
    Student student;

    //Parameterized Constructor
    public GiveExam(Student student) {
        this.student = student;
        grade = 0;
    }

    //REQUIRES: Choice entered by user
    //EFFECTS: Selection of exam by the student
    public void selectExam(int choice) {
        exam = examList.get(choice);
    }

    //REQUIRES: Counter of question
    //EFFECTS: Gets the question corresponding to the counter
    public String getQuestion(int counter) throws EndOfExamException {
        if (counter >= exam.questionList.size()) {
            throw new EndOfExamException();
        } else {
            return exam.questionList.get(counter).getQues();
        }
    }

    //MODIFIES: this
    public int getGrade() {
        return grade;
    }

    //REQUIRES: Answers to be entered by the student
    //EFFECTS: Displays questions and receives answers for the corresponding question. Displays grade at the end
    public void attempt(int counter, String answer) {
        if (exam.questionList.get(counter).checkAnswer(answer)) {
            grade++;
        }

    }
}