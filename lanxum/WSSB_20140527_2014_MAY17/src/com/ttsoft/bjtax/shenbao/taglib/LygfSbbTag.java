package com.ttsoft.bjtax.shenbao.taglib;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨ģ��</p>
 * <p>Description: ʵ�ֱ�����˰�걨ģ�飬�˱�ǩ��������걨��form����</p>
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

    //��Ӫ����
    protected final String LXDM_LY = "1";
    //�ɷݴ���
    protected final String LXDM_GF = "2";
    //�������
    protected final String LXDM_ZWHZ = "3";
    //Ͷ��
    protected final String LXDM_TZ = "4";

    //����򳤶�
    protected final int SIZE = 16;
    //������򳤶�
    protected final int SHOT_SIZE = 10;
    //��󳤶�
    protected final int MAXLENGTH = 15;

    //�걨��form bean Name;
    protected String formName;

    private QysdsnbForm sbbForm = null;

    /**
     * The scope to be searched to retrieve the specified bean.
     */
    protected String scope = null;
    //��Ӫ�ɷ����ʹ���
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
            //��Ӫ�ɷ����ʹ���
            String lygfdjzclxdm[] = sbbForm.getLygfdjzclxdm();
            //��ҵ����
            String[] lygfqymc = sbbForm.getLygfqymc();
            //˰��
            String[] lygfsl = sbbForm.getLygfsl();
            //Ӧ������˰��
            String[] lygfybsdse = sbbForm.getLygfybsdse();
            //Ӧ������˰��
            String[] lygfynsdse = sbbForm.getLygfynsdse();
            //Ӧ��˰���ö�
            String[] lygfynssde = sbbForm.getLygfynssde();
            //����
            String[] lygflrgx = sbbForm.getLygflrgx();

            /**
             * ��Ӫ�ɷ�˰�տ۳���
             */
            String lygfsskce[] = sbbForm.getLygfsskce();

            //�ϼ�Ӧ������˰��
            double lygfybsdse_sum = 0;
            //�ϼ�Ӧ������˰��
            double lygfynsdse_sum = 0;
            //�ϼ�Ӧ��˰���ö�
            double lygfynssde_sum = 0;
            //�ϼ�����
            double lygflrgx_sum = 0;
            double lygfsskce_sum = 0;

            if(lygfdjzclxdm != null)
            {
                for(int i = 0; i < lygfdjzclxdm.length; i++)
                {
                    if(lygfdjzclxdm[i].equals(qylxdm))
                    {

                        out.println("<tr>");
                        //checkbox ��ҵ���ʹ���
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                                    "\"><input type='checkbox' name='del_" +
                                    qylxdm +
                            "'><input type='hidden' name='lygfdjzclxdm' value='" +
                            qylxdm + "'></td>");

                        //��ҵ����
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfqymc' id='lygfqymc_"+qylxdm+
                            "' value='" +lygfqymc[i] +
                            "' size='30' maxlength='200'></td>");

                        //˰��
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfsl' id='lygfsl_" +
                            qylxdm + "' value='" +
                            lygfsl[i] +
                            "' size='7' onchange=\"return computeLygfItem(this,'" +
                            qylxdm + "','lygfsl')\"  maxlength=7></td>");

                        //�����Ϣ
                        lygflrgx_sum += Double.parseDouble(lygflrgx[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygflrgx' id='lygflrgx_" +
                            qylxdm + "' value='" + lygflrgx[i] +
                            "' size='" + SHOT_SIZE + "'  onchange=\"return computeLygfItem(this,'" +
                            qylxdm + "','lygflrgx')\"  MAXLENGTH=" + MAXLENGTH +" )></td>");

                        //Ӧ��˰���ö�
                        lygfynssde_sum += Double.parseDouble(lygfynssde[i]);
                        out.println(" <td class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfynssde' id='lygfynssde_" +
                            qylxdm + "' value='" + lygfynssde[i] +
                            "' size='" + SIZE + "' readonly class=" +
                            CLASS_INPUT_NOBORDER + "  MAXLENGTH=" + MAXLENGTH +"></td>");

                        //Ӧ������˰��
                        lygfynsdse_sum += Double.parseDouble(lygfynsdse[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfynsdse' id='lygfynsdse_" +
                            qylxdm + "' value='" + lygfynsdse[i] +
                            "' size='" + SIZE + "'  readonly class=" +
                            CLASS_INPUT_NOBORDER + " MAXLENGTH=" + MAXLENGTH +"></td>");

                        //˰�տ۳���
                        lygfsskce_sum += Double.parseDouble(lygfsskce[i]);
                        out.println(" <td  class=\"" +
                                    CLASS_2_TD2_LEFT +
                            "\"><input type='text' name='lygfsskce' id='lygfsskce_" +
                            qylxdm + "' value='" + lygfsskce[i] +
                            "'  size='" + SHOT_SIZE + "' readonly class=" +
                            CLASS_INPUT_NOBORDER + " MAXLENGTH=" + MAXLENGTH +"></td>");

                        //Ӧ������˰��
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
                        CLASS_2_TD2_LEFT + "> �� �� </td>");
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        ">&nbsp;</td>");

            //�ֻ����󡢹�Ϣ������������ϼ�
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygflrgx_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygflrgx_sum) + "' size='10' readonly class=" +
                        CLASS_INPUT_NOBORDER +
                        "></td>");

            //����Ӧ��˰���ö�ϼ�
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygfynssde_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfynssde_sum) +
                        "' size='16' readonly class=" + CLASS_INPUT_NOBORDER +
                        "></td>");

            //Ӧ������˰��ϼ�
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygfynsdse_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfynsdse_sum) +
                        "' size='16' readonly class=" +
                        CLASS_INPUT_NOBORDER +
                        "></td>");

            //˰�տ۳���ϼ�
            out.println(" <td  class=" + CLASS_2_TD2_LEFT +
                        "><input type='text'  name='lygfsskce_" + qylxdm +
                        "_sum' value='" +
                        nf.format(lygfsskce_sum) +
                        "' size='10' readonly class=" +
                        CLASS_INPUT_NOBORDER +
                        "></td>");

            //Ӧ������˰��ϼ�
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
            throw new JspException("�������");
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