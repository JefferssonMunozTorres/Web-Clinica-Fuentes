google.charts.load('current', {'packages':['sankey','corechart', 'bar', 'calendar']});
google.charts.setOnLoadCallback(drawCharts);

function drawCharts() {
	checkfieldDate("from");
	checkfieldDate("to");
	
	var params="";
	params+="?from="+escape( getFieldDate("from") );
	params+="&to="+escape( getFieldDate("to") );
	
	var mensaje=" entre las fechas ";
	if(getFieldDate("from") != ""){
		mensaje +=getFieldDate("from");
	}else{
		mensaje +=" xxx ";
	}
	if(getFieldDate("to") != ""){
		mensaje += " - "+getFieldDate("to");
	}else{
		mensaje +=" - xxx ";
	}
	
	fetch('/api'+params, { credentials: "same-origin" }).then(function(response) {
		response.json().then(function(value) { 
			drawChartP1( value.subsdia );
			drawChartP2( value.subssem );
			});
		});
	}

function drawChartP1( values ) {
	var head=[ ['Fecha', 'Boletas'] ];
	
	var data = google.visualization.arrayToDataTable(
		head.concat( values )
		);

  	//var chart = new google.visualization.LineChart(document.getElementById('p1Chart'));
	var chart = new google.charts.Bar(document.getElementById('p1Chart'));
  	chart.draw(data, { 
  		 series: [{color: '#4776e6', visibleInLegend: true}]
  	});
	}

function drawChartP2( values ) {
	var head=[ ['Fecha', 'Cita'] ];
	
	var data = google.visualization.arrayToDataTable(
		head.concat( values )
		);
	
  	var chart = new google.charts.Bar(document.getElementById('p2Chart'));
  	chart.draw(data, { 
        series: [{color: '#45aaf2', visibleInLegend: true}]
        });
	}

function checkfieldDate( idElement ) {
	var el=document.getElementById( idElement );
	//console.log(el.value);
	//if ( el.value=='' ) return;
	//if ( el.value.split('/').length!=3 ) { return el.value=''; }
	
	}

function getFieldDate( idElement ) {
	var el=document.getElementById( idElement );
	if ( el.value=='' ) return '';
	//var d=el.value.split('/');
	//console.log(d);
	//return d[2]+'-'+d[1]+'-'+d[0];	
	return el.value;
	}
