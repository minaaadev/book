# 도서 정보 관리 시스템 📚

도서 목록에 도서를 추가하고 도서 정보를 수정, 삭제, 조회하는 시스템

<br>

**기술 스택**

Java, Spring boot, Postgre SQL

<br>




## API 엔드포인트
- GET /books : 메인 페이지, 도서 목록 확인
- POST /books/new : 도서 추가 페이지, 새로운 도서 정보 저장
- GET /books/search : 도서 조회 페이지, id를 조회해서 도서 정보 조회
- PUT /books/edit/{id} : 도서 수정 페이지, 해당 도서의 정보 수정
- DELETE books/{id} : 특정 id의 도서 삭제 

<br>

## API 흐름

### 도서 추가

**사용자** : /books/add 페이지에서 도서 제목, 작가, 가격을 입력하고 추가 버튼 클릭(POST)

**서버** : BookController가 POST 요청을 처리하고, 전달된 도서 정보를 BookService로 전달

BookService는 도서 정보의 유효성 확인 후 BookRepository를 통해 PostgreSQL에 새로운 도서 정보 저장

저장 완료 후 /books로 리다이렉트

**사용자** : 해당 도서가 목록에 추가되어 있는것 확인 가능

<br>

### 도서 수정

**사용자** : 수정 버튼을 클릭해 /books/edit/{id}페이지로 이동

도서 정보를 수정한 후 저장 버튼 클릭(POST)

**서버** : BookController가 POST 요청을 처리하고, 수정된 도서 정보를 BookService로 전달

BookService는 도서 ID와 새로운 정보를 BookRepository에 전달

BookRepository가 PostgreSQL에서 해당 ID의 도서 정보를 수정

수정 완료 후 /books로 리다이렉트

**사용자** : 해당 도서가 도서 목록에 수정된 정보로 나타나 있는것 확인 가능

<br>

### 도서 삭제

**사용자** : 삭제 버튼 클릭 (POST 요청 전송)

**서버** : BookController가 POST 요청을 처리하고, 삭제 요청된 도서 ID를 BookService로 전달.

BookService는 도서 ID의 유효성을 확인한 후, BookRepository를 통해 PostgreSQL에서 해당 도서를 삭제

**사용자** : 해당 도서가 도서 목록에서 삭제된 것 확인 가능

<br>

### ID로 도서정보 조회

**사용자** : /books/search 페이지에서 책 ID를 입력 후 조회 버튼 클릭(POST)

**서버** : BookController가 GET 요청을 받고, BookService에서 ID 유효성 확인 후 BookRepository로 전달

BookRepository가 PostgreSQL에서 해당 ID의 도서정보 조회

조회 결과를 DTO로 변환 후 Controller에 반환

Controller는 데이터를 search.html 페이지에 전달해 렌더링


<br>


## 데이터베이스 테이블 구조

![image](https://github.com/user-attachments/assets/a50265cb-9972-4d5e-b385-91ecee6d4048)

<br>

## 시스템 동작과정
![image](https://github.com/user-attachments/assets/e1de76a8-2998-475c-b690-16d0925e2a25)

도서 목록 확인

![image](https://github.com/user-attachments/assets/d6bf426e-9534-4fe2-95a2-73de2213569e)

도서 추가버튼을 누르면 도서 제목, 작가,가격을 입력해 도서를 추가 할 수 있다. 


![image](https://github.com/user-attachments/assets/fba7f081-8ac4-438e-bd21-d4b542a60471)

수정 버튼을 누르면 도서 정보를 수정할 수 있다.

삭제 버튼을 누르면 도서가 삭제된다. 

![image](https://github.com/user-attachments/assets/e9f7ad91-d380-4c8a-8931-df17d1f38fb9)
![image](https://github.com/user-attachments/assets/5093c34c-db4f-46cd-a8c4-5d54231ba7eb)

도서 조회버튼을 누르면 id를 입력해 해당 id의 도서 정보를 조회할 수 있다. 

![image](https://github.com/user-attachments/assets/a005032d-0b19-47a3-bf7b-40b3c7d51a3f)

데이터베이스에서 실시간으로 도서 목록의 업데이트가 이루어지는것을 확인할 수 있다. 

<br>



---
