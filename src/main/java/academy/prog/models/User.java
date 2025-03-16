package academy.prog.models;

import academy.prog.enums.StatusType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {

    private String login;
    private String password;

    private StatusType status;

    public User(String login, StatusType status){
        this.login = login;
        this.status = status;
    }

    public String getPassword() {
    return password;
}

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
                .append(", ").append(status)
                .append("] ")
                .toString();
    }
}

