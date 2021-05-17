package model;

import org.json.JSONObject;
import persistence.Writable;

//Instructor Class extends the person class and stores details about a particular Instructor
public class Student extends Person implements Writable {

    //Parameterized Constructor
    public Student(String name, String email, String id,String pass) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    //EFFECTS: Converts this class into a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("email",email);
        json.put("id",id);
        json.put("pass",pass);
        return json;
    }
}
