# 📌Goo's Reservation [예약서비스]

오너가 등록한 시설을 유저가 예약하는 서비스입니다.

[플레이 시연 영상 보러가기👉🏻👉🏻](https://youtu.be/pLfiAwebams?si=JCW6iWWsxNgLg3gX)

<br/><br/>

## 목차
- [전체적인 구조](#전체적인-구조)
- [개요](#개요)
- [개발환경](#개발환경)
- [기능 및 설계](#기능-및-설계)
- [ERD](#erd)

<br/>

## 전체적인 구조

> 오너 : 공공시설을 등록하고 예약을 받아요!
> 
> 유저 : 공공시설을 원하는 시간에 예약해요!

<br/>

<img src="https://github.com/JGoo99/goos-reservation/assets/126454114/f0c8473d-5a04-47e8-a9da-f2f7460b98ba" width="60%">

| 오너는 공유하고싶은 시설을 간단한 정보와 함께 등록합니다.  
| 유저는 등록된 시설의 정보를 확인하고 원하는 시설을 선택합니다.  
| 가능한 날짜와 시간을 선택하여 예약을 진행합니다.  
| 오너는 예약 내역을 확인합니다.  
| 선택적으로 예약을 거부할 수 있습니다.

<br/>

## 개요

- 개발 인원 : 1인 창작 프로젝트
- 개발 기간 : 2024.01.13 ~ 2024.01.16 (총 3일)

<br/>

## 개발환경

<div>
    <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
    <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=java&logoColor=white">
</div>

> Spring Boot 3.1.5 (JDK 17)
>
> Java 17
>
> Gradle - Groovy
>
> JPA
>
> MariaDB
>
> Spring Security (2개의 로그인 페이지)
>
> Lombok
>
> Validation

<br/>

## 기능 및 설계

### 회원가입 기능

사용자는 회원가입을 할 수 있다.  
유저페이지는 유저아이디(ROLE_USER), 오너페이지는 오너아이디(ROLE_OWNER)로 개별로 회원가입이 필요하다.
`main`, `user-home`, `owner-home` 페이지를 제외하고 각 계정의 권한이 필요하다.

### 로그인 기능

사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야한다.

### 오너페이지

- 시설 등록 : 간단한 시설정보와 함께 시설을 등록할 수 있다.
- 시설 조회 : 모든 시설의 상세정보를 조회할 수 있다.
- 마이페이지
  - 시설 정보 조회 : 자신이 등록한 시설정보를 확인할 수 있다.
  - 시설 정보 수정 : 자신이 등록한 시설정보를 수정할 수 있다.
  - 시설 삭제 : 자신이 등록한 시설을 삭제할 수 있다.
  - 예약 내역 조회 : 유저가 신청한 예약 내역을 조회할 수 있다.
    - 예약 거부 : 유저가 신청한 예약을 거부할 수 있다.

### 유저페이지

- 시설 조회 : 모든 시설의 상세정보를 조회할 수 있다.
- 예약 신청 : 가능한 날짜와 시간을 선택하여 예약을 진행한다.
  - 조건1 : 이미 지난 날짜와 시간은 선택할 수 없다.
  - 조건2 : 연속된 시간만 예약할 수 있다.
- 마이페이지
  - 예약 내역 조회 : 자신이 신청한 예약내역을 조회할 수 있다.
  - 예약 거부 조회 : 예약이 거부되었는지 확인할 수 있다.
 
<br/>

## ERD

<img src="https://github.com/JGoo99/goos-reservation/assets/126454114/885b15f4-7866-4b16-b9bb-99c7bb0b4b93" width="60%">