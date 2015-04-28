package com.ttsoft.bjtax.shenbao.taglib;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Properties;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseFieldTag;
import org.apache.struts.util.RequestUtils;

import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp;
import com.ttsoft.bjtax.shenbao.util.JspUtil;
import com.ttsoft.bjtax.shenbao.util.ResponseUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.*;

/**
 * ˰��˰Ŀ��
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class SzsmTreeTag extends BaseFieldTag
{
    public SzsmTreeTag()
    {
        super();
        
        if (showFullTree == null)
        {
        	Properties prop = new Properties();

            try
            {
                prop.load(com.ttsoft.bjtax.shenbao.taglib.SbzlTag.class.getClassLoader().
                          getResourceAsStream("ApplicationResources.properties"));
                          
                if ("true".equals(prop.getProperty("ShowFullTree")))
                {
                	showFullTree = new Boolean("true");
                }
                else
                {
                	showFullTree = new Boolean("false");
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
    private String static_contextpath;

    private String szsm;

    private String filter;

    private String filterReadonly;
    
    private String hideItems;
    
    private static Boolean showFullTree = null;

    public void setSzsm(String szsm)
    {
        this.szsm = szsm;
    }
    
    public void setHideItems(String hideItems)
    {
        this.hideItems = hideItems;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public void setFilterReadonly(String filterReadonly)
    {
        this.filterReadonly = filterReadonly;
    }

    public int doStartTag() throws JspException
    {
        try
        {
            UserData ud = (UserData)pageContext.getSession().getAttribute(SessionKey.USER_DATA);
            if (ud == null)
            {
                throw new JspException("no session found.");
            }
            static_contextpath = ResourceLocator.getStaticFilePath(ud.getCaflag());
            
            List szsmRawMaterial = (List)RequestUtils.lookup(pageContext, name, this.szsm, null);

            List filteList = (List)RequestUtils.lookup(pageContext, name, filter, null);
            
            boolean disabled = Boolean.valueOf(this.filterReadonly).booleanValue();

            List hide = null;
            
            if (disabled && !showFullTree.booleanValue())  // Favorite��Ҫ��ʾ����
            {            
                hide = (List)RequestUtils.lookup(pageContext, name, hideItems, null);
            }

            SzsmForest forest = new SzsmForest();
            forest.create(szsmRawMaterial,
                          filteList,
                          hide,
                          disabled,
                          CodeTableUtil.getCodeTableMap((HttpServletRequest)pageContext.getRequest(),
                                                         CodeTable.SZSM_MAP),
                                                         static_contextpath);

            pageContext.getOut().println(forest.toHtml());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return TagSupport.SKIP_BODY;
    }

}

/**
 * szsmForest�ؼ�����������
 * 1. ����һ���� constructAncestors
 * 2. ����һ���� traverse
 * �����������ṹ����һ��HashMap��n��ArrayList����
 *   HashMap    n �� ArrayList
 * ����������  ���������Щ�������
 * ��02    ������021   ��022   ��
 * ����������  ���������੤�����੤������
 * ��021   ������0211  ��0212  ��0213  ��
 * ����������  ���������੤�����੤�����੤�����Щ�������
 * ��0211  ������021101��021102��021103��021104��021105��
 * ����������  ���������੤�����ة������ة������ة�������
 * ��0212  ������021200��
 * ����������  ����������
 * ��0213  ������021300��
 * ����������  ���������੤������
 * ��022   ������0221  ��0222  ��
 * ����������  ���������ة�������
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Aaron Ding
 * @version 1.0
 */
class SzsmForest
{
    public SzsmForest()
    {
    }

    /**
     * ˰��˰Ŀɭ��
     */
    private Map szsmForest = new HashMap();

    /**
     * ˰��˰Ŀ�����
     */
    private Map szsmTable;

    /**
     * Ҫ������ѡ��checkbox������
     */
    private List filterList;
    
    /**
     * ����ѡ��checkbox�������Ƿ���Ը���
     */
    private boolean disabled;

    private String context_path;

    // ���и��ļ���
    private List roots = new ArrayList();

    /**
     * Create a forest
     * @param rawTree The raw material of a forest
     * @param filterList The elements to be filtered
     * @param disabled Do you wanna disable the element that is filtered?
     * @param szsmTable Code table:szsm
     */
    public void create(List rawTree, List filterList, List hide, boolean disabled, Map szsmTable,String staticfile)
    {
        this.context_path = staticfile + "images/";
        this.szsmTable = szsmTable;
        this.filterList = filterList;
        this.disabled = disabled;
        
        if (hide != null)
        {
            for (int i=rawTree.size()-1; i>=0; i--)
            {
                String szsmdm = ((Szsm)rawTree.get(i)).getSzsmdm();
        	    for (int j=0; j<hide.size(); j++)
                {
        		    if (szsmdm.equals(((Szsm)hide.get(j)).getSzsmdm()))
        		    {
        			    rawTree.remove(i);
        			    break;
            		}
            	}
            }
        }
        createTree(rawTree);
    }

