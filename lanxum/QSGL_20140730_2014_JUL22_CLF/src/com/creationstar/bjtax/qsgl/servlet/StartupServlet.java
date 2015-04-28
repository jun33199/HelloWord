package com.creationstar.bjtax.qsgl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QsglPropertiesUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;


public class StartupServlet extends HttpServlet {
    /**
     * 初始化全局变量.
     *
     * @throws  ServletException    抛出的servlet异常
     */
    public void init() throws ServletException {
        //将processor-map.properties放入到 ServletContext 中，
        //以备 各个业务的 Action 调用，定位processor之用；
        Properties prop = QsglPropertiesUtil.getProperties(Constants.
                PROCESSOR_MAP_FILE_NAME);
        this.getServletContext().setAttribute(Constants.PROCESSOR_MAP_FILE_NAME,
                                              prop);

        Debug.out(" 重新部署了契税管理的WEB MODEL ");

        /* 初始化代码表到application中 */
        try {
            //构造VoPackage
            VOPackage vo = new VOPackage();
            //构造需要存放代码表的Vo的ArrayList
            HashMap codesMap = new HashMap();
            ArrayList zcwhList = new ArrayList();
            ArrayList bzList = new ArrayList();
            ArrayList fwlxList = new ArrayList();
            ArrayList jsfsList = new ArrayList();
            ArrayList nsrlxList = new ArrayList();
            ArrayList zjlxList = new ArrayList();
            ArrayList fwytList = new ArrayList();
            ArrayList bzgrfwytList = new ArrayList();
            ArrayList bzfgrfwytList = new ArrayList();
            ArrayList qszyList = new ArrayList();
            ArrayList jmzcList = new ArrayList();
            ArrayList gjList = new ArrayList();
            ArrayList swjgzzjgList = new ArrayList();

            ArrayList tdfwytjmList = new ArrayList();//zzb add  090820 房屋类型减免分类
            
            //发票类代码表 Begin add tutu 2013-05-07
            ArrayList fpzlList = new ArrayList();//发票种类代码List
            ArrayList kplxList = new ArrayList();//开票类型代码List
            //发票类代码表 End
            
            //房屋产权证标注住房类型代码表 Begin add tutu 2013-08-14
            ArrayList fwcqzbzzflxList = new ArrayList();
            
            ArrayList fwxzList = new ArrayList();//唐昌富  add 2013-10-13 存量房房屋性质
            ArrayList fwszqyList = new ArrayList();//唐昌富  add 2013-10-13 房屋所在区域
            ArrayList qszyxsmxList = new ArrayList();//房屋权属转移明细,added by zhangj
            
            //房屋产权证标注住房类型代码表 End

            HashMap BzMap = new HashMap(); //不征情况代码表
            HashMap jmzcMap = new HashMap(); //减免政策代码表map
            ArrayList jmzcGrList = new ArrayList(); //减免政策代码表－－个人
            ArrayList jmzcFgrList = new ArrayList(); //减免政策代码表map－－非个人
            ArrayList tdjcList = new ArrayList(); //土地级次代码表
            ArrayList szqyList = new ArrayList(); //土地级次代码表
            codesMap.put(Constants.ZCWH, zcwhList);
            codesMap.put(Constants.BZ, bzList);
            codesMap.put(Constants.FWLX, fwlxList);
            codesMap.put(Constants.JSFS, jsfsList);
            codesMap.put(Constants.NSRLX, nsrlxList);
            codesMap.put(Constants.BZQK, BzMap); //不征情况代码表
            codesMap.put(Constants.ZJLX, zjlxList);
            codesMap.put(Constants.TDFWYT, fwytList);
            codesMap.put(Constants.BZ_TDFWYT_GR, bzgrfwytList);
            codesMap.put(Constants.BZ_TDFWYT_FGR, bzfgrfwytList);
            codesMap.put(Constants.TDFWQSZY, qszyList);
            codesMap.put(Constants.JMZC, jmzcList);
            codesMap.put(Constants.JMZCMAP, jmzcMap);
            codesMap.put(Constants.JMZC_GR, jmzcGrList);
            codesMap.put(Constants.JMZC_FGR, jmzcFgrList);
            codesMap.put(Constants.GJ, gjList);
            codesMap.put(Constants.SWJGZZJG, swjgzzjgList);
            codesMap.put(Constants.TDJC, tdjcList);
            codesMap.put(Constants.SZQY, szqyList);

            codesMap.put(Constants.TDFWYTJM, tdfwytjmList);//zzb add  090820 房屋类型减免分类
            
            //发票类代码表 Begin add tutu 2013-05-07
            codesMap.put(Constants.FPZL, fpzlList);//发票种类代码List
            codesMap.put(Constants.KPLX, kplxList);//开票类型代码List
            //发票类代码表 End
            
            //房屋产权证标注住房类型代码表 Begin add tutu 2013-08-14
            codesMap.put(Constants.FWCQZBZZFLX, fwcqzbzzflxList);//房屋产权证标注住房类型代码List
            
            codesMap.put(Constants.FWXZ, fwxzList);//唐昌富  add 2013-10-13 存量房房屋性质
            codesMap.put(Constants.FWSZQY, fwszqyList);//唐昌富  add 2013-10-13 房屋所在区域
			codesMap.put(Constants.QS_JMXZ, new ArrayList());//契税减免性质            
			codesMap.put(Constants.QSZYXSMX, qszyxsmxList);//房屋权属转移明细,added by zhangj
			
            //房屋产权证标注住房类型代码表 End

            //构造向后台传输的Data，放入到VoPackage.setData()中
            vo.setData(codesMap);
            //VoPackage.setUserData()放入null
            vo.setUserData(null);
            vo.setAction(ActionType.QUERY);

            //VoPackage.setProcessor()中，
            //定义调用CodeTableProcessor的字符串Constants.Load_CodeTables;
            vo.setProcessor(prop.getProperty(Constants.Load_CodeTables));
            codesMap = (HashMap) QsglProxy.getInstance().process(vo);

            this.getServletContext().setAttribute(Constants.CodeTables,
                                                  codesMap);
        } catch (Exception ex) {

        }
    }

    /**
     * doGet   处理get请求.
     *
     * @param request   the HttpServletRequest
     * @param response  the HttpServletResponse
     * @throws ServletException 抛出的servlet异常
     * @throws IOException      抛出的I/O异常
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }

    /**
     * doPost   处理post请求.
     *
     * @param request   the HttpServletRequest
     * @param response  the HttpServletResponse
     * @throws ServletException 抛出的servlet异常
     * @throws IOException      抛出的I/O异常
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Debug.out("int StartupServlet Service Reload ...");

        try {
            /* 初始化代码表到application中 */

        } catch (Exception ex) {
            Debug.printException(ex);
        }
    }
}
