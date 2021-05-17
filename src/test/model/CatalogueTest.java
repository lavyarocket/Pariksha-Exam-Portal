package model;

import model.exceptions.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

    Catalogue catalogue;
    Student student1;
    Student student2;
    Instructor instructor1;
    Instructor instructor2;

    @BeforeEach
    public void createObject() {
        catalogue = new Catalogue();
        student1 = new Student("testStudent1","student1@student.ubc.ca","46447389","student1");
        student2 = new Student("testStudent2","student2@student.ubc.ca","59452992","student2");
        instructor1 = new Instructor("testInstructor1","intructor1@student.ubc.ca","86520489","instructor1");
        instructor2 = new Instructor("testInstructor2","intructor2@student.ubc.ca","23524628","instructor2");
    }

    @Test
    public void addStudentTest() {
        try {
            catalogue.isEmptyStudent();
            fail("Exception should be thrown!");
        } catch (EmptyListException e) {
            //expected
        }
        catalogue.addStudent(student1);
        catalogue.addStudent(student2);
        try {
            catalogue.isEmptyStudent();
        } catch (EmptyListException e) {
            fail("Exception should not be thrown!");
        }
    }

    @Test
    public void addInstructorTest() {
        try {
            catalogue.isEmptyInstructor();
            fail("Exception should be thrown!");
        } catch (EmptyListException e) {
            //expected
        }
        catalogue.addInstructor(instructor1);
        catalogue.addInstructor(instructor2);
        try {
            catalogue.isEmptyInstructor();
        } catch (EmptyListException e) {
            fail("Exception should not be thrown!");
        }
    }

    @Test
    public void checkStudentTest() {
        assertFalse(catalogue.checkStudent("12345678","student1"));
        assertFalse(catalogue.checkStudent("46447389","student2"));
        catalogue.addStudent(student1);
        assertTrue(catalogue.checkStudent("46447389","student1"));
        assertEquals("testStudent1",catalogue.getStudentName());
        assertEquals("46447389",catalogue.getStudentID());
        assertEquals(student1.getName(),catalogue.getStudent().getName());
        catalogue.addStudent(student2);
        assertTrue(catalogue.checkStudent("59452992","student2"));
        assertEquals("testStudent2",catalogue.getStudentName());
        assertEquals("59452992",catalogue.getStudentID());
        assertEquals(student2.getName(),catalogue.getStudent().getName());
    }

    @Test
    public void checkInstructorTest() {
        assertFalse(catalogue.checkInstructor("12345678","instructor1"));
        assertFalse(catalogue.checkInstructor("86520489","instructor2"));
        catalogue.addInstructor(instructor1);
        assertTrue(catalogue.checkInstructor("86520489","instructor1"));
        assertEquals("testInstructor1",catalogue.getInstructorName());
        assertEquals("86520489",catalogue.getInstructorID());
        assertEquals(instructor1.getName(),catalogue.getInstructor().getName());
        catalogue.addInstructor(instructor2);
        assertTrue(catalogue.checkInstructor("23524628","instructor2"));
        assertEquals("testInstructor2",catalogue.getInstructorName());
        assertEquals("23524628",catalogue.getInstructorID());
        assertEquals(instructor2.getName(),catalogue.getInstructor().getName());
    }
}