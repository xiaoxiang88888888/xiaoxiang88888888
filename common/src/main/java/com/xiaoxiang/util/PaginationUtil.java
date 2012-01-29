package com.xiaoxiang.util;

import com.xiaoxiang.Constant;
import com.xiaoxiang.pagination.SortPagination;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具类
 *
 * @author xiang.xiaox
 */

public class PaginationUtil {
    public static int newPageSize = 20; // 默认一页显示20条数据

    public static SortPagination parse(HttpServletRequest request) {
        return parse(request, -1);
    }

    public static SortPagination parse(HttpServletRequest request, int totalRow) {
        int newTotalCount = totalRow;
        int newCurPage;
        if (request.getParameter("_CUR_PAGE") == null) {
            newCurPage = 1;
        } else {
            newCurPage = Integer.parseInt(request.getParameter("_CUR_PAGE") + "");
        }
        if (newCurPage == 0)
            newCurPage = 1;
        //当前展现页大小
        int newPageSize = Integer.parseInt(request.getParameter("_PAGE_SIZE") + "");
        //默认20行
        if (newPageSize == 0) {
            newPageSize = PaginationUtil.newPageSize;
        }

        if (Constant.YES.equalsIgnoreCase(request.getParameter("_IS_REPLY_SELECT_COUNT"))) {
            newTotalCount = -1;
        }
        if (Constant.YES.equalsIgnoreCase(request.getParameter("_IS_REPLY_SELECT_COUNT_CHANGE_NUM_PRE_PAGE"))) {
            int preStartRow = (newCurPage - 1) * newPageSize + 1;
            newCurPage = (preStartRow - 1) / newPageSize + 1;
            newTotalCount = -1;
        }
        String column = request.getParameter("_ORDER_COLUMN");
        String order = request.getParameter("_ORDER_BY");
        return parse(newTotalCount, newCurPage, newPageSize, column, order);
    }

    private static SortPagination parse(int newTotalSize,
                                        int newCurrentPage,
                                        int newShowNum,
                                        String column,
                                        String order) {
        SortPagination SortPagination = new SortPagination(newTotalSize, newCurrentPage, newShowNum, "desc");
        SortPagination.setTotalSize(newTotalSize);

        if (!StringUtil.isEmpty(column)) {
            SortPagination.setColumn(column);
        }

        if (StringUtil.isEmpty(order)) {
            SortPagination.setOrder("desc");
        } else {
            SortPagination.setOrder(order);
        }
        return SortPagination;
    }

}
