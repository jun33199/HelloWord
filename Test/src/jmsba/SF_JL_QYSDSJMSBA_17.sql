-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_17
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZRSRJE     NUMBER(17,2),
  SJJE1      NUMBER(17,2),
  SJJE2      NUMBER(17,2),
  SFYZRZMCL  CHAR(1),
  SFYSJZMCL  CHAR(1),
  SFYSRZMCL  CHAR(1),
  SFYHSQKSM  CHAR(1),
  HLND       CHAR(4),
  QTZL       VARCHAR2(2000),
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  JZQSND     CHAR(4),
  JZZZND     CHAR(4),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_17
  is '17��෢չ������Ŀ�����ñ��������������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_17.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.ZRSRJE
  is '�������������ת��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SJJE1
  is '�Ͻɸ����ҵ�HFC��PFC��CDM��Ŀ�Ľ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SJJE2
  is '�Ͻɸ����ҵ�N2O��CDM��Ŀ�Ľ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYZRZMCL
  is '��ת��֤�����ϣ�0���У�0����';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYSJZMCL
  is '���Ͻ�֤�����ϣ�0���У�0����';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYSRZMCL
  is '������֤�����ϣ�0���У�0����';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.SFYHSQKSM
  is '�к������������0���У�0����';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.HLND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.JZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.JZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_17.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_17
  add constraint PK_SF_JL_QYSDSJMSBA_17 primary key (XH, BASQWSH)
  using index 
  --tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_17
  add constraint FK_JMSBA_17_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_17 to SFDB;--R_SWZG
