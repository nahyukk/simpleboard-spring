# simpleboard 
### [노션 기록 구경가기](https://nahyuk.notion.site/19d7ef37c24380b5a079d7d8341557a0?pvs=4)

## 과제 내용
- JPA를 사용하여 **게시글**과 **댓글**이 있는 **게시판**을 구현
- **게시글**: 제목과 본문이 있으며, 제목과 본문 모두 Text만 지원
- **댓글**: 게시글에 댓글을 달 수 있으며, **대댓글은 고려하지 않습니다.**
<br/>

## 기술스택
- Backend : 
    - JAVA 21
    - Spring Boot 3.4.2
    - Spring Data JPA (Hibernate) - ORM
    - Spring Web (Rest API)
- Database :
    - H2 (개발용 인메모리 DB)
    - PostgreSQL 17.4 via Docker
- Frontend : 
    - Thymeleaf (Template Engine)
    - Bootstrap (UI Styling)
- Build :
    - Gradle
- Containerization :
    - Docker 
<br/>

## API 요구사항
1. 게시글 등록 `POST /posts` --> `POST /posts`, `GET /posts/new`
	 - 게시글을 등록할 수 있다.
	 - 요청 본문: 제목, 내용

2. 게시글 삭제 `DELETE /posts/{id}` --> `POST /posts/{id}/delete`
   - 게시글을 soft delete 방식으로 삭제한다.
   - 해당 게시글의 댓글도 함께 삭제된다.

3. 게시글 수정 `PUT /posts/{id}` --> `POST /posts/{id}`, `GET /posts/{id}/edit`
	- 게시글을 수정할 수 있다.
	- 삭제된 게시글은 수정할 수 없다.

4. 게시글 목록 조회 `GET /posts`
	-	게시글 목록을 조회한다.
	-	응답에는 본문이 포함되지 않으며, 페이징 처리가 가능하다.
	-	최신 글부터 정렬된다.

5. 댓글 등록 `POST /posts/{postId}/comments`
	- 게시글에 댓글을 등록한다.

6. 댓글 수정  `PUT /posts/{postId}/comments/{commentId}` --> `POST /posts/{postID}/comments/{id}/edit`
	-	댓글을 수정할 수 있다.

7. 댓글 삭제  `DELETE /posts/{postId}/comments/{commentId}` --> `POST /posts/{postID}/comments/{id}/delete`
	-	댓글을 soft delete 방식으로 삭제한다.

8. 게시글 단건 조회 `GET /posts/{id}`
	-	게시글의 제목과 본문을 조회한다.
	-	해당 게시글에 달린 댓글도 함께 응답한다.
	-	삭제된 댓글은 포함되지 않는다.
<br/>

## 결과물
|게시글 목록과 페이지네이션|
|---|
![image](https://github.com/user-attachments/assets/fc651785-1bda-4561-84fd-ea6f3fe490c5)

|게시글 작성 | 단건 조회|
|---------|---------|
|![image](https://github.com/user-attachments/assets/95d9f296-003a-46f2-a7ab-21c01cef1501)|![image](https://github.com/user-attachments/assets/9c4dc3b8-6a60-47b6-b095-2642be482f11)|

|게시글 수정 | 단건 조회 (수정 후)|
|---------|---------|
|![image](https://github.com/user-attachments/assets/fd2de8bd-7b72-4a9a-a3b0-84c14fadd02b)|![image](https://github.com/user-attachments/assets/0094b0cf-0fdd-4c06-a187-a6c8c0e8944c)



|댓글 작성|댓글 수정|
|-------|-------|
|![image](https://github.com/user-attachments/assets/00a8a6ae-33f8-4f24-a19f-a91c60a34087)|![image](https://github.com/user-attachments/assets/ebc90fd8-4791-48e1-8b69-1980b609bc31)



