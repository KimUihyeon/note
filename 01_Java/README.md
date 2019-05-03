# title

## 목차
1. Java Version별 이슈
1. :star2: Clean Code
1. 개발하면서 이슈사항

    3-1. Vue js

    3-2. hibernate myBatis collaboration


<br>
<br>
<hr>

### Java Version 별 이슈사항

1) 초기 자바 ~ JDK 1.0

- 1991년, OAK 발표: GE사의 요청으로, 썬마이크로 시스템즈에서 C++의 단점을 극복하고자 만든 언어.

  메모리 할당/해제의 어려움과 다중상속으로 인한 실수유발을 극복하려고 했다.

- 1996년, JDK 1.0발표 :  언어 이름을 자바라 바꾸고, Java Virtual Machine 1.0을 발표했다. Java Applet도 이때 처음 도입.


<br>

2) JDK1.1

- 1997년 : RMI, JDBC, reflection, JIT,Inner Class 개념이 포함되었다.



<br>
3) JDK 1.2

- 1998년 Java SE 1.2, ME 1.2, EE 1.2 발표 

- 자바를 세가지 버전으로 나눴다. Swing이 SE에 포함, Corba IDL(이종기기간 함수호출 스펙), Collection Framework 포함


<br>

4) JDK 1.3 

- 2000년도

- HotSpot(Sun에서 만든 JIT구현), JNDI(디렉토리랑 이름으로 원하는 서비스찾는것) 포함



<br>
5) JDK 1.4 

- 2002년도

- JCP(Java Community Process)에 의해서 오픈소스 정책으로 자바가 관리되기 시작한 버전.

- Java 2 Security 모델의 확립(Sandbox)

- Java Web Start포함 (Java Applet이 브라우저에서 돌아가는 것과 다르게, 외부 sandbox에서 동작)

- Language: assert 도입

- API :  Regular Expression, Assert keyword, Security 2 version(현재 security model), Non Blocking IO(NIO)


<br>

6) JDK 1.5

- 2004년

- 기능적으로 가장 많은 변화가 생긴버전 (Generics가 가장 대표적)

- LanguageI: Generics , annotation, auto boxing, enum,vararg ,foreach, static imports 도입

- API : java.util.concurrent API, scanner class


<br>

7) JDK 1.6

- 2006년도

- 기능에 별 차이 없슴 - 보안, 성능강화 주력. 

- JVM/Swing에 있어 많은 Performance 향상(synchronization, compiler, GC,start-up time)

- G1(Garbage First) GC도입.

<br>


8) JDK 1.7

- 2011 

- JVM : Dynamic Language support (invokedynamic - new byte operation)

- Language : `Switch에서 String, try-resource, generics에서 타입추론, 숫자에서 underscore사용`

- API:Concurrency 강화, `NIO 강화`, sort강화, crypto강화, GPU강화, 

- `JavaFX`가 기본으로 포함

- 안정적인 ARM지원


<br>

9) JDK 1.8

- 2014

- 오라클로 인수된 후 첫번째 버전

-  JDK 1.5이후 가장 큰 언어적 변화`(Lambda및 함수형프로그래밍,default method)이며 러닝커브가 크다.`

- JEP에 의해서 새로운 기능들이 발의되기 시작.

- Language : Lambda expression, Default Method Interface, functional programming for MapReduce style 지원, default method이용한 다중상속지원,메소드 참조

- API : Nashorn (JS엔진), new Date and Time API, stream api,Collection에 대한 함수형화 (Interface에 default가 생김으로서 가능)

- `병철처리에 접합한 구조로 진화 (추후 공부할 것)`

<br>

10) JDK 1.9

- 2016 예정

- Modular System (Jigsaw)지원예정

- Money API지원예정

- Java Shell지원예정

- 변수에 대한 타입 추론 지원예정(var,val)

- OpenCL이용한 자동화된 병렬 프로그래밍 지원예정

- value 타입 지원예정



<br>
<br>
<hr>

### Clean Code의 원칙

`OJT` 와 `clean code <로버트 C 마틴 저서>에서 발췌`


* 네이밍 규칙 

    1. 

