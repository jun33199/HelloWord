package com.ttsoft.bjtax.shenbao.zjyjmsb;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbz;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx12;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx3;
import com.ttsoft.bjtax.shenbao.model.client.ZjyjmsbInfor;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zjyjmsb.ZjyjmsbForm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.zjyjmsb.ZjyjmsbConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.util.Skssrq;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �پ�ҵ����˰�걨������</p>
 * <p>Company: ��һ���Źɷ����޹�˾</p>
 * @author  qinwei
 * @version 1.1 2006-4-28
 */
public class ZjyjmsbAction extends ShenbaoAction {
	
	public ZjyjmsbAction()
    {
    }
	
	
	protected int getActionID()
    {
        return SbzlAccess.ZJYJMSB;
    }
    
	/**
     * ��ʼ����ʾ����
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
  
	public ActionForward doShow(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws BaseException {
		
            
            ZjyjmsbForm myForm = (ZjyjmsbForm)form;
            UserData userData = this.getUserData(request);
            String jsjdm = userData.yhid;
            //���ü��������
            myForm.setJsjdm(jsjdm);
            //�������
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Timestamp sbrq = TinyTools.second2Day(now); // �걨����
            String nd = new SimpleDateFormat("yyyy").format(sbrq); //���
            String dqyf = new SimpleDateFormat("MM").format(sbrq);//��ǰ�·�
                
            int yuefen =Integer.parseInt(dqyf);
            int xsjd=0;//���ݵ�ǰ�·�ϵͳ�Զ������ļ���
            int xsyf=0;//���ݵ�ǰ����ϵͳ�Զ��������·�
            int xsnd=0;//��ʾ��ȣ�1-3�µ�˰��������������4����
            
            //�پ�ҵ���������걨��Ϊ0411-0415����ǰ��1���ȡ�0601-0615������4���ȡ�0701-0715����ǰ��2���ȡ�1001-1015����ǰ��3����
            //20080330�µ�ҵ������������˳�4��6��7��10�·ݣ�����ʵ���ϲ�Ӧ��
            //modify by zhangyijun 20080417 ���޸�6��
            
            switch(yuefen){
            //case 1: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1; break;
            //case 2: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
            //case 3: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
            case 4: xsjd=1; xsyf=1;xsnd=Integer.parseInt(nd);break;
            //case 5: xsjd=1; xsyf=1;xsnd=Integer.parseInt(nd);break;
            case 6: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
            case 7: xsjd=2; xsyf=4;xsnd=Integer.parseInt(nd);break;
            //case 8: xsjd=2; xsyf=4;xsnd=Integer.parseInt(nd);break;
            //case 9: xsjd=2; xsyf=4;xsnd=Integer.parseInt(nd);break;
            case 10:xsjd=3; xsyf=7;xsnd=Integer.parseInt(nd);break;
            //case 11:xsjd=3; xsyf=7;xsnd=Integer.parseInt(nd);break;
            //case 12:xsjd=3; xsyf=7;xsnd=Integer.parseInt(nd);break;
            }
            myForm.setJd(Integer.toString(xsjd));
            myForm.setYf(xsyf);
            myForm.setNd(Integer.toString(xsnd));
         
           //ȡ�õǼ�Map
            Map djMap = FriendHelper.getDjInfo(request);
           //�Ǽǻ�������
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get("JBSJ");
            String djcelx = jbsj.getDjzclxdm();
            if(djcelx.equals(ZjyjmsbConstant.DJCELX_GTGSH) || djcelx.equals(ZjyjmsbConstant.DJCELX_GRHH)){
            	myForm.setDjcelx(ZjyjmsbConstant.DJCELX_GTJY);
            }
            else{
            	myForm.setDjcelx(ZjyjmsbConstant.DJCELX_QY);
            }
            myForm.setDh(jbsj.getZcdzlxdh());
            myForm.setNsrmc(jbsj.getNsrmc());
           //���ú�̨EJB�ж��Ƿ����걨����
            Map map = new HashMap();
            map.put("JSJDM", jsjdm);
            map.put("BBND",Integer.toString(xsnd));
            map.put("BBQ",Integer.toString(xsjd));
            VOPackage vo = new VOPackage();
            vo.setUserData(userData);
            vo.setProcessor(ProcessorNames.ZJYJMSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_QUERY);
            vo.setData(map);
            myForm.setActionType("Show");
            try
            {   
             Map mxMap = (Map)ShenbaoProxy.getInstance().process(vo);
            
             List mx12List = (List)mxMap.get("MX12LIST");
           
             Zjyjmsbmx3 zjyjmsbmx3 =(Zjyjmsbmx3)mxMap.get("MX3");

             myForm.setSjpd(mxMap.get("SJPD").toString().matches("true"));
             
             if(mx12List != null)
             {
            	 for(int i=0;i<mx12List.size();i++)
            	 {   
            		 Zjyjmsbmx12 zjyjmsbmx12 = (Zjyjmsbmx12)mx12List.get(i);
            		 
            		if(Integer.parseInt(zjyjmsbmx12.getSbblxdm())==Integer.parseInt(ZjyjmsbConstant.ZJYJMSBB1))
            		 {   
            			 myForm.setJ1(zjyjmsbmx12.getXnxgsyrs());
            			 myForm.setQylx(zjyjmsbmx12.getNsrlxdm());
            			
            		   if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_YYS))
            		   {
            				 myForm.setJ2(zjyjmsbmx12.getJ1());
            				 myForm.setJ3(zjyjmsbmx12.getJ2());
            				 myForm.setJ4(zjyjmsbmx12.getJ3());
            		   }
            		   if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JSS))
            		   {
            				 myForm.setJ5(zjyjmsbmx12.getJ1());
            				 myForm.setJ6(zjyjmsbmx12.getJ2());
            				 myForm.setJ7(zjyjmsbmx12.getJ3());
            		   }
            		   if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JYFFJ))
            		   {
            				 myForm.setJ8(zjyjmsbmx12.getJ1());
            				 myForm.setJ9(zjyjmsbmx12.getJ2());
            				 myForm.setJ10(zjyjmsbmx12.getJ3());
            		   }
            		   if (zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_QYSDS))
            		   {
            				 
            				 myForm.setJ14(zjyjmsbmx12.getJ3());
            				 
            			 }
            		 }
            			 else
            			 {   
            				 myForm.setJ21(zjyjmsbmx12.getXnxgsyrs());
            				 myForm.setQylx1(zjyjmsbmx12.getNsrlxdm());
            				 
            				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_YYS))
                  		     {
                				 myForm.setJ22(zjyjmsbmx12.getJ1());
                				 myForm.setJ23(zjyjmsbmx12.getJ2());
                				 myForm.setJ24(zjyjmsbmx12.getJ3());
                  		     }
            				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JSS))
                  		     {
                				 myForm.setJ25(zjyjmsbmx12.getJ1());
                				 myForm.setJ26(zjyjmsbmx12.getJ2());
                				 myForm.setJ27(zjyjmsbmx12.getJ3());
                  		     }
            				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JYFFJ))
                  		    {
                				 myForm.setJ28(zjyjmsbmx12.getJ1());
                				 myForm.setJ29(zjyjmsbmx12.getJ2());
                				 myForm.setJ30(zjyjmsbmx12.getJ3());
                  		    }
            				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_GRSDS))
                  		    {
            				     myForm.setJ31(zjyjmsbmx12.getJ1());
                				 myForm.setJ32(zjyjmsbmx12.getJ2());
                				 myForm.setJ33(zjyjmsbmx12.getJ3());
                  		    }
                			if (zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_QYSDS))
                      	   {
                				 myForm.setJ34(zjyjmsbmx12.getJ3());
                      	   }
            				
            			 }
            		 
            	 }
             }
             if(zjyjmsbmx3 != null)
             {
            	 myForm.setQnyysr(zjyjmsbmx3.getQnyysr());
        
            	 myForm.setQnjmse(zjyjmsbmx3.getQnjmse());
             }
           
            if(myForm.getQylx()==null)
            {
            	myForm.setQylx("1");
            }
            if(myForm.getQylx1()==null)
            {
            	myForm.setQylx1("1");
            }
	     }
            catch(Exception ex)
            {    
    		 System.out.println(ex);
             throw ExceptionUtil.getBaseException(ex);
               
            }
            
            return mapping.findForward("Show");
	    
		
	}
	
	//�����پ�ҵ�����걨����
	public ActionForward doSave(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
	        throws BaseException {
		 // ���token
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        // ȡ��zjyjmsbForm
        ZjyjmsbForm zjyForm = (ZjyjmsbForm)form;
        UserData userData = getUserData(request);
        String jsjdm = userData.yhid;
        zjyForm.setJsjdm(jsjdm);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp sbrq = TinyTools.second2Day(now); // �걨����
        String nd = new SimpleDateFormat("yyyy").format(sbrq); //���
        String dqyf = new SimpleDateFormat("MM").format(sbrq);;//��ǰ�·�
        Map skssrqMap = Skssrq.zjyjmsbQuarter(new Date());
        int yuefen =Integer.parseInt(dqyf);
        int xsjd=0;//���ȳ�ʼ��
        int xsnd=0;
      
        switch(yuefen){
        case 1: xsjd=4;xsnd=Integer.parseInt(nd)-1; break;
        case 2: xsjd=4;xsnd=Integer.parseInt(nd)-1; break;
        case 3: xsjd=4;xsnd=Integer.parseInt(nd)-1; break;
        case 4: xsjd=1;xsnd=Integer.parseInt(nd);break;
        case 5: xsjd=1;xsnd=Integer.parseInt(nd);break;
        case 6: xsjd=4;xsnd=Integer.parseInt(nd)-1;break;
        case 7: xsjd=2;xsnd=Integer.parseInt(nd);break;
        case 8: xsjd=2;xsnd=Integer.parseInt(nd);break;
        case 9: xsjd=2;xsnd=Integer.parseInt(nd);break;
        case 10:xsjd=3;xsnd=Integer.parseInt(nd);break;
        case 11:xsjd=3;xsnd=Integer.parseInt(nd);break;
        case 12:xsjd=3;xsnd=Integer.parseInt(nd);break;
        }
        String jd =Integer.toString(xsjd);
        zjyForm.setJd(jd);
  
        
        try {
        SWDJJBSJ djInfo = FriendHelper.getSWDJJBSJ(request);
        //����پ�ҵ�����걨����
        Zjyjmsbz zjyjmsbz = new Zjyjmsbz();
        zjyjmsbz.setJsjdm(jsjdm);
        zjyjmsbz.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
        zjyjmsbz.setFsdm(CodeConstant.FSDM_WSSB);
        zjyjmsbz.setBblxdm(ZjyjmsbConstant.BBLXDM_JB);
        zjyjmsbz.setBbq(zjyForm.getJd());
        zjyjmsbz.setBbnd(Integer.toString(xsnd));
        zjyjmsbz.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        zjyjmsbz.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        zjyjmsbz.setSbrq(sbrq);
        zjyjmsbz.setJzbz(ZjyjmsbConstant.JZBS);
        zjyjmsbz.setLrr(jsjdm);
        zjyjmsbz.setLrrq(sbrq);
        zjyjmsbz.setCjr(jsjdm);
        zjyjmsbz.setCjrq(sbrq);
        zjyjmsbz.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
        zjyjmsbz.setNd(Integer.toString(xsnd));

           //����پ�ҵ�����걨��ϸ�б�
           List zjymxlist = new ArrayList();
           String qylxdm=zjyForm.getQylx();
           String qylxdm1=zjyForm.getQylx1();
           String qxdm =djInfo.getSwjgzzjgdm().substring(0, 2);
           int qylx=Integer.parseInt(qylxdm);
           int qylx1=Integer.parseInt(qylxdm1);
           Map createMap = new HashMap();
           createMap.put("JSJDM",jsjdm);
           createMap.put("ND",Integer.toString(xsnd));
           createMap.put("SBRQ",sbrq);
           createMap.put("QXDM",qxdm);
           createMap.put("ZJYFORM",zjyForm);

          
           //������ҵ���͹�����ϸ�б�
           switch(qylx){
           case 1: 
        	   
        	   if(!zjyForm.getJ2().equals("")||!zjyForm.getJ3().equals("")||!zjyForm.getJ4().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_YYS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   if(!zjyForm.getJ5().equals("")||!zjyForm.getJ6().equals("")||!zjyForm.getJ7().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   if(!zjyForm.getJ8().equals("")||!zjyForm.getJ9().equals("")||!zjyForm.getJ10().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB1));}
               break;
           case 2:
        	   if(!zjyForm.getJ5().equals("")||!zjyForm.getJ6().equals("")||!zjyForm.getJ7().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   if(!zjyForm.getJ8().equals("")||!zjyForm.getJ9().equals("")||!zjyForm.getJ10().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           case 3:
        	   if(!zjyForm.getJ14().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           case 4:
        	   if(!zjyForm.getJ14().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           case 5:
        	   if(!zjyForm.getJ14().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           }

          
        	 
           //������ҵ���͹�����ϸ�б�
           switch(qylx1){
           case 1: 
        	   if(!zjyForm.getJ22().equals("")||!zjyForm.getJ23().equals("")||!zjyForm.getJ24().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_YYS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ25().equals("")||!zjyForm.getJ26().equals("")||!zjyForm.getJ27().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ28().equals("")||!zjyForm.getJ29().equals("")||!zjyForm.getJ30().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ34().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB2));}
               break;
               
           case 2:
        	   if(!zjyForm.getJ34().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   break;
           
           case 3:
        	   if(!zjyForm.getJ22().equals("")||!zjyForm.getJ23().equals("")||!zjyForm.getJ24().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_YYS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ25().equals("")||!zjyForm.getJ26().equals("")||!zjyForm.getJ27().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ28().equals("")||!zjyForm.getJ29().equals("")||!zjyForm.getJ30().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ31().equals("")||!zjyForm.getJ32().equals("")||!zjyForm.getJ33().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_GRSDS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   break;
           }

        VOPackage vo = new VOPackage();
        Map dataMap = new HashMap();
        dataMap.put("JSJDM", jsjdm);
        dataMap.put("BBND",Integer.toString(xsnd));
        dataMap.put("BBQ",Integer.toString(xsjd));
        ZjyjmsbInfor zjyjmsbinfor =new ZjyjmsbInfor();
        zjyjmsbinfor.setZjyjmsbmxList(zjymxlist);
        zjyjmsbinfor.setZjyjmsbz(zjyjmsbz);
        //����ǰ̨���͹����������ж��Ƿ���Ҫ�����ϸ����
        if(!zjyForm.getQnjmse().equals("") || !zjyForm.getQnyysr().equals("")){
     	  Zjyjmsbmx3 zjyjmsbmx3=createMX3(createMap);
     	  zjyjmsbinfor.setZjyjmsbmx3(zjyjmsbmx3);
        }
        dataMap.put("zjyjmsbinfor",zjyjmsbinfor);
        vo.setAction(ActionConstant.INT_ACTION_SAVE);
        vo.setProcessor(ProcessorNames.ZJYJMSB_PROCESSOR);
        vo.setData(dataMap);
        vo.setUserData(userData);
        ShenbaoProxy.getInstance().process(vo);
      }
      
      catch(Exception ex){
    	  throw ExceptionUtil.getBaseException(ex);
      }
		 
    
		
        removeForm(mapping,request);
        request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "�����پ�ҵ����˰�걨��ɹ���");
        return mapping.findForward("Success");
		
		//return mapping.findForward("Save");
       
   
		}
	
	 //	ɾ���걨����
	public ActionForward doDelete(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	        throws BaseException {
        //�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		ZjyjmsbForm zjyForm = (ZjyjmsbForm)form;
		Map dataMap = new HashMap();
        dataMap.put("JSJDM", zjyForm.getJsjdm());
        dataMap.put("BBND",zjyForm.getNd());
        dataMap.put("BBQ",zjyForm.getJd());
        //      ��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(ActionConstant.INT_ACTION_DELETE);
        UserData userData = this.getUserData(request);
        vo.setUserData(userData);
        vo.setData(dataMap);

        //����ProxyҪ���õ�processor������
        vo.setProcessor(ProcessorNames.ZJYJMSB_PROCESSOR);
        try
        {
         ShenbaoProxy.getInstance().process(vo);
        }
        catch(Exception ex)
        {
        	 throw ExceptionUtil.getBaseException(ex);
        }
        removeForm(mapping,request);
        request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "ɾ���پ�ҵ����˰�걨���ϳɹ���");
        return mapping.findForward("Success");
	}
	
	public ActionForward doRtn(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
	        throws BaseException {
		
		
		
		return mapping.findForward("Rtn");
		
	}
	//�����پ�ҵ�����걨��ϸ��12,���ݲ���SZDM������J1��J2,J3��ȡ�ı��ֶ�
	public static Zjyjmsbmx12 createMX12(Map createMap,String szdm,String sbblxdm){
		Zjyjmsbmx12 zjyjmsbmx12 = new Zjyjmsbmx12();
		ZjyjmsbForm zjyForm =(ZjyjmsbForm)createMap.get("ZJYFORM");
		String jsjdm =(String)createMap.get("JSJDM");
		String nd =(String)createMap.get("ND");
		String qxdm =(String)createMap.get("QXDM");
		Timestamp sbrq =(Timestamp)createMap.get("SBRQ");
    	zjyjmsbmx12.setJsjdm(jsjdm);
    	zjyjmsbmx12.setBblxdm(ZjyjmsbConstant.BBLXDM_JB);
    	zjyjmsbmx12.setBbq(zjyForm.getJd());
    	zjyjmsbmx12.setBbnd(nd);
        zjyjmsbmx12.setSbblxdm(sbblxdm);
    	zjyjmsbmx12.setLrr(jsjdm);
    	zjyjmsbmx12.setLrrq(sbrq);
    	zjyjmsbmx12.setCjr(jsjdm);
    	zjyjmsbmx12.setCjrq(sbrq);
    	zjyjmsbmx12.setQxdm(qxdm);
    	zjyjmsbmx12.setNd(nd);
    	zjyjmsbmx12.setSzdm(szdm);
    	//���ݱ������ʹ����ж������ϸ��1��2
    	if(sbblxdm==ZjyjmsbConstant.ZJYJMSBB1){
    		zjyjmsbmx12.setXnxgsyrs(zjyForm.getJ1());
    		zjyjmsbmx12.setNsrlxdm(zjyForm.getQylx());
    	    zjyjmsbmx12.setNsrlxmc(ZjyjmsbConstant.getNsrlxmc(zjyForm.getQylx()));
    
    	//Ӫҵ˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_YYS))
		   {
            zjyjmsbmx12.setJ1(zjyForm.getJ2());
    		zjyjmsbmx12.setJ2(zjyForm.getJ3());
    		zjyjmsbmx12.setJ3(zjyForm.getJ4());
		   }
    	//����ά������˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_JSS))
		   {
    	
    		zjyjmsbmx12.setJ1(zjyForm.getJ5());
    		zjyjmsbmx12.setJ2(zjyForm.getJ6());
    		zjyjmsbmx12.setJ3(zjyForm.getJ7());
		   }
    	//�����Ѹ���
    	if(szdm.equals(ZjyjmsbConstant.SZDM_JYFFJ))
    	{
    		zjyjmsbmx12.setJ1(zjyForm.getJ8());
    		zjyjmsbmx12.setJ2(zjyForm.getJ9());
    		zjyjmsbmx12.setJ3(zjyForm.getJ10());
    	}	
    	//��ҵ����˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_QYSDS))
    	{
    		
    		zjyjmsbmx12.setJ3(zjyForm.getJ14());
    		
    
    	}
      }
    	else {
    		zjyjmsbmx12.setXnxgsyrs(zjyForm.getJ21());
    		zjyjmsbmx12.setNsrlxdm(zjyForm.getQylx1());
    	    zjyjmsbmx12.setNsrlxmc(ZjyjmsbConstant.getNsrlxmc1(zjyForm.getQylx1()));
       
        //Ӫҵ˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_YYS))
 		   {
        
        	zjyjmsbmx12.setJ1(zjyForm.getJ22());
    		zjyjmsbmx12.setJ2(zjyForm.getJ23());
    		zjyjmsbmx12.setJ3(zjyForm.getJ24());
 		   }
        //����ά������˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_JSS))
		   {
    		zjyjmsbmx12.setJ1(zjyForm.getJ25());
    		zjyjmsbmx12.setJ2(zjyForm.getJ26());
    		zjyjmsbmx12.setJ3(zjyForm.getJ27());
		   }
        //�����Ѹ���
    	if(szdm.equals(ZjyjmsbConstant.SZDM_JYFFJ))
    	{
    		zjyjmsbmx12.setJ1(zjyForm.getJ28());
    		zjyjmsbmx12.setJ2(zjyForm.getJ29());
    		zjyjmsbmx12.setJ3(zjyForm.getJ30());
    	}
        //��������˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_GRSDS))
    	{
    	
    		zjyjmsbmx12.setJ1(zjyForm.getJ31());
    		zjyjmsbmx12.setJ2(zjyForm.getJ32());
    		zjyjmsbmx12.setJ3(zjyForm.getJ33());
    	}
        //��ҵ����˰
    	if(szdm.equals(ZjyjmsbConstant.SZDM_QYSDS))
    	{
    		zjyjmsbmx12.setJ3(zjyForm.getJ34());
    		
    		
        }
    	}
    	return zjyjmsbmx12;
	}
    //�����پ�ҵ�����걨��ϸ��3
	public static Zjyjmsbmx3 createMX3(Map createMap){
		Zjyjmsbmx3 zjyjmsbmx3 = new Zjyjmsbmx3();
		ZjyjmsbForm zjyForm =(ZjyjmsbForm)createMap.get("ZJYFORM");
		String jsjdm =(String)createMap.get("JSJDM");
		String nd =(String)createMap.get("ND");
		String qxdm =(String)createMap.get("QXDM");
		Timestamp sbrq =(Timestamp)createMap.get("SBRQ");
		zjyjmsbmx3.setJsjdm(jsjdm);
	    zjyjmsbmx3.setBblxdm(ZjyjmsbConstant.BBLXDM_JB);//������������˴�ֻ�����
	    zjyjmsbmx3.setBbq(zjyForm.getJd());
	    zjyjmsbmx3.setBbnd(nd);
	    zjyjmsbmx3.setQnyysr(zjyForm.getQnyysr());
	    zjyjmsbmx3.setQnjmse(zjyForm.getQnjmse());
	    zjyjmsbmx3.setLrr(jsjdm);
	    zjyjmsbmx3.setLrrq(sbrq);
	    zjyjmsbmx3.setCjr(jsjdm);
	    zjyjmsbmx3.setCjrq(sbrq);
	    zjyjmsbmx3.setQxdm(qxdm);
	    zjyjmsbmx3.setNd(nd);
		return zjyjmsbmx3;
	}
   
   }
