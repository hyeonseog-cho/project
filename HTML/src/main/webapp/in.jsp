<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="Mainstyle.css">
</head>
<body>
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
	<div class="insize">
		<div class="intitle">
			<span>도서관 내부</span>
		</div>
		<div class="inbody">
			<div class="one"><div>1층 내부</div><img src="./images/one.jpg" alt="1층 내부"></div>
			<div class="two"><div>2층 내부</div><img src="./images/two.jpg" alt="2층 내부"></div>
			<div class="three"><div>디지털 자료실</div><img src="./images/three.jpg" alt="디지털 자료실"></div>
			<div class="four"><div>어린이 도서관</div><img src="./images/four.jpg" alt="어린이 도서관"></div>
		</div>
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