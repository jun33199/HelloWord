-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_13A
(
  XH           VARCHAR2(15) not null,
  BASQWSH      VARCHAR2(12) not null,
  JSJDM        VARCHAR2(8),
  BAND         VARCHAR2(4),
  SWJGZZJGDM   VARCHAR2(8) not null,
  GXJSLYDM     CHAR(2) not null,
  CTQYZSJBH    VARCHAR2(100),
  BTZQYMCJZSBH VARCHAR2(100),
  SFYNJHGTZS   CHAR(1),
  SFTJTZYZQK   CHAR(1),
  SFTJYZZM     CHAR(1),
  SFTJWSSSM    CHAR(1),
  SFRSYYEZCBCX CHAR(1),
  QTZL         VARCHAR2(2000),
  SHBJ         CHAR(1),
  CJR          VARCHAR2(30),
  CJRQ         DATE,
  LRR          VARCHAR2(30),
  LRRQ         DATE
)
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBA_13A
  is '13A创业投资企业投资、抵扣应纳税所得额资格';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.GXJSLYDM
  is '高新技术领域代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.CTQYZSJBH
  is '创投企业证书及编号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.BTZQYMCJZSBH
  is '被投资企业名称及证书编号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFYNJHGTZS
  is '提交年检合格通知书，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFTJTZYZQK
  is '提交投资运作情况，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFTJYZZM
  is '提交验资证明，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFTJWSSSM
  is '提交未上市声明，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFRSYYEZCBCX
  is '人数营业额资产不超限，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_13A
  add constraint PK_SF_JL_QYSDSJMSBA_13A primary key (XH, BASQWSH)
  using index 
--  tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_13A
  add constraint FK_JMSBA_13A_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_13A to SFDB;--R_SWZG
