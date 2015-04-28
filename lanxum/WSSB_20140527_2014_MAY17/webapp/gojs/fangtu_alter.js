


//取得 xxx_1 命名方式ID的数字部分
function getElementNumber(obj) {
    var o = obj;
    var _pos = o.indexOf("_");
    if (_pos)
        return o.substring(_pos + 1);
    return "";
}

function selectZhengCe() {
	var args = selectZhengCe.arguments;
    var element = args[0];
    var elementNumber = getElementNumber(element.id);
    var target = 2;
    
    if ( args[1] ) target = args[1];
    
    var dest = $("ba_" + elementNumber);
    if ( element.value == target ) {
		$("bZCDiv_" + elementNumber).style.display = "block";
	} else {
		$("bZCDiv_" + elementNumber).style.display = "none";
	}
}

//
function show_m() {
    var args = show_m.arguments;
    var element = args[0];
    var elementNumber = getElementNumber(element.id);

    var dest = $("ba_" + elementNumber);

    if (dest) {
        if (element.checked) {
            dest.style.display = "block";
            $("ac_" + elementNumber).disabled = "on";
            changeColor( $("aa_" + elementNumber), "#C2FFFF" );
        }
        else {
            dest.style.display = "none";
            $("ac_" + elementNumber).disabled = "";
            changeColor( $("aa_" + elementNumber), "" );
        }
    }
}

function show_m1() {
	//alert("enter show_m1");
    var args = show_m1.arguments;
    var element = args[0];
    var elementNumber = getElementNumber(element.id);

    var dest = $("ba_" + elementNumber);

    if (dest) {
        if (element.checked) {
            dest.style.display = "block";
            $("ac_" + elementNumber).disabled = "on";
            changeColor( $("aa_" + elementNumber), "#C2FFFF" );
        }
        else {
        	if (  $("aa_" + elementNumber).name != "update" )
            	dest.style.display = "none";
            $("ac_" + elementNumber).disabled = "";
            changeColor( $("aa_" + elementNumber), "" );
        }
    }
}

// 新增数据的【修改】
function modify_new() {
	//alert("enter modify_new");
	
	var args = modify_new.arguments;
    var element = args[0];
    var elementNumber = getElementNumber(element.id);

	if (element.checked) {
	    $("ac_" + elementNumber).disabled = "on";
	    changeColor( $("aa_" + elementNumber), "#C2FFFF" );
	    $("ad_" + elementNumber).innerHTML = "修改";
	}
	else {
	    $("ac_" + elementNumber).disabled = "";
	    changeColor( $("aa_" + elementNumber), "" );
	    $("ad_" + elementNumber).innerHTML = "后";
	}
}

/**
标记当前记录为删除
1. 如果checked，标记【修改】为disable
2. 如果取消check，标记【修改】enable
*/
function delete_m() {
	
    var args = delete_m.arguments;
    var element = args[0];
    var num = getElementNumber(element.id);

    var my_aa = $("aa_" + num);
    var my_ab = $("ab_" + num);
    var my_ad = $("ad_" + num);

    if (element.checked) {
        if (my_ab) my_ab.disabled = "on";
        changeColor( my_aa, "#EA8A8C" );
        my_ad.innerHTML = "删除";
    }
    else {
        if (my_ab) my_ab.disabled = "";

        if ( my_aa.name == "new" ) {
            changeColor( my_aa, "lightskyblue" );
            my_ad.innerHTML = "新增";
        } else if ( my_aa.name == "old" ) {
            changeColor( my_aa, "" );
            my_ad.innerHTML = "前";
        } else if ( my_aa.name == "old_new" ) {
            changeColor( my_aa, "" );
            my_ad.innerHTML = "后";
        } else if ( my_aa.name == "update" ) {
            changeColor( my_aa, "" );
            my_ad.innerHTML = "后";
        }
    }
}

function changeColor(object, color) {
    if ( object.tagName == "TD" ) {
        object.style.backgroundColor = color;
    }
    if ( object.tagName == "TR" ) {
        for ( var i=0; i<object.childNodes.length; i++) {
            var td = object.childNodes[i];
            td.style.backgroundColor = color;
        }
    }
}

    constLabelSeparator = "!^";
//组装table数据
function serialize(tableObject, LabelSeparator, ColSeparator, RowSeparator) {
	//alert("serialize()");
    var oTable = $(tableObject);
    //alert( oTable );
    var oRows = oTable.tBodies[0].rows;
    var i = 0, j = 0;
    var submitData = "";

    for (i = 0; i < oRows.length; i++) {
        //解析 input element
        var oInputs = oRows[i].getElementsByTagName("input");
        for (j = 0; j < oInputs.length; j++) {
            var value = oInputs[j].value;
            if (oInputs[j].type == "checkbox") {
                if (oInputs[j].checked) value = "1";
                else value = "0";
            }
            if (oInputs[j].type == "radio") {
                if (oInputs[j].checked) value = "1";
                else value = "0";
            }
            submitData += oInputs[j].name + LabelSeparator + value + ColSeparator;
        }

        //解析 select element
        var oSelects = oRows[i].getElementsByTagName("select");
        for (j = 0; j < oSelects.length; j++) {
            submitData += oSelects[j].name + LabelSeparator + oSelects[j].value + ColSeparator;
        }

        //解析 textarea element
        var oTextAreas = oRows[i].getElementsByTagName("textarea");
        for (j = 0; j < oTextAreas.length; j++) {
            submitData += oTextAreas[j].name + LabelSeparator + oTextAreas[j].value + ColSeparator;
        }

        submitData += RowSeparator;
    }

    //document.writeln(submitData);
    //alert(submitData);
    return submitData;
}
function formatData(obj) {
	//alert("formatData()");
	return serialize(obj, constLabelSeparator, constColSeparator, constRowSeparator);
	//return serialize(obj, ":", " | ", "\n");
}
function doAlter() {
//alert("doAlter...");
	//拼成一个串, 提交
	$("submitData").value = formatData("theTable");
	
	doSave1();
	
}

function doSave1() {
	document.forms[0].submit();
}

function change_alter(target) {

	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = target;
		//doAlterSubmit();
		doAlterCheck();
	}
}

//查找对应的政策正文
function queryZhengce(targetNumber) {
	try {
		var targetName = "list["+targetNumber+"].alterVO.jmzcdm";
		//alert("targetName: " + targetName);
		var targets = document.getElementsByName(targetName);
		if ( targets && targets.length > 0 ) {
			var target = targets[0];
			//alert("target:" + target);
			if ( target.tagName == "SELECT" ) {
				//alert(target.value);
				if ( target.value != "" ) {
					var top = target.clientTop; //getElementTop(target);
					var left = target.clientLeft; //getElementLeft(target);
					//alert(top + ":" + left);
					var features = "height=300, width=300, toolbar=no, titlebar=no"
						+ ", status=no, scrollbars=yes, resizable=no, menubar=no"
						//+ ", left="+left+", top="+top
						//+ ", screenX="+left+", screenY="+top+""
						;
					window.open("zhengceAction.do?actionType=Init&jmzc.jmzcdm="+target.value,"zhengce",features);
					
				}
			}
		}
	} catch(e) { 
		//alert(e);
	}
}

function getElementTop( obj ) {
	try {
		var t = obj.offsetTop;
		while(obj = obj.offsetParent) t += obj.offsetTop;
		return t;
	}
	catch(e) {
		return 0;
	}
}

function getElementLeft( obj ) {
	try {
		var t = obj.offsetLeft;
		while(obj = obj.offsetParent) t += obj.offsetLeft;
		return t;
	}
	catch(e) {
		return 0;
	}
}
