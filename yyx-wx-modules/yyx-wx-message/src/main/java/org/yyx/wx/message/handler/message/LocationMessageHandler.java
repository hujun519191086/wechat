package org.yyx.wx.message.handler.message;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.LocationMessageRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.LocationMessageHandlerProxy;

/**
 * 地理位置消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:45
 */
public class LocationMessageHandler extends AbstractMessageHandler {
    /**
     * 创建对象
     */
    private static final LocationMessageHandler LOCATION_MESSAGE_HANDLER = new LocationMessageHandler();
    /**
     * TextMessageHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationMessageHandler.class);

    /**
     * 私有构造
     */
    private LocationMessageHandler() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static LocationMessageHandler getInstance() {
        return LOCATION_MESSAGE_HANDLER;
    }

    /**
     * 文本消息处理器
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后的消息
     */
    @Override
    protected BaseMessageResponse dealTask(Element element) {
        LOGGER.info("[地理位置消息处理器开始工作....]");
        LocationMessageRequest locationMessageRequest = this.modelMethod(element);
        return baseMessageHandlerProxy.dealMessage(locationMessageRequest);
    }

    /**
     * 获取地理位置消息处理器级别
     *
     * @return 地理位置消息处理器级别
     */
    @Override
    protected String getHandlerLevel() {
        return MessageTypeEnum.location.toString();
    }

    /**
     * 模板方法
     *
     * @param element 微信请求过来的消息:xml
     * @return xml转换之后的实体对象 有可能为空
     */
    @Override
    protected LocationMessageRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element.asXML().trim());
        LocationMessageRequest locationMessageRequest;
        try {
            locationMessageRequest = WxXmlAndObjectUtil.xmlToObject(element, LocationMessageRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return locationMessageRequest;
    }

    /**
     * 检查是否是自己的代理类
     *
     * @return true / false
     */
    @Override
    protected boolean isMineProxy(BaseMessageHandlerProxy baseMessageHandlerProxy) {
        if (baseMessageHandlerProxy instanceof LocationMessageHandlerProxy) {
            this.baseMessageHandlerProxy = baseMessageHandlerProxy;
            return true;
        }
        return false;
    }
}
