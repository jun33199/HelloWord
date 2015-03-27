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
  is '02开发新技术、新产品、新工艺发生的研究开发费用备案事项表';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.YFFYLYDM
  is '研发费用领域代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.XMMC
  is '项目名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYJHYS
  is '是否有计划书和预算,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYBZRY
  is '是否有编制情况和专业人员名单,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYYFFYQK
  is '是否有研发费用情况归集表,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYJYWJ
  is '是否有决议文件,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYHTXY
  is '是否有合同协议,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SFYYJCG
  is '是否有研究成果报告,0:是,1:否';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.DQKCJE
  is '当期扣除金额';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.WXZCJE
  is '形成无形资产金额';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.JJKCJE
  is '加计扣除额';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.QTZL
  is '其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_2014_02.LRRQ
  is '录入日期';
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
