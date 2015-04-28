-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_2014_05
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(4) not null,
  NLMYJMXMDM CHAR(2),
  JMSDE      NUMBER(17,2),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_2014_05
  is '05从事农、林、牧、渔业项目的所得';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.NLMYJMXMDM
  is '农林牧渔减免项目代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.JMSDE
  is '减免所得额';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_2014_05
  add constraint PK_SF_JL_QYSDSJMSBA_2014_05 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_2014_05
  add constraint FK_JMSBA_05_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_2014_05 to SFDB;--R_SWZG
