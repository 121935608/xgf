//dataTable init
function _Datatable_InitTwo(_dataTable, _columns, _url, lengths, _sortName, _sortOrder) {
	if (typeof (_dataTable) == "undefined") {
		var _sort = typeof(_sortName)=="undefined" ? [] : [_sortName, _sortOrder];
		_dataTable = $('.table-sort-Two').dataTable({
			"language" : {
				"processing" : "正在加载数据...",					// 显示正在处理字符串
				"lengthMenu" : "_MENU_ 条记录每页",					// 页面显示数据条数选项
				"zeroRecords" : "没有找到记录",					    // 过滤后空的结果字符串
				"info" : "第 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",  // 表格主要信息显示字符串
				"infoEmpty" : "无记录",							// 当表格没有数据和搜索记录时，表格主要信息显示字符串
				"infoFiltered" : "(总共 _MAX_  条)",				// 当使用搜索功能后，表格主要信息出追加的字符
				"infoPostFix" : "",								// 追加到所有其他主要信息字符串之后
				"search" : "模糊查询",								// 搜索框显示字符串
				"url" : "",										// 从远程加载语言信息文件url
				"paginate" : {
					"first" : "首页",								// 翻页中“首页”字符串
					"previous" : " 上一页 ",						// 翻页中“上一页”字符串
					"next" : " 下一页 ",							// 翻页中“下一页”字符串
					"last" : " 尾页 "								// 翻页中“尾页”字符串
				}
			},
			//"pagingType": "full_numbers",			// 显示首页和尾页
			//"scrollY":    "200px",                // 垂直方向上下滚动
			//"scrollX":     true,					// 水平方向左右滚动
			"processing" : true, 					// 显示加载信息
			"serverSide" : true, 					// 开启服务器模式
			"info" : true, 							// 开启Datatables信息显示
			"lengthChange" : false,					// 是否允许用户选择分页的页数,10、25、50、100
			"pageLength" : lengths,					// 更改初始页面长度 （每页的行数）
			"ordering" : true,						// 是否启用Datatables排序
			"order": _sort,							// 表格初始化排序
			"searching" : false,					// 开启搜索功能
			"columns" : _columns,					// 列配置数组
			"sAjaxSource" : _url,					// 请求的地址
			"aAjaxDataProp" : "data",				// 表数据的数据属性
			"fnServerData" : retrieveDataTwo			// 源加载数据的表的内容
		});
	} else {
		_dataTable.fnSettings().sAjaxSource = url;// 请求的地址
		// _dataTable.fnClearTable(0);
		_dataTable.fnDraw();
	}
	return _dataTable;
}

// 处理台请求数据
function retrieveDataTwo(sSource, data, fnCallback) {
	$.ajax({
		url : sSource,// 这个就是请求地址对应sAjaxSource
		data : data,// 这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数，每行显示的字段
		type : 'post',
		dataType : 'json',
		retrieve : true,
		async : true,
		success : function(result) {
			fnCallback(result);// 把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
			defaultOperation();
		},
		error : function(msg) {
			alert(msg);
		}
	});
}

function defaultOperation() {
	$("#defaultCheckbox").attr("checked", false);
}
