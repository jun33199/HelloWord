--减免税年报季报
--减免税主表与子表
select t.*,t.rowid from  SFDB.SF_JL_QYSDSJMSBAJL t where jsjdm='01054650'--企业所得税减免税备案记录主表
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_01 t --01企业综合利用资源，生产符合国家产业政策规定的产品所取得的收入备案事项表
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_02 t --02开发新技术、新产品、新工艺发生的研究开发费用备案事项表
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_03 t --03安置残疾人所支付的工资备案事项
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_04 t --04从事国家重点扶持的公共基础设施项目投资经营的所得
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_05 t --05从事农、林、牧、渔业项目的所得
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_06 t --06从事符合条件的环境保护、节能节水项目的所得
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_07 t --07符合条件的技术转让所得
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_08 t --08国家需要重点扶持的高新技术企业
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_09 t --09新办软件生产企业、集成电路设计企业
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_10 t --10国家规划布局内的重点软件生产企业
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_11 t --11生产线宽小于0.8微米（含）集成电路产品的生产企业备案事项
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_12 t --12投资额超过80亿元人民币或集成电路线宽小于0.25um的集成电路生产企业
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_13a t --13A创业投资企业投资、抵扣应纳税所得额资格
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_13b t --13B创业投资企业投资、抵扣应纳税所得额
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_14a t --14A企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额资格备案事项
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_14b t --14B企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额备案事项
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_15 t --15固定资产缩短折旧年限或加速折旧备案事项
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_16 t --16外购软件缩短折旧或摊销年限
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_17 t --17清洁发展机制项目的所得备案事项具体描述
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_18 t --18经认定的技术先进型服务企业备案事项具体描述
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_19 t --19经营性文化事业单位转制为企业备案事项具体描述
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_20 t --20经认定的动漫企业备案事项具体描述
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_21 t --21生产和装配伤残人员专门用品企业备案事项
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_22 t --22节能服务公司实施合同能源管理项目的所得备案事项

--减免备案事项代码表
select * from dmdb.sf_dm_jmbasxdm    --减免备案事项代码表

--减免税备案记录资料清单
select t.*,t.rowid from  SFDB.SF_JL_QYSDSJMSBAJLZLQD t --减免税备案记录资料清单
select t.*,t.rowid from  djdb.dj_jl_jbsj t where jsjdm='01054650' --税务登记-基本数据




--主表查询sql
select a.jsjdm,b.nsrmc,d.swjgzzjgmc,d.swjgzzjgdm,a.band,A.BASQBH,(select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=a.jmbasxdm) jmbasxmc,a.jmbasxdm,a.sqlxdm,a.sqzt
from sfdb.sf_jl_qysdsjmsbajl a ,djdb.dj_jl_jbsj b,aqdb.zk_jl_wsyh c ,dmdb.gy_dm_swjgzzjg d 
where a.jsjdm=b.jsjdm and a.jsjdm=c.yhid and a.sqzt<>'1' and a.swjgzzjgdm=d.swjgzzjgdm 
