/*按加号数量增*/
function addNum(rid) {
	var n = parseInt($("#goodsCount"+rid).val());
	$("#goodsCount"+rid).val(n + 1);
	calcRow(rid);
}
/*按减号数量减*/
function reduceNum(rid) {
	var n = parseInt($("#goodsCount"+rid).val());
	if (n == 0)
		return;
	$("#goodsCount"+rid).val(n - 1);
	calcRow(rid);
}
/*全选全不选*/
function checkall(ckbtn) {
	$(".ckitem").prop("checked", $(ckbtn).prop("checked"));
	calcTotal();
}

//删除单个商品
function delCartItem(num,btn) {
	$.ajax({
		url: "/carts/deleteByCid",
		type: "GET",
		data: "cid="+num,
		dataType: "json",
		success: function(json) {
			if (json.state == 200) {
				$(btn).parents("tr").remove();
				//将选中的数量和价格赋值
				$("#selectTotal").html(0);
				$("#selectCount").html(0);
				calcTotal();
			} else {
				alert(json.message);
			}
		}
	});
}

function calcTotal() {
	//选中商品的数量
	var vselectCount = 0;
	//选中商品的总价
	var vselectTotal = 0;

	//循环遍历所有tr
	for (var i = 0; i < $(".cart-body tr").length; i++) {
		//计算每个商品的价格小计开始
		//取出1行
		var $tr = $($(".cart-body tr")[i]);
		//取单价
		var vprice = parseFloat($tr.children(":eq(3)").children("span").html());

		//检查是否选中
		if ($tr.children(":eq(0)").children(".ckitem").prop("checked")) {
			//计数
			vselectCount++;
			//计总价
			vselectTotal += vprice;
		}
		//将选中的数量和价格赋值
		$("#selectTotal").html(vselectTotal);
		$("#selectCount").html(vselectCount);
	}
}