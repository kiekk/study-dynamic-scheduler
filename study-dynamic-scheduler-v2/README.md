# study-dynamic-scheduler-v2

### 사용 기술
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring Batch-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/pug-A86454?style=for-the-badge&logo=pug&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">

```
Spring Batch를 활용해 동적 스케줄러를 만들어봅니다.

v2에서는 특정 작업(Job)이 미리 선언되어 있다고 가정합니다.
이 때 작업 대상으로 A, B, C 와 같이 개발자가 수동으로 작업 대상을 설정할 수 있습니다.

그렇게 되면 A, B, C 각각에 대한 동적 Job이 생성되며 수정, 삭제도 가능합니다.
그리고 Batch에서 자체적으로 제공하는 Job History를 활용해 실행 결과를 관리할 수 있습니다.

v2는 위와 같은 시나리오의 동적 스케줄러를 만들어봅니다.
```