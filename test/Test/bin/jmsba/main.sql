create user SFDB identified by zhangj123;
-- Create table
create table SFDB.SF_JL_QYSDSJMSBAJL_2014
(
  BASQWSH    VARCHAR2(12) not null,
  BASQBH     VARCHAR2(50) not null,
  JSJDM      VARCHAR2(8) not null,
  BAND       VARCHAR2(4) not null,
  BBQLX      VARCHAR2(1) not null,--����
  QH         VARCHAR2(2) not null,--����
  SKSSSQQ    DATE not null,--����
  SKSSSQZ    DATE not null,--����
  JMBASXDM   VARCHAR2(4) not null,
  SZDM       VARCHAR2(9) not null,
  SWJGZZJGDM VARCHAR2(8) not null,
  SQZT       CHAR(1),
  TJR        VARCHAR2(30),
  TJSJ       DATE,
  SHR        VARCHAR2(30),
  SHSJ       DATE,
  SQLXDM     CHAR(1) not null,
  FHWJMC     VARCHAR2(4000),
  QSRQ       DATE,
  JZRQ       DATE,
  CJR        VARCHAR2(30) not null,
  CJRQ       DATE not null,
  LRR        VARCHAR2(30) not null,
  LRRQ       TIMESTAMP(6) not null,
  BAJMSE     NUMBER(17,2),
  BAJMBL     NUMBER(6,2),
  ZFSM       VARCHAR2(1000),
  ZFR        VARCHAR2(30),
  ZFRQ       DATE,
  HTR        VARCHAR2(30),
  HTRQ       DATE
);
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBAJL_2014
  is '��ҵ����˰����˰������¼����';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BASQBH
  is '����������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BBQLX
  is '����������,0-�¶�,1-����,2-���';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.QH
  is '�ں� ����BBLX���ֲ�ͬ�ڵ��걨,����,BBQLX=2���ںſ�ʼΪ1';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SKSSSQQ
  is '˰������ʱ����';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SKSSSQZ
  is '˰������ʱ��ֹ';    
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.JMBASXDM
  is '���ⱸ���������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SZDM
  is '˰�ִ���';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SQZT
  is '����״̬���룬1������δ�ύ��2������δ��ˣ�3���ύδ��ˣ�4�������ͨ����5�����δͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.TJR
  is '�ύ��';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.TJSJ
  is '�ύʱ��';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SHR
  is '�����';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SHSJ
  is '���ʱ��';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.SQLXDM
  is '�������ʹ��룬0���������룬1����������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.FHWJMC
  is '�����ӡ¼�������ִ�����';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.QSRQ
  is '�����ӡ¼�����ʼ����';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.JZRQ
  is '�����ӡ¼�����������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.LRRQ
  is '¼������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BAJMSE
  is '��������˰��';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.BAJMBL
  is '�����������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.ZFSM
  is '����˵��';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.ZFR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.ZFRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.HTR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBAJL_2014.HTRQ
  is '��������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBAJL_2014
  add constraint PK_SF_JL_QYSDSJMSBAJL_2014 primary key (BASQWSH)
  using index 
--  tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index SFDB.SF_JL_QYSDSJMSBAJL_2014_JSJDM_BAND on SFDB.SF_JL_QYSDSJMSBAJL_2014 (JSJDM, BAND, JMBASXDM)
--  tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBAJL_2014 to SFDB;--R_SWZG
