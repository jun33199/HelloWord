--����˰�걨����
--����˰�������ӱ�
select t.*,t.rowid from  SFDB.SF_JL_QYSDSJMSBAJL t where jsjdm='01054650'--��ҵ����˰����˰������¼����
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_01 t --01��ҵ�ۺ�������Դ���������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����뱸�������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_02 t --02�����¼������²�Ʒ���¹��շ������о��������ñ��������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_03 t --03���òм�����֧���Ĺ��ʱ�������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_04 t --04���¹����ص���ֵĹ���������ʩ��ĿͶ�ʾ�Ӫ������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_05 t --05����ũ���֡�������ҵ��Ŀ������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_06 t --06���·��������Ļ������������ܽ�ˮ��Ŀ������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_07 t --07���������ļ���ת������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_08 t --08������Ҫ�ص���ֵĸ��¼�����ҵ
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_09 t --09�°����������ҵ�����ɵ�·�����ҵ
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_10 t --10���ҹ滮�����ڵ��ص����������ҵ
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_11 t --11�����߿�С��0.8΢�ף��������ɵ�·��Ʒ��������ҵ��������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_12 t --12Ͷ�ʶ��80��Ԫ����һ򼯳ɵ�·�߿�С��0.25um�ļ��ɵ�·������ҵ
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_13a t --13A��ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ�
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_13b t --13B��ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö�
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_14a t --14A��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰���ʸ񱸰�����
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_14b t --14B��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰�������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_15 t --15�̶��ʲ������۾����޻�����۾ɱ�������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_16 t --16�⹺��������۾ɻ�̯������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_17 t --17��෢չ������Ŀ�����ñ��������������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_18 t --18���϶��ļ����Ƚ��ͷ�����ҵ���������������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_19 t --19��Ӫ���Ļ���ҵ��λת��Ϊ��ҵ���������������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_20 t --20���϶��Ķ�����ҵ���������������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_21 t --21������װ���˲���Աר����Ʒ��ҵ��������
select t.*,t.rowid from  sfdb.sf_jl_qysdsjmsba_22 t --22���ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������

--���ⱸ����������
select * from dmdb.sf_dm_jmbasxdm    --���ⱸ����������

--����˰������¼�����嵥
select t.*,t.rowid from  SFDB.SF_JL_QYSDSJMSBAJLZLQD t --����˰������¼�����嵥
select t.*,t.rowid from  djdb.dj_jl_jbsj t where jsjdm='01054650' --˰��Ǽ�-��������




--�����ѯsql
select a.jsjdm,b.nsrmc,d.swjgzzjgmc,d.swjgzzjgdm,a.band,A.BASQBH,(select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=a.jmbasxdm) jmbasxmc,a.jmbasxdm,a.sqlxdm,a.sqzt
from sfdb.sf_jl_qysdsjmsbajl a ,djdb.dj_jl_jbsj b,aqdb.zk_jl_wsyh c ,dmdb.gy_dm_swjgzzjg d 
where a.jsjdm=b.jsjdm and a.jsjdm=c.yhid and a.sqzt<>'1' and a.swjgzzjgdm=d.swjgzzjgdm 
