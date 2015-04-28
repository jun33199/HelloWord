//<input type="text" size="20" 
//onfocus="fPopCalendar_date(this,this,this.value);return false;" />
//<input type="text" size="20" 
//onfocus="fPopCalendar_time(this,this,this.value);return false;" />
// 不能把两个 calendar 靠太近，如果覆盖了就会显示不正常


var _gdCtrl = new Object();
var _goSelectTag = new Array();
var _gcGray   = "#aaaaaa";
var _gcToggle = "#FB8664";
var _gcBG = "#eeeeee";

var _previousObject = null;
var _gdCurDate = new Date();
var _giYear = _gdCurDate.getFullYear();
var _giMonth = _gdCurDate.getMonth()+1;
var _giDay = _gdCurDate.getDate();

function _fSetDate(_iYear, _iMonth, _iDay){
var _VicPopCal = document.getElementById('_VicPopCal');
  _VicPopCal.style.visibility = "hidden";
  if ((_iYear == 0) && (_iMonth == 0) && (_iDay == 0)){
   _gdCtrl.value = "";
  }else{
   _iMonth = _iMonth + 100 + "";
   _iMonth = _iMonth.substring(1);
 _iDay   = _iDay + 100 + "";
  _iDay   = _iDay.substring(1);
   if(_gdCtrl.tagName == "INPUT"){
      _gdCtrl.value = _iYear+"-"+_iMonth+"-"+_iDay;
   }else{
      _gdCtrl.innerText = _iYear+"-"+_iMonth+"-"+_iDay;
   }
  }
 
  for (i in _goSelectTag)
   _goSelectTag[i].style.visibility = "visible";
  _goSelectTag.length = 0;
  
  window.returnValue=_gdCtrl.value;
  //window.close();
}
function _HiddenDiv()
{
var _VicPopCal = document.getElementById('_VicPopCal');
 var i;
  _VicPopCal.style.visibility = "hidden";
  for (i in _goSelectTag)
   _goSelectTag[i].style.visibility = "visible";
  _goSelectTag.length = 0;
}
function _fSetSelected(_aCell){
var _tbSelMonth1 = document.getElementById('_tbSelMonth');
var _tbSelYear1 = document.getElementById('_tbSelYear');
  var _iOffset = 0;
  var _iYear = parseInt(_tbSelYear1.value);
  var _iMonth = parseInt(_tbSelMonth1.value);
  
  _aCell.bgColor = _gcBG;
 // with (_aCell.children["_cellText"]){
  with (_aCell.childNodes[0]){
   var _iDay = parseInt(innerHTML);
   if (color==_gcGray)
  _iOffset = (Victor<10)?-1:1;
 _iMonth += _iOffset;
 if (_iMonth<1) {
  _iYear--;
  _iMonth = 12;
 }else if (_iMonth>12){
  _iYear++;
  _iMonth = 1;
 }
  }
  _fSetDate(_iYear, _iMonth, _iDay);
}
function _Point(iX, iY){
 this.x = iX;
 this.y = iY;
}
function _fBuildCal(_iYear, _iMonth) {
  var _aMonth=new Array();
  for(i=1;i<7;i++)
   _aMonth[i]=new Array(i);
    
  var _dCalDate=new Date(_iYear, _iMonth-1, 1);
  var _iDayOfFirst=_dCalDate.getDay();
  var _iDaysInMonth=new Date(_iYear, _iMonth, 0).getDate();
  var _iOffsetLast=new Date(_iYear, _iMonth-1, 0).getDate()-_iDayOfFirst+1;
  var _iDate = 1;
  var _iNext = 1;
  for (d = 0; d < 7; d++)
 _aMonth[1][d] = (d<_iDayOfFirst)?-(_iOffsetLast+d):_iDate++;
  for (w = 2; w < 7; w++)
   for (d = 0; d < 7; d++)
  _aMonth[w][d] = (_iDate<=_iDaysInMonth)?_iDate++:-(_iNext++);
  return _aMonth;
}
function _fDrawCal(_iYear, _iMonth, iCellHeight, sDateTextSize) {
  //var WeekDay = new Array("S","M","T","W","T","W","S");
  var WeekDay = new Array("日","一","二","三","四","五","六");
  var styleTD = " bgcolor='"+_gcBG+"' bordercolor='"+_gcBG+"' valign='middle' align='center' height='"+iCellHeight+"' style='font:b arial "+sDateTextSize+";";            //Coded by Hcy email:hcy110@263.net
  with (document) {
 write("<tr>");
 for(i=0; i<7; i++){
  write("<td "+styleTD+"color:maroon' >"+ WeekDay[i] + "</td>");
 }
 write("</tr>");
   for (w = 1; w < 7; w++) {
  write("<tr>");
  for (d = 0; d < 7; d++) {
   write("<td id='_calCell' "+styleTD+"cursor:pointer;' onMouseOver='this.bgColor=_gcToggle' onMouseOut='this.bgColor=_gcBG' onclick='_fSetSelected(this)'>");
   write("<font id='_cellText' name='_cellText' Victor='Hcy_Flag'> </font>");  
   write("</td>");
  }
  write("</tr>");
 }
  }
}
function _fUpdateCal() {
_iYear = document.getElementById('_tbSelYear').value;
_iMonth = document.getElementById('_tbSelMonth').value;
  myMonth = _fBuildCal(_iYear, _iMonth);
  
  var _cellText = document.getElementsByName('_cellText');
  var i = 0;
  for (w = 0; w < 6; w++)
 for (d = 0; d < 7; d++)
  with (_cellText[(7*w)+d]) {
   Victor = i++;
   if (myMonth[w+1][d]<0) {
    color = _gcGray;
    innerHTML = -myMonth[w+1][d];
   }else{
    color = ((d==0)||(d==6))?"red":"black";
    innerHTML = myMonth[w+1][d];
   }
  }
  
}
function _fSetYearMon(_iYear, _iMon){
  var _tbSelMonth = document.getElementById('_tbSelMonth');
  _tbSelMonth.options[_iMon-1].selected = true;
  var _tbSelYear = document.getElementById('_tbSelYear');
  for (i = 0; i < _tbSelYear.length; i++)
 if (_tbSelYear.options[i].value == _iYear)
  _tbSelYear.options[i].selected = true;
 
  _fUpdateCal(_iYear, _iMon);
  
}
function _fPrevMonth(){
var _tbSelYear = document.getElementById('_tbSelYear');
var _tbSelMonth = document.getElementById('_tbSelMonth');
  var _iMon = _tbSelMonth.value;
  var _iYear = _tbSelYear.value;
  
  if (--_iMon<1) {
   _iMon = 12;
   _iYear--;
  }
  
  _fSetYearMon(_iYear, _iMon);
}
function _fNextMonth(){
var _tbSelMonth = document.getElementById('_tbSelMonth');
var _tbSelYear = document.getElementById('_tbSelYear');
  var _iMon = _tbSelMonth.value;
  var _iYear = _tbSelYear.value;
  
  if (++_iMon>12) {
   _iMon = 1;
   _iYear++;
  }
  
  _fSetYearMon(_iYear, _iMon);
}
function _fToggleTags(){
var select = document.getElementsByTagName("SELECT");
var isIE4 = ((navigator.userAgent.indexOf('Win')  != -1) && (navigator.userAgent.indexOf('MSIE') != -1) && (parseInt(navigator.appVersion) >= 4 ));
if (isIE4)
{
 for (i=0; i<select.length; i++)
    if ((select[i].Victor != '_Won') && _fTagInBound(select[i])){
     select[i].style.visibility = "hidden";
     _goSelectTag[_goSelectTag.length] = select[i];
    }
 with (document.all.tags("SELECT")){
   for (i=0; i<length; i++)
    if ((item(i).Victor!="_Won")&&_fTagInBound(item(i))){
     item(i).style.visibility = "hidden";
     _goSelectTag[_goSelectTag.length] = item(i);
    }
 }
}
}
function _fTagInBound(aTag){
var _VicPopCal = document.getElementById('_VicPopCal');
  with (_VicPopCal.style){
   var l = parseInt(left);
   var t = parseInt(top);
   var r = l+parseInt(width);
   var b = t+parseInt(height);
 var ptLT = _fGetXY(aTag);
 return !((ptLT.x>r)||(ptLT.x+aTag.offsetWidth<l)||(ptLT.y>b)||(ptLT.y+aTag.offsetHeight<t));
  }
}
function _fGetXY(aTag){
  var oTmp = aTag;
  var pt = new _Point(0,0);
  do {
   pt.x += oTmp.offsetLeft;
   pt.y += oTmp.offsetTop;
   oTmp = oTmp.offsetParent;
  } while(oTmp.tagName!="BODY");
  
  return pt;
}
// Main: popCtrl is the widget beyond which you want this calendar to appear;
//       dateCtrl is the widget into which you want to put the selected date.
// i.e.: <input type="text" name="dc" style="text-align:center" readonly><INPUT type="button" value="V" onclick="fPopCalendar(dc,dc);return false">
function fPopCalendar_date(popCtrl, dateCtrl,strDate){
var _VicPopCal = document.getElementById('_VicPopCal');
  if (popCtrl == _previousObject){
    if (_VicPopCal.style.visibility == "visible"){
    _HiddenDiv();
    return true;
   }
   
  }
 
  _previousObject = popCtrl;
  _gdCtrl = dateCtrl;
  _fInitialDate(strDate);
  
  _fSetYearMon(_giYear, _giMonth); 
  
  var point = _fGetXY(popCtrl);
  with (_VicPopCal.style) {
   left = point.x;
 top  = point.y+popCtrl.offsetHeight;
 width = _VicPopCal.offsetWidth;
 width = 210; //
 height = _VicPopCal.offsetHeight;
 _fToggleTags(point);  
 visibility = 'visible';
  }
  //在firefox下面必须带上px,否则会有异常
  _VicPopCal.style.left = point.x + "px";
  _VicPopCal.style.top = point.y + popCtrl.offsetHeight + "px";
}
// Added by Han Chen
function _fInitialDate(strDate){
 if( strDate == null || strDate.length != 10 )
  return false;
 var sYear  = strDate.substring(0,4);
 var sMonth = strDate.substring(5,7);
 var sDay   = strDate.substring(8,10);
 if( sMonth.charAt(0) == '0' ) { sMonth = sMonth.substring(1,2); }
 if( sDay.charAt(0)   == '0' ) { sDay   = sDay.substring(1,2);   }
 var nYear  = parseInt(sYear );
 var nMonth = parseInt(sMonth);
 var nDay   = parseInt(sDay  );
 
 if ( isNaN(nYear ) ) return false;
 if ( isNaN(nMonth) ) return false;
 if ( isNaN(nDay  ) ) return false;
 var arrMon = new Array(12);
 arrMon[ 0] = 31; arrMon[ 1] = nYear % 4 == 0 ? 29:28;
 arrMon[ 2] = 31; arrMon[ 3] = 30;
 arrMon[ 4] = 31; arrMon[ 5] = 30;
 arrMon[ 6] = 31; arrMon[ 7] = 31;
 arrMon[ 8] = 30; arrMon[ 9] = 31;
 arrMon[10] = 30; arrMon[11] = 31;
 if ( nYear  < 2010 || nYear > 2020 )   return false;
 if ( nMonth < 1 || nMonth > 12 )    return false;
 if ( nDay < 1 || nDay > arrMon[nMonth - 1] ) return false;
 _giYear  = nYear;
 _giMonth = nMonth;
 _giDay   = nDay;
 return true;
}
var _gMonths = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
with (document) {
write("<Div id='_VicPopCal' style='POSITION:absolute;VISIBILITY:hidden;border:0px ridge;z-index:100;'>");
write("<table border='1' bgcolor='#eeeeee'  style='FONT-SIZE: 10px;'>");
write("<TR>");
write("<td valign='middle' align='center'><input type='button' name='_PrevMonth' value='<' style='height:20;width:20;FONT:b' onClick='_fPrevMonth()'>");
write(" <SELECT name='_tbSelYear' id='_tbSelYear' onChange='_fUpdateCal()' Victor='_Won'>");
for(i=2011;i<2020;i++) 
 write("<OPTION value='"+i+"'>"+i+" </OPTION>");
write("</SELECT>");
write(" <select name='_tbSelMonth' id='_tbSelMonth' onChange='_fUpdateCal()' Victor='_Won'>");
for (i=0; i<12; i++)
 write("<option value='"+(i+1)+"'>"+_gMonths[i]+"</option>");
write("</SELECT>");
write(" <input type='button' name='_PrevMonth' value='>' style='height:20;width:20;FONT:b' onclick='_fNextMonth()'>");
write("</td>");
write("</TR><TR>");
write("<td align='center'>");
write("<DIV style='background-color:#ccccdd'><table width='100%' border='0'>");
_fDrawCal(_giYear, _giMonth, 20, '12');
write("</table></DIV>");
write("</td>");
write("</TR><TR><TD align='center'>");

write("<TABLE width='100%' style='FONT-SIZE: 14px;'>");
write("<tr>");
write("<td algin='center' width='78%' style='FONT-SIZE: 14px;'>");
write("<DIV style='cursor:pointer' onclick='_fSetDate(" + _giYear + "," + _giMonth + "," + _giDay + ")'");
write(" onMouseOver='this.style.color=_gcToggle' onMouseOut='this.style.color=0'>");
write("今天: "+_giYear+"-"+_giMonth+"-"+_giDay+"</DIV></td>");

write("<TD align='left' width='22%'>");
write("<DIV style='cursor:pointer' onclick='_HiddenDiv()' onMouseOver='this.style.color=_gcToggle'");
write(" onMouseOut='this.style.color=0'>关闭</DIV></td>");
write("</table></TABLE></Div>");
}

