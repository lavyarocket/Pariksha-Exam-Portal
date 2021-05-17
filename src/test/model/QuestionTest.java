package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    Question question1;
    Question question2;

    @BeforeEach
    public void createObject() {
        question1 = new Question("testQues1","testAns1");
        question2 = new Question("testQues2","testAns2");
    }

    @Test
    public void getQuesTest() {
        assertEquals("testQues1",question1.getQues());
        assertEquals("testQues2",question2.getQues());
    }

    @Test
    public void checkAnswerTest() {
        assertFalse(question1.checkAnswer("wrong"));
        assertFalse(question2.checkAnswer("wrong"));
        assertTrue(question1.checkAnswer("testAns1"));
        assertTrue(question2.checkAnswer("testAns2"));
    }
}
