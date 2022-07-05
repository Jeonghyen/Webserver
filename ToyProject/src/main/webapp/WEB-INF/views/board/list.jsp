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
			<h2>Board</h2>
			
			<c:if test="${map.isSearch == 'y' }">
			<div style="text-align:center; margin-bottom: 10px; color: cornflowerblue;">
				'${map.word}'으로 검색 결과 총 ${list.size()}개의 게시물 발견
			</div>
			</c:if>
			
			<table class="table table-bordered horizontal">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>이름</th>
					<th>날짜</th>
					<th>읽음</th>
				</tr>
				<c:forEach items="${list}" var="dto">
				<!--  조회수 높을 글 색 넣기 -->
				<%-- 
				<c:if test="${dto.readcount < 10 }">
				<tr>
				</c:if>
				<c:if test="${dto.readcount >= 10 }">
				<tr style="background-color: gold;"></tr>
				</c:if> --%>
				
				<tr>
					<td>${dto.seq}</td>
					<td>
						<!-- 답변 들여쓰기 -->
						<c:if test="${dto.depth > 0 }">
						
						<i class="fa-solid fa-caret-right" style="margin-left:${dto.depth * 20}px;"></i>
						</c:if>
						
						<a href="/toy/board/view.do?seq=${dto.seq}&isSearch=${map.isSearch}&column=${map.column}&word=${map.word}">${dto.subject}</a>
						
						<c:if test="${not empty dto.filename }">
						<i class="fa-solid fa-floppy-disk"></i>
						</c:if>
						
					<c:if test="${dto.commentcount > 0}">
					<span class="badge badge-primary">${dto.commentcount }</span>
					</c:if>
					
					<!-- 세시간 이내의 글 new -->
					<c:if test="${(dto.isnew * 24) <3 }">
						<span style="color:tomato;">new</span>
					</c:if>
					
					</td>
					<td>${dto.name}</td>
					<td>${dto.regdate }</td>
					<td>${dto.readcount}</td>
				</tr>
				</c:forEach>
				<!-- 검색 결과 없을 경우-->
				<c:if test="${list.size() == 0 }">
				<tr>
					<td colspan="5">게시물이 없습니다.</td>
				</tr>
				</c:if>
			</table>
			
		<%-- 	<div>
				<select id="pagebar">
					<c:forEach var="i" begin="1" end="${totalPage }">
					<option value="${i }">${i }페이지</option>
					</c:forEach>
				</select>
				${totalCount }
				${totalPage }
			</div> --%>
			
			
			<!-- 페이지바 -->
			<div style="text-align: center;">
					
				${pagebar }
			</div>
			
			
			<div>
			<!-- 검색 용도의 form은 get방식으로 만든다 -->
				<form method="GET" action="/toy/board/list.do">
					<table class="search">
						<tr>
							<td>
								<select name="column" class="form-control">
									<option value="subject">제목</option>
									<option value="content">내용</option>
									<option value="name">이름</option>
								</select>
							</td>
							<td>
								<input type="text" name="word" class="form-control" required />
							</td>
							<td>
								<button class="btn btn-primary">
									검색
								</button>
								<c:if test="${map.isSearch == 'y' }">
								<button class="btn btn-secondary" type="button" onclick="location.href='/toy/board/list.do';">
									목록
								</button>
								</c:if>
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			
			<c:if test="${not empty auth }">
			<div class="btns" id="listbtn">
				<input type="button" value="글쓰기" class="btn btn-primary" onclick="location.href='/toy/board/add.do';" />
			</div>
			</c:if>
		</section>
	</main>
	
	<script>
		//검색중
		//검색중 > 상태유지
		<c:if test="${map.isSearch == 'y' }">
		$('select[name=column]').val('${map.column}');
		$('input[name=word]').val('${map.word}');
		</c:if>
		
		
		$("#pagebar").change(function() {
			
			location.href = '/toy/board/list.do?page=' + $(this).val() + "&column=${map.column}&word=${map.word}";
		});
		
		$('#pagebar').val(${nowPage});
		
	</script>
</body>
</html>









