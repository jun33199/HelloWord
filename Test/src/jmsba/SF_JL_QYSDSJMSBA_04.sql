-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_2014_04
(
  XH           VARCHAR2(15) not null,
  BASQWSH      VARCHAR2(12) not null,
  JSJDM        VARCHAR2(8),
  BAND         VARCHAR2(4),
  SWJGZZJGDM   VARCHAR2(8) not null,
  GGJCSSXMLXDM CHAR(2) not null,
  WJMC         VARCHAR2(100),
  WH           VARCHAR2(100),
  DYBZLMC      VARCHAR2(100),
  DYBRQ        VARCHAR2(6),
  SFYHSSM      CHAR(1),
  QTZL         VARCHAR2(2000),
  MZQSND       CHAR(4),
  MZZZND       CHAR(4),
  JZQSND       CHAR(4),
  JZZZND       CHAR(4),
  SHBJ         CHAR(1),
  ZCBA         CHAR(1) default '1',
  CJR          VARCHAR2(30),
  CJRQ         DATE,
  LRR          VARCHAR2(30),
  LRRQ         DATE
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
comment on table SFDB.SF_JL_QYSDSJMSBA_2014_04
  is '04���¹����ص���ֵĹ���������ʩ��ĿͶ�ʾ�Ӫ������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.GGJCSSXMLXDM
  is '����������ʩ��Ŀ���ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.WJMC
  is '�ļ�����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.WH
  is '�ĺ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.DYBZLMC
  is 'ȡ�õ�һ�������֤����������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.DYBRQ
  is 'ȡ�õ�һ������ʱ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.SFYHSSM
  is '������Ŀ���ú����������,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.JZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.JZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.ZCBA
  is '�ٴα��� 0:�ٴα�����1��δ�ٴα���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_04.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_2014_04
  add constraint PK_SF_JL_QYSDSJMSBA_2014_04 primary key (XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_2014_04
  add constraint UK_SF_JL_QYSDSJMSBA_2014_04 unique (BASQWSH, GGJCSSXMLXDM, WJMC, WH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_2014_04
  add constraint FK_JMSBA_04_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_2014_04 to SFDB;--R_SWZG
