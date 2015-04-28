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
  is '07符合条件的技术转让所得';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_07.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JNJWBS
  is '境内境外标识，0：境内，1：境外';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSZRLXDM
  is '技术转让类型代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYJSZRHT
  is '技术转让合同，0：无，1：有';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYRDDJZM
  is '认定登记证明，0：无，1：有';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYSRMXB
  is '收入明细表，0：无，1：有';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SFYHSQKSM
  is '核算情况声明，0：无，1：有';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSZRSD
  is '技术转让所得';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.JSZRHTMC
  is '经科学技术行政部门认定登记的技术转让合同名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_07.LRRQ
  is '录入日期';
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
