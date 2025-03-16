package academy.prog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import academy.prog.enums.StatusType;
import academy.prog.models.Message;
import academy.prog.models.MessageList;
import academy.prog.models.User;
import academy.prog.models.UserList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
	private static final long inactiveTime = 30_000;
	private MessageList msgList = MessageList.getInstance();
//	private UserList usrList = UserList.getInstance();
//	private final Gson gson = new Gson();
//	private final Map<String, Long> lastRequestTime = new ConcurrentHashMap<>();

//	public GetListServlet() {
//		new Thread(() -> {
//			while (true) {
//				try {
//					Thread.sleep(10_000); //
//					checkInactiveUsers();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//	}
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
//			if (!Objects.isNull(jsonMessages) && !jsonMessages.isEmpty()) {
//				jsonMessages = jsonMessages;
//			}
//			String jsonUsers = usrList.toJSON(StatusType.active);
//			if (!Objects.isNull(jsonUsers) && !jsonUsers.isEmpty()) {
//				jsonResponse = jsonResponse.isEmpty() ? jsonUsers : jsonMessages + jsonUsers;
//			}
//
//			String login = req.getParameter("login");
//			if(login != null){
//				usrList.setUserActive(login);
//				lastRequestTime.put(login, System.currentTimeMillis());
//			}

			try (PrintWriter out = resp.getWriter()) {
				out.write(jsonMessages);
			}

		} catch (Exception ex) {
				ex.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				resp.getWriter().write("Internal Server Error: " + ex.getMessage());
			}
	}
//	private void checkInactiveUsers() {
//		long currentTime = System.currentTimeMillis();
//		for (Map.Entry<String, Long> entry : lastRequestTime.entrySet()) {
//			String login = entry.getKey();
//			long lastTime = entry.getValue();
//			if (currentTime - lastTime > inactiveTime) {
//				usrList.setUserInactive(login);
//				lastRequestTime.remove(login);
//				System.out.println("User " + login + " set to inactive status.");
//			}
//		}
//	}
}
