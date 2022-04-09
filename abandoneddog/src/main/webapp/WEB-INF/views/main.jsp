<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
	<title>Spring Boot 에서의 JSP</title>
	<meta charset="UTF-8" />
</head>
<body>
	<table border="1">
		<tr>
			<th>언어</th>
			<th>통합 개발 환경</th>
			<th>빌드 도구</th>
			<th>웹 애플리케이션 서버</th>
		</tr>
		
		<tr>
			<td>${map.Language}</td>
			<td>${map.IDE}</td>
			<td>${map.Build}</td>
			<td>${map.WAS}</td>
		</tr>
	</table>
</body>
</html>