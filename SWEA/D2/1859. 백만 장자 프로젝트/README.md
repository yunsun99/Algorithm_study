# [D2] 백만 장자 프로젝트 - 1859 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc) 

### 성능 요약

메모리: 168,960 KB, 시간: 949 ms, 코드길이: 1,083 Bytes

### 제출 일자

2024-07-30 11:02

### 시간 소요

40분

### 특이 사항

처음에 시도했을 때는 시간초과가 발생했다.
ArrayList를 사용해서 전체 배열의 최고값을 찾고
최고값이 나온 날 이전까지는 전부 구매를 하고 최고값이 나온 날 판매를 하고
ArrayList에서 최고값이 나온 날까지의 데이터를 지우고
다시 남은 ArrayList에 대해 이 과정을 반복하는 방법이었다.

로직 상으로 틀린 방법은 아니었으나, N이 백만까지 갈 수 있었던 바람에 시간 초과가 발생했고
주어진 테스트 케이스에서는 해당 경우를 확인할 수가 없어서 당황했다.

결국 더 나은 로직을 생각해서 이중 반복문이 나오지 않도록 했다.
뒤에서부터 탐색하면 쉽게 답을 찾을 수 있었다.

실제 코딩테스트에서 겪는다는 숨겨진 테스트케이스와 시간초과를 경험할 수 있었고 
제출 전에 시간초과가 발생할 수 있는지 확인하는 능력을 길러야 할 것 같다.



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do
