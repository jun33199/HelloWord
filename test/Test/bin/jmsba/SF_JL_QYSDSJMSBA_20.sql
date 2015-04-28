-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_20
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  DMQYLXDM   CHAR(2) not null,
  ZSBH       VARCHAR2(30),
  ZSQSRQ     DATE,
  ZSZZRQ     DATE,
  SFYNSHGZM  CHAR(1),
  SFYXSYH    CHAR(1),
  HLND       CHAR(4),
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  JZQSND     CHAR(4),
  JZZZND     CHAR(4),
  JMSE       NUMBER(17,2),
  QTZL       VARCHAR2(2000),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_20
  is '20经认定的动漫企业备案事项具体描述';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_20.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.DMQYLXDM
  is '动漫企业类型代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.ZSBH
  is '证书编号';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.ZSQSRQ
  is '证书起始日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.ZSZZRQ
  is '证书终止日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.SFYNSHGZM
  is '有年审合格证明,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.SFYXSYH
  is '已享受优惠,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.HLND
  is '获利年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.MZQSND
  is '免征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.MZZZND
  is '免征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JZQSND
  is '减征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JZZZND
  is '减征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JMSE
  is '减免税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_20
  add constraint PK_SF_JL_QYSDSJMSBA_20 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_20
  add constraint FK_JMSBA_20_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_20 to SFDB;--R_SWZG
