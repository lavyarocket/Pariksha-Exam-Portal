package ui;

import model.ExamCollection;
import java.util.ArrayList;
import java.util.List;


//Account Class is the backend helper class for both the Student and Instructor Accounts
public class Account extends ExamCollection {

    //Default Constructor
    public Account() {
    }

    //EFFECTS: Returns a string containing the exams created by various Instructors
    public List<String> viewExams() {
        List<String> list = new ArrayList<>();
        if (examList.size() == 0) {
            list.add("No Exams have been created yet!");
        } else {
            for (int i = 0; i < examList.size(); i++) {
                String entry = (i + 1) + "." + examList.get(i).toString();
                list.add(entry);
            }
        }
        return list;
    }
}