package boraddemo.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.BoardDAO;
import notice.BoardDTO;
import notice.PageDTO;

public class ListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		BoardDAO dao = BoardDAO.getInstance();
		String pageNum =req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		req.setAttribute("page", pageNum);
		
		if(dao.rowTotalCount()>0) {
			PageDTO pdto = new PageDTO(currentPage,dao.rowTotalCount());
			List<BoardDTO> dto = dao.listMethod(pdto);
			req.setAttribute("pdto", pdto);
			req.setAttribute("aList", dto);
		}
		
		
		
		
	}
}
