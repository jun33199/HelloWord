-- Create table
create table SFDB.SF_JL_QYSDSJMSBAJLZLQD
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  ZLQD       VARCHAR2(1000) not null,
  SWJGZZJGDM VARCHAR2(8) not null,
  CJR        VARCHAR2(30),
  CJRQ       DATE,
  LRR        VARCHAR2(30),
  LRRQ       DATE,
  SFSHTG     CHAR(1) default '0',
  SFKYSC     CHAR(1) default '1'
)
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 4M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBAJLZLQD
  is '����˰������¼�����嵥';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.ZLQD
  is '����˰���������嵥';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.LRRQ
  is '¼������';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.SFSHTG
  is '����Ƿ�ͨ�������ʱ���޴����ϣ�0δ��ˣ�1���ʱ�У�2���ʱ��';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.SFKYSC
  is '�Ƿ����ɾ����0���ǣ�1����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBAJLZLQD
  add constraint PK_SF_JL_QYSDSJMSBAJLZLQD primary key (XH, BASQWSH)
  using index 
 -- tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBAJLZLQD
  add constraint FK_JMSBAJLZLQD_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBAJLZLQD to SFDB;--R_SWZG
