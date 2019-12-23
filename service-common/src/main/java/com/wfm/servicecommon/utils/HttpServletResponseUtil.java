package com.wfm.servicecommon.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * description: HttpServletResponseUtil
 * date: 2019-12-21 15:42
 *
 * @author: wfm
 * @version: 1.0
 */
public final class HttpServletResponseUtil {

    private static String UTF8 = "UTF-8";
    private static String CONTENT_TYPE = "application/json";

    private HttpServletResponseUtil(){
        throw new AssertionError();
    }

    public static void printJSON(HttpServletResponse response, Object object) throws Exception{
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JacksonUtil.BeanToJson(object));
        printWriter.flush();
        printWriter.close();
    }
}
