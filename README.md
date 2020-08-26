#MyFood [![Build Status](https://travis-ci.org/lsjjong8/myfood.svg?branch=master)](https://travis-ci.org/lsjjong8/myfood)

# Getting Started

### .gitignore

## Dependency

## Environment
### java 8, spring boot, gradle, jpa, TDD junit 5, 

## Deploy
### aws ec2 linux, git, java8, gradle, s3, aws deploy, travici, nginx

### example URL :
http://cookrcp.com/api/user/1

http://cookrcp.com/api/adminUser/{id}

http://cookrcp.com/api/category/{id}

http://cookrcp.com/api/item/{id}

http://cookrcp.com/api/orderDetail/{id}

http://cookrcp.com/api/orderGroup/{id}

http://cookrcp.com/api/partner/{id}

http://cookrcp.com/api/user/{id}

# Lesson & Learned

## 정성적 교훈

Lombok @Data 사용 시 foreign key 설정 된 부분에서 스택 오버플로우 발생(서로 호출 무한 루프)

각 패키지의 테스트 수행 필요 (Repository 외에 Controller, Service 등)
 - MockMVC를 활용하여 테스트 수행 가능해 보임

데이터베이스에 회원 및 관리자 비밀번호 저장에 암호화 필요
 - sha 256 또는 MD 5를 이용하여 암호화하여 저장 (DB Admin도 비밀번호 평문을 볼 수 없도록)

데이터베이스 구축 및 전처리는 운영할 서비스에 있어 지대한 영향을 끼치므로 설계가 중요

무중단 배포 및 배포 자동화를 통한 업무 편의성을 제공할 수 있음
 - Master Branch에 Commit 되면 자동으로 배포가 수행되기 때문에 Git 전략이 필요
 - Git flow를 활용하여 Master, Develop, Feature 등을 따로 관리할 필요성 있음

## 정량적 교훈

기존에 Prototype을 Node js로 구현하면서 회원 서비스는 OAuth로 구축하는 것을 추천

직접 구현하기 보다는 OAuth로 구축하여 생산성 및 서비스 개발에 집중

관리자 페이지와 사용자에게 제공되는 서비스는 분리해서 구현
 - 외부에서 접근을 막기 위한 인증/권한 관련 기능이 추가로 필요하기 때문에 프로젝트 분리 필요
 
API의 결과 데이터 혹은 HTML 렌더링 결과는 클라이언트에서도 캐시할 수 있음
 - 브라우저 변수를 적극 활용하여 서버 자원을 아낄 수 있음
 
메인이 되는 기능 외에 나머지는 전주 써드파티 라이브러리에 의존하는 걸 추천
 - 1인 개발은 무엇보다 시간이 부족하기 때문에 최대한 외부 서비스를 이용할 것

