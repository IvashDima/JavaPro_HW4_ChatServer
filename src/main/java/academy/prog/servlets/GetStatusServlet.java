package academy.prog.servlets;

import academy.prog.models.MessageList;
import academy.prog.models.UserList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetStatusServlet extends HttpServlet {

    private UserList usrList = UserList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) from = 0;
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setContentType("application/json");

//        String json = usrList.toJSON(from);
//        if (json != null) {
//            OutputStream os = resp.getOutputStream();
//            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
//            os.write(buf);

//			PrintWriter pw = resp.getWriter();
//			pw.print(json);
//        }
    }
}
