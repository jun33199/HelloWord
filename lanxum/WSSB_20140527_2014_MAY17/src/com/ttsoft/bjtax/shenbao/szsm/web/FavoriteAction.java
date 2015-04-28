package com.ttsoft.bjtax.shenbao.szsm.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class FavoriteAction extends ShenbaoAction
{
    public FavoriteAction()
    {
    }

    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
    	UserData ud = (UserData)this.getUserData(request);
    	SzsmInfoHelper.putSzsmInfoIntoSession(request,ud,"");
        try
        {
            //String jsjdm = this.getUserData(request).yhid;

            //SWDJJBSJ djsj = FriendHelper.getSWDJJBSJ(request);

            // 取出税种税目树信息
            //SzsmHelper helper = new SzsmHelper(this.getUserData(request));

           // Map szsmTreeInfo = helper.getSzsmTreeInfo(djsj);

            FavoriteForm myForm = (FavoriteForm)form;
            myForm.setPreviousSzsm((List)request.getSession().getAttribute("favoriteSzsm"));
            myForm.setToBeAlertSzsm((List)request.getSession().getAttribute("toBeAlertszsm"));
            myForm.setWtdzszsm((List)request.getSession().getAttribute("wtdzszsm"));
           /* List l = (List)request.getSession().getAttribute("favoriteSzsm");
            if(l!=null){
            	System.out.println(l.size());
            	for(int i=0;i<l.size();i++){
            		Sqsbtmp tmp = (Sqsbtmp)l.get(i);
                	System.out.println(tmp.getSzsmdm());
                	
                }
            }else{
            	System.out.println("常用税种税目为空");
            }
            /*myForm.setPreviousSzsm((List)szsmTreeInfo.get("favorite"));

            myForm.setSzsm((List)szsmTreeInfo.get("szsm"));*/

            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        try
        {
            // 检查token
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null)
            {
            	
                return forward;
            }

            String jsjdm = getUserData(request).yhid;

            FavoriteForm myForm = (FavoriteForm)form;
            String[] szsmdm = myForm.getChecked_szsmdm();
           
            List sqsbtmpList = null;

            Timestamp now = new Timestamp(System.currentTimeMillis());

            String swjgzzjgdm = FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm();

            Map data = new HashMap(2);

            data.put("swjgzzjgdm", swjgzzjgdm);
            //System.out.println("取出的szsmdm集合为"+szsmdm);
            if (szsmdm != null)
            {
                int length = szsmdm.length;
                sqsbtmpList = new ArrayList(length);
                //System.out.println(length);
                for(int i = 0; i < length; i++)
                {
                	//System.out.println(szsmdm[i]+"*************");
                    Sqsbtmp sqsbtmp = new Sqsbtmp();
                    sqsbtmp.setJsjdm(jsjdm);
                    sqsbtmp.setSzsmdm(szsmdm[i]);
                    sqsbtmp.setSzsmmc(" ");
                    sqsbtmp.setLrrq(now);
                    sqsbtmp.setCjrq(now);
                    sqsbtmp.setSwjgzzjgdm(swjgzzjgdm);
                    sqsbtmp.setQxdm(swjgzzjgdm.substring(0, 2));
                    sqsbtmpList.add(sqsbtmp);
                }

                data.put("favorite", sqsbtmpList);
            }
            UserData ud = (UserData)this.getUserData(request);
            SzsmHelper helper = new SzsmHelper(ud);
            helper.refreshFavorite(data);
            SzsmInfoHelper.refreshFavoriteSzsmInSession(request,ud,"");
          /* System.out.println("刷新session后结果");
           List l = (List)request.getSession().getAttribute("favoriteSzsm");
            if(l!=null){
            	System.out.println(l.size());
            	for(int i=0;i<l.size();i++){
            		Sqsbtmp tmp = (Sqsbtmp)l.get(i);
                	System.out.println(tmp.getSzsmdm());
                	
                }
            }else{
            	System.out.println("常用税种税目为空");
            }*/
            
            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}