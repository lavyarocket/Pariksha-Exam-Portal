package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExamCollectionTest {
    ExamCollection examCollection;
    Exam exam1;
    Exam exam2;

    @BeforeEach
    public void createObject() {
        examCollection = new ExamCollection();
        exam1 = new Exam();
        exam2 = new Exam();
        exam1.addQuestion("testQues1","testAns1");
        exam1.addQuestion("testQues2","testAns2");
        exam1.addQuestion("testQues3","testAns3");
        exam1.addQuestion("testQues4","testAns4");
        exam2.addQuestion("testQues1","testAns1");
        exam2.addQuestion("testQues2","testAns2");
        exam2.addQuestion("testQues3","testAns3");
        exam2.addQuestion("testQues4","testAns4");
        exam2.addQuestion("testQues5","testAns5");
        exam2.addQuestion("testQues6","testAns6");
    }

    @Test
    public void addExamTest() {
        assertEquals(0, ExamCollection.examList.size());
        examCollection.addExam(exam1);
        examCollection.addExam(exam2);
        assertEquals(2,ExamCollection.examList.size());
    }

}
