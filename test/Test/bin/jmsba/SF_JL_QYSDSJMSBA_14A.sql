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
  is '14A企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额资格备案事项';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.ZYSBLXDM
  is '专用设备类型代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.ZYSBMC
  is '专业设备名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.GZND
  is '购置年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFGMFPJQD
  is '是否有购买专用设备发票及清单，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFSYKPHMXZ
  is '是否设置专用设备固定资产使用卡片或专用设备固定资产明细帐，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFSYGDSM
  is '专用设备属于目录规定的环境保护、节能节水、安全生产等专用设备的声明，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SFSYQKSM
  is '专用设备使用情况声明，0：是，1：否';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.TZE
  is '购置专用设备的投资额';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.DMYNSE
  is '购置专用设备投资额可抵免的应纳税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.QTZL
  is '主管税务机关要求报送的其他资料';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.ZCBA
  is '再次备案 0:再次备案，1：未再次备案';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_14A.LRRQ
  is '录入日期';
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
