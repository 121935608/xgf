function MyAutoComplete(input,auto,arr,handle) {
    this.obj = document.getElementById(input);
    this.autoObj = document.getElementById(auto);
    this.search_value = "";	//当前的搜索输入值
    this.index = -1;		//当前选中的DIV的索引
    this.value_arr = arr;	//数据库中供检索的值 不包含重复值

    this.handle = handle;
}
MyAutoComplete.prototype = {
    // 初始化
    init: function(){
        var This = this;
        setClass.removeClass(This.autoObj,"hidden");
        this.autoObj.style.left = this.obj.offsetLeft + "px";
        this.autoObj.style.top = this.obj.offsetTop + this.obj.offsetHeight + "px";
    },
    //删除自动完成需要的所有DIV
    deleteDIV: function(){
        while(this.autoObj.hasChildNodes()){
            this.autoObj.removeChild(this.autoObj.firstChild);
        }
        setClass.addClass(this.autoObj,"hidden");
    },
    autoOnmouseover: function(index){
        if(index != this.index){
            setClass.addClass(this.autoObj.children[index],"on");
            setClass.removeClass(this.autoObj.children[this.index],"on");
            this.index = index;
        }
    },
    setValue: function(This){
        return function(){
            This.obj.value = this.seq;//This.obj.value ：选中的值    This.obj：input元素
            var input = This.obj;
            setClass.addClass(This.autoObj,"hidden");
            //选中值后，获取该商品数据填入表格
            $.ajax({
                url:"/xgf_admin/commodity/loadCommodity.action",
                type:'post',
                async:true ,
                cache:false ,
                dataType:"json",
                data:{
                    "commodityName":This.obj.value
                },
                success:function(data){
                    if(data.commodityNo == null){
                        return;
                    }else{
                        input.parentNode.parentNode.parentNode.children[2].children[0].value=data.commodityNo;
                        input.parentNode.parentNode.parentNode.children[3].children[0].value=data.unitName;
                        input.parentNode.parentNode.parentNode.children[4].children[0].value=data.stockNum;
                    }
                },
            });
        }
    },
    // 响应键盘
    pressKey: function(event){
        var code = event.keyCode;
        var length = this.autoObj.children.length;
        if(code == 38){		//↑
            setClass.removeClass(this.autoObj.children[this.index],"on");
            this.index--;
            if(this.index < 0){
                this.index = length - 1;
            }
            setClass.addClass(this.autoObj.children[this.index],"on");
            this.obj.value = this.autoObj.children[this.index].seq;
        }else if(code == 40){	//↓
            setClass.removeClass(this.autoObj.children[this.index],"on");
            this.index++;
            if(this.index > length-1){
                this.index = 0;
            }
            setClass.addClass(this.autoObj.children[this.index],"on");
            this.obj.value = this.autoObj.children[this.index].seq;
        }else{			//回车
            this.obj.value = this.autoObj.children[this.index].seq;
            setClass.addClass(this.autoObj,"hidden");
            this.index = -1;
        }
    },
    // 程序入口
    start: function(event){
        event = event || window.event;
        var code = event.keyCode;
        var This = this;
        if(code != 13 && code != 38 && code != 40){
            this.init();
            this.deleteDIV();
            this.search_value = this.obj.value;
            var valueArr = this.value_arr.unique();
            //去掉前后空格不能为空
            if(this.obj.value.replace(/(^\s*)|(\s*$)/g,"") == ""){ return;}
            //判断数组中是否含有输入的关键字
            try{
                var reg = new RegExp("("+ this.obj.value +")","i");	//输入"aaa" 则 reg = /(aaa)/i
            }catch(e){
                alert(e.message);
            }
            var div_index = 0;	//记录匹配索引个数
            for (var i = 0; i < valueArr.length; i++) {
                if(reg.test(valueArr[i])){
                    var div = document.createElement("div");
                    div.className = "auto_out";
                    div.seq = valueArr[i];
                    div.index = div_index;
                    div.innerHTML = valueArr[i].replace(reg,"<strong>$1</strong>");
                    this.autoObj.appendChild(div);
                    setClass.removeClass(this.autoObj,"hidden");
                    div_index++;
                    if(div_index == 1) {
                        setClass.addClass(this.autoObj.firstChild,"on");
                        this.index = 0;
                    }
                    div.onmouseover = function(){
                        This.autoOnmouseover(this.index);
                    }
                    div.onclick = this.setValue(This);
                }
            }
        }else{
            this.pressKey(event);
        }
        window.onresize = Bind(This);
    }

}