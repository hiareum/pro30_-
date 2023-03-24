<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"
 %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
<style type="text/css">
a {
	text-decoration: none;
	color: black;
}
#name{font-size: 35px;}
.log{font-size: 20px;}
</style>
</head>
<body>
	<table border=0 width="100%">
		<tr>
			<td width="15%"><a href="${contextPath}/main.do">
					<h1 id="name">a-reum</h1>
			</a></td>
			 <td width="73%">
				<!-- <h1>
					<font color="#999999">스프링실습 홈페이지 입니다</font>
				</h1>
 -->
			</td>
 
			<td>
				<!-- <a href="#"><h3>로그인</h3></a> --> <c:choose>
					<c:when test="${isLogOn == true  && member!= null}">
						<h3 style="font-size: 15px;">환영합니다</br>${member.name }님!</h3>
						<a href="${contextPath}/member/logout.do"><h3 class="log">로그아웃</h3></a>
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/member/loginForm.do"><h3 class="log">로그인</h3></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>


</body>
</html>