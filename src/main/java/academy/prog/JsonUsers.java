package academy.prog;

import academy.prog.enums.StatusType;
import academy.prog.models.User;

import java.util.ArrayList;
import java.util.List;

public class JsonUsers {
    private final List<User> list = new ArrayList<>();

    public JsonUsers(List<User> sourceList, StatusType status) {
        for (int i = 0; i < sourceList.size(); i++){
            if (sourceList.get(i).getStatus() == status){
                list.add(sourceList.get(i));
            }
        }
    }
}
