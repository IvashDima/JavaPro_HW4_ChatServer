package academy.prog.servlets;

import academy.prog.enums.DefaultPassword;
import academy.prog.enums.StatusType;
import academy.prog.models.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MakeCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req); // json
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User usr = User.fromJSON(bufStr);

        if (DefaultPassword.fromString(usr.getPassword()) == null)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400

        usr.setStatus(StatusType.active);
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