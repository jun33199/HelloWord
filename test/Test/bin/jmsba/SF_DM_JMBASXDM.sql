-- Create table
create table DMDB.SF_DM_JMBASXDM_2014
(
  JMBASXDM VARCHAR2(4) not null,
  JMBASXMC VARCHAR2(100) not null,
  JMLBDM   CHAR(1) not null,
  CJR      VARCHAR2(30),
  CJRQ     DATE,
  LRR      VARCHAR2(30),
  LRRQ     DATE,
  BALXDM   CHAR(1),
  ZFBS     CHAR(1) default '0'
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
comment on table DMDB.SF_DM_JMBASXDM_2014
  is '减免备案事项代码表';
-- Add comments to the columns 
comment on column DMDB.SF_DM_JMBASXDM_2014.JMBASXDM
  is '减免备案事项代码';
comment on column DMDB.SF_DM_JMBASXDM_2014.JMBASXMC
  is '减免备案事项名称';
comment on column DMDB.SF_DM_JMBASXDM_2014.JMLBDM
  is '减免类别代码，0：免税额 ，1：免税基';
comment on column DMDB.SF_DM_JMBASXDM_2014.CJR
  is '创建人';
comment on column DMDB.SF_DM_JMBASXDM_2014.CJRQ
  is '创建日期';
comment on column DMDB.SF_DM_JMBASXDM_2014.LRR
  is '录入人';
comment on column DMDB.SF_DM_JMBASXDM_2014.LRRQ
  is '录入日期';
comment on column DMDB.SF_DM_JMBASXDM_2014.BALXDM
  is '备案类型代码，0：单笔，1：多条';
comment on column DMDB.SF_DM_JMBASXDM_2014.ZFBS
  is '作废标识，0：有效，1：作废';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DMDB.SF_DM_JMBASXDM_2014
  add constraint PK_SF_DM_JMBASXDM_2014 primary key (JMBASXDM)
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
grant select, insert, update, delete on DMDB.SF_DM_JMBASXDM_2014 to DMDB;--R_SWZG
