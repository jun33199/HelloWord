package com.ttsoft.bjtax.shenbao.taglib;

import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.struts.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.util.ResponseUtils;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;

/**
 * <p>Title: 北京地税综合管理系统  申报模块</p>
 * <p>Description: 实现北京地税申报模块，此标签必须放在申报表form表单中</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class SbbTag extends TagSupport
{

    protected final String CLASS_2_TD1_LEFT = "2-td1-left";
    protected final String CLASS_2_TD1_CENTER = "2-td1-center";
    protected final String CLASS_2_TD2_LEFT = "2-td2-left";
    protected final String CLASS_2_TD2_CENTER = "2-td2-center";
    protected final String CLASS_TABLE_99 = "table-99";
    protected final String CLASS_INPUT_NOBORDER = "inputnoborder";
    protected final String STYLE_TEXT_CENTER = "text-align:center";
    protected final String CLASS_TEXT_CENTER = "noborder-txtcenter";

    protected final int SIZE = 15;
    protected final int MAXLENGTH = 15;

    //申报表form bean Name;
    protected String sbbName;

    private Object sbbForm = null;

    public String getSbbName()
    {
        return sbbName;
    }

    public void setSbbName(String sbbName)
    {
        this.sbbName = sbbName;
    }

    /**
     * The scope to be searched to retrieve the specified bean.
     */
    protected String scope = null;

    //本期累计数
    private String bqljs;
    //年初数
    private String ncs;
    //年末数
    private String nms;
    //行次
    private String sbbhc;
    private String defineList;
    //申报表定义的列表对应javabeans的属性
    private String formName;
    private String xmmc;

    public String getScope()
    {
        return(this.scope);
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public String getBqljs()
    {
        return bqljs;
    }

    public void setBqljs(String bqljs)
    {
        this.bqljs = bqljs;
    }

    public String getNcs()
    {
        return ncs;
    }

    public void setNcs(String ncs)
    {
        this.ncs = ncs;
    }

    public String getNms()
    {
        return nms;
    }

    public void setNms(String nms)
    {
        this.nms = nms;
    }

    public String getHc()
    {
        return sbbhc;
    }

    public void setHc(String hc)
    {
        this.sbbhc = hc;
    }

    public String getDefineList()
    {
        return defineList;
    }

    public void setDefineList(String defineList)
    {
        this.defineList = defineList;
    }

    String jsgsArray = null;

    /**
     * @return int
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException
    {

        // Look up the requested bean (if necessary)
        Object bean = null;
        if( (bean = RequestUtils.lookup(pageContext, formName, scope)) == null)
        {
            return(SKIP_BODY); // Nothing to output
        }

        if(bean instanceof BaseForm)
        {
            if(bean != null)
            {
                sbbForm = (BaseForm)bean;

                jsgsArray = sbbName + "_jsgss";

                //画出申报表
                drawSbb();

                //对申报表赋值并设置显示控制
                drawSbbData();
            }
        }

        // Continue processing this page
        return(SKIP_BODY);
    }

    /**
     * 根据申报表定义数据输出申报表
     * @throws JspException
     */
    private void drawSbb() throws JspException
    {
        try
        {

            JspWriter out = pageContext.getOut();

            out.println(
                "<table width='100%' border='0' cellpadding='0' cellspacing='0' class='" +
                CLASS_TABLE_99 + "'>");

            //表头
            drawSbbHeader();

            List thisDefineList = null;
            try
            {
                thisDefineList = (java.util.List)PropertyUtils.
                    getSimpleProperty(sbbForm, defineList);
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }

            //表体
            drawTableBody(thisDefineList);

            out.println("</table>");

            //输出javascript
            out.println("<script language='javascript'>");

            //设置计算公式
            drawJsgs(thisDefineList);

            out.println("</script>");

        }
        catch(java.io.IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * 申报表表头描述
     * @throws JspException
     */
    private void drawSbbHeader() throws JspException
    {
        try
        {

            int count = 0;
            if(bqljs != null)
            {
                count++;
            }
            if(ncs != null)
            {
                count++;
            }
            if(nms != null)
            {
                count++;
            }

            JspWriter out = pageContext.getOut();

            out.println(" <tr>");
            out.println("  <td align='center' nowrap class='" +
                        CLASS_2_TD1_LEFT +
                        "'>项目</td>");
            out.println("  <td align='center' nowrap class='" +
                        CLASS_2_TD1_LEFT +
                        "'>行次</td>");

            if(bqljs != null)
            {
                out.println("  <td align='center' nowrap class='" +
                            CLASS_2_TD1_LEFT +
                            "'>本期累计数</td>");
            }
            if(ncs != null)
            {
                out.println("  <td align='center' nowrap class='" +
                            CLASS_2_TD1_LEFT +
                            "'>年初数</td>");
            }
            if(nms != null)
            {
                out.println("  <td align='center' nowrap class='" +
                            CLASS_2_TD1_LEFT +
                            "'>年末数</td>");
            }
            out.println("  <td align='center' nowrap class='" +
                        CLASS_2_TD1_LEFT +
                        "'>项目</td>");
            out.println("  <td align='center' nowrap class='" +
                        CLASS_2_TD1_LEFT +
                        "'>行次</td>");

            int index = 0;
            if(bqljs != null)
            {
                index++;
                out.println("  <td align='center' nowrap class='" +
                            (index < count ? CLASS_2_TD1_LEFT :
                             CLASS_2_TD1_CENTER) +
                            "'>本期累计数</td>");
            }
            if(ncs != null)
            {
                index++;
                out.println("  <td align='center' nowrap class='" +
                            (index < count ? CLASS_2_TD1_LEFT :
                             CLASS_2_TD1_CENTER) +
                            "'>年初数</td>");
            }
            if(nms != null)
            {
                index++;
                out.println("  <td align='center' nowrap class='" +
                            (index < count ? CLASS_2_TD1_LEFT :
                             CLASS_2_TD1_CENTER) +
                            "'>年末数</td>");
            }
            out.println(" </tr>");

        }
        catch(java.io.IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * 设置计算公式
     * @param defineList 申报表定义数据集合
     * @throws JspException
     */
    private void drawJsgs(List defineList) throws JspException
    {
        try
        {
            JspWriter out = pageContext.getOut();

            List jsgsList = new ArrayList();

            Iterator iter = defineList.iterator();
            while(iter.hasNext())
            {
                Sbbbxm item = (Sbbbxm)iter.next();

                // 编辑性质为计算
                if(item.getBjxz().equals(CodeConstant.SBBXMDM_BJXZ_CAL))
                {
                    jsgsList.add(item.getSjly());
                }
            }

            out.println("var " + jsgsArray + " = new Array();");

            int jsgxIndex = 0;
            iter = jsgsList.iterator();
            while(iter.hasNext())
            {
                String gs = (String)iter.next();

                if(bqljs != null)
                {
                    out.println(jsgsArray + "[" + jsgxIndex +
                                "] = new MathString(\"" +
                                changeGsVarName(gs, sbbName + "_B_") +
                                "\");");
                    jsgxIndex++;
                }
                if(ncs != null)
                {
                    out.println(jsgsArray + "[" + jsgxIndex +
                                "] = new MathString(\"" +
                                changeGsVarName(gs, sbbName + "_C_") +
                                "\");");
                    jsgxIndex++;
                }
                if(nms != null)
                {
                    out.println(jsgsArray + "[" + jsgxIndex +
                                "] = new MathString(\"" +
                                changeGsVarName(gs, sbbName + "_M_") +
                                "\");");
                    jsgxIndex++;
                }

            }
            /*
                  //draw compute and check function()
                  out.println("      function compute"+sbbName+"()");
                  out.println("      {");
                 out.println("         var id = trim(window.event.srcElement.id+\"\");");
                  out.println("         if(!check"+sbbName+"Value(id)){");
                  out.println("            return false;");
                  out.println("         }else{");
                  out.println("             compute"+sbbName+"GS(id);");
                  out.println("         }");
                  out.println("      }");
//      //draw compute and check function()
//      out.println("      function compute"+sbbName+"()");
//      out.println("      {");
//      out.println("         var id = trim(window.event.srcElement.id+\"\");");
//      out.println("         var index = -1;");
//      out.println("         for(var i=0;i<id.length;i++){");
//      out.println("           if(id.charAt(i) == '_'){");
//      out.println("              index = i;");
//      out.println("             }");
//      out.println("         }");
//      out.println("   ");
//      out.println("         if(index >= 0){");
//      out.println("                 var hc = parseInt(id.substring(index+1,id.length));");
//      out.println("                 if(!check"+sbbName+"Value(hc)){");
//      out.println("                         return false;");
//      out.println("                 }else{");
//      out.println("                         compute"+sbbName+"GS(id);");
//      out.println("                 }");
//      out.println("         }");
//
//      out.println("      }");
                  //draw computegs function()
                  out.println("      function compute"+sbbName+"GS(myid)");
                  out.println("      {");
                  out.println("          var stack = new Stack();");
                  out.println("          var strmyid = \"\" + myid;");
                  out.println("          var hc = strmyid;");
                 out.println("          for(var i=0;i<"+jsgsArray+".length;i++)");
                  out.println("          {");
                 out.println("              if("+jsgsArray+"[i].isInExpress(hc))");
                  out.println("              {");
                 out.println("                  var retBolan = "+jsgsArray+"[i].compute();");
                  out.println("                  if(retBolan != null)");
                  out.println("                  {");
                  out.println("                      setValue(retBolan.operator, retBolan.value);");
                 out.println("                      stack.push(retBolan.operator);");
                  out.println("                  }");
                  out.println("              }");
                  out.println("          }");
                  out.println("          while(stack.length() > 0)");
                  out.println("          {");
                  out.println("              var nHc = stack.pop();");
                  out.println("              compute"+sbbName+"GS(nHc);");
                  out.println("          }");
                  out.println("      }");
             */

        }

        catch(java.io.IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * 输出申报表表体部分
     * @param defineList List
     * @throws JspException
     */
    private void drawTableBody(List defineList) throws JspException
    {
        try
        {
            int count = 0;
            if(bqljs != null)
            {
                count++;
            }
            if(ncs != null)
            {
                count++;
            }
            if(nms != null)
            {
                count++;

            }
            JspWriter out = pageContext.getOut();

            //将申报表按照左右两列的顺序输出
            if(defineList != null && defineList.size() > 0)
            {
                //得到申报表的行数
                int l = defineList.size() / 2;
                if(defineList.size() % 2 != 0)
                {
                    l++;
                }

                //申报表项目代码
                Sbbbxm xmdm = null;
                int index;
                //转换后的项目名称
                String filterXmmc = null;
                //行次
                String hc = null;

                for(int i = 0; i < l; i++)
                {

                    out.println(" <tr>");

                    //****************draw left item**************

                    xmdm = (Sbbbxm)defineList.get(i);

                    //行次
                    hc = xmdm.getHc();
                    if(hc == null)
                    {
                        hc = "";
                    }

                    //转换项目名称
                    filterXmmc = ResponseUtils.filter(xmdm.getXmmc());
                    out.println("  <td align='center'  class='" +
                                CLASS_2_TD2_LEFT +
                                "'><div align='left'>&nbsp;" +
                                filterXmmc +
                                "</div><input type='hidden' name='" + xmmc +
                                "' id='" + xmmc + "_" + hc + "' value='" +
                                xmdm.getXmmc() + "'></td>");

                    //编辑性质,是否只读（0：录入，其他：只读）
                    String readonly = xmdm.getBjxz().equals("0") ? " " :
                        " readonly class='inputnoborder' ";

                    if(hc.equals("24") || hc.equals("63") || hc.equals("77")
                       || hc.equals("81") || hc.equals("82") || hc.equals("87"))
                    {
                        readonly = "";
                    }


                    out.println("  <td align='center' nowrap class='" +
                                CLASS_2_TD2_LEFT +
                                "'><input type='text' name='" + sbbhc +
                                "' value='" + hc + "' class='" +
                                CLASS_TEXT_CENTER +
                                "' readonly size='4'  > </td>");

                    if(bqljs != null)
                    {
                        out.println("  <td align='center' nowrap class='" +
                                    CLASS_2_TD2_LEFT +
                                    "'><input type='text' name='" + bqljs +
                                    "' id='" + sbbName + "_B_" + hc +
                                    "' size='" + SIZE + "' " +
                                    drawCompute(xmdm.getBjxz()) + " " +
                                    readonly + " MAXLENGTH=" + MAXLENGTH +
                                    " ></td>");
                    }
                    if(ncs != null)
                    {
                        out.println("  <td align='center' nowrap class='" +
                                    CLASS_2_TD2_LEFT +
                                    "'><input type='text' name='" + ncs +
                                    "' id='" + sbbName + "_C_" + hc +
                                    "' size='" + SIZE + "' " +
                                    drawCompute(xmdm.getBjxz()) + " " +
                                    readonly + " MAXLENGTH=" + MAXLENGTH +
                                    " ></td>");
                    }
                    if(nms != null)
                    {
                        out.println("  <td align='center' nowrap class='" +
                                    CLASS_2_TD2_LEFT +
                                    "'><input type='text' name='" + nms +
                                    "' id='" + sbbName + "_M_" + hc +
                                    "' size='" + SIZE + "' " +
                                    drawCompute(xmdm.getBjxz()) + " " +
                                    readonly + " MAXLENGTH=" + MAXLENGTH +
                                    " ></td>");
                    }

                    //****************draw right item**************
                     xmdm = null;
                    if(l + i < defineList.size())
                    {
                        xmdm = (Sbbbxm)defineList.get(l + i);
                    }

                    index = 0;
                    if(xmdm != null)
                    {
                        readonly = xmdm.getBjxz().equals("0") ? " " :
                            " readonly class='inputnoborder'";

                        hc = xmdm.getHc();
                        if(hc == null)
                        {
                            hc = "";
                        }

                        if(hc.equals("24") || hc.equals("63") || hc.equals("77")
                           || hc.equals("81") || hc.equals("82") || hc.equals("87"))
                        {
                            readonly = "";
                        }

                        filterXmmc = ResponseUtils.filter(xmdm.getXmmc());

                        out.println("  <td align='center'  class='" +
                                    CLASS_2_TD2_LEFT +
                                    "'><div align='left'>&nbsp;" + filterXmmc +
                                    "</div><input type='hidden' name='" + xmmc +
                                    "' id='" + xmmc + "_" + hc + "' value='" +
                                    xmdm.getXmmc() + "'></td>");

                        out.println("  <td align='center' nowrap class='" +
                                    CLASS_2_TD2_LEFT +
                                    "'> <input type='text' name='" + sbbhc +
                                    "' value='" + hc + "'   class='" +
                                    CLASS_TEXT_CENTER +
                                    "' readonly size='4'> </td>");

                        if(bqljs != null)
                        {
                            index++;
                            out.println("  <td align='center' nowrap class='" +
                                        (index < count ? CLASS_2_TD2_LEFT :
                                         CLASS_2_TD2_CENTER) +
                                        "'><input type='text' name='" + bqljs +
                                        "' id='" + sbbName + "_B_" + hc +
                                        "' size='" + SIZE + "' " +
                                        drawCompute(xmdm.getBjxz()) + " " +
                                        readonly + " MAXLENGTH=" + MAXLENGTH +
                                        " ></td>");
                        }
                        if(ncs != null)
                        {
                            index++;
                            out.println("  <td align='center' nowrap class='" +
                                        (index < count ? CLASS_2_TD2_LEFT :
                                         CLASS_2_TD2_CENTER) +
                                        "'><input type='text' name='" + ncs +
                                        "' id='" + sbbName + "_C_" + hc +
                                        "' size='" + SIZE + "' " +
                                        drawCompute(xmdm.getBjxz()) + " " +
                                        readonly + "  MAXLENGTH=" + MAXLENGTH +
                                        "></td>");
                        }
                        if(nms != null)
                        {
                            index++;
                            out.println("  <td align='center' nowrap class='" +
                                        (index < count ? CLASS_2_TD2_LEFT :
                                         CLASS_2_TD2_CENTER) +
                                        "'><input type='text' name='" + nms +
                                        "' id='" + sbbName + "_M_" + hc +
                                        "' size='" + SIZE + "' " +
                                        drawCompute(xmdm.getBjxz()) + " " +
                                        readonly + "  MAXLENGTH=" + MAXLENGTH +
                                        " ></td>");
                        }
                    }
                    else
                    {
                        out.println("  <td align='center'  class='" +
                                    CLASS_2_TD2_LEFT +
                                    "'><div align='left'>&nbsp;</td>");
                        out.println("  <td align='center' nowrap class='" +
                                    CLASS_2_TD2_LEFT + "'>&nbsp;</td>");

                        if(bqljs != null)
                        {
                            index++;
                            out.println("  <td align='center' nowrap class='" +
                                        (index < count ? CLASS_2_TD2_LEFT :
                                         CLASS_2_TD2_CENTER) + "'>&nbsp;</td>");
                        }
                        if(ncs != null)
                        {
                            index++;
                            out.println("  <td align='center' nowrap class='" +
                                        (index < count ? CLASS_2_TD2_LEFT :
                                         CLASS_2_TD2_CENTER) +
                                        "'>&nbsp;</td>");
                        }
                        if(nms != null)
                        {
                            index++;
                            out.println("  <td align='center' nowrap class='" +
                                        (index < count ? CLASS_2_TD2_LEFT :
                                         CLASS_2_TD2_CENTER) + "'>&nbsp;</td>");
                        }
                    }

                    out.println(" </tr>");

                }

            }

        }
        catch(java.io.IOException ex)
        {
            throw new JspException("输出错误！");
        }

    }

    /**
     * 设置自动计算，此函数事实上已无用处，但为了保持一致性，因此保留了下来
     * @param bjxz String 编辑性质
     * @return Strking
     */
    private String drawCompute(String bjxz)
    {
//      return " onchange='return compute"+sbbName+"()' ";
        return " ";
    }

    /**
     * 输出申报表中数据部分
     * @throws JspException
     */
    private void drawSbbData() throws JspException
    {
        try
        {
            JspWriter out = pageContext.getOut();
            out.println("<script language ='javascript'>");

            String[] thisHc = BeanUtils.getArrayProperty(sbbForm, sbbhc);
            if(thisHc != null)
            {
                String[] thisBqljs = null;
                if(bqljs != null)
                    thisBqljs = BeanUtils.getArrayProperty(sbbForm, bqljs);

                String[] thisNcs = null;
                if(ncs != null)
                    thisNcs = BeanUtils.getArrayProperty(sbbForm, ncs);
                String[] thisNms = null;
                if(nms != null)
                    thisNms = BeanUtils.getArrayProperty(sbbForm, nms);

                for(int i = 0; i < thisHc.length; i++)
                {

                    if(bqljs != null)
                    {
                        if(thisBqljs[i] != null &&
                           thisBqljs[i].trim().length() > 0)
                        {
                            out.println(this.formName + "." + sbbName + "_B_" +
                                        thisHc[i] + ".value = '" + thisBqljs[i] +
                                        "';");
                        }
                    }
                    if(ncs != null)
                    {
                        if(thisNcs[i] != null && thisNcs[i].trim().length() > 0)
                        {
                            out.println(this.formName + "." + sbbName + "_C_" +
                                        thisHc[i] + ".value = '" + thisNcs[i] +
                                        "';");
                        }
                    }
                    if(nms != null)
                    {
                        if(thisNms[i] != null && thisNms[i].trim().length() > 0)
                        {
                            out.println(this.formName + "." + sbbName + "_M_" +
                                        thisHc[i] + ".value = '" + thisNms[i] +
                                        "';");
                        }
                    }

                }
            }

            out.println("</script>");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new JspException("输出错误！");
        }
    }

    /**
     *
     */
    public void release()
    {
        super.release();

        sbbForm = null;

        defineList = null;

        formName = null;
        sbbName = null;

        sbbhc = null;
        ncs = null;
        nms = null;
        bqljs = null;
        scope = null;
    }

    public String getFormName()
    {
        return formName;
    }

    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    private String changeGsVarName(String jsgs, String replaceName)
    {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < jsgs.length(); i++)
        {
            char c = jsgs.charAt(i);
            if(c == 'x' || c == 'X')
            {
                buffer.append(replaceName);
            }
            else
            {
                buffer.append(c);
            }
        }

        return buffer.toString();
    }

    public String getXmmc()
    {
        return xmmc;
    }

    public void setXmmc(String xmmc)
    {
        this.xmmc = xmmc;
    }

}