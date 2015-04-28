/**
 * @Title:       NdsbbVO.java
 * @Description: TODO
 * @date:        2014-11-12下午03:12:17
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.xml.XMLVOInterface;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Gjdq;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM.Sfzjlx;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-12
 */
public class NdsbbVO extends YWRootVO implements XMLVOInterface {

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Description：国籍
	 */
	private List gjList = new ArrayList();

	/**
	 * Description：身份證件類型代碼表
	 */
	private List sfzjlxList = new ArrayList();
	
	
	
	/**
	 * Description：申报信息——企业
	 */
	private SbinfQyVO inf_qy = new SbinfQyVO();
	
	/**
	 * Description：申报信息——个人
	 */
	private SbinfGrVO inf_gr = new SbinfGrVO();
	
	public static void main(String[] args) {
		String xmldateString="<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[grsdsndsbb2014]]></xsltVersion><schemaVersion><![CDATA[]]></schemaVersion><ywlx><![CDATA[]]></ywlx><ywczlx><![CDATA[]]></ywczlx><inf_qy><btzzxx_jsjdm>01002300</btzzxx_jsjdm><btzzxx_name>2222</btzzxx_name><btzzxx_nsrsbh>110101400616680</btzzxx_nsrsbh><btzzxx_djzclx>333</btzzxx_djzclx><btzzxx_npjzgrs>1</btzzxx_npjzgrs><btzzxx_gzze>1</btzzxx_gzze><btzzxx_tzzrs>1</btzzxx_tzzrs><col_1>0.00</col_1><col_2>0.00</col_2><col_3>0.00</col_3><col_4>0.00</col_4><col_5>0.00</col_5><col_6>0.00</col_6><col_7>0.00</col_7><col_8>0.00</col_8><col_9>0.00</col_9><col_10>0.00</col_10><col_11>0.00</col_11><col_12>0.00</col_12><col_13>0.00</col_13><col_14>0.00</col_14><col_15>0.00</col_15><col_16>0.00</col_16><col_17>0.00</col_17><col_18>0.00</col_18><col_19>0.00</col_19><col_20>0.00</col_20><col_21>0.00</col_21><col_22>0.00</col_22><col_23>0.00</col_23><col_24>0.00</col_24><col_25>0.00</col_25><col_26>0.00</col_26><col_27>0.00</col_27><col_28>0.00</col_28><col_29>0.00</col_29><col_30>0.00</col_30><col_31>0.00</col_31><col_32>0.00</col_32><col_33>0.00</col_33><col_34>0.00</col_34><col_35>0.00</col_35><col_36>0.00</col_36><col_37>0.00</col_37><col_38>0.00</col_38><col_39>0.00</col_39></inf_qy><inf_gr><skssqq>00101</skssqq><skssqz>01231</skssqz><tzzxx_gj>1</tzzxx_gj><tzzxx_name>111111</tzzxx_name><tzzxx_nsrsbh>1111111</tzzxx_nsrsbh><tzzxx_sfzjhm>1234</tzzxx_sfzjhm><tzzxx_sfzjlx>09</tzzxx_sfzjlx><col_40>11.00</col_40><col_41>0.00</col_41><col_42>0.00</col_42><col_43>0.00</col_43><col_44>0.00</col_44><col_45>0.00</col_45><col_46>0.00</col_46><col_47>0.00</col_47><col_48>0.00</col_48><col_49>0.00</col_49><col_50>0.00</col_50><col_51>0.00</col_51></inf_gr></taxdoc>";
		NdsbbVO nVo=new NdsbbVO();
		try {
			XMLParseHelper.parseXMLString(nVo, xmldateString ,XMLParseHelper.VTDXML_PARSER, false, false);
			
			System.out.println("sdafsa");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>");
		sb.append(toXMLHead());
		
		sb.append(toXMLChilds());
		sb.append("</taxdoc>");
		return sb.toString();
	}

	public String toXMLChilds() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<sfzjlxList>");
		Iterator sfzjlxListIterator =sfzjlxList.iterator();
		while(sfzjlxListIterator.hasNext())
		{
			sb.append(((Sfzjlx)sfzjlxListIterator.next()).toXML());
		}
		sb.append("</sfzjlxList>");
	
		sb.append("<gjList>");
		Iterator glListIterator =gjList.iterator();
		while(glListIterator.hasNext())
		{
			sb.append(((Gjdq)glListIterator.next()).toXML());
		}
		sb.append("</gjList>");
		
		sb.append(inf_qy.toXML());
		sb.append(inf_gr.toXML());
		
		return (sb.toString());
	}

	
	/**
	 * @description: getter-- sfzjlxList
	 * @return the sfzjlxList
	 */
	public final List getSfzjlxList() {
		return sfzjlxList;
	}

	/**
	 * @description: getter-- gjList
	 * @return the gjList
	 */
	public final List getGjList() {
		return gjList;
	}

	/**
	 * @description: setter-- gjList
	 * @param gjList the gjList to set
	 */
	public final void setGjList(List gjList) {
		this.gjList = gjList;
	}

	/**
	 * @description: setter-- sfzjlxList
	 * @param sfzjlxList the sfzjlxList to set
	 */
	public final void setSfzjlxList(List sfzjlxList) {
		this.sfzjlxList = sfzjlxList;
	}

	/**
	 * @description: getter-- inf_qy
	 * @return the inf_qy
	 */
	public final SbinfQyVO getInf_qy() {
		return inf_qy;
	}

	/**
	 * @description: setter-- inf_qy
	 * @param inf_qy the inf_qy to set
	 */
	public final void setInf_qy(SbinfQyVO inf_qy) {
		this.inf_qy = inf_qy;
	}

	/**
	 * @description: getter-- inf_gr
	 * @return the inf_gr
	 */
	public final SbinfGrVO getInf_gr() {
		return inf_gr;
	}

	/**
	 * @description: setter-- inf_gr
	 * @param inf_gr the inf_gr to set
	 */
	public final void setInf_gr(SbinfGrVO inf_gr) {
		this.inf_gr = inf_gr;
	}

	
	 
	
}
