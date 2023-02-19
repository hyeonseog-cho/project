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
	<div class="mapsize">
		<div class="maptitle">
			<span>오시는 길</span>
		</div>
		<div class="mapbody">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3160.215520166408!2d127.00888734904036!3d37.62061792882125!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357cbc7442da0e3f%3A0x69c05e9952107785!2z7IaU7IOY66y47ZmU7KCV67O064-E7ISc6rSA!5e0!3m2!1sko!2skr!4v1675415532476!5m2!1sko!2skr" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
		</div>
		<div class="where">
			<span>
			<span style="color: green">우이신설선</span> 솔샘역 하차  <br>
			->1번출구 <br>
			->3분 거리 도보
			</span>
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