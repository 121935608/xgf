<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader title="优惠券列表"/>
<style>
	img{                                                                                                                      
	cursor: pointer;
	transition: all 0.6s;
	z-index:-9;
}
 img:hover{
	transform: scale(3.6);
	z-index:9999999;
} 
</style>
<body>
<div class="page-container">
	<div class="mt-20">
		<div class="info-div">
			<div class="row cl">
				<div class="col-xs-3 col-sm-2">
					<h4>优惠券信息</h4>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">门店名称</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.storeName}</span>
				</div>
				
				<label class="form-label col-xs-3 col-sm-2">联系人</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.contacts}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">手机号</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.phoneNum}</span>
				</div>
				
				<label class="form-label col-xs-3 col-sm-2">门店地址</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.address}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">订单编号</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.orderNum}</span>
				</div>
				
				<label class="form-label col-xs-3 col-sm-2">物流单号</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.logisticsSingleNum}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">损坏果物</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.damagedFruit}（Kg）</span>
				</div>
				
				<label class="form-label col-xs-3 col-sm-2">验货现场</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span id="inspectionScene" inspectionScene ="${coupon.inspectionScene}"></span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">损坏描述</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.damageDescription}</span>
				</div>
			
				<label class="form-label col-xs-3 col-sm-2">补券金额</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.amount}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">审核状态</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span id="auditStatus" auditStatus="${coupon.auditStatus}"></span>
				</div>
			
				<label class="form-label col-xs-3 col-sm-2">审核时间</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span id="auditTime" auditTime = "${coupon.auditTime}"></span>
				</div>
	        </div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">创建时间</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span id="createTime" createTime = "${coupon.createTime}"></span>
				</div>
		
			    <label class="form-label col-xs-3 col-sm-2">备注</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.remarks}</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">审核备注</label>
				<div class="formControls col-xs-6 col-sm-4">
					<span>${coupon.auditRemarks}</span>
				</div>
			</div>
		</div>
		<div class="info-div">
			<div class="row cl">
				<div class="col-xs-3 col-sm-2">
					<h4>照片信息</h4>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-2 col-sm-2">
				</div>
				
				<c:forEach items="${coupon.couponImgList}" var="couponImg" varStatus="vs">
				<div class="col-xs-2 col-sm-2">
					<tr>
						<td><s:property value="#vs.index+1" /></td>
						<td align="center"><img style="width: 100px; height: 100px; padding: 10px;" src="${couponImg.imgUrl}"></td>
					</tr>
				</div>
				</c:forEach>
				
		   </div>
	  </div>
</div>
<script type="text/javascript">
	$(function() {
		//审核状态 ： 0-已审核 1-未审核
		var auditStatus = $("#auditStatus").attr("auditStatus");
		if (auditStatus == 0) {
			auditStatus = "未审核";
		} else if(auditStatus == 1) {
			auditStatus = "已通过";
		} else if(auditStatus == 2) {
			auditStatus = "未通过";
		} else{
			auditStatus = "";
		}
		$("#auditStatus").text(auditStatus);

		//验货现场(0-是 1-否)
		var inspectionScene = $("#inspectionScene").attr("inspectionScene");
		if (inspectionScene == 0) {
			inspectionScene = "是";
		} else {
			inspectionScene = "否";
		}
		$("#inspectionScene").text(inspectionScene);

		//创建时间格式化
		$("#createTime").text(
				formatDate($("#createTime").attr("createTime"),
						"yyyy-MM-dd hh:mm:ss"));

		//审核时间格式化，存在则格式化
		var auditTime = $("#auditTime").attr("auditTime");
		if (auditTime != null && auditTime != "") {
			$("#auditTime").text(formatDate(auditTime, "yyyy-MM-dd hh:mm:ss"));
		}
	})
</script>
</body>
</html>
