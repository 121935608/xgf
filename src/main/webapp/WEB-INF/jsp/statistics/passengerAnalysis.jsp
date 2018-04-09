<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
</span> 数据统计 <span class="c-gray en">&gt;</span> 今日客流分析
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
var data1 = new Array();
var data2 = new Array();
var dateArray = ["08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"]; 
$.get("${context_root}/statistics/passengerAnalysisGraph.action",function(data){
	var oChart = new Highcharts.Chart(oOptions);
	var n = 0;
	l:for(var i = 0; i<16;i++){
		if(data1.length == 16){
			break l;
		}
		if(n == data.length){
			for(var j = 0;j<16-i;j++){
				data1.push(dateArray[i]);
				var oSeries = {
						name: '人数',
		                data: [{
		                    name: dateArray[i],
		                    y: 0
		                }]
		            };
				oChart.addSeries(oSeries);
			}
		}else{
			if(data[n].hour==dateArray[i]){
				data1.push(dateArray[i]);
				var oSeries = {
						name: '人数',
		                data: [{
		                    name: dateArray[i],
		                    y: parseInt(data[n].number)
		                }]
		            };
				oChart.addSeries(oSeries);
				n ++;
			}else{
				data1.push(dateArray[i]);
				var oSeries = {
						name: '人数',
		                data: [{
		                    name: dateArray[i],
		                    y: 0
		                }]
		            };
				oChart.addSeries(oSeries);
			}
		}
	}
    
});
var oOptions = {
        chart: {
        	renderTo: 'container',
            type: 'column'
        },
        title: {
            text: '今日客流分析'
        },
        xAxis: {
        	type: 'category'
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.y:.1f}</b>',
        }
	}
</script>
</body>
</html>