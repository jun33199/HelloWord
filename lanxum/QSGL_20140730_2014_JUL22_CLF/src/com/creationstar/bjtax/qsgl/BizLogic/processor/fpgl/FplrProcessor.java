package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FplrBO;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票管理模块的发票号段录入Processor类</p>
 * @author tutu
 * @version 1.1
 */
public class FplrProcessor implements Processor {
	
	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FplrProcessor.process(vo)");

       if (vo == null) {
           throw new NullPointerException();
       }
       switch (vo.getAction()) 
       {
       		case ActionType.INSERT:
       			result = doSave(vo);
       		break;
       		
       		default:
       		throw new ApplicationException(
                   "ActionType有错误，processor中找不到相应的方法.");
       }

       return result;
   }


/**
    * 插入操作表，库存表
    * VOPackage中的Data属性，从前台传过来的是FplrBO的数据对象
    * @param vo 数据集对象（包括Qswszz值对象和UserData对象）
    * @return 当前页面对应的Form对象
    * @throws BaseException
    */
    private Object doSave(VOPackage vo) throws BaseException 
   {
       FplrBO bo = (FplrBO) vo.getData();
       
       // 库存结存list
       ArrayList KcList = new ArrayList();
       
       Connection conn = null;
       try {
    	   conn = QSDBUtil.getConnection();
    	   
    	   //得到当前时间
           Timestamp now = CommonUtil.getDBtime(conn);
           
           String fpkfdm = bo.getFpkfdm();
           String yhr = bo.getYhid()+bo.getYhmc();
           String swjgzzjgdm = bo.getSwjgzzjgdm();
           ArrayList fplrList = bo.getFplrList();
           
           //inerst fpkc
           //构造Fpkc需要插入的发票号码List
           Timestamp newTime = null;
           Fpkc fpkcNew = null;
           Fpkc fpkcOld = null;
           
           for(int i = 0; i < fplrList.size(); i++)
  			{
        	   fpkcNew = new Fpkc();
        	   Fpkc fpkcNewItem = (Fpkc)fplrList.get(i);
        	   
        	   fpkcNew.setFpkfdm(fpkfdm);
        	   fpkcNew.setFpzldm(fpkcNewItem.getFpzldm());
        	   fpkcNew.setJcsj(now);
        	   fpkcNew.setQshm(fpkcNewItem.getQshm());
        	   fpkcNew.setJzhm(fpkcNewItem.getJzhm());
        	   fpkcNew.setSl(fpkcNewItem.getSl());
        	   fpkcNew.setSwjgzzjgdm(swjgzzjgdm);
        	   fpkcNew.setCjr(yhr);
        	   fpkcNew.setCjrq(now);
        	   fpkcNew.setLrr(yhr);
        	   fpkcNew.setLrrq(now);
        	   fpkcNew.setBz("");
        	   fpkcNew.setRkbs(Constants.FP_RKBZ_RK);
        	   
        	   KcList.add(fpkcNew);
  			}
           
           //发票种类代码去重
           ArrayList fpzldmList = new ArrayList();
           
           for(int i = 0; i < KcList.size(); i++)
           {
        	   Fpkc fpzlfpkc = new Fpkc();
        	   
        	   Fpkc fpzlItem = (Fpkc)fplrList.get(i);
        	   
        	   fpzlfpkc.setFpzldm(fpzlItem.getFpzldm());
        	   
        	   //如果发票种类代码不存在fpzldmList中，则加入，否则略过
        	   if(!fpzldmList.contains(fpzlfpkc.getFpzldm()))
        	   {
        		   fpzldmList.add(fpzlfpkc.getFpzldm());
        	   }
           }
           
           //获得库存已经存在的库存数据，规则为1.根据库存最大时间，获取相应的已有库存数据，并且对获得的库存数据做更新，更新库存时间为现在新插入库存时间 now       
           //循环获取最新填用的发票代码的库房最大结存时间，并且通过最大时间找到相应的记录，更其改填用时间为当前时间，且重新插入到库存表
           for(int i = 0; i < fpzldmList.size(); i++)
           {
        	   String queryFpzldm = (String)fpzldmList.get(i);
        	   
               //得到最新的结存时间
               try 
               {	
            	   //拼接SQL
            	   String conditions = " where fpkfdm = '" + fpkfdm+ "'  and fpzldm = '" + queryFpzldm + "' ";
            	   newTime = DAOFactory.getInstance().getFpkcDAO().queryMaxTime(conn, conditions);
            	   
            	   Debug.out("发票号段录入：查询最大结存时间成功...."+newTime);
            	} 
               catch (Exception ex) 
               {
            		 Debug.out(ex);
            		 throw new ApplicationException("查询最大结存时间出错！");
            	}
            	
            	// 根据发票库房代码fpkfdm、发票种类代码fpzldm、最大结存时间newTimeStr 获取最新时间对应的库存数据
            	if(newTime != null)
            	{
            		String newTimeStr = newTime.toString();
            		
            		try 
            		{
            			String conditions = "where fpkfdm = '" + fpkfdm + "'  and fpzldm = '" + queryFpzldm + "' and jcsj = to_date('"+newTimeStr.substring(0,19)+"','yyyy-mm-dd hh24:mi:ss') and sl <>'"+Constants.FP_SL_ZERO+"'  ";
            			ArrayList oldKcList = DAOFactory.getInstance().getFpkcDAO().queryMaxList(conn, conditions);
            			
            			for (int j = 0; j < oldKcList.size(); j++)
            			{
            				fpkcOld = new Fpkc();
            				Fpkc fpkcOldItem = (Fpkc) oldKcList.get(j);
            				
            				fpkcOld.setFpkfdm(fpkcOldItem.getFpkfdm());
            				fpkcOld.setFpzldm(fpkcOldItem.getFpzldm());
            				fpkcOld.setJcsj(now);
            				fpkcOld.setQshm(fpkcOldItem.getQshm());
            				fpkcOld.setJzhm(fpkcOldItem.getJzhm());
            				fpkcOld.setSl(fpkcOldItem.getSl());
            				fpkcOld.setSwjgzzjgdm(fpkcOldItem.getSwjgzzjgdm());
            				fpkcOld.setCjr(fpkcOldItem.getCjr());
            				fpkcOld.setCjrq(fpkcOldItem.getCjrq());
            				fpkcOld.setLrr(yhr);
            				fpkcOld.setLrrq(newTime);
            				fpkcOld.setBz(fpkcOldItem.getBz());
            				fpkcOld.setRkbs(fpkcOldItem.getRkbs());
            				
            				KcList.add(fpkcOld);
            			}
            				Debug.out("发票号段录入：查询最大结存记录结束....");
            		} 
            		catch (Exception ex) 
            		{
            			Debug.out(ex);
            			throw new ApplicationException("查询最大结存记录出错！");
            		}
            	}
           }
           
           //通过插入库存表List 与现有库存中List 的集合 将数据写入到库存表。
           for(int index =0; index < KcList.size() ; index ++)
           {
        	   Fpkc fpkc = new Fpkc();
        	   Fpkc fpkcItem = (Fpkc) KcList.get(index);
        	   
        	   fpkc.setFpkfdm(fpkcItem.getFpkfdm());
        	   fpkc.setFpzldm(fpkcItem.getFpzldm());
        	   fpkc.setJcsj(fpkcItem.getJcsj());
        	   fpkc.setQshm(fpkcItem.getQshm());
        	   fpkc.setJzhm(fpkcItem.getJzhm());
        	   fpkc.setSl(fpkcItem.getSl());
        	   fpkc.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
        	   fpkc.setCjr(fpkcItem.getCjr());
        	   fpkc.setCjrq(fpkcItem.getCjrq());
        	   fpkc.setLrr(fpkcItem.getLrr());
        	   fpkc.setLrrq(fpkcItem.getLrrq());
        	   fpkc.setBz(fpkcItem.getBz());
        	   fpkc.setRkbs(fpkcItem.getRkbs());
        	   
        	   try 
        	   {
        		   DAOFactory.getInstance().getFpkcDAO().insert(fpkc, conn);
        		   Debug.out("发票号段录入：已经将fpkc的数据插入到数据库中....");
        	   } 
        	   catch (Exception ex) 
        	   {
        		   Debug.out(ex);
        		   throw new ApplicationException("插入发票库存表出错！");
        	   }
			}
        	
       } catch (Exception ex) {
           // 处理失败信息:控制台 ＋ 日志
           Debug.printException(ex);
           ErrorLog.makeLog(vo.getUserData(), "契税发票管理－FplrProcessor，操作出错",new StackMsg2StringUtil(ex,
                   Constants.STACK_MSG_CAP).getStackMsg(), "失败");
           throw ExceptionUtil.getBaseException(ex);
       } finally {
           QSDBUtil.freeConnection(conn);
       }
       return bo;
   }
   
}
