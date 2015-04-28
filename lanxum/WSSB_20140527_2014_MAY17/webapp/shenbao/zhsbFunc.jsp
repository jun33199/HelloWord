<%@page import = "com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.client.SbjkmxData"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%!
/**
* \u5728\u9875\u9762\u4E0A\u5199\u51FA\u7EB3\u7A0E\u4EBA\u9009\u62E9\u7684\u5404\u79CD\u7A0E\u6B3E\u7C7B\u578B\u7684\u7A0E\u76EE\u4E2A\u6570
*/
List writeItemSize(String type, HttpSession session, JspWriter out) throws java.io.IOException
{
    if (type.equals(SessionKey.ZCJK_LIST))
    {
        out.print("var itemsize_zcjk = ");
    }
    else if (type.equals(SessionKey.BJQS_LIST))
    {
        out.print("var itemsize_bjqs = ");
    }
    else if (type.equals(SessionKey.SDJJ_LIST))
    {
        out.print("var itemsize_sdjj = ");
    }

    List list = (List)session.getAttribute(type);
    if (list == null)
    {
        out.println("-1;");
        return null;
    }
    out.println(list.size() + ";");

    return list;
}

/**
* \u5728\u9875\u9762\u4E0A\u8F93\u51FA\u4E3B\u7A0E\u4E0E\u9644\u52A0\u7A0E\u7684\u4F4D\u7F6E\u5173\u7CFB
* \u7B2C\u4E00\u4E2A\u662F\u4E3B\u7A0E\uFF0C\u540E\u9762\u7684\u662F\u9644\u52A0\u7A0E
*/
void writeFjsInfo(String type, HttpSession session, JspWriter out)throws java.io.IOException
{
    Map fjsInfo = (Map)session.getAttribute(type);
    if (fjsInfo == null)
    {
        return;
    }

    List list = null;
    if (type.equals(SessionKey.ZCJK_FJS_INFO))
    {
        out.print("var fssdyzs_zcjk = [");
        list = (List)session.getAttribute(SessionKey.ZCJK_LIST);
    }
    else if (type.equals(SessionKey.BJQS_FJS_INFO))
    {
        out.print("var fssdyzs_bjqs = [");
        list = (List)session.getAttribute(SessionKey.BJQS_LIST);
    }
    else if (type.equals(SessionKey.SDJJ_FJS_INFO))
    {
        out.print("var fssdyzs_sdjj = [");
        list = (List)session.getAttribute(SessionKey.SDJJ_LIST);
    }

    Iterator iterator = fjsInfo.keySet().iterator();

    while(iterator.hasNext())
    {
        out.print("[");
        Integer i = (Integer)iterator.next();

        out.print("'" + ((SbjkmxData)list.get(i.intValue())).getSzsmdm() + "'");

        out.print(",");
        List l = (List)fjsInfo.get(i);

        out.print("'" + ((SbjkmxData)list.get(((Integer)l.get(0)).intValue())).getSzsmdm() + "'");

        for (int j=1; j<l.size(); j++)
        {
            out.print(",");
            out.print("'" + ((SbjkmxData)list.get(((Integer)l.get(j)).intValue())).getSzsmdm() + "'");
        }
        out.print("],");
    }
    out.println("[-1]];");
}

