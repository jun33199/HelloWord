
var isDOM = (document.getElementById ? true : false); 
var isIE4 = ((document.all && !isDOM) ? true : false);
var isNS4 = (document.layers ? true : false);
function getRef(id) {
if (isDOM) return document.getElementById(id);
if (isIE4) return document.all[id];
if (isNS4) return document.layers[id];
}function getSty(id) {
return (isNS4 ? getRef(id) : getRef(id).style);
} 
// Hide timeout.
var popTimer = 0;
// Array showing highlighted menu items.
var litNow = new Array();
function popOver(menuNum, itemNum) {
clearTimeout(popTimer);
hideAllBut(menuNum);
litNow = getTree(menuNum, itemNum);
changeCol(litNow, true);
targetNum = menu[menuNum][itemNum].target;
if (targetNum > 0) {
thisX = parseInt(menu[menuNum][0].ref.left) + parseInt(menu[menuNum][itemNum].ref.left);
thisY = parseInt(menu[menuNum][0].ref.top) + parseInt(menu[menuNum][itemNum].ref.top);
with (menu[targetNum][0].ref) {
left = parseInt(thisX + menu[targetNum][0].x);
top = parseInt(thisY + menu[targetNum][0].y);
visibility = 'visible';
      }
   }
}
function popOut(menuNum, itemNum) {
if ((menuNum == 0) && !menu[menuNum][itemNum].target)
hideAllBut(0)
else
popTimer = setTimeout('hideAllBut(0)', 500);
}
function getTree(menuNum, itemNum) {

// Array index is the menu number. The contents are null (if that menu is not a parent)
// or the item number in that menu that is an ancestor (to light it up).
itemArray = new Array(menu.length);

while(1) {
itemArray[menuNum] = itemNum;
// If we've reached the top of the hierarchy, return.
if (menuNum == 0) return itemArray;
itemNum = menu[menuNum][0].parentItem;
menuNum = menu[menuNum][0].parentMenu;
   }
}

// Pass an array and a boolean to specify colour change, true = over colour.
function changeCol(changeArray, isOver) {
for (menuCount = 0; menuCount < changeArray.length; menuCount++) {
if (changeArray[menuCount]) {
newCol = isOver ? menu[menuCount][0].overCol : menu[menuCount][0].backCol;
// Change the colours of the div/layer background.
with (m謀趌趎趍趍讍蜀蒅???enu[menuCount][changeArray[menuCount]].ref) {
if (isNS4) bgColor = newCol;
else backgroundColor = newCol;
         }
      }
   }
}
function hideAllBut(menuNum) {
var keepMenus = getTree(menuNum, 1);
for (count = 0; count < menu.length; count++)
if (!keepMenus[count])
menu[count][0].ref.visibility = 'hidden';
changeCol(litNow, false);
}

// *** MENU CONSTRUCTION FUNCTIONS ***

function Menu(isVert, popInd, x, y, width, overCol, backCol, borderClass, textClass) {
// True or false - a vertical menu?
this.isVert = isVert;
// The popout indicator used (if any) for this menu.
this.popInd = popInd
// Position and size settings.
this.x = x;
this.y = y;
this.width = width;
// Colours of menu and items.
this.overCol = overCol;
this.backCol = backCol;
// The stylesheet class used for item borders and the text within items.
this.borderClass = borderClass;
this.textClass = textClass;
// Parent menu and item numbers, indexed later.
this.parentMenu = null;
this.parentItem = null;
// Reference to the object's style properties (set later).
this.ref = null;
}

function Item(text, href, frame, length, spacing, target) {
this.text = text;
this.href = href;
this.frame = frame;
this.length = length;
this.spacing = spacing;
this.target = target;
// Reference to the object's style properties (set later).
this.ref = null;
}

