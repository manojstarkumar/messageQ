<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>College student data</title>
<script type="text/javascript"
	src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="//code.highcharts.com/highcharts.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
  var options = {
      chart: {
          renderTo: 'container',
          type: 'line'
          },
		  xAxis : {
			  type: 'datetime'
		  },
          
    title: {
        text: 'PayOff Curve'
    },
    legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
    },
    series: []
};
$.getJSON('http://localhost:7070/PerfDash/rest/getJson.dsh?format=chart&scenario=Login', function(list) {
options.series = list; // <- just assign the data to the series property.
        var chart = new Highcharts.Chart(options);
  });
  });
    </script>
</head>
<body>
	<div id="container" style="height: 400px"></div>
</body>
</html>