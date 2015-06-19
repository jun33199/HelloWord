-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_18
(
  XH         NUMBER(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  FWYWFWDM   CHAR(2) not null,
  ZSBH       VARCHAR2(30),
  ZSQSRQ     DATE,
  ZSZZRQ     DATE,
  SFYNSHGZM  CHAR(1),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_18
  is '18���϶��ļ����Ƚ��ͷ�����ҵ���������������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_18.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.FWYWFWDM
  is '�����Ƚ��ͷ���ҵ��Χ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.ZSBH
  is '֤����';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.ZSQSRQ
  is '֤����ʼ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.ZSZZRQ
  is '֤����ֹ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.SFYNSHGZM
  is '������ϸ�֤����0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.JMSE
  is '����˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_18.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_18
  add constraint PK_SF_JL_QYSDSJMSBA_18 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_18
  add constraint FK_JMSBA_18_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_18 to SFDB;--R_SWZG
