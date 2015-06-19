package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Szqy;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwcqzbzzflx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qszyxsmx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfjyxxCXForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfjyxxCXBO;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
import com.ttsoft.framework.util.VOPackage;

/**
 * ������������Ϣ��ѯ
 * @author admin
 *
 */
public class ClfjyxxCXAction  extends QueryBaseAction {
	public ActionForward doShow(ActionMapping mapping,
	        ActionForm form,
	        HttpServletRequest request,
	        HttpServletResponse response) {
	//����Token;
	saveToken(request);
	
    //��ÿͻ����ύ������
	ClfjyxxCXForm cf = (ClfjyxxCXForm)form;

	cf.reset();
	this.removeForm(mapping, request);
	
	request.setAttribute(mapping.getAttribute(), cf);
	

	//ת��showҳ�档
	return mapping.findForward("show");
	}
	
	
	/**
	 * ������������Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doQuery(ActionMapping mapping,
	        ActionForm form,
	        HttpServletRequest request,
	        HttpServletResponse response) {
	//����Token;
	saveToken(request);
	
    //��ÿͻ����ύ������
	ClfjyxxCXForm cf = (ClfjyxxCXForm)form;
	ClfjyxxCXBO data = (ClfjyxxCXBO)cf.getData();
	System.out.println("��ѯ����--��ʼ����-->"+cf.getQuery_qsrq());
	System.out.println("��ѯ����--��ֹ����-->"+cf.getQuery_jzrq());
	System.out.println("��ѯ����--��ͬ���-->"+cf.getQuery_htbh());
	
    
    
    //��ȡ�����ServletContext�е�processor-map.properties������
    Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
    
    VOPackage vo = new VOPackage();
    vo.setAction(ActionType.QUERY);
    vo.setProcessor(prop.getProperty(new ClfjyxxCXBO().getClass().getName()));
    vo.setUserData(this.getUserData());
    vo.setData(data);
    try {
    	ArrayList resList = (ArrayList)QsglProxy.getInstance().process(vo);
    	cf.setResList(resList);
    	//cf = (ClfxxcjForm)dataRes.getFromData();
    	 // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
    	
        // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
        Debug.out("userdata.maxrowperpage: " + this.getUserData().myxszds);
        QueryCache cache = new QueryCache(cf.getResList(), this.getUserData().myxszds);
        cf.setQueryCache(cache);
        //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
        cf.setStatus("Result");
        
		// ��ҳ�����������mapping
		request.setAttribute(mapping.getAttribute(), cf);
		 request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�����ɹ���");
    } catch (Exception te) {
        Debug.printException(te);
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
        this.removeForm(mapping, request);
        request.setAttribute(mapping.getAttribute(), cf);
        return new ActionForward(mapping.getInput());
    }
    
    
    
    

	
	request.setAttribute(mapping.getAttribute(), cf);
	//ת��showҳ�档
	return mapping.findForward("show");
	}
	
	
	
	/**
	 * ����excel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doExport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		
	    //��ÿͻ����ύ������
		ClfjyxxCXForm cf = (ClfjyxxCXForm)form;
		ClfjyxxCXBO data = (ClfjyxxCXBO)cf.getData();
		System.out.println("��ѯ����--��ʼ����-->"+cf.getQuery_qsrq()+"��ѯ����--��ֹ����-->"+cf.getQuery_jzrq()+"��ѯ����--��ͬ���-->"+cf.getQuery_htbh());
		
		//System.out.println("==========="+(request.getSession(false).getServletContext().getAttribute(Constants.MODEL_NAME)));
		//System.out.println("==========="+(request.getSession(false).getServletContext().getAttribute(Constants.MODEL_NAME).getClass().getName()));
		
	    try {
	        //��ȡ�����ServletContext�е�processor-map.properties������
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.QUERY);
	        vo.setProcessor(prop.getProperty(new ClfjyxxCXBO().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	        
	    	
	    	ArrayList resList = (ArrayList)QsglProxy.getInstance().process(vo);
	    	cf.setResList(resList);
	    	
	    	if(resList.size() != 0){
	    		// �����ļ�����
	    		String fileName = "������������ϸ".concat(DateUtils.getDate()).concat(".xls");
	    		String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(fileName);
	    		response.resetBuffer();
	    		response.setHeader("Content-disposition", "attachment; filename=" + encodeFileName);
	    		response.setContentType("application/vnd.ms-excel");
	    		
	    		write2Model(response.getOutputStream(),resList,request);
	    		
	    	}
			// ��ҳ�����������mapping
			request.setAttribute(mapping.getAttribute(), cf);
			request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�����ɹ���");
	    	
		}catch (Exception e) {
            Debug.printException(e);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, e.getMessage());
            response.resetBuffer();
            return new ActionForward(mapping.getInput());
		}
		
		return null;
	}
	
	
	public  void write2Model(OutputStream outputStream,List resList,HttpServletRequest request){
		HSSFWorkbook wb = null;
		try {

			//new File("/").getAbsolutePath());
			//String path=this.getClass().getClassLoader().getResource("/").getPath();
			
		   // wb = new HSSFWorkbook(new FileInputStream("D:\\workspacesvn\\QSGL_SVN\\src\\com\\creationstar\\bjtax\\qsgl\\VisionLogic\\action\\clfgl\\clfjyxx_model.xls"));
			
			//System.out.println(path+"\\clfjyxx_model.xls");
			
			//wb = new HSSFWorkbook(new FileInputStream(path+"/clfjyxx_model.xls"));
			
			InputStream inputStream = ClfjyxxCXAction.class.getResourceAsStream("clfjyxx_model.xls");
			
		    ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
		    int count =0;
		    while ((count = inputStream.read(buffer,0,1024)) != -1){
		    	byteOS.write(buffer, 0, count);
		    	
		    }
		    byteOS.close();
		    byte[] allBytes = byteOS.toByteArray();

		// create workbook from array:
		InputStream byteIS = new ByteArrayInputStream(allBytes);
		//HSSFWorkbook wb = new HSSFWorkbook(byteIS);
		
			
			wb = new HSSFWorkbook(byteIS);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		HSSFCellStyle styleValue = wb.createCellStyle(); // ��ʽ����
		styleValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleValue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleValue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		
		
		HSSFSheet sheet = wb.getSheetAt(0);
		
		
		for(int index =0 ; index <resList.size(); index ++){
			ClfjyxxCXBO.resInfo  resInfo = (ClfjyxxCXBO.resInfo)resList.get(index);
			//��ô������ɼ���Ϣ
			ClfxxcjBo clfxxcjBo = resInfo.getClfxxcjBo();
			//��ô������˶���Ϣ
			ClfswjghdxxlrBo clfswjghdxxlrBo=resInfo.getClfswjghdxxlrBo();
			for(int indexInner =0; indexInner < resInfo.getClfjyxxList().size(); indexInner ++){
				ClfjyxxCXBO.clfjyxx  oneItem= (ClfjyxxCXBO.clfjyxx)resInfo.getClfjyxxList().get(indexInner);
				
				System.out.println("��������"+indexInner+"----htbh---"+clfxxcjBo.getHtbh());
				
				
				int rowNum = 2+index+indexInner;//�ӵڶ��п�ʼ
				int columNum =0;		
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getHtbh());//��ͬ���
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getAllSellerNames4jyxxcx());//��������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getAllBuyerNames4jyxxcx());//������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getFwzldz());//���������ַ
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getFwqszylxmc());//����Ȩ��ת������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getFwjzmj());//�������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getHtwsqyrq());//��������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getLrrq());//�������ڣ�¼�����ڣ�
				updateExcel(sheet,styleValue,rowNum,columNum++,clfxxcjBo.getHtzj());//��ͬ�ܼ�
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getMpmhdjg());//ÿƽ�׺˶�����
				updateExcel(sheet,styleValue,rowNum,columNum++,"1".equals(clfswjghdxxlrBo.getFwhdlxdm())?"��ס��":"ס��");//���ݺ˶�����
				updateExcel(sheet,styleValue,rowNum,columNum++,getFwszqymc(request,clfswjghdxxlrBo.getFwszqydm()));//������������
				updateExcel(sheet,styleValue,rowNum,columNum++,"1".equals(clfswjghdxxlrBo.getJtwyshyhbz())?"��":"��");//�Ƿ�Ϊ��ͥΨһ�����÷�				
				updateExcel(sheet,styleValue,rowNum,columNum++,getZfsysj(clfswjghdxxlrBo.getZfsjsjfl()));//ס��ʹ��ʱ��
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getYgffpje());//ԭ������Ʊ���
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getQdfcqsje());//ȡ�÷��ز�ʱ�����ɵ���˰���
				updateExcel(sheet,styleValue,rowNum,columNum++,getHfbzmc(clfswjghdxxlrBo.getHfbz()));//���ֱ�׼
				updateExcel(sheet,styleValue,rowNum,columNum++,getFwcqzbzzflxmc(request,clfswjghdxxlrBo.getFwcqzbzzflxdm()));//���ݲ�Ȩ֤�����ķ�������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getZfzxfy());//ס��װ�޷���
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getHlfy());//�������
				updateExcel(sheet,styleValue,rowNum,columNum++,clfswjghdxxlrBo.getAnjjse());//����Ӽ�����
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getAll_ynse_hj()?"":oneItem.getAll_ynse_hj().toString());//����˰���ܶ�
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getQs_jsje_hj()?"":oneItem.getQs_jsje_hj().toString());//��˰���ܶ��ʱΪ�գ�modify by tangchangfu 2014-06-16 ��ԭ���Ŀ�ֵ��ΪoneItem.getQs_jsje_hj()
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYys_JSJE_hj_02()?"":oneItem.getYys_JSJE_hj_02().toString());//Ӫҵ˰--��˰���
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYys_YNSE_hj_02()?"":oneItem.getYys_YNSE_hj_02().toString());//Ӫҵ˰--˰��
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYys_JMSE_hj_02()?"":oneItem.getYys_JMSE_hj_02().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYys_SJJE_hj_02()?"":oneItem.getYys_SJJE_hj_02().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYcjd_YNSE_hj_02_10_51_54()?"":oneItem.getYcjd_YNSE_hj_02_10_51_54().toString());//Ӫ�ǽ̵غϼ�
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYcjd_JMSE_hj_02_10_51_54()?"":oneItem.getYcjd_JMSE_hj_02_10_51_54().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYcjd_SJJE_hj_02_10_51_54()?"":oneItem.getYcjd_SJJE_hj_02_10_51_54().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getGrsds_JSJE_hj_05()?"":oneItem.getGrsds_JSJE_hj_05().toString());//��������˰--��˰���
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getGrsds_sl_05()?"":oneItem.getGrsds_sl_05().toString());//��������˰--˰��				
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getGrsds_YNSE_hj_05()?"":oneItem.getGrsds_YNSE_hj_05().toString());//��������˰--˰��
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getGrsds_JMSE_hj_05()?"":oneItem.getGrsds_JMSE_hj_05().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getGrsds_SJJE_hj_05()?"":oneItem.getGrsds_SJJE_hj_05().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYhs_YNSE_hj_16()?"":oneItem.getYhs_YNSE_hj_16().toString());//ӡ��˰--˰��
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYhs_JMSE_hj_16()?"":oneItem.getYhs_JMSE_hj_16().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getYhs_SJJE_hj_16()?"":oneItem.getYhs_SJJE_hj_16().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getTdzzs_JSJE_hj_08()?"":oneItem.getTdzzs_JSJE_hj_08().toString());//��������˰--��˰���
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getTdzzs_sl_08()?"":oneItem.getTdzzs_sl_08().toString());//��������˰--˰��				
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getTdzzs_YNSE_hj_08()?"":oneItem.getTdzzs_YNSE_hj_08().toString());//��������˰--˰��
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getTdzzs_JMSE_hj_08()?"":oneItem.getTdzzs_JMSE_hj_08().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getTdzzs_SJJE_hj_08()?"":oneItem.getTdzzs_SJJE_hj_08().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getQs_sl()?"":oneItem.getQs_sl().toString());//��˰--˰�ʣ���ʱΪ�գ�,modify by tangchangfu 2014-06-12��ԭ���Ŀ�ֵ��Ϊ  oneItem.getQs_sl()
				updateExcel(sheet,styleValue,rowNum,columNum++,null==oneItem.getQs_jsje_hj()?"":oneItem.getQs_jsje_hj().toString());//��˰--˰���ʱΪ�գ���modify by tangchangfu 2014-06-12 ��ԭ���Ŀ�ֵ��Ϊ oneItem.getQs_jsje_hj()
				
				
				updateExcel(sheet,styleValue,rowNum,columNum++,null==(oneItem.getAll_jsje_hj())?"":oneItem.getAll_jsje_hj().toString());
				//if(oneItem.getYhs_YNSE_hj_16() != null){
				//	updateExcel(sheet,styleValue,rowNum,columNum++,(oneItem.getAll_ynse_hj().subtract(oneItem.getYhs_YNSE_hj_16())));//�ϼ�(����ӡ��˰)
					
				//}else{
				//	if(oneItem.getQs_jsje_hj() != null){
				//		updateExcel(sheet,styleValue,rowNum,columNum++,(oneItem.getAll_ynse_hj().add(oneItem.getQs_jsje_hj())));//�ϼ�(��ӡ��˰)modify by tangchangfu  �ϼ�˰��������˰���  2014-06-12 ��ԭ����"oneItem.getAll_ynse_hj()"��Ϊ "oneItem.getAll_ynse_hj().add(oneItem.getQs_jsje_hj()))"
				//	}else{
						updateExcel(sheet,styleValue,rowNum,columNum++,null==(oneItem.getAll_ynse_hj())?"":oneItem.getAll_ynse_hj().toString());
						
				//	}
				updateExcel(sheet,styleValue,rowNum,columNum++,null==(oneItem.getAll_jmse_hj())?"":oneItem.getAll_jmse_hj().toString());
				updateExcel(sheet,styleValue,rowNum,columNum++,null==(oneItem.getAll_sjje_hj())?"":oneItem.getAll_sjje_hj().toString());
				//}
			}
		}
		try {
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}
	
	
	/**
	 * Excel2003(only) writer
	 * 
	 * FUNCTION:: Write the data into the HSSFCell,just update
	 * 
	 * 
	 * METHOD::Use  the column number and the row number to get a cell 
	 * 
	 * 
	 * NOTICE:: before write  data into the cell should judge the data type
	 * 
	 * 
	 * @param sheet2           Excel sheet
	 * @param rowNo       
	 * @param columnNo   
	 * @param data            
	 */
	public static void updateExcel(HSSFSheet sheet2,HSSFCellStyle styleValue, int rowNo, int columnNo,Object data){
	    HSSFRow row2 = sheet2.getRow(rowNo);
	    if(row2 == null)
	    {
	    	row2 = sheet2.createRow((short)rowNo);   
	    	HSSFCell cell = row2.getCell((short)columnNo);
	    	if(cell == null)
	    	{
	    		cell = row2.createCell((short)columnNo);
	    		setValuetoCell(cell,styleValue, data);
	    	}else{
	    		setValuetoCell(cell,styleValue, data);
	    	}
	    }else{
	    	HSSFCell cell = row2.getCell((short)columnNo);
	    	if(cell==null){
	    		cell = row2.createCell((short)columnNo);
	    		setValuetoCell(cell,styleValue, data);
	    	}else{
	    		setValuetoCell(cell,styleValue, data);
	    	}
	    }
}
	/**
	 *FUNCTION:: define  XSSFCell  type and fill the cell
	 *
	 * changed by TangchangFu 
	 * 
	 * @param cell
	 *            HSSFCell    
	 * @param valueobj
	 *            Object       
	 */
	public static void setValuetoCell(HSSFCell cell,HSSFCellStyle styleValue,Object valueobj) {	

		
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);// ���Ĵ���
		cell.setCellStyle(styleValue);
		
