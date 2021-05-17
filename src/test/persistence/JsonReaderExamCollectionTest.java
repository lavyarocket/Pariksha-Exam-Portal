package persistence;



import model.ExamCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderExamCollectionTest {

    @Test
    void testReaderNonExistentFile() {
        ReaderExamCollection readerExamCollection = new ReaderExamCollection("./data/noSuchFile.json");
        try {
            ExamCollection examCollection = (ExamCollection) readerExamCollection.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExamCollection() {
        ReaderExamCollection readerExamCollection = new ReaderExamCollection("./data/testWriterEmptyExamCollection" +
                ".json");
        try {
            ExamCollection examCollection = (ExamCollection) readerExamCollection.read();
            assertEquals(0,examCollection.examList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCatalogue() {
        ReaderExamCollection readerExamCollection = new ReaderExamCollection("./data/testWriterExamCollection.json");
        try {
            ExamCollection examCollection = (ExamCollection) readerExamCollection.read();
            assertEquals(2,examCollection.examList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}