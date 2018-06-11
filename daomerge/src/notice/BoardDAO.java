package notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class BoardDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;
	private static BoardDAO dao = new BoardDAO();

	public static BoardDAO getInstance() {
		return dao;
	}

	private Connection init() throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "a1234";

		return DriverManager.getConnection(url, user, password);
	}

	private void exit() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			conn.close();
		}
		if (rs != null) {
			conn.close();
		}
		if (pstmt != null) {
			conn.close();
		}

	}
	// default option

	public int rowTotalCount() {
		int cnt =0;
		try {
			conn=init();
			String sql ="select count(*) from board";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
					if(rs.next()) {
						cnt = rs.getInt(1);
					}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return cnt;
	}
	// add option

	public List<BoardDTO> listMethod(PageDTO pdto) {
		List<BoardDTO> dto = new ArrayList<>();
		try {
			conn = init();
			String sql = "select b.* from (select rownum rm,a.* from (select * from board order by ref desc, re_step asc) a )b where rm>=? and rm<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdto.getStartRow());
			pstmt.setInt(2, pdto.getEndRow());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO bod = new BoardDTO();

				bod.setNum(rs.getInt("num"));
				bod.setWriter(rs.getString("writer"));
				bod.setSubject(rs.getString("subject"));
				bod.setReadcount(rs.getInt("readcount"));
				bod.setRe_level(rs.getInt("re_level"));
				bod.setUpload(rs.getString("upload"));

				dto.add(bod);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void readCountMethod(int num) {
		try {
			conn = init();
			String sql = "update board set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	public BoardDTO viewMethod(int num) {
		BoardDTO dto = new BoardDTO();

		try {
			conn = init();
			String sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setWriter(rs.getString("writer"));
				dto.setNum(num);
				dto.setEmail(rs.getString("email"));
				dto.setUpload(rs.getString("upload"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

	public void insertMethod(BoardDTO dto) {
		try {
			conn = init();
			if (dto.getRe_level() == 0) {
				
				String sql = "insert into board(num,writer,email,subject,reg_date,ref,re_step,re_level,content,ip,upload) "
						+ "values(board_seq.nextval,?,?,?,sysdate,board_seq.nextval,0,0,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getEmail());
				pstmt.setString(3, dto.getSubject());
				pstmt.setString(4, dto.getContent());
				pstmt.setString(5, dto.getIp());
				pstmt.setString(6, dto.getUpload());
			}else {
				
				String sql = "insert into board(num,writer,email,subject,reg_date,ref,re_step,re_level,content,ip,upload) "
						+ "values(board_seq.nextval,?,?,?,sysdate,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getEmail());
				pstmt.setString(3, dto.getSubject());
				pstmt.setInt(4, dto.getRef());
				pstmt.setInt(5, dto.getRe_step());
				pstmt.setInt(6, dto.getRe_level());
				pstmt.setString(7, dto.getContent());
				pstmt.setString(8, dto.getIp());
				pstmt.setString(9, dto.getUpload());
			}
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateMethod(HashMap<String, Integer> map) {
		try {
			conn = init();
			String sql = "update board set re_step = re_step+1 where ref=? and re_step>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, map.get("ref"));
			pstmt.setInt(2, map.get("re_step"));
			System.out.println(map.get("re_step"));
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		}
	}

}
