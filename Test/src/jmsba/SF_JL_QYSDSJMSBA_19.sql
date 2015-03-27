-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_19
(
  XH          VARCHAR2(15) not null,
  BASQWSH     VARCHAR2(12) not null,
  JSJDM       VARCHAR2(8),
  BAND        VARCHAR2(4),
  SWJGZZJGDM  VARCHAR2(8) not null,
  WHSYDWLXDM  CHAR(2) not null,
  SFYQYMD     CHAR(1),
  SFYZZFAPFH  CHAR(1),
  SFBLGSYYZZ  CHAR(1),
  SFYZXSYDWZM CHAR(1),
  SFCJSHBX    CHAR(1),
  SFYBGZBJG   CHAR(1),
  JMSE        NUMBER(17,2),
  QTZL        VARCHAR2(2000),
  CJR         VARCHAR2(30),
  CJRQ        DATE,
  LRR         VARCHAR2(30),
  LRRQ        DATE
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
comment on table SFDB.SF_JL_QYSDSJMSBA_19
  is '19��Ӫ���Ļ���ҵ��λת��Ϊ��ҵ���������������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_19.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.WHSYDWLXDM
  is '�Ļ���ҵ��λ���ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYQYMD
  is '��ר����ҵ������0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYZZFAPFH
  is '��ר�Ʒ�����������0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFBLGSYYZZ
  is '������Ӫҵִ�գ�0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYZXSYDWZM
  is '��ע����ҵ��λ֤����0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFCJSHBX
  is '�μ���ᱣ�գ�0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYBGZBJG
  is '����ʱ�������0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.JMSE
  is '����˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_19
  add constraint PK_SF_JL_QYSDSJMSBA_19 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_19
  add constraint FK_JMSBA_19_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_19 to SFDB;--R_SWZG
