package com.creationstar.bjtax.qsgl.BizLogic.dao.Base;

import java.io.IOException;
import java.util.Properties;

import com.creationstar.bjtax.qsgl.BizLogic.dao.BlQueryJksDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.CodeTablesDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.CqxxCwbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.CqxxbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.DrpcInfoDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.DrzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.FgrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.GrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.HdjmmxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.HdtzsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JmsbbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsfsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.PlcxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.PzlrDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.QswszhzDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.QswszmxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.QswszzDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbcqglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbjkmxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbjkzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbtdfwglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SpjgxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SwjgzzjgDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SzsmDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SzsmYskmDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.WbhsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.YsjcDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.YskmDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.ZcwhDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.ZhDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.ClfjycsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.ClfjyxxCXDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.FwhdxxbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.FwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.HtypzdzgxbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.HtypzdzgxbLsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.MfgrxxBuyerDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.MfgrxxSellerDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.MfsbxxmxSellerDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.MfsbxxprintSellerDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.MfsbxxzbSellerDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.QscxdyrzDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl.SzsmInitDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl.FpczmxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl.FpczzDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl.FpkcDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl.FpkfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl.FpzlDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.fpgl.KplxDAO;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.framework.exception.BaseException;

/**
 * dao对象工厂
 */
public class DAOFactory {

    private static final String PROPERTY_FILE = "qsgl_dao-processor.properties";


    /**
     * 储存配置文件信息
     */
    Properties prop = new Properties();

    /**
     * DAOFactory自身的实例
     */
    private static DAOFactory instance;

    /**
     * Access method for the instance property.
     *
     * @return the current value of the instance property
     * @throws BaseException
     * @throws IOException
     */
    public static DAOFactory getInstance() throws BaseException, IOException {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    private DAOFactory() throws IOException {
        //从文件中查询类名
        prop.load(getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE));
    }

    /**
     * 获得DAO的通用方法（推荐使用）
     * @param Dao_name 在Constants.java中定义的Dao的名称；
     * @return
     * @throws Exception
     */ public BaseDAO getDAO(String Dao_name) throws Exception {
        BaseDAO dao = (BaseDAO) Class.forName(prop.getProperty(Dao_name)).
                      newInstance();

        return dao;
    }

    /**
     * ZcwhDAO dao;
     * dao = (ZcwhDAO) Class.forName(ROLE_RESOURCE_DAO).newInstance();
     * return dao;
     *
     * @return ZcwhDAO
     * @throws Exception
     */
    public ZcwhDAO getZcwhDAO() throws Exception {
        ZcwhDAO dao;
        dao = (ZcwhDAO) Class.forName(prop.getProperty(Constants.Zcwh_DAO)).
              newInstance();

        return dao;
    }

    /** @todo 补录注释 */
    public FgrxxDAO getFgrxxDAO() throws Exception {
        FgrxxDAO dao;
        dao = (FgrxxDAO) Class.forName(prop.getProperty(Constants.FgrxxDAO)).
              newInstance();

        return dao;
    }

    public GrxxDAO getGrxxDAO() throws Exception {
        GrxxDAO dao;
        dao = (GrxxDAO) Class.forName(prop.getProperty(Constants.GrxxDAO)).
              newInstance();

        return dao;
    }

    public JmsbbDAO getJmsbbDAO() throws Exception {
        JmsbbDAO dao;
        dao = (JmsbbDAO) Class.forName(prop.getProperty(Constants.JmsbbDAO)).
              newInstance();

        return dao;
    }

    public JsblcqDAO getJsblcqDAO() throws Exception {
        JsblcqDAO dao;
        dao = (JsblcqDAO) Class.forName(prop.getProperty(Constants.JsblcqDAO)).
              newInstance();

        return dao;
    }

    public JsblgyzfDAO getJsblgyzfDAO() throws Exception {
        JsblgyzfDAO dao;
        dao = (JsblgyzfDAO) Class.forName(prop.getProperty(Constants.
                JsblgyzfDAO)).newInstance();

        return dao;
    }

    public SbtdfwglDAO getSbtdfwglDAO() throws Exception {
        SbtdfwglDAO dao;
        dao = (SbtdfwglDAO) Class.forName(prop.getProperty(Constants.
                SbtdfwglDAO)).newInstance();

        return dao;
    }

