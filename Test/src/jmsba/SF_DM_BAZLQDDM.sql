-- Create table
create table DMDB.SF_DM_BAZLQDDM_2014
(
  JMBASXDM VARCHAR2(4) not null,
  ZLQDDM   VARCHAR2(2) not null,
  ZLQDMC   VARCHAR2(1000) not null,
  SFKYSC   CHAR(1) not null,
  ZFBS     CHAR(1) default '0',
  CJR      VARCHAR2(30),
  CJRQ     DATE,
  LRR      VARCHAR2(30),
  LRRQ     DATE,
  SYND     VARCHAR2(4) default 2012 not null
)
--tablespace TB_DMDB_NO_PT_DEFAULT
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
comment on table DMDB.SF_DM_BAZLQDDM_2014
  is '备案资料清单代码表';
-- Add comments to the columns 
comment on column DMDB.SF_DM_BAZLQDDM_2014.JMBASXDM
  is '减免备案事项代码';
comment on column DMDB.SF_DM_BAZLQDDM_2014.ZLQDDM
  is '资料清单代码';
comment on column DMDB.SF_DM_BAZLQDDM_2014.ZLQDMC
  is '资料清单名称';
comment on column DMDB.SF_DM_BAZLQDDM_2014.SFKYSC
  is '是否可以删除';
comment on column DMDB.SF_DM_BAZLQDDM_2014.ZFBS
  is '作废标识，0：有效，1：作废';
comment on column DMDB.SF_DM_BAZLQDDM_2014.CJR
  is '创建人';
comment on column DMDB.SF_DM_BAZLQDDM_2014.CJRQ
  is '创建日期';
comment on column DMDB.SF_DM_BAZLQDDM_2014.LRR
  is '录入人';
comment on column DMDB.SF_DM_BAZLQDDM_2014.LRRQ
  is '录入日期';
comment on column DMDB.SF_DM_BAZLQDDM_2014.SYND
  is '适用年度';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DMDB.SF_DM_BAZLQDDM_2014
  add constraint PK_SF_DM_BAZLQDDM_2014 primary key (JMBASXDM, ZLQDDM, SYND)
  using index 
 -- tablespace TB_DMDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Grant/Revoke object privileges 
grant select, insert, update, delete on DMDB.SF_DM_BAZLQDDM_2014 to DMDB;--R_SWZG
