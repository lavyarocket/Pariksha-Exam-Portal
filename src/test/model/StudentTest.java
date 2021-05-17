package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    Student student1;

    @BeforeEach
    public void createObject() {
        student1 = new Student("testStudent1","student1@student.ubc.ca","46447389","student1");
    }

    @Test
    public void checkCredentials() {
        assertEquals("student1",student1.getPass());
        assertEquals("student1@student.ubc.ca",student1.getEmail());
        assertEquals("46447389",student1.getId());
        assertEquals("testStudent1",student1.getName());
    }
}
