<%@page import="board.BoardBean"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/board/list.jsp</h1>
<%
// BoardDAO bdao 객체생성
BoardDAO bdao=new BoardDAO();
// 게시판 글 전체 개수   select count(*) from board
// int count = getBoardCount() 메서드 만들기 호출
int count=bdao.getBoardCount();
// 한페이지에 보여줄 글개수 설정
int pageSize=10;
// 현페이지 페이지번호 가져오기 
// http://localhost:8080/StudyJSP/board/list.jsp
// http://localhost:8080/StudyJSP/board/list.jsp?pageNum=7
//  pageNum 파라미터 가져오기
String pageNum=request.getParameter("pageNum");
// pageNum없으면  "1" 페이지 설정
if(pageNum==null){
	pageNum="1";
}
// 시작하는 행번호 구하기 
int currentPage=Integer.parseInt(pageNum);
// int startRow= currentPage  pageSize 이용해서 계산식 만들기
// currentPage  pageSize  => startRow 
//         1          10     =>  (1-1)*10+1=>0*10+1=> 0+1=> 1
//         2          10     =>  (2-1)*10+1=>1*10+1=>10+1=> 11
//         3          10     =>  (3-1)*10+1=>2*10+1=>20+1=> 21

int startRow= (currentPage-1)*pageSize+1;

// String sql="select * from board order by num desc limit ?,?";
// ? startRow-1  ? pageSize
		
// List boardList   = getBoardList() 메서드 만들 호출
List boardList=bdao.getBoardList(startRow,pageSize);
%>
<h1>전체 글개수 [<%=count %>]</h1>
<table border="1">
<tr><td>번호</td><td>제목</td><td>작성자</td><td>날짜</td><td>조회수</td></tr>
<%
for(int i=0;i < boardList.size();i++){
	// 배열 한칸에서 게시판 글 하나 가져오기
	BoardBean bb=(BoardBean)boardList.get(i);
%>
<tr><td><%=bb.getNum() %></td>
<td>
<%
// 답글 이면 이미지 보이기
// 들여쓰기 1 => 흰색이미지 너비 10px , 들여쓰기2 => 흰색이미지 너비 20px
if(bb.getRe_lev() > 0){
	int wid=bb.getRe_lev()*10;
	%>
	<img src="level.gif" width="<%=wid%>" height="15">
	<img src="re.gif"><%
}
%>
<a href="content.jsp?num=<%=bb.getNum()%>"><%=bb.getSubject()%></a>
</td>
    <td><%=bb.getName() %></td>
    <td><%=bb.getDate() %>
    </td><td><%=bb.getReadcount() %></td></tr>
<%
}
%>
</table>
<%
//한화면에 보여줄  페이지 개수 설정
int pageBlock=10;
// 시작하는 페이지번호 구하기
// 페이지번호     pageBlock  => startPage
//  1~10         10      =>   (0 ~ 9) /10*10+1=>0*10+1=>0+1=>1
//  11~20        10      =>   (10~19)/10*10+1=>1*10+1=>10+1=>11
int startPage=(currentPage-1)/pageBlock*pageBlock+1;
// 끝나는 페이지번호 구하기
// 시작페이지번호  pageBlock => endPage
//     1           10    =>   10
//     11          10    =>   20
//     21          10    =>   30
int endPage=startPage+pageBlock-1;
// 페이지번호 10까지 없을경우
// 전체페이지수 구하기
// 전체게시판글개수 / pageSize => pageCount
// count  12     /  pageSize 10 =>  1+1 => 2
//        19     /           10 =>  1+1  => 2
//        20     /           10 =>  2+0 => 2
//               count/pageSize 나머지 없으면 0페이지 더하고   나머지 있으면 1
int pageCount = count/pageSize +(count%pageSize==0?0:1);
if(endPage > pageCount){
	endPage=pageCount;
}
//이전
if(startPage > pageBlock){
	%><a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a><%
}	
for(int i=startPage;i<=endPage;i++){
	%><a href="list.jsp?pageNum=<%=i %>"><%=i %></a> <%
}
//다음
if(endPage < pageCount){
	%><a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a><%
}
%>
<!--         1 2 3 4 5 ... 10 [다음] -->
<!-- [이전]  11 12 13  ... 20  -->
</body>
</html>





