package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterExamCollectionTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            WriterExamCollection writerExamCollection = new WriterExamCollection("./data/my\0illegal:fileName.json");
            writerExamCollection.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyExamCollection() {
        try {
            ExamCollection examCollection = new ExamCollection();
            WriterExamCollection writerExamCollection = new WriterExamCollection("./data" +
                    "/testWriterEmptyExamCollection" +
                    ".json");
            writerExamCollection.open();
            writerExamCollection.write(examCollection);
            writerExamCollection.close();
            ReaderExamCollection readerExamCollection = new ReaderExamCollection("./data" +
                    "/testWriterEmptyExamCollection" +
                    ".json");
            ExamCollection examCollection1;
            examCollection1 = (ExamCollection) readerExamCollection.read();
            assertEquals(0, examCollection1.examList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterExamCollection() {
        try {
            ExamCollection examCollection = new ExamCollection();
            Exam exam1 = new Exam();
            Exam exam2 = new Exam();
            exam1.addQuestion("testQues1","testAns1");
            exam1.addQuestion("testQues2","testAns2");
            exam1.addQuestion("testQues3","testAns3");
            exam1.addQuestion("testQues4","testAns4");
            exam1.addTitleAndName("TestTitle1","TestName1");
            exam2.addQuestion("testQues1","testAns1");
            exam2.addQuestion("testQues2","testAns2");
            exam2.addQuestion("testQues3","testAns3");
            exam2.addQuestion("testQues4","testAns4");
            exam2.addQuestion("testQues5","testAns5");
            exam2.addQuestion("testQues6","testAns6");
            exam2.addTitleAndName("TestTitle2","TestName2");
            assertEquals(0, examCollection.examList.size());
            examCollection.addExam(exam1);
            examCollection.addExam(exam2);
            assertEquals(2,examCollection.examList.size());
            WriterExamCollection writerExamCollection = new WriterExamCollection("./data" +
                    "/testWriterExamCollection" +
                    ".json");
            writerExamCollection.open();
            writerExamCollection.write(examCollection);
            writerExamCollection.close();
            ReaderExamCollection readerExamCollection = new ReaderExamCollection("./data" +
                    "/testWriterExamCollection" +
                    ".json");
            ExamCollection examCollection1 = (ExamCollection) readerExamCollection.read();
            assertEquals(2,examCollection1.examList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}