void outSbb(List mxDataList, String sklxdm, JspWriter out, HttpServletRequest request) throws java.io.IOException
{
    if (mxDataList == null)
    {
        return;
    }

    out.println("<br>");
    out.println("<div align=\"left\" style=\"font-size: 10pt;font-weight: bold;color: #3C5564\">");
    out.println("税款类型：" + JspUtil.getSklxmc(request, sklxdm));
    out.println("</div>");

    String tableid= "Table_Master_" + getSklxID(sklxdm);
    out.println("<table id=" + tableid + " width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table-99\">");

    outHeader(out);

    for (int i=0; i<mxDataList.size(); i++)
    {
        out.println("<tr>");
        SbjkmxData mxData = (SbjkmxData)mxDataList.get(i);
        outSzsmdm(mxData, sklxdm, out);
        outSzmc(mxData, sklxdm, out);
        outSzsmmc(mxData, sklxdm, out);
        outSl(mxData, sklxdm, out);
        outKssl(mxData, i, sklxdm, out);
        outJsje(mxData, i, sklxdm, out);
        outSjse(mxData, i, sklxdm, out);
        outDel(sklxdm, out);
        out.println("</tr>");
    }
    out.println("<tr>");
    out.println("<td nowrap class=\"2-td2-left\" colspan=\"6\"><div align=\"right\">合计</div></td>");
    out.println("<td nowrap class=\"2-td2-left\"><input name=\"" + "hj_" + getSklxID(sklxdm) + "\" readonly size=\"15\" class=\"inputnoborder\" tabindex=\"-1\"></td>");
    out.println("<td nowrap class=\"2-td2-center\">&nbsp;</td>");
    out.println("</tr>");

    out.println("</table>");
}

/**
* \u8868\u5934
*/
void outHeader(JspWriter out) throws java.io.IOException
{
    out.println("<tr>");
    out.println("<td width=\"10%\" nowrap class=\"2-td1-left\">&nbsp;<!--税种税目代码--></td>");
    out.println("<td width=\"15%\" nowrap class=\"2-td1-left\">税种名称</td>");
    out.println("<td width=\"25%\" nowrap class=\"2-td1-left\">税目名称</td>");
    out.println("<td width=\"5%\" nowrap class=\"2-td1-left\">税率/计税基数</td>");
    out.println("<td width=\"10%\" nowrap class=\"2-td1-left\">课税数量</td>");
    out.println("<td width=\"15%\" nowrap class=\"2-td1-left\">计税金额</td>");
    out.println("<td width=\"15%\" nowrap class=\"2-td1-left\">实际缴税额</td>");
    out.println("<td width=\"5%\" nowrap class=\"2-td1-center\">&nbsp;</td>");
    out.println("</tr>");
}

void outSzsmdm(SbjkmxData mxData, String sklxdm, JspWriter out) throws java.io.IOException
{
    String name = "szsmID_" + getSklxID(sklxdm);
    out.println("<td nowrap class=\"2-td2-left\">&nbsp;<input type='hidden' name=\"" + name + "\" class=\"inputnoborder\" value=\"" + mxData.getSzsmdm() + "\" size=\"6\" readonly tabIndex=\"-1\"></td>");
}

void outSzmc(SbjkmxData mxData, String sklxdm, JspWriter out) throws java.io.IOException
{
    out.println("<td nowrap class=\"2-td2-left\">" + mxData.getSzmc() + "</td>");
}

void outSzsmmc(SbjkmxData mxData, String sklxdm, JspWriter out) throws java.io.IOException
{
    out.println("<td nowrap class=\"2-td2-left\">" + mxData.getSzsmmc() + "</td>");
}

/**
* \u7A0E\u7387
*/
void outSl(SbjkmxData mxData, String sklxdm, JspWriter out) throws java.io.IOException
{
    String name = "sl_" + getSklxID(sklxdm);
    out.println("<td nowrap class=\"2-td2-left\">");
    String sl = "";
    if (mxData.getSl()!=null)
    {
        sl = mxData.getSl().toString();
    }
    out.println("<input type=\"text\" name=\"" + name + "\" value=\"" + sl  + "\" size=\"6\" readonly class=\"inputnoborder\" tabindex=\"-1\"");
    out.println("</td>");
}

