package boraddemo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.BoardDAO;
import notice.BoardDTO;

public class ViewAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.readCountMethod(num);
		BoardDTO dto = dao.viewMethod(num);
		
		
		req.setAttribute("dto", dto);
		
	}
}
