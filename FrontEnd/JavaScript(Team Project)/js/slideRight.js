/** slideRight button**/

$(document).ready(function() {

	$("#resvBtn").click(function() {
		$("#resvScreen").css('display','block').animate({
			left: "0",
		},function(){
		});

		//Loading Map

		var map = new ol.Map({
			target: 'map',
			layers: [
				new ol.layer.Tile({
					source: new ol.source.OSM()
				})
			],
			view: new ol.View({
				center: ol.proj.fromLonLat([-73.9482774,45.403244]),
				zoom: 18
			})
		});
		// Add marker
		var marker = new ol.Feature({
			geometry: new ol.geom.Point(
					ol.proj.fromLonLat([-73.9482774,45.403244])
			),  // Cordinates of New York's Town Hall
		});
		var vectorSource = new ol.source.Vector({
			features: [marker]
		});
		var markerVectorLayer = new ol.layer.Vector({
			source: vectorSource,
		});
		map.addLayer(markerVectorLayer);
		// End of Map

	});

	$("#closeBtn").click(function() {
		$("#resvScreen").animate({
			left: "-100%"
		},function(){
			$(this).css('display','none');
			$("#map").empty();
		});
	});
});
