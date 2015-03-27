-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_2014_01
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZYZHLYZLDM CHAR(2) not null,
  WJMC       VARCHAR2(100) not null,
  WH         VARCHAR2(30) not null,
  ZSBH       VARCHAR2(30),
  ZSYXKSRQ   DATE,
  ZSYXJZRQ   DATE,
  SFTJSM     CHAR(1),
  QDSR       NUMBER(17,2),
  JJSR       NUMBER(17,2),
  SHBJ       CHAR(1),
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
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBA_2014_01
  is '01企业综合利用资源，生产符合国家产业政策规定的产品所取得的收入备案事项表';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZYZHLYZLDM
  is '资源综合利用种类代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.WJMC
  is '文件名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.WH
  is '文号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZSBH
  is '证书编号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZSYXKSRQ
  is '证书有效开始日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZSYXJZRQ
  is '证书有效截止日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.SFTJSM
  is '是否提交声明,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.QDSR
  is '取得收入(单位:元)';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.JJSR
  is '减计收入(单位:元)';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_2014_01
  add constraint PK_SF_JL_QYSDSJMSBA_2014_01 primary key (XH, BASQWSH)
  using index 
 -- tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_2014_01
  add constraint FK_JMSBA_01_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_2014_01 to SFDB;--R_SWZG
