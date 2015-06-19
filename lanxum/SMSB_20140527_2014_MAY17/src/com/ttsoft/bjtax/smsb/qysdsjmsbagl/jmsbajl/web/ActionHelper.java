package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.framework.exception.ApplicationException;

public class ActionHelper {
	public static final String V_QUERY_HTML_BODY="V_QUERY_HTML_BODY";
	public static final String QUERY_HTML_HEAD=
		"<table  width=\"940\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+		
		"	<tr> "+
		"		<td width=\"50\" class=\"2-td1-left\"  align=\"center\">���������</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">��˰������</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">��������˰�����</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">����˰���</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">����������</td> "+
		"		<td width=\"30\"  class=\"2-td1-left\"  align=\"center\">�������</td> "+
		"		<td width=\"60\"  class=\"2-td1-left\"  align=\"center\">��������</td> "+
		"		<td width=\"65\" class=\"2-td1-left\"  align=\"center\">����״̬</td> "+
		"		<td width=\"60\" class=\"2-td1-center\"  align=\"center\">����</td> "+
		"	</tr> "+V_QUERY_HTML_BODY+
		"</table> ";

	public static final String QUERY_HTML_HEAD_ZFBG=
		"<table  width=\"940\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+		
		"	<tr> "+
		"		<td width=\"80\" class=\"2-td1-left\"  align=\"center\">���������</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">��˰������</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">����˰�����</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">����˰���</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">����������</td> "+
		"		<td width=\"60\"  class=\"2-td1-left\"  align=\"center\">�������</td> "+
		"		<td width=\"60\"  class=\"2-td1-center\"  align=\"center\">��������</td> "+
		"	</tr> "+V_QUERY_HTML_BODY+
		"</table> ";

	
	
	
	 /**
     * ��ʼ��ҳ��������Ϣ
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request,List list,List list1) throws ApplicationException
    {
        // ��ȡsession
        HttpSession session = request.getSession(false);
        // ��ȡ����˰���
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASXDM) == null) {
            String[][] jmsbasxArray = new String[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
            	DmVo vo=(DmVo)list.get(i);
            	jmsbasxArray[i][0] = vo.getDm();
            	jmsbasxArray[i][1] = vo.getMc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASXDM, jmsbasxArray);
        }           
        
        //��ȡ����˰�����
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG) == null) {
            String[][] jmszgswjgArray = new String[list1.size()][2];
            for (int i = 0; i < list1.size(); i++) {
            	DmVo vo=(DmVo)list1.get(i);
            	jmszgswjgArray[i][0] = vo.getDm();
            	jmszgswjgArray[i][1] = vo.getMc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG, jmszgswjgArray);
        } 
        
        // ��ȡ��������
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX) == null) {
            String[][] jmssqlxArray = new String[2][2];
            
            jmssqlxArray[0][0]="0";
            jmssqlxArray[0][1]="��������";
            
            jmssqlxArray[1][0]="1";
            jmssqlxArray[1][1]="��������";
            
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX, jmssqlxArray);
        }           
        
        // ��ȡ����״̬
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQZT) == null) {
            String[][] jmssqztArray = new String[4][2];
          
            jmssqztArray[0][0]="2";
            jmssqztArray[0][1]="����δ���";
            
            jmssqztArray[1][0]="3";
            jmssqztArray[1][1]="�ύδ���";
            
            jmssqztArray[2][0]="4";
            jmssqztArray[2][1]="�����ͨ��";
            
            jmssqztArray[3][0]="5";
            jmssqztArray[3][1]="���δͨ��";
            
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQZT, jmssqztArray);
        }           
    }
    
    
	
	 /**
    * ��ʼ��ҳ��������Ϣ
    * @param request HttpServletRequest
    * @throws ApplicationException
    */
   public static void initialZfbgSelectItem(HttpServletRequest request) throws ApplicationException
   {
       // ��ȡsession
       HttpSession session = request.getSession(false);

       // ��ȡ����״̬
       if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBAZFYY) == null) {
           String[][] jmssqztArray = new String[4][2];
         
           jmssqztArray[0][0]="1";
           jmssqztArray[0][1]="����ʵ����˰�˲����ڼ���˰�Ż����߷�Χ";
           
           jmssqztArray[1][0]="2";
           jmssqztArray[1][1]="����ʵ����˰���ṩ�ļ���˰������������";
           
           jmssqztArray[2][0]="3";
           jmssqztArray[2][1]="����ʵ����˰���ṩ����ٵļ���˰��������";
           
           jmssqztArray[3][0]="4";
           jmssqztArray[3][1]="��������������·���д����ԭ��";
           
           session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBAZFYY, jmssqztArray);
       }           
   }
   
    
    public static String boToHtml(List list){
    	if(list==null){
    		return QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, "");	
    	}else{
    		return QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, getBodyByList(list));	
    	}
    		
    }
    
    
    public static String getBodyByList(List list)
    {
        String returnHtml = "";
        if (list == null || list.size() == 0) {
            returnHtml = "";
        }
        else
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                sb.append("<tr>");
                Map map = (Map) list.get(i);
                for (int j = 1; j <= 9; j++) {
                    String valueKey = "COL_" + String.valueOf(j);
                    
                    
                    if(j==9)sb.append("<td class=\"2-td2-center\">");
                    
                    else sb.append("<td class=\"2-td2-left\">");
                    if(j>=2 && j<=5)sb.append("<div align=\"left\">");
                    sb.append(nullConvertToNbsp((String) map.get(valueKey)));
                    if(j>=2 && j<=5)sb.append("</div>");
                    sb.append("</td>");
                }
                sb.append("</tr>");
            }
            returnHtml = sb.toString();
        }
    	return returnHtml;

    }
    
    public static String boToHtmlZfbg(List list){
    	if(list==null){
    		return QUERY_HTML_HEAD_ZFBG.replaceAll(V_QUERY_HTML_BODY, "");	
    	}else{
    		return QUERY_HTML_HEAD_ZFBG.replaceAll(V_QUERY_HTML_BODY, getBodyByListZfbg(list));	
    	}
    		
    }
    
    public static String getBodyByListZfbg(List list)
    {
        String returnHtml = "";
        if (list == null || list.size() == 0) {
            returnHtml = "";
        }
        else
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                sb.append("<tr>");
                Map map = (Map) list.get(i);
                for (int j = 1; j <= 7; j++) {
                    String valueKey = "COL_" + String.valueOf(j);
                    
                    
                    if(j==7)sb.append("<td class=\"2-td2-center\">");
                    
                    else sb.append("<td class=\"2-td2-left\">");
                    if(j>=2 && j<=5)sb.append("<div align=\"left\">");
                    sb.append(nullConvertToNbsp((String) map.get(valueKey)));
                    if(j>=2 && j<=5)sb.append("</div>");
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
    
    
    public static String getForwardPath(String jmbasxdm,String wsh,String czlx,String basqbh){
    	String str = "?actionType=Show&basqwsh="+wsh+"&czlx="+czlx+"&basqbh="+basqbh;
    	if(Integer.parseInt(czlx)==CodeConstant.QYSDSJMSBAJL_CZLX_ZFBG){
    		str="?actionType=Zfbg&basqwsh="+wsh+"&czlx="+czlx+"&basqbh="+basqbh;
    	}
    	Map map=new HashMap();
    	map.put("0010", ForwardPath.PAGE_01_SHOW+str);
    	map.put("0020", ForwardPath.PAGE_02_SHOW+str);
    	map.put("0030", ForwardPath.PAGE_03_SHOW+str);
    	map.put("0040", ForwardPath.PAGE_04_SHOW+str);
    	map.put("0050", ForwardPath.PAGE_05_SHOW+str);
    	map.put("0060", ForwardPath.PAGE_06_SHOW+str);
    	map.put("0070", ForwardPath.PAGE_07_SHOW+str);
    	map.put("0080", ForwardPath.PAGE_08_SHOW+str);
    	map.put("0090", ForwardPath.PAGE_09_SHOW+str);
    	map.put("0100", ForwardPath.PAGE_10_SHOW+str);
    	map.put("0110", ForwardPath.PAGE_11_SHOW+str);
    	map.put("0120", ForwardPath.PAGE_12_SHOW+str);
    	map.put("013A", ForwardPath.PAGE_13A_SHOW+str);
    	map.put("013B", ForwardPath.PAGE_13B_SHOW+str);
    	map.put("014A", ForwardPath.PAGE_14A_SHOW+str);
    	map.put("014B", ForwardPath.PAGE_14B_SHOW+str);
    	map.put("0150", ForwardPath.PAGE_15_SHOW+str);
    	map.put("0160", ForwardPath.PAGE_16_SHOW+str);
    	map.put("0170", ForwardPath.PAGE_17_SHOW+str);
    	map.put("0180", ForwardPath.PAGE_18_SHOW+str);
    	map.put("0190", ForwardPath.PAGE_19_SHOW+str);
    	map.put("0200", ForwardPath.PAGE_20_SHOW+str);
    	map.put("0210", ForwardPath.PAGE_21_SHOW+str);
    	map.put("0220", ForwardPath.PAGE_22_SHOW+str);
    	return (String)map.get(jmbasxdm);
    
    }
    
    
}
