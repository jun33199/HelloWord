//package com.syax.bjtax.shenbao.jmba.xmlvo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.syax.bjtax.ca.xml.util.XMLParseHelper;
//import com.syax.bjtax.shenbao.model.common.NsrxxVO;
//import com.syax.frame.exception.ApplicationException;
//
//public class Test {
//
//	public static JmbaVO createCxVOFromXML() {
//		JmbaVO cxvo=new JmbaVO();
//		//String xmldata ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030000]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[0000003]]></jsjdm><nsrmc><![CDATA[测试用户]]></nsrmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CDATA[bh1]]></basqbh><band><![CDATA[2009]]></band><jmbasxdm><![CDATA[]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc></jmsbajl><jmsbajl><basqbh><![CDATA[bh2]]></basqbh><band><![CDATA[2019]]></band><jmbasxdm><![CDATA[]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc></jmsbajl></taxdoc>";
//		String xmldata ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030000]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[0000003]]></jsjdm><nsrmc><![CDATA[测试用户]]></nsrmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CDATA[bh1]]></basqbh><band><![CDATA[2009]]></band><jmbasxdm><![CDATA[]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc><bajlzlqd><xh><![CDATA[zlqd1]]></xh><zlqd><![CDATA[]]></zlqd></bajlzlqd><bajlzlqd><xh><![CDATA[zlqd2]]></xh><zlqd><![CDATA[]]></zlqd></bajlzlqd></jmsbajl></taxdoc>";
//		
//		try {
//			XMLParseHelper.parseXMLString(cxvo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
//		} catch (ApplicationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return cxvo;
//	}
//
//	public static JmbaVO testVOFromXML() {
//		JmbaVO cxvo=new JmbaVO();
//		//String xmldata ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030000]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[0000003]]></jsjdm><nsrmc><![CDATA[测试用户]]></nsrmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CDATA[bh1]]></basqbh><band><![CDATA[2009]]></band><jmbasxdm><![CDATA[]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc></jmsbajl><jmsbajl><basqbh><![CDATA[bh2]]></basqbh><band><![CDATA[2019]]></band><jmbasxdm><![CDATA[]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc></jmsbajl></taxdoc>";
//		String xmldata ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[]]></ywlx><ywczlx><![CDATA[1]]></ywczlx><nsrxx><jsjdm><![CDATA[]]></jsjdm><nsrmc><![CDATA[]]></nsrmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CDATA[]]></basqbh><band><![CDATA[]]></band><jmbasxdm><![CDATA[0070]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc><qysdsjmba><xh><![CDATA[]]></xh><basqwsh><![CDATA[]]></basqwsh><jsjdm><![CDATA[]]></jsjdm><band><![CDATA[]]></band><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><jszrlxdm><![CDATA[]]></jszrlxdm><jszyht><![CDATA[1]]></jszyht><djb><![CDATA[1]]></djb><mxb><![CDATA[1]]></mxb><hsqksm><![CDATA[1]]></hsqksm><qtzl><![CDATA[dflgj]]></qtzl><jszrsd><![CDATA[1]]></jszrsd><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq></qysdsjmba></jmsbajl></taxdoc>";
//		
//		try {
//			XMLParseHelper.parseXMLString(cxvo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
//		} catch (ApplicationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return cxvo;
//	}
//	
//	public static JmbaVO createBa01VOFromXML() {
//		JmbaVO cxvo=new JmbaVO();
//		//cxvo.getListTypeMap().put("qysdsjmba","com.syax.bjtax.shenbao.jmba.xmlvo.Jmba01VO");
//		String xmldata ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030001]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[0000003]]></jsjdm><nsrmc><![CDATA[测试用户]]></nsrmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CDATA[bh1]]></basqbh><band><![CDATA[2009]]></band><jmbasxdm><![CDATA[0010]]></jmbasxdm><jmbasxmc><![CDATA[]]></jmbasxmc><bajmsehbl><![CDATA[]]></bajmsehbl><fhwjmc><![CDATA[]]></fhwjmc><qsrq><![CDATA[]]></qsrq><ztdm><![CDATA[]]></ztdm><ztmc><![CDATA[]]></ztmc><jzrq><![CDATA[]]></jzrq><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><bsfsdm><![CDATA[]]></bsfsdm><bsfsmc><![CDATA[]]></bsfsmc><qysdsjmba><XH><![CDATA[mx01-01]]></XH><ZYZHLYZLDM><![CDATA[]]></ZYZHLYZLDM><ZYZHLYZLMC><![CDATA[]]></ZYZHLYZLMC><WJMC><![CDATA[]]></WJMC><WH><![CDATA[]]></WH><ZSBH><![CDATA[]]></ZSBH><ZSYXKSRQ><![CDATA[]]></ZSYXKSRQ><ZSYXJZRQ><![CDATA[]]></ZSYXJZRQ></qysdsjmba><qysdsjmba><XH><![CDATA[mx01-01]]></XH><ZYZHLYZLDM><![CDATA[]]></ZYZHLYZLDM><ZYZHLYZLMC><![CDATA[]]></ZYZHLYZLMC><WJMC><![CDATA[]]></WJMC><WH><![CDATA[]]></WH><ZSBH><![CDATA[]]></ZSBH><ZSYXKSRQ><![CDATA[]]></ZSYXKSRQ><ZSYXJZRQ><![CDATA[]]></ZSYXJZRQ></qysdsjmba></jmsbajl></taxdoc>";
//		
//		try {
//			XMLParseHelper.parseXMLString(cxvo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
//		} catch (ApplicationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return cxvo;
//	}
//
//	public static JmbaVO createCxVO() {
//		JmbaVO cxvo=new JmbaVO();
//		cxvo.setXsltVersion("20100101");
//		cxvo.setSchemaVersion("20100101");
//		cxvo.setYwlx("030000");
//		cxvo.setYwczlx("0");
//		JmbaZbVO zb1 = new JmbaZbVO();
//		zb1.setBasqbh("bh1");
//		zb1.setBand("2009");
//		JmbaZbVO zb2 = new JmbaZbVO();
//		zb2.setBasqbh("bh2");
//		zb2.setBand("2019");
//		
//		NsrxxVO nsr = new NsrxxVO();
//		nsr.setJsjdm("0000003");
//		nsr.setNsrmc("测试用户");
//		
//		//JmbaZbVO[] zbvos=new JmbaZbVO[]{zb1,zb2};
//		List zbvos= new ArrayList();
//		zbvos.add(zb1);
//		zbvos.add(zb2);
//		cxvo.setJmsbajl(zbvos);
//		cxvo.setNsrxx(nsr);
//		
//		return cxvo;
//		
//	}
//	public static JmbaVO createBazbVO() {
//		JmbaVO bavo=new JmbaVO();
//		bavo.setXsltVersion("20100101");
//		bavo.setSchemaVersion("20100101");
//		bavo.setYwlx("030000");
//		bavo.setYwczlx("0");
//		JmbaZbVO zb1 = new JmbaZbVO();
//		zb1.setBasqbh("bh1");
//		zb1.setBand("2009");
//
//		JmbaZlqdVO zlqd1=new JmbaZlqdVO();
//		zlqd1.setXh("zlqd1");
//		JmbaZlqdVO zlqd2=new JmbaZlqdVO();
//		zlqd2.setXh("zlqd2");
//		
//		//JmbaZlqdVO[] zlqds=new JmbaZlqdVO[]{zlqd1,zlqd2};
//		List zlqds = new ArrayList();
//		zlqds.add(zlqd1);
//		zlqds.add(zlqd2);
//		
//		zb1.setBajlzlqd(zlqds);
//		
//		
//		NsrxxVO nsr = new NsrxxVO();
//		nsr.setJsjdm("0000003");
//		nsr.setNsrmc("测试用户");
//		
//		
//		//JmbaZbVO[] zbvos=new JmbaZbVO[]{zb1};
//		List zbvos= new ArrayList();
//		zbvos.add(zb1);
//		bavo.setJmsbajl(zbvos);
//		bavo.setNsrxx(nsr);
//		
//		
//		return bavo;
//		
//	}
//	public static JmbaVO createBa01VO() {
//		JmbaVO ba01vo=new JmbaVO();
//		ba01vo.setXsltVersion("20100101");
//		ba01vo.setSchemaVersion("20100101");
//		ba01vo.setYwlx("030001");
//		ba01vo.setYwczlx("0");
//		JmbaZbVO zb1 = new JmbaZbVO();
//		zb1.setBasqbh("bh1");
//		zb1.setBand("2009");
//		zb1.setJmbasxdm("0010");
//		
//		Jmba01VO mx1 = new Jmba01VO();
//		mx1.setXH("mx01-01");
//
//		Jmba01VO mx2 = new Jmba01VO();
//		mx2.setXH("mx01-01");
//
//		//JmbamxVoInterface[] mxs=new JmbamxVoInterface[]{mx1,mx2};
//		List mxs= new ArrayList();
//		mxs.add(mx1);
//		mxs.add(mx2);
//		zb1.setQysdsjmba(mxs);
//		
//		NsrxxVO nsr = new NsrxxVO();
//		nsr.setJsjdm("0000003");
//		nsr.setNsrmc("测试用户");
//		
//		//JmbaZbVO[] zbvos=new JmbaZbVO[]{zb1};
//		List zbvos= new ArrayList();
//		zbvos.add(zb1);
//		ba01vo.setJmsbajl(zbvos);
//		ba01vo.setNsrxx(nsr);
//		
//		return ba01vo;
//		
//		
//	}
//	
//	public void test() {
//		
//		SimpleOne one = new SimpleOne();
//		
//		one.setName(new String[]{"张三","李四","王五"});
//		one.setAge(new String[]{"22","23","24"});
//		one.setNum(new String[]{"001","002","003"});
//		
//		JmbaZlqdVO zl = new JmbaZlqdVO();
//	//	zl.setXh(new String[]{"1","2","3"});
//		//zl.setZlqd(new String[]{"资料清单一","资料清单二","资料清单三"});
//		
//		JmbaZbVO zb1 = new JmbaZbVO();
//		zb1.setBasqbh("bh1");
//		zb1.setBand("2009");
//	//	zb1.setZlqdvo(zl);
//	//	zb1.setMxvo(one);
//		
//		JmbaZbVO zb2 = new JmbaZbVO();
//		zb2.setBasqbh("bh2");
//		zb2.setBand("2019");
//		
//		NsrxxVO nsr = new NsrxxVO();
//		
//		nsr.setJsjdm("0000003");
//		nsr.setNsrmc("测试用户");
//		JmbaVO vo = new JmbaVO();
//		
//		//JmbaZbVO[] zbs = new JmbaZbVO[2];
//		List zbs = new ArrayList();
//		zbs.add(zb1);
//		zbs.add(zb2);
//		//zbs[0] = zb1;
//		//zbs[1] = zb2;
//		
//		vo.setNsrxx(nsr);
//		vo.setJmsbajl(zbs);
//		vo.setSchemaVersion("schemage");
//		vo.setXsltVersion("091219");
//		
//		
//		System.out.println(vo.toXML());
//	}
//	
//	public static void main(String[] args){
//		JmbaVO vo1 = null;
//		vo1 = testVOFromXML();
////		vo1 = createBa01VOFromXML();
//		System.out.println(vo1.getYwlx());
//		System.out.println(((Jmba07Vo)((JmbaZbVO)vo1.getJmsbajl().get(0)).getQysdsjmba().get(0)).getXh());
//		 //vo1 = createCxVO();
//		//System.out.println(vo1.toXML());
//		
//		//vo1 = createBa01VO();
//		//System.out.println(vo1.toXML());
//
//		//vo1 = createBazbVO();
//		//System.out.println(vo1.toXML());
//}
//}
