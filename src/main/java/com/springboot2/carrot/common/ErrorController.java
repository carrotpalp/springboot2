package com.springboot2.carrot.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ErrorController extends AbstractErrorController {

    @Autowired
    ObjectMapper objectMapper;

    public ErrorController() {
        super(new DefaultErrorAttributes());
    }

    @Override
    public String getErrorPath() {

        return null;
    }

    @RequestMapping("/error")
    public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
        Throwable cause = getCause(request);
        Integer status = (Integer)model.get("status");
        String message = (String)model.get("message");
        String errorMessage = getErrorMessage(cause);
        response.setStatus(status);
        if (!isJsonRequest(request)){
            ModelAndView view = new ModelAndView("/error.btl");
            view.addAllObjects(model);
            view.addObject("errorMessage",errorMessage);
            view.addObject("status",status);
            view.addObject("cause",cause);
            return view;
        } else {
            Map error = new HashMap();
            error.put("success",false);
            error.put("errorMessage",errorMessage);
            error.put("message",message);
            try {
                objectMapper.writeValueAsString(error);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    protected Throwable getCause(HttpServletRequest request){
        Throwable error = (Throwable)request.getAttribute("javax.servlet.error.exception");
        if(error != null){
            while (error instanceof ServletException && error.getCause() != null){
                error = ((ServletException) error).getCause();
            }
        }
        return error;
    }

    protected String getErrorMessage(Throwable ex){
        return "服务器错误，请联系洋哥！";
    }

    protected boolean isJsonRequest(HttpServletRequest request){
        String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri != null && requestUri.endsWith(".json")){
            return true;
        } else {
//            request.getHeader("Accept").contains("application/json");
            return false;
        }

    }

}