/**
* \u8BFE\u7A0E\u6570\u91CF
*/
void outKssl(SbjkmxData mxData, int i, String sklxdm, JspWriter out) throws java.io.IOException
{
    out.println("<td nowrap class=\"2-td2-left\">");
    String name = "kssl_" + getSklxID(sklxdm);
    if (mxData.isAksslj())
    {
        String config = "";
        if (mxData.isReadOnly())
        {
//            config = "readonly class=\"inputnoborder\" tabindex=\"-1\"";
        }
        out.println("<input type=\"text\" name=\"" + name + "\" value=\"" + JspUtil.format(mxData.getKssl()) + "\"" + config + " onchange='if(checkvalue(this,0)&&formatCurrency(this,0)){run(this," + i + ",\"" + mxData.getSzsmdm() + "\");}else{return false;}' size=\"15\" maxlength=\"16\">");
    }
    else
    {
        out.println("<input type=\"hidden\" name=\"" + name + "\" value=\"" + JspUtil.format(mxData.getKssl()) + "\">");
    }
    out.println("</td>");
}

/**
* \u8BA1\u7A0E\u91D1\u989D
*/
void outJsje(SbjkmxData mxData, int i, String sklxdm, JspWriter out) throws java.io.IOException
{
    out.println("<td nowrap class=\"2-td2-left\">");
    String name = "jsje_" + getSklxID(sklxdm);

    String config = "";
    if (mxData.isReadOnly())
    {
//        config = "readonly class=\"inputnoborder\" tabindex=\"-1\"";
    }
    out.println("<input type=\"text\" name=\"" + name + "\" onchange='if(checkvalue(this,0)&&formatCurrency(this,0)){run(this," + i + ",\"" + mxData.getSzsmdm() + "\");}else{return false;}' value='" + JspUtil.format(mxData.getJsje()) + "' " + config + " size=\"15\" maxlength=\"16\">");
    out.println("</td>");
}

/**
* \u5B9E\u7F34\u7A0E\u989D
*/
void outSjse(SbjkmxData mxData, int i, String sklxdm, JspWriter out) throws java.io.IOException
{
    out.println("<td nowrap class=\"2-td2-left\">");
    String name = "sjse_" + getSklxID(sklxdm);

    String config = "";
    if (mxData.isIsFjs())
    {
//        config = "readonly class=\"inputnoborder\" tabindex=\"-1\"";
    }
    out.println("<input type=\"text\" name=\"" + name + "\" onchange='if(checkvalue(this,0)&&formatCurrency(this,0)){run(this," + i + ",\"" + mxData.getSzsmdm() + "\");}else{return false;}' value='" + JspUtil.format(mxData.getSjse()) + "' " + config + " size=\"15\" maxlength=\"16\">");
    out.println("<input type=\"hidden\" name=\"asljbs_" + getSklxID(sklxdm) + "\" value='" + JspUtil.format(mxData.getAsljbs()) + "'>");
    out.println("<input type=\"hidden\" name=\"coefficient_" + getSklxID(sklxdm) + "\" value='" + JspUtil.format(mxData.getCoefficient()) + "'>");
    out.println("</td>");
}

void outDel(String sklxdm, JspWriter out) throws java.io.IOException
{
    out.println("<td nowrap class=\"2-td2-center\">");

    String sklxid = getSklxID(sklxdm);
    String tableid = "Table_Master_" + sklxid;
    String params = "'" + sklxid + "', " + tableid + ", this" ;

    String idname = "delIndex_" + sklxid;
    out.println("<a id=\"" + idname + "\"" + "  href=\"#\" onclick=\"doDel(" + params + ");return false;\">删除</a>");
    out.println("</td>");
}

String getSklxID(String sklxdm)
{
    if (sklxdm.equals(CodeConstant.SKLXDM_ZCJK))
    {
        return "zcjk";
    }
    else if (sklxdm.equals(CodeConstant.SKLXDM_BJQS))
    {
        return "bjqs";
    }
    else if (sklxdm.equals(CodeConstant.SKLXDM_SDJJ))
    {
        return "sdjj";
    }
    else
    {
        return "";
    }
}
%>