    public SpjgxxDAO getSpjgxxDAO() throws Exception {
        SpjgxxDAO dao;
        dao = (SpjgxxDAO) Class.forName(prop.getProperty(Constants.SpjgxxDAO)).
              newInstance();

        return dao;
    }

    public TufwxxDAO getTufwxxDAO() throws Exception {
        TufwxxDAO dao;
        dao = (TufwxxDAO) Class.forName(prop.getProperty(Constants.TufwxxDAO)).
              newInstance();

        return dao;
    }

    public SbzbDAO getSbzbDAO() throws Exception {
        SbzbDAO dao;
        dao = (SbzbDAO) Class.forName(prop.getProperty(Constants.SbzbDAO)).
              newInstance();

        return dao;
    }

    public SbcqglDAO getSbcqglDAO() throws Exception {
        SbcqglDAO dao;
        dao = (SbcqglDAO) Class.forName(prop.getProperty(Constants.SbcqglDAO)).
              newInstance();

        return dao;
    }

    public SbgyzfDAO getSbgyzfDAO() throws Exception {
        SbgyzfDAO dao;
        dao = (SbgyzfDAO) Class.forName(prop.getProperty(Constants.SbgyzfDAO)).
              newInstance();

        return dao;
    }

    public HdtzsDAO getHdtzsDAO() throws Exception {
        HdtzsDAO dao;
        dao = (HdtzsDAO) Class.forName(prop.getProperty(Constants.HdtzsDAO)).
              newInstance();

        return dao;
    }

    public HdjmmxDAO getHdjmmxDAO() throws Exception {
        HdjmmxDAO dao;
        dao = (HdjmmxDAO) Class.forName(prop.getProperty(Constants.HdjmmxDAO)).
              newInstance();

        return dao;
    }

    public CodeTablesDAO getCodeTablesDAO() throws Exception {
        CodeTablesDAO dao;
        dao = (CodeTablesDAO) Class.forName(prop.getProperty(Constants.
                CodeTables_DAO)).newInstance();

        return dao;
    }

    public QswszzDAO getQswszzDAO() throws Exception {
        QswszzDAO dao;
        dao = (QswszzDAO) Class.forName(prop.getProperty(Constants.Wsz_DAO)).
              newInstance();

        return dao;
    }

    public QswszhzDAO getQswszhzDAO() throws Exception {
        QswszhzDAO dao;
        dao = (QswszhzDAO) Class.forName(prop.getProperty(Constants.Wszhz_DAO)).
              newInstance();

        return dao;
    }

    public QswszmxDAO getQswszmxDAO() throws Exception {
        QswszmxDAO dao;
        dao = (QswszmxDAO) Class.forName(prop.getProperty(Constants.Wszmx_DAO)).
              newInstance();

        return dao;
    }

    public SbjkzbDAO getSbjkzbDAO() throws Exception {
        SbjkzbDAO dao;
        dao = (SbjkzbDAO) Class.forName(prop.getProperty(Constants.Jkszb_DAO)).
              newInstance();

        return dao;
    }

    public SbjkmxDAO getSbjkmxDAO() throws Exception {
        SbjkmxDAO dao;
        dao = (SbjkmxDAO) Class.forName(prop.getProperty(Constants.Jksmx_DAO)).
              newInstance();

        return dao;
    }

    public SzsmYskmDAO getSzsmYskmDAO() throws Exception {
        SzsmYskmDAO dao;
        dao = (SzsmYskmDAO) Class.forName(prop.getProperty(Constants.
                SzsmYskm_DAO)).
              newInstance();

        return dao;
    }

    public SzsmDAO getSzsmDAO() throws Exception {
        SzsmDAO dao;
        dao = (SzsmDAO) Class.forName(prop.getProperty(Constants.Szsm_DAO)).
              newInstance();

        return dao;
    }

    public YskmDAO getYskmDAO() throws Exception {
        YskmDAO dao;
        dao = (YskmDAO) Class.forName(prop.getProperty(Constants.Yskm_DAO)).
              newInstance();

        return dao;
    }

    public YsjcDAO getYsjcDAO() throws Exception {
        YsjcDAO dao;
        dao = (YsjcDAO) Class.forName(prop.getProperty(Constants.Ysjc_DAO)).
              newInstance();

        return dao;
    }

    public SwjgzzjgDAO getSwjgzzjgDAO() throws Exception {
        SwjgzzjgDAO dao;
        dao = (SwjgzzjgDAO) Class.forName(prop.getProperty(Constants.
                Swjgzzjg_DAO)).
              newInstance();

        return dao;
    }

