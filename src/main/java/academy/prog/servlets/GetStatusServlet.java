package academy.prog.servlets;

import academy.prog.enums.StatusType;
import academy.prog.models.MessageList;
import academy.prog.models.UserList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class GetStatusServlet extends HttpServlet {
    //	private static final long inactiveTime = 30_000;
    private UserList usrList = UserList.getInstance();

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
        String login = req.getParameter("login");
            try {
                if(login != null){
                    usrList.setUserActive(login);
//                    lastRequestTime.put(login, System.currentTimeMillis());
                }
            } catch (Exception ex) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Invalid 'from' parameter");
                return;
            }

            resp.setContentType("application/json");

            String jsonUsers = usrList.toJSON(StatusType.active);

            try (PrintWriter out = resp.getWriter()) {
                out.write(jsonUsers);
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
