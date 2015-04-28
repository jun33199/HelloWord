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
  is '14B企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额备案事项';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.ZYSBLXDM
  is '专用设备类型代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.ZYSBMC
  is '专用设备名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.TZEZS
  is '当年购置设备投资额';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.DMND
  is '抵免年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.DMYNSE
  is '当年实际抵免的应纳税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.YWCBABS
  is '已完成备案标识 0:已完成，1：未完成';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.LRRQ
  is '录入日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.SBID
  is '设备ID';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.DNKDMSE
  is '当年可抵免的应纳税额 ';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.JZE
  is '结转以后年度抵免的应纳税额';
comment on column SFDB.SF_JL_QYSDSJMSBA_14B.TZND
  is '投资年度';
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
