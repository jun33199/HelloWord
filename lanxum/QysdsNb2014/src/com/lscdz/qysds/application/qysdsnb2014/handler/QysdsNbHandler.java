package com.lscdz.qysds.application.qysdsnb2014.handler;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.Tools;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.main.access.JmsbaMainAccessExt;
import com.lscdz.qysds.application.nsrjbxx2014.extDao.NsrjbxxExtDao;
import com.lscdz.qysds.application.qysdsnb2014.QysdsNb2014Contant;
import com.lscdz.qysds.application.qysdsnb2014.access.QysdsNbAccessExt;
import com.lscdz.qysds.application.qysdsnb2014.helper.DownloadDataHelper;
import com.lscdz.qysds.application.qysdsnb2014.helper.JmsbaHelper;
import com.lscdz.qysds.application.qysdsnb2014.helper.QysdsNsrjbxxHelper;
import com.lscdz.qysds.application.qysdsnb2014.helper.UpdateFormulaHelper;
import com.lscdz.qysds.application.qysdsnb2014.helper.UploadQysdsDataHelper;
import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsJmsbajl;
import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsZgswjg;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.DownloadQysdsDataReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.NsrjbxxVo2014;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.UpdateFormulaReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.UploadQysdsDataReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.ZygdDwtzReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbInitVo;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbResponse;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbSqlx;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNsrJbxxVo;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNsrjbxxVo2014;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsWsInitVo;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.ZygdDwtzRes;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq;
import com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_gxjslymx;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.qysds.common.util.SbzlAccess;
import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;
import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;
import com.lscdz.util.ActionHandler;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;
import com.lscdz.util.SimplePageUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class QysdsNbHandler extends ActionHandler{
	MyLogger myLogger =new MyLogger(QysdsNbHandler.class);
	//当前登录用户信息
	private Yh userData=null;
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		
		
		try {
			userData=this.getUserData(msg);
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.rtnBizCode=RtnCodeMessage.Error_1010.Code;
			this.rtnBizMessage=RtnCodeMessage.Error_1010.Message;
			return new StringBuffer(this.ConvertVoToXml(null));
		}
		//初始化
		if(msg.getAction().equalsIgnoreCase("doInit")){
			return doInit(msg);
		}
		//获取纳税人基本信息
		if(msg.getAction().equalsIgnoreCase("doQueryNsrjbxx")){
			return doQueryNsrjbxx(msg);
		}
		//更新审核公式
		if(msg.getAction().equalsIgnoreCase("doUpdateFormula")){
			return doUpdateFormula(msg);
		}
		//上传纳税人企业所得税年报数据
		if(msg.getAction().equalsIgnoreCase("doUpload")){
			return doUpload(msg);
		}
		//下载纳税人企业所得税年报数据
		if(msg.getAction().equalsIgnoreCase("doDownload")){
			return doDownload(msg);
		}
		//查询主要股东，对外投资主要信息
		if(msg.getAction().equalsIgnoreCase("queryZygdDwtz")){
			return doQueryZygdDwtz(msg);
		}
		//加载网上的国家地区代码表
		if(msg.getAction().equalsIgnoreCase("initWsGjdq")){
			return doInitWsGjdq(msg);
		}
		throw new FrameException("暂不支持的Action方法" + msg.getAction());
	}
	/**
	 * 初始化网上国家地区代码表
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	private StringBuffer doInitWsGjdq(ClientMessage msg) throws FrameException {
		StringBuffer  sb=new StringBuffer();
		QysdsWsInitVo vo=new QysdsWsInitVo();
		//国籍地区
		List<gy_dm_gjdq> gjdmdzList=CodeTableManager.getCodeTableList(CodeTableKey.GY_DM_GJDQ);
		vo.setGjdmdzList(gjdmdzList);
		//高新技术
		List<sb_dm_qysds_gxjslymx>  gxjslymxList=CodeTableManager.getCodeTableList(CodeTableKey.SB_DM_QYSDS_GXJSLYMX);
		vo.setGxjslymxList(gxjslymxList);
		sb.append(this.ConvertVoToXml(vo));
		return sb;
	}
	/**
	 * 查询对外投资和主要股东的信息
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doQueryZygdDwtz(ClientMessage msg) throws FrameException {
		StringBuffer  sb=new StringBuffer();
		Connection conn = null;
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			sb.append(ConvertVoToXml(null));
			return sb;
		}
		ZygdDwtzRes ResVo=new ZygdDwtzRes();
		try{
			conn=ResourceManager.getConnection();
			//请求参数
			ZygdDwtzReq vo=new ZygdDwtzReq();
			this.ConvertXmlToVo(msg, vo);
			String jsjdm=vo.getJsjdm();
			String nd=vo.getNd();
			//备案事项
			JmsbaHelper jmsbaUtil=new JmsbaHelper();
			List<QysdsJmsbajl> jmsbajlList=jmsbaUtil.basxQuery(jsjdm, nd);
			ResVo.setJmsbajlList(jmsbajlList);
			//2014版企业所得税纳税人基本信息
			String sqlWhere=" where jsjdm='"+jsjdm+"' and nd='"+nd+"' and version='"+QysdsNbConstant.REPORT_VERSION_2014+"'";
			List<sb_jl_qysds_nsrjbxxb_2014> jbxxList2014=new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();
			jbxxList2014=NsrjbxxExtDao.readNsrjbxxRecords(conn, sqlWhere);
			//企业主要股东id
			String qyzygdid="";
			//对外投资企业id
			String dwtzqyid="";
			if(jbxxList2014!=null&&jbxxList2014.size()>0){
				qyzygdid=jbxxList2014.get(0).getQyzygdid();
				dwtzqyid=jbxxList2014.get(0).getDwtzqyid();
			}
			//主要股东的查询条件
			if(qyzygdid!=null&&qyzygdid.trim().length()>0){
				String	zygdWhere=" where qyzygdid='"+qyzygdid+"' order by tzbl desc";
				List<sb_jl_qysds_qyzygd> qyzygdList=NsrjbxxExtDao.readZygdRecords(conn, zygdWhere);
				ResVo.setQyzygdList(qyzygdList);
			}
			//对外投资企业查询条件
			if(dwtzqyid!=null&&dwtzqyid.trim().length()>0){
				String dwtzWhere="where dwtzqyid ='"+dwtzqyid+"' order by tzbl desc";
				List<sb_jl_qysds_dwtz> dwtzList=NsrjbxxExtDao.readDwtzRecords(conn, dwtzWhere);
				ResVo.setDwtzList(dwtzList);
			}
			//转换成xml
			sb.append(ConvertVoToXml(ResVo));
		}catch (FrameException ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//myLogger.log("获取纳税人基本信息出错："+ex.getMessage());
			ex.printStackTrace();
			this.setRtnBizMessage(ex.getMessage());
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			sb.append(ConvertVoToXml(null));
			return sb;	
		}finally{
			Tools.closeConnection(conn);
		}
		return sb;
	}
	/**
	 * 初始化信息
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doInit(ClientMessage msg) throws FrameException {
		StringBuffer  sb=new StringBuffer();
		QysdsNbInitVo initVo=new QysdsNbInitVo ();
		//企业所得税主管税务
		List<QysdsZgswjg> qysdsZgswjg=QysdsNbAccessExt.getZgswjgList(userData);
		initVo.setQysdsZgswjg(qysdsZgswjg);
		//年报申请类型
		List<QysdsNbSqlx> qysdsnbSqlxList = new ArrayList<QysdsNbSqlx>();
		QysdsNbSqlx wsVo=new QysdsNbSqlx();
		wsVo.setQysdsSqlxDm(QysdsNb2014Contant.QYSDS_NB_SQLX_WS_CODE);
		wsVo.setQysdsSqlxMc(QysdsNb2014Contant.QYSDS_NB_SQLX_WS_NAME);
		qysdsnbSqlxList.add(wsVo);
		QysdsNbSqlx smVo=new QysdsNbSqlx();
		smVo.setQysdsSqlxDm(QysdsNb2014Contant.QYSDS_NB_SQLX_SM_CODE);
		smVo.setQysdsSqlxMc(QysdsNb2014Contant.QYSDS_NB_SQLX_SM_NAME);
		qysdsnbSqlxList.add(smVo);
		initVo.setQysdsnbSqlxList(qysdsnbSqlxList);
		//国籍地区
		List<gy_dm_gjdq> gjdmdzList=CodeTableManager.getCodeTableList(CodeTableKey.GY_DM_GJDQ);
		initVo.setGjdmdzList(gjdmdzList);
		//高新技术明细
		List<sb_dm_qysds_gxjslymx>  gxjslymxList=CodeTableManager.getCodeTableList(CodeTableKey.SB_DM_QYSDS_GXJSLYMX);
		initVo.setGxjslymxList(gxjslymxList);
		sb.append(this.ConvertVoToXml(initVo));
		return sb;
	}

	/**
	 * 获取纳税人基本信息
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doQueryNsrjbxx(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Connection conn = null;
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		QysdsNsrJbxxVo nsrjbxxVo = new QysdsNsrJbxxVo();
		try {
			conn=ResourceManager.getConnection();
			//请求参数
			NsrjbxxVo2014 vo=new NsrjbxxVo2014();
			this.ConvertXmlToVo(msg, vo);
			//获取是否关联申报的标志
			String sfglsbFlag=SbzlAccess.getProperty(QysdsNbConstant.QYSDS_GLSB_FLAG);
			nsrjbxxVo.setSfglsbFlag(sfglsbFlag);
			List<QysdsNsrjbxxVo2014> jbxxList2014=new ArrayList<QysdsNsrjbxxVo2014>();
			if(QysdsNb2014Contant.QYSDS_NB_SQLX_SM_CODE.equals(vo.getSmwsbz())){
				StringBuffer sqlWhere=QysdsNsrjbxxHelper.doQuery(vo,userData);
				int countPage = JmsbaMainAccessExt.getCountPage(SimplePageUtil.CountPageSql(sqlWhere.toString()));
				jbxxList2014=QysdsNbAccessExt.readNsrjbxxRecords(conn, SimplePageUtil.SimplePageSql(
												sqlWhere.toString(),vo.getPageSize(), vo.getCurPage()));
				QysdsNsrjbxxVo2014 nsrjbxxVo2014=new QysdsNsrjbxxVo2014();
					
				QysdsHelperUtil.getNsrjbxxVo2014(jbxxList2014, nsrjbxxVo2014, nsrjbxxVo);
				//返回分页信息
				int totalPage = (countPage + vo.getPageSize() - 1)/ vo.getPageSize();
				nsrjbxxVo.setTotalPage(totalPage);// 总页数
				nsrjbxxVo.setTotalCount(countPage);// 总记录数
				// 把前台传过来的当前页返回，上一页，下一页都是前台进行加1和减1操作
				nsrjbxxVo.setCurPage(vo.getCurPage());// 当前页数
				// 把前台传过来的每页大小传给前台
				nsrjbxxVo.setPageSize(vo.getPageSize());// 页面大小

			
		   }else if(QysdsNb2014Contant.QYSDS_NB_SQLX_WS_CODE.equals(vo.getSmwsbz())){
			   String nd=QysdsHelperUtil.getYear();//获得税款年度
			  /* JmsbaHelper jmsbaUtil=new JmsbaHelper();
			   List<QysdsJmsbajl> jmsbajlList=jmsbaUtil.basxQuery(vo.getJsjdm(), nd);
			   nsrjbxxVo.setJmsbajlList(jmsbajlList);*/
			   //网上查询条件
			   String wsWhere=" where jsjdm='"+vo.getJsjdm()+"' and nd='"+nd+"' and version='"+QysdsNbConstant.REPORT_VERSION_2014+"'";
			   jbxxList2014=QysdsNbAccessExt.readRecords(wsWhere,conn);
			   QysdsNsrjbxxVo2014 nsrjbxxVo2014=new QysdsNsrjbxxVo2014();
			   QysdsHelperUtil.getNsrjbxxVo2014(jbxxList2014, nsrjbxxVo2014, nsrjbxxVo);
		   }
		}catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1001.Message);
			throw new FrameException(ex.getMessage());
		}finally{
			Tools.closeConnection(conn);
		}
		returnBuff.append(this.ConvertVoToXml(nsrjbxxVo));
		return returnBuff;
	}

	/**
	 * 更新审核公式
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doUpdateFormula(ClientMessage msg) throws FrameException {
		StringBuffer strBuf=new StringBuffer();
		UpdateFormulaReq request=new UpdateFormulaReq();
		QysdsNbResponse qysdsResponse = new QysdsNbResponse();
		try {
			
			this.ConvertXmlToVo(msg, request);
			qysdsResponse.setJsjdm(request.getJsjdm());	
			qysdsResponse=UpdateFormulaHelper.updateFormula(request, qysdsResponse);
			strBuf.append(this.ConvertVoToXml(qysdsResponse));
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return strBuf;
	}
	
	/**
	 * 上传纳税人企业所得税年报数据
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doUpload(ClientMessage msg) throws FrameException {
		StringBuffer strBuf=new StringBuffer();
		UploadQysdsDataReq request=new UploadQysdsDataReq();
		QysdsNbResponse qysdsResponse = new QysdsNbResponse();
		try {
			this.ConvertXmlToVo(msg, request);
			qysdsResponse.setJsjdm(request.getJsjdm());	
			qysdsResponse=UploadQysdsDataHelper.upload(request,userData);
			strBuf.append(this.ConvertVoToXml(qysdsResponse));
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return strBuf;
	}
	
	/**
	 * 下载纳税人企业所得税年报数据
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doDownload(ClientMessage msg) throws FrameException {
		StringBuffer strBuf=new StringBuffer();
		DownloadQysdsDataReq request=new DownloadQysdsDataReq();
		QysdsNbResponse qysdsResponse = new QysdsNbResponse();
		try {
			this.ConvertXmlToVo(msg, request);
			//如果为内网需要校验权限，检查纳税人是否是当前用户的管户
			if(ResourceManager.getTokenByName("DEPLOY_ENVIRONMENT").equals("INNER")){
				
			}
			qysdsResponse.setJsjdm(request.getJsjdm());	
			qysdsResponse=DownloadDataHelper.download(request);
			strBuf.append(this.ConvertVoToXml(qysdsResponse));
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return strBuf;
	}
}
