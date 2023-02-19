<%@page import="java.beans.beancontext.BeanContext"%>
<%@page import="Package.LibraryBean"%>
<%@page import="java.util.Vector"%>
<%@page import="Package.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서 검색</title>
	<link type="text/css" rel="stylesheet" href="Mainstyle.css">
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	String fristfind = request.getParameter("fristfind");
	String secondfind = request.getParameter("secondfind");
	
	DAO d = new DAO();
	Vector<LibraryBean> v = d.findbook(fristfind, secondfind);
%>
<header>
	<div class="all">
    <header>
        <div class="top">
            <div class="title" onclick="location.href='MainForm.jsp'"><span>ㅇㅇ도서관<span></div>
        </div>
        <div class="find">
            <span>도서검색</span>
            <form action="findbook.jsp">
            <select class="fristfind" name="fristfind">
                <option value="동화">동화</option>
                <option value="역사">역사</option>
                <option value="과학">과학</option>
                <option value="생물">생물</option>
                <option value="전문서적">전문서적</option>
            </select>

            <select class="secondfind" name="secondfind">
                <option value="어린이">어린이</option>
                <option value="학생">학생</option>
                <option value="성인">성인</option>
            </select>
            <input type="submit" value="검색"></div>
            </form>
    </header>
</header>
<div id="content">
<div id="tablescroll">
<table id="findtable" border="1">
	<p class="findtitle">검색 결과: &nbsp; &nbsp; <%=fristfind %> &nbsp; &nbsp; <%=secondfind %></p>
		<tr class="findbooktitletr"><td>책 제목</td><td>대여일자</td><td>반납일자</td><td>대여여부</td></tr>
		<%
			for(int i=0; i<v.size(); i++){
				 LibraryBean bean = v.get(i); 
		%>
		<tr class="findbookbodytr"><td><%=bean.getBookname() %></td><td><%=bean.getRentalday() %></td><td><%=bean.getReturnday() %></td><td><%=bean.getCanrent() %></td></tr>
		<%
			}
		%>
</table>
</div>
</div>
<footer>
	<div class="footermeun">
        <div><span class="map" onclick="location.href='map.jsp'">오시는길</span>
        <span class="in" onclick="location.href='in.jsp'">내부사진</span>
    </div>
</footer>
</body>
</html>