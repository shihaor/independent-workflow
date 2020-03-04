package com.sdt.workflow.core.diybpmnimage.impl;

import com.sdt.common.constant.WorkflowConstants;
import org.activiti.bpmn.model.AssociationDirection;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.image.exception.ActivitiImageException;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;
import org.activiti.image.util.ReflectUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * 自定义图片转换类
 *
 * @author shihaoran
 * @date 2019/11/20
 */
public class DiyProcessDiagramCanvas extends DefaultProcessDiagramCanvas {

    /**
     * 定义标签颜色
     */
    protected static Color LEBEL_COLOR = new Color(0, 0, 0);
    /**
     * 方法开关
     */
    private static volatile boolean flag = false;
    /**
     * 活动节点里面的字体
     */
    protected String activitiFontName = "宋体";
    /**
     * 标签字体
     */
    protected String labelFontName = "宋体";
    /**
     * 注释字体
     */
    protected String annotationFontName = "宋体";

    /**
     * 图形
     *
     * @param width              宽度
     * @param height             高度
     * @param minX               x轴
     * @param minY               y轴
     * @param imageType          图片类型
     * @param activityFontName   节点字体
     * @param labelFontName      标签字体
     * @param annotationFontName 注释字体
     * @param customClassLoader  自定义加载类
     */
    public DiyProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
    }

    /**
     * 图形
     *
     * @param width     宽度
     * @param height    高度
     * @param minX      x轴
     * @param minY      y轴
     * @param imageType 图片类型
     */
    public DiyProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType) {
        super(width, height, minX, minY, imageType);
    }

    /**
     * 重写里面的drawHighLight方法，增加Color参数
     *
     * @param x      x轴
     * @param y      y轴
     * @param width  宽度
     * @param height 高度
     * @param color  颜色
     */
    public void drawHighLight(boolean isStartOrEnd, int x, int y, int width, int height, Color color) {

        Paint paint = g.getPaint();
        Stroke stroke = g.getStroke();

        g.setPaint(color);
        g.setStroke(MULTI_INSTANCE_STROKE);

        if (isStartOrEnd) {
            g.drawOval(x, y, width, height);
        } else {
            RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 5, 5);
            g.draw(rect);
        }

        g.setPaint(paint);
        g.setStroke(stroke);
    }

    /**
     * 画线
     *
     * @param xPoints     x轴
     * @param yPoints     y轴
     * @param conditional 情况
     * @param isDefault   是否默认
     * @param highLighted 高亮
     * @param scaleFactor
     * @param color       颜色
     */
    public void drawSequenceflow(int[] xPoints, int[] yPoints, boolean conditional, boolean isDefault,
                                 boolean highLighted, double scaleFactor, Color color) {
        drawConnection(xPoints, yPoints, conditional, isDefault, "sequenceFlow", AssociationDirection.ONE, highLighted,
                scaleFactor, color);
    }

    /**
     * 画连接
     *
     * @param xPoints              x轴
     * @param yPoints              y轴
     * @param conditional          情况
     * @param isDefault            是否默认
     * @param highLighted          高亮
     * @param scaleFactor
     * @param associationDirection 连接描述
     * @param connectionType       连接类型
     * @param color                颜色
     */
    public void drawConnection(int[] xPoints, int[] yPoints, boolean conditional, boolean isDefault,
                               String connectionType, AssociationDirection associationDirection, boolean highLighted, double scaleFactor,
                               Color color) {

        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();

        g.setPaint(CONNECTION_COLOR);
        if ("association".equals(connectionType)) {
            g.setStroke(ASSOCIATION_STROKE);
        } else if (highLighted) {
            g.setPaint(color);
            g.setStroke(HIGHLIGHT_FLOW_STROKE);
        }

        for (int i = 1; i < xPoints.length; i++) {
            Integer sourceX = xPoints[i - 1];
            Integer sourceY = yPoints[i - 1];
            Integer targetX = xPoints[i];
            Integer targetY = yPoints[i];
            Line2D.Double line = new Line2D.Double(sourceX, sourceY, targetX, targetY);
            g.draw(line);
        }

        if (isDefault) {
            Line2D.Double line = new Line2D.Double(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
            drawDefaultSequenceFlowIndicator(line, scaleFactor);
        }

        if (conditional) {
            Line2D.Double line = new Line2D.Double(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
            drawConditionalSequenceFlowIndicator(line, scaleFactor);
        }

        if (associationDirection.equals(AssociationDirection.ONE)
                || associationDirection.equals(AssociationDirection.BOTH)) {
            Line2D.Double line = new Line2D.Double(xPoints[xPoints.length - 2], yPoints[xPoints.length - 2],
                    xPoints[xPoints.length - 1], yPoints[xPoints.length - 1]);
            drawArrowHead(line, scaleFactor);
        }
        if (associationDirection.equals(AssociationDirection.BOTH)) {
            Line2D.Double line = new Line2D.Double(xPoints[1], yPoints[1], xPoints[0], yPoints[0]);
            drawArrowHead(line, scaleFactor);
        }
        g.setPaint(originalPaint);
        g.setStroke(originalStroke);
    }

    /**
     * 画标签
     *
     * @param highLighted 高亮的节点
     * @param text        字
     * @param graphicInfo 绘图信息
     * @param centered    中心
     */
    public void drawLabel(boolean highLighted, String text, GraphicInfo graphicInfo, boolean centered) {
        float interline = 1.0f;

        // text
        if (text != null && text.length() > 0) {
            Paint originalPaint = g.getPaint();
            Font originalFont = g.getFont();
            if (highLighted) {
                g.setPaint(WorkflowConstants.COLOR_NORMAL);
            } else {
                g.setPaint(LABEL_COLOR);
            }
            g.setFont(new Font(labelFontName, Font.BOLD, 10));

            int wrapWidth = 100;
            int textY = (int) graphicInfo.getY();

            // TODO: use drawMultilineText()
            AttributedString as = new AttributedString(text);
            as.addAttribute(TextAttribute.FOREGROUND, g.getPaint());
            as.addAttribute(TextAttribute.FONT, g.getFont());
            AttributedCharacterIterator aci = as.getIterator();
            FontRenderContext frc = new FontRenderContext(null, true, false);
            LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);

            while (lbm.getPosition() < text.length()) {
                TextLayout tl = lbm.nextLayout(wrapWidth);
                textY += tl.getAscent();
                Rectangle2D bb = tl.getBounds();
                double tX = graphicInfo.getX();
                if (centered) {
                    tX += (int) (graphicInfo.getWidth() / 2 - bb.getWidth() / 2);
                }
                tl.draw(g, (float) tX, textY);
                textY += tl.getDescent() + tl.getLeading() + (interline - 1.0f) * tl.getAscent();
            }

            // restore originals
            g.setFont(originalFont);
            g.setPaint(originalPaint);
        }
    }

    @Override
    public BufferedImage generateBufferedImage(String imageType) {
        if (closed) {
            throw new ActivitiImageException("ProcessDiagramGenerator already closed");
        }

        // Try to remove white space
        minX = (minX <= WorkflowConstants.PROCESS_PADDING) ? WorkflowConstants.PROCESS_PADDING : minX;
        minY = (minY <= WorkflowConstants.PROCESS_PADDING) ? WorkflowConstants.PROCESS_PADDING : minY;
        BufferedImage imageToSerialize = processDiagram;
        if (minX >= 0 && minY >= 0) {
            imageToSerialize = processDiagram.getSubimage(
                    minX - WorkflowConstants.PROCESS_PADDING,
                    minY - WorkflowConstants.PROCESS_PADDING,
                    canvasWidth - minX + WorkflowConstants.PROCESS_PADDING,
                    canvasHeight - minY + WorkflowConstants.PROCESS_PADDING);
        }
        return imageToSerialize;
    }

    @Override
    public void initialize(String imageType) {
        this.processDiagram = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        this.g = processDiagram.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(Color.black);

        Font font = new Font(activityFontName, Font.BOLD, FONT_SIZE);
        g.setFont(font);
        this.fontMetrics = g.getFontMetrics();

        LABEL_FONT = new Font(labelFontName, Font.ITALIC, 10);
        ANNOTATION_FONT = new Font(annotationFontName, Font.PLAIN, FONT_SIZE);
        //优化加载速度
        if (flag) {
            return;
        }
        try {
            USERTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/userTask.png", customClassLoader));
            SCRIPTTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/scriptTask.png", customClassLoader));
            SERVICETASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/serviceTask.png", customClassLoader));
            RECEIVETASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/receiveTask.png", customClassLoader));
            SENDTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/sendTask.png", customClassLoader));
            MANUALTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/manualTask.png", customClassLoader));
            BUSINESS_RULE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/businessRuleTask.png", customClassLoader));
            SHELL_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/shellTask.png", customClassLoader));
            CAMEL_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/camelTask.png", customClassLoader));
            MULE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/muleTask.png", customClassLoader));

            TIMER_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/timer.png", customClassLoader));
            COMPENSATE_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/compensate-throw.png", customClassLoader));
            COMPENSATE_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/compensate.png", customClassLoader));
            ERROR_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/error-throw.png", customClassLoader));
            ERROR_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/error.png", customClassLoader));
            MESSAGE_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/message-throw.png", customClassLoader));
            MESSAGE_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/message.png", customClassLoader));
            SIGNAL_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/signal-throw.png", customClassLoader));
            SIGNAL_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/signal.png", customClassLoader));
            flag = true;
        } catch (IOException e) {
            flag = false;
            LOGGER.warn("Could not load image for process diagram creation: {}", e.getMessage());
        }
    }
}
