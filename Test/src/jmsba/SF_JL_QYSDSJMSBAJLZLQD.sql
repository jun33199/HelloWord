-- Create table
create table SFDB.SF_JL_QYSDSJMSBAJLZLQD
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  ZLQD       VARCHAR2(1000) not null,
  SWJGZZJGDM VARCHAR2(8) not null,
  CJR        VARCHAR2(30),
  CJRQ       DATE,
  LRR        VARCHAR2(30),
  LRRQ       DATE,
  SFSHTG     CHAR(1) default '0',
  SFKYSC     CHAR(1) default '1'
)
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 4M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBAJLZLQD
  is '减免税备案记录资料清单';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.XH
  is '序号';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.BASQWSH
  is '备案申请文书号';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.ZLQD
  is '减免税备案资料清单';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.SWJGZZJGDM
  is '税务机关组织机构代码';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.CJR
  is '创建人';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.CJRQ
  is '创建日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.LRR
  is '录入人';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.LRRQ
  is '录入日期';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.SFSHTG
  is '审核是否通过，审核时有无此资料，0未审核，1审核时有，2审核时无';
comment on column SFDB.SF_JL_QYSDSJMSBAJLZLQD.SFKYSC
  is '是否可以删除，0：是，1：否';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBAJLZLQD
  add constraint PK_SF_JL_QYSDSJMSBAJLZLQD primary key (XH, BASQWSH)
  using index 
 -- tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBAJLZLQD
  add constraint FK_JMSBAJLZLQD_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBAJLZLQD to SFDB;--R_SWZG
