package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdt.dynamicform.entity.BusinessObject;

/**
 * 业务对象vo
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessObjVO extends BusinessObject {

}
