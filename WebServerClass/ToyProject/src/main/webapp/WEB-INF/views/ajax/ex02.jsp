<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Toy Project</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<section>
			<h2>Ajax</h2>
			
			
			<h3>기존 방식</h3>
			<input type="text" id="txt1" value="${cnt }"/>
			<input type="button" value="확인" id="btn1" onclick="location.href='/toy/ajax/ex02data.do';"/>
			<div>
				<input type="text" />
			</div>
			
			
			<h3>Ajax 방식</h3>
			
			<input type="text" id="txt2"/>
			<input type="button" value="확인" id="btn2" onclick="test();"/>
			<div>
				<input type="text" />
			</div>
		</section>
	</main>
	
	<script>
		let ajax;
		
		function test(){
			
			//Ajax 통신을 지원한느 자바스크립트 객체
			ajax = new XMLHttpRequest();
			
			//alert(ajax);
			
			
			if(ajax != null){
				
				//서버에게 데이터 요청
				ajax.onreadystatechange = m1; 
				ajax.open('GET','/toy/ajax/ex02data2.do'); //<form method action>
				ajax.send();  //submit
				
			} else {
				alert('접속한 브라우저가 XMLHttpRequest를 지원하지 않습니다.');
			}
			
			
		}
		
		function m1(){
			if(ajax.readyState == 4 && ajax.status == 200){
				//alert(ajax.responseText);
				$('#txt2').val(ajax.responseText);
			}
		}
		
		
	</script>
</body>
</html>




