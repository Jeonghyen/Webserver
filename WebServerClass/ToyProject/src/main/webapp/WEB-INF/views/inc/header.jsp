<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<style>
	.fa-regular.fa-lemon{
	
		<c:if test="${not empty auth and lv ==2}">
		color: orange;
		</c:if>
		<c:if test="${not empty auth and lv ==1}">
		color: gold;
		</c:if>
	}
	
	header > h1 > span {
		font-size: 14px;
		color: #777;
		
	}
	
	header > h1:hover{
		cursor: pointer;
	}
	
</style>
<header>
	<h1  onclick="location.href='/toy/index.do';">
	<i class="fa-regular fa-lemon"></i> Toy Project <i class="fa-regular fa-lemon"></i> 
	
	<c:if test="${not empty auth}">
	<span>${name}(${auth})님 안녕하세요</span>
	</c:if>
	</h1>
	<nav>
		<ul>
			<li><a href="/toy/index.do">Home</a></li>
			
			<c:if test="${empty auth}">
			<li><a href="/toy/member/register.do">Register</a></li>
			<li><a href="/toy/member/login.do">Login</a></li>
			</c:if>
			
			<li><a href="/toy/board/list.do">Board</a></li>
			<li><a href="/toy/etc/chart.do">Chart</a></li>
			<li><a href="/toy/etc/food.do">Etc</a></li>
			
			<c:if test="${not empty auth}">
			<li><a href="/toy/member/info.do">Info</a></li>
			<li><a href="/toy/member/logout.do">Logout</a></li>
			</c:if>
			
		</ul>
	</nav>
</header>