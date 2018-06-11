package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boraddemo.action.ListAction;
import boraddemo.action.ViewAction;
import boraddemo.action.WriteAction;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getRequestURI();
		str = str.substring(str.lastIndexOf("/"));
		String path="";		
		
		if(str.equals("/view.do")) {
			
			ViewAction view = new ViewAction();
			view.execute(req, resp);
			path="/boardview/view.jsp";
			
		}else if(str.equals("/*") || str.equals("/list.do")) {
			ListAction list = new ListAction();
			list.execute(req, resp);
			path="/boardview/list.jsp";
		}else if(str.equals("/writeForm.do")) {
			path="/boardview/write.jsp";
			
		}else if(str.equals("/write.do")) {
			WriteAction write = new WriteAction();
			write.execute(req, resp);
			
			resp.sendRedirect("list.do");
		}
		
		if(path!="") {
			RequestDispatcher dis = req.getRequestDispatcher(path);
			dis.forward(req, resp);	
		}
			
		
		
		
	}
}
