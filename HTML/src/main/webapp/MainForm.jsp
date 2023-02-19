<%@page import="Package.LibraryBean"%>
<%@page import="java.util.Vector"%>
<%@page import="Package.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="Mainstyle.css">
    
</head>
<body>
<%-- 
	DAO d = new DAO();
	d.refresh();
	
%> --%>
<%
	DAO d = new DAO();
	Vector<LibraryBean> v = d.findfiverentbook();
%>
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

    <div id="content">
            <div class="noticetitle">
            <div class="contentsize">
                <div class="smalltitle"><span>notice</span></div>
                <div class="notice">
                    <div onclick="location.href='notice1-21.jsp'">1월21~24일 도서관 휴무<span class="noticesamll">2023.1.18</span></div>
                    <div onclick="location.href='notice1-1.jsp'">1월1일 도서관 휴무<span class="noticesamll">2022.12.28</span></div>
                    <div onclick="location.href='notice12-25.jsp'">12월25일 도서관 휴무<span class="noticesamll">2022.12.14</span></div>
                    <div onclick="location.href='notice12-1.jsp'">12월 1일 도서관 휴무<span class="noticesamll">2022.11.18</span></div>
                </div>
            </div>
            </div>  
            <div class="book">
                <div class="booksize">
                <div class="booktitle"><span>대여중인 도서</span></div>
                <form>
                <table border="1" class="table">
                        <tr height="40">
                            <td width="350" align="center">책제목</td>
                            <td width="200" align="center">반납일자</td>
                        </tr>
                        <%
							for(int i=0; i<v.size(); i++){
								 LibraryBean bean = v.get(i); 
						%>
						<tr class="findbookbodytr"><td><%=bean.getBookname() %></td><td><%=bean.getReturnday() %></td></tr>
						<%
							}
						%>
                        <tr height="40">
                            <td align="center" colspan="2">
                            <input type="button" value="전체 보기" onclick="location.href='allrentbook.jsp'">
                            </td>
                        </tr>
                </table>
                </form>
                </div>
            </div>
            <div class="introduce">
                <div class="introducebody">
                <b>문을 열면 세월의 흔적이 묻어나는 도서관<br>
                그 속에서 변화를 꿈꾸는 곳이 ㅇㅇ도서관입니다.</b><br>
                <span>사람, 책, 미래의 중심에 있으며, 좋은 자료와<br>
                정보와 프로그램 운영으로 지역주민에게 만족을<br>
                주는 도서관이 되기 위해 노력하고 있습니다.<br>
                도서관은 책을 매개로 지식과 정보를 공유하며,<br>
                배움을 통해 행복한 삶을 꿈꾸는 과거와 미래가 <br>
                함께 공존하는 곳입니다.</span>
                </div>
            </div>
    </div>
    
    
    <footer>
        <div class="footermeun">
        <div><span class="map" onclick="location.href='map.jsp'">오시는길</span>
        <span class="in" onclick="location.href='in.jsp'">내부사진</span>
        </div>
    </footer>
    
</div>
</body>
</html>