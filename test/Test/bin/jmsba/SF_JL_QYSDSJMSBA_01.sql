-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_2014_01
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZYZHLYZLDM CHAR(2) not null,
  WJMC       VARCHAR2(100) not null,
  WH         VARCHAR2(30) not null,
  ZSBH       VARCHAR2(30),
  ZSYXKSRQ   DATE,
  ZSYXJZRQ   DATE,
  SFTJSM     CHAR(1),
  QDSR       NUMBER(17,2),
  JJSR       NUMBER(17,2),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_2014_01
  is '01��ҵ�ۺ�������Դ���������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����뱸�������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZYZHLYZLDM
  is '��Դ�ۺ������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.WJMC
  is '�ļ�����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.WH
  is '�ĺ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZSBH
  is '֤����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZSYXKSRQ
  is '֤����Ч��ʼ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.ZSYXJZRQ
  is '֤����Ч��ֹ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.SFTJSM
  is '�Ƿ��ύ����,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.QDSR
  is 'ȡ������(��λ:Ԫ)';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.JJSR
  is '��������(��λ:Ԫ)';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_01.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_2014_01
  add constraint PK_SF_JL_QYSDSJMSBA_2014_01 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_2014_01
  add constraint FK_JMSBA_01_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_2014_01 to SFDB;--R_SWZG
