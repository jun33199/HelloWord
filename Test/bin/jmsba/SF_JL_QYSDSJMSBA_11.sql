-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_11
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  SFSYJCDLQY CHAR(1),
  HLND       CHAR(4),
  QTZL       VARCHAR2(2000),
  JZQSND     CHAR(4),
  MZZZND     CHAR(4),
  MZQSND     CHAR(4),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_11
  is '11�����߿�С��0.8΢�ף��������ɵ�·��Ʒ��������ҵ��������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_11.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.SFSYJCDLQY
  is '���ڼ��ɵ�·��ҵ��0����1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.HLND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.JZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.JZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.YJJMSE
  is 'Ԥ�Ƽ���˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_11.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_11
  add constraint PK_SF_JL_QYSDSJMSBA_11 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_11
  add constraint FK_JMSBA_11_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_11 to SFDB;--R_SWZG
