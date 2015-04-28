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
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 代征代扣代缴Action </p>
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

    //分页操作的类型
    private static final String OPTTYPE_FIRST = "FIRST";
    private static final String OPTTYPE_FORWARD = "FORWARD";
    private static final String OPTTYPE_NEXT = "NEXT";
    private static final String OPTTYPE_LAST = "LAST";
    private static final String ERR_MSG = "请检查其他记录,确保数据正确性,然后重新导入!";

    /**
      * 初始化显示处理
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

             //1. 总控中获得纳税人和录入人信息
             String jsjdm = getUserData(request).yhid;
             myForm.setJsjdm(jsjdm); //计算机代码
             //录入人
             myForm.setLrr(getUserData(request).yhid);
             if(getUserData(request).getGxswjgzzjgdm() != null)
             {
                 myForm.setErrTag(DsdzdkForm.HASERR);
                 myForm.setSwjgzzjgdm(getUserData(request).getGxswjgzzjgdm());
             }

             //3. 得到此计算机用户的登记信息并填写form中的相应字段
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
      * 上传处理
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
             //先清除上次上传的文件信息
             myForm.setSplitPageList(new ArrayList());
             myForm.setMaxpageindex(0);
             //设置第一页为显示的数据
             myForm.setDsdzdkmxItemList(new ArrayList());
             myForm.setPageindex(0);

             //得到上传的文件并解析成分页数据
             FormFile theFile = myForm.getTheFile();
             List splitList = parseExcelFile(theFile);
             theFile = null;
             myForm.setTheFile(null);
             //把分页数据挂到Form中
             myForm.setSplitPageList(splitList);
             myForm.setMaxpageindex(splitList.size());
             //设置第一页为显示的数据
             myForm.setDsdzdkmxItemList((List)splitList.get(0));
             myForm.setPageindex(1);

             //检查上传数据的合法性
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
     * 返回处理
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
      * 分页处理
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
        //得到Form
         DsdzdkForm myForm = (DsdzdkForm)form;
         //得到当前用户的操作类型
         String optType = myForm.getViewPageType();
         //得到当前用户对应的jsp页面
         String optJspTag = myForm.getOptJspTag();
         if(optJspTag.equals(DsdzdkForm.OPTJSP_DR))
         {
             if(optType == null)
             {
                 return mapping.findForward("SplitPage");
             }

             //确认用户操作返回相应的显示数据
             //得到splitPageList
             List splitPageList = myForm.getSplitPageList();
             {
                 if(splitPageList == null || splitPageList.size() < 1)
                 {
                     return mapping.findForward("SplitPage");
                 }
             }
             //得到当前用户显示的导入jsp页面的页索引
             int curPageIndex = myForm.getPageindex();
             if(optType.equals(OPTTYPE_FIRST)) //1.第一页操作
             {
                 myForm.setDsdzdkmxItemList( (List)splitPageList.get(0));
                 curPageIndex = 1;
             }
             if(optType.equals(OPTTYPE_FORWARD)) //2.前一页
             {
                 if(curPageIndex > 1)
                 {
                     curPageIndex--;
                     myForm.setDsdzdkmxItemList((List)splitPageList.get(curPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_NEXT)) //3.下一页
             {
                 if(curPageIndex < myForm.getMaxpageindex())
                 {
                     curPageIndex++;
                     myForm.setDsdzdkmxItemList((List)splitPageList.get(curPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_LAST)) //最后一页
             {
                 myForm.setDsdzdkmxItemList((List)splitPageList.get(
                     myForm.getMaxpageindex() - 1));
                 curPageIndex = myForm.getMaxpageindex();
             }
             //设置当前页
             myForm.setPageindex(curPageIndex);
             //如果是其他情况就直接返回
             //返回页面
             return mapping.findForward("SplitPage");
         }

         //处理汇总页面
         if(optJspTag.equals(DsdzdkForm.OPTJSP_HZ))
         {
             if(optType == null)
             {
                 return mapping.findForward("SplitPageHz");
             }
             //确认用户操作返回相应的显示数据
             //得到splitPageList
             List splitPageList = myForm.getSplitHzInforList();
             {
                 if(splitPageList == null || splitPageList.size() < 1)
                 {
                     return mapping.findForward("SplitPageHz");
                 }
             }
             //得到当前用户显示的汇总页索引
             int curHzPageIndex = myForm.getHzpageindex();
             if(optType.equals(OPTTYPE_FIRST)) //1.第一页操作
             {
                 myForm.setHzInforList((List)splitPageList.get(0));
                 curHzPageIndex = 1;
             }
             if(optType.equals(OPTTYPE_FORWARD)) //2.前一页
             {
                 if(curHzPageIndex > 1)
                 {
                     curHzPageIndex--;
                     myForm.setHzInforList((List)splitPageList.get(curHzPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_NEXT)) //3.下一页
             {
                 if(curHzPageIndex < myForm.getHzmaxpageindex())
                 {
                     curHzPageIndex++;
                     myForm.setHzInforList((List)splitPageList.get(curHzPageIndex - 1));
                 }
             }
             if(optType.equals(OPTTYPE_LAST)) //最后一页
             {
                 myForm.setHzInforList((List)splitPageList.get(
                     myForm.getHzmaxpageindex() - 1));
                 curHzPageIndex = myForm.getHzmaxpageindex();
             }
             //设置当前页
             myForm.setHzpageindex(curHzPageIndex);
             //如果是其他情况就直接返回
             //返回页面
             return mapping.findForward("SplitPageHz");
         }
         //没有动作
         return  mapping.findForward("Success");
     }


     /**
      * 保存处理
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
         // 检查token
         ActionForward forward = doHandleToken(mapping, request);
         if(forward != null)
         {
             removeForm(mapping, request);
             return forward;
         }

         ActionErrors errors = new ActionErrors();
         //得到form
         DsdzdkForm myForm = (DsdzdkForm)form;
         //得到数据
         List splitdsdzdkList = myForm.getSplitPageList();
         try
         {
             if(splitdsdzdkList == null || splitdsdzdkList.size() < 1)
             {
                 throw new ApplicationException("提交的数据为空!");
             }
             //转换数据为后台的值对象List
             List dsdzdkmxList = itemToVO(splitdsdzdkList, myForm);
             //填写VOPackage
             VOPackage vo = new VOPackage();
             vo.setAction(DsdzdkActionConstant.ACTION_SAVESBMX);
             vo.setProcessor(ProcessorNames.DSDZDK_PROCESSOR);
             vo.setData(dsdzdkmxList);
             vo.setUserData(getUserData(request));
             List resultHzList = null; //返回的缴款主表数据

             resultHzList = (List)ShenbaoProxy.getInstance().process(vo);
             //转换为汇总的前台数据
             if(resultHzList != null)
             {
                 List hzSplitList = splitList(resultHzList,DsdzdkForm.MAXNUMBEREACHPAGE);
                 myForm.setSplitHzInforList(hzSplitList);
                 myForm.setHzInforList((List)hzSplitList.get(0));
                 myForm.setHzmaxpageindex(hzSplitList.size());
                 myForm.setHzpageindex(1);
                 BigDecimal sjjehj = new BigDecimal(0.00);
                 for(int i=0; i<resultHzList.size(); i++)  //对代征数据的实缴税额进行合计
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
      * 查询撤消汇总单数据
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

         //得到form
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
                 throw new ApplicationException("您没有可以撤消的汇总数据!");
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
      * 撤消汇总单数据
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
         // 检查token
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
                 throw new ApplicationException("系统错误,请与管理员联系!");
             }
             SdwszsbhzCx sdwssbhzCx = (SdwszsbhzCx)myForm.getErasehzdList().get(
                 index);
             Sdwszsbhz wsz = (Sdwszsbhz)myForm.getErasejkpzhList().get(0);
             //区县代码
             String qxdm = wsz.getQxdm();
             String sbbh = wsz.getSbbh();   //申报编号
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
                            new ActionError("error.server.custom", "撤消缴款书失败!"));
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
      * 撤消汇总单数据
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
      * 查看缴款凭证号信息
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
      * 撤消汇总数据成功
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
         myForm.setResultMessage("撤消代售代征代扣汇总数据成功!");

//         removeForm(mapping, request);
         return mapping.findForward("EraseHz");
     }


     //得到登记信息并填写到Form中
     private void getDjInfor(HttpServletRequest request, DsdzdkForm myForm)
         throws BaseException
     {
         try
         {
             //调用登记接口得到登记信息
             SWDJJBSJ swdjjbsj = FriendHelper.getSWDJJBSJ(request);
             //把登记信息填充到form中
             //联系电话
             if(swdjjbsj.getZcdzlxdh() != null)
             {
                 myForm.setLxdh(swdjjbsj.getZcdzlxdh());
             }

             //税款所属时期
             Map ssrq = Skssrq.monthSkssrq(new Date());
             myForm.setSkssksrq((Timestamp)ssrq.get(Skssrq.SKSSKSRQ));  //前一个月的1号
             myForm.setSkssjsrq((Timestamp)ssrq.get(Skssrq.SKSSJSRQ));  //前一个月的最后一天

             myForm.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());  //税务机关组织机构

             myForm.setDwmc(swdjjbsj.getNsrmc()); //单位名称
         }
         catch(BaseException ex)
         {
             throw ExceptionUtil.getBaseException(ex);
         }
     }


     //解析Excel文件为值对象列表同时做数据的检查 不合法抛出异常
     private List parseExcelFile(FormFile theFile) throws BaseException
     {
         List splitList = new ArrayList();
         try
         {
             ExcelParser parser = new ExcelParser();
             //解析文件
             List dsdzdkmxItemList =
                 parser.parseExcel(theFile.getInputStream(), DsdzdkmxItem.class);
             //分页存放数据
             return splitList(dsdzdkmxItemList,DsdzdkForm.MAXNUMBEREACHPAGE);
         }
         catch(Exception ex)
         {
             throw new ApplicationException("当前上传的excel文件格式错误!");
         }
     }

     //检查数据的合法性
     private List checkData(List dsdzdkmxItemList) throws BaseException
     {
         List hjList = new ArrayList();
         double sjjehj = 0.00;
         double jsjehj = 0.00;
         //缓存一个dsdzdkmxItemLis的拷贝
         List dsdzdkmxItemListTmp = new ArrayList(dsdzdkmxItemList);
         //1.首先检查是否有重的记录
         List param = new ArrayList();
         //票证种类+完税证号+税种税目代码是唯一的
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
                 throw new ApplicationException("数据不合规则:有重复记录!");
             }
         }
         catch(ApplicationException ex)
         {
             throw ex;
         }
         catch(Exception e)
         {
             throw new ApplicationException("数据不合规则！");
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
                 //检查税种代码必须为两位
                 if(szdm.length() != 2)
                 {
                     throw new ApplicationException(
                         "在第" + (i + 1) + "条记录中:" + "税种代码必须为两位" + ERR_MSG);
                 }

                 //检查szsmdm必须为6位
                 if( (szsmdm.length() != 6))
                 {
                     throw new ApplicationException(
                         "在第" + (i + 1) + "条记录中:" + "税目代码必须为6位" + ERR_MSG);
                 }

                 //检查税种税目代码和税种代码相匹配
                 if(!szsmdm.substring(0, 2).equals(szdm))
                 {
                     throw new ApplicationException(
                         "在第" + (i + 1) + "条记录中:" + "税种代码和税目代码不匹配" + ERR_MSG);
                 }

                 //检查实缴金额不为空
                 double doublesjse = 0.00;
                 if(sjse != null)
                 {

                     if((sjse.indexOf(".")) >= 0 && sjse.length() - sjse.indexOf(".") > 3)
                     {
                         throw new ApplicationException(
                             "在第" + (i + 1) + "条记录中:" + "实缴金额的小数部分不合法:只允许两位." + ERR_MSG);
                     }
                     // 如果实缴金额不是数值，抛出ApplicationException(贺智勇：2003-10-08 修改)
                     try
                     {
                         doublesjse = Double.parseDouble(sjse);
                     }
                     catch(Exception ex)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "实缴金额的数据格式不合法:不是数值." + ERR_MSG);
                     }
                     if (doublesjse - 10000000000000f > 0)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "实缴金额的数据过大." + ERR_MSG);
                     }
                     if(doublesjse <= 0.00)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "实缴金额必须大于0" + ERR_MSG);
                     }
                 }
                 else
                 {
                     throw new ApplicationException(
                        "在第" + (i + 1) + "条记录中:" + "实缴金额不能为空" + ERR_MSG);
                 }

                 //检查税率如果有的话必须是数值型且必须大于0.00000
                 if(sl != null)
                 {
                     if(sl.indexOf(".") >= 0 &&
                        (sl.length() - sl.indexOf(".")) > 6)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "税率的小数部分不合法:最多允许五位." + ERR_MSG);
                     }
                     if(sl.length() > 11)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "税率的位数不合法:最多允许十位数字." + ERR_MSG);
                     }
                     // 如果税率不是数值，抛出ApplicationException(贺智勇：2003-10-08 修改)
                     double doublesl = 0.00000;
                     try
                     {
                         doublesl = Double.parseDouble(sl);
                     }
                     catch(Exception ex)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "税率的数据格式不合法:不是数值." + ERR_MSG);
                     }
                     if(doublesl < 0.00000)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "税率不能小于0" + ERR_MSG);
                     }
                 }
                 double jsjedouble = 0.00;
                 //检查计税金额有的话就必须是数值型的
                 if(jsje != null)
                 {
                     if(jsje.indexOf(".") >= 0 &&
                        (jsje.length() - jsje.indexOf(".")) > 3)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "计税金额的小数部分不合法:只允许两位." + ERR_MSG);
                     }

                     // 如果计税金额不是数值，抛出ApplicationException(贺智勇：2003-10-08 修改)
                     try
                     {
                         jsjedouble = Double.parseDouble(jsje);
                     }
                     catch(Exception ex)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "计税金额的数据格式不合法:不是数值." + ERR_MSG);
                     }
                     if (jsjedouble - 10000000000000f > 0)
                     {
                         throw new ApplicationException(
                            "在第" + (i + 1) + "条记录中:" + "计税金额的数据过大." + ERR_MSG);
                     }
                 }

                 //检查完税证号必须为8位
                 if(wszh.length() != 8)
                 {
                     throw new ApplicationException(
                        "在第" + (i + 1) + "条记录中:" + "完税证号必须为8位." + ERR_MSG);
                 }
                 if(pzzldm.length() > 4)
                 {
                     throw new ApplicationException(
                        "在第" + (i + 1) + "条记录中:" + "票证种类代码长度必须小于4位." + ERR_MSG);
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
                 throw new ApplicationException("数据格式错误！");
             }
         }
         hjList.add(new BigDecimal(jsjehj));
         hjList.add(new BigDecimal(sjjehj));
         return hjList;
     }

    //转换前台数据为后台值对象
    private List itemToVO(List splitList,DsdzdkForm myForm)
    {
        List dsdzdkmxList = new ArrayList();
        Dsdzdkmx dsdzdkmx         = null;//后台值对象
        List dsdzdkmxItemList     = null; //前台显示的List
        DsdzdkmxItem dsdzdkmxItem = null; //前台显示对象

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
                dsdzdkmx.setBdzrmc(dsdzdkmxItem.getBdzrmc()); //备注
                dsdzdkmx.setCjrq(now); //创建时间
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
        //按照汇总单号进行拆分
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
            throw ExceptionUtil.getBaseException(ex, "拆分数据失败！");
        }
    }

    /**
     * 三代数据打印动作
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
                //区县代码
                String qxdm =
                    FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm().substring(0, 2);

                Map eraseMap = new HashMap(2);
                eraseMap.put(DsdzdkConstant.KEY_QXDM, qxdm);
                eraseMap.put(DsdzdkConstant.KEY_SBBH, sbbh);

                VOPackage vo = new VOPackage();
                vo.setAction(DsdzdkActionConstant.ACTION_PRINT); //打印请求
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
                // 一票一税
                return mapping.findForward("ViewYPYS");
            }
            else
            {
                // 一票多税
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
