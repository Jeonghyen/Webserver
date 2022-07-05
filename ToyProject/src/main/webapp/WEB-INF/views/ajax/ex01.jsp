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
	.item{
		margin: 10px;
		padding: 5px;
		text-align:right;
	}
	
	#item1 { background-color:tomato;}
	#item2 { background-color:gold;}
	#item3 { background-color:cornflowerblue;}
	#item4 { background-color:yellowgreen;}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<section>
			
			<h2>Ajax</h2>
			
			<table class="table table-bordered">
				<tr>
					<td><strong>질문</strong>${dto.question }</td>
				</tr>
				<tr>
					<<td>
					<div>1. ${dto.item1 }(${dto.cnt1 }표)</div>
					<div>2. ${dto.item2 }(${dto.cnt2 }표)</div>
					<div>3. ${dto.item3 }(${dto.cnt3 }표)</div>
					<div>4. ${dto.item4 }(${dto.cnt4 }표)</div>
					
					</td>
				</tr>
				<tr>
					<td>
						<div class="item" id="item1">${dto.item1 } <span class="badge">${dto.cnt1 }</span> </div>
						<div class="item" id="item2">${dto.item2 } <span class="badge">${dto.cnt2 }</span> </div>
						<div class="item" id="item3">${dto.item3 } <span class="badge">${dto.cnt3 }</span> </div>
						<div class="item" id="item4">${dto.item4 } <span class="badge">${dto.cnt4 }</span> </div>
						
					</td>
				</tr>
			</table>
			
			<hr />
			
			<input type="text" class="form-control" placeholder = "다른 내용.." />
		</section>
	</main>
	
	<script>
		$('#item1').width(${dto.cnt1 * 25 + 30});
		$('#item2').width(${dto.cnt2 * 25 + 30});
		$('#item3').width(${dto.cnt3 * 25 + 30});
		$('#item4').width(${dto.cnt4 * 25 + 30});
	</script>
</body>
</html>