		if (valueobj == null) {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		} else if (valueobj instanceof String) {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			cell.setCellValue(valueobj.toString()+"");
		} else if (valueobj instanceof BigDecimal) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(((BigDecimal) valueobj).doubleValue());
		} else if (valueobj instanceof Timestamp) {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			String timestr = ((Timestamp) valueobj).toString();
		if (timestr.length() > 11
				&& timestr.substring(11).equals("00:00:00.0")) {
			timestr = timestr.substring(0, 11);//substring Timestamp  from 2010-04-26 00:00:00 to 2010-04-26
		}else{
			timestr = timestr.substring(0, 16);//substring Timestamp from 2010-08-04 09:38:47  to 2010-08-04 09:38
		}
		cell.setCellValue(timestr);
	} else if (valueobj instanceof Long) {
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(((Long) valueobj).longValue());
	} else if (valueobj instanceof Integer) {
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(((Integer) valueobj).intValue());
	} else if (valueobj instanceof java.sql.Date||valueobj.getClass().toString().equals("class java.util.Date")) {
		//cell.setCellType(XSSFCell.CELL_TYPE_BLANK);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
		 cell.setCellValue(sdf.format((java.util.Date)valueobj));	
	} else {
		cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		cell.setCellValue("unknown type");
			}
		}
	
	/**
	 * 
	 * @param request
	 * @param dm
	 * @return
	 * @author zhangj
	 */
	public String getFwcqzbzzflxmc(HttpServletRequest request,String dm){
		HttpSession session = request.getSession(false);
		ArrayList list=ActionUtil.getCodeTables(session.getServletContext(), Constants.FWCQZBZZFLX);
		if(dm==null || "".equals(dm)){
			return "";
		}else{
			for(int i=0;i<list.size();i++){
				Fwcqzbzzflx fwcqzbzzflx=(Fwcqzbzzflx)list.get(i);
				if(dm.equals(fwcqzbzzflx.getFwcqzbzzflxdm())){
					return fwcqzbzzflx.getFwcqzbzzflxmc();
				}
			}
		}
		return dm;
	}
	/**
	 * 
	 * @param request
	 * @param dm
	 * @return
	 * @author zhangj
	 */
	public String getFwszqymc(HttpServletRequest request,String dm){
		HttpSession session = request.getSession(false);
		ArrayList list=ActionUtil.getCodeTables(session.getServletContext(), Constants.FWSZQY);
		if(dm==null || "".equals(dm)){
			return "";
		}else{
			for(int i=0;i<list.size();i++){
				Szqy fwcqzbzzflx=(Szqy)list.get(i);
				if(dm.equals(fwcqzbzzflx.getFwszqydm())){
					return fwcqzbzzflx.getFwszqymc();
				}
			}
		}
		return dm;
	}
	/**
	 * 
	 * @param dm
	 * @return
	 * @author zhangj
	 */
	public String getZfsysj(String dm){
		String mc="";
		if(dm==null || "".equals(dm)){
			return "";
		}else{
			if("0".equals(dm)) mc="5�꣨��������";
			if("1".equals(dm)) mc="5������3������";
			if("2".equals(dm)) mc="3�꣨����������";

			if("".equals(mc))		   return dm;

			return mc;
		}
	}
	/**
	 * 
	 * @param dm
	 * @return
	 * @author zhangj
	 */
	public String getHfbzmc(String dm){
		String mc="";
		if(dm==null || "".equals(dm)){
			return "";
		}else{
			if("0".equals(dm)) mc="��ͨס��";
			if("1".equals(dm)) mc="����ͨס��";
			if("2".equals(dm)) mc="��ס��";
			if("3".equals(dm)) mc="�����޳�����";
			if("4".equals(dm)) mc="���˼̳�";
			if("".equals(mc))		   return dm;

			return mc;
		}
	}
	
}
