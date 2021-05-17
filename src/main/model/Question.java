package model;

import org.json.JSONObject;
import persistence.Writable;

//Question class stores the attributes for a particular question
public class Question implements Writable {
    //String which stores the question
    protected String ques;
    //String which stores the answer
    protected String answer;

    //Parameterized Constructor
    public Question(String ques, String answer) {
        this.ques = ques;
        this.answer = answer;
    }

    //EFFECTS: checks the answer given by the user
    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }

    //EFFECTS: Shows the question to the user
    public String getQues() {
        return ques;
    }

    //EFFECTS: Converts this class into a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ques", ques);
        json.put("answer", answer);
        return json;
    }
}
