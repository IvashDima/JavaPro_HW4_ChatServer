package academy.prog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import academy.prog.models.MessageList;
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String fromStr = req.getParameter("from");
			int from = 0;
			try {
				from = Integer.parseInt(fromStr);
				if (from < 0) from = 0;
			} catch (Exception ex) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().write("Invalid 'from' parameter");
				return;
			}
			resp.setContentType("application/json");
//			List<Message> messages = msgList.getMessages(from);
//			List<User> activeUsers = usrList.getActiveUsers(StatusType.active);
//
//			JsonObject jsonResponse = new JsonObject();
//			if (!messages.isEmpty()) {
//				JsonArray jsonMessages = gson.toJsonTree(messages).getAsJsonArray();
//				jsonResponse.add("messages", jsonMessages);
//				System.out.println(jsonMessages);
//			}
//			if (!activeUsers.isEmpty()) {
//				JsonArray jsonUsers = gson.toJsonTree(activeUsers).getAsJsonArray();
//				jsonResponse.add("users", jsonUsers);
//				System.out.println(jsonUsers);
//			}

//			String jsonResponse = "{}";
			String jsonMessages = msgList.toJSON(from);
			if (!Objects.isNull(jsonMessages) && !jsonMessages.isEmpty()) {
				try (PrintWriter out = resp.getWriter()) {
					out.write(jsonMessages);
				}
			}

		} catch (Exception ex) {
				ex.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				resp.getWriter().write("Internal Server Error: " + ex.getMessage());
			}
	}
}
