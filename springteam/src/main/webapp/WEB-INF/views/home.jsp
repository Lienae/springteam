<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />     
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
  </head>
  <body>
    <h1>home.jsp - java</h1>
    <button onclick="location.href='${root}test1'">test1</button>        
    <br><br>
    
    <img src="images/tjoeun.jpg" >
  </body>
</html>

