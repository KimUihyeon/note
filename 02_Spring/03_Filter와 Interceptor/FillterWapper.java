package com.springtest.ijs.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class FillterWapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params;

    @SuppressWarnings("unchecked")
    public FillterWapper(HttpServletRequest request) {
        super(request);
        this.params = new HashMap<String, String[]>(request.getParameterMap()); // 기존 Request에 있는 데이터를 그대로 밀어넣어줌
    }

    public String getParameter(String name) {

        String returnValue = null;

        String[] paramArray = getParameterValues(name);

        if (paramArray != null && paramArray.length > 0) {

            returnValue = paramArray[0];

        }

        return returnValue;
    }

    public Map<String, String[]> getParameterMap() {

        return Collections.unmodifiableMap(params);
    }


    public Enumeration<String> getParameterNames() {

        return Collections.enumeration(params.keySet());

    }




    // params Setter.
    // HttpServletRequestWrapper 와 무관한 유틸함수.
    public void setParameter(String name, String value) {
        String[] oneParam = { (String) value};
        setParameter(name, oneParam);

    }
    
    // params Setter.
    // HttpServletRequestWrapper 와 무관한 유틸함수.
    public void setParameter(String name, String[] value) {
        params.put(name, value);
    }

}
