package com.xiaoxiang.pagination;

/**
 * 分页对象 加入排序
 *
 * @author xiang.xiaox
 */

public class SortPagination extends Pagination {

    protected String column = ""; //那一列
    protected String order = "desc";   //排序,升或降

    public SortPagination() {

    }

    public SortPagination(int newTotalSize,
                          int newCurPage,
                          int newPageSize,
                          String order) {
        this(newTotalSize, newCurPage, newPageSize, null, order);
    }

    public SortPagination(int newTotalSize,
                          int newCurPage,
                          int newPageSize,
                          String column,
                          String order) {
        this.totalSize = newTotalSize;
        this.curPage = newCurPage;
        this.pageSize = newPageSize;
        build(newTotalSize, newPageSize);
        this.column = column;
        this.order = order;

    }

    private void build(int newTotalSize, int newPageSize) {
        //第一次请求时
        if (newTotalSize == -1) {
            startRow = 1;
            endRow = (startRow + newPageSize) - 1;
            return;
        }
        totalPage = ((newTotalSize - 1) / newPageSize) + 1;
        curPage = (curPage <= 0) ? 1 : curPage;
        curPage = (curPage > totalPage) ? totalPage : curPage;
        totalSize = newTotalSize;
        startRow = ((curPage - 1) * newPageSize) + 1;
        endRow = (startRow + newPageSize) - 1;
        endRow = (endRow > newTotalSize) ? newTotalSize : endRow;
        prePage = curPage - 1;
        nextPage = curPage + 1;
        if (curPage > 1) {
            hasPrePage = true;
        }
        if (curPage < totalPage) {
            hasNextPage = true;
        }
    }


    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

}
