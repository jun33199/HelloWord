-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_22
(
  XH           VARCHAR2(15) not null,
  BASQWSH      VARCHAR2(12) not null,
  JSJDM        VARCHAR2(8),
  BAND         VARCHAR2(4),
  SWJGZZJGDM   VARCHAR2(8) not null,
  JNJPJSGZXMDM CHAR(2) not null,
  DYBZLMC      VARCHAR2(100),
  DYBRQ        VARCHAR2(6),
  MZQSND       CHAR(4),
  MZZZND       CHAR(4),
  JBZSQSND     CHAR(4),
  JBZSZZND     CHAR(4),
  SHBJ         CHAR(1),
  CJR          VARCHAR2(30),
  CJRQ         DATE,
  LRR          VARCHAR2(30),
  LRRQ         DATE,
  ZRHTXM       CHAR(1),
  ZRHTXMYH     CHAR(1),
  ZRHTXMYHWJ   CHAR(1)
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
comment on table SFDB.SF_JL_QYSDSJMSBA_22
  is '22节能服务公司实施合同能源管理项目的所得备案事项';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_22.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.JNJPJSGZXMDM
  is '节能减排技术改造项目类型代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.DYBZLMC
  is '取得第一笔收入的相关证明资料名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.DYBRQ
  is '取得第一笔生产经营收入的时间';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.MZQSND
  is '免征起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.MZZZND
  is '免征终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.JBZSQSND
  is '减半征收起始年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.JBZSZZND
  is '减半征收终止年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.LRRQ
  is '录入日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.ZRHTXM
  is '是否发生合同能源管理项目转让、受让';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.ZRHTXMYH
  is '项目转让合同、项目原享受优惠';
comment on column SFDB.SF_JL_QYSDSJMSBA_22.ZRHTXMYHWJ
  is '项目转让合同、项目原享受优惠备案文件';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_22
  add constraint PK_SF_JL_QYSDSJMSBA_22 primary key (XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_22
  add constraint UK_SF_JL_QYSDSJMSBA_22 unique (BASQWSH, JNJPJSGZXMDM)
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
alter table SFDB.SF_JL_QYSDSJMSBA_22
  add constraint FK_JMSBA_22_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_22 to SFDB;--R_SWZG
