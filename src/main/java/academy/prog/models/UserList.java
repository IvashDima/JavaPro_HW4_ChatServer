package academy.prog.models;

import academy.prog.JsonUsers;
import academy.prog.enums.StatusType;
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
//    public synchronized List<User> getUserList() {
//        return list;
//    }

    public synchronized String toJSON(StatusType status) {
        if (list.size() == 0) return null;
        return gson.toJson(new JsonUsers(list, status));
    }
    public synchronized List<User> getActiveUsers(StatusType status) {
        return list.stream()
                .filter(user -> user.getStatus() == status)
                .toList();
    }
    public synchronized void setUserActive(String login) {
        for (User user : list) {
            if (user.getLogin().equals(login)) {
                user.setStatus(StatusType.active);
                break;
            }
        }
    }
    public synchronized void setUserInactive(String login) {
        for (User user : list) {
            if (user.getLogin().equals(login)) {
                user.setStatus(StatusType.inactive);
                return;
            }
        }
    }
}
