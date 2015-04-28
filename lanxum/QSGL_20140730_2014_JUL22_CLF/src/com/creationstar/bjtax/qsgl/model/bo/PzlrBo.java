package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PzlrBo implements Serializable {

    private String id;
    //���������
    private String jsjdm = "";
    //���ؼ���
    private String tdjb;
    //���ز���Ŀ����
    private String fdcxmmc;
    //�ݻ���
    private String rjl;
    //�������
    private String jzmj;
    //ƽ�����׼۸�
    private String pjjyjg;
    /*added by gaoyh to 20141016*/
    //����ÿ�׼۸�
    private String fwmtjg;
	//ʹ����ʼ����
    private String qsrq;
    //ʹ�ý�������
    private String jzrq;
    //�Ƿ�ɲ�����Ǩ��Ϣ
    private String czcq;
    //�Ƿ�ɲ����ѹ�����ס��������Ϣ
    private String czcsxx;
    //�Ƿ�ɲ������ݽ�����Ϣ
    private String czfwjyxx;
    //������Ա
    private String czry;

    //��˰������
    private String nsrmc;
    //��˰������˰�����
    private String ssdwmc;
    private String xtdqsj;
    //xml�ļ�item����list
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
     * ���ݴ����name������������Ӧ��get������ȡ��value��ֵ
     * @param name String
     * @return String
     */
//    public String getValue(String name){
//        //ƴд��������
//        String methodName = "get"+name.substring(0,1).toUpperCase()
//                            +name.substring(1);
//        String value="";
//        try{
//            //ȡ�÷���
//            Method method = this.getClass().getMethod(methodName, new Class[]
//                    {});
//            // ���÷���ȡ��ֵ
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
     * ���÷�����Ƶ�field������ȡ��ǰ����field���ֵ
     * @param name String����ǰ���������
     * @return String ����ǰ��ָ���������ֵ
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
