package com.wfm.servicecommon.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wfm.servicecommon.vo.ClientInfo;
import com.wfm.servicecommon.vo.DeviceInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户客户端信息工具类
 * description: ClientInfoUtil
 * date: 2019-12-23 11:02
 *
 * @author: wfm
 * @version: 1.0
 */
public class ClientInfoUtil {

    private static Pattern USER_AGENT = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?Build/(\\S*?)[;)]");

    private static Pattern USER_AGENT_2= Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?\\)");

    /**
     * 获取用户客户端信息
     * @param request
     * @return
     */
    public static ClientInfo get(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        return get(userAgent);
    }

    /**
     * 获取用户客户端信息
     * @param userAgentString
     * @return
     */
    public static ClientInfo get(String userAgentString){
        ClientInfo clientInfo = new ClientInfo();

        UserAgent userAgent = UserAgentUtil.parse(userAgentString);

        // 浏览器名称
        clientInfo.setBrowserName(userAgent.getBrowser().getName());
        // 浏览器版本
        clientInfo.setBrowserversion(userAgent.getVersion());
        // 浏览器引擎名称
        clientInfo.setEngineName(userAgent.getEngine().getName());
        // 浏览器引擎版本
        clientInfo.setEngineVersion(userAgent.getEngineVersion());
        // 用户操作系统名称
        clientInfo.setOsName(userAgent.getOs().getName());
        // 用户操作平台名称
        clientInfo.setPlatformName(userAgent.getPlatform().getName());
        // 是否是手机
        clientInfo.setMobile(userAgent.isMobile());

        // 获取移动设备名称和机型
        DeviceInfo deviceInfo = getDeviceInfo(userAgentString);
        // 设置移动设备名称和机型
        clientInfo.setDeviceName(deviceInfo.getName());
        clientInfo.setDeviceModel(deviceInfo.getModel());

        // ip
        clientInfo.setIp(IpUtil.getRequestIp());

        return clientInfo;
    }

    /**
     * 获取移动端用户设备的名称和机型
     * @param userAgentString
     * @return
     */
    public static DeviceInfo getDeviceInfo(String userAgentString){
        DeviceInfo deviceInfo = new DeviceInfo();
        try {
            // Pattern pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?Build/(\\S*?)[;)]");
            Matcher matcher = USER_AGENT.matcher(userAgentString);
            String model = null;
            String name = null;

            if (matcher.find()) {
                model = matcher.group(1);
                name = matcher.group(2);
            }

            if (model == null && name == null){
                // pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?\\)");
                matcher = USER_AGENT_2.matcher(userAgentString);
                if (matcher.find()) {
                    model = matcher.group(1);
                }
            }

            deviceInfo.setName(name);
            deviceInfo.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceInfo;
    }
}
