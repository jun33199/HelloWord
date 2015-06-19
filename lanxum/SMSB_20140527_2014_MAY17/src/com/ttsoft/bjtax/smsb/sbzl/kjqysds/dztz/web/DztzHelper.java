package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web;

/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Action������</p>
 *
 * <p>Description: ��ع��÷���</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import javax.servlet.http.HttpServletRequest;
import com.ttsoft.framework.exception.ApplicationException;
import javax.servlet.http.HttpSession;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import java.sql.Connection;

import com.ttsoft.bjtax.dj.model.dm.BZ;



public class DztzHelper
{
	public static final String V_KJYWRMC="V_KJYWRMC";
	public static final String V_KJYWRNSSBH="V_KJYWRNSSBH";
	public static final String V_LXR="V_LXR";
	public static final String V_DH="V_DH";
	public static final String V_DZ="V_DZ";
	public static final String V_FJMQYMC="V_FJMQYMC";
	public static final String V_GBDQ="V_GBDQ";
	public static final String V_QUERY_HTML_BODY="V_QUERY_HTML_BODY";

	public static final String QUERY_HTML_HEAD=
		"<table  width=\"1566\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+
		"	<tr> "+
		"		<td class=\"2-td2-t-left\" colspan='2' align=\"center\">�۽�����������</td> "+
		"		<td class=\"2-td2-t-left\" colspan='2' height=\"53\" align=\"center\">"+V_KJYWRMC+"��</td> "+
		"		<td class=\"2-td2-t-left\" width=\"5%\" height=\"53\" colspan='2' align=\"center\">�۽���������˰ʶ���</td> "+
		"		<td class=\"2-td2-t-left\" width=\"5%\" height=\"53\" align=\"center\">"+V_KJYWRNSSBH+"</td> "+
		"		<td class=\"2-td2-t-left\" width=\"6%\" height=\"53\" align=\"center\">��ϵ��</td> "+
		"		<td class=\"2-td2-t-left\" width=\"6%\" height=\"53\" align=\"center\">"+V_LXR+"</td> "+
		"		<td class=\"2-td2-t-left\" width=\"3%\" height=\"53\" align=\"center\">�� ��</td> "+
		"		<td class=\"2-td2-t-left\" colspan=\"2\" height=\"53\" align=\"center\">"+V_DH+"</td> "+
		"		<td class=\"2-td2-t-left\" width=\"5%\" height=\"53\" align=\"center\">�� ַ</td> "+
		"		<td class=\"2-td2-t-left\" height=\"53\" colspan='6' align=\"center\">"+V_DZ+"</td> "+
//		"		<td class=\"2-td2-t-left\" height=\"53\" width=\"9%\" colspan=\"2\" align=\"center\">�Ǿ�����ҵ����</td> "+
//		"		<td class=\"2-td2-t-left\" height=\"53\" width=\"12%\" colspan=\"2\" align=\"center\">"+V_FJMQYMC+"</td> "+
//		"		<td class=\"2-td2-t-left\" height=\"53\" width=\"6%\" align=\"center\">�� �𣨵�����</td> "+
//		"		<td class=\"2-td2-t-left\" height=\"53\" width=\"5%\" align=\"center\">"+V_GBDQ+"</td> "+
		"	</tr> "+
		"	<tr> "+
		"		<td class=\"2-td2-left\" width=\"4%\" rowspan=\"2\" align=\"center\">�� ��</td> "+
        "		<td class=\"2-td2-left\" width=\"7%\" rowspan=\"2\"align=\"center\">�Ǿ�����ҵ����</td> "+
        "		<td class=\"2-td2-left\" width=\"7%\" rowspan=\"2\" align=\"center\">�� �𣨵�����</td> "+
		"		<td class=\"2-td2-left\" width=\"7%\" rowspan=\"2\" align=\"center\">��ͬ��Ŀ����</td> "+
		"		<td class=\"2-td2-left\" width=\"5%\" rowspan=\"2\" align=\"center\">��ͬ��</td> "+
		"		<td class=\"2-td2-left\" width=\"5%\" rowspan=\"2\" align=\"center\">��ͬ�ܼۿ�</td> "+
		"		<td class=\"2-td2-left\" width=\"6%\" rowspan=\"2\" align=\"center\">��ͬǩ������</td> "+
		"		<td class=\"2-td2-left\" width=\"6%\" rowspan=\"2\" align=\"center\">��ִͬ������</td> "+
		"		<td class=\"2-td2-left\" width=\"3%\" rowspan=\"2\" align=\"center\">��֧ͬ������</td> "+
		"		<td class=\"2-td2-left\" colspan=\"3\" align=\"center\">������</td> "+
		"		<td class=\"2-td2-left\" width=\"5%\" rowspan=\"2\" align=\"center\">��������</td> "+
		"		<td class=\"2-td2-left\" width=\"4%\" rowspan=\"2\" align=\"center\">Ӧ��˰��</td> "+
		"		<td class=\"2-td2-left\" width=\"4%\" rowspan=\"2\" align=\"center\">����˰��</td> "+
		"		<td class=\"2-td2-left\" width=\"6%\" rowspan=\"2\" align=\"center\">�걨��˰����</td> "+
		"		<td class=\"2-td2-left\" width=\"6%\" rowspan=\"2\" align=\"center\">˰���������</td> "+
		"		<td class=\"2-td2-left\" width=\"6%\" rowspan=\"2\" align=\"center\">��˰֤��������˰���ĺ�</td> "+
		"		<td class=\"2-td2-left\" width=\"5%\" rowspan=\"2\" align=\"center\">�� ע</td> "+
		"	</tr> "+
		"	<tr> "+
		"		<td class=\"2-td2-left\" width=\"4%\" align=\"center\">�� �� </td> "+
		"		<td class=\"2-td2-left\" width=\"4%\" align=\"center\">��ҽ��</td> "+
		"		<td class=\"2-td2-left\" width=\"5%\" align=\"center\">�ۺ������</td> "+
		"	</tr> "+V_QUERY_HTML_BODY+
		"</table> ";

