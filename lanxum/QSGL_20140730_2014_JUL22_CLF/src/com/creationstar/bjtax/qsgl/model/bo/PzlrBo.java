package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PzlrBo implements Serializable {

    private String id;
    //计算机代码
    private String jsjdm = "";
    //土地级别
    private String tdjb;
    //房地产项目名称
    private String fdcxmmc;
    //容积率
    private String rjl;
    //建筑面积
    private String jzmj;
    //平均交易价格
    private String pjjyjg;
    /*added by gaoyh to 20141016*/
    //房屋每套价格
    private String fwmtjg;
	//使用起始日期
    private String qsrq;
    //使用结束日期
    private String jzrq;
    //是否可操作拆迁信息
    private String czcq;
    //是否可操作已购公有住房出售信息
    private String czcsxx;
    //是否可操作房屋交易信息
    private String czfwjyxx;
    //操作人员
    private String czry;

    //纳税人名称
    private String nsrmc;
    //纳税人所属税务机关
    private String ssdwmc;
    private String xtdqsj;
    //xml文件item对象list
    private List itemList = new ArrayList();

    public List getNameList() {
        List list = new ArrayList();
        list.add("jsjdm");
        list.add("nsrmc");
        list.add("fdcxmmc");
        list.add("tdjb");
        list.add("rjl");
        list.add("jzmj");
        list.add("pjjyjg");
        list.add("fwmtjg");
        list.add("qsrq");
        list.add("jzrq");
        list.add("czcq");
        list.add("czcsxx");
        list.add("czfwjyxx");
        return list;
    }

    public List getItemList() {
        List nameList = getNameList();
        for (int i = 0; i < getNameList().size(); i++) {
            PzxxXMLItem item = new PzxxXMLItem();
            String itemName = (String) nameList.get(i);
            item.setItemName(itemName);
            item.setItemValue(this.getFileValue(itemName));
            this.itemList.add(item);
        }

        return this.itemList;
    }

    public String getCzcq() {
        return czcq;
    }

    public String getCzcsxx() {
        return czcsxx;
    }

    public String getCzfwjyxx() {
        return czfwjyxx;
    }

    public String getCzry() {
        return czry;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getJsjdm() {
        return jsjdm;
        //return "aaaa";
    }

    public String getJzmj() {
        return jzmj;
    }

    public String getJzrq() {
        return jzrq;
    }

    public String getPjjyjg() {
        return pjjyjg;
    }

    public String getQsrq() {
        return qsrq;
    }

    public String getRjl() {
        return rjl;
    }


    public String getNsrmc() {
        return nsrmc;
    }

    public String getSsdwmc() {
        return ssdwmc;
    }

    public String getTdjb() {
        return tdjb;
    }

    public String getXtdqsj() {
        return xtdqsj;
    }

    public String getId() {
        return id;
    }

    public void setCzcq(String czcq) {
        this.czcq = czcq;
    }

    public void setCzcsxx(String czcsxx) {
        this.czcsxx = czcsxx;
    }

    public void setCzfwjyxx(String czfwjyxx) {
        this.czfwjyxx = czfwjyxx;
    }

    public void setCzry(String czry) {
        this.czry = czry;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public void setPjjyjg(String pjjyjg) {
        this.pjjyjg = pjjyjg;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }


    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSsdwmc(String ssdwmc) {
        this.ssdwmc = ssdwmc;
    }


    public void setTdjb(String tdjb) {
        this.tdjb = tdjb;
    }

    public void setXtdqsj(String xtdqsj) {
        this.xtdqsj = xtdqsj;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getFwmtjg() {
		return fwmtjg;
	}

	public void setFwmtjg(String fwmtjg) {
		this.fwmtjg = fwmtjg;
	}

    /**
     * 根据传入的name属性名调用相应的get方法，取得value的值
     * @param name String
     * @return String
     */
//    public String getValue(String name){
//        //拼写方法名称
//        String methodName = "get"+name.substring(0,1).toUpperCase()
//                            +name.substring(1);
//        String value="";
//        try{
//            //取得方法
//            Method method = this.getClass().getMethod(methodName, new Class[]
//                    {});
//            // 调用方法取得值
//            value = (String)method.invoke(this, new Class[]{});
//        }catch(Exception e){
//            e.printStackTrace();
//        }
////        Method[] methods=this.getClass().getMethods();
////        for(int i=0;i<methods.length;i++){
////            if(methods[i].getName().equalsIgnoreCase(methodName)){
////                value=methods[i].invoke(this,new Class[]{});
////            }
////        }
//        return value;
//    }

//    public static void main(String[] args){
//        PzlrBo bo = new PzlrBo();
////       List list = bo.getItemList();
////       System.out.println(list.size());
////           for(int i=0;i<list.size();i++){
////             PzxxXMLItem item = (PzxxXMLItem)list.get(i);
////             System.out.println("name==="+item.getItemName());
////             System.out.println("value==="+item.getItemValue());
////         }
//        String jsjdm = bo.getValue("jsjdm");
//        System.out.println("jsjdm="+jsjdm);
//
//
//    }
    /**
     * 利用反射机制的field类来获取当前类中field域的值
     * @param name String，当前类的属性域
     * @return String ，当前类指定属性域的值
     */
    public String getFileValue(String name) {
        String obj = "";
        try {
            Field f = this.getClass().getDeclaredField(name);

            obj = (String) f.get(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

//    public static void main(String[] args){
//        PzlrBo bo = new PzlrBo();
//        String jsjdm=bo.getFileValue("jsjdm");
//        System.out.println("jsjdm=="+jsjdm);
//    }



}
