-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_13B
(
  XH          VARCHAR2(15) not null,
  BASQWSH     VARCHAR2(12) not null,
  JSJDM       VARCHAR2(8),
  BAND        VARCHAR2(4),
  SWJGZZJGDM  VARCHAR2(8) not null,
  GXJSLYDM    CHAR(2) not null,
  BTZQYJSJDM  VARCHAR2(8),
  BTZQYSWDJZH VARCHAR2(30),
  BTZQYMC     VARCHAR2(100),
  BTZQYSSD    CHAR(1),
  TZND        VARCHAR2(4),
  TZE         NUMBER(17,2) default 0,
  DKE         NUMBER(17,2) default 0,
  SHBJ        CHAR(1),
  YWCBABS     CHAR(1) default '1',
  CJR         VARCHAR2(30),
  CJRQ        DATE,
  LRR         VARCHAR2(30),
  LRRQ        DATE,
  DNKDKE      NUMBER(17,2) default 0,
  JZE         NUMBER(17,2) default 0
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
comment on table SFDB.SF_JL_QYSDSJMSBA_13B
  is '13B创业投资企业投资、抵扣应纳税所得额';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.JSJDM
  is '计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BAND
  is '备案年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.GXJSLYDM
  is '高新技术领域代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYJSJDM
  is '被投资企业计算机代码';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYSWDJZH
  is '被投资企业税务登记证号';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYMC
  is '被投资企业名称';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.BTZQYSSD
  is '被投资企业所属地，0：本市，1：外省市';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.TZND
  is '投资年度';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.TZE
  is '当年新增投资额 ';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.DKE
  is '当年实际抵扣应纳税所得额';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.SHBJ
  is '审核标记,0:通过,1:不通过';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.YWCBABS
  is '已完成备案标识 0:已完成，1：未完成';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.LRRQ
  is '录入日期';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.DNKDKE
  is '当年可抵扣应纳税所得额';
comment on column SFDB.SF_JL_QYSDSJMSBA_13B.JZE
  is '结转以后年度抵扣应纳税所得额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_13B
  add constraint PK_SF_JL_QYSDSJMSBA_13B primary key (BASQWSH, XH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_13B
  add constraint FK_JMSBA_13B_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_13B to SFDB;--R_SWZG
