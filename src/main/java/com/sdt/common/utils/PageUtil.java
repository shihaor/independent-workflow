package com.sdt.common.utils;


import java.util.List;

/**
 * 将工作流中筛选出来的list进行分页输出
 *
 * @author shihaoran
 * @date 2019/12/19
 */
public class PageUtil {

    /**
     * 将查询时不方便分页的结果集进行分页
     *
     * @param list      结果集
     * @param startPage 页码
     * @param limit     每页显示的参数
     * @return 分页的结果
     */
    public static List pageSubList(List list, int startPage, int limit) {

        if (null == list) {
            return null;
        }
        if (list.isEmpty()) {
            return null;
        }
        int count = list.size();
        int pageCount = 0;
        if (count % limit == 0) {
            pageCount = count / limit;
        } else {
            pageCount = count / limit + 1;
        }

        int startIndex = 0;
        int endIndex = 0;
        if (startPage != pageCount) {
            startIndex = (startPage - 1) * limit;
            endIndex = startIndex + limit;
        } else {
            startIndex = (startPage - 1) * limit;
            endIndex = count;
        }
        return list.subList(startIndex, endIndex);
    }
}
