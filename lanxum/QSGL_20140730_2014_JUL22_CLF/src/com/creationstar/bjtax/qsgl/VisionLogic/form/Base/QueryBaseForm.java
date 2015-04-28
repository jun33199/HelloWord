package com.creationstar.bjtax.qsgl.VisionLogic.form.Base;

import java.util.ArrayList;

import com.ttsoft.common.util.QueryCache;

/**
 *
 * <p>Title: 页面查询的父类</p>
 *
 * <p>Description:基本上完成了一般页面查询操作中需要ActionForm完成的工作
 * 所有的属性和方法需要跟页面元素配合使用；
 *  </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company:北京创讯益达软件技术有限公司 </p>
 *
 * @author 赵博
 * @version 1.0
 */
public class QueryBaseForm extends BaseForm {

    /**
     * Form当前所处的状态。
     * Query: 还没有执行查询。
     * Result: 已经执行完查询。
     */
    protected String status = "Query";

    /**
     * 查询结果数据结构，主要是缓存数据和提供翻页的操作。
     */
    protected QueryCache queryCache = new QueryCache();

    /**
     * BaseForm对象，保存要查看的数据，用于查看详细和修改数据。
     */
    protected BaseForm viewForm;

    /**
     * 选择的要查看/修改的数据在当前页的位置索引。
     */
    protected int viewIndex = -1;

    /**
     * 被选中的数据在当前页中的位置索引数组。
     */
    protected int selectedIndex[];

    /**
     * 请求的页码。
     * 请求翻页时用。
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
     * 获取选中的数据
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
     * @return 选中的数据集合
     */
    public ArrayList getSelectedData() {
        //获取选中的数据
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
     * 将结果集中的数据删除。
     * queryCache.delete(selectedData);
     *
     * @param selectedData
     */
    public void removeSelectedData(java.util.ArrayList selectedData) {
        queryCache.delete(selectedData);
    }

    /**
     * 将结果集中的指定位置的数据删除。
     *
     * @param selectedData
     */
    public void removeData(int index) {
        ArrayList delList = new ArrayList();
        delList.add(queryCache.getDataDetail(index));
        queryCache.delete(delList);
    }

    /**
     * 获取当前页码
     * return queryCache.getCurrentPageNum();
     *
     * @return 当前页码
     */
    public int getCurrentPageNum() {
        return queryCache.getCurrentPageNum();
    }

    /**
     * 获取当前页的数据
     * return queryCache.getCurrentPage();
     *
     * @return 当前页码
     */
    public ArrayList getCurrentPage() {
        return queryCache.getCurrentPage();
    }

    /**
     * queryCache.getPage(changePage);
     * 获取指定页码的结果子集
     *
     * @param pageNum 指定的页码
     */
    public void changePage() {
        queryCache.getPage(this.changePage);
    }

    /**
     * 生成查看Form.由子类实现。
     */
    public void createViewForm() {

    }

    /**
     * 修改结果集中的数据。
     * queryCache.modify(getCurrentPageNum,viewIndex,data);
     *
     * @param data
     */
    public void modifyData(Object data) {
        queryCache.modify(viewIndex, data);
    }

    /**
     * 将结果集中的所有数据删除
     * queryCache.removeAll();
     */
    public void removeAll() {
        queryCache.removeAll();
    }

    /**
     * 获取查看数据的详细数据
     * return queryCache.getDataDetail(getCurrentPageNum,viewIndex);
     *
     * @return Object
     */
    public Object getViewDataDetail() {
        return queryCache.getDataDetail(viewIndex);
    }

    /**
     * 获取修改后的详细数据
     * @return Object
     */
    public Object getModifiedData() {
        return viewForm.getData();
    }

    /**
     * 清空各域
     * 由各子类实现
     */
    public void clear() {

    }
}
