# S3 사용해보기

## 개발 요구사항

- [x] S3 띄우기
    - [x] bucket policy 설정
    - [x] public access restrict

- [x] CloudFront
    - [x] S3 OAI(Origin Access Identity) 설정

- [x] 서버 띄우기
    - [x] ec2 설정 (was)
    - [ ] nginx 설정 (reverse proxy) -> 그냥 8080 포트로 하자.

- [x] Application
    - [x] 이미지 저장 기능
        - [x] 이미지 저장 api 추가
    - [x] 이미지 조회 기능
        - [x] 이미지 조회 api 추가

- [ ] Postman test
    - [ ] get object : GET /images
    - [ ] put object : POST /images

## 메모

- 이미지 업로드 시 Multipart/form-data
- 이미지의 이름을 어떻게 정할 것인가?
    - 이미지 이름이 중복되면 어떻게 처리가 될까?
- ec2에 있는 이미지를 어떻게 s3로 옮길까?
- s3 사용하는 api에 대한 Transaction 관리를 어떻게 할까?
    - facade pattern 적용

- 허브가 보내준 자료
- s3 테스트 알아보기
- 경로 이미지 ImageIO 어떻게 처리할지 고민
