package com.sdt.workflow.core.diybpmnimage;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 继承图形绘制的接口，增加color
 *
 * @author shihaoran
 * @date 2019/11/20
 */
public interface IDiyProcessDiagramGenerator extends ProcessDiagramGenerator {

    /**
     * 将Color类加载进去
     *
     * @param bpmnModel             流程图模型
     * @param imageType             图片类型
     * @param highLightedActivities 高亮节点
     * @param highLightedFlows      高亮线条
     * @param activityFontName      节点字体
     * @param labelFontName         标签字体
     * @param annotationFontName    注释字体
     * @param customClassLoader     自定义类加载
     * @param scaleFactor
     * @param colors                颜色加载
     * @param currIds
     * @return 图片流
     */
    InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities,
                                List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName,
                                ClassLoader customClassLoader, double scaleFactor, Color[] colors, Set<String> currIds);
}
