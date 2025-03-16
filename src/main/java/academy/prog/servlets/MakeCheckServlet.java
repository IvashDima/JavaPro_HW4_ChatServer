package academy.prog.servlets;

import academy.prog.enums.DefaultPassword;
import academy.prog.enums.StatusType;
import academy.prog.models.User;
import academy.prog.models.UserList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MakeCheckServlet extends HttpServlet {
    private UserList usrList = UserList.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req); // json
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User usr = User.fromJSON(bufStr);
        if(usr != null){
            if (!Objects.equals(DefaultPassword.fromString(usr.getPassword()), null)) {
                usr.setStatus(StatusType.active);
                usrList.add(usr);
                System.out.println("User '" + usr.getLogin() + "' set to active status.");
            }else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
        }else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
    }
    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException { // Apache commons-io
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;
        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toByteArray();
    }
}