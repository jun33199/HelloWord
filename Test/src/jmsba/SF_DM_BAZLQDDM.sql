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
  is '���������嵥�����';
-- Add comments to the columns 
comment on column DMDB.SF_DM_BAZLQDDM_2014.JMBASXDM
  is '���ⱸ���������';
comment on column DMDB.SF_DM_BAZLQDDM_2014.ZLQDDM
  is '�����嵥����';
comment on column DMDB.SF_DM_BAZLQDDM_2014.ZLQDMC
  is '�����嵥����';
comment on column DMDB.SF_DM_BAZLQDDM_2014.SFKYSC
  is '�Ƿ����ɾ��';
comment on column DMDB.SF_DM_BAZLQDDM_2014.ZFBS
  is '���ϱ�ʶ��0����Ч��1������';
comment on column DMDB.SF_DM_BAZLQDDM_2014.CJR
  is '������';
comment on column DMDB.SF_DM_BAZLQDDM_2014.CJRQ
  is '��������';
comment on column DMDB.SF_DM_BAZLQDDM_2014.LRR
  is '¼����';
comment on column DMDB.SF_DM_BAZLQDDM_2014.LRRQ
  is '¼������';
comment on column DMDB.SF_DM_BAZLQDDM_2014.SYND
  is '�������';
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
