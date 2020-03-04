package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdt.dynamicform.entity.DynamicForm;

import java.io.Serializable;

/**
 * 表单的VO对象
 *
 * @author shihaoran
 * @date 2019/11/7
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormsVO extends DynamicForm implements Serializable {

    private static final long serialVersionUID = 3310157903046144948L;

}
