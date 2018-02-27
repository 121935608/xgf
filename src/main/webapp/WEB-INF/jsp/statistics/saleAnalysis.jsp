<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
</span> 数据统计 <span class="c-gray en">&gt;</span> 销售分析
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
</div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
/* 设置日期，当前日期的前七天 */
var myDate = new Date();
myDate.setDate(myDate.getDate() - 6);
var dateArray = []; 
var dateTemp; 
var flag = 1; 
var data1 = new Array();
var data2 = new Array();
var data3 = new Array();
var data4 = new Array();
for (var i = 0; i < 7; i++) {
    dateTemp = (myDate.getFullYear())+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
    dateArray.push(dateTemp);
    myDate.setDate(myDate.getDate() + flag);
}
$.get("${context_root}/statistics/salesAnalysisList.action",function(data){
var n = 0;
	l:for(var i = 0; i<7;i++){
		if(data1.length == 7){
			break l;
		}
		if(n == data.length){
			for(var j = 0;j<7-i;j++){
				data1.push(0);
				data2.push(0);
				data3.push(0);
				data4.push(0);
			}
		}else{
			var addTime = formatDateTime(data[n].cashId);
			if(Date.parse(addTime)==Date.parse(dateArray[i])){
				data1.push(parseFloat(data[n].money));
				data2.push(parseFloat(data[n].number));
				data3.push(parseFloat(data[n].lirun));
				data4.push(parseFloat(data[n].maolilv));
				n ++;
			}else{
				data1.push(0);
				data2.push(0);
				data3.push(0);
				data4.push(0);
			}
		}
	}
	aa();
});
function formatDateTime(timeStamp) {   
	var date = new Date(timeStamp);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ?(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	return Y+M+D;   
}; 
function aa(){
	$('#container').highcharts({ 
		chart: {
            zoomType: 'xy'
        },
	    title: {
	        text: ''
	    },
	    xAxis: {
	        categories: dateArray,
	        crosshair: true
	    },
	    yAxis: [{
            title: {
                text: '',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            }
	    },
	    {
            title: {
                text: '',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            },
            opposite: true
        }],
        tooltip: {
            shared: true
        },
	    series: [{
	        type: 'column',
	        name: '销售总额',
	        data: data1
	    }, {
	        type: 'column',
	        name: '商品数量',
	        data: data2
	    }, {
	        type: 'column',
	        name: '利润',
	        data: data3
	    }, {
	        type: 'spline',
	        name: '毛利率',
	        yAxis: 1,
	        data: data4,
	        marker: {
	            lineWidth: 2,
	            lineColor: Highcharts.getOptions().colors[3],
	            fillColor: 'white'
	        }
	    }]
	});
}


		</script>
</body>
</html>