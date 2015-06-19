-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_08
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  GXJSLYDM   CHAR(2) not null,
  ZSBH       VARCHAR2(100),
  ZSYXQSRQ   DATE,
  ZSYXZZRQ   DATE,
  SFYSYGDFW  CHAR(1),
  SFYZJJZBG  CHAR(1),
  ZKYSBL     NUMBER(5,2),
  YFRYBL     NUMBER(5,2),
  F_FYJGMXB  CHAR(1),
  YFFYBL     NUMBER(5,2),
  GXCPSRBL   NUMBER(5,2),
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
    initial 192K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBA_08
  is '08������Ҫ�ص���ֵĸ��¼�����ҵ';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_08.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.GXJSLYDM
  is '���¼����������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZSBH
  is '֤����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZSYXQSRQ
  is '֤����Ч��ʼ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZSYXZZRQ
  is '֤����Ч��ֹ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.SFYSYGDFW
  is '�Ƿ����ڹ涨��Χ��0����1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.SFYZJJZBG
  is '�н������֤���棬0����1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.ZKYSBL
  is 'ר�����ϱ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.YFRYBL
  is '�з���Ա����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.F_FYJGMXB
  is '���ýṹ��ϸ��0����1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.YFFYBL
  is '�з����ñ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.GXCPSRBL
  is '���²�Ʒ�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_08.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_08
  add constraint PK_SF_JL_QYSDSJMSBA_08 primary key (BASQWSH, XH)
  using index 
  --tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_08
  add constraint FK_JMSBA_08_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_08 to SFDB;--R_SWZG
