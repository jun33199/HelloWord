-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_07
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  JNJWBS     CHAR(1),
  JSZRLXDM   CHAR(2) not null,
  SFYJSZRHT  CHAR(1),
  SFYRDDJZM  CHAR(1),
  SFYSRMXB   CHAR(1),
  SFYHSQKSM  CHAR(1),
  QTZL       VARCHAR2(2000),
  JSZRSD     NUMBER(17,2),
  JSZRHTMC   VARCHAR2(400),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_07
  is '07���������ļ���ת������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_07.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JNJWBS
  is '���ھ����ʶ��0�����ڣ�1������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSZRLXDM
  is '����ת�����ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYJSZRHT
  is '����ת�ú�ͬ��0���ޣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYRDDJZM
  is '�϶��Ǽ�֤����0���ޣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYSRMXB
  is '������ϸ��0���ޣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYHSQKSM
  is '�������������0���ޣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSZRSD
  is '����ת������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSZRHTMC
  is '����ѧ�������������϶��Ǽǵļ���ת�ú�ͬ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_07
  add constraint PK_SF_JL_QYSDSJMSBA_07 primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_07
  add constraint FK_JMSBA_07_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_07 to SFDB;--R_SWZG
