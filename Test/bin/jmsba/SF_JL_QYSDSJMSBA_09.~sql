-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_09
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZSLXDM     CHAR(1),
  ZSBH       VARCHAR2(100),
  ZSYXQSRQ   DATE,
  ZSYXZZRQ   DATE,
  HLND       CHAR(4),
  BNSDQKSM   VARCHAR2(100),
  QTZL       VARCHAR2(2000),
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  JZQSND     CHAR(4),
  JZZZND     CHAR(4),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_09
  is '09新办软件生产企业、集成电路设计企业';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_09.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.ZSLXDM
  is '证书类型代码，0：北京市软件企业证书，1：北京市集成电路设计企业证书';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.ZSBH
  is '证书编号';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.ZSYXQSRQ
  is '证书有效起始日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.ZSYXZZRQ
  is '证书有效终止日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.HLND
  is '获利年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.BNSDQKSM
  is '本年所得情况说明';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.MZQSND
  is '免征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.MZZZND
  is '免征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.JZQSND
  is '减征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.JZZZND
  is '减征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_09.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_09
  add constraint PK_SF_JL_QYSDSJMSBA_09 primary key (XH, BASQWSH)
  using index 
  --tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_09
  add constraint FK_JMSBA_09_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_09 to SFDB;--R_SWZG
