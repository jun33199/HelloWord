//系统功能菜单动态显示
//菜单
var menuOffX=0; //菜单距连接文字最左端距离
var menuOffY=25;	//菜单距连接文字顶端距离

var ie=document.all&&navigator.userAgent.indexOf("Opera")==-1;
var ns_ff=document.getElementById&&!document.all;

function showmenu(e,vmenu,mod)
{
	which=vmenu;
	menuobj=document.getElementById("popmenu");
	menuobj.thestyle=menuobj.style;
	menuobj.innerHTML=which;
	menuobj.contentwidth=menuobj.offsetWidth;
	eventX=e.clientX;
	eventY=e.clientY;
	var rightedge=document.body.clientWidth-eventX;
	var bottomedge=document.body.clientHeight-eventY;

	if (rightedge<menuobj.contentwidth)
		menuobj.thestyle.left=document.body.scrollLeft+eventX-menuobj.contentwidth+menuOffX;
	else
		menuobj.thestyle.left=ie? ie_x(event.srcElement)+menuOffX : ns_ff? window.pageXOffset+eventX : eventX;

	if (bottomedge<menuobj.contentheight&&mod!=0)
		menuobj.thestyle.top=document.body.scrollTop+eventY-menuobj.contentheight-event.offsetY+menuOffY-23;
	else
		menuobj.thestyle.top=ie? ie_y(event.srcElement)+menuOffY : ns_ff? window.pageYOffset+eventY+10 : eventY;

	menuobj.thestyle.visibility="visible";
}


function ie_y(e)
{
	var t=e.offsetTop;
	while(e=e.offsetParent)
	{
		t+=e.offsetTop;
	}
	return t;
}

function ie_x(e)
{
	var l=e.offsetLeft;
	while(e=e.offsetParent)
	{
		l+=e.offsetLeft;
	}
	return l;
}

function highlightmenu(e,state)
{
	if (document.all) source_el=event.srcElement;
	while(source_el.id!="popmenu")
	{
		source_el=document.getElementById? source_el.parentNode : source_el.parentElement;
		if (source_el.className=="menuitems")
		{
			source_el.id=(state=="on")? "mouseoverstyle" : "";
	    }
	}
}

function hidemenu()
{
    if (window.menuobj) menuobj.thestyle.visibility="hidden";
}

function dynamichide(e)
{
    if ((ie||ns_ff)&&!menuobj.contains(e.toElement))hidemenu();
}

document.onclick=hidemenu;
document.write("<div class=menuskin id=popmenu onmouseover=highlightmenu(event,'on') onmouseout=highlightmenu(event,'off');dynamichide(event)></div>");
//菜单END