    public ZhDAO getZhDAO() throws Exception {
        ZhDAO dao;
        dao = (ZhDAO) Class.forName(prop.getProperty(Constants.Zh_DAO)).
              newInstance();

        return dao;
    }

    public WbhsDAO getWbhsDAO() throws Exception {
        WbhsDAO dao;
        dao = (WbhsDAO) Class.forName(prop.getProperty(Constants.Wbhs_DAO)).
              newInstance();

        return dao;
    }

    public DrzbDAO getDrzbDAO() throws Exception {
        DrzbDAO dao;
        dao = (DrzbDAO) Class.forName(prop.getProperty(Constants.Drzb_DAO)).
              newInstance();

        return dao;
    }

    public DrpcInfoDAO getDrpcInfoDAO() throws Exception {
        DrpcInfoDAO dao;
        dao = (DrpcInfoDAO) Class.forName(prop.getProperty(Constants.
                DrpcInfo_DAO)).
              newInstance();

        return dao;
    }

    public PlcxDAO getPlcxDAO() throws Exception {
        PlcxDAO dao;
        dao = (PlcxDAO) Class.forName(prop.getProperty(Constants.Plcx_DAO)).
              newInstance();
        return dao;
    }

    public CqxxbDAO getCqxxbDAO() throws Exception {
        CqxxbDAO dao;
        dao = (CqxxbDAO) Class.forName(prop.getProperty(Constants.CQXXB_DAO)).
              newInstance();
        return dao;
    }

    public CqxxCwbDAO getCqxxCwbDAO() throws Exception {
        CqxxCwbDAO dao;
        dao = (CqxxCwbDAO) Class.forName(
                "com.creationstar.bjtax.qsgl.BizLogic.dao.CqxxCwbDAO").
              newInstance();
        return dao;
    }

    public JsfsDAO getJsfsDAO() throws Exception {
        JsfsDAO dao;
        dao = (JsfsDAO) Class.forName(prop.getProperty(Constants.Jsfs_DAO)).
              newInstance();
        return dao;
    }

    public BlQueryJksDAO getBlQueryJksDAO() throws Exception {
        BlQueryJksDAO dao;
        Object[] objArray = prop.keySet().toArray();
        dao = (BlQueryJksDAO) Class.forName(prop.getProperty(Constants.
                Bl_QueryJksDAO)).
              newInstance();
        return dao;
    }