    /**
     * output the html code
     * @return String
     */
    public String toHtml()
    {
        StringBuffer ret = new StringBuffer();

        ret.append("<div align=\"left\">");

        for(int i = 0; i < roots.size(); i++)
        {
            String root = (String)roots.get(i);
            ret.append(traverse(root, szsmForest));
        }

        ret.append("</div>");

        ret.append(css());
        ret.append(js());

        return ret.toString();
    }

    private String js()
    {
        return "\n" +
            "<SCRIPT LANGUAGE=\"javascript\">\n" +
            "var myForm = document.forms[0];\n" +
            "function ToggleDisplay(divitem, imgitem) {\n" +
            "    if ((divitem.style.display == \"\") || (divitem.style.display == \"none\")) {\n" +
            "        divitem.style.display = \"block\";\n" +
            "        imgitem.src = \"" + context_path + "minus.gif\";\n" +
            "    } \n" +
            "    else { \n" +
            "        divitem.style.display = \"none\"; \n" +
            "        imgitem.src = \"" + context_path + "plus.gif\"; \n" +
            "    }\n" +
            "} \n" +
            "</SCRIPT>\n";
    }

    private String css()
    {
        return "\n" +
            "<STYLE TYPE='text/css'>\n" +
            "    DIV.indent {margin-left:15}\n" +
            "</STYLE>\n";
    }

    // construct a tree
    private void createTree(List rawTree)
    {
        for(int i = 0; i < rawTree.size(); i++)
        {
            Szsm node = (Szsm)rawTree.get(i);
            // ��һ��˰Ŀ��Ҷ�ӣ�����������
            constructAncestors(node);
        }
    }

    // ����һ��������Ҷ��construct��root
    private void constructAncestors(Szsm node)
    {
        while(!node.getCcbs().equals("0"))  // unless it is not the root
        {
            String father = node.getFjddm();

            if (node.getSzsmdm().equals(father))
            {
                // Its father is itself. wrong data! :(
                Debug.out(father + ": wrong data.");
                return;
            }

            List tmp = (List)szsmForest.get(father);
          
            if(tmp == null)
            {
                tmp = new ArrayList();
                szsmForest.put(father, tmp);
            }

            boolean found = false;
            for(int i = 0; i < tmp.size(); i++)
            {
                if(((Szsm)tmp.get(i)).getSzsmdm().equals(node.getSzsmdm()))
                {
                    found = true;
                }
            }

            if(!found)
            {
                tmp.add(node.replicate());
            }

            node = (Szsm)szsmTable.get(father);

            if (node == null)
            {
                return;
            }
        }
        if(!roots.contains(node.getSzsmdm()))
        {
            roots.add(node.getSzsmdm());
        }
    }

    // ������Ŀ�ʼ����
    private String outputRootBegin(Szsm szsm)
    {
        String dm = ResponseUtils.filter(szsm.getSzsmdm());
        String mc = ResponseUtils.filter(szsm.getSzsmmc());

        String a = "<div>" +
            " <a href=\"javascript:ToggleDisplay(div" + dm +
            ",document.forms[0].img" + dm + ")\"><IMG id=\"img" +
            dm + "\" SRC=\"" + context_path + "plus.gif\" BORDER=0>" +
            /*dm + " " +*/ mc + "</a>" +
            "  <div id=\"div" + dm + "\" class=\"indent\" style=\"display:none\">";

        return a;
    }

    // ������Ľ�������
    private String outputRootEnd()
    {
        return "</div></div>";
    }