function writeMenus() {
if (!isDOM && !isIE4 && !isNS4) return;

for (currMenu = 0; currMenu < menu.length; currMenu++) with (menu[currMenu][0]) {
// Variable for holding HTML for items and positions of next item.
var str = '', itemX = 0, itemY = 0;

// Remember, items start from 1 in the array (0 is menu object itself, above).
// Also use properties of each item nested in the other with() for construction.
for (currItem = 1; currItem < menu[currMenu].length; currItem++) with (menu[currMenu][currItem]) {
var itemID = 'menu' + currMenu + 'item' + currItem;

// The width and height of the menu item - dependent on orientation!
var w = (isVert ? width : leng謀趌趎趍趍讍蜀蒅???th);
var h = (isVert ? length : width);

// Create a div or layer text string with appropriate styles/properties.
// Thanks to Paul Maden (www.jojoo.net) for helping debug this in IE4, apparently
// the width must be a miniumum of 3 for it to work in that browser.
if (isDOM || isIE4) {
str += '<div id="' + itemID + '" style="position: absolute; left: ' + itemX + '; top: ' + itemY + '; width: ' + w + '; height: ' + h + '; visibility: inherit; ';
if (backCol) str += 'background: ' + backCol + '; ';
str += '" ';
}
if (isNS4) {
str += '<layer id="' + itemID + '" left="' + itemX + '" top="' + itemY + '" width="' +  w + '" height="' + h + '" visibility="inherit" ';
if (backCol) str += 'bgcolor="' + backCol + '" ';
}
if (borderClass) str += 'class="' + borderClass + '" ';

// Add mouseover handlers and finish div/layer.
str += 'onMouseOver="popOver(' + currMenu + ',' + currItem + ')" onMouseOut="popOut(' + currMenu + ',' + currItem + ')">';

// Add contents of item (default: table with link inside).
// In IE/NS6+, add padding if there's a border to emulate NS4's layer padding.
// If a target frame is specified, also add that to the <a> tag.

str += '<table width="' + (w - 8) + '" border="0" cellspacing="0" cellpadding="' + (!isNS4 && borderClass ? 3 : 0) + '"><tr><td align="left" height="' + (h - 7) + '">' + '<a class="' + textClass + '" href="' + href + '"' + (frame ? ' target="' + frame + '">' : '>') + text + '</a></td>';
if (target > 0) {

// Set target's parents to this menu item.
menu[target][0].parentMenu = currMenu;
menu[target][0].parentItem = currItem;

// Add a popout indicator.
if (popInd) str += '<td class="' + textClass + '" align="right">' + popInd + '</td>';
}
str += '</tr></table>' + (isNS4 ? '</layer>' : '</div>');
if (isVert) itemY += length + spacing;
else itemX += length + spacing;
}
if (isDOM) {
var newDiv = document.createElement('div');
document.getElementsByTagName('body').item(0).appendChild(newDiv);
newDiv.innerHTML = str;
ref = newDiv.style;
ref.posit謀趌趎趍趍讍蜀蒅???ion = 'absolute';
ref.visibility = 'hidden';
}

// Insert a div tag to the end of the BODY with menu HTML in place for IE4.
if (isIE4) {
document.body.insertAdjacentHTML('beforeEnd', '<div id="menu' + currMenu + 'div" ' + 'style="position: absolute; visibility: hidden">' + str + '</div>');
ref = getSty('menu' + currMenu + 'div');
}

// In NS4, create a reference to a new layer and write the items to it.
if (isNS4) {
ref = new Layer(0);
ref.document.write(str);
ref.document.close();
}

for (currItem = 1; currItem < menu[currMenu].length; currItem++) {
itemName = 'menu' + currMenu + 'item' + currItem;
if (isDOM || isIE4) menu[currMenu][currItem].ref = getSty(itemName);
if (isNS4) menu[currMenu][currItem].ref = ref.document[itemName];
   }
}
with(menu[0][0]) {
ref.left = x;
ref.top = y;
ref.visibility = 'visible';
   }
}

