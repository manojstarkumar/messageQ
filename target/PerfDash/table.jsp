<!DOCTYPE html>
 
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>College student data</title>
    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
</head>
<body>
    <div id="tableDiv"></div>
</body>
	<script>
    $( document ).ready( function() {
        $.ajax({
                "url": 'http://localhost:7070/PerfDash/rest/getJson.dsh?format=table&scenario=Login',
                "success": function(json) {
                    var tableHeaders;
                    $.each(json.columns, function(i, val){
                    	console.log("val::"+val);
                        tableHeaders += "<th>" + val + "</th>";
                    });
                     
                    //$("#tableDiv").empty();
                    $("#tableDiv").append('<table id="displayTable" class="display" cellspacing="0" width="100%"><thead><tr>' + tableHeaders + '</tr></thead></table>');
                     
                    $('#displayTable').dataTable( {
						"data" : json.data
					});
                },
                "dataType": "json"
				
            });
    });
</script>
</html>