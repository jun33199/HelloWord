-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_11
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  SFSYJCDLQY CHAR(1),
  HLND       CHAR(4),
  QTZL       VARCHAR2(2000),
  JZQSND     CHAR(4),
  MZZZND     CHAR(4),
  MZQSND     CHAR(4),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_11
  is '11生产线宽小于0.8微米（含）集成电路产品的生产企业备案事项';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_11.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.SFSYJCDLQY
  is '属于集成电路企业，0：否，1：是';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.HLND
  is '获利年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.JZQSND
  is '减征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.MZZZND
  is '免征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.MZQSND
  is '免征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.JZZZND
  is '减征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.YJJMSE
  is '预计减免税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_11
  add constraint PK_SF_JL_QYSDSJMSBA_11 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_11
  add constraint FK_JMSBA_11_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_11 to SFDB;--R_SWZG
