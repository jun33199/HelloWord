package com.lscdz.qysds.common.service.qysdsCheck.processor;

import java.sql.Connection;
import com.lscdz.qysds.common.service.qysdsCheck.IQysdsCheckServer;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import yangjian.frame.util.FrameException;

/**
 * ��ҵ����˰����������
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-18 ����02:27:34
 */
public class QysdsCheckServer implements IQysdsCheckServer{
	/**
	 * ��ҵ����˰����
	 * ʹ��֮ǰ��Ҫ��checkbean��������׼���� ���븳ֵ���jsjdm����ѡ�setCheckQysds��setCheckJdlx��setCheckZfba��Ĭ��Ϊfalse����
	 * ���isCheckJdlxΪtrue����Ҫ����setJdlxCheckStyle(ѡ��ʹ��sknd����˰������������ֹ���ж�)��
	 * �����˰������������ֹ���Ե���checkBean.createZgrqByCurrenttimeY()���ݵ�ǰ��������
	 */
	@Override
	public String check(Connection conn,CheckBean checkBean)throws FrameException{
		String jdlx="";
		//1.���������ڣ������ڽ��������걨��������������Ȼ�����걨
		if(checkBean.isCheckQsq()){
			new IsInQsqObserver().update(conn,checkBean);
		}
		//2.���ܷ�Χ����
		if(checkBean.isCheckJdlx()){
			new CheckJdlxObserver().update(conn,checkBean);
		}
		//3.�����ֱܷ���
		if(checkBean.isCheckZfba()){
			new CheckZfbaObserver().update(conn,checkBean);
		}
		jdlx=checkBean.getJdlx();
		return jdlx;
	}

}
