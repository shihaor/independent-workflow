package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 表单的控件
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Column {

    /**
     * 表单图标
     */
    private String icon;

    /**
     * 控件类型
     */
    private String type;

    /**
     * 控件标签
     */
    private String label;

    /**
     * 控件长短
     */
    private Integer span;

    /**
     * 是否展示控件
     */
    private Boolean display;

    /**
     * 是否显示选中值的完整路径
     */
    private Boolean showAllLevels;

    /**
     * uel拼接
     */
    private String separator;

    /**
     * 唯一的prop值
     */
    private String prop;

    /**
     * post的值的内容
     */
    private JsonMap dicQuery;

    /**
     * 远程url
     */
    private String dicUrl;

    /**
     * 请求方式
     */
    private String dicMethod;

    /**
     * 是否可清除
     */
    private Boolean clearable;

    /**
     * 是否可搜索
     */
    private Boolean filterable;

    /**
     * 是否可禁用
     */
    private Boolean disabled;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 校验规则
     */
    private List<Rule> rules;

    /**
     * 如果有子表单的话会有子控件
     */
    private Children children;

    /**
     * 分组控件
     */
    private List<Column> group;

    /**
     * 只读
     */
    private Boolean readonly;

    /**
     * 最大长度
     */
    private Boolean maxlength;

    /**
     * 正则
     */
    private String pattern;

    /**
     * 控件前缀
     */
    private String append;

    /**
     * 控件后缀
     */
    private String prepend;

    /**
     * 默认值
     */
    private String valueDefault;

    /**
     * 占位值
     */
    private String placeholder;

    /**
     * 最小行数
     */
    private Integer minRows;

    /**
     * 最大行数
     */
    private Integer maxRows;

    /**
     * 是否展示字数限制
     */
    private Boolean showWordLimit;

    /**
     * 边框
     */
    private Boolean border;

    /**
     * 大小
     */
    private String size;

    /**
     * 静态值
     */
    private List<DicData> dicData;

    /**
     * 属性选择器展示子节点
     */
    private Boolean parent;

    /**
     * 时间格式化
     */
    private String format;

    /**
     * 上传组件 是否显示已上传的文件列表
     */
    private Boolean showFileList;

    /**
     *
     */
    private Boolean multiple;

    /**
     * 几个文件同时上传
     */
    private Integer limit;

    /**
     * 是否支持拖拽式上传
     */
    private Boolean drag;

    /**
     * 数据对象的地址和名称
     */
    private DicData props;

    /**
     * 服务器返回的参数设置
     */
    private PropsHttp propsHttp;

    /**
     * 图片水印设置
     */
    private CanvasOption canvasOption;

    /**
     * 请求头
     */
    private List<JsonMap> headers;

    /**
     * 请求体
     */
    private List<JsonMap> body;

    /**
     * 文件大小限制
     */
    private Long filesize;

    /**
     * 行为
     */
    private String action;

    /**
     * 接收
     */
    private String accept;

    /**
     * 源
     */
    private String oss;

    /**
     * 提示
     */
    private String tip;

    /**
     * 上传中的提示
     */
    private String loadText;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 最短
     */
    private Integer min;

    /**
     * 最长
     */
    private Integer max;

    /**
     * 评价默认文本
     */
    private String[] texts;

    /**
     * 默认颜色
     */
    private String[] colors;

    /**
     * 富文本
     */
    private Options options;

    /**
     * 时间格式
     */
    private String valueFormat;

    /**
     * 不可见节点
     */
    private List<String> noViewTask;

    /**
     * 不可编辑节点
     */
    private List<String> noEditTask;

    public List<String> getNoViewTask() {
        return noViewTask;
    }

    public void setNoViewTask(List<String> noViewTask) {
        this.noViewTask = noViewTask;
    }

    public List<String> getNoEditTask() {
        return noEditTask;
    }

    public void setNoEditTask(List<String> noEditTask) {
        this.noEditTask = noEditTask;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSpan() {
        return span;
    }

    public void setSpan(Integer span) {
        this.span = span;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Boolean getShowAllLevels() {
        return showAllLevels;
    }

    public void setShowAllLevels(Boolean showAllLevels) {
        this.showAllLevels = showAllLevels;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public JsonMap getDicQuery() {
        return dicQuery;
    }

    public void setDicQuery(JsonMap dicQuery) {
        this.dicQuery = dicQuery;
    }

    public String getDicUrl() {
        return dicUrl;
    }

    public void setDicUrl(String dicUrl) {
        this.dicUrl = dicUrl;
    }

    public String getDicMethod() {
        return dicMethod;
    }

    public void setDicMethod(String dicMethod) {
        this.dicMethod = dicMethod;
    }

    public Boolean getClearable() {
        return clearable;
    }

    public void setClearable(Boolean clearable) {
        this.clearable = clearable;
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

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public List<Column> getGroup() {
        return group;
    }

    public void setGroup(List<Column> group) {
        this.group = group;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public Boolean getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Boolean maxlength) {
        this.maxlength = maxlength;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getAppend() {
        return append;
    }

    public void setAppend(String append) {
        this.append = append;
    }

    public String getPrepend() {
        return prepend;
    }

    public void setPrepend(String prepend) {
        this.prepend = prepend;
    }

    public String getValueDefault() {
        return valueDefault;
    }

    public void setValueDefault(String valueDefault) {
        this.valueDefault = valueDefault;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getMinRows() {
        return minRows;
    }

    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public Boolean getShowWordLimit() {
        return showWordLimit;
    }

    public void setShowWordLimit(Boolean showWordLimit) {
        this.showWordLimit = showWordLimit;
    }

    public Boolean getBorder() {
        return border;
    }

    public void setBorder(Boolean border) {
        this.border = border;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<DicData> getDicData() {
        return dicData;
    }

    public void setDicData(List<DicData> dicData) {
        this.dicData = dicData;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getShowFileList() {
        return showFileList;
    }

    public void setShowFileList(Boolean showFileList) {
        this.showFileList = showFileList;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getDrag() {
        return drag;
    }

    public void setDrag(Boolean drag) {
        this.drag = drag;
    }

    public DicData getProps() {
        return props;
    }

    public void setProps(DicData props) {
        this.props = props;
    }

    public PropsHttp getPropsHttp() {
        return propsHttp;
    }

    public void setPropsHttp(PropsHttp propsHttp) {
        this.propsHttp = propsHttp;
    }

    public CanvasOption getCanvasOption() {
        return canvasOption;
    }

    public void setCanvasOption(CanvasOption canvasOption) {
        this.canvasOption = canvasOption;
    }

    public List<JsonMap> getHeaders() {
        return headers;
    }

    public void setHeaders(List<JsonMap> headers) {
        this.headers = headers;
    }

    public List<JsonMap> getBody() {
        return body;
    }

    public void setBody(List<JsonMap> body) {
        this.body = body;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getOss() {
        return oss;
    }

    public void setOss(String oss) {
        this.oss = oss;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLoadText() {
        return loadText;
    }

    public void setLoadText(String loadText) {
        this.loadText = loadText;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String[] getTexts() {
        return texts;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public String getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(String valueFormat) {
        this.valueFormat = valueFormat;
    }
}
