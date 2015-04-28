package com.ttsoft.bjtax.shenbao.taglib;

/**
 * <p>Title: 北京地税综合管理系统  申报模块</p>
 * <p>Description: 实现北京地税申报模块，此标签必须放在申报表form表单中</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.struts.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.util.ResponseUtils;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web.QysdsnbForm;

public class LygfSbbTag extends TagSupport
{

    protected final String CLASS_2_TD1_LEFT = "2-td1-left";
    protected final String CLASS_2_TD1_CENTER = "2-td1-center";
    protected final String CLASS_2_TD2_LEFT = "2-td2-left";
    protected final String CLASS_2_TD2_CENTER = "2-td2-center";
    protected final String CLASS_TABLE_99 = "table-99";
    protected final String CLASS_INPUT_NOBORDER = "inputnoborder";

    //联营代码
    protected final String LXDM_LY = "1";
    //股份代码
    protected final String LXDM_GF = "2";
    //中外合资
    protected final String LXDM_ZWHZ = "3";
    //投资
    protected final String LXDM_TZ = "4";

    //输入框长度
    protected final int SIZE = 16;
    //短输入框长度
    protected final int SHOT_SIZE = 10;
    //最大长度
    protected final int MAXLENGTH = 15;

    //申报表form bean Name;
    protected String formName;

    private QysdsnbForm sbbForm = null;

    /**
     * The scope to be searched to retrieve the specified bean.
     */
    protected String scope = null;
    //联营股份类型代码
    private String qylxdm;

    public String getScope()
    {
        return(this.scope);
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public String getFormName()
    {
        return formName;
    }

    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    /**
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

        if(bean instanceof QysdsnbForm)
        {
            if(bean != null)
            {
                sbbForm = (QysdsnbForm)bean;

                drawSbb();
            }
        }

        // Continue processing this page
        return(SKIP_BODY);

    }

    private void drawSbb() throws JspException
    {
        try
        {
            JspWriter out = pageContext.getOut();
            //联营股份类型代码
            String lygfdjzclxdm[] = sbbForm.getLygfdjzclxdm();
            //企业名称
            String[] lygfqymc = sbbForm.getLygfqymc();
            //税率
            String[] lygfsl = sbbForm.getLygfsl();
            //应补所得税额
            String[] lygfybsdse = sbbForm.getLygfybsdse();
            //应纳所得税额
            String[] lygfynsdse = sbbForm.getLygfynsdse();
            //应纳税所得额
            String[] lygfynssde = sbbForm.getLygfynssde();
            //利润
            String[] lygflrgx = sbbForm.getLygflrgx();

            /**
             * 联营股份税收扣除额
             */
            String lygfsskce[] = sbbForm.getLygfsskce();

            //合计应补所得税额
            double lygfybsdse_sum = 0;
            //合计应纳所得税额
            double lygfynsdse_sum = 0;
            //合计应纳税所得额
            double lygfynssde_sum = 0;
            //合计利润
            double lygflrgx_sum = 0;
            double lygfsskce_sum = 0;

            if(lygfdjzclxdm != null)
            {
                for(int i = 0; i < lygfdjzclxdm.length; i++)
                {
                    if(lygfdjzclxdm[i].equals(qylxdm))
                    {

                        out.println("<tr>");
                        //checkbox 企业类型代码
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                                    "\"><input type='checkbox' name='del_" +
                                    qylxdm +
                            "'><input type='hidden' name='lygfdjzclxdm' value='" +
                            qylxdm + "'></td>");

                        //企业名称
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfqymc' id='lygfqymc_"+qylxdm+
                            "' value='" +lygfqymc[i] +
                            "' size='30' maxlength='200'></td>");

                        //税率
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfsl' id='lygfsl_" +
                            qylxdm + "' value='" +
                            lygfsl[i] +
                            "' size='7' onchange=\"return computeLygfItem(this,'" +
                            qylxdm + "','lygfsl')\"  maxlength=7></td>");

                        //利润股息
                        lygflrgx_sum += Double.parseDouble(lygflrgx[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygflrgx' id='lygflrgx_" +
                            qylxdm + "' value='" + lygflrgx[i] +
                            "' size='" + SHOT_SIZE + "'  onchange=\"return computeLygfItem(this,'" +
                            qylxdm + "','lygflrgx')\"  MAXLENGTH=" + MAXLENGTH +" )></td>");

                        //应纳税所得额
                        lygfynssde_sum += Double.parseDouble(lygfynssde[i]);
                        out.println(" <td class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfynssde' id='lygfynssde_" +
                            qylxdm + "' value='" + lygfynssde[i] +
                            "' size='" + SIZE + "' readonly class=" +
                            CLASS_INPUT_NOBORDER + "  MAXLENGTH=" + MAXLENGTH +"></td>");

                        //应纳所得税额
                        lygfynsdse_sum += Double.parseDouble(lygfynsdse[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfynsdse' id='lygfynsdse_" +
                            qylxdm + "' value='" + lygfynsdse[i] +
                            "' size='" + SIZE + "'  readonly class=" +
                            CLASS_INPUT_NOBORDER + " MAXLENGTH=" + MAXLENGTH +"></td>");

                        //税收扣除额
                        lygfsskce_sum += Double.parseDouble(lygfsskce[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfsskce' id='lygfsskce_" +
                            qylxdm + "' value='" + lygfsskce[i] +
                            "'  size='" + SHOT_SIZE + "' readonly class=" +
                            CLASS_INPUT_NOBORDER + " MAXLENGTH=" + MAXLENGTH +"></td>");

                        //应补所得税额
                        lygfybsdse_sum += Double.parseDouble(lygfybsdse[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_CENTER +
                            "\"><input type='text' name='lygfybsdse' id='lygfybsdse_" +
                            qylxdm + "' value='" + lygfybsdse[i] +
                            "'  size='" + SIZE + "' readonly class=" +
                            CLASS_INPUT_NOBORDER + " MAXLENGTH=" + MAXLENGTH +"></td>");
                        out.println("</tr>");
                    }

                }
            }

            java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
            //write sum message
            out.println("<tr>");
            out.println(" <td colspan=2 class=" +
                        CLASS_2_TD2_LEFT + "> 合 计 </td>");
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        ">&nbsp;</td>");

            //分回利润、股息、红利、收益合计
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygflrgx_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygflrgx_sum) + "' size='10' readonly class=" +
                        CLASS_INPUT_NOBORDER +
                        "></td>");

            //换算应纳税所得额合计
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygfynssde_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfynssde_sum) +
                        "' size='16' readonly class=" + CLASS_INPUT_NOBORDER +
                        "></td>");

            //应纳所得税额合计
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygfynsdse_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfynsdse_sum) +
                        "' size='16' readonly class=" +
                        CLASS_INPUT_NOBORDER +
                        "></td>");

            //税收扣除额合计
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygfsskce_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfsskce_sum) +
                        "' size='10' readonly class=" +
                        CLASS_INPUT_NOBORDER +
                        "></td>");

            //应补所得税额合计
            out.println(" <td  class=" + CLASS_2_TD2_CENTER +
                        "><input type='text' name='lygfybsdse_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfybsdse_sum) +
                        "'  size='16' readonly class=" + CLASS_INPUT_NOBORDER +
                        "></td>");
            out.println("</tr>");

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new JspException("输出错误！");
        }
    }

    public String getQylxdm()
    {
        return qylxdm;
    }

    public void setQylxdm(String qylxdm)
    {
        this.qylxdm = qylxdm;
    }

}