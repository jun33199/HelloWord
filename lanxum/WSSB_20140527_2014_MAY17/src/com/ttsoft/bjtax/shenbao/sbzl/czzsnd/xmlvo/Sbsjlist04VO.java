package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 申报数据列表
 * @author guzx
 *
 */
public class Sbsjlist04VO implements XMLVOInterface
{
    List sbxm=new ArrayList();

    List fpblsj=new ArrayList();
    private Map m = new HashMap();

   
    public Sbsjlist04VO()
    {
        super();
        m.put("sbxm", "com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Sbxm04VO");
        m.put("fpblsj", "com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Fpblsj04VO");
    }
    public Map getListTypeMap()
    {
        return m;
    }
    public String toXML()
    {
        String xmlstr = "<sbsjlist>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsjlist>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        if (sbxm != null )
        {
            for (int i = 0; i < sbxm.size(); i++)
            {
                xmlstr += ((Sbxm04VO) sbxm.get(i)).toXML();
            }
        }
        if (fpblsj != null )
        {
            for (int i = 0; i < fpblsj.size(); i++)
            {
                xmlstr += ((Fpblsj04VO) fpblsj.get(i)).toXML();
            }
        }
        
        return xmlstr;
    }

	/**
	 * @return Returns the fpblsj.
	 */
	public List getFpblsj() {
		return fpblsj;
	}
	/**
	 * @param fpblsj The fpblsj to set.
	 */
	public void setFpblsj(List fpblsj) {
		this.fpblsj = fpblsj;
	}
	/**
	 * @return Returns the sbxm.
	 */
	public List getSbxm() {
		return sbxm;
	}
	/**
	 * @param sbxm The sbxm to set.
	 */
	public void setSbxm(List sbxm) {
		this.sbxm = sbxm;
	}
}
