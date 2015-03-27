-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_14A
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  ZYSBLXDM   CHAR(2) not null,
  ZYSBMC     VARCHAR2(30),
  GZND       CHAR(4),
  SFGMFPJQD  CHAR(1),
  SFSYKPHMXZ CHAR(1),
  SFSYGDSM   CHAR(1),
  SFSYQKSM   CHAR(1),
  TZE        NUMBER(17,2),
  DMYNSE     NUMBER(17,2),
  QTZL       VARCHAR2(2000),
  SHBJ       CHAR(1),
  ZCBA       CHAR(1) default '1',
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
comment on table SFDB.SF_JL_QYSDSJMSBA_14A
  is '14A��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰���ʸ񱸰�����';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.ZYSBLXDM
  is 'ר���豸���ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.ZYSBMC
  is 'רҵ�豸����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.GZND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFGMFPJQD
  is '�Ƿ��й���ר���豸��Ʊ���嵥��0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFSYKPHMXZ
  is '�Ƿ�����ר���豸�̶��ʲ�ʹ�ÿ�Ƭ��ר���豸�̶��ʲ���ϸ�ʣ�0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFSYGDSM
  is 'ר���豸����Ŀ¼�涨�Ļ������������ܽ�ˮ����ȫ������ר���豸��������0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFSYQKSM
  is 'ר���豸ʹ�����������0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.TZE
  is '����ר���豸��Ͷ�ʶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.DMYNSE
  is '����ר���豸Ͷ�ʶ�ɵ����Ӧ��˰��';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.QTZL
  is '����˰�����Ҫ���͵���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.ZCBA
  is '�ٴα��� 0:�ٴα�����1��δ�ٴα���';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_14A
  add constraint PK_SF_JL_QYSDSJMSBA_14A primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_14A
  add constraint FK_JMSBA_14A_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_14A to SFDB;--R_SWZG
