package academy.prog.jsons;

import academy.prog.models.Message;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, int fromIndex) {
        for (int i = fromIndex; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }
}
