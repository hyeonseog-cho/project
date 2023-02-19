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
	<div class="noticesize">
		<div class="noticebodytitlediv"><span class="noticebodytitle">1월 1일 도서관 휴무</span></div>
		<div class="noticebodydiv"><span class="noticebody">신정으로 인한 도서관 휴무 안내</span><br><br>
		<img src="./images/day1-1.jpg"></div>
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