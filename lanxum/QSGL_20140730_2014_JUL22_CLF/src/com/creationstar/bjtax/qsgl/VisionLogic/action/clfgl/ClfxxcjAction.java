package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfxxcjForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.bjtax.dj.model.dm.BZ;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;




/**
 * 
 * <p>
 * Title:��������Ϣ�ɼ�ACTION
 * </p>
 * <p>
 * Description: action
 * </p>
 * 
 * @author �Ʋ���
 * @version 1.1
 */
public class ClfxxcjAction extends BaseAction {
	
	public ActionForward doShow(ActionMapping mapping,
	        ActionForm form,
	        HttpServletRequest request,
	        HttpServletResponse response) {
	//����Token;
	saveToken(request);
	
    //��ÿͻ����ύ������
    ClfxxcjForm cf = (ClfxxcjForm)form;
    
    try {
    	cf.clear();
    	
		initCodeList(request, cf);
		
		cf.setXxly("01");
		cf.setCjfsdm("01");
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}
	
	request.setAttribute(mapping.getAttribute(), cf);
	

	//ת��showҳ�档
	return mapping.findForward("show");
	}

	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//��ֹ�ظ��ύ
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
        //����Token;
        saveToken(request);
        
        
        //��ÿͻ����ύ������
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        //����ҳ�涨��
        String goPage ="show";
        //������ֹ��ɼ�
        String xxly = cf.getXxly();
        if(xxly!= null && "02".equals(xxly)){
        	goPage ="ToSGCJ";
        	
        }
        
        
		System.out.println("------------------������ ��ǰ̨�ύ������� start ------------------");
		System.out.println("+++++++���ܶ�ά����Ϣ++++++++"+cf.getUNEpiccode());
		System.out.println("+++++++�汾��ʾ++++++++"+cf.getBbbs());
		System.out.println("+++++++��ͬ���++++++++"+cf.getHtbh());
		System.out.println("+++++++��ͬ����ǩԼ����++++++++"+cf.getHtwsqyrq());
		System.out.println("+++++++������������++++++++"+cf.getFwzlqx());
		System.out.println("+++++++���������ַ++++++++"+cf.getFwzldz());
		System.out.println("+++++++����Ȩ��ת������_����++++++++"+cf.getFwqszylx());
		System.out.println("+++++++����Ȩ��ת������_����++++++++"+cf.getFwqszylxmc());
		System.out.println("+++++++�Ƿ�Ϊ�״������ѹ�����_����++++++++"+cf.getSfwscsssggf());
		System.out.println("+++++++�Ƿ�Ϊ�״������ѹ�����_����++++++++"+cf.getSfwscsssggfmc());
		System.out.println("+++++++���ݲ�Ȩ֤��++++++++"+cf.getFwcqzh());
		System.out.println("+++++++��������Ȩ֤�����++++++++"+cf.getFwsyqztfrq());
		System.out.println("+++++++���ݽ������++++++++"+cf.getFwjzmj());
		System.out.println("+++++++�����ṹ����++++++++"+cf.getJzjgdm());
		System.out.println("+++++++�����ṹ����++++++++"+cf.getJzjgmc());
		System.out.println("+++++++�滮��;++++++++"+cf.getGhyt());
		System.out.println("+++++++��ͬ�ܼ�++++++++"+cf.getHtzj());
		System.out.println("+++++++���ִ���++++++++"+cf.getBzdm());
		System.out.println("+++++++��������++++++++"+cf.getBzmc());
		System.out.println("+++++++����++++++++"+cf.getHl());
		System.out.println("+++++++��ҽ��++++++++"+cf.getWbje());
		System.out.println("+++++++����¥��++++++++"+cf.getSzlc());
		System.out.println("+++++++���ز��н��������++++++++"+cf.getFdczjjgmc());
		
		//������Ϣ
		System.out.println("+++++++������Ϣ++++++++"+cf.getAll_sellerInfo());//���ƣ�������	
		//����Ϣ
		System.out.println("+++++++����Ϣ++++++++"+cf.getAll_buyerInfo());
		System.out.println("------------------������  end ------------------");
        
		//ִ�б������
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
        data.setZjMap(zjMap);
        
