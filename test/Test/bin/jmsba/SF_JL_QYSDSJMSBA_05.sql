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
  is '05����ũ���֡�������ҵ��Ŀ������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.NLMYJMXMDM
  is 'ũ�����������Ŀ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.JMSDE
  is '�������ö�';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_05.LRRQ
  is '¼������';
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
