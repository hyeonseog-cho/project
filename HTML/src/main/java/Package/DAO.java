package Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class DAO {
	String id="system";
	String pw="0425";
	String url="jdbc:oracle:thin:@localhost:1521/xe";
	
	Connection con; 
	PreparedStatement pstmt; 
	ResultSet rs; 		
	
	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pw);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<LibraryBean> findbook(String fristfind, String secondfind) {
		Vector<LibraryBean> v = new Vector<LibraryBean>();
		try {
			getCon();

			fristfind = "'" + fristfind + "'";
			secondfind = "'" + secondfind + "'";
			
			String sql = "select * from library where fristfind="+fristfind+" and secondfind="+secondfind+" order by canrent, bookname";
			
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) { // 저장된 데이터를 반복문을 돌려서 저장
				LibraryBean bean = new LibraryBean();
				
				// 테이블에 저장되어있는 데이터들을 벡터에 저장
				bean.setBookname(rs.getString(1));
				bean.setFristfind(rs.getString(2));
				bean.setSecondfind(rs.getString(3));
				bean.setRentalday(rs.getString(4));
				bean.setReturnday(rs.getString(5));
				bean.setCanrent(rs.getString(6));
				
				v.add(bean); // bean에 DB에서 가져온 값들을 벡터에 저장
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return v;
	}
	
//	public void refresh() {
//		try {
//			getCon();
//			
//			String sql = "update library set rentalday = sysdate, returnday = sysdate, canrent = '가능' where returnday < sysdate";
//			
//			pstmt = con.prepareStatement(sql);
//			pstmt.executeUpdate();
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	public Vector<LibraryBean> findfiverentbook(){
		Vector<LibraryBean> v = new Vector<LibraryBean>();
		try {
			getCon();
			String sql = "select bookname, returnday from(select * from library where canrent='불가능' order by returnday) where rownum <= 5";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
		
			while(rs.next()) { // 저장된 데이터를 반복문을 돌려서 저장
				LibraryBean bean = new LibraryBean();
				
				// 테이블에 저장되어있는 데이터들을 벡터에 저장
				bean.setBookname(rs.getString(1));
				bean.setReturnday(rs.getString(2));
				
				v.add(bean); // bean에 DB에서 가져온 값들을 벡터에 저장
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
		
	
	
	public Vector<LibraryBean> findrentbook() {
		Vector<LibraryBean> v = new Vector<LibraryBean>();
		try {
			getCon();
			
			String sql = "select * from library where canrent='불가능' order by returnday";
			
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
		
			while(rs.next()) { // 저장된 데이터를 반복문을 돌려서 저장
				LibraryBean bean = new LibraryBean();
				
				// 테이블에 저장되어있는 데이터들을 벡터에 저장
				bean.setBookname(rs.getString(1));
				bean.setFristfind(rs.getString(2));
				bean.setSecondfind(rs.getString(3));
				bean.setRentalday(rs.getString(4));
				bean.setReturnday(rs.getString(5));
				bean.setCanrent(rs.getString(6));
				
				v.add(bean); // bean에 DB에서 가져온 값들을 벡터에 저장
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
}
