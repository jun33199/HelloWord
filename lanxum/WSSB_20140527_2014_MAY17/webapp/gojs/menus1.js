    /*  The following statement must not be removed from this code:
        This code is copyright 1997 by Scott Isaacs. More information about this
        code can be found at Inside Dynamic HTML: HTTP://www.insideDHTML.com
        You are permitted use this code on non-profit sites as long as it is left unmodified. 
        This code cannot be changed, used on commercial sites, or reproduced in any manner without
        prior consent of Scott Isaacs - scotti@insideDHTML.com
		Optimized for Access Washington by David Unruh, dunruh@rxlpulitzer.com
    */

    var childActive = null 
    var menuActive = null
    var lastHighlight = null
    var active = false


    function getReal(el, menuID) {
      // Find a table cell element in the parent chain */
      temp = el
      if (temp.tagName=="TD")
        el = temp
        while ((temp.tagName!="TABLE") && (temp.className!="root") && (temp.id!=menuID)) {
       temp = temp.offsetParent
	   
        if (temp.tagName=="TD")
          el = temp
       
      }
      return el
    }

    function raiseMenu(el) {
      el.style.borderLeft = "0px #EEEEEE solid"
      el.style.borderTop = "0px #EEEEEE solid"
      el.style.borderRight = "0px gray solid"
      el.style.borderBottom = "0px gray solid"
    }
    function clearHighlight(el) {
      if (el==null) return
      el.style.borderRight = "0px lightgrey solid"
      el.style.borderBottom = "0px lightgrey solid"
      el.style.borderTop = "0px lightgrey solid"
      el.style.borderLeft = "0px lightgrey solid" 
    }

    function sinkMenu(el) {
      el.style.borderRight = "0px #EEEEEE solid"
      el.style.borderBottom = "0px #EEEEEE solid"
      el.style.borderTop = "0px gray solid"
      el.style.borderLeft = "0px gray solid"
    }
    function menuHandler(menuItem) {
      // Write generic menu handlers here!
      // Returning true collapses the menu. Returning false does not collapse the menu
      return true
    }
  
    function processClick(myMenu) {
      var el = getReal(event.srcElement, myMenu)
      if ((el.offsetParent.offsetParent.id==myMenu) && (active)) {        
        cleanupMenu(menuActive)
        clearHighlight(menuActive)
        active=false
        lastHighlight=null
        doHighlight(el)
      }
      else {
        if (el.className=="root")  
          doMenuDown(el)
        else {
          if (el._childItem==null) 
            el._childItem = getChildren(el)
          if (el._childItem!=null)  return;
          if ((el.id!="break") && (el.className!="disabled") && (el.className!="disabledhighlight") && (el.className!="clear"))  {
            if (menuHandler(el)) {
              cleanupMenu(menuActive)
              clearHighlight(menuActive)
              active=false
              lastHighlight=null
            }
          }
        }
      }
    }

    function doHighlight(el, myMenu) {
	el = getReal(el, myMenu)

	if ("root"==el.className) {
        if ((menuActive!=null) && (menuActive!=el)) {
          clearHighlight(menuActive)
        }
        if (!active) {
          raiseMenu(el)
        }          
        else {
          sinkMenu(el)}
        if ((active) && (menuActive!=el)) {
          cleanupMenu(menuActive)          
          doMenuDown(el)
        }
        menuActive = el  
        lastHighlight=null
      }
	  
      else {
        if (childActive!=null) 
          if (!childActive.contains(el)) 
            closeMenu(childActive, el)    
        if (("TD"==el.tagName) && ("clear"!=el.className)) {
          var ch = el.offsetParent.offsetParent          
          if (ch.active!=null) {
            if (ch.active!=el) {
              if (ch.active.className=="disabledhighlight")  
                ch.active.className="disabled"
              else
                ch.active.className=""
              }
            }
          ch.active = el
          lastHighlight = el
          if ((el.className=="disabled") || (el.className=="disabledhighlight") || (el.id=="break")) 
            el.className = "disabledhighlight"
          else {
            if (el.id!="break") {
              el.className = "highlight"
              if (el._childItem==null) 
                el._childItem = getChildren(el)
              if (el._childItem!=null) {
                doMenuDown(el)
              }
            }  
          }
        }
      }
    }

    function getChildren(el) {
      var tList = el.children.tags("TABLE")
      var i = 0
      while ((i < tList.length) && (tList[i].tagName!="TABLE"))
        i++
      if (i==tList.length)
        return null
      else
        return tList[i]
    }
    
    function doMenuDown(el) {
      if (el._childItem==null) 
        el._childItem = getChildren(el)
      if ((el._childItem!=null) && (el.className!="disabled") && (el.className!="disabledhighlight")) {
        // Performance Optimization - Cache child element
        ch = el._childItem
        if (ch.style.display=="block") {
          removeHighlight(ch.active)
          return
        }
        ch.style.display = "block"
        if (el.className=="root") {
          //ch.style.pixelTop = el.offsetParent.offsetTop
          ch.style.pixelLeft = el.offsetParent.offsetWidth
		  if (ch.style.pixelWidth == 0)
		      ch.style.pixelWidth = ch.offsetWidth+2
          sinkMenu(el)
          active = true
          menuActive = el
        } else {
          childActive = el
            ch.style.pixelLeft = el.offsetParent.offsetWidth
            ch.style.pixelTop = el.parentElement.offsetTop			
			if (ch.style.pixelWidth == 0)
		    	ch.style.pixelWidth = ch.offsetWidth+3
			
        }     
      }
    }

    
    function removeHighlight(el) {
      if (el!=null)
        if ((el.className=="disabled") || (el.className=="disabledhighlight"))  
          el.className="disabled"
        else
          el.className=""
    }
    
    function cleanupMenu(el) {
      if (el==null) return
      for (var i = 0; i < el.all.length; i++) {
        var item = el.all[i]
        if (item.tagName=="TABLE")
         item.style.display = ""
        removeHighlight(item.active)
        item.active=null
      }
    }

    function closeMenu(ch, el) {
      var start = ch

      while (ch.className != "root") {
          ch = ch.parentElement
          if (((!ch.contains(el)) && (ch.className!="root"))) {
            start=ch
          }
      }
      cleanupMenu(start)
    
	}

 
	function checkMenu() {
		var thisX = window.event.x; 
	        var el = window.event.srcElement;
		if (menuActive != null) {
			if (!menuActive.contains(el)) 
					if (thisX > 135) {
						clearHighlight(menuActive);
						closeMenu(menuActive);
						active = false;
						menuActive = null;
						choiceActive = null;
						window.event.cancelBubble = true;
					}
			}
 
	}

   function doCheckOut() {
      var el = event.toElement      
      if ((!active) && (menuActive!=null) && (!menuActive.contains(el))) {
        clearHighlight(menuActive)
        //menuActive=null
		closeMenu(menuActive)
      }
    }


    function processKey(myMenu) {
	  if (active) {
        switch (event.keyCode) {
         case 13: lastHighlight.click(); break;
         case 39:  // right
           if ((lastHighlight==null) || (lastHighlight._childItem==null)) {
             var idx = menuActive.cellIndex
             if (idx==menuActive.offsetParent.cells.length-2)
               idx = 0
             else
               idx++
             newItem = menuActive.offsetParent.cells[idx]
           } else
           {
             newItem = lastHighlight._childItem.rows[0].cells[0]
           }
           doHighlight(newItem)
           break; 
         case 37: //left
           if ((lastHighlight==null) || (lastHighlight.offsetParent.offsetParent.offsetParent.id==myMenu)) {
             var idx = menuActive.cellIndex
             if (idx==0)
               idx = menuActive.offsetParent.cells.length-2
             else
               idx--
             newItem = menuActive.offsetParent.cells[idx]
           } else
           {
             newItem = lastHighlight.offsetParent
             while (newItem.tagName!="TD")
               newItem = newItem.parentElement
           }
           doHighlight(newItem)
           break; 
         case 40: // down
            if (lastHighlight==null) {
              itemCell = menuActive._childItem
              curCell=0
              curRow = 0
            }
            else {
              itemCell = lastHighlight.offsetParent.offsetParent
              if (lastHighlight.cellIndex==lastHighlight.offsetParent.cells.length-1) {
                curCell = 0
                curRow = lastHighlight.offsetParent.rowIndex+1
                if (lastHighlight.offsetParent.rowIndex==itemCell.rows.length-1)
                  curRow = 0
              } else {
                curCell = lastHighlight.cellIndex+1
                curRow = lastHighlight.offsetParent.rowIndex
              }
            }
            doHighlight(itemCell.rows[curRow].cells[curCell])
            break;
         case 438: // up
            if (lastHighlight==null) {
              itemCell = menuActive._childItem
              curRow = itemCell.rows.length-1
              curCell= itemCell.rows[curRow].cells.length-1
            }
            else {
              itemCell = lastHighlight.offsetParent.offsetParent
              if (lastHighlight.cellIndex==0) {
                curRow = lastHighlight.offsetParent.rowIndex-1
                if (curRow==-1)
                  curRow = itemCell.rows.length-1
                curCell= itemCell.rows[curRow].cells.length-1

              } else {
                curCell = lastHighlight.cellIndex - 1
                curRow = lastHighlight.offsetParent.rowIndex
              }
            }
            doHighlight(itemCell.rows[curRow].cells[curCell])
            break;
           if (lastHighlight==null) {
              curCell = menuActive._childItem
              curRow = curCell.rows.length-1
            }
            else {
              curCell = lastHighlight.offsetParent.offsetParent
              if (lastHighlight.offsetParent.rowIndex==0)
                curRow = curCell.rows.length-1
              else
                curRow = lastHighlight.offsetParent.rowIndex-1
            }
            doHighlight(curCell.rows[curRow].cells[0])
            break;
        }
      }
    }
