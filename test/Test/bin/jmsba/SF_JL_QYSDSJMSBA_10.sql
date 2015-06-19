-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_10
(
  XH          VARCHAR2(15) not null,
  BASQWSH     VARCHAR2(12) not null,
  JSJDM       VARCHAR2(8),
  BAND        VARCHAR2(4),
  SWJGZZJGDM  VARCHAR2(8) not null,
  SFGHBJNRJQY CHAR(1),
  QTZL        VARCHAR2(2000),
  YJJSE       NUMBER(17,2),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_10
  is '10���ҹ滮�����ڵ��ص����������ҵ';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_10.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.SFGHBJNRJQY
  is '�滮�������ص������ҵ��0����1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.YJJSE
  is 'Ԥ�Ƽ�˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_10.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_10
  add constraint PK_SF_JL_QYSDSJMSBA_10 primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_10
  add constraint FK_JMSBA_10_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_10 to SFDB;--R_SWZG
