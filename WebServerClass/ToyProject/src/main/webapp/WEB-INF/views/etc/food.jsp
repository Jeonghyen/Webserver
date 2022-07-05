<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Toy Project</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b2c4452052d56d04184af646057bc11"></script>

<link rel="stylesheet" href="/toy/asset/css/rateit.css" />

<style>
	#map {
		width: 500px;
		height: 400px;
	}
	
	.table .table {
		width: 250px;
	}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<section>
			<h2>맛집 즐겨찾기</h2>
			
			<table class="table table-bordered">
				<tr>
					<td><div id="map"></div></td>
					<td>
						<table class="table table-bordered">
							<c:forEach items="${list}" var="dto">
							<tr>
								<td>
									<span class="${dto.icon}"></span> 
									<a href="#!" onclick="selFood(${dto.lat}, ${dto.lng}, ${dto.category});">${dto.name}</a>(<span class="rateit" data-rateit-resetable="false" data-rateit-readonly="true"></span>)
								</td>
							</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			
			<div class="btns">
				<input type="button" value="등록하기" class="btn btn-primary"
					onclick="location.href='/toy/etc/addfood.do';">
			</div>
			
		</section>
	</main>
	
	
	<script src="/toy/asset/js/jquery.rateit.js"></script>
	<script>
		var container = document.getElementById('map');
		
		var options = {
			center: new kakao.maps.LatLng(37.499321, 127.033220),
			level: 3
		};
	
		var map = new kakao.maps.Map(container, options);
		
		
		//rateit
		$('.rateit').ready(function() {
			
			<c:forEach items="${list}" var="dto" varStatus="status">
			$('.rateit').eq(${status.index}).rateit('value', '${dto.star}');
			</c:forEach>
			
		});
		
		
		<%--
		<c:forEach items="${list}" var="dto" varStatus="status">
		let m${status.index} = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
		});
		
		m${status.index}.setMap(map);
		</c:forEach>
		--%>
		
		
		let m = null;
		
		function selFood(lat, lng, category) {
			
			//alert(lat + ',' + lng);
			map.panTo(new kakao.maps.LatLng(lat, lng));
			
			if (m != null) {
				m.setMap(null);
			}
			
			/* 
			m = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(lat, lng)
			});
			
			m.setMap(map); 
			*/
			
			let imageSrc = '/toy/pic/m' + category + '.png',
			    imageSize = new kakao.maps.Size(64, 69),
			    imageOption = {offset: new kakao.maps.Point(27, 69)}; 
			      
			let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
			    markerPosition = new kakao.maps.LatLng(lat, lng); 
	
			m = new kakao.maps.Marker({
			    position: markerPosition, 
			    image: markerImage
			});
			
			m.setMap(map);
				
		}
			
		
	</script>

</body>
</html>



















