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
  is '20���϶��Ķ�����ҵ���������������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_20.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.DMQYLXDM
  is '������ҵ���ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.ZSBH
  is '֤����';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.ZSQSRQ
  is '֤����ʼ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.ZSZZRQ
  is '֤����ֹ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.SFYNSHGZM
  is '������ϸ�֤��,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.SFYXSYH
  is '�������Ż�,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.HLND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.JMSE
  is '����˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_20.LRRQ
  is '¼������';
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
