package com.sdt.common.listener;

import com.sdt.common.constant.DatePattern;
import com.sdt.common.utils.CommonUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

/**
 * 在对象进行新增、更新等持久化操作时，为对象的指定属性赋值
 *
 * @author lianghaoran
 */
public class EimsBaseBeanAuditListener {

    /**
     * 在新增对象之前，通过反射对uuid、createTime、delete字段赋值
     *
     * @param object 待赋值的对象
     */
    @PrePersist
    protected void prePersist(Object object) {
        try {
            BeanUtils.setProperty(object, "uuid", UUID.randomUUID().toString());
            BeanUtils.setProperty(object, "createTime", CommonUtils.getCurrentTime(DatePattern.DATE_ALL));
            BeanUtils.setProperty(object, "deleted", false);
        } catch (Exception e) {
            throw new RuntimeException("保存对象时，为对象赋值失败！");
        }
    }

    /**
     * 在更新对象之前，通过反射updateTime字段赋值
     *
     * @param object 待赋值的对象
     */
    @PreUpdate
    protected void preUpdate(Object object) {
        try {
            BeanUtils.setProperty(object, "updateTime", CommonUtils.getCurrentTime(DatePattern.DATE_ALL));
        } catch (Exception e) {
            throw new RuntimeException("更新对象时，为对象赋值失败！");
        }
    }
}
