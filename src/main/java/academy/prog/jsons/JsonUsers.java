package academy.prog.jsons;

import academy.prog.enums.StatusType;
import academy.prog.models.User;

import java.util.ArrayList;
import java.util.List;

public class JsonUsers {
    private final List<User> list = new ArrayList<>();

    public JsonUsers(List<User> sourceList, StatusType status) {
        for (User u : sourceList){
            if (u.getStatus() == status){
                list.add(u);
            }
        }
    }
}