    public PzlrDAO getPzlrDAO() throws Exception {
        PzlrDAO dao;

        dao = (PzlrDAO) Class.forName(prop.getProperty(Constants.PLSL_PZLRDAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 发票库房代码
     * @return
     * @throws Exception
     */
    public FpkfDAO getFpkfDAO() throws Exception {
    	FpkfDAO dao;

        dao = (FpkfDAO) Class.forName(prop.getProperty(Constants.Fpkf_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 发票种类代码表
     * @return
     * @throws Exception
     */
    public FpzlDAO getFpzlDAO() throws Exception {
    	FpzlDAO dao;

        dao = (FpzlDAO) Class.forName(prop.getProperty(Constants.Fpzl_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 开票种类代码表
     * @return
     * @throws Exception
     */
    public KplxDAO getKplxDAO() throws Exception {
    	KplxDAO dao;

        dao = (KplxDAO) Class.forName(prop.getProperty(Constants.Kplx_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 发票库存表
     * @return
     * @throws Exception
     */
    public FpkcDAO getFpkcDAO() throws Exception {
    	FpkcDAO dao;

        dao = (FpkcDAO) Class.forName(prop.getProperty(Constants.Fpkc_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 发票开票主表
     * @return
     * @throws Exception
     */
    public FpczzDAO getFpczzDAO() throws Exception {
    	FpczzDAO dao;

        dao = (FpczzDAO) Class.forName(prop.getProperty(Constants.Fpczz_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 发票开票明细
     * @return
     * @throws Exception
     */
    public FpczmxDAO getFpczmxDAO() throws Exception {
    	FpczmxDAO dao;

        dao = (FpczmxDAO) Class.forName(prop.getProperty(Constants.Fpczmx_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 房屋信息
     * @return
     * @throws Exception
     */
    public FwxxDAO getFwxxDAO() throws Exception {
    	FwxxDAO dao;

        dao = (FwxxDAO) Class.forName(prop.getProperty(Constants.Fwxx_DAO)).
              newInstance();
        return dao;
    }
    
    
    /**
     * 存量房交易信息
     * @return
     * @throws Exception
     */
    public ClfjyxxCXDAO getClfjyxxCXDAO() throws Exception {
    	ClfjyxxCXDAO dao;

        dao = (ClfjyxxCXDAO) Class.forName(prop.getProperty(Constants.ClfjyxxCX_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 买方个人信息
     * @return
     * @throws Exception
     */
    public MfgrxxBuyerDAO getMfgrxxBuyerDAO() throws Exception {
    	MfgrxxBuyerDAO dao;

        dao = (MfgrxxBuyerDAO) Class.forName(prop.getProperty(Constants.MfgrxxBuyer_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 卖方个人信息
     * @return
     * @throws Exception
     */
    public MfgrxxSellerDAO getMfgrxxSellerDAO() throws Exception {
    	MfgrxxSellerDAO dao;

        dao = (MfgrxxSellerDAO) Class.forName(prop.getProperty(Constants.MfgrxxSeller_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 卖方申报信息表主表
     * @return
     * @throws Exception
     */
    public MfsbxxzbSellerDAO getMfsbxxzbSellerDAO() throws Exception {
    	MfsbxxzbSellerDAO dao;

        dao = (MfsbxxzbSellerDAO) Class.forName(prop.getProperty(Constants.MfsbxxzbSeller_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 卖方申报信息表子表
     * @return
     * @throws Exception
     */
    public MfsbxxmxSellerDAO getMfsbxxmxSellerDAO() throws Exception {
    	MfsbxxmxSellerDAO dao;

        dao = (MfsbxxmxSellerDAO) Class.forName(prop.getProperty(Constants.MfsbxxmxSeller_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 合同与凭证对照关系表
     * @return
     * @throws Exception
     */
    public HtypzdzgxbDAO getHtypzdzgxbDAO() throws Exception {
    	HtypzdzgxbDAO dao;

        dao = (HtypzdzgxbDAO) Class.forName(prop.getProperty(Constants.Htypzdzgxb_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 合同与凭证对照关系临时表
     * @return
     * @throws Exception
     */
    public HtypzdzgxbLsDAO getHtypzdzgxbLsDAO() throws Exception {
    	HtypzdzgxbLsDAO dao;

        dao = (HtypzdzgxbLsDAO) Class.forName(prop.getProperty(Constants.HtypzdzgxbLs_DAO)).
              newInstance();
        return dao;
    }

    /**
     * 税种税目初始化
     * @return
     * @throws Exception
     */
    public SzsmInitDAO getSzsmInitDAO() throws Exception {
    	SzsmInitDAO dao;

        dao = (SzsmInitDAO) Class.forName(prop.getProperty(Constants.SzsmInit_DAO)).
              newInstance();
        return dao;
    }
    
    /**
     * 卖方申报信息查询打印
     * @return
     * @throws Exception
     */
    public MfsbxxprintSellerDAO getMfsbxxprintSellerDAO() throws Exception {
    	MfsbxxprintSellerDAO dao;

        dao = (MfsbxxprintSellerDAO) Class.forName(prop.getProperty(Constants.MfsbxxprintSeller_DAO)).
              newInstance();
        return dao;
    }  
    
    /**
     * 存量房税务机关核定信息录入
     * @return
     * @throws Exception
     */
    public FwhdxxbDAO getFwhdxxbDAO() throws Exception {
    	FwhdxxbDAO dao;

        dao = (FwhdxxbDAO) Class.forName(prop.getProperty(Constants.Fwhdxxb_DAO)).
              newInstance();
        return dao;
    } 
    
    /**
     * 存量房交易契税查询打印操作记录日志
     * @return
     * @throws Exception
     */
    public QscxdyrzDAO getQscxdyrzDAO() throws Exception {
    	QscxdyrzDAO dao;

        dao = (QscxdyrzDAO) Class.forName(prop.getProperty(Constants.Qscxdyrz_DAO)).
              newInstance();
        return dao;
    } 
    
    /**
     * 存量房交易参数表
     * @return
     * @throws Exception
     */
    public ClfjycsDAO getClfjycsDAO() throws Exception {
    	ClfjycsDAO dao;

        dao = (ClfjycsDAO) Class.forName(prop.getProperty(Constants.Clfjycs_DAO)).
              newInstance();
        return dao;
    } 
}
