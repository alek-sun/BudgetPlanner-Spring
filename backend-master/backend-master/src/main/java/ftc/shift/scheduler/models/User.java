package ftc.shift.scheduler.models;


import java.io.Serializable;

public class User implements Serializable {

    private String name;

    public User(String name) {
        this.name = name;
    }
}