-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_12
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  SFSYJCDLQY CHAR(1),
  HLND       CHAR(4),
  QTZL       VARCHAR2(2000),
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  JZQSND     CHAR(4),
  JZZZND     CHAR(4),
  YJJMSE     NUMBER(17,2),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_12
  is '12Ͷ�ʶ��80��Ԫ����һ򼯳ɵ�·�߿�С��0.25um�ļ��ɵ�·������ҵ';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_12.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.SFSYJCDLQY
  is '���ڼ��ɵ�·��ҵ��0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.HLND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.JZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.JZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.YJJMSE
  is 'Ԥ�Ƽ���˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_12.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_12
  add constraint PK_SF_JL_QYSDSJMSBA_12 primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_12
  add constraint FK_JMSBA_12_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_12 to SFDB;--R_SWZG
