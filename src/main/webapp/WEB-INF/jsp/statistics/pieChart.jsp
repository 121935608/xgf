<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
</span> 数据统计 <span class="c-gray en">&gt;</span> 销售饼状图
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<div class="text-c">
	<div class="row col-xs-6 col-sm-4 .col-md-4" > 
					<div class="col-xs-8 col-sm-8 .col-md-8" style="margin-left:90%;">
						<input type="text" placeholder="开始时间" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
						<input type="text" placeholder="结束时间" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
					</div>
				</div>
		<div style="margin-right:10%;">
			<input type="text" class="input-text" style="width:250px" placeholder="商品编码|商品名称|商品条码" id="commodityNo" name="commodityNo">
			<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</div>
	</div>
</div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<script type="text/javascript">
$.get("${context_root}/statistics/pieList.action",function(data){
	var oChart = new Highcharts.Chart(oOptions);
	for(var i = 0;i<data.length;i++){
		var oSeries = {
				name: '营业占比',
	            colorByPoint: true,
                data: [{
                    name: data[i].categoryName,
                    y: parseFloat(data[i].totalVipPrice)
                }]
            };
       oChart.addSeries(oSeries);
	}
});
	var oOptions = {
        chart: {
        	renderTo: 'container',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '营业占比统计'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        }
	}
function query() {
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    $.ajax({  
        type: "POST",  
        url: "${context_root}/statistics/pieList.action?beginTime="+beginTime+"&endTime="+endTime,  
        dataType: "json",  
        success: function(data){  
        	var oChart = new Highcharts.Chart(oOptions);
        	for(var i = 0;i<data.length;i++){
        		var oSeries = {
        				name: '营业占比',
        	            colorByPoint: true,
                        data: [{
                            name: data[i].categoryName,
                            y: parseFloat(data[i].totalVipPrice)
                        }]
                    };
               oChart.addSeries(oSeries);
        	}
        }  
     });  
} 


</script>
</body>
</html>