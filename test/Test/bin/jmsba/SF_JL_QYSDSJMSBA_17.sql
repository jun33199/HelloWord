-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_17
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZRSRJE     NUMBER(17,2),
  SJJE1      NUMBER(17,2),
  SJJE2      NUMBER(17,2),
  SFYZRZMCL  CHAR(1),
  SFYSJZMCL  CHAR(1),
  SFYSRZMCL  CHAR(1),
  SFYHSQKSM  CHAR(1),
  HLND       CHAR(4),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_17
  is '17清洁发展机制项目的所得备案事项具体描述';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_17.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.ZRSRJE
  is '温室气体减排量转让收入金额';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SJJE1
  is '上缴给国家的HFC和PFC类CDM项目的金额';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SJJE2
  is '上缴给国家的N2O类CDM项目的金额';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYZRZMCL
  is '有转让证明材料，0：有，0：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYSJZMCL
  is '有上缴证明材料，0：有，0：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYSRZMCL
  is '有收入证明材料，0：有，0：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYHSQKSM
  is '有核算情况声明，0：有，0：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.HLND
  is '获利年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.MZQSND
  is '免征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.MZZZND
  is '免征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.JZQSND
  is '减征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.JZZZND
  is '减征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_17
  add constraint PK_SF_JL_QYSDSJMSBA_17 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_17
  add constraint FK_JMSBA_17_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_17 to SFDB;--R_SWZG
