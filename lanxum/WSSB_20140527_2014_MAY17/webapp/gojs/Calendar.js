var outObject;
function setday(tt,obj) 
{
  if (arguments.length >  2){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
  if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
  var dads  = document.all.epodDateLayer.style;
	var th = tt;
  var ttop  = tt.offsetTop;     
  var thei  = tt.clientHeight;  
  var tleft = tt.offsetLeft;    
  var ttyp  = tt.type;          
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = (arguments.length == 1) ? th : obj;
	window.calendar.setObject(outObject);
	dads.zindex = '0';
  dads.display = '';
  event.returnValue=false;
}    
function document.onclick() 
{ 
  with(window.event.srcElement)
  { if (getAttribute("Author")==null && tagName != "INPUT")
    document.all.epodDateLayer.style.display="none";
  }
}
function closeLayer()           
  {
    document.all.epodDateLayer.style.display="none";
  }
function document.onkeydown()
  {
    if (window.event.keyCode==27)document.all.epodDateLayer.style.display="none";
  }