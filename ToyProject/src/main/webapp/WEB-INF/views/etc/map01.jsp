<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Toy Project</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=44bcc5d27f762bc49e6d42266c38387d"></script>
<style>
	#map{
		width: 90%;
		height: 400px;
		margin: 0 auto;
	}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<section>
			<h2>카카오 맵</h2>
			
			<div id="map"></div>
		</section>
	</main>
	
	<script>
			var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
			var options = { //지도를 생성할 때 필요한 기본 옵션
				center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
				level: 3 //지도의 레벨(확대, 축소 정도)
			};
		
			var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
			
			
			//map.setCenter(좌표);
			//map.panTo(좌표);
			
			let m = null;
			
			kakao.maps.event.addListener(map, 'click', function(evet){
				//event.latLng
				//map.panTo(event.latLng);
				
				//이전 마커가 존재하면 삭제
				if(m != null){
					m.setMap(null);
				}
				
				m = new kakao.maps.Marker({
					
					position: event.latLng
				});
				
				m.setMap(map);
			});
			
	</script>
</body>
</html>