* 구조설계

    1. UI 객체 / 운용데이터 / DB데이터를 분리하라.

        * 파일디비 (jsonFile)을 사용할때에 문제점.

        jsonFileDB에 저장될 객체에 UI에 객체를 바로 물도록 하지 않는 것이 좋다. 실제 진행했던 프로젝트를 사례로 들어서 이야기해보자.

        첫번째로, 파일디비를 사용하는 프로젝트에서 운용데이터에 UI객체를 물고 있도록 개발한 사례가 있다. 일단 직렬화 하였을때 UI객체 때문에 파일사이즈가 방대해지고, UI쓰레드를 적용시키더라도 데이터자체의 `쓰레드나 병렬처리`를 하기 어려웠다. 파일사이즈의 경우 JsonIgnore로 해결했지만 병렬 처리의 경우 결국 해결하지 못했다.

        두번째로, DB 데이터를 운용데이터가 물고있는 경우가 있었는데, 이럴경우 저장을 하면 DB 데이터가 같이 객체 타입으로 직렬화 되서 저장되게 된다. 이후 DB Master Data를 변경할경우 내가 이전에 `직렬화한 데이터와 현재 DB데이터가 서로 다르기 때문에 동기화 작업`을 해주어야 했다. 또 스키마가 변경 될 경우 스키마에 맞게 값을 채워주는 Converting로직도 필요했다.

        세번쨰로, 운용데이터를 통째로 직렬화하여 DB데이터에 한 컬럼에 몰아 넣은 프로젝트가 있었는데, 데이터가 9MB에 달하는 데이터를 한 컬럼에 몰아 넣고 C# Entity로 관리하니 `Application Memory가 2기가까지 치솟은 문제`가 발생한 적 있다.

        결론은 `운용데이터를 파일 디비로 저장할때는` UI객체를 바로 물지 말고 UI객체의 몇가지 속성만 저장하고 Project load시에 UI객체를 복원하는 식으로 개발하는 것 이 좋다. 또 DB 데이터를 물고있어야 할 경우 DB의 Key값만 저장하고 Key 객체가 필요한 시점에 DB에서 값을 뺴오는 식으로 개발 하는 것 이 좋다.

        종합해보면 `UI 객체 / 운용데이터 / DB데이터`는 무조건 분리하는게 좋다.


* 주석

    * 좋은주석
        1. 코드로 의도를 표현해라.

        ```
        /// 직원에게 복지 해택을 받을 자격이 있는지 검사.
        if( (imployee != null & employee.flags  ) ){
        }
        ```
        2. 의도를 설명하는 주석 
        3. 작성자를 명시하는 주석

    * 나쁜주석
        1. 주저리 주저리 적은 주석
        2. 있으나 마나한 주석

        ```
        코드가 최고의 주석이다. 코드로 커버가 불가능한 부분만 주석을 작성해라.
        ```

        3. 클래스의 획일성 떄문에 모두 주석을 다는 행위


* 함수

    1. 작게 만들어라
    
        * 한가지 기능만 하는 함수를 만들것

        ```
        안좋은 예.
        
        public Object getData(Obejct paramData1,Obejct paramData2){
            if( paramData1 == null ){
                return null; 
            }
            else {
                // 데이터 처리 로직
            }
        }


        좋은 예
        
        public Object getData(Obejct paramData1,Obejct paramData2){
            // 데이터 처리 로직
        }
        ```

        하나의 함수가 한가지일만 하도록 세분화 하여라. 자주 범하는 실수가 데이터처리 로직이나 계산로직 안쪽에서 값을 검증하는 행위를 한다. 값을 검증하는 행위는 비즈니스로직에서 처리하고 데이터를 가져오거나 계산하는 로직에서는 `본연의 임무에 충실하는 것` 이 바람직하다. 이렇게 한다면 자연스럽게 함수는 작아질 것이다. (`clean Code 발췌`)

    2. 함수 파라미터는 0개, 1개, 2개를 사용해라.

        함수파라미터는 될수있으면 0개, 1개, 2개로 사용하라. 0개가 바람직하고 1개는 적합, 2개까진 쓸수있지만 3개 4개는 쓰지않는것이 좋다. 경험삼 3개 이상부터는 같은 클래스형의 자료형이 들어갈경우 순서로 햇갈리기때문에 높은 퀄리티의 개발을 하기 어려웠다. (`clean Code 발췌`)

        ```
        안좋은 예.
        public void drawRactangle(double x, double y, double w, double h)
        
        좋은 예.
        public void drawRactangle(Point point, Point size)

        ```

        `실제 개발하면서 겪었던 사례로는 아래와 같다. C# WPF`

        ```
        안좋은 예.
        public void ReRenderCanvas(byte[] MapData, double scaleSize, 
            List<PolygonWrapper> densities 
            ,List<PolyLineWrapper> disablePipe
            , List<PolyLineWrapper> activePipe
            , List<ShapeElement> relesePoints
            , List<PipeItemWrapper> disablePipeItem
            , PipeItemWrapper activePipeItem
            , List<PolygonWrapper> risks
            , CenterPointWrapper centerLineCtrl
            , List<PolygonWrapper> heaterRadius)

        
        좋은 예.
        
        (byte[] MapData, double scaleSize)를 MapBackgroundInfo 로 Wapping.
        (List<PolyLineWrapper> .. ) 집합을 MapShapePackage로 Wapping.
        
        public void ReRenderCanvas(MapBackgroundInfo mapBackgroundInfo
                                    MapShapePackage mapShapePackage)

        ```

        ![123123](https://github.com/KimUihyeon/note/blob/master/assets/image/C#_badCodingStyle-1.jpg)
        ![123123](https://github.com/KimUihyeon/note/blob/master/assets/image/C#_badCodingStyle-2.jpg)

        실제 제작했던 WPF Canvas ReRendering Function, 비슷한 유형의 인자가 너무 많아서 구별하기 쉽지 않다. 쓰기도 쉽지 않고, 내부적으로 구현하기도 쉽지 않았다. 



<br>
<br>
<hr>

### 개발하면서 이슈사항



