-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_08
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  GXJSLYDM   CHAR(2) not null,
  ZSBH       VARCHAR2(100),
  ZSYXQSRQ   DATE,
  ZSYXZZRQ   DATE,
  SFYSYGDFW  CHAR(1),
  SFYZJJZBG  CHAR(1),
  ZKYSBL     NUMBER(5,2),
  YFRYBL     NUMBER(5,2),
  F_FYJGMXB  CHAR(1),
  YFFYBL     NUMBER(5,2),
  GXCPSRBL   NUMBER(5,2),
  CJR        VARCHAR2(30),
  CJRQ       DATE,
  LRR        VARCHAR2(30),
  LRRQ       DATE
)
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBA_08
  is '08国家需要重点扶持的高新技术企业';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_08.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.GXJSLYDM
  is '高新技术领域代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZSBH
  is '证书编号';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZSYXQSRQ
  is '证书有效起始日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZSYXZZRQ
  is '证书有效终止日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.SFYSYGDFW
  is '是否属于规定范围，0：否，1：是';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.SFYZJJZBG
  is '中介机构鉴证报告，0：否，1：是';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZKYSBL
  is '专科以上比例';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.YFRYBL
  is '研发人员比例';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.F_FYJGMXB
  is '费用结构明细表，0：否，1：是';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.YFFYBL
  is '研发费用比例';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.GXCPSRBL
  is '高新产品收入比例';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_08
  add constraint PK_SF_JL_QYSDSJMSBA_08 primary key (BASQWSH, XH)
  using index 
  --tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_08
  add constraint FK_JMSBA_08_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_08 to SFDB;--R_SWZG
