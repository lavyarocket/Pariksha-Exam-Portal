package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {

    Instructor instructor1;

    @BeforeEach
    public void createObject() {
        instructor1 = new Instructor("testInstructor1","intructor1@student.ubc.ca","86520489","instructor1");
    }

    @Test
    public void checkCredentials() {
        assertEquals("instructor1",instructor1.getPass());
        assertEquals("intructor1@student.ubc.ca",instructor1.getEmail());
        assertEquals("86520489",instructor1.getId());
        assertEquals("testInstructor1",instructor1.getName());
    }
}