var gdCtrl = new Object();
var goSelectTag = new Array();
var gcGray   = "#aaaaaa";
var gcToggle = "#FB8664";
var gcBG = "#eeeeee";

var previousObject = null;
var gdCurDate = new Date();
var giYear = gdCurDate.getFullYear();
var giMonth = gdCurDate.getMonth()+1;
var giDay = gdCurDate.getDate();
var giHour = gdCurDate.getHours();
var giMin = gdCurDate.getMinutes();


function fUpdateTime(){
var VicPopCal = document.getElementById('VicPopCal');
  VicPopCal.style.visibility = "hidden";
  // format month and day
  var sMonth = giMonth;
  var sDay = giDay;
  if (giMonth < 10) sMonth = "0" + giMonth;
  if (giDay < 10) sDay = "0" + giDay;
  // get hour and minute
  var tbSelHour1 = document.getElementById('tbSelHour').value;
  var tbSelMin1 = document.getElementById('tbSelMin').value;
   if(gdCtrl.tagName == "INPUT"){
      gdCtrl.value = giYear+"-"+sMonth+"-"+sDay+" "+tbSelHour1+":"+tbSelMin1;
   }else{
      gdCtrl.innerText = giYear+"-"+sMonth+"-"+sDay+" "+tbSelHour1+":"+tbSelMin1;
   }
 
  for (i in goSelectTag)
   goSelectTag[i].style.visibility = "visible";
  goSelectTag.length = 0;
  
}
function fSetDate(iYear, iMonth, iDay){
var VicPopCal = document.getElementById('VicPopCal');
  VicPopCal.style.visibility = "hidden";
  if ((iYear == 0) && (iMonth == 0) && (iDay == 0)){
   gdCtrl.value = "";
  }else{
   iMonth = iMonth + 100 + "";
   iMonth = iMonth.substring(1);
 iDay   = iDay + 100 + "";
  iDay   = iDay.substring(1);
  // get hour and minute
  var tbSelHour1 = document.getElementById('tbSelHour').value;
  var tbSelMin1 = document.getElementById('tbSelMin').value;
   if(gdCtrl.tagName == "INPUT"){
      gdCtrl.value = iYear+"-"+iMonth+"-"+iDay+" "+tbSelHour1+":"+tbSelMin1;
   }else{
      gdCtrl.innerText = iYear+"-"+iMonth+"-"+iDay+" "+tbSelHour1+":"+tbSelMin1;
   }
  }
 
  for (i in goSelectTag)
   goSelectTag[i].style.visibility = "visible";
  goSelectTag.length = 0;

//  window.returnValue=tbSelHour1+":"+tbSelMin1;
  
  //window.close();
}
function HiddenDiv()
{
var VicPopCal = document.getElementById('VicPopCal');
 var i;
  VicPopCal.style.visibility = "hidden";
  for (i in goSelectTag)
   goSelectTag[i].style.visibility = "visible";
  goSelectTag.length = 0;
}
function fSetSelected(aCell){
var tbSelMonth1 = document.getElementById('tbSelMonth');
var tbSelYear1 = document.getElementById('tbSelYear');
  var iOffset = 0;
  var iYear = parseInt(tbSelYear1.value);
  var iMonth = parseInt(tbSelMonth1.value);
  
  aCell.bgColor = gcBG;
 // with (aCell.children["cellText"]){
  with (aCell.childNodes[0]){
   var iDay = parseInt(innerHTML);
   if (color==gcGray)
  iOffset = (Victor<10)?-1:1;
 iMonth += iOffset;
 if (iMonth<1) {
  iYear--;
  iMonth = 12;
 }else if (iMonth>12){
  iYear++;
  iMonth = 1;
 }
  }
  fSetDate(iYear, iMonth, iDay);
}
function Point(iX, iY){
 this.x = iX;
 this.y = iY;
}
function fBuildCal(iYear, iMonth) {
  var aMonth=new Array();
  for(i=1;i<7;i++)
   aMonth[i]=new Array(i);
    
  var dCalDate=new Date(iYear, iMonth-1, 1);
  var iDayOfFirst=dCalDate.getDay();
  var iDaysInMonth=new Date(iYear, iMonth, 0).getDate();
  var iOffsetLast=new Date(iYear, iMonth-1, 0).getDate()-iDayOfFirst+1;
  var iDate = 1;
  var iNext = 1;
  for (d = 0; d < 7; d++)
 aMonth[1][d] = (d<iDayOfFirst)?-(iOffsetLast+d):iDate++;
  for (w = 2; w < 7; w++)
   for (d = 0; d < 7; d++)
  aMonth[w][d] = (iDate<=iDaysInMonth)?iDate++:-(iNext++);
  return aMonth;
}
function fDrawCal(iYear, iMonth, iCellHeight, sDateTextSize) {
  //var WeekDay = new Array("S","M","T","W","T","W","S");
  var WeekDay = new Array("日","一","二","三","四","五","六");
  var styleTD = " bgcolor='"+gcBG+"' bordercolor='"+gcBG+"' valign='middle' align='center' height='"+iCellHeight+"' style='font:b arial "+sDateTextSize+";";            //Coded by Hcy email:hcy110@263.net
  with (document) {
 write("<tr>");
 for(i=0; i<7; i++){
  write("<td "+styleTD+"color:maroon' >"+ WeekDay[i] + "</td>");
 }
 write("</tr>");
   for (w = 1; w < 7; w++) {
  write("<tr>");
  for (d = 0; d < 7; d++) {
   write("<td id='calCell' "+styleTD+"cursor:pointer;' onMouseOver='this.bgColor=gcToggle' onMouseOut='this.bgColor=gcBG' onclick='fSetSelected(this)'>");
   write("<font id='cellText' name='cellText' Victor='Hcy_Flag'> </font>");  
   write("</td>");
  }
  write("</tr>");
 }
  }
}
function fUpdateCal() {
  iYear = document.getElementById('tbSelYear').value;
  iMonth = document.getElementById('tbSelMonth').value;
  giYear = iYear;
  giMonth = iMonth;

  myMonth = fBuildCal(iYear, iMonth);
  
  var cellText = document.getElementsByName('cellText');
  var i = 0;
  for (w = 0; w < 6; w++)
 for (d = 0; d < 7; d++)
  with (cellText[(7*w)+d]) {
   Victor = i++;
   if (myMonth[w+1][d]<0) {
    color = gcGray;
    innerHTML = -myMonth[w+1][d];
   }else{
    color = ((d==0)||(d==6))?"red":"black";
    innerHTML = myMonth[w+1][d];
   }
  }
  
}
function fSetYearMon(iYear, iMon){
  var tbSelMonth = document.getElementById('tbSelMonth');
  tbSelMonth.options[iMon-1].selected = true;
  var tbSelYear = document.getElementById('tbSelYear');
  for (i = 0; i < tbSelYear.length; i++)
 if (tbSelYear.options[i].value == iYear)
  tbSelYear.options[i].selected = true;
 
  fUpdateCal(iYear, iMon);
  
}
function fSetTime(iHour, iMin){
  var tbSelHour = document.getElementById('tbSelHour');
  tbSelHour.options[iHour].selected = true;
  var tbSelMin = document.getElementById('tbSelMin');
  tbSelMin.options[iMin].selected = true;
}
function fPrevMonth(){
var tbSelYear = document.getElementById('tbSelYear');
var tbSelMonth = document.getElementById('tbSelMonth');
  var iMon = tbSelMonth.value;
  var iYear = tbSelYear.value;
  
  if (--iMon<1) {
   iMon = 12;
   iYear--;
  }
  
  fSetYearMon(iYear, iMon);
}
function fNextMonth(){
var tbSelMonth = document.getElementById('tbSelMonth');
var tbSelYear = document.getElementById('tbSelYear');
  var iMon = tbSelMonth.value;
  var iYear = tbSelYear.value;
  
  if (++iMon>12) {
   iMon = 1;
   iYear++;
  }
  
  fSetYearMon(iYear, iMon);
}
function fToggleTags(){
var select = document.getElementsByTagName("SELECT");
var isIE4 = ((navigator.userAgent.indexOf('Win')  != -1) && (navigator.userAgent.indexOf('MSIE') != -1) && (parseInt(navigator.appVersion) >= 4 ));
if (isIE4)
{
 for (i=0; i<select.length; i++)
    if ((select[i].Victor != 'Won') && fTagInBound(select[i])){
     select[i].style.visibility = "hidden";
     goSelectTag[goSelectTag.length] = select[i];
    }
 with (document.all.tags("SELECT")){
   for (i=0; i<length; i++)
    if ((item(i).Victor!="Won")&&fTagInBound(item(i))){
     item(i).style.visibility = "hidden";
     goSelectTag[goSelectTag.length] = item(i);
    }
 }
}
}
function fTagInBound(aTag){
var VicPopCal = document.getElementById('VicPopCal');
  with (VicPopCal.style){
   var l = parseInt(left);
   var t = parseInt(top);
   var r = l+parseInt(width);
   var b = t+parseInt(height);
 var ptLT = fGetXY(aTag);
 return !((ptLT.x>r)||(ptLT.x+aTag.offsetWidth<l)||(ptLT.y>b)||(ptLT.y+aTag.offsetHeight<t));
  }
}
function fGetXY(aTag){
  var oTmp = aTag;
  var pt = new Point(0,0);
  do {
   pt.x += oTmp.offsetLeft;
   pt.y += oTmp.offsetTop;
   oTmp = oTmp.offsetParent;
  } while(oTmp.tagName!="BODY");
  
  return pt;
}
// Main: popCtrl is the widget beyond which you want this calendar to appear;
//       dateCtrl is the widget into which you want to put the selected date.
// i.e.: <input type="text" name="dc" style="text-align:center" readonly><INPUT type="button" value="V" onclick="fPopCalendar(dc,dc);return false">
function fPopCalendar_time(popCtrl, dateCtrl,strDate){
var VicPopCal = document.getElementById('VicPopCal');
  if (popCtrl == previousObject){
    if (VicPopCal.style.visibility == "visible"){
    HiddenDiv();
    return true;
   }
   
  }
 
  previousObject = popCtrl;
  gdCtrl = dateCtrl;
  fInitialDate(strDate);
  
  fSetYearMon(giYear, giMonth);
  fSetTime(giHour, giMin); 
  
  var point = fGetXY(popCtrl);
  with (VicPopCal.style) {
   left = point.x;
 top  = point.y+popCtrl.offsetHeight;
 width = VicPopCal.offsetWidth;
 width = 210; //
 height = VicPopCal.offsetHeight;
 fToggleTags(point);  
 visibility = 'visible';
  }
  //在firefox下面必须带上px,否则会有异常
  VicPopCal.style.left = point.x + "px";
  VicPopCal.style.top = point.y + popCtrl.offsetHeight + "px";
}
// Added by Han Chen
function fInitialDate(strDate){

 if( strDate == null || strDate.length != 16 )
  return false;
 var sYear  = strDate.substring(0,4);
 var sMonth = strDate.substring(5,7);
 var sDay   = strDate.substring(8,10);
 var sHour  = strDate.substring(11,13);
 var smin   = strDate.substring(14,16);
 if( sMonth.charAt(0) == '0' ) { sMonth = sMonth.substring(1,2); }
 if( sDay.charAt(0)   == '0' ) { sDay   = sDay.substring(1,2);   }
 if( sHour.charAt(0)  == '0' ) { sHour  = sHour.substring(1,2);   }
 if( smin.charAt(0)   == '0' ) { smin   = smin.substring(1,2);   }
 
 var nYear  = parseInt(sYear );
 var nMonth = parseInt(sMonth);
 var nDay   = parseInt(sDay  );
 var nHour  = parseInt(sHour);
 var nMin   = parseInt(smin  );
 
 if ( isNaN(nYear ) ) return false;
 if ( isNaN(nMonth) ) return false;
 if ( isNaN(nDay  ) ) return false;
 if ( isNaN(nHour ) ) return false;
 if ( isNaN(nMin  ) ) return false;
 
 var arrMon = new Array(12);
 arrMon[ 0] = 31; arrMon[ 1] = nYear % 4 == 0 ? 29:28;
 arrMon[ 2] = 31; arrMon[ 3] = 30;
 arrMon[ 4] = 31; arrMon[ 5] = 30;
 arrMon[ 6] = 31; arrMon[ 7] = 31;
 arrMon[ 8] = 30; arrMon[ 9] = 31;
 arrMon[10] = 30; arrMon[11] = 31;
 if ( nYear  < 2010 || nYear > 2020 )   return false;
 if ( nMonth < 1 || nMonth > 12 )    return false;
 if ( nDay < 1 || nDay > arrMon[nMonth - 1] ) return false;
 if ( nHour < 0 || nHour > 23 ) return false;
 if ( nMin < 0 || nMin > 59) return false;
 giYear  = nYear;
 giMonth = nMonth;
 giDay   = nDay;
 giHour  = nHour;
 giMin   = nMin;
 
 return true;
}
var gMonths = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
with (document) {
write("<Div id='VicPopCal' style='POSITION:absolute;VISIBILITY:hidden;border:0px ridge;z-index:100;'>");
write("<table border='1' bgcolor='#eeeeee'  style='FONT-SIZE: 10px;'>");
//
write("<TR><td style='FONT-SIZE: 14px;' align='center' valign='top'>");
write("小时 <select name='tbSelHour' id='tbSelHour' onChange='fUpdateCal()' Victor='Won'>");
for(i=0;i<10;i++) 
 write("<OPTION value='0"+i+"'>"+i+"</OPTION>");
for(i=10;i<24;i++) 
 write("<OPTION value='"+i+"'>"+i+"</OPTION>");
write("</SELECT>");
write("分钟 <select name='tbSelMin' id='tbSelMin' onChange='fUpdateCal()' Victor='Won'>");
for(i=0;i<10;i++) 
 write("<OPTION value='0"+i+"'>"+i+"</OPTION>");
for(i=10;i<60;i++) 
 write("<OPTION value='"+i+"'>"+i+"</OPTION>");
write("</SELECT>");
write("</td></tr>");
//

write("<TR>");
write("<td valign='middle' align='center'>");
write("<input type='button' name='PrevMonth' value='<' style='height:20;width:20;FONT:b' onClick='fPrevMonth()'>");
write(" <SELECT name='tbSelYear' id='tbSelYear' onChange='fUpdateCal()' Victor='Won'>");
for(i=2011;i<2020;i++) 
 write("<OPTION value='"+i+"'>"+i+" </OPTION>");
write("</SELECT>");
write(" <select name='tbSelMonth' id='tbSelMonth' onChange='fUpdateCal()' Victor='Won'>");
for (i=0; i<12; i++)
 write("<option value='"+(i+1)+"'>"+gMonths[i]+"</option>");
write("</SELECT>");
write(" <input type='button' name='PrevMonth' value='>' style='height:20;width:20;FONT:b' onclick='fNextMonth()'>");
write("</td>");
write("</TR><TR>");
write("<td align='center'>");
write("<DIV style='background-color:#ccccdd'><table width='100%' border='0'>");
fDrawCal(giYear, giMonth, 20, '12');
write("</table></DIV>");
write("</td>");
write("</TR><TR><TD align='center'>");

write("<TABLE width='100%' style='FONT-SIZE: 14px;'>");
write("<tr>");
write("<td algin='center' width='78%' style='FONT-SIZE: 14px;'>");
write("<DIV style='cursor:pointer' onclick='fSetDate(" + giYear + "," + giMonth + "," + giDay + ")'");
write(" onMouseOver='this.style.color=gcToggle' onMouseOut='this.style.color=0'>");
write("今天: "+giYear+"-"+giMonth+"-"+giDay+"</DIV></td>");

write("<TD align='left' width='22%'>");
write("<DIV style='cursor:pointer' onclick='fUpdateTime()'");
write(" onMouseOver='this.style.color=gcToggle'");
write(" onMouseOut='this.style.color=0'>确定</DIV></td>");
write("</tr>");
write("</table></TABLE></Div>");
}