    // ���һ���ڵ�Ŀ�ʼ����
    private String outputBegin(String father, int pos, Map tree)
    {
        Szsm szsm = (Szsm) ( (List)tree.get(father)).get(pos);

        String ret = "";

        StringBuffer ajs = new StringBuffer();
        String dm = ResponseUtils.filter(szsm.getSzsmdm());
        String mc = ResponseUtils.filter(szsm.getSzsmmc());
        String id = "id_" + dm;
        String js = "adjust('" + dm +"','" + mc + "')";

        boolean found = false;

        if(szsm.getCcbs().equals("2"))
        {
            StringBuffer config = new StringBuffer();
            // filter the leaves
            if(filterList != null && filterList.size() != 0)
            {
                for(int i = 0; i < this.filterList.size(); i++)
                {
                    Sqsbtmp filter = (Sqsbtmp)filterList.get(i);
                    if(filter.getSzsmdm().equals(szsm.getSzsmdm()))
                    {
                        found = true;
                        break;
                    }
                }
            }

            if (found && disabled)//�ҵ��ˣ����ܸ���
            {
                config.append(" checked disabled ");
            }
            else
            {
                config.append(" onclick=\"").append(js).append("\"");
                ajs.append("myForm.").append(id).append(".checked=!myForm.").
                    append(id).append(".checked;").append(js);
                if(found)
                {
                    config.append(" checked ");
                }
            }

            // output the leaf
            ret = "<input type=\"checkbox\" name=\"check_szsmdm\"" +
                " id=\"" + id + "\"" + config.toString() + " value=\"" + dm + "\">" +
                "<a href=\"javascript:" + ajs.toString() + "\">" + /*dm + " " +*/ mc + "</a><br>";
        }
        else
        {
            String a = "<div>" + " <a href=\"javascript:ToggleDisplay(div" +
                szsm.getSzsmdm() + ",myForm.img" + szsm.getSzsmdm() +
                ")\"><IMG id=\"img" + szsm.getSzsmdm() + "\" SRC=\"" + context_path +
                "plus.gif\" BORDER=0>" + /*szsm.getSzsmdm() + " " +*/ szsm.getSzsmmc() +
                "</a>" + "  <div id=\"div" + szsm.getSzsmdm() +
                "\" class=\"indent\" style=\"display:none\">\n";

            ret = a;
        }

        return ret + "\n";
    }

    // ����ڵ�Ľ�������
    private String outputEnd(String father, int pos, Map tree)
    {
        String ret = "";
        Szsm szsm = (Szsm) ( (List)tree.get(father)).get(pos);
        if(szsm.getCcbs().equals("1"))  // is a branch
        {
            ret = "</div></div>\n";
        }
        return ret;
    }

    // ����һ�����������������
    private StringBuffer traverse(String root, Map tree)
    {
        StringBuffer ret = new StringBuffer();

        Stack stack1 = new Stack();  // store szsmdm
        Stack stack2 = new Stack();  // store the szsmdm's position

        stack1.push(root);
        stack2.push(new Integer(0));

        ret.append(outputRootBegin( (Szsm)szsmTable.get(root)));

        while(!stack1.empty())
        {
            String father = (String)stack1.peek();
            int pos = ( (Integer)stack2.peek()).intValue();

            if (tree.get(father) == null)
            {
                // the father code is invalid. discard it!
                stack1.pop();
                stack2.pop();
                continue;
            }

            ret.append(outputBegin(father, pos, tree));

            if(hasOffsprings(father, pos, tree))
            {
                // ��Ϊ��������ȣ�����Ҫ�Ȱ�����һ�����ӷŵ�stack��
                stack1.push(((Szsm)((List)tree.get(father)).get(pos)).getSzsmdm());
                stack2.push(new Integer(0));
            }
            else
            {
                // û�к���ˣ�pop���Լ���
                father = (String)stack1.pop();
                pos = ((Integer)stack2.pop()).intValue();

                // ��������Լ�
                ret.append(outputEnd(father, pos, tree));

                if(hasSiblings(father, pos, tree))
                {
                    // ��sibling�Ļ���ʼ������һ��sibling
                    stack1.push(father);
                    stack2.push(new Integer(pos + 1));
                }
                else
                {
                    // ��û��offspringҲû��sibling��
                    while(!stack1.empty())
                    {
                        // ����father
                        father = (String)stack1.pop();
                        pos = ((Integer)stack2.pop()).intValue();

                        // �������father
                        ret.append(outputEnd(father, pos, tree));

                        // ���father��sibling������֮
                        if(hasSiblings(father, pos, tree))
                        {
                            stack1.push(father);
                            stack2.push(new Integer(pos + 1));
                            break;
                        }
                    }
                }
            }
        }
        ret.append(outputRootEnd());

        return ret;
    }

    //�Ƿ����ֵܽ���
    private boolean hasSiblings(String dm, int pos, Map tree)
    {
        return( (List)tree.get(dm)).size() > pos + 1;
    }

    // �Ƿ��к��
    private boolean hasOffsprings(String dm, int pos, Map tree)
    {
        return tree.get( ( (Szsm) ( (List)tree.get(dm)).get(pos)).getSzsmdm()) != null;
    }
}