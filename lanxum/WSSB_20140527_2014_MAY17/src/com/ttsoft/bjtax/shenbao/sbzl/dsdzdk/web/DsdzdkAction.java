package com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.bjtax.shenbao.model.client.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.framework.action.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import java.text.SimpleDateFormat;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.shenbao.zhsb.*;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �������۴���Action </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-7
 */
public class DsdzdkAction extends ShenbaoAction
{
    public DsdzdkAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.SD;
    }

    //��ҳ����������
    private static final String OPTTYPE_FIRST = "FIRST";
    private static final String OPTTYPE_FORWARD = "FORWARD";
    private static final String OPTTYPE_NEXT = "NEXT";
    private static final String OPTTYPE_LAST = "LAST";
    private static final String ERR_MSG = "����������¼,ȷ��������ȷ��,Ȼ�����µ���!";

    /**
      * ��ʼ����ʾ����
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
     {
         ActionErrors errors = new ActionErrors();
         try
         {
             DsdzdkForm myForm = (DsdzdkForm)form;
             myForm.setCurEraseIndex(0);
             myForm.setDsdzdkmxItemList(new ArrayList());
             myForm.setErasehzdList(new ArrayList());
             myForm.setErasejkpzhList(new ArrayList());
             myForm.setHzInforList(new ArrayList());
             myForm.setHzmaxpageindex(0);
             myForm.setHzpageindex(0);
             myForm.setMaxpageindex(0);
             myForm.setOptJspTag(DsdzdkForm.OPTJSP_DR);
             myForm.setPageindex(0);
             myForm.setSplitHzInforList(new ArrayList());
             myForm.setSplitPageList(new ArrayList());
             myForm.setTheFile(null);
             myForm.setViewPageType("");

             //1. �ܿ��л����˰�˺�¼������Ϣ
             String jsjdm = getUserData(request).yhid;
             myForm.setJsjdm(jsjdm); //���������
             //¼����
             myForm.setLrr(getUserData(request).yhid);
             if(getUserData(request).getGxswjgzzjgdm() != null)
             {
                 myForm.setErrTag(DsdzdkForm.HASERR);
                 myForm.setSwjgzzjgdm(getUserData(request).getGxswjgzzjgdm());
             }

             //3. �õ��˼�����û��ĵǼ���Ϣ����дform�е���Ӧ�ֶ�
             getDjInfor(request, myForm);
         }
         catch(ApplicationException e)
         {
             errors.add("", new ActionError("error.server.custom", e.getMessage()));
             saveErrors(request, errors);
         }
         catch(Exception ex)
         {
             errors.add("", new ActionError("error.server.default"));
             saveErrors(request, errors);
         }
         return mapping.findForward("Show");
     }

     /**
      * �ϴ�����
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      * @throws BaseException
      */
     public ActionForward doUpload(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
         throws BaseException
     {
         DsdzdkForm myForm = null;
         try
         {
             myForm = (DsdzdkForm)form;
             //������ϴ��ϴ����ļ���Ϣ
             myForm.setSplitPageList(new ArrayList());
             myForm.setMaxpageindex(0);
             //���õ�һҳΪ��ʾ������
             myForm.setDsdzdkmxItemList(new ArrayList());
             myForm.setPageindex(0);

             //�õ��ϴ����ļ��������ɷ�ҳ����
             FormFile theFile = myForm.getTheFile();
             List splitList = parseExcelFile(theFile);
             theFile = null;
             myForm.setTheFile(null);
             //�ѷ�ҳ���ݹҵ�Form��
             myForm.setSplitPageList(splitList);
             myForm.setMaxpageindex(splitList.size());
             //���õ�һҳΪ��ʾ������
             myForm.setDsdzdkmxItemList((List)splitList.get(0));
             myForm.setPageindex(1);

             //����ϴ����ݵĺϷ���
             List dsdzdkmxItemTmpList = new ArrayList();
             for(int i=0; i<splitList.size(); i++)
             {
                 dsdzdkmxItemTmpList.addAll((List)splitList.get(i));
             }
             List hjList = checkData(dsdzdkmxItemTmpList);
             myForm.setJsjehj(((BigDecimal)hjList.get(0)).setScale(2,BigDecimal.ROUND_HALF_UP));
             myForm.setSjjehj(((BigDecimal)hjList.get(1)).setScale(2,BigDecimal.ROUND_HALF_UP));
             myForm.setErrTag(myForm.NOERR);
             return mapping.findForward("Upload");
         }
         catch(Exception ex)
         {
             myForm.setErrTag(myForm.HASERR);
             throw ExceptionUtil.getBaseException(ex);
         }
     }

     /**
     * ���ش���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doBack(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    {
        ActionErrors errors = new ActionErrors();
        try
        {
            DsdzdkForm myForm  = (DsdzdkForm)form;
            myForm.setCurEraseIndex(0);
            myForm.setDsdzdkmxItemList(new ArrayList());
            myForm.setErasehzdList(new ArrayList());
            myForm.setErasejkpzhList(new ArrayList());
            myForm.setHzInforList(new ArrayList());
            myForm.setHzmaxpageindex(0);
            myForm.setHzpageindex(0);
            myForm.setMaxpageindex(0);
            myForm.setOptJspTag(DsdzdkForm.OPTJSP_DR);
            myForm.setPageindex(0);
            myForm.setSplitHzInforList(new ArrayList());
            myForm.setSplitPageList(new ArrayList());
            myForm.setTheFile(null);
            myForm.setViewPageType("");
        }
        catch(Exception ex)
        {
            errors.add("", new ActionError("error.server.default"));
            saveErrors(request, errors);
        }
        return mapping.findForward("Show");
    }


     /**
      * ��ҳ����
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doSplitPage(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
     {
         ActionErrors errors = new ActionErrors();
        //�õ�Form
         DsdzdkForm myForm = (DsdzdkForm)form;
         //�õ���ǰ�û��Ĳ�������
         String optType = myForm.getViewPageType();
         //�õ���ǰ�û���Ӧ��jspҳ��
         String optJspTag = myForm.getOptJspTag();
         if(optJspTag.equals(DsdzdkForm.OPTJSP_DR))
         {
             if(optType == null)
             {
                 return mapping.findForward("SplitPage");
             }

             //ȷ���û�����������Ӧ����ʾ����
             //�õ�splitPageList
             List splitPageList = myForm.getSplitPageList();
             {
                 if(splitPageList == null || splitPageList.size() < 1)
                 {
                     return mapping.findForward("SplitPage");
                 }
             }
             //�õ���ǰ�û���ʾ�ĵ���jspҳ���ҳ����
             int curPageIndex = myForm.getPageindex();
             if(optType.equals(OPTTYPE_FIRST)) //1.��һҳ����
             {
                 myForm.setDsdzdkmxItemList( (List)splitPageList.get(0));
                 curPageIndex = 1;
             }
             if(optType.equals(OPTTYPE_FORWARD)) //2.ǰһҳ
             {
                 if(curPageIndex > 1)
                 {
                     curPageIndex--;
                     myForm.setDsdzdkmxItemList((List)splitPageList.get(curPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_NEXT)) //3.��һҳ
             {
                 if(curPageIndex < myForm.getMaxpageindex())
                 {
                     curPageIndex++;
                     myForm.setDsdzdkmxItemList((List)splitPageList.get(curPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_LAST)) //���һҳ
             {
                 myForm.setDsdzdkmxItemList((List)splitPageList.get(
                     myForm.getMaxpageindex() - 1));
                 curPageIndex = myForm.getMaxpageindex();
             }
             //���õ�ǰҳ
             myForm.setPageindex(curPageIndex);
             //��������������ֱ�ӷ���
             //����ҳ��
             return mapping.findForward("SplitPage");
         }

         //�������ҳ��
         if(optJspTag.equals(DsdzdkForm.OPTJSP_HZ))
         {
             if(optType == null)
             {
                 return mapping.findForward("SplitPageHz");
             }
             //ȷ���û�����������Ӧ����ʾ����
             //�õ�splitPageList
             List splitPageList = myForm.getSplitHzInforList();
             {
                 if(splitPageList == null || splitPageList.size() < 1)
                 {
                     return mapping.findForward("SplitPageHz");
                 }
             }
             //�õ���ǰ�û���ʾ�Ļ���ҳ����
             int curHzPageIndex = myForm.getHzpageindex();
             if(optType.equals(OPTTYPE_FIRST)) //1.��һҳ����
             {
                 myForm.setHzInforList((List)splitPageList.get(0));
                 curHzPageIndex = 1;
             }
             if(optType.equals(OPTTYPE_FORWARD)) //2.ǰһҳ
             {
                 if(curHzPageIndex > 1)
                 {
                     curHzPageIndex--;
                     myForm.setHzInforList((List)splitPageList.get(curHzPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_NEXT)) //3.��һҳ
             {
                 if(curHzPageIndex < myForm.getHzmaxpageindex())
                 {
                     curHzPageIndex++;
                     myForm.setHzInforList((List)splitPageList.get(curHzPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_LAST)) //���һҳ
             {
                 myForm.setHzInforList((List)splitPageList.get(
                     myForm.getHzmaxpageindex() - 1));
                 curHzPageIndex = myForm.getHzmaxpageindex();
             }
             //���õ�ǰҳ
             myForm.setHzpageindex(curHzPageIndex);
             //��������������ֱ�ӷ���
             //����ҳ��
             return mapping.findForward("SplitPageHz");
         }
         //û�ж���
         return  mapping.findForward("Success");
     }


     /**
      * ���洦��
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doSave(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
     {
         // ���token
         ActionForward forward = doHandleToken(mapping, request);
         if(forward != null)
         {
             removeForm(mapping, request);
             return forward;
         }

         ActionErrors errors = new ActionErrors();
         //�õ�form
         DsdzdkForm myForm = (DsdzdkForm)form;
         //�õ�����
         List splitdsdzdkList = myForm.getSplitPageList();
         try
         {
             if(splitdsdzdkList == null || splitdsdzdkList.size() < 1)
             {
                 throw new ApplicationException("�ύ������Ϊ��!");
             }
             //ת������Ϊ��̨��ֵ����List
             List dsdzdkmxList = itemToVO(splitdsdzdkList, myForm);
             //��дVOPackage
             VOPackage vo = new VOPackage();
             vo.setAction(DsdzdkActionConstant.ACTION_SAVESBMX);
             vo.setProcessor(ProcessorNames.DSDZDK_PROCESSOR);
             vo.setData(dsdzdkmxList);
             vo.setUserData(getUserData(request));
             List resultHzList = null; //���صĽɿ���������

             resultHzList = (List)ShenbaoProxy.getInstance().process(vo);
             //ת��Ϊ���ܵ�ǰ̨����
             if(resultHzList != null)
             {
                 List hzSplitList = splitList(resultHzList,DsdzdkForm.MAXNUMBEREACHPAGE);
                 myForm.setSplitHzInforList(hzSplitList);
                 myForm.setHzInforList((List)hzSplitList.get(0));
                 myForm.setHzmaxpageindex(hzSplitList.size());
                 myForm.setHzpageindex(1);
                 BigDecimal sjjehj = new BigDecimal(0.00);
                 for(int i=0; i<resultHzList.size(); i++)  //�Դ������ݵ�ʵ��˰����кϼ�
                 {
                     Sdwszsbhz temp = (Sdwszsbhz)resultHzList.get(i);
                     sjjehj = sjjehj.add(temp.getSjse());
                 }
                 myForm.setSjjehj(sjjehj);
             }
         }
         catch(ApplicationException e)
         {
             e.printStackTrace();
             errors.add("", new ActionError("error.server.custom", e.getMessage()));
             saveErrors(request, errors);
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
             errors.add("", new ActionError("error.server.default"));
             saveErrors(request, errors);
         }
         return mapping.findForward("Save");
     }

     /**
      * ��ѯ�������ܵ�����
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doQueryHz(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
     {
         ActionErrors errors = new ActionErrors();

         //�õ�form
         DsdzdkForm myForm = (DsdzdkForm)form;
         VOPackage vo = new VOPackage();
         vo.setAction(DsdzdkActionConstant.ACTION_QUERYHZ);
         vo.setProcessor(ProcessorNames.DSDZDK_PROCESSOR);
         vo.setData(myForm.getJsjdm());
         vo.setUserData(getUserData(request));
         List hzdEraseInfor = new ArrayList();
         try
         {
             hzdEraseInfor = (List)ShenbaoProxy.getInstance().process(vo);
             if(hzdEraseInfor == null || hzdEraseInfor.size() < 1)
             {
                 throw new ApplicationException("��û�п��Գ����Ļ�������!");
             }
             else
             {
                 List curjkpzhList = ((SdwszsbhzCx)hzdEraseInfor.get(0)).getSdwszsbhzList();
                 myForm.setErasejkpzhList(curjkpzhList);
                 myForm.setErasehzdList(hzdEraseInfor);
             }
         }
         catch(ApplicationException e)
         {
             errors.add("", new ActionError("error.server.custom", e.getMessage()));
             saveErrors(request, errors);
         }
         catch(Exception ex)
         {
             errors.add("", new ActionError("error.server.default"));
             saveErrors(request, errors);
         }
         return mapping.findForward("QueryHz");
     }


     /**
      * �������ܵ�����
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doEraseHz(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
     {
         // ���token
         ActionForward forward = doHandleToken(mapping, request);
         if(forward != null)
         {
             removeForm(mapping, request);
             return forward;
         }

         ActionErrors errors = new ActionErrors();
         DsdzdkForm myForm = (DsdzdkForm)form;
         try
         {
             int index = myForm.getCurEraseIndex();
             if(index > myForm.getErasehzdList().size())
             {
                 throw new ApplicationException("ϵͳ����,�������Ա��ϵ!");
             }
             SdwszsbhzCx sdwssbhzCx = (SdwszsbhzCx)myForm.getErasehzdList().get(
                 index);
             Sdwszsbhz wsz = (Sdwszsbhz)myForm.getErasejkpzhList().get(0);
             //���ش���
             String qxdm = wsz.getQxdm();
             String sbbh = wsz.getSbbh();   //�걨���
             Map eraseMap = new HashMap();

             eraseMap.put(DsdzdkConstant.KEY_QXDM, qxdm);
             eraseMap.put(DsdzdkConstant.KEY_SBHZDH, sdwssbhzCx.getSbhzdh());
             eraseMap.put(DsdzdkConstant.KEY_SBBH, sbbh);

             VOPackage vo = new VOPackage();
             vo.setAction(DsdzdkActionConstant.ACTION_ERASEHZ);
             vo.setProcessor(ProcessorNames.DSDZDK_PROCESSOR);
             vo.setData(eraseMap);
             vo.setUserData(getUserData(request));

             Boolean result = (Boolean)ShenbaoProxy.getInstance().process(vo);
             if(result.booleanValue() == false)
             {
                 errors.add("",
                            new ActionError("error.server.custom", "�����ɿ���ʧ��!"));
                 saveErrors(request, errors);
             }
             else
             {
                 List eraseHzList = myForm.getErasehzdList();
                 eraseHzList.remove(index);
                 if(eraseHzList.size() > 0)
                 {
                     sdwssbhzCx = (SdwszsbhzCx)eraseHzList.get(0);
                     myForm.setErasejkpzhList(sdwssbhzCx.getSdwszsbhzList());
                 }
                 else
                 {
                     myForm.setErasejkpzhList(new ArrayList());
                 }
             }
//             removeForm(mapping, request);
             return mapping.findForward("EraseHz");
//             return doEraseSuccess(mapping, form, request, response);
         }
         catch(ApplicationException e)
         {
             errors.add("", new ActionError("error.server.custom", e.getMessage()));
             saveErrors(request, errors);
             return mapping.findForward("EraseHz");
         }
         catch(Exception ex)
         {
             errors.add("", new ActionError("error.server.default"));
             saveErrors(request, errors);
             return mapping.findForward("EraseHz");
         }
     }

     /**
      * �������ܵ�����
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doReturnHz(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
     {
         return mapping.findForward("QueryHz");
     }

     /**
      * �鿴�ɿ�ƾ֤����Ϣ
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doViewJkpzh(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
     {
         ActionErrors errors = new ActionErrors();
         try
         {
             DsdzdkForm myForm = (DsdzdkForm)form;
             int index = myForm.getCurEraseIndex();
             SdwszsbhzCx sdwssbhzCx = null;
             sdwssbhzCx = (SdwszsbhzCx)myForm.getErasehzdList().get(index);
             myForm.setErasejkpzhList(sdwssbhzCx.getSdwszsbhzList());
         }
         catch(Exception ex)
         {
             errors.add("", new ActionError("error.server.default"));
             saveErrors(request, errors);
         }
         return mapping.findForward("ViewJkpzh");
     }

     /**
      * �����������ݳɹ�
      * @param mapping ActionMapping
      * @param form ActionForm
      * @param request HttpServletRequest
      * @param response HttpServletResponse
      * @return ActionForward
      */
     public ActionForward doEraseSuccess(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response)
     {
         DsdzdkForm myForm = (DsdzdkForm)form;
         myForm.setCurEraseIndex(0);
         myForm.setDsdzdkmxItemList(new ArrayList());
         myForm.setErasehzdList(new ArrayList());
         myForm.setErasejkpzhList(new ArrayList());
         myForm.setHzInforList(new ArrayList());
         myForm.setHzmaxpageindex(0);
         myForm.setHzpageindex(0);
         myForm.setMaxpageindex(0);
         myForm.setOptJspTag(DsdzdkForm.OPTJSP_DR);
         myForm.setPageindex(0);
         myForm.setSplitHzInforList(new ArrayList());
         myForm.setSplitPageList(new ArrayList());
         myForm.setTheFile(null);
         myForm.setViewPageType("");
         myForm.setErrTag(DsdzdkForm.NOERR);
         myForm.setResultMessage("�������۴������ۻ������ݳɹ�!");

//         removeForm(mapping, request);
         return mapping.findForward("EraseHz");
     }


     //�õ��Ǽ���Ϣ����д��Form��
     private void getDjInfor(HttpServletRequest request, DsdzdkForm myForm)
         throws BaseException
     {
         try
         {
             //���õǼǽӿڵõ��Ǽ���Ϣ
             SWDJJBSJ swdjjbsj = FriendHelper.getSWDJJBSJ(request);
             //�ѵǼ���Ϣ��䵽form��
             //��ϵ�绰
             if(swdjjbsj.getZcdzlxdh() != null)
             {
                 myForm.setLxdh(swdjjbsj.getZcdzlxdh());
             }

             //˰������ʱ��
             Map ssrq = Skssrq.monthSkssrq(new Date());
             myForm.setSkssksrq((Timestamp)ssrq.get(Skssrq.SKSSKSRQ));  //ǰһ���µ�1��
             myForm.setSkssjsrq((Timestamp)ssrq.get(Skssrq.SKSSJSRQ));  //ǰһ���µ����һ��

             myForm.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());  //˰�������֯����

             myForm.setDwmc(swdjjbsj.getNsrmc()); //��λ����
         }
         catch(BaseException ex)
         {
             throw ExceptionUtil.getBaseException(ex);
         }
     }


     //����Excel�ļ�Ϊֵ�����б�ͬʱ�����ݵļ�� ���Ϸ��׳��쳣
     private List parseExcelFile(FormFile theFile) throws BaseException
     {
         List splitList = new ArrayList();
         try
         {
             ExcelParser parser = new ExcelParser();
             //�����ļ�
             List dsdzdkmxItemList =
                 parser.parseExcel(theFile.getInputStream(), DsdzdkmxItem.class);
             //��ҳ�������
             return splitList(dsdzdkmxItemList,DsdzdkForm.MAXNUMBEREACHPAGE);
         }
         catch(Exception ex)
         {
             throw new ApplicationException("��ǰ�ϴ���excel�ļ���ʽ����!");
         }
     }

     //������ݵĺϷ���
     private List checkData(List dsdzdkmxItemList) throws BaseException
     {
         List hjList = new ArrayList();
         double sjjehj = 0.00;
         double jsjehj = 0.00;
         //����һ��dsdzdkmxItemLis�Ŀ���
         List dsdzdkmxItemListTmp = new ArrayList(dsdzdkmxItemList);
         //1.���ȼ���Ƿ����صļ�¼
         List param = new ArrayList();
         //Ʊ֤����+��˰֤��+˰��˰Ŀ������Ψһ��
         param.add("getWszh");
         param.add("getSzsmdm");
         param.add("getPzzldm");
         List splitList = null;
         try
         {
             splitList = FindObjInList.splitListByParam(dsdzdkmxItemList, DsdzdkmxItem.class,
                 param);
             dsdzdkmxItemList.addAll(dsdzdkmxItemListTmp);
             if(splitList.size() != dsdzdkmxItemList.size())
             {
                 throw new ApplicationException("���ݲ��Ϲ���:���ظ���¼!");
             }
         }
         catch(ApplicationException ex)
         {
             throw ex;
         }
         catch(Exception e)
         {
             throw new ApplicationException("���ݲ��Ϲ���");
         }

         String sjse = null;
         String sl = null;
         String jsje = null;
         String szdm = null;
         String szsmdm = null;
         String wszh = null;
         String pzzldm = null;
         DsdzdkmxItem dsdzdkmxItem = null;
         for(int i = 0; i < dsdzdkmxItemList.size(); i++)
         {
             dsdzdkmxItem = (DsdzdkmxItem)dsdzdkmxItemList.get(i);
             sjse = dsdzdkmxItem.getSjse();
             sl = dsdzdkmxItem.getSl();
             jsje = dsdzdkmxItem.getJsje();
             szdm = dsdzdkmxItem.getSzdm();
             szsmdm = dsdzdkmxItem.getSzsmdm();
             wszh = dsdzdkmxItem.getWszh();
             pzzldm = dsdzdkmxItem.getPzzldm();
             try
             {
                 //���˰�ִ������Ϊ��λ
                 if(szdm.length() != 2)
                 {
                     throw new ApplicationException(
                         "�ڵ�" + (i + 1) + "����¼��:" + "˰�ִ������Ϊ��λ" + ERR_MSG);
                 }

                 //���szsmdm����Ϊ6λ
                 if( (szsmdm.length() != 6))
                 {
                     throw new ApplicationException(
                         "�ڵ�" + (i + 1) + "����¼��:" + "˰Ŀ�������Ϊ6λ" + ERR_MSG);
                 }

                 //���˰��˰Ŀ�����˰�ִ�����ƥ��
                 if(!szsmdm.substring(0, 2).equals(szdm))
                 {
                     throw new ApplicationException(
                         "�ڵ�" + (i + 1) + "����¼��:" + "˰�ִ����˰Ŀ���벻ƥ��" + ERR_MSG);
                 }

                 //���ʵ�ɽ�Ϊ��
                 double doublesjse = 0.00;
                 if(sjse != null)
                 {

                     if((sjse.indexOf(".")) >= 0 && sjse.length() - sjse.indexOf(".") > 3)
                     {
                         throw new ApplicationException(
                             "�ڵ�" + (i + 1) + "����¼��:" + "ʵ�ɽ���С�����ֲ��Ϸ�:ֻ������λ." + ERR_MSG);
                     }
                     // ���ʵ�ɽ�����ֵ���׳�ApplicationException(�����£�2003-10-08 �޸�)
                     try
                     {
                         doublesjse = Double.parseDouble(sjse);
                     }
                     catch(Exception ex)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "ʵ�ɽ������ݸ�ʽ���Ϸ�:������ֵ." + ERR_MSG);
                     }
                     if (doublesjse - 10000000000000f > 0)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "ʵ�ɽ������ݹ���." + ERR_MSG);
                     }
                     if(doublesjse <= 0.00)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "ʵ�ɽ��������0" + ERR_MSG);
                     }
                 }
                 else
                 {
                     throw new ApplicationException(
                        "�ڵ�" + (i + 1) + "����¼��:" + "ʵ�ɽ���Ϊ��" + ERR_MSG);
                 }

                 //���˰������еĻ���������ֵ���ұ������0.00000
                 if(sl != null)
                 {
                     if(sl.indexOf(".") >= 0 &&
                        (sl.length() - sl.indexOf(".")) > 6)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "˰�ʵ�С�����ֲ��Ϸ�:���������λ." + ERR_MSG);
                     }
                     if(sl.length() > 11)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "˰�ʵ�λ�����Ϸ�:�������ʮλ����." + ERR_MSG);
                     }
                     // ���˰�ʲ�����ֵ���׳�ApplicationException(�����£�2003-10-08 �޸�)
                     double doublesl = 0.00000;
                     try
                     {
                         doublesl = Double.parseDouble(sl);
                     }
                     catch(Exception ex)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "˰�ʵ����ݸ�ʽ���Ϸ�:������ֵ." + ERR_MSG);
                     }
                     if(doublesl < 0.00000)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "˰�ʲ���С��0" + ERR_MSG);
                     }
                 }
                 double jsjedouble = 0.00;
                 //����˰����еĻ��ͱ�������ֵ�͵�
                 if(jsje != null)
                 {
                     if(jsje.indexOf(".") >= 0 &&
                        (jsje.length() - jsje.indexOf(".")) > 3)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "��˰����С�����ֲ��Ϸ�:ֻ������λ." + ERR_MSG);
                     }

                     // �����˰������ֵ���׳�ApplicationException(�����£�2003-10-08 �޸�)
                     try
                     {
                         jsjedouble = Double.parseDouble(jsje);
                     }
                     catch(Exception ex)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "��˰�������ݸ�ʽ���Ϸ�:������ֵ." + ERR_MSG);
                     }
                     if (jsjedouble - 10000000000000f > 0)
                     {
                         throw new ApplicationException(
                            "�ڵ�" + (i + 1) + "����¼��:" + "��˰�������ݹ���." + ERR_MSG);
                     }
                 }

                 //�����˰֤�ű���Ϊ8λ
                 if(wszh.length() != 8)
                 {
                     throw new ApplicationException(
                        "�ڵ�" + (i + 1) + "����¼��:" + "��˰֤�ű���Ϊ8λ." + ERR_MSG);
                 }
                 if(pzzldm.length() > 4)
                 {
                     throw new ApplicationException(
                        "�ڵ�" + (i + 1) + "����¼��:" + "Ʊ֤������볤�ȱ���С��4λ." + ERR_MSG);
                 }
                 sjjehj += doublesjse;
                 jsjehj += jsjedouble;
             }
             catch(ApplicationException e)
             {
                 throw e;
             }
             catch(Exception ex)
             {
                 throw new ApplicationException("���ݸ�ʽ����");
             }
         }
         hjList.add(new BigDecimal(jsjehj));
         hjList.add(new BigDecimal(sjjehj));
         return hjList;
     }

    //ת��ǰ̨����Ϊ��ֵ̨����
    private List itemToVO(List splitList,DsdzdkForm myForm)
    {
        List dsdzdkmxList = new ArrayList();
        Dsdzdkmx dsdzdkmx         = null;//��ֵ̨����
        List dsdzdkmxItemList     = null; //ǰ̨��ʾ��List
        DsdzdkmxItem dsdzdkmxItem = null; //ǰ̨��ʾ����

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String nd = (new SimpleDateFormat("yyyy")).format(now);
        for(int i=0; i<splitList.size(); i++)
        {
            dsdzdkmxItemList = (List)splitList.get(i);
            for(int j=0; j<dsdzdkmxItemList.size(); j++)
            {
                dsdzdkmx = new Dsdzdkmx();
                dsdzdkmxItem = (DsdzdkmxItem)dsdzdkmxItemList.get(j);
                dsdzdkmx.setBdzrmc(dsdzdkmxItem.getBdzrmc());
                dsdzdkmx.setBdzrmc(dsdzdkmxItem.getBdzrmc()); //��ע
                dsdzdkmx.setCjrq(now); //����ʱ��
                dsdzdkmx.setFsdm(CodeConstant.FSDM_WSSB);
                dsdzdkmx.setJsjdm(myForm.getJsjdm());
                if(dsdzdkmxItem.getJsje() != null)
                {
                    dsdzdkmx.setJsje(new BigDecimal(dsdzdkmxItem.getJsje()));
                }
                dsdzdkmx.setLrr(myForm.getLrr());
                dsdzdkmx.setLrrq(now);
                dsdzdkmx.setQylxdh(myForm.getLxdh());
                dsdzdkmx.setSjse(new BigDecimal(dsdzdkmxItem.getSjse()));
                dsdzdkmx.setSkssjsrq(myForm.getSkssjsrq());
                dsdzdkmx.setSkssksrq(myForm.getSkssksrq());
                if(dsdzdkmxItem.getSl() != null)
                {
                    dsdzdkmx.setSl(new BigDecimal(dsdzdkmxItem.getSl()));
                }
                dsdzdkmx.setSwjgzzjgdm(myForm.getSwjgzzjgdm());
                dsdzdkmx.setSzdm(dsdzdkmxItem.getSzdm());
                dsdzdkmx.setSzmc(dsdzdkmxItem.getSzmc());
                dsdzdkmx.setSzsmdm(dsdzdkmxItem.getSzsmdm());
                dsdzdkmx.setSzsmmc(dsdzdkmxItem.getSzsmmc());
                dsdzdkmx.setWszh(dsdzdkmxItem.getWszh());
                dsdzdkmx.setPzzldm(dsdzdkmxItem.getPzzldm());
                dsdzdkmx.setNd(nd);
                dsdzdkmx.setQxdm(myForm.getSwjgzzjgdm().substring(0, 2));

                dsdzdkmxList.add(dsdzdkmx);
            }
        }
        return dsdzdkmxList;
    }

    private List splitList(List sourcelist, int max)
    {
        List splitList = new ArrayList();
        int eachpageIndex = 1;
        List eachPageList = new ArrayList();
        for(int i = 0; i < sourcelist.size(); i++)
        {
            if(eachpageIndex == max)
            {
                eachPageList.add(sourcelist.get(i));
                splitList.add(eachPageList);
                eachPageList = new ArrayList();
                eachpageIndex = 1;
            }
            else
            {
                eachPageList.add(sourcelist.get(i));
                eachpageIndex++;
            }
        }
        if(eachpageIndex != 1)
        {
            splitList.add(eachPageList);
        }
        return splitList;
    }

    private List createErase(List hzdInforList) throws BaseException
    {
        List cxhzdhList = new ArrayList();
        //���ջ��ܵ��Ž��в��
        List param = new ArrayList();
        param.add("getSbhzdh");
        List splitList = null;
        List tmphzdhList = null;
        Sdwszsbhz tmpSdwszsbhz = null;
        SdwszsbhzCx tmpsdwszsbhzCx = null;
        BigDecimal tmpsjjehz = new BigDecimal(0.00);
        try
        {
            splitList = FindObjInList.splitListByParam(hzdInforList, Sdwszsbhz.class, param);
            boolean eraseable = true;
            for(int i=0; i<splitList.size(); i++)
            {
                eraseable = true;
                tmphzdhList = (List)splitList.get(i);
                for(int j = 0; j < tmphzdhList.size(); j++)
                {
                    tmpSdwszsbhz = (Sdwszsbhz)tmphzdhList.get(j);
                    if(! (tmpSdwszsbhz.getClbjdm().equals(CodeConstant.CLBJDM_WCL)
                          || tmpSdwszsbhz.getClbjdm().equals(CodeConstant.CLBJDM_YSB)))
                    {
                        tmpsjjehz = tmpsjjehz.add(tmpSdwszsbhz.getSjse());
                        eraseable = false;
                        break;
                    }
                }
                if(eraseable)
                {
                    tmpsdwszsbhzCx = new SdwszsbhzCx();
                    tmpsdwszsbhzCx.setHzrq(tmpSdwszsbhz.getHzrq());
                    tmpsdwszsbhzCx.setSbhzdh(tmpSdwszsbhz.getSbhzdh());
                    tmpsdwszsbhzCx.setSjjehz(tmpsjjehz);
                    tmpsdwszsbhzCx.setSdwszsbhzList(tmphzdhList);
                    tmpsdwszsbhzCx.setJkpzhNum(tmphzdhList.size());
                    cxhzdhList.add(tmpsdwszsbhzCx);
                }
            }
            return cxhzdhList;
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�������ʧ�ܣ�");
        }
    }

    /**
     * �������ݴ�ӡ����
     * @param mapping  ActionMapping
     * @param form  ActionForm
     * @param request   HttpServletRequest
     * @param response   HttpServletResponse
     * @return   ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws BaseException
    {
        ActionErrors errors = new ActionErrors();

        try
        {
            String sbbh = request.getParameter("sbbhIndex");

            String preSbbh = ((DsdzdkForm)form).getPreSbbh();

            if (!sbbh.equals(preSbbh))
            {
                //���ش���
                String qxdm =
                    FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm().substring(0, 2);

                Map eraseMap = new HashMap(2);
                eraseMap.put(DsdzdkConstant.KEY_QXDM, qxdm);
                eraseMap.put(DsdzdkConstant.KEY_SBBH, sbbh);

                VOPackage vo = new VOPackage();
                vo.setAction(DsdzdkActionConstant.ACTION_PRINT); //��ӡ����
                vo.setProcessor(ProcessorNames.DSDZDK_PROCESSOR);
                vo.setData(eraseMap);
                vo.setUserData(getUserData(request));

                Object obj = ShenbaoProxy.getInstance().process(vo);

                request.getSession().removeAttribute(SessionKey.JKS);
                request.getSession().setAttribute(SessionKey.JKS, obj);

                ((DsdzdkForm)form).setPreSbbh(sbbh);
            }
            HashMap jksMap = (HashMap)request.getSession().getAttribute(SessionKey.JKS);
            HashMap sbb = (HashMap)jksMap.get(sbbh);

            Integer printTag = (Integer)sbb.get(ZhsbMapConstant.PRINTTAG);

            if(printTag.intValue() == CodeConstant.PRINT_YPYS)
            {
                // һƱһ˰
                return mapping.findForward("ViewYPYS");
            }
            else
            {
                // һƱ��˰
                return mapping.findForward("ViewYPDS");
            }
        }
        catch(ApplicationException e)
        {
            errors.add("", new ActionError("error.server.custom", e.getMessage()));
            saveErrors(request, errors);
        }
        catch(Exception ex)
        {
            errors.add("", new ActionError("error.server.default"));
            saveErrors(request, errors);
        }
        return mapping.findForward("");
    }

    public ActionForward doBacktoCx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws BaseException
    {
        return mapping.findForward("ViewJkpzh");
    }

    public ActionForward doBacktoHz(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws BaseException
    {
        return mapping.findForward("Save");
    }
}
