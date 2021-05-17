package persistence;

import model.Catalogue;
import model.exceptions.EmptyListException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderCatalogueTest {

    @Test
    void testReaderNonExistentFile() {
        ReaderCatalogue readerCatalogue = new ReaderCatalogue("./data/noSuchFile.json");
        try {
            readerCatalogue.readInstructors();
            readerCatalogue.readStudents();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCatalogue() {
        ReaderCatalogue readerCatalogue = new ReaderCatalogue("./data/testWriterEmptyCatalogue.json");
        Catalogue catalogue = new Catalogue();
        try {
            catalogue.copyStudentList(readerCatalogue.readStudents());
            catalogue.copyInstructorList(readerCatalogue.readInstructors());
            try {
                catalogue.isEmptyStudent();
                fail("Exception should be thrown!");
            } catch (EmptyListException e) {
                //expected
            }
            try {
                catalogue.isEmptyInstructor();
                fail("Exception should be thrown!");
            } catch (EmptyListException e) {
                //expected
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCatalogue() {
        ReaderCatalogue readerCatalogue = new ReaderCatalogue("./data/testWriterCatalogue.json");
        try {
            Catalogue catalogue = new Catalogue();
            catalogue.copyInstructorList(readerCatalogue.readInstructors());
            catalogue.copyStudentList(readerCatalogue.readStudents());
            try {
                catalogue.isEmptyStudent();
            } catch (EmptyListException e) {
                fail("Exception should not be thrown!");
            }
            try {
                catalogue.isEmptyInstructor();
            } catch (EmptyListException e) {
                fail("Exception should not be thrown!");
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}