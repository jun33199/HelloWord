-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_10
(
  XH          VARCHAR2(15) not null,
  BASQWSH     VARCHAR2(12) not null,
  JSJDM       VARCHAR2(8),
  BAND        VARCHAR2(4),
  SWJGZZJGDM  VARCHAR2(8) not null,
  SFGHBJNRJQY CHAR(1),
  QTZL        VARCHAR2(2000),
  YJJSE       NUMBER(17,2),
  CJR         VARCHAR2(30),
  CJRQ        DATE,
  LRR         VARCHAR2(30),
  LRRQ        DATE
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
comment on table SFDB.SF_JL_QYSDSJMSBA_10
  is '10国家规划布局内的重点软件生产企业';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_10.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.SFGHBJNRJQY
  is '规划布局内重点软件企业，0：否，1：是';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.YJJSE
  is '预计减税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.LRRQ
  is '录入日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_10
  add constraint PK_SF_JL_QYSDSJMSBA_10 primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_10
  add constraint FK_JMSBA_10_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_10 to SFDB;--R_SWZG
