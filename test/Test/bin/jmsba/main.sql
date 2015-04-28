create user SFDB identified by zhangj123;
-- Create table
create table SFDB.SF_JL_QYSDSJMSBAJL_2014
(
  BASQWSH    VARCHAR2(12) not null,
  BASQBH     VARCHAR2(50) not null,
  JSJDM      VARCHAR2(8) not null,
  BAND       VARCHAR2(4) not null,
  BBQLX      VARCHAR2(1) not null,--新增
  QH         VARCHAR2(2) not null,--新增
  SKSSSQQ    DATE not null,--新增
  SKSSSQZ    DATE not null,--新增
  JMBASXDM   VARCHAR2(4) not null,
  SZDM       VARCHAR2(9) not null,
  SWJGZZJGDM VARCHAR2(8) not null,
  SQZT       CHAR(1),
  TJR        VARCHAR2(30),
  TJSJ       DATE,
  SHR        VARCHAR2(30),
  SHSJ       DATE,
  SQLXDM     CHAR(1) not null,
  FHWJMC     VARCHAR2(4000),
  QSRQ       DATE,
  JZRQ       DATE,
  CJR        VARCHAR2(30) not null,
  CJRQ       DATE not null,
  LRR        VARCHAR2(30) not null,
  LRRQ       TIMESTAMP(6) not null,
  BAJMSE     NUMBER(17,2),
  BAJMBL     NUMBER(6,2),
  ZFSM       VARCHAR2(1000),
  ZFR        VARCHAR2(30),
  ZFRQ       DATE,
  HTR        VARCHAR2(30),
  HTRQ       DATE
);
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBAJL_2014
  is '企业所得税减免税备案记录主表';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BASQBH
  is '备案申请编号';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BBQLX
  is '报表期类型,0-月度,1-季度,2-年度';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.QH
  is '期号 根据BBLX区分不同期的申报,例如,BBQLX=2则期号开始为1';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SKSSSQQ
  is '税款所属时期起';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SKSSSQZ
  is '税款所属时期止';    
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.JMBASXDM
  is '减免备案事项代码';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SZDM
  is '税种代码';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SQZT
  is '申请状态代码，1：保存未提交，2：保存未审核，3：提交未审核，4：审核已通过，5：审核未通过';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.TJR
  is '提交人';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.TJSJ
  is '提交时间';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SHR
  is '审核人';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SHSJ
  is '审核时间';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SQLXDM
  is '申请类型代码，0：网上申请，1：上门申请';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.FHWJMC
  is '文书打印录入项：政策执行情况';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.QSRQ
  is '文书打印录入项：起始日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.JZRQ
  is '文书打印录入项：截至日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.LRRQ
  is '录入日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BAJMSE
  is '备案减免税额';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BAJMBL
  is '备案减免比例';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.ZFSM
  is '作废说明';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.ZFR
  is '作废人';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.ZFRQ
  is '作废日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.HTR
  is '回退人';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.HTRQ
  is '回退日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBAJL_2014
  add constraint PK_SF_JL_QYSDSJMSBAJL_2014 primary key (BASQWSH)
  using index 
--  tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index SFDB.SF_JL_QYSDSJMSBAJL_2014_JSJDM_BAND on SFDB.SF_JL_QYSDSJMSBAJL_2014 (JSJDM, BAND, JMBASXDM)
--  tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBAJL_2014 to SFDB;--R_SWZG
