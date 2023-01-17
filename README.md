# study-dynamic-scheduler

### 사용 기술
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring Batch-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

```
Quartz와 Spring Batch를 사용하여 동적 스케줄러를 만들어봅니다.

v1은 이미 작성되어 있는 Batch Job을 수정/삭제/재등록 할 수 있는 스케줄러이며,

v2는 등록된 하나의 Batch Job을 가지고 전달되는 파라미터별로 새로운 스케줄러를 생성하는 스케줄러입니다.
Airbyte와 비슷한 형식으로 만들어보았습니다.
```