// Syntaxes: *** START EDITING HERE, READ THIS SECTION CAREFULLY! ***
//
// menu[menuNumber][0] = new Menu(Vertical menu? (true/false), 'popout indicator', left, top,
// width, 'mouseover colour', 'background colour', 'border stylesheet', 'text stylesheet');
//
// Left and Top are measured on-the-fly relative to the top-left corner of its trigger, or
// for the root menu, the top-left corner of the page.
//
// menu[menuNumber][itemNumber] = new Item('Text', 'URL', 'target frame', length of menu item,
//  additional spacing to next menu item, number of target menu to popout);
//
// If no target menu (popout) is desired, set it to 0. Likewise, if your site does not use
// frames, pass an empty string as a frame target.
//
// Something that needs explaining - the Vertical Menu setup. You can see most menus below
// are 'true', that is they are vertical, except for the first root menu. The 'length' and
// 'width' of an item depends on its orientation -- length is how long the item runs for in
// the direction of the menu, and width is the lateral dimension of the menu. Just look at
// the examples and tweak the numbers, they'll make sense eventually :).
謀趌趎趍趍讍蜀蒅???
var menu = new Array();

// Default colours passed to most menu constructors (just passed to functions, not
// a global variable - makes things easier to change later in bulk).
var defOver = '#F9EDC8', defBack = '#DCE6EB';

// Default 'length' of menu items - item height if menu is vertical, width if horizontal.
var defLength = 25;

// Menu 0 is the special, 'root' menu from which everything else arises.
menu[0] = new Array();
// A non-vertical menu with a few different colours and no popout indicator, as an example.
// *** MOVE ROOT MENU AROUND HERE ***  it's positioned at (5, 0) and is 17px high now.
menu[0][0] = new Menu(true, '', 556, 78, 65, '#ffffff', '#ffffff', 'crazyBorder', 'itemText');
// Notice how the targets are all set to nonzero values...
// The 'length' of each of these items is 40, and there is spacing of 10 to the next item.
// Most of the links are set to '#' hashes, make sure you change them to actual files.
menu[0][1] = new Item('功能导航', '#', '', 14, 1, 1);
// An example of a link with a target frame/window as well...

// File menu.
menu[1] = new Array();
// The File menu is positioned 0px across and 22 down from its trigger, and is 80 wide.
// All text in this menu has the stylesheet class 'item' -- see the <style> section above.
// We've passed a 'greater-than' sign '>' as a popout indicator. Try an image...?
menu[1][0] = new Menu(true, '', -15, 25, 110, defOver, defBack, 'itemBorder', 'itemText');
menu[1][1] = new Item('<< 税收征管系统', '#', '', defLength, 0, 2);
menu[1][2] = new Item(' <<   行政办公系统', '#', '', defLength, 0, 0);
// Non-zero target means this will trigger a popout -- menu[4] which is the 'Reopen' menu.
menu[1][3] = new Item('<< 网上服务系统', '#', '', defLength, 0, 5);
menu[1][4] = new Item('<< 辅助决策系统', '#', '', defLength, 0, 4);

// Edit menu.
menu[2] = new Array();
menu[2][0] = new Menu(true, '', -91, 6, 90, defOver, defBack, 'itemBorder', 'itemText');
menu[2][1] = new Item('<< 税收登记', '#', '', defLength, 0, 3);
menu[2][2] = new Item('   申报征收', 'she謀趌趎趍趍讍蜀蒅???nbao-gzhshx-02.htm', 'blank', defLength, 0, 0);
menu[2][3] = new Item('<< 涉税审批', '#', '', defLength, 0, 7);
menu[2][4] = new Item('<< 纳税评估', '#', '', defLength, 0, 6);
// Help menu
menu[3] = new Array();
menu[3][0] = new Menu(true, '', -93, 6, 92, defOver, defBack, 'itemBorder', 'itemText');
menu[3][1] = new Item('<< 开业登记', '#', '', defLength, 0, 10);
menu[3][2] = new Item('变更登记', '#', '', defLength, 0, 0);
menu[3][3] = new Item('停业复业登记', '#', '', defLength, 0, 0);
menu[3][4] = new Item('注销登记', '#', '', defLength, 0, 0);

// Reopen menu
menu[4] = new Array();
// This is across but not down... a horizontal popout (with crazy stylesheets :)...
menu[4][0] = new Menu(true, '', -119, 6, 118, defOver, defBack, 'itemBorder', 'itemText');
// These items are lengthier than normal, and have extra spacing due to the fancy borders.
menu[4][1] = new Item('01辅助决策系统-01', '#', '', defLength, 0, 0);
menu[4][2] = new Item('01辅助决策系统-02', '#', '', defLength, 0, 0);
menu[4][3] = new Item('01辅助决策系统-03', '#', '', defLength, 0, 0);

