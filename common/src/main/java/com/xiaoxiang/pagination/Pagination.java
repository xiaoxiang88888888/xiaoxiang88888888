package com.xiaoxiang.pagination;

/**
 * 分页对象
 *
 * @author xiang.xiaox
 */

public class Pagination {

    protected int curPage = 1; //当前页
    protected int totalPage;   //总页
    protected int totalSize;   //总记录数
    protected int pageSize = 20;//每页数量
    protected int startRow;     //开始行
    protected int endRow;       //结束行
    protected int prePage;      //上一页
    protected int nextPage;     //下一页

    protected boolean hasPrePage;//是否有上一页
    protected boolean hasNextPage;//是否有下一页

    protected int prePageSize;   //上一次的每页数量

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPrePageSize() {
        return prePageSize;
    }

    public void setPrePageSize(int prePageSize) {
        this.prePageSize = prePageSize;
    }
}
