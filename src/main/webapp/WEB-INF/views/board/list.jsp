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
<h1>WebContent/board/list.jsp</h1>
<%
// 게시판 글 전체 개수   select count(*) from board
// int count = getBoardCount() 메서드 만들기 호출
// 한페이지에 보여줄 글개수 설정
%>
<h1>전체 글개수 [${pb.count }]</h1>
<table border="1">
<tr><td>번호</td><td>제목</td><td>작성자</td><td>날짜</td><td>조회수</td></tr>

<c:forEach var="bb" items="${boardList }">
<tr><td>${bb.num }</td>
<td>
<a href='<c:url value="content?num=${bb.num }"/>'>${bb.subject }</a>
</td>
    <td>${bb.name }</td>
    <td>${bb.date }
    </td><td>${bb.readcount }</td></tr>
</c:forEach>


</table>

<c:if test="${pb.startPage > pb.pageBlock }">
	<a href='<c:url value="board/list?pageNum=${pb.startPage - pb.pageBlock }"/>'>[이전]</a>
</c:if>
<c:forEach var="i" begin="${pb.startPage }" end="${pb.endPage }" step="1">
	<a href='<c:url value="board/list?pageNum=${i }"/>'>${i }</a>
</c:forEach>
<c:if test="${pb.endPage < pb.pageCount }">
	<a href='<c:url value="board/list?pageNum=${pb.startPage + pb.pageBlock }"/>'>[다음]</a>
</c:if>

<!--         1 2 3 4 5 ... 10 [다음] -->
<!-- [이전]  11 12 13  ... 20  -->
</body>
</html>





