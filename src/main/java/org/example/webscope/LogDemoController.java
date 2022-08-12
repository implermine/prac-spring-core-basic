package org.example.webscope;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLoggerWithProxy myLoggerWithProxy;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){


        String requestURL = request.getRequestURL().toString();

        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }

    @RequestMapping("log-demo-proxy")
    @ResponseBody
    public String logDemoWithProxy(HttpServletRequest request){


        String requestURL = request.getRequestURL().toString();

        myLoggerWithProxy.setRequestURL(requestURL);

        myLoggerWithProxy.log("controller test");
        logDemoService.logic4proxy("testId");

        return "OK";
    }
}
