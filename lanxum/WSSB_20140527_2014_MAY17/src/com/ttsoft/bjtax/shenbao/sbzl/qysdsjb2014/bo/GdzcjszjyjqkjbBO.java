package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo;


/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- ������˰��֧���������BO</p>
 *
 * <p>Description: ����ejb���������ݶ���</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjblistVO;

public class GdzcjszjyjqkjbBO implements Serializable
{
    /**
     * �ܻ������������
     */
    private String jsjdm = "";

    /**
     * �ܻ�����˰��ʶ���
     */
    private String nsrsbh = "";

    /**
     * �ܻ�����˰������
     */
    private String nsrmc = "";

    /**
     * �걨����
     */
    private Timestamp sbrq;

    /**
     * ҳ��չʾ���걨����
     */
    private String sbrqshow = "";

    /**
     * ˰��������ʼ����
     */
    private Timestamp skssksrq;

    /**
     * ˰��������������
     */
    private Timestamp skssjsrq;

    /**
     * ����
     */
    private String jd = "";

    /**
     * �������
     */
    private String nd = "";

    /**
     * �걨��Ϣ
     */
    private GdzcjszjyjqkjblistVO sbsj = new GdzcjszjyjqkjblistVO();


    /**
     * ����������
     */
    private String bbqlx = "";

    /**
     * ������ҵ
     */
    private String sshy = "";
    /**
     * ������ҵ����
     */
    private String sshydm = "";

	public GdzcjszjyjqkjbBO()
    {
    }

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public Timestamp getSbrq() {
		return sbrq;
	}

	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	public String getSbrqshow() {
		return sbrqshow;
	}

	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}

	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}



	public String getBbqlx() {
		return bbqlx;
	}

	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public GdzcjszjyjqkjblistVO getSbsj() {
		return sbsj;
	}

	public void setSbsj(GdzcjszjyjqkjblistVO sbsj) {
		this.sbsj = sbsj;
	}

	public String getSshydm() {
		return sshydm;
	}

	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}




}
