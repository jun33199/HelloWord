var oCurrentBar=null;

// 2004-01-29
function clickOnBar(obj)
{
    var oID='div'+obj.id;
    try
    {
        if (oCurrentBar!=null) oCurrentBar.style.display='none';
    }
    catch (ex)
    {
        //alert("what???" + ex);
        // do nothing.
    }
    try
    {
        oCurrentBar=eval(oID);
        oCurrentBar.style.marginTop  =0;
        oCurrentBar.style.marginLeft =obj.offsetLeft+divNavBar.offsetLeft;
        oCurrentBar.style.display='block';
    }
    catch (ex)
    {
        //alert("what???" + ex);
        // do nothing.
    }
}

function clickOnEntity(entity)
{
    if(entity.open == "false")
    {
        expand(entity, true);
    }
    else
    {
        collapse(entity,true);
    }

    if(entity.url != null && entity.url != "#")
    {
        if (entity.url.indexOf("redirect/http://") >= 0)
        {
           parent.document.forms[0].target = "_blank";
        }
        else
        {
           parent.document.forms[0].target = "";
        }
                
        parent.document.forms[0].action = entity.url;
        parent.document.forms[0].jdid.value = entity.jdid;
        parent.document.forms[0].submit();
        
    }
    window.event.cancelBubble = true;
}

function expand(entity)
{
    var oImage;

    oImage = entity.childNodes(0).all["image"];
    oImage.src = entity.imageOpen;

    for(i=0; i < entity.childNodes.length; i++)
    {
        if(entity.childNodes(i).tagName == "DIV")
        {
            entity.childNodes(i).style.display = "block";
        }
    }
    entity.open = "true";
}

function collapse(entity)
{
    var oImage;
    var i;

    oImage = entity.childNodes(0).all["image"];
    oImage.src = entity.image;

    // collapse and hide children
    for(i=0; i < entity.childNodes.length; i++)
    {
        if(entity.childNodes(i).tagName == "DIV")
        {
            if(entity.id != "folderTree") entity.childNodes(i).style.display = "none";
            collapse(entity.childNodes(i));
        }
    }
    entity.open = "false";
}

function expandAll(entity)
{
    var oImage;
    var i;

    expand(entity, false);

    // expand children
    for(i=0; i < entity.childNodes.length; i++)
    {
        if(entity.childNodes(i).tagName == "DIV")
        {
            expandAll(entity.childNodes(i));
        }
    }
}

