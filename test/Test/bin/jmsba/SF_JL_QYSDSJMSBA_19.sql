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
  is '19经营性文化事业单位转制为企业备案事项具体描述';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_19.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.WHSYDWLXDM
  is '文化事业单位类型代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYQYMD
  is '有专制企业名单，0：有，1：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYZZFAPFH
  is '有专制反感批复函，0：有，1：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFBLGSYYZZ
  is '办理工商营业执照，0：有，1：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYZXSYDWZM
  is '有注销事业单位证明，0：有，1：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFCJSHBX
  is '参加社会保险，0：有，1：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.SFYBGZBJG
  is '变更资本机构，0：有，1：无';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.JMSE
  is '减免税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_19.LRRQ
  is '录入日期';
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
