package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 控件的限制VO
 *
 * @date 2019/11/4
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionVO implements Serializable {

    private static final long serialVersionUID = 4272039078420552803L;

    /**
     * width
     */
    private String width;

    /**
     * defaultValue
     */
    private Object defaultValue;

    /**
     * required
     */
    private Boolean required;

    /**
     * datatype 比如： url, email
     */
    private String datatype;

    /**
     * 正则匹配
     */
    private String pattern;

    /**
     * 占位内容
     */
    private String placeholder;

    /**
     * disabled
     */
    private Boolean disabled;

    /**
     * remoteFunc 远程地址
     */
    private String remoteFunc;

    /**
     * 计数器中用到的
     * min 最小值
     * max 最大值
     * step 步数
     * controlsPosition 控制位置
     */
    private Integer min;
    private Integer max;
    private Integer step;
    private String controlsPosition;

    /**
     * 单选框中用到的
     * inline 一行true
     * showLabel 显示标签
     * labelOption 选项，选项序号
     */
    private Boolean inline;
    private Boolean showLabel;
    private List<JsonMap> labelOptions;

    /**
     * 是否通过url获取
     * TODO:需要和前端确定remoteOptions格式
     * remote 是否远程 true是
     * props 值， 标签
     * filterable
     */
    private Boolean filterable;
    private Boolean remote;
    private List<JsonMap> remoteOptions;
    private Props props;

    /**
     * 时间选择器用的
     * readonly 只读
     * editable 可写
     * clearable 可清空
     * startPlaceholder 开始时间范围
     * endPlaceholder 截止时间范围
     * isRange 是否限定范围
     * arrowControl
     * format 格式
     */
    private Boolean readonly;
    private Boolean editable;
    private Boolean clearable;
    private String startPlaceholder;
    private String endPlaceholder;
    private Boolean isRange;
    private Boolean arrowControl;
    private String format;

    /**
     * 位置
     */
    private String align;
    private String justify;
    private Integer gutter;

    /**
     * 图片
     */
    private String tokenFunc;
    private String token;
    private String domain;
    private Long length;
    private Boolean multiple;
    private Boolean isQiniu;
    private Boolean isDelete;
    private Boolean isEdit;
    private String action;

    /**
     * 控件属性
     */
    private List<Object> nonEditable;
    private List<Object> invisible;

    public List<Object> getNonEditable() {
        return nonEditable;
    }

    public void setNonEditable(List<Object> nonEditable) {
        this.nonEditable = nonEditable;
    }

    public List<Object> getInvisible() {
        return invisible;
    }

    public void setInvisible(List<Object> invisible) {
        this.invisible = invisible;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getFilterable() {
        return filterable;
    }

    public void setFilterable(Boolean filterable) {
        this.filterable = filterable;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getInline() {
        return inline;
    }

    public void setInline(Boolean inline) {
        this.inline = inline;
    }

    public Boolean getShowLabel() {
        return showLabel;
    }

    public void setShowLabel(Boolean showLabel) {
        this.showLabel = showLabel;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getClearable() {
        return clearable;
    }

    public void setClearable(Boolean clearable) {
        this.clearable = clearable;
    }

    public Boolean getRange() {
        return isRange;
    }

    public void setRange(Boolean range) {
        isRange = range;
    }

    public Boolean getArrowControl() {
        return arrowControl;
    }

    public void setArrowControl(Boolean arrowControl) {
        this.arrowControl = arrowControl;
    }

    public String getTokenFunc() {
        return tokenFunc;
    }

    public void setTokenFunc(String tokenFunc) {
        this.tokenFunc = tokenFunc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Boolean getQiniu() {
        return isQiniu;
    }

    public void setQiniu(Boolean qiniu) {
        isQiniu = qiniu;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getJustify() {
        return justify;
    }

    public void setJustify(String justify) {
        this.justify = justify;
    }

    public Integer getGutter() {
        return gutter;
    }

    public void setGutter(Integer gutter) {
        this.gutter = gutter;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean isRequired() {
        return required;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public String getRemoteFunc() {
        return remoteFunc;
    }

    public void setRemoteFunc(String remoteFunc) {
        this.remoteFunc = remoteFunc;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getControlsPosition() {
        return controlsPosition;
    }

    public void setControlsPosition(String controlsPosition) {
        this.controlsPosition = controlsPosition;
    }

    public Boolean isInline() {
        return inline;
    }

    public Boolean isShowLabel() {
        return showLabel;
    }

    public List<JsonMap> getLabelOptions() {
        return labelOptions;
    }

    public void setLabelOptions(List<JsonMap> labelOptions) {
        this.labelOptions = labelOptions;
    }

    public Boolean isRemote() {
        return remote;
    }

    public List<JsonMap> getRemoteOptions() {
        return remoteOptions;
    }

    public void setRemoteOptions(List<JsonMap> remoteOptions) {
        this.remoteOptions = remoteOptions;
    }

    public Props getProps() {
        return props;
    }

    public void setProps(Props props) {
        this.props = props;
    }

    public Boolean isReadonly() {
        return readonly;
    }

    public Boolean isEditable() {
        return editable;
    }

    public Boolean isClearable() {
        return clearable;
    }

    public String getStartPlaceholder() {
        return startPlaceholder;
    }

    public void setStartPlaceholder(String startPlaceholder) {
        this.startPlaceholder = startPlaceholder;
    }

    public String getEndPlaceholder() {
        return endPlaceholder;
    }

    public void setEndPlaceholder(String endPlaceholder) {
        this.endPlaceholder = endPlaceholder;
    }

    public Boolean isRange() {
        return isRange;
    }

    public Boolean isArrowControl() {
        return arrowControl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

