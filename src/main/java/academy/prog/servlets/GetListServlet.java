package academy.prog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import academy.prog.models.MessageList;
import jakarta.servlet.http.*;

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
