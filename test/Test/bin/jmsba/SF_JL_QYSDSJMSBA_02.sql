-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_2014_02
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  YFFYLYDM   CHAR(2) not null,
  XMMC       VARCHAR2(200),
  SFYJHYS    CHAR(1),
  SFYBZRY    CHAR(1),
  SFYYFFYQK  CHAR(1),
  SFYJYWJ    CHAR(1),
  SFYHTXY    CHAR(1),
  SFYYJCG    CHAR(1),
  DQKCJE     NUMBER(17,2),
  WXZCJE     NUMBER(17,2),
  JJKCJE     NUMBER(17,2),
  QTZL       VARCHAR2(2000),
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
    initial 256K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBA_2014_02
  is '02�����¼������²�Ʒ���¹��շ������о��������ñ��������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.YFFYLYDM
  is '�з������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.XMMC
  is '��Ŀ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYJHYS
  is '�Ƿ��мƻ����Ԥ��,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYBZRY
  is '�Ƿ��б��������רҵ��Ա����,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYYFFYQK
  is '�Ƿ����з���������鼯��,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYJYWJ
  is '�Ƿ��о����ļ�,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYHTXY
  is '�Ƿ��к�ͬЭ��,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYYJCG
  is '�Ƿ����о��ɹ�����,0:��,1:��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.DQKCJE
  is '���ڿ۳����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.WXZCJE
  is '�γ������ʲ����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.JJKCJE
  is '�Ӽƿ۳���';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_2014_02
  add constraint PK_SF_JL_QYSDSJMSBA_2014_02 primary key (XH, BASQWSH)
  using index 
 -- tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_2014_02
  add constraint FK_JMSBA_02_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_2014_02 to SFDB;--R_SWZG