// Help About popout
menu[5] = new Array();
// Leftwards popout with a negative x and y relative to its trigger.
menu[5][0] = new Menu(true, '', -111, 0, 110, defOver, defBack, 'itemBorder', 'itemText');
menu[5][1] = new Item('01网上服务-01', '#', '', defLength, 0, 0);
menu[5][2] = new Item('01网上服务-02', '#', '', defLength, 0, 0);
menu[5][3] = new Item('01网上服务-03', '#', '', defLength, 0, 0);
menu[6] = new Array();
menu[6][0] = new Menu(true, '', -86, 6, 85, defOver, defBack, 'itemBorder', 'itemText');
menu[6][1] = new Item('生成告知书', '#', '', defLength, 0, 0);
menu[6][2] = new Item('审批', '#', '_new', defLength, 0, 0);
menu[6][3] = new Item('听证', '#', '_new', defLength, 0, 0);
menu[6][4] = new Item('处罚决定', '#', '_new', defLength, 0, 0);
menu[6][5] = new Item('查询', '#', '_new', defLength, 0, 0);
menu[7] = new Array();
// This is across but not down... a horizontal popout (with crazy stylesheets :)...
menu[7][0] = new Menu(true, '', -95, 6, 94, defOver, defBack, 'itemBorder', 'itemT謀趌趎趍趍讍蜀蒅???ext');
// These items are lengthier than normal, and have extra spacing due to the fancy borders.
menu[7][1] = new Item('<< 核准审批', '#', '', defLength, 0, 8);
menu[7][2] = new Item('   减免审批', '#', '', defLength, 0, 0);
menu[7][3] = new Item('   延期缴纳审批', '#', '', defLength, 0, 0);
menu[7][4] = new Item('<< 退税审批', '#', '', defLength, 0, 9);
menu[7][5] = new Item('   审批查询进度', '#', '', defLength, 0, 0);
menu[8] = new Array();
menu[8][0] = new Menu(true, '', -101, 6, 100, defOver, defBack, 'itemBorder', 'itemText');
// These items are lengthier than normal, and have extra spacing due to the fancy borders.
menu[8][1] = new Item('特殊企业认定', '#', '', defLength, 0, 0);
menu[8][2] = new Item('征收方式鉴定', '#', '', defLength, 0, 0);
menu[9] = new Array();
menu[9][0] = new Menu(true, '', -101, 6, 100, defOver, defBack, 'itemBorder', 'itemText');
// These items are lengthier than normal, and have extra spacing due to the fancy borders.
menu[9][1] = new Item('退税款审批', '#', '', defLength, 0, 0);
menu[9][2] = new Item('退手续费审批', '#', '', defLength, 0, 0);
menu[10] = new Array();
menu[10][0] = new Menu(true, '', -101, 6, 100, defOver, defBack, 'itemBorder', 'itemText');
// These items are lengthier than normal, and have extra spacing due to the fancy borders.

menu[10][1] = new Item('登记申请', '../html/kydj_query.htm', '', defLength, 0, 0);
menu[10][2] = new Item('受理申请', '../html/kydj_shouli_liebiao.htm', '', defLength, 0, 0);// *** OPTIONAL CODE FROM HERE DOWN ***

// These two lines handle the window resize bug in NS4. See <body onResize="...">.
// I recommend you leave this here as otherwise when you resize NS4's width menus are hidden.

var popOldWidth = window.innerWidth;
nsResizeHandler = new Function('if (popOldWidth != window.innerWidth) location.reload()');


// This is a quick snippet that captures all clicks on the document and hides the menus
// every time you click. Use if you want.

if (isNS4) document.captureEvents(Event.CLICK);
document.onclick = clickHandle;

function clickHandle(evt)
{
 if (isNS4) document.routeEvent(evt);
 h謀趌趎趍趍讍蜀蒅???ideAllBut(0);
}


// This is just the moving command for the example.

function moveRoot()
{
 with(menu[0][0].ref) left = ((parseInt(left) < 100) ? 100 : 5);
}
//  End -->
