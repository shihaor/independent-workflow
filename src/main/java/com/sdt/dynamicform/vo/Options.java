package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 富文本编辑器
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Options {

    private String action;

    private String oss;

    private List props;

    private List ali;

    private List qinlu;
}
