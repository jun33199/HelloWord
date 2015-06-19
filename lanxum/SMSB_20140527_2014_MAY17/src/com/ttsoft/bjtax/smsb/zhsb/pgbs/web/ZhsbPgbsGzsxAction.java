/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ۺ��걨_������˰��֪�������и�֪��������ʾ�б�������ȷ�Ϻ�ת���ۺ��걨ҳ�档<br>
 * ���û�и�֪������ֱ��ת���ۺ��걨ҳ�档 </p>
 * @author zzb  20090327
 * @version 1.1
 */

public class ZhsbPgbsGzsxAction
    extends SmsbAction
{
  /**
   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
   * @param mapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;��֪����");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "��֪����");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/zhsb/zhsb-000.htm");
  }

  /**
   *���ݼ���������ѯ��֪����
   * ����и�֪��������ʾ
   * ���û�и�֪������ת���ۺ��걨ҳ��
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doQuery(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws
      BaseException
  {
    try
    { 
      //������˰	
      ZhsbPgbsGzsxActionForm form = (ZhsbPgbsGzsxActionForm) actionForm;
      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_ZHSB_GZSX);
      //������˰
      vo.setProcessor(CodeConstant.ZHSB_PGBS_SBLR_PROCESSOR);
      vo.setData(actionForm);
      ArrayList gzsxList = (ArrayList) ZhsbProxy.getInstance().process(vo);

      //0Ϊ����״̬��1Ϊ������״̬
      form.setFzcbs("0");
//����з�������֪������÷�������־
      String names[] =
          {
          "gzsxfcrq", "gzsxnr", "fzcbs"};

      ArrayList mapList = new ArrayList();
      for (int i = 0; i < gzsxList.size(); i++)
      {
        Gzsx temp = (Gzsx) gzsxList.get(i);
        Map map = new HashMap();
        BeanUtil.copyBeanToMap(names, temp, map);
        //ת��vo�е�������ʾ��ʽ
        //map.put("gzsxfcrq",SfTimeUtil.getDateFromDateTime((Timestamp)map.get("gzsxfcrq")) );
        //����б��к��з�������֪���򽫱�ʾ��Ϊ1
        if (temp.getFzcbs().equals("1"))
        {
          form.setFzcbs("1");
        }
        mapList.add(map);
      }

      //form.setDataList(gzsxList);
      form.setDataList(mapList);

      return actionMapping.findForward("Query");
    }
    catch (Exception ex)
    {
      return (new ActionForward(actionMapping.getInput()));
    }
  }

  /**
   *���ݼ���������ѯ��֪����
   * ����и�֪��������ʾ
   * ���û�и�֪������ת���ۺ��걨ҳ��
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creatin
   * @return ActionForward
   * @throws BaseException
   */
  public ActionForward doZhsb(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //������˰
	ZhsbPgbsGzsxActionForm form = (ZhsbPgbsGzsxActionForm) actionForm;
	//������˰
	ZhsbPgbsActionForm zhsb = new ZhsbPgbsActionForm();
    
    zhsb.setJsjdm(form.getJsjdm());
    zhsb.setSbrq(form.getSbrq());
    //zhsb.setJsjdm("06123456");
    zhsb.setGzsx("1");
    //������˰
    httpServletRequest.setAttribute("zhsbPgbsActionForm", zhsb);
    return actionMapping.findForward("Zhsb");
  }

}