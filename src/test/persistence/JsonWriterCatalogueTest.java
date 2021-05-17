package persistence;

import model.*;
import model.exceptions.EmptyListException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterCatalogueTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            WriterCatalogue writerCatalogue = new WriterCatalogue("./data/my\0illegal:fileName.json");
            writerCatalogue.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyCatalogue() {
        try {
            Catalogue catalogue = new Catalogue();
            WriterCatalogue writerCatalogue = new WriterCatalogue("./data/testWriterEmptyCatalogue.json");
            writerCatalogue.open();
            writerCatalogue.write();
            writerCatalogue.close();
            ReaderCatalogue readerCatalogue = new ReaderCatalogue("./data/testWriterEmptyCatalogue.json");
            Catalogue catalogue1 = new Catalogue();
            catalogue1.copyInstructorList(readerCatalogue.readInstructors());
            catalogue1.copyStudentList(readerCatalogue.readStudents());
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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCatalogue() {
        try {
            Catalogue catalogue = new Catalogue();
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
            Student student1;
            student1 = new Student("testStudent1","student1@student.ubc.ca","46447389","student1");
            Student student2;
            student2 = new Student("testStudent2","student2@student.ubc.ca","59452992","student2");
            Instructor instructor1;
            instructor1 = new Instructor("testInstructor1","intructor1@student.ubc.ca","86520489",
                    "instructor1");
            Instructor instructor2;
            instructor2 = new Instructor("testInstructor2","intructor2@student.ubc.ca","23524628",
                    "instructor2");
            catalogue.addStudent(student1);
            catalogue.addStudent(student2);
            catalogue.addInstructor(instructor1);
            catalogue.addInstructor(instructor2);
            WriterCatalogue writerCatalogue = new WriterCatalogue("./data/testWriterCatalogue.json");
            writerCatalogue.open();
            writerCatalogue.write();
            writerCatalogue.close();
            ReaderCatalogue readerCatalogue = new ReaderCatalogue("./data/testWriterCatalogue.json");
            Catalogue catalogue1 = new Catalogue();
            catalogue1.copyInstructorList(readerCatalogue.readInstructors());
            catalogue1.copyStudentList(readerCatalogue.readStudents());
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
            fail("Exception should not have been thrown");
        }
    }
}