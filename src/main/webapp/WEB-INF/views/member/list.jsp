<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.id }">
		<c:redirect url="./login"/>
	</c:when>
	<c:otherwise>
		<c:if test="${ sessionScope.id ne 'admin'}">
		<c:redirect url="./main"/>
		</c:if>
	</c:otherwise>
</c:choose>
<h1>WebContent/member/list.jsp</h1>
<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입일자</td></tr>
	<c:forEach var="mb" items="${mbList }">
	<tr><td>${mb.id }</td>
	    <td>${mb.pass }</td>
	    <td>${mb.name }</td>
	    <td>${mb.date }</td></tr>
	</c:forEach>
</table>
</body>
</html>