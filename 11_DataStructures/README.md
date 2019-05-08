# Data Structures

## 목차
1. :leaves: 선형구조 [link](#ArrayList)
    1. 일반 리스트 (Java->ArrayList | C# ->List) [link](#ArrayList) 
    1. 이중 연결리스트 (double Linked List)  [link](#doubleLinkedList) 
    1. 큐 (Queue)  [link](#Queue) 
    1. 스텍 (stack)  [link](#Stack) 
1. :dizzy: 비선형구조 [link](#Tree)
    1. Tree [link](#Tree) 
    1. Graph [link](#Graph) 
1. :skull: 탐색 [link](#MailService)
1. :sunny: 정렬 [link](#MailService)

<br>
<br>

<hr>

### ArrayList

[openJDK Link](#https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) | [sourceCode](#) | [자세히 보기](#) 
    
ArrayList 내부적으로 size 10의 배열을 Defualt로 생성해서사용하고 추가될 데이터의 인덱스가 현재 배열의 사이즈보다 큰 경우배열을 Growth factor(a) 만큼 곱한값으로 새 배열의 크기를지정하고 기존값을 복사해 채워넣는다.  Growth factor(성장인자)a는 다음표와 같다.



| Implementation |  Growth factor (a) |
|:---------------|:-------------------|
|Java ArrayList  |	1.5 (3/2)         |
|Python PyListObject| ~1.125 (n + n >> 3)|
|Microsoft Visual C++ 2013|	1.5 (3/2)|
|G++ 5.2.0                |	2         |
|Clang 3.6                |	2         |
|Facebook folly/FBVector  |	1.5 (3/2) |

`표 출처 Dynamic Array Wikipedia [link](https://en.wikipedia.org/wiki/Dynamic_array)`[link](https://en.wikipedia.org/wiki/Dynamic_array)


그러므로 내부적인 사이즈는 10-> 15-> 22 ... 순으로 1.5  배씩커지게 되어있다. 따라서 많은 양의 데이터를 삽입하는 경우는 add로반복문을 돌리는것보단 addAll을 사용하는 것이 내부적으로 값복사가 한번만 일어나기 때문에 효율성이 좋다.

ArrayList의 최대 사이즈는 Integer.MAX_VALUE (0x7fffffff)를넘을수 없고, OS나 IDE에서 할당한 최대 메모리까지만 허용가능하다.


`스터디 구현 Interface `

* Iterator  [jdk8 docs link](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html) | [link](https://github.com/KimUihyeon/DataStructures/blob/master/List/studyInterface/Iterator.java)
    
    * hasNext();
    * next();
    * remove();  | 미구현

* List [jdk8 docs link](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) | [link](https://github.com/KimUihyeon/DataStructures/blob/master/List/studyInterface/List.java)

    * add();
    * addAll();
    * get();
    * contain();
    * indexOf();
    * remove();
    * size();
    * clear();

<br>
<br>

<hr>

### Double Linked List

[sourceCode](#) | [자세히 보기](#) 

List는 head, tail을 가지고 있고 각 Node는 nextNode와 prevNode를 기억하고 있다. 서로 연결구조로 되어있기 때문에 메모리 사용량이 ArrayList보다 적고 서로 add시 불필요한 작업을 하지 않는다. 일반 LinkedList보다 좋은점은 탐색,삽입시 반절만 검색해서 Indexing 하기때문에 O(n/2) 로 두배가량 빠르다.


`스터디 구현 Interface `

* add(E data); // 맨뒤 삽입
* add(index ,E data) // 해당 인덱스에 삽입
* remove(index) // 해당 데이터 삭제
* remove(E data) // 해당 인덱스 삭제
* clear() // 해당 인덱스 삭제
* indexOf(index) // 해당 인덱스 반환
* contain() // 값이 있는지 boolean 리턴
* toArray() // Array 로 변환



<br>
<br>

<hr>


### Queue

[openJDK Link](#https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html) | [sourceCode](#) | [자세히 보기](#) 

선입선출  first-in-first-out (FIFO) 방식으로 데이터를 운용하고 일반 Que의 경우 배열의 길이를 고정으로 놓음, 동적 배열은 ArrayQue임

`스터디 구현 Interface `

* empty() // 빈값인지 체크
* peek()  // 재일 뒤의 값을 확인함
* pop()   // 재일 뒤의 값을 뽑아냄 (삭제하고 리턴)
* push(E data)  // 재일 뒤로 값을 넣음
* search(E data) // 값이 있는지 검색하고 인덱스를 리턴함 없으면 -1



<br>
<br>

<hr>

### Stack

[openJDK Link](#https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html) | [sourceCode](#) | [자세히 보기](#) 

후입선출  last-in-first-out (LIFO) 방식으로 데이터를 운용하고 일반 Stack의 경우 배열의 길이를 고정으로 놓음, 동적 배열은 ArrayStack임

`스터디 구현 Interface `

* empty() // 빈값인지 체크
* peek()  // 재일 앞의 값을 확인함
* pop()   // 재일 앞의 값을 뽑아냄 (삭제하고 리턴)
* push(E data)  // 재일 뒤로 값을 넣음
* search(E data) // 값이 있는지 검색하고 인덱스를 리턴함 없으면 -1

<br>
<br>
<hr>

### Tree

* 트리의 정의

자신과 같은 클래스 타입을 변수로 가지고 있고 아래와 같은 트리형태로 되어있는 자료구조. 노드[link](https://github.com/KimUihyeon/DataStructures/blob/master/Tree/MyTree/MyTreeNode.java)(POJO)클래스와 노드를 운용하는 Tree(Management Class)[link]()로 구분해서 구현한다. `힙정렬 혹은 학습을 위해 Binary Tree`가 일반적이지만 `실제협업에서는 노드클래스가 NodeCollection`을 가지고 있게 하는 구조로 많이 개발한다.

대표적인 트리의 종류로는 신장트리, 이진트리, 레드블랙트리.. 등이 있다.


트리를 순회를 `구현`할때에는 크게 `재귀(Recucive)`, `Stack`, `Queue`를 이용해서 구현할수있다. 재귀의 경우 구현은 쉽지만 해당 원하는 값을 검색해서 반환하는경우 반환하는 타이밍을 잡기 어렵고 디버깅을 하기 어렵기 떄문에 활용도가 떨어진다. 따라서 스텍과 큐를 이용해서 구현하는 것을 추천한다. `DFS는 Stack`으로, `BFS는 Queue`로 구현할 수 있다.

* 트리의 순회


1. DFS - 중위 탐색 (in-order) [sourceCode](https://github.com/KimUihyeon/DataStructures/blob/master/Tree/MyTree/MyTree.java#L24)
```
            1
         /     \
        2       3
      /   \   /   \
    4      5 6      7
```
`순회결과 || 4-> 2-> 5-> 1-> 6-> 3-> 7`

2. DFS - 전위 탐색 (in-order) [sourceCode](https://github.com/KimUihyeon/DataStructures/blob/master/Tree/MyTree/MyTree.java#L64)

```
            1
         /     \
        2       3
      /   \   /   \
    4      5 6      7
```
`순회결과 || 1-> 2-> 4-> 5-> 3-> 6-> 7`



3. DFS - 후위 탐색 (post-order) [sourceCode](https://github.com/KimUihyeon/DataStructures/blob/master/Tree/MyTree/MyTree.java#L78)

```
            1
         /     \
        2       3
      /   \   /   \
    4      5 6      7
```
`순회결과 || 1-> 2-> 3-> 4-> 5-> 6-> 7`



4. BFS - 계층 우선 탐색 (in-order) [sourceCode](https://github.com/KimUihyeon/DataStructures/blob/master/Tree/MyTree/MyTree.java#L112)

```
            1
         /     \
        2       3
      /   \   /   \
    4      5 6      7

```
`순회결과 || 1-> 2-> 3-> 4-> 5-> 6-> 7`


