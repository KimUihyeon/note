# title

## 목차
1. :star2: Sptring filter와 Interceptor의 차이
1. :star2: 필터를 만들어보자!  (Contoller가 파라미터를 받는 원리)
1. :star2: Interceptor를 만들어보자

<br>
<br>
<hr>


### Sptring filter와 Interceptor의 차이

![](./asset/0_SPTRING_.FILTER_INTERCEPTOR.jpg)

크게보면 `filter`는 DispatherServlet이 들어오고 나가고 전후에 처리해주고 `Interceptor`는 컨트롤러에 들어가기 직전, 컨트롤러에서 뷰로 가기 직전, 뷰를 랜더링하고 직후에 데이터를 취할수있다.  따라서 뭔가 `전역으로 처리해야할일이 있으면 Filter`가 맞고,  `디테일한 처리가 필요한 부분은 인터셉터`로 구현하는것이 맞다. 실제 라이프 사이클은  

`filter Init()` - `filter doFilter() canin.doFilter() 앞 로직 ` - `Interceptor preHandle()` - `Controller` - `Interceptor postHandle()` - `화면랜더링` - `Interceptor afterCompletion()` - `filter doFilter() canin.doFilter() 뒷 로직 ` - `filter distroy()` 

순으로 처리된다.



<br>
<br>
<hr>


### 필터를 만들어보자.


1. 필터가 될 클래스에 `Filter` Implements 해준다 . ( 예제 | MyFilter )

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

        myFillter.setParameter("test","1"); // 원하는 데이터를 담아주자
        myFillter.setParameter("test2","2"); // 원하는 데이터를 담아주자

        filterChain.doFilter(myFillter, servletResponse);
    }


    public void destroy() {
        // 죽을때 실행됨

    }
}
```

<br>


2. `doFilter` 메소드 구현하고 filterChain.doFilter(HttpServletRequest , servletResponse) 이렇게해서 servletRequest에 실어 보내면됨. `FillterWapper` 클래스는 `HttpServletRequestWrapper`을 상속받아 만든 클래스이다. Controller의 원리는 예시를 들어 Controller에서 Map 타입의 자료를 파라미터로 받고있다면, `Spring 내부적으로 getParameterMap이 콜되는 형식`으로 되어있다. Map타입의 자료를 파라미터로 쓰고있다면 `getParameterMap`을 재정의 해주자. Spring 버전 별로 Map<String,String[]> 이거나 Map<String, Object>인지 확인해야한다.  ( 예제 | MyFilter에 FillterWapper 부분)

```
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
        this.params = new HashMap<String, String[]>(request.getParameterMap());  // 기존  request 에 담긴 정보를 그대로 밀어 넣어줌 
    }

    public String getParameter(String name) {

        String returnValue = null;

        String[] paramArray = getParameterValues(name);

        if (paramArray != null && paramArray.length > 0) {

            returnValue = paramArray[0];

        }

        return returnValue;
    }

    public Map<String, String[]> getParameterMap() {  // 이부분 중요함 

        return Collections.unmodifiableMap(params);
    }


    public Enumeration<String> getParameterNames() {

        return Collections.enumeration(params.keySet());

    }



    public void setParameter(String name, String value) {

        String[] oneParam = { (String) value};

        setParameter(name, oneParam);

    }

    public void setParameter(String name, String[] value) {
        params.put(name, value);
    }

}

```


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


<br>
<br>
<hr>