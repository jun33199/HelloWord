-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_13B
(
  XH          VARCHAR2(15) not null,
  BASQWSH     VARCHAR2(12) not null,
  JSJDM       VARCHAR2(8),
  BAND        VARCHAR2(4),
  SWJGZZJGDM  VARCHAR2(8) not null,
  GXJSLYDM    CHAR(2) not null,
  BTZQYJSJDM  VARCHAR2(8),
  BTZQYSWDJZH VARCHAR2(30),
  BTZQYMC     VARCHAR2(100),
  BTZQYSSD    CHAR(1),
  TZND        VARCHAR2(4),
  TZE         NUMBER(17,2) default 0,
  DKE         NUMBER(17,2) default 0,
  SHBJ        CHAR(1),
  YWCBABS     CHAR(1) default '1',
  CJR         VARCHAR2(30),
  CJRQ        DATE,
  LRR         VARCHAR2(30),
  LRRQ        DATE,
  DNKDKE      NUMBER(17,2) default 0,
  JZE         NUMBER(17,2) default 0
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
comment on table SFDB.SF_JL_QYSDSJMSBA_13B
  is '13B��ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö�';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.GXJSLYDM
  is '���¼����������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYJSJDM
  is '��Ͷ����ҵ���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYSWDJZH
  is '��Ͷ����ҵ˰��Ǽ�֤��';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYMC
  is '��Ͷ����ҵ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYSSD
  is '��Ͷ����ҵ�����أ�0�����У�1����ʡ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.TZND
  is 'Ͷ�����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.TZE
  is '��������Ͷ�ʶ� ';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.DKE
  is '����ʵ�ʵֿ�Ӧ��˰���ö�';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.YWCBABS
  is '����ɱ�����ʶ 0:����ɣ�1��δ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.LRRQ
  is '¼������';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.DNKDKE
  is '����ɵֿ�Ӧ��˰���ö�';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.JZE
  is '��ת�Ժ���ȵֿ�Ӧ��˰���ö�';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_13B
  add constraint PK_SF_JL_QYSDSJMSBA_13B primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_13B
  add constraint FK_JMSBA_13B_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_13B to SFDB;--R_SWZG
