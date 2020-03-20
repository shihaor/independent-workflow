package com.sdt.dynamicform.displayform.impl;

import com.sdt.common.utils.JsonObjectMapper;
import com.sdt.dynamicform.displayform.IDisplayForm;
import com.sdt.dynamicform.vo.Column;
import com.sdt.dynamicform.vo.ContainAllVO;

import java.util.List;

/**
 * 抽象公共类，存放公共方法
 *
 * @author shihaoran
 * @date 2020/3/16
 */
public abstract class AbstractDisplayForm implements IDisplayForm {

    protected final JsonObjectMapper mapper = new JsonObjectMapper();

    /**
     * 改变表单展示样式
     *
     * @param containAllVO 表单的内容
     * @param taskId       节点Id
     * @return 修改后的表单样式
     */
    protected ContainAllVO changeStyle(ContainAllVO containAllVO, String taskId) {

        // 普通展示的需要修改
        changeParam(taskId, containAllVO.getColumn());
        // 分组需要修改
        containAllVO.getGroup().forEach(group -> changeParam(taskId, group.getColumn()));
        return containAllVO;
    }

    /**
     * 修改表单参数
     *
     * @param taskId     节点id
     * @param columnList 控件内容
     */
    private void changeParam(String taskId, List<Column> columnList) {

        columnList.forEach(column -> {
            if (column.getType().equals("子表单")) {
                //单独处理
            }
            if (column.getNoViewTask().equals(taskId)) {
                column.setDisplay(false);
            } else {
                column.setDisplay(true);
            }
            if (column.getNoEditTask().equals(taskId)) {
                column.setDisabled(true);
            } else {
                column.setDisabled(false);
            }
        });
    }
}
