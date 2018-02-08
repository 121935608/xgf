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

/* 设置日期，当前日期的前七天 */

var dateArray = ["8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"]; 
var data1 = new Array();
var data2 = new Array();
var data3 = new Array();
var data4 = new Array();

$.get("${context_root}/statistics/passengerAnalysisGraph.action",function(data){
	var oChart = new Highcharts.Chart(oOptions);
	for(var i = 0;i<data.length;i++){
		var oSeries = {
                data: [{
                    name: data[i].hour,
                    y: parseInt(data[i].number)
                }]
            };
       oChart.addSeries(oSeries);
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
            categories:dateArray,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        }
	}
</script>
</body>
</html>