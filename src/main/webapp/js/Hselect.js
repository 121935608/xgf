﻿/**
 * Created by hzwy23 on 2016/10/23.
 */
(function($){
    $.fn.Hselect = function(param){
        var sel = this
        var obj = document.createElement("div")

        //init div css
        //get parent class to it
        $(obj).css({
            "padding":"0",
            "margin":"0",
            "border":"none",
            "outline":"none",
            "text-align":"left",
            "position":"relative"
        }).addClass($(sel).attr("class"))

        // default parameters
        var __DEFAULT = {
            data: "",
            height:"26px",
            width:"auto",
            border:"#ccc solid 1px",
            fontSize:"13px",
            borderRadius:"5px",
            bgColor:"white",
            placeholder:"--请选择--",
            
            opwidth:"170px",
            showLiHeight:"30px",
            showHeight:"230px",
            showBorder:"",
            showFontSize:"14px",
            iconColor:"#ff5763",

            onChange:"",
        }

        $.extend(true,__DEFAULT,param);

        // set showBorder to border style
        if (__DEFAULT.showBorder==""){
            __DEFAULT.showBorder = __DEFAULT.border
        }

        /*
         * This function sort array.
         * Accept One Array Variable.
         * */
        function sortTree(a){

            // load result sorted
            var list = [];

            // get select's options
            // append it to new select which simulate by ul li
            if (Object.prototype.toString.call(a) == '[object Array]'){
                $(sel).find("option").each(function(index,element){
                    var ijs = {}
                    ijs.id = $(element).val();
                    ijs.text = $(element).text()
                    a.push(ijs)
                })
            } else {
                $(sel).find("option").each(function(index,element){
                    var ijs = {}
                    ijs.id = $(element).val();
                    ijs.text = $(element).text()
                    list.push(ijs)
                })
                return list
            }

            //set max dept val
            var MAXDEPT = 8;

            var INDEX = 1;

            function getRoots(arr){
                var Roots = [];
                for(var i = 0; i < arr.length;i++){
                    var rootFlag = true
                    for ( var j = 0; j < arr.length;j++){
                        if (arr[i].upId == arr[j].id){
                            rootFlag = false
                            break
                        }
                    }
                    if (rootFlag == true){
                        Roots.push(arr[i])
                    }
                }
                return Roots
            }

            function traversed(node,arr){
                if (++INDEX > MAXDEPT){
                    console.log("递归超过8层,为保护计算机,退出递归");
                    return
                }
                for (var i = 0; i < arr.length; i++){

                    if (node == arr[i].upId){
                        arr[i].dept = INDEX
                        list.push(arr[i])
                        traversed(arr[i].id,arr)
                    }
                }
                INDEX--;
            }

            function listElem(roots,arr){
                for (var i = 0; i < roots.length; i++){
                    roots[i].dept = INDEX
                    list.push(roots[i])
                    traversed(roots[i].id,arr)
                }
            }

            listElem(getRoots(a),a)

            return list
        }

        function genTreeUI(a){
            var odivStyle='cursor:pointer;background-color: '+__DEFAULT.bgColor+';padding:0px;text-align: left !important;width: '+__DEFAULT.width+'; border:'+__DEFAULT.border+'; height: '+__DEFAULT.height+'; line-height: '+__DEFAULT.height+'; padding-left: 10px;border-radius:'+__DEFAULT.borderRadius+''
            var odiv = '<div class="HshowSelectValue" style="'+odivStyle+'">' +
                '<span style="height: '+__DEFAULT.height+'; font-size: '+__DEFAULT.fontSize+'">'+__DEFAULT.placeholder+'</span>' +
                '<hzw style="position: relative;width: 20px; float: right;height: '+__DEFAULT.height+'; line-height: '+__DEFAULT.height+';">' +
                '<i style="border-color:#888 transparent transparent transparent;border-style: solid;border-width: 5px 4px 0px 4px;height: 0;left: 50%;margin-left: -4px;margin-top:-3px ;position: absolute;top: 50%;width: 0;"></i>' +
                '</hzw></div>'
            odiv+='<div class="HselectShowAreaHuangZhanWei" style="width:'+__DEFAULT.opwidth+';white-space:nowrap;background-color: #fefefe;border: '+__DEFAULT.showBorder+';display: none; border-radius: 3px ;position: fixed;z-index:9999">' +
                '<input style="border:#6699CC solid 1px; padding-left:5px;margin:5px 5px;height:'+__DEFAULT.showLiHeight+';"/>'
            var opt = odiv+'<ul style="z-index: 9999;padding: 0px;list-style: none;margin:0px;' +
                'max-height:'+__DEFAULT.showHeight+';' +
                'overflow: auto;' +
                '">'
            for(var i = 0; i < a.length; i++){
                var pd = parseInt(a[i].dept)*20 - 10
                if (isNaN(pd)){
                    pd = 10
                }
                var li = '<li data-id="'+a[i].id+'" data-dept="'+a[i].dept+'" style="margin:0px; text-align: left;font-weight:500;padding-left:'+pd+'px; height:'+__DEFAULT.showLiHeight+'; line-height: '+__DEFAULT.showLiHeight+'; font-size: '+__DEFAULT.showFontSize+'; cursor: pointer;position: relative;">' +
                    '<hzw class="HshowOrHideIconHzw" style="height: '+__DEFAULT.showLiHeight+'; line-height: '+__DEFAULT.showLiHeight+'; width: 20px;cursor: cell;display: inline-block">' +
                    '<i style="border-color:'+__DEFAULT.iconColor+' transparent transparent transparent;border-style: solid;border-width: 6px 5px 0px 5px;height: 0;margin-left: 1px;margin-top: -5px;position: absolute;top: 50%;width: 0;"></i>' +
                    '</hzw>' +
                    '<span style="height: '+__DEFAULT.showLiHeight+'; line-height: '+__DEFAULT.showLiHeight+'; position: absolute;">'+a[i].text+'</span></li>'
                opt+=li;
            }
            opt +='</ul></div>'
            return opt;
        }

        function showUp(e){
            var dept = $(e).attr("data-dept")
            $(e).prevAll().each(function(index,element){
                if (parseInt(dept)>parseInt($(element).attr("data-dept"))){
                    $(element).show();
                    dept = $(element).attr("data-dept")
                }
            })
        }


        function initSelect(selObj,arr){
            var optHtml = ""
            for (var i = 0; i < arr.length; i++){
                optHtml+='<option value="'+arr[i].id+'">'+arr[i].text+'</option>'
            }
            $(selObj).append(optHtml)
            $(selObj).hide()
        }

        function showOrHide(e){
            var topBorderColor = __DEFAULT.iconColor+' transparent transparent transparent'
            var leftBorderColor = 'transparent transparent transparent '+__DEFAULT.iconColor
            var dept = $(e).attr("data-dept")
            var nextObj = $(e).next()
            var nextDept = $(nextObj).attr("data-dept")
            var nextDisplay = $(nextObj).css("display")
            if (nextDisplay == "none" && parseInt(nextDept)>parseInt(dept)){
                $(e).find("i").css({
                    "border-color":topBorderColor,
                    "border-width":"6px 5px 0px 5px"
                })

                $(e).nextAll().each(function(index,element){
                    if (parseInt(dept)+1==parseInt($(element).attr("data-dept"))){
                        $(element).find("i").css({
                            "border-color":leftBorderColor,
                            "border-width":"5px 0px 5px 6px",
                        });

                        $(element).show();
                    }else if (parseInt(dept)+1 < parseInt($(element).attr("data-dept"))){
                        $(element).find("i").css({
                            "border-color":leftBorderColor,
                            "border-width":"5px 0px 5px 6px",
                        })

                        $(element).hide();
                    }else{
                        return false
                    }
                })
            }else if (nextDisplay == "none" && parseInt(nextDept)<=parseInt(dept)){
                return
            }else if (nextDisplay != "none" && parseInt(nextDept)>parseInt(dept)){

                $(e).find("i").css({
                    "border-color":leftBorderColor,
                    "border-width":"5px 0px 5px 6px",
                })

                $(e).nextAll().each(function(index,element){
                    if (parseInt(dept)<parseInt($(element).attr("data-dept"))){
                        $(element).find("i").css({
                            "border-color":leftBorderColor,
                            "border-width":"5px 0px 5px 6px",
                        })

                        $(element).hide();
                    }else if (parseInt(dept)>=parseInt($(element).attr("data-dept"))){
                        return false
                    }
                })
            }else {
                return
            }
        }

        var ui = genTreeUI(sortTree(__DEFAULT.data))
        initSelect(sel,__DEFAULT.data)

        $(obj).html(ui)
        $(sel).after(obj)

        $(obj).find("input").focus();

        //when select was change
        //change show values
        $(sel).on('change',function(){

            //window.event.cancelBubble = true;
            var text = $(this).find("option:selected").text()
            $(obj).find(".HshowSelectValue span").html(text)
        })


        $(obj).find("ul li").each(function(index,element){
            var curDept = parseInt($(element).attr("data-dept"))
            var nextDept = parseInt($(element).next().attr("data-dept"))
            if (curDept>=nextDept || isNaN(nextDept)){
                $(element).find("hzw").remove()
            }
        })

        $(obj).find("input").on('input',function(){
            window.event.cancelBubble = true;
            var inpText = $(this).val();
            if (inpText == ""){
                $(obj).find("ul li").show();
                return
            }
            $(obj).find("ul li").each(function(index,element){
                if ($(element).find("span").html().indexOf(inpText)>=0){
                    $(element).show()
                    showUp(element)
                }else{
                    $(element).hide()
                }
            })
        })

        $(obj).find("input").on('click',function(){
            window.event.cancelBubble = true;
            $(this).focus();
        })

        $(obj).find(".HshowOrHideIconHzw").on("click",function(){
            window.event.cancelBubble = true;
            showOrHide($(this).parent())
        })

        $(obj).find("li").on('mouseover',function(){
            window.event.cancelBubble = true;

            var ul = $(this).closest("ul")

            $(ul).find("li").css({
                "background-color":"",
                "color":""
            })

            $(this).css({
                "background-color":"#6699CC",
                "color":"white"
            })
        })

        $(obj).find("li").on('click',function(){
            window.event.cancelBubble = true;

            var text = $(this).find("span").html();
            var id = $(this).attr("data-id")
            $(sel).val(id)
            $(this).closest("div").prev().find("span").html(text)
            $(this).closest("div").hide();
            $("body").find(".Hzwy23FillBodyForSelectItems").animate({height:'0px'},500,function(){
                $(this).remove()
            })

            $(obj).find(".HshowSelectValue i").css({
                "border-color":"#888 transparent transparent transparent",
                "border-width":"5px 4px 0px 4px"
            })

            if (typeof __DEFAULT.onChange == "function"){
                __DEFAULT.onChange();
            }
        })

        $(obj).find("ul").on('mousewheel',function(){
            window.event.cancelBubble = true;
        })

        $("div").scroll(function() {
            window.event.cancelable = true;
            var showUiStatus = $(obj).find(".HselectShowAreaHuangZhanWei").css("display")
            if (showUiStatus != "none"){
                var ptop = $(obj).offset().top
                var pleft = $(obj).offset().left;
                var tp = ptop+$(obj).find(".HshowSelectValue").height()
                $(obj).find(".HselectShowAreaHuangZhanWei").offset({top:tp,left:pleft})
            }
        });

        $(document).scroll(function() {
            window.event.cancelable = true;
            var showUiStatus = $(obj).find(".HselectShowAreaHuangZhanWei").css("display")
            if (showUiStatus != "none"){
                var ptop = $(obj).offset().top
                var pleft = $(obj).offset().left;
                var tp = ptop+$(obj).find(".HshowSelectValue").height()
                $(obj).find(".HselectShowAreaHuangZhanWei").offset({top:tp,left:pleft})
            }
        });

        $("body").scroll(function() {
            window.event.cancelable = true;
            var showUiStatus = $(obj).find(".HselectShowAreaHuangZhanWei").css("display")
            if (showUiStatus != "none"){
                var ptop = $(obj).offset().top
                var pleft = $(obj).offset().left;
                var tp = ptop+$(obj).find(".HshowSelectValue").height()
                $(obj).find(".HselectShowAreaHuangZhanWei").offset({top:tp,left:pleft})
            }
        });

        $(obj).find(".HshowSelectValue").on('click',function(){
            var showUiStatus = $(obj).find(".HselectShowAreaHuangZhanWei").css("display")
            window.event.cancelBubble = true;
            if (showUiStatus == "none"){
                $(".HselectShowAreaHuangZhanWei").hide()
                $(".HshowSelectValue i").css({
                    "border-color":"#888 transparent transparent transparent",
                    "border-width":"5px 4px 0px 4px",
                })
                var w = $(obj).width()
                $(obj).find(".HselectShowAreaHuangZhanWei").css("min-width",w)
                $(obj).find(".HselectShowAreaHuangZhanWei input").css("min-width",w-12)


                var nextObj = $(this).next()
                $(nextObj).find("input").val("")
                $(nextObj).show();
                $(nextObj).find("input").focus();
                $(nextObj).find("ul").scrollTop(0);
                $(nextObj).find("ul").scrollLeft(0);
                $(obj).find(".HshowSelectValue i").css({
                    "border-color":"transparent transparent #888 transparent",
                    "border-width":"0px 4px 5px 4px"
                })

                var ptop = $(obj).offset().top
                var pleft = $(obj).offset().left;
                var tp = ptop+$(this).height()
                var ulHeight = $(nextObj).height()
                if (tp+ulHeight > document.body.scrollHeight){
                    var addHeight = tp+ulHeight+30 - document.body.scrollHeight
                    var appdiv = document.createElement("div")
                    $(appdiv).css("height",addHeight).addClass("Hzwy23FillBodyForSelectItems")
                    $("body").append(appdiv)
                    var st = $("body").scrollTop();
                    $("body").animate({scrollTop:st+addHeight},500)
                }
                $(obj).find(".HselectShowAreaHuangZhanWei").offset({
                    top:tp,
                    left:pleft
                })

            }else{
                $(obj).find("li").closest("div").hide();
                $(obj).find(".HshowSelectValue i").css({
                    "border-color":"#888 transparent transparent transparent",
                    "border-width":"5px 4px 0px 4px"
                })

                $("body").find(".Hzwy23FillBodyForSelectItems").animate({height:'0px'},500,function(){
                    $(this).remove()
                })
            }
        })

        $(document).on('click',function(){
            $(obj).find("li").closest("div").hide();
            $(obj).find(".HshowSelectValue i").css({
                "border-color":"#888 transparent transparent transparent",
                "border-width":"5px 4px 0px 4px"
            })
            $("body").find(".Hzwy23FillBodyForSelectItems").animate({height:'0px'},500,function(){
                $(this).remove()
            })
        })
    }
}(jQuery));

