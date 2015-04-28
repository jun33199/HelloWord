package com.ttsoft.bjtax.shenbao.fangtu.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.xml.XMLVOInterface;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.fangtu.SimpleTimestampConvert;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuForm;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChengzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter.AlterFangtuVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDJBXX;

public class Test {

	public static void main(String[] args) throws Exception {
		A a = new A();
		//a.setTs("20010101");
		a.setTs("");
		
		B b = new B();
		ConvertUtils.register(new SimpleTimestampConvert(null), Timestamp.class);
		
		
		//PropertyUtils.copyProperties(b, a);
		//BeanUtils.copyProperty(b, "ts", a.getTs());
		BeanUtils.copyProperties(b, a);
		
		ConvertUtils.deregister(Timestamp.class);
		//System.out.println(b.getTs());
		
	}

	/**
	 * @throws ApplicationException
	 */
	private static void vo2xml() throws ApplicationException {
		FangtuForm fangtuForm = new FangtuForm();
		CZUTDJBXX xx = new CZUTDJBXX();
		xx.setBz("1111111111");
		fangtuForm.getList().add(xx);
		
		Test test = new Test();
		List list = test.parseList(fangtuForm, CZUTDJBXX.class,
				TDChengzuVO.class);
		//System.out.println(list.size());
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			TDChengzuVO vo = (TDChengzuVO) iter.next();
			//System.out.println( vo.getBz() );
		}
	}

	private static void vo2xml2() throws ApplicationException {
		FangtuForm fangtuForm = new FangtuForm();
		CZUTDJBXX xx = new CZUTDJBXX();
		xx.setBz("1111111111");
		fangtuForm.getList().add(xx);
		
		Test test = new Test();
		List list = test.parseList(fangtuForm, CZUTDJBXX.class,
				TDChengzuVO.class);
		//System.out.println(list.size());
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			TDChengzuVO vo = (TDChengzuVO) iter.next();
			//System.out.println( vo.getBz() );
		}
	}
	
	public static void main2(String[] args) throws BaseException {
		
		test2();		
	}

	/**
	 * @throws BaseException
	 */
	private static void test3() throws BaseException {
		//将xml -> vo 测试
		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmldata += "<taxdoc><xsltVersion><![CDATA[]]></xsltVersion><schemaVersion><![CDATA[]]></schemaVersion><ywlx><![CDATA[]]></ywlx><ywczlx><![CDATA[]]></ywczlx><jsjdm><![CDATA[]]></jsjdm><taxpayerName><![CDATA[]]></taxpayerName><taxpayerId><![CDATA[]]></taxpayerId><inputDate><![CDATA[]]></inputDate><cat><![CDATA[]]></cat><destCat><![CDATA[]]></destCat><fwChengzuList><opFlag><![CDATA[]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><czrmc><![CDATA[]]></czrmc><zjlxdm><![CDATA[]]></zjlxdm><czrzjhm><![CDATA[]]></czrzjhm><czfwzl><![CDATA[]]></czfwzl><nzj><![CDATA[]]></nzj><bz><![CDATA[]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><bgqczrmc><![CDATA[]]></bgqczrmc><bgqzjlxdm><![CDATA[]]></bgqzjlxdm><bgqczrzjhm><![CDATA[]]></bgqczrzjhm><bgqczfwzl><![CDATA[]]></bgqczfwzl><bgqnzj><![CDATA[]]></bgqnzj><bgqbz><![CDATA[]]></bgqbz><bghczrmc><![CDATA[]]></bghczrmc><bghzjlxdm><![CDATA[]]></bghzjlxdm><bghczrzjhm><![CDATA[]]></bghczrzjhm><bghczfwzl><![CDATA[]]></bghczfwzl><bghnzj><![CDATA[]]></bghnzj><bghbz><![CDATA[]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO></fwChengzuList></taxdoc>";
		AlterFangtuVO fangtuVO = new AlterFangtuVO();
		try {
			if (xmldata != null) {
				XMLParseHelper.parseXMLString(fangtuVO, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);

				//System.out.println(fangtuVO.toXML());
				
				boolean equals = xmldata.equals(fangtuVO.toXML());
				//System.out.println(equals ? "Bingo" : "Sign~~");
				
				List list = fangtuVO.getFwChengzuList();
				//System.out.println(list.size());
			}
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 * @throws BaseException
	 */
	private static void test2() throws BaseException {
		//将xml -> vo 测试
		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		//xmldata += "<taxdoc><xsltVersion><![CDATA[]]></xsltVersion><schemaVersion><![CDATA[]]></schemaVersion><ywlx><![CDATA[]]></ywlx><ywczlx><![CDATA[]]></ywczlx><jsjdm><![CDATA[]]></jsjdm><taxpayerName><![CDATA[]]></taxpayerName><taxpayerId><![CDATA[]]></taxpayerId><inputDate><![CDATA[]]></inputDate><cat><![CDATA[]]></cat><destCat><![CDATA[]]></destCat><fwChengzuList><opFlag><![CDATA[]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><czrmc><![CDATA[]]></czrmc><zjlxdm><![CDATA[]]></zjlxdm><czrzjhm><![CDATA[]]></czrzjhm><czfwzl><![CDATA[]]></czfwzl><nzj><![CDATA[]]></nzj><bz><![CDATA[]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><bgqczrmc><![CDATA[]]></bgqczrmc><bgqzjlxdm><![CDATA[]]></bgqzjlxdm><bgqczrzjhm><![CDATA[]]></bgqczrzjhm><bgqczfwzl><![CDATA[]]></bgqczfwzl><bgqnzj><![CDATA[]]></bgqnzj><bgqbz><![CDATA[]]></bgqbz><bghczrmc><![CDATA[]]></bghczrmc><bghzjlxdm><![CDATA[]]></bghzjlxdm><bghczrzjhm><![CDATA[]]></bghczrzjhm><bghczfwzl><![CDATA[]]></bghczfwzl><bghnzj><![CDATA[]]></bghnzj><bghbz><![CDATA[]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO></fwChengzuList></taxdoc>";
		//xmldata += "<taxdoc><fwZiyongList><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><bz><![CDATA[fsadfasd]]></bz></regVO><alterVO><bghbz><![CDATA[XXXXXX]]></bghbz></alterVO></fwZiyongList><fwZiyongList><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><bz><![CDATA[]]></bz></regVO><alterVO><bghbz><![CDATA[]]></bghbz></alterVO></fwZiyongList><fwZiyongList><opFlag><![CDATA[]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><bz><![CDATA[]]></bz></regVO><alterVO><bghbz><![CDATA[]]></bghbz></alterVO></fwZiyongList></taxdoc>";
		//xmldata += "<taxdoc><fwZiyongList><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><fwZiyongList><bz><![CDATA[fsadfasd]]></bz></fwZiyongList></regVO><alterVO><fwZiyongList><bghbz><![CDATA[XXXXXX]]></bghbz></fwZiyongList></alterVO></fwZiyongList><fwZiyongList><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><fwZiyongList><bz><![CDATA[]]></bz></fwZiyongList></regVO><alterVO><fwZiyongList><bghbz><![CDATA[]]></bghbz></fwZiyongList></alterVO></fwZiyongList><fwZiyongList><opFlag><![CDATA[]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><fwZiyongList><bz><![CDATA[]]></bz></fwZiyongList></regVO><alterVO><fwZiyongList><bghbz><![CDATA[]]></bghbz></fwZiyongList></alterVO></fwZiyongList></taxdoc>";
		xmldata += "<taxdoc><fwZiyongList><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><fwzl><![CDATA[]]></fwzl><cqzsh><![CDATA[]]></cqzsh><fcyz><![CDATA[]]></fcyz><swjggz><![CDATA[]]></swjggz><qzmsyz><![CDATA[]]></qzmsyz><qzysyz><![CDATA[]]></qzysyz><nynse><![CDATA[]]></nynse><sfdj><![CDATA[]]></sfdj><bz><![CDATA[fsadfasd]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><ysfcyzbgyy><![CDATA[]]></ysfcyzbgyy><jmsyzbgyy><![CDATA[]]></jmsyzbgyy><jmzcdm><![CDATA[]]></jmzcdm><jmsqxq><![CDATA[]]></jmsqxq><jmsqxz><![CDATA[]]></jmsqxz><bgqfwzl><![CDATA[]]></bgqfwzl><bgqcqzsh><![CDATA[]]></bgqcqzsh><bgqfcyz><![CDATA[]]></bgqfcyz><bgqswjggz><![CDATA[]]></bgqswjggz><bgqqzmsyz><![CDATA[]]></bgqqzmsyz><bgqqzysyz><![CDATA[]]></bgqqzysyz><bgqnynse><![CDATA[]]></bgqnynse><bgqsfdj><![CDATA[]]></bgqsfdj><bgqbz><![CDATA[]]></bgqbz><bghfwzl><![CDATA[]]></bghfwzl><bghcqzsh><![CDATA[]]></bghcqzsh><bghfcyz><![CDATA[]]></bghfcyz><bghswjggz><![CDATA[]]></bghswjggz><bghqzmsyz><![CDATA[]]></bghqzmsyz><bghqzysyz><![CDATA[]]></bghqzysyz><bghnynse><![CDATA[]]></bghnynse><bghsfdj><![CDATA[value_1]]></bghsfdj><bghbz><![CDATA[XXXXXX]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[on]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><fwzl><![CDATA[]]></fwzl><cqzsh><![CDATA[]]></cqzsh><fcyz><![CDATA[]]></fcyz><swjggz><![CDATA[]]></swjggz><qzmsyz><![CDATA[]]></qzmsyz><qzysyz><![CDATA[]]></qzysyz><nynse><![CDATA[]]></nynse><sfdj><![CDATA[]]></sfdj><bz><![CDATA[fsadfasd]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><ysfcyzbgyy><![CDATA[]]></ysfcyzbgyy><jmsyzbgyy><![CDATA[1]]></jmsyzbgyy><jmzcdm><![CDATA[]]></jmzcdm><jmsqxq><![CDATA[]]></jmsqxq><jmsqxz><![CDATA[]]></jmsqxz><bgqfwzl><![CDATA[]]></bgqfwzl><bgqcqzsh><![CDATA[]]></bgqcqzsh><bgqfcyz><![CDATA[]]></bgqfcyz><bgqswjggz><![CDATA[]]></bgqswjggz><bgqqzmsyz><![CDATA[]]></bgqqzmsyz><bgqqzysyz><![CDATA[]]></bgqqzysyz><bgqnynse><![CDATA[]]></bgqnynse><bgqsfdj><![CDATA[]]></bgqsfdj><bgqbz><![CDATA[]]></bgqbz><bghfwzl><![CDATA[asdf]]></bghfwzl><bghcqzsh><![CDATA[sdfasfd]]></bghcqzsh><bghfcyz><![CDATA[asdfsadf]]></bghfcyz><bghswjggz><![CDATA[asdfdsaf]]></bghswjggz><bghqzmsyz><![CDATA[]]></bghqzmsyz><bghqzysyz><![CDATA[]]></bghqzysyz><bghnynse><![CDATA[]]></bghnynse><bghsfdj><![CDATA[value_1]]></bghsfdj><bghbz><![CDATA[XXXXXX]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO></fwZiyongList><fwZiyongList><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><fwzl><![CDATA[]]></fwzl><cqzsh><![CDATA[]]></cqzsh><fcyz><![CDATA[]]></fcyz><swjggz><![CDATA[]]></swjggz><qzmsyz><![CDATA[]]></qzmsyz><qzysyz><![CDATA[]]></qzysyz><nynse><![CDATA[]]></nynse><sfdj><![CDATA[]]></sfdj><bz><![CDATA[]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><ysfcyzbgyy><![CDATA[]]></ysfcyzbgyy><jmsyzbgyy><![CDATA[]]></jmsyzbgyy><jmzcdm><![CDATA[]]></jmzcdm><jmsqxq><![CDATA[]]></jmsqxq><jmsqxz><![CDATA[]]></jmsqxz><bgqfwzl><![CDATA[]]></bgqfwzl><bgqcqzsh><![CDATA[]]></bgqcqzsh><bgqfcyz><![CDATA[]]></bgqfcyz><bgqswjggz><![CDATA[]]></bgqswjggz><bgqqzmsyz><![CDATA[]]></bgqqzmsyz><bgqqzysyz><![CDATA[]]></bgqqzysyz><bgqnynse><![CDATA[]]></bgqnynse><bgqsfdj><![CDATA[]]></bgqsfdj><bgqbz><![CDATA[]]></bgqbz><bghfwzl><![CDATA[]]></bghfwzl><bghcqzsh><![CDATA[]]></bghcqzsh><bghfcyz><![CDATA[]]></bghfcyz><bghswjggz><![CDATA[]]></bghswjggz><bghqzmsyz><![CDATA[]]></bghqzmsyz><bghqzysyz><![CDATA[]]></bghqzysyz><bghnynse><![CDATA[]]></bghnynse><bghsfdj><![CDATA[value_1]]></bghsfdj><bghbz><![CDATA[]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO><opFlag><![CDATA[old]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><fwzl><![CDATA[]]></fwzl><cqzsh><![CDATA[]]></cqzsh><fcyz><![CDATA[]]></fcyz><swjggz><![CDATA[]]></swjggz><qzmsyz><![CDATA[]]></qzmsyz><qzysyz><![CDATA[]]></qzysyz><nynse><![CDATA[]]></nynse><sfdj><![CDATA[]]></sfdj><bz><![CDATA[]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><ysfcyzbgyy><![CDATA[]]></ysfcyzbgyy><jmsyzbgyy><![CDATA[]]></jmsyzbgyy><jmzcdm><![CDATA[]]></jmzcdm><jmsqxq><![CDATA[]]></jmsqxq><jmsqxz><![CDATA[]]></jmsqxz><bgqfwzl><![CDATA[]]></bgqfwzl><bgqcqzsh><![CDATA[]]></bgqcqzsh><bgqfcyz><![CDATA[]]></bgqfcyz><bgqswjggz><![CDATA[]]></bgqswjggz><bgqqzmsyz><![CDATA[]]></bgqqzmsyz><bgqqzysyz><![CDATA[]]></bgqqzysyz><bgqnynse><![CDATA[]]></bgqnynse><bgqsfdj><![CDATA[]]></bgqsfdj><bgqbz><![CDATA[]]></bgqbz><bghfwzl><![CDATA[]]></bghfwzl><bghcqzsh><![CDATA[]]></bghcqzsh><bghfcyz><![CDATA[]]></bghfcyz><bghswjggz><![CDATA[]]></bghswjggz><bghqzmsyz><![CDATA[]]></bghqzmsyz><bghqzysyz><![CDATA[]]></bghqzysyz><bghnynse><![CDATA[]]></bghnynse><bghsfdj><![CDATA[value_1]]></bghsfdj><bghbz><![CDATA[]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO></fwZiyongList><fwZiyongList><opFlag><![CDATA[]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><fwzl><![CDATA[]]></fwzl><cqzsh><![CDATA[]]></cqzsh><fcyz><![CDATA[]]></fcyz><swjggz><![CDATA[]]></swjggz><qzmsyz><![CDATA[]]></qzmsyz><qzysyz><![CDATA[]]></qzysyz><nynse><![CDATA[]]></nynse><sfdj><![CDATA[]]></sfdj><bz><![CDATA[]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><ysfcyzbgyy><![CDATA[]]></ysfcyzbgyy><jmsyzbgyy><![CDATA[]]></jmsyzbgyy><jmzcdm><![CDATA[]]></jmzcdm><jmsqxq><![CDATA[]]></jmsqxq><jmsqxz><![CDATA[]]></jmsqxz><bgqfwzl><![CDATA[]]></bgqfwzl><bgqcqzsh><![CDATA[]]></bgqcqzsh><bgqfcyz><![CDATA[]]></bgqfcyz><bgqswjggz><![CDATA[]]></bgqswjggz><bgqqzmsyz><![CDATA[]]></bgqqzmsyz><bgqqzysyz><![CDATA[]]></bgqqzysyz><bgqnynse><![CDATA[]]></bgqnynse><bgqsfdj><![CDATA[]]></bgqsfdj><bgqbz><![CDATA[]]></bgqbz><bghfwzl><![CDATA[]]></bghfwzl><bghcqzsh><![CDATA[]]></bghcqzsh><bghfcyz><![CDATA[]]></bghfcyz><bghswjggz><![CDATA[]]></bghswjggz><bghqzmsyz><![CDATA[]]></bghqzmsyz><bghqzysyz><![CDATA[]]></bghqzysyz><bghnynse><![CDATA[]]></bghnynse><bghsfdj><![CDATA[]]></bghsfdj><bghbz><![CDATA[]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO><opFlag><![CDATA[]]></opFlag><deleteFlag><![CDATA[]]></deleteFlag><updateFlag><![CDATA[]]></updateFlag><regVO><djbh><![CDATA[]]></djbh><id><![CDATA[]]></id><fwzl><![CDATA[]]></fwzl><cqzsh><![CDATA[]]></cqzsh><fcyz><![CDATA[]]></fcyz><swjggz><![CDATA[]]></swjggz><qzmsyz><![CDATA[]]></qzmsyz><qzysyz><![CDATA[]]></qzysyz><nynse><![CDATA[]]></nynse><sfdj><![CDATA[]]></sfdj><bz><![CDATA[]]></bz><opFlag><![CDATA[]]></opFlag></regVO><alterVO><jsjdm><![CDATA[]]></jsjdm><djbh><![CDATA[]]></djbh><jcbh><![CDATA[]]></jcbh><id><![CDATA[]]></id><bglx><![CDATA[]]></bglx><ysfcyzbgyy><![CDATA[]]></ysfcyzbgyy><jmsyzbgyy><![CDATA[]]></jmsyzbgyy><jmzcdm><![CDATA[]]></jmzcdm><jmsqxq><![CDATA[]]></jmsqxq><jmsqxz><![CDATA[]]></jmsqxz><bgqfwzl><![CDATA[]]></bgqfwzl><bgqcqzsh><![CDATA[]]></bgqcqzsh><bgqfcyz><![CDATA[]]></bgqfcyz><bgqswjggz><![CDATA[]]></bgqswjggz><bgqqzmsyz><![CDATA[]]></bgqqzmsyz><bgqqzysyz><![CDATA[]]></bgqqzysyz><bgqnynse><![CDATA[]]></bgqnynse><bgqsfdj><![CDATA[]]></bgqsfdj><bgqbz><![CDATA[]]></bgqbz><bghfwzl><![CDATA[]]></bghfwzl><bghcqzsh><![CDATA[]]></bghcqzsh><bghfcyz><![CDATA[]]></bghfcyz><bghswjggz><![CDATA[]]></bghswjggz><bghqzmsyz><![CDATA[]]></bghqzmsyz><bghqzysyz><![CDATA[]]></bghqzysyz><bghnynse><![CDATA[]]></bghnynse><bghsfdj><![CDATA[]]></bghsfdj><bghbz><![CDATA[]]></bghbz><fhbs><![CDATA[]]></fhbs><fhr><![CDATA[]]></fhr><fhrq><![CDATA[]]></fhrq><tbrq><![CDATA[]]></tbrq><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><qxdm><![CDATA[]]></qxdm><lrr><![CDATA[]]></lrr><lrrq><![CDATA[]]></lrrq><cjr><![CDATA[]]></cjr><cjrq><![CDATA[]]></cjrq></alterVO></fwZiyongList><fwZiyongList/><fwZiyongList/><fwZiyongList/></taxdoc>";
		AlterFangtuVO alterFangtuVO = new AlterFangtuVO();
		try {
			if (xmldata != null) {
				XMLParseHelper.parseXMLString(alterFangtuVO, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);

				//System.out.println(alterFangtuVO.toXML());
				
				boolean equals = xmldata.equals(alterFangtuVO.toXML());
				//System.out.println(equals ? "Bingo" : "Sign~~");
				
				List list = alterFangtuVO.getFwZiyongList();
				//System.out.println(list.size());
			}
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/**
	 * @throws BaseException
	 */
	private static void test1() throws BaseException {
		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmldata += "<taxdoc><xsltVersion><![CDATA[]]></xsltVersion><schemaVersion><![CDATA[]]></schemaVersion><ywlx><![CDATA[]]></ywlx><ywczlx><![CDATA[]]></ywczlx><fwZiyongList><djbh><![CDATA[11564124341400]]></djbh><id><![CDATA[11564124373120]]></id><fwzl><![CDATA[1]]></fwzl><cqzsh><![CDATA[1]]></cqzsh><fcyz><![CDATA[0.0]]></fcyz><swjggz><![CDATA[1.0]]></swjggz><qzmsyz><![CDATA[0.0]]></qzmsyz><qzysyz><![CDATA[1.0]]></qzysyz><nynse><![CDATA[1.0]]></nynse><sfdj><![CDATA[1]]></sfdj><bz><![CDATA[1]]></bz></fwZiyongList><fwZiyongList><djbh><![CDATA[11564124341400]]></djbh><id><![CDATA[11564124373120]]></id><fwzl><![CDATA[1]]></fwzl><cqzsh><![CDATA[1]]></cqzsh><fcyz><![CDATA[0.0]]></fcyz><swjggz><![CDATA[1.0]]></swjggz><qzmsyz><![CDATA[0.0]]></qzmsyz><qzysyz><![CDATA[1.0]]></qzysyz><nynse><![CDATA[1.0]]></nynse><sfdj><![CDATA[1]]></sfdj><bz><![CDATA[1]]></bz></fwZiyongList></taxdoc>";
		FangtuVO fangtuVO = new FangtuVO();
		try {
			if (xmldata != null) {
				XMLParseHelper.parseXMLString(fangtuVO, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);

				//System.out.println(fangtuVO.toXML());
				
				boolean equals = xmldata.equals(fangtuVO.toXML());
				//System.out.println(equals ? "Bingo" : "Sign~~");
				
				List list = fangtuVO.getFwZiyongList();
				//System.out.println(list.size());
			}
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	


	private List parseList(FangtuForm fangtuForm, Class clazz, Class voClass)
			throws ApplicationException {
		List vos = new ArrayList();
		if (fangtuForm == null ) return vos;
		List list = fangtuForm.getList();
		if (list != null) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object vo = (iter.next());

				XMLVOInterface xmlvo;

				try {
					xmlvo = (XMLVOInterface) voClass.newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
					throw new ApplicationException("实例化房土时出错", e1);
				}
				try {
					BeanUtils.copyProperties(xmlvo, vo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ApplicationException("房土属性复制时出错", e);
				}
				vos.add(xmlvo);
			}
		}
		return vos;
	}
	
}
