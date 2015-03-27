-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_12
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  SFSYJCDLQY CHAR(1),
  HLND       CHAR(4),
  QTZL       VARCHAR2(2000),
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  JZQSND     CHAR(4),
  JZZZND     CHAR(4),
  YJJMSE     NUMBER(17,2),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_12
  is '12投资额超过80亿元人民币或集成电路线宽小于0.25um的集成电路生产企业';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_12.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.SFSYJCDLQY
  is '属于集成电路企业，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.HLND
  is '获利年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.MZQSND
  is '免征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.MZZZND
  is '免征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.JZQSND
  is '减征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.JZZZND
  is '减征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.YJJMSE
  is '预计减免税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_12
  add constraint PK_SF_JL_QYSDSJMSBA_12 primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_12
  add constraint FK_JMSBA_12_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_12 to SFDB;--R_SWZG
