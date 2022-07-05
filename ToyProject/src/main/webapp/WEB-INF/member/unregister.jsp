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
			
			<h2>Unregister</h2>
			
			<div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt beatae odio quidem impedit eius itaque officia iusto dolor repudiandae veritatis tenetur necessitatibus nam provident laborum fuga pariatur soluta in quod.</div>
			
			<div class="btns rg">
					<input type="button" value="돌아가기" class="btn btn-secondary" onclick="location.href='/toy/index.do'" />
					<input type="submit" value="탈퇴하기" class="btn btn-primary" onclick="location.href='/toy/member/unregisterok.do'"/>
				</div>
		
		</section>
	</main>
	
	<script>
	
	</script>
</body>
</html>