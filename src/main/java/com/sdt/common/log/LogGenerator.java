package com.sdt.common.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.constant.DatePattern;
import com.sdt.common.utils.CommonUtils;
import com.sdt.common.utils.JsonObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志生成
 *
 * @author shihaoran
 * @date 2020/3/9
 */
public class LogGenerator {

    private static Logger LOGGER = LoggerFactory.getLogger(LogGenerator.class);

    private static JsonObjectMapper mapper = new JsonObjectMapper();

    public static void getLog(String remoteAddr, Long userId, String action, boolean result, Object info) throws JsonProcessingException {
        LOGGER.info(mapper.writeValueAsString(new LogObj(action, userId, CommonUtils.getCurrentTime(DatePattern.DATE_ALL), remoteAddr, result, "执行方法 : " + info)));
    }
}
