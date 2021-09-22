package com.isbein.cloud.common.basic.rpc;

import hprose.server.HproseServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;

@Slf4j
public class BootHproseServlet extends HproseServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        super.processRequest(request, response);
        String requestURI = request.getRequestURI();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">>>>>>>>>>> 收到请求 >>>>>>>>>>>>>>\n").append("RequestURI: ").append(requestURI).append("\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        stringBuilder.append(">>>> Headers: ").append("\n");
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            stringBuilder.append(name).append(": ").append(header).append("\n");
        }
        stringBuilder.append(">>>> Params: ").append("\n");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            String parameter = request.getParameter(name);
            stringBuilder.append(name).append(": ").append(parameter).append("\n");
        }

        //响应
        stringBuilder.append(">>>> Response: ").append("\n");
        int status = response.getStatus();
        stringBuilder.append("Status Code: ").append(status).append("\n");
        Collection<String> responseHeaderNames = response.getHeaderNames();
        stringBuilder.append(">> Headers: ").append("\n");
        for (String responseHeaderName : responseHeaderNames) {
            String header = response.getHeader(responseHeaderName);
            stringBuilder.append(responseHeaderName).append(": ").append(header).append("\n");
        }
        stringBuilder.append("<<<<<<<<<<<< End <<<<<<<<<<<<<<<").append("\n");
        log.trace(stringBuilder.toString());
    }
}
