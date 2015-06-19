package com.creationstar.bjtax.qsgl.VisionLogic.form.Base;

import java.util.ArrayList;

import com.ttsoft.common.util.QueryCache;

/**
 *
 * <p>Title: ҳ���ѯ�ĸ���</p>
 *
 * <p>Description:�����������һ��ҳ���ѯ��������ҪActionForm��ɵĹ���
 * ���е����Ժͷ�����Ҫ��ҳ��Ԫ�����ʹ�ã�
 *  </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company:������Ѷ�������������޹�˾ </p>
 *
 * @author �Բ�
 * @version 1.0
 */
public class QueryBaseForm extends BaseForm {

    /**
     * Form��ǰ������״̬��
     * Query: ��û��ִ�в�ѯ��
     * Result: �Ѿ�ִ�����ѯ��
     */
    protected String status = "Query";

    /**
     * ��ѯ������ݽṹ����Ҫ�ǻ������ݺ��ṩ��ҳ�Ĳ�����
     */
    protected QueryCache queryCache = new QueryCache();

    /**
     * BaseForm���󣬱���Ҫ�鿴�����ݣ����ڲ鿴��ϸ���޸����ݡ�
     */
    protected BaseForm viewForm;

    /**
     * ѡ���Ҫ�鿴/�޸ĵ������ڵ�ǰҳ��λ��������
     */
    protected int viewIndex = -1;

    /**
     * ��ѡ�е������ڵ�ǰҳ�е�λ���������顣
     */
    protected int selectedIndex[];

    /**
     * �����ҳ�롣
     * ����ҳʱ�á�
     */
    protected int changePage = -1;

    /**
     * Access method for the status property.
     *
     * @return   the current value of the status property
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param aStatus the new value of the status property
     */
    public void setStatus(String aStatus) {
        status = aStatus;
    }

    /**
     * Access method for the queryCache property.
     *
     * @return   the current value of the queryCache property
     */
    public QueryCache getQueryCache() {
        return queryCache;
    }

    /**
     * Sets the value of the queryCache property.
     *
     * @param aQueryCache the new value of the queryCache property
     */
    public void setQueryCache(QueryCache aQueryCache) {
        queryCache = aQueryCache;
    }

    /**
     * Access method for the viewForm property.
     *
     * @return   the current value of the viewForm property
     */
    public BaseForm getViewForm() {
        return viewForm;
    }

    /**
     * Sets the value of the viewForm property.
     *
     * @param aViewForm the new value of the viewForm property
     */
    public void setViewForm(BaseForm aViewForm) {
        viewForm = aViewForm;
    }

    /**
     * Access method for the viewIndex property.
     *
     * @return   the current value of the viewIndex property
     */
    public int getViewIndex() {
        return viewIndex;
    }

    /**
     * Sets the value of the viewIndex property.
     *
     * @param aViewIndex the new value of the viewIndex property
     */
    public void setViewIndex(int aViewIndex) {
        viewIndex = aViewIndex;
    }

    /**
     * Access method for the selectedIndex property.
     *
     * @return   the current value of the selectedIndex property
     */
    public int[] getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Sets the value of the selectedIndex property.
     *
     * @param aSelectedIndex the new value of the selectedIndex property
     */
    public void setSelectedIndex(int[] aSelectedIndex) {
        selectedIndex = aSelectedIndex;
    }

    /**
     * Access method for the changePage property.
     *
     * @return   the current value of the changePage property
     */
    public int getChangePage() {
        return changePage;
    }

    /**
     * Sets the value of the changePage property.
     *
     * @param aChangePage the new value of the changePage property
     */
    public void setChangePage(int aChangePage) {
        changePage = aChangePage;
    }

    /**
     * ��ȡѡ�е�����
     *         ArrayList currentPageData = getCurrentPage();
     *         if (seletedIndex != null)
     *         {
     *             ArrayList list = new ArrayList();
     *             int size = seletedIndex.length ;
     *             for (int i = 0 ; i < size; i++)
     *             {
     *                 list.add(currentPageData.get(selectedIndex[i])) ;
     *             }
     *             return list;
     *         }
     *         return null;
     *
     *
     * @return ѡ�е����ݼ���
     */
    public ArrayList getSelectedData() {
        //��ȡѡ�е�����
        ArrayList currentPageData = getCurrentPage();
        if (selectedIndex != null) {
            ArrayList list = new ArrayList();
            int size = selectedIndex.length;
            for (int i = 0; i < size; i++) {
                list.add(currentPageData.get(selectedIndex[i]));
            }
            return list;
        }
        return null;
    }

    /**
     * ��������е�����ɾ����
     * queryCache.delete(selectedData);
     *
     * @param selectedData
     */
    public void removeSelectedData(java.util.ArrayList selectedData) {
        queryCache.delete(selectedData);
    }

    /**
     * ��������е�ָ��λ�õ�����ɾ����
     *
     * @param selectedData
     */
    public void removeData(int index) {
        ArrayList delList = new ArrayList();
        delList.add(queryCache.getDataDetail(index));
        queryCache.delete(delList);
    }

    /**
     * ��ȡ��ǰҳ��
     * return queryCache.getCurrentPageNum();
     *
     * @return ��ǰҳ��
     */
    public int getCurrentPageNum() {
        return queryCache.getCurrentPageNum();
    }

    /**
     * ��ȡ��ǰҳ������
     * return queryCache.getCurrentPage();
     *
     * @return ��ǰҳ��
     */
    public ArrayList getCurrentPage() {
        return queryCache.getCurrentPage();
    }

    /**
     * queryCache.getPage(changePage);
     * ��ȡָ��ҳ��Ľ���Ӽ�
     *
     * @param pageNum ָ����ҳ��
     */
    public void changePage() {
        queryCache.getPage(this.changePage);
    }

    /**
     * ���ɲ鿴Form.������ʵ�֡�
     */
    public void createViewForm() {

    }

    /**
     * �޸Ľ�����е����ݡ�
     * queryCache.modify(getCurrentPageNum,viewIndex,data);
     *
     * @param data
     */
    public void modifyData(Object data) {
        queryCache.modify(viewIndex, data);
    }

    /**
     * ��������е���������ɾ��
     * queryCache.removeAll();
     */
    public void removeAll() {
        queryCache.removeAll();
    }

    /**
     * ��ȡ�鿴���ݵ���ϸ����
     * return queryCache.getDataDetail(getCurrentPageNum,viewIndex);
     *
     * @return Object
     */
    public Object getViewDataDetail() {
        return queryCache.getDataDetail(viewIndex);
    }

    /**
     * ��ȡ�޸ĺ����ϸ����
     * @return Object
     */
    public Object getModifiedData() {
        return viewForm.getData();
    }

    /**
     * ��ո���
     * �ɸ�����ʵ��
     */
    public void clear() {

    }
}
