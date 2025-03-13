package academy.prog.models;

import academy.prog.enums.StatusType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {

    private String login;
    private String password;

    private StatusType status;

    public User(String name, String password){
        this.login = login;
        this.password = password;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public String getPassword() {
    return password;
}

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(login)
                .append(", ").append(password)
                .append(", ").append(status)
                .append("] ")
                .toString();
    }
}

