-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_13A
(
  XH           VARCHAR2(15) not null,
  BASQWSH      VARCHAR2(12) not null,
  JSJDM        VARCHAR2(8),
  BAND         VARCHAR2(4),
  SWJGZZJGDM   VARCHAR2(8) not null,
  GXJSLYDM     CHAR(2) not null,
  CTQYZSJBH    VARCHAR2(100),
  BTZQYMCJZSBH VARCHAR2(100),
  SFYNJHGTZS   CHAR(1),
  SFTJTZYZQK   CHAR(1),
  SFTJYZZM     CHAR(1),
  SFTJWSSSM    CHAR(1),
  SFRSYYEZCBCX CHAR(1),
  QTZL         VARCHAR2(2000),
  SHBJ         CHAR(1),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_13A
  is '13A��ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ�';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.GXJSLYDM
  is '���¼����������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.CTQYZSJBH
  is '��Ͷ��ҵ֤�鼰���';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.BTZQYMCJZSBH
  is '��Ͷ����ҵ���Ƽ�֤����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFYNJHGTZS
  is '�ύ���ϸ�֪ͨ�飬0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFTJTZYZQK
  is '�ύͶ�����������0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFTJYZZM
  is '�ύ����֤����0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFTJWSSSM
  is '�ύδ����������0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SFRSYYEZCBCX
  is '����Ӫҵ���ʲ������ޣ�0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13A.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_13A
  add constraint PK_SF_JL_QYSDSJMSBA_13A primary key (XH, BASQWSH)
  using index 
--  tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_13A
  add constraint FK_JMSBA_13A_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_13A to SFDB;--R_SWZG
