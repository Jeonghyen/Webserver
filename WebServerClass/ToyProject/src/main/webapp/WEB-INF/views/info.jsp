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

	section {
		margin: 0 auto;
	}

	main > section > div {
		width: 150px;
		height: 150px;
		background-repeat: no-repeat;
		background-size: contain;
		margin: 15px 0 10px 0;
	}
	
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<section>
			<h2>Info</h2>
			
			<div id="pic" style="background-image:url(/toy/pic/${dto.pic})"></div>
			<ul>
				<li>${dto.name}(${dto.id})</li>
				<li>Lv${dto.lv}.(${dto.lv==1?"일반":"관리자"})</li>
				<li>가입일(${dto.regdate})</li>
			</ul>
			
			<hr />
			
			<input type="button" value="탈퇴" class="btn btn-danger" onclick="location.href='/toy/member/unregister.do';"/>	
			<input type="button" value="돌아가기" class="btn btn-secondary" onclick="location.href='/toy/index.do';"/>	
		
			
			
		</section>
	</main>
	
	<script>
	
	</script>
</body>
</html>