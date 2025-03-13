package academy.prog.models;

import academy.prog.JsonUsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class UserList {

    private static final UserList usrList = new UserList();

    private final Gson gson;
    private final List<User> list = new LinkedList<>();

    public static UserList getInstance() {
        return usrList;
    }

    private UserList() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public synchronized void add(User user) {
        list.add(user);
    }

    public synchronized String toJSON(int n) {
        if (n < 0 || n >= list.size()) return null;
        return gson.toJson(new JsonUsers(list, n));
    }
}
