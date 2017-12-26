window.onload = function() {
	/** 表格全选 */
	$("table thead th input:checkbox").on(
			"click",
			function() {
				$(this).closest("table").find(
						"tr > td:first-child input:checkbox").prop("checked",
						$("table thead th input:checkbox").prop("checked"));
			});

	/** 选择checkBox样式 */
	$('.skin-minimal input').iCheck({
		checkboxClass : 'icheckbox-blue',
		radioClass : 'iradio-blue',
		increaseArea : '20%'
	});
}

/** 时间格式化 */
function formatDate(date, pattern) {
	var oldDate = new Date(date);
	var newDate = oldDate.format(pattern);
	return newDate;
}

Date.prototype.format = function(format) {
	var date = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '')
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k]
					: ("00" + date[k]).substr(("" + date[k]).length));
		}
	}
	return format;
}

$.y = {
	getRowId : function () {
		var rowIds = "";
		$("input[name=rowId]").each(function () {
			if (this.checked) {
				rowIds += $(this).val() + ",";
			}
		});
		return rowIds.replace(/,$/g, "");
	}
};