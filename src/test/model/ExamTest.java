package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExamTest {
    Exam exam;

    @BeforeEach
    public void createObject() {
        exam = new Exam();
    }

    @Test
    public void addQuestionTest() {
        assertEquals(0,exam.questionList.size());
        exam.addQuestion("testQues1","testAns1");
        exam.addQuestion("testQues2","testAns1");
        assertEquals(2,exam.questionList.size());
    }

    @Test
    public void addNameAndTitleTest() {
        exam.addTitleAndName("testTitle","testName");
        assertEquals(" Exam: testTitle by Instructor: testName",exam.toString());
    }

}
