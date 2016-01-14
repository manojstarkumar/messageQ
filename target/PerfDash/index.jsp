<jsp:include page="common.jsp"></jsp:include>
<div id="page-wrapper">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Dashboard <small>Statistics Overview</small>
				</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> <a href="/PerfDash">Dashboard</a>
					</li>
					<li class="active"><i class="fa fa-edit"></i> Trends</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-1">

				<label>Select Transaction</label>
			</div>
			<div class="col-lg-3">
				<select id="transactionTrend" class="form-control">
					<option value="Login">Login</option>
					<option value="Devices">Devices</option>
				</select>
			</div>

			<div class="col-lg-3">
				<button type="submit" class="btn btn-primary" onclick="javascript:getTrendMetrics();">View Trend</button>
				<div></div>
			</div>
			<!-- /.row -->
			<!-- Leave some head space. Trust me. It looks nice! -->
			<div class="row">
				<div class="col-lg-12">&nbsp;</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o fa-fw"></i> Trend Chart
							</h3>
						</div>
						<div class="panel-body">
							<div id="chartContainer">Select a Transaction to view trend</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-table fa-fw"></i> Trend Metrics
							</h3>
						</div>
						<div class="panel-body">
							<div id="tableDiv">Select a Transaction to view trend</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->

			<!-- Container Fluid -->
		</div>

		<!-- Page Wrapper -->
	</div>

	<!-- Wrapper -->
</div>

</body>
</html>
