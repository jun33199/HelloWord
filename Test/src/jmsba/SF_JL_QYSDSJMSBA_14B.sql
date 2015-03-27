-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_14B
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZYSBLXDM   CHAR(2) not null,
  ZYSBMC     VARCHAR2(30),
  TZEZS      NUMBER(17,2) default 0,
  DMND       VARCHAR2(4),
  DMYNSE     NUMBER(17,2) default 0,
  SHBJ       CHAR(1),
  YWCBABS    CHAR(1) default '1',
  CJR        VARCHAR2(30),
  CJRQ       DATE,
  LRR        VARCHAR2(30),
  LRRQ       DATE,
  SBID       VARCHAR2(15),
  DNKDMSE    NUMBER(17,2) default 0,
  JZE        NUMBER(17,2) default 0,
  TZND       VARCHAR2(4)
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
comment on table SFDB.SF_JL_QYSDSJMSBA_14B
  is '14B��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰�������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.ZYSBLXDM
  is 'ר���豸���ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.ZYSBMC
  is 'ר���豸����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.TZEZS
  is '���깺���豸Ͷ�ʶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.DMND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.DMYNSE
  is '����ʵ�ʵ����Ӧ��˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.YWCBABS
  is '����ɱ�����ʶ 0:����ɣ�1��δ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.LRRQ
  is '¼������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.SBID
  is '�豸ID';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.DNKDMSE
  is '����ɵ����Ӧ��˰�� ';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.JZE
  is '��ת�Ժ���ȵ����Ӧ��˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.TZND
  is 'Ͷ�����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_14B
  add constraint PK_SF_JL_QYSDSJMSBA_14B primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_14B
  add constraint FK_JMSBA_14B_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_14B to SFDB;--R_SWZG
