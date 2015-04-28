-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_16
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  GJRJPZMC   VARCHAR2(30),
  SFFHQRTJ   CHAR(1),
  SFYSDNXLY  CHAR(1),
  SFYSDNXZM  CHAR(1),
  SFYXGQKSM  CHAR(1),
  SDTXNX     NUMBER(4),
  JTZJQSND   CHAR(4),
  JTZJZZND   CHAR(4),
  KKCZJE     NUMBER(17,2),
  ZCYZ       NUMBER(17,2),
  YJTZJNX    NUMBER(4),
  YJTZJE     NUMBER(17,2),
  SHBJ       CHAR(1),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_16
  is '16�⹺���������۾ɻ�̯������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_16.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.GJRJPZMC
  is '��ҵ����������ƾ֤����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SFFHQRTJ
  is '����ȷ��������0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SFYSDNXLY
  is '�������������ɣ�0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SFYSDNXZM
  is '����������֤����0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SFYXGQKSM
  is '����������˵����0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SDTXNX
  is '�⹺���������۾ɻ�̯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.JTZJQSND
  is '�����۾���ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.JTZJZZND
  is '�����۾���ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.KKCZJE
  is 'ÿ��ɿ۳����۾ɶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.ZCYZ
  is '�̶��ʲ��������ʲ���ԭֵ';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.YJTZJNX
  is '�Ѽ����۾ɵ�����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.YJTZJE
  is '�Ѽ�����۾ɶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.SHBJ
  is '��˱�ǣ�0��ͨ����1����ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_16.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_16
  add constraint PK_SF_JL_QYSDSJMSBA_16 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_16
  add constraint FK_JMSBA_16_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_16 to SFDB;--R_SWZG