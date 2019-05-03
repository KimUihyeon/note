# title

## 목차
1. :star2: 필터를 만들어보자!  (Contoller가 파라미터를 받는 원리)

<br>
<br>
<hr>


### 필터를 만들어보자.


1. 필터가 될 클래스에 `Filter` Implements 해준다 .
```

public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 될때 실행됨

    }


    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) 
                         throws IOException, ServletException {

        FillterWapper myFillter 
                        = new FillterWapper( (HttpServletRequest)servletRequest );

        myFillter.setParameter("test","1");
        myFillter.setParameter("test2","2");

        filterChain.doFilter(myFillter, servletResponse);
    }


    public void destroy() {
        // 죽을때 실행됨

    }
}
```

<br>


2. `doFilter` 메소드 구현하고 filterChain.doFilter(HttpServletRequest , servletResponse) 이렇게해서 servletRequest에 실어 보내면됨. `FillterWapper` 클래스는 `HttpServletRequestWrapper`을 상속받아 만든 클래스이다. Controller의 원리는 예시를 들어 Controller에서 Map 타입의 자료를 파라미터로 받고있다면, `Spring 내부적으로 getParameterMap이 콜되는 형식`으로 되어있다. Map타입의 자료를 파라미터로 쓰고있다면 `getParameterMap`을 재정의 해주자. Spring 버전 별로 Map<String,String[]> 이거나 Map<String, Object>인지 확인해야한다. 


3. WEB.xml filter Setting 


```
    <!--필터 셋팅 -->
    <!--
        1. 필터 생성 <filter>
            filter-name : 사용자 임의 지정
            filter-calss : 필터 클래스 (패키지명.클래스)
        2. 필터 맵핑 <filter-mapping>
            filter-name : 위에서 지정한 필터네임
            url-pattern : 필터가 적용될 URL 패턴   
    -->
    <filter>
        <filter-name>MyFilter</filter-name>
        <filter-class>com.springtest.ijs.config.MyFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>MyFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```


4. 컨트롤러 파라미터로 받을때 @RequestParam Map<String, Obejct> req 로 받으면됨.