        //
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.INSERT);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        try {
        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfxxcjForm)dataRes.getFromData();
    		// ��ҳ�����������mapping
        	initCodeList(request, cf);
        	
        	
        	cf.setCjfsdm(xxly);
        	
        	
        	System.out.println("+++++++++++++++++++++++++++++�ɼ���ʽ#####"+xxly);
        	
    		request.setAttribute(mapping.getAttribute(), cf);
    		request.setAttribute(Constants.MESSAGE_KEY, "����ɹ���");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("error");
            
            try {
				initCodeList(request, cf);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            cf.setCjfsdm(xxly);
            request.setAttribute(mapping.getAttribute(), cf);
            //form��Ϣ����,ȥ�����������һ��֤������������Ϣ
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward(goPage);
        }
        
        
        
		System.out.println("------------------������ ������ɹ���̨��������� start ------------------");
		System.out.println("+++++++���ܶ�ά����Ϣ++++++++"+cf.getUNEpiccode());
		System.out.println("+++++++�汾��ʾ++++++++"+cf.getBbbs());
		System.out.println("+++++++��ͬ���++++++++"+cf.getHtbh());
		System.out.println("+++++++��ͬ����ǩԼ����++++++++"+cf.getHtwsqyrq());
		System.out.println("+++++++������������++++++++"+cf.getFwzlqx());
		System.out.println("+++++++���������ַ++++++++"+cf.getFwzldz());
		System.out.println("+++++++����Ȩ��ת������_����++++++++"+cf.getFwqszylx());
		System.out.println("+++++++����Ȩ��ת������_����++++++++"+cf.getFwqszylxmc());
		System.out.println("+++++++�Ƿ�Ϊ�״������ѹ�����_����++++++++"+cf.getSfwscsssggf());
		System.out.println("+++++++�Ƿ�Ϊ�״������ѹ�����_����++++++++"+cf.getSfwscsssggfmc());
		System.out.println("+++++++���ݲ�Ȩ֤��++++++++"+cf.getFwcqzh());
		System.out.println("+++++++��������Ȩ֤�����++++++++"+cf.getFwsyqztfrq());
		System.out.println("+++++++���ݽ������++++++++"+cf.getFwjzmj());
		System.out.println("+++++++�����ṹ����++++++++"+cf.getJzjgdm());
		System.out.println("+++++++�����ṹ����++++++++"+cf.getJzjgmc());
		System.out.println("+++++++�滮��;++++++++"+cf.getGhyt());
		System.out.println("+++++++��ͬ�ܼ�++++++++"+cf.getHtzj());
		System.out.println("+++++++���ִ���++++++++"+cf.getBzdm());
		System.out.println("+++++++��������++++++++"+cf.getBzmc());
		System.out.println("+++++++����++++++++"+cf.getHl());
		System.out.println("+++++++��ҽ��++++++++"+cf.getWbje());
		System.out.println("+++++++����¥��++++++++"+cf.getSzlc());
		System.out.println("+++++++���ز��н��������++++++++"+cf.getFdczjjgmc());
		
		//������Ϣ
		System.out.println("+++++++������Ϣ++++++++"+cf.getAll_sellerInfo());//���ƣ�������	
		//����Ϣ
		System.out.println("+++++++����Ϣ++++++++"+cf.getAll_buyerInfo());
		System.out.println("------------------������  end ------------------");
		// ת�򷵻غ��ҳ�档
		
		return mapping.findForward(goPage);
	}
	
	/**
	 * ���²ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//��ֹ�ظ��ύ
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
        //����Token;
        saveToken(request);
        
        
        //��ÿͻ����ύ������
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        
		//ִ�б������
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
        data.setZjMap(zjMap);
        
        //
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.UPDATE);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        try {
        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfxxcjForm)dataRes.getFromData();
    		// ��ҳ�����������mapping
        	initCodeList(request, cf);
        	cf.setCjfsdm("02");
    		request.setAttribute(mapping.getAttribute(), cf);
    		 request.setAttribute(Constants.MESSAGE_KEY, "�޸ĳɹ���");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("1");
            
            try {
				initCodeList(request, cf);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            cf.setCjfsdm("02");
            request.setAttribute(mapping.getAttribute(), cf);
            //form��Ϣ����,ȥ�����������һ��֤������������Ϣ
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward("ToSGCJ");
        }       
        
		
		return mapping.findForward("ToSGCJ");
	}
	
	
	/**
	 * ɾ���ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//��ֹ�ظ��ύ
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
        //����Token;
        saveToken(request);
        
        
        //��ÿͻ����ύ������
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        
		//ִ��ɾ������
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
        data.setZjMap(zjMap);
        
        //
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.DELETE);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        try {
        	QsglProxy.getInstance().process(vo);
    		// ��ҳ�����������mapping
        	cf.clear();
        	initCodeList(request, cf);
        	cf.setXxly("02");
        	cf.setCjfsdm("02");
    		request.setAttribute(mapping.getAttribute(), cf);
    		 request.setAttribute(Constants.MESSAGE_KEY, "ɾ���ɹ���");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("error");
            
            try {
				initCodeList(request, cf);
				cf.setXxly("02");
				cf.setCjfsdm("02");
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            
            request.setAttribute(mapping.getAttribute(), cf);
            //form��Ϣ����,ȥ�����������һ��֤������������Ϣ
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward("ToSGCJ");
        }       
        
		
		return mapping.findForward("ToSGCJ");		
	}
	
	
	
	
	/**
	 * ��ѯ�������ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

        //����Token;
        saveToken(request);
        
        //��ÿͻ����ύ������
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        
        //��ͬ���
        String queryHtbh = cf.getHtbh();
        
        System.out.println("++++++++++++"+cf.getOperationType());
        
        //
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        
        System.out.println("++++++++++++������λ����action++++++++++"+this.getUserData().getSsdwdm());
        
        vo.setData(data);
        try {
        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfxxcjForm)dataRes.getFromData();
        	
        	cf.setSaveIsSuccess("1");
    		// ��ҳ�����������mapping
        	initCodeList(request, cf);
        	cf.setOperationType("Query");
        	
        	//��Ϣ��Դ
        	if(cf.getXxly() == null || "".equals(cf.getXxly())){
        		
        		cf.setXxly("02");
        	}
        	cf.setCjfsdm("02");
        	
    		request.setAttribute(mapping.getAttribute(), cf);
    		 request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�ɹ���");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("error");
            cf.clear();
            cf.setHtbh(queryHtbh);
            cf.setXxly("02");
            cf.setCjfsdm("02");
            try {
				initCodeList(request, cf);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            
            request.setAttribute(mapping.getAttribute(), cf);
            //form��Ϣ����,ȥ�����������һ��֤������������Ϣ
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward("ToSGCJ");
        }
        
		
		
		
		return mapping.findForward("ToSGCJ");
	}
	
	
	/**
	 * 
	 * @methodName:doToPrint
	 * @function:
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-17 ����02:30:51
	 * @version 1.1
	 * 
	 *
	 */
	public ActionForward doToPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("toSwryQR");
	}
	
	//ת����˰������
	public ActionForward doToSellerQSZS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toSellerQSZS");
	}
	//ת��Ʊ����	
	public ActionForward doToFPDK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toFPDK");
	}
	//ת��˰�걨
	public ActionForward doToQSSB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toQSSB");
	}
	
	//ת�ֹ��ɼ�
	public ActionForward doToSGCJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//����Token;
		saveToken(request);
		
	    //��ÿͻ����ύ������
	    ClfxxcjForm cf = (ClfxxcjForm)form;
	    //����
	    cf.clear();
		try {
			 //��ʼ�����������
		    initCodeList(request, cf);
		    
		    cf.setXxly("02");
		    cf.setCjfsdm("02");
			
			request.setAttribute(mapping.getAttribute(), cf);
		} catch (Exception e) {
			request.setAttribute(Constants.MESSAGE_KEY, "ת�ֹ��ɼ�ʧ�ܣ�");
			e.printStackTrace();
		}
		return mapping.findForward("ToSGCJ");
	}


	
	/**
	 * Ϊ��������һ����ѡ��
	 * @param list  ���������
	 * @param classobj ÿ�����ݶ��������
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private List setBlankOption2CodeList(List list,Class classobj) throws InstantiationException, IllegalAccessException{
		List resList = new ArrayList();
		resList.add(classobj.newInstance());
		
		for(int index =0; index < list.size(); index ++){
			resList.add(list.get(index));
		}
		return resList;
	}
	
	
	/**
	 * ����AJAX����form��ֵ
	 * @methodName:doReset
	 * @function:
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-27 ����04:33:16
	 * @version 1.1
	 * 
	 *
	 */
	public ActionForward doReset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("+++++++++++++++++++������Ϣ��ʼ+++++++++++++++++++");
		
		ClfxxcjForm cf = (ClfxxcjForm)form;
			//����saveIsSuccess��ֵ
			String saveIsSuccess_new = (String)request.getParameter("saveIsSuccess");
			
			Enumeration em = request.getParameterNames();
			while(em.hasMoreElements()){
				System.out.println("p------------->"+em.nextElement());
			}
			
			System.out.println("���õ�ֵΪ��������"+saveIsSuccess_new);
			System.out.println("����ǰ****form��saveIsSuccess��������"+cf.getSaveIsSuccess());
			cf.setSaveIsSuccess(saveIsSuccess_new);
			System.out.println("���ú�****form��saveIsSuccess��������"+cf.getSaveIsSuccess());
			request.setAttribute(mapping.getAttribute(), cf);
			System.out.println("+++++++++++++++++++������Ϣ����+++++++++++++++++++");
		return null;
	}
	
	

	/**
	 * ���֤����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
		public ActionForward doGetZjlxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("+++++++++++++++++++��ȡ֤�����ƿ�ʼ+++++++++++++++++++");
		
		ClfxxcjForm cf = (ClfxxcjForm)form;
			//
			String dszjlxdm = (String)request.getParameter("DSzjlxdm");
			System.out.println("����������������������Ҫת����֤�����ʹ���Ϊ+++"+dszjlxdm);
			
			Map zjMap= ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
			
//			cf.setTmp_zllxmc(((Zjlx) zjMap.get(dszjlxdm)).getZjlxmc());
			System.out.println("����������������������Ҫת����֤����������Ϊ+++"+((Zjlx) zjMap.get(dszjlxdm)).getZjlxmc());
			
			//request.setAttribute(mapping.getAttribute(), cf);
			
			
			try {
				//���������ļ������ͺͱ��뷽ʽ  

				response.setContentLength(9);
				 response.setContentType("text/xml;charset=UTF-8");  

				 response.setHeader("Cache-Control", "no-cache");  
				 

				PrintWriter returnValue = response.getWriter();
				String output="";
				//output="<response>��ʢ��</response>";   
				output=((Zjlx) zjMap.get(dszjlxdm)).getZjlxmc();

				returnValue.print(output);   
				//response.setContentLength(output.getBytes().length);
				

				returnValue.close();   
				response.flushBuffer();
			} catch (Exception e) {
			}
			
			
			
			
			
			System.out.println("+++++++++++++++++++��ȡ֤�����ƽ���+++++++++++++++++++");
		return null;
	}
		
		
		/**
		 *  ��ʼ�����������
		 * @param request
		 * @param cf
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 */
		private void initCodeList(HttpServletRequest request, ClfxxcjForm cf)
				throws InstantiationException, IllegalAccessException {
			//��ʼ�����������
			//0.����޸�ɾ��Ȩ��
			getAuth(request, cf);
			//1.֤������
			getZjlxcode(request, cf);
			//2.��ʼ�����ִ������Ϣ			
			cf.setCodeList_bz(setBlankOption2CodeList(ActionUtil.getCodeTables(request.getSession().getServletContext(), Constants.BZ),BZ.class));
			//3.��������
			cf.setCodeList_fwxz(ActionUtil.getCodeTables(request.getSession().getServletContext(), Constants.FWXZ));
		}		
		
		/**
		 * ֤�����ʹ����
		 * @param request
		 * @param cf
		 * @return
		 */
		public List getZjlxcode(HttpServletRequest request, ClfxxcjForm cf) {
			List tempZjList = new ArrayList();
			HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
			
			//cf.setZjMap(zjMap);
			
			Iterator it = zjMap.keySet().iterator();  
			
			while(it.hasNext()){
				tempZjList.add(zjMap.get(it.next()));
			}
			
			cf.setZjList(tempZjList);
			return tempZjList;
		}
		
		/**
		 * ����޸ĺ�ɾ��Ȩ��
		 * @param request
		 * @param cf
		 */
		public void getAuth(HttpServletRequest request, ClfxxcjForm cf){
	        //��ÿͻ����ύ������
	        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
	        
	        //
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.LOADCODES);
	        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	        try {
	        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
	        	cf.setHasMAuthorise(dataRes.isHasMAuthorise());
	        	
	        	System.out.println("�Ƿ����޸�Ȩ��+++++++++"+dataRes.isHasMAuthorise());
	        } catch (Exception te) {
	            Debug.printException(te);
	        }			
		}
}
