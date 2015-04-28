package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 *
 * <p>Title: 给退税提供的接口</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class InterFaceProcessor extends CommonProcessor {

    public static List getWszDataQS(String wspzh, String ndzb) throws
            BaseException {
        if (wspzh == null || wspzh.equals("")) {
            throw ExceptionUtil.getBaseException(
                    new ApplicationException("完税证号不能为空！"));
        }

        if (ndzb == null || ndzb.equals("")) {
            throw ExceptionUtil.getBaseException(
                    new ApplicationException("年度字别不能为空！"));
        }

        int step = 0;
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            Qswszz qswszz = new Qswszz();
            qswszz.setWszh(wspzh);
            qswszz.setNdzb(ndzb);
            qswszz.setPzzldm(Constants.WSZ_PZZLDM);

            step = 1;
            Sbjkzb sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().
                            query(qswszz, conn);

            //如果该完税证未汇总生成缴款书或者缴款书未入库，均不能退税，返回一个长度为0的list
            if (sbjkzb.getJkpzh() == null || sbjkzb.getJkpzh().equals("") ||
                sbjkzb.getZwbs().charAt(1) != '1') {
                return new ArrayList(0);
            }

            //如果该完税证已汇总并且已入库，则返回完税证的信息
            step = 2;
            ArrayList wszList = new ArrayList();
            StringBuffer condition = new StringBuffer(" AND a.wszh = '");
            condition.append(wspzh).append("' AND a.ndzb = '").append(ndzb)
                    .append("' AND a.pzzldm in ('")
                    .append(Constants.WSZ_PZZLDM)
                    .append("', '")
                    .append(Constants.WSZ_PZZLDM_OLD)
                    .append("') ");

            wszList = DAOFactory.getInstance().getQswszzDAO().queryAll(
                    condition.toString(), conn);

            return wszList;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (step == 1) {
                throw ExceptionUtil.getBaseException(
                        new ApplicationException("查询完税证是否已汇总时出错！"));
            } else if (step == 2) {
                throw ExceptionUtil.getBaseException(
                        new ApplicationException("得到完税证数据出错！"));
            } else {
                //step == 0, 得到数据库连接发生错误
                throw ExceptionUtil.getBaseException(
                        new ApplicationException(ex.getMessage()));
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }


    /**
     * 提供给票证的接口，用于反填完税证主表的结报单号
     * @param ndzb_wszh ArrayList
     * @param jbdh String
     * @param ud UserData
     * @return boolean
     * @throws BaseException
     */
    public static boolean Jiebao(ArrayList ndzb_wszh, String jbdh, UserData ud) throws
            BaseException {
        //返回的是否操作成功的标识
        boolean flag = false;

        if (ndzb_wszh.isEmpty()) {
            throw ExceptionUtil.getBaseException(new ApplicationException(
                    "完税证号和年度字别不能为空！！！"));
        }

        if (jbdh == null || jbdh.equals("")) {
            throw ExceptionUtil.getBaseException(new ApplicationException(
                    "结报单号不能为空！！！"));
        }
        if (ud == null) {
            throw ExceptionUtil.getBaseException(new ApplicationException(
                    "UserData不能为空！！！"));
        }

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String ndzb = null;
            String wszh = null;

            int size = ndzb_wszh.size();
            for (int i = 0; i < size; i++) {
                String haoma = (String) ndzb_wszh.get(i);
                if (haoma == null || haoma.equals("")) {
                    throw new Exception("完税证号和年度字别不能为空！！！");
                }

                if (haoma.length() != 11 || haoma.length() != 12) {
                    throw new Exception("完税证号或年度字别的位数不合法！！！");
                }
                ndzb = haoma.substring(0, 4);
                wszh = haoma.substring(4);

                Debug.out("完税凭证号： " + wszh);
                Debug.out("年度字别： " + ndzb);

                StringBuffer sql = new StringBuffer(
                        "UPDATE sbdb.sb_jl_qswszz a ");
                sql.append("SET a.jbdh = '").append(jbdh).append(
                        "' WHERE a.wszh = '")
                        .append(wszh).append("' AND ndzb = '").append(ndzb).
                        append("' ");

                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
                Debug.out("反填申报单号给完税证主表....");

                flag = true;
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(ud, "契税申报征收－InterFaceProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
            return flag;
        }

    }

    /**
     * 测试使用
     * @param args
     */
    public static void main(String[] args) {
        try {
            /*ArrayList wszList = (ArrayList)InterFaceProcessor.getWszDataQS("0041186", "2003");
                         System.out.println(wszList.size() );
                         if(wszList.size() > 0)
                         {
                Qswszz wsz = ((QueryWszBo) wszList.get(0)).getQswszzVo();
                System.out.println(wsz.getHjsjje());
                         }*/

            /*
                         ArrayList list = new ArrayList();
                         String haoma = "20030000012";
                         String jbdh = "12345678";
                         list.add(haoma);

                         UserData ud = new UserData();
                         ud.yhid = "hanl";
                         ud.ssdwdm = "0601";
                         ud.yhmc = "韩雷";
                         ud.xtsbm1 = "0106010017";


             boolean flag = InterFaceProcessor.Jiebao(list,jbdh, ud);
                         System.out.println(flag);*/
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
