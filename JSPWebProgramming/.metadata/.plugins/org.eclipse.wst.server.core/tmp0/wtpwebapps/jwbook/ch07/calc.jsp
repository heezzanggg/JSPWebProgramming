<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="calc" class="ch07.Calculator"/>
<jsp:setProperty name="calc" property="*" /> <!--calcForm.html의 입력양식에서 선택한 숫자와 연산자를 Calculator클래스의 멤버 변수값으로 전달 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기-useBean</title>
</head>
<body>
	<h2>게산 결과-useBean</h2>
	<hr>
	결과 : <%= calc.calc() %>
</body>
</html>