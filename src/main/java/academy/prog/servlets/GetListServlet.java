package academy.prog.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import academy.prog.enums.StatusType;
import academy.prog.models.MessageList;
import academy.prog.models.UserList;
import jakarta.servlet.http.*;

/*
	0 - m /get?from=0
	1 - m
	2 - m
	....
	100 - m / from=101
	....
 */
public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();
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
		String json = msgList.toJSON(from);
		json += usrList.toJSON(StatusType.active);

		String login = req.getParameter("login");
//		for (int i = 0; i < usrList.get; i++){
//			if (sourceList.get(i).getStatus() == status){
//				list.add(sourceList.get(i));
//			}
//		}



		if (json != null) {
			OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);

//			PrintWriter pw = resp.getWriter();
//			pw.print(json);
		}
	}
}
