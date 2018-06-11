package boraddemo.action;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.BoardDAO;
import notice.BoardDTO;

public class WriteAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp){
		MultipartRequest multi = null;
		String saveDirectory = "c:\\temp";
		InetAddress inet=null;
		
		File file = new File(saveDirectory);
		
		if(!file.isDirectory()) {
			file.mkdirs();
		}
		
		int maxPostSize = 1*1024*1024*1024;
		String encoding ="UTF-8";
		// MutipartRequest (HttPServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
		// request =servlet, saveDirectory =File, encoding= String("UTF-8"), FileRenamePolicy =DefaultFileRenamePolicy; 
		
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			inet = Inet4Address.getLocalHost();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//multi.getFilesystemName("upload") =읽어올 파일 인풋 네임
		String str = multi.getFilesystemName("upload");
		
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
		
		dto.setWriter(multi.getParameter("writer"));
		dto.setEmail(multi.getParameter("email"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setIp(inet.getHostAddress());
		dto.setUpload(str);
		
		if(!(multi.getParameter("re_level").equals("null"))) {
			HashMap<String, Integer> map = new HashMap<>();
			map.put("ref", Integer.parseInt(multi.getParameter("ref")));
			map.put("re_step", Integer.parseInt(multi.getParameter("re_step")));
			
			dao.updateMethod(map);
			dto.setRef(Integer.parseInt(multi.getParameter("ref")));
			dto.setRe_step(Integer.parseInt(multi.getParameter("re_step"))+1);
			System.out.println(Integer.parseInt(multi.getParameter("re_level")));
			dto.setRe_level(Integer.parseInt(multi.getParameter("re_level"))+1);
			System.out.println(dto.getRe_level());
		}
		
		
		dao.insertMethod(dto);	
		
	}
	
}
