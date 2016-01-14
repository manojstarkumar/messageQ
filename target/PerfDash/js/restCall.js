/**
 * Do rest call to get data
 */

/* Generate Chart */
function getTrendMetrics() {

	$(document)
			.ready(
					function() {
						
						$("#chartContainer").html("<img src='img/spinner-small.gif'>Loading, Please Wait...");
						$("#tableDiv").html("<img src='img/spinner-small.gif'>Loading, Please Wait...");

						var scenario = document
								.getElementById("transactionTrend").value;
						var options = {
							chart : {
								renderTo : 'chartContainer',
								type : 'line'
							},
							xAxis : {
								type : 'datetime'
							},
							title : {
								text : 'Performance Trend'
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'middle',
								borderWidth : 0
							},
							series : []
						};
						$
								.getJSON(
										'rest/getJson.dsh?format=chart&scenario='
												+ scenario,
										function(list) {
											options.series = list; // <- just
																	// assign
																	// the data
																	// to the
																	// series
																	// property.
											var chart = new Highcharts.Chart(
													options);
										})
								.error(
										function() {
											$("#chartContainer")
													.html(
															"Something is not right here. Please try again later<br/>If the problem persists, drop a note <a href='mailto:manojkumar_k@symantec.com'>here</a>");
										});

						/* Generate Table */
						$
								.ajax(
										{
											"url" : 'rest/getJson.dsh?format=table&scenario='
													+ scenario,
											"success" : function(json) {
												var tableHeaders="";
												$.each(json.columns, function(
														i, val) {
													tableHeaders += "<th>"
															+ val + "</th>";
												});

												$("#tableDiv").empty();
												$("#tableDiv")
														.append(
																'<table id="displayTable" class="display" cellspacing="0" width="100%"><thead><tr>'
																		+ tableHeaders
																		+ '</tr></thead></table>');

												$('#displayTable').dataTable({
													"data" : json.data
												});
												},
											"dataType" : "json"

										})
								.error(
										function() {
											$("#tableDiv")
													.html(
															"Something is not right here. Please try again later<br/>If the problem persists, drop a note <a href='mailto:manojkumar_k@symantec.com'>here</a>");
										});
					});
}