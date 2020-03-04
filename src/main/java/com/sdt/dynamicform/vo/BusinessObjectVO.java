package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdt.dynamicform.entity.BusinessObject;

import java.io.Serializable;

/**
 * 所有业务对象的VO
 *
 * @date 2019/11/11
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessObjectVO extends BusinessObject implements Serializable {

    private static final long serialVersionUID = 9152738916392835073L;

}