	public static final String QUERY_HTML_BODY_EMPTY=
		"	<tr> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
        "		<td class=\"2-td2-left\" >&nbsp;��</td> "+
        "		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"	</tr> "+
		"	<tr> "+
		"		<td class=\"2-td2-left\" >��Ŀ�ϼ�</td> "+
        "		<td class=\"2-td2-left\" >&nbsp;��</td> "+
        "		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"	</tr> "+
		"	<tr> "+
		"		<td class=\"2-td2-left\">��Ⱥϼ�</td> "+
        "		<td class=\"2-td2-left\" >&nbsp;��</td> "+
        "		<td class=\"2-td2-left\" >&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;��</td> "+
		"		<td class=\"2-td2-left\">&nbsp;</td> "+
		"	</tr> ";

    /**
     * ��BO ת����HTML ��չ��
     * @param bo BadjBo
     * @return  ����̨�ʲ�Ѷ���HTML
     */
    public static String boToHtml(DztzBO bo){
    	String v_kjywrmc=nullConvertToNbsp(bo.getKjywrmc());
    	String v_kjywrnssbh=nullConvertToNbsp(bo.getKjywrnssbh());
    	String v_lxr=nullConvertToNbsp(bo.getLxr());
    	String v_dh=nullConvertToNbsp(bo.getDh());
    	String v_dz=nullConvertToNbsp(bo.getDz());
    	String v_fjmqymc=nullConvertToNbsp(bo.getFjmqymc());
    	String v_gbdq=nullConvertToNbsp(bo.getGbdq());
    	String v_query_html_body = getBodyByList(bo.getTzsjxx());
    	String tzxxHtml=QUERY_HTML_HEAD;
    	tzxxHtml=tzxxHtml.replaceAll(V_KJYWRMC, v_kjywrmc)
    					 .replaceAll(V_KJYWRNSSBH, v_kjywrnssbh)
    					 .replaceAll(V_LXR, v_lxr)
    					 .replaceAll(V_DH, v_dh)
    					 .replaceAll(V_DZ, v_dz)
//    					 .replaceAll(V_FJMQYMC, v_fjmqymc)
//    					 .replaceAll(V_GBDQ, v_gbdq)
    					 .replaceAll(V_QUERY_HTML_BODY, v_query_html_body);

    	return tzxxHtml;

    }

    public static String getBodyByList(List list)
    {
        String returnHtml = "";
        if (list == null || list.size() == 0) {
            returnHtml = QUERY_HTML_BODY_EMPTY;
        }
        else
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                sb.append("<tr>");
                Map map = (Map) list.get(i);
                for (int j = 1; j <= 19; j++) {
                    String valueKey = "COL_" + String.valueOf(j);
                    sb.append("<td class=\"2-td2-left\">");
                    sb.append(nullConvertToNbsp((String) map.get(valueKey)));
                    sb.append("</td>");
                }
                sb.append("</tr>");
            }
            returnHtml = sb.toString();
        }
    	return returnHtml;

    }


    public static String nullConvertToNbsp(String value){
    	return (value == null || value.trim().length() == 0)?"&nbsp;":value;
    }
}
