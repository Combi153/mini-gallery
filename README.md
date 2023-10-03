# S3 사용해보기

## 개발 요구사항

- [x] S3 띄우기

- [ ] 서버 띄우기
    - [x] ec2 설정 (was)
    - [ ] nginx 설정 (reverse proxy)

- [ ] Application
    - [ ] 이미지 저장 기능
        - [ ] Thymeleaf 저장 화면 생성
            - 동작을 안 하네..
        - [x] 이미지 저장 api 추가
    - [ ] 이미지 조회 기능
        - [ ] Thymeleaf 조회 화면 생성
            - 동작을 안 하네..
        - [x] 이미지 조회 api 추가

## 메모

- 이미지 업로드 시 Multipart/form-data
- 이미지의 이름을 어떻게 정할 것인가?
    - 이미지 이름이 중복되면 어떻게 처리가 될까?
- ec2에 있는 이미지를 어떻게 s3로 옮길까?
- s3 사용하는 api에 대한 Transaction 관리를 어떻게 할까?
    - facade pattern 적용
    - TransactionTemplate 사용
