# framework-study
항해 플러스 프레임워크 사전 스터디

# Usecase
![usecase drawio](https://github.com/user-attachments/assets/621a0e0d-7127-4a62-ab82-4e25ae4c59bb)

# ERD
![ERD drawio](https://github.com/user-attachments/assets/2368c432-6371-48ec-ad9b-f0c3cf19c61d)

# API 명세서
<details>
<summary>전체 게시글 목록 조회 API</summary>

### endpoint: /posts  
### method: GET  
### query parameter:  

| 이름      | 타입      | 필수 여부 | 기본값 | 설명                      |
|-----------|-----------|-----------|--------|-------------------------|
| `page`    | `integer` | 선택      | 1      | 조회할 페이지 번호              |
| `size`    | `integer` | 선택      | 10     | 페이지당 항목 수               |
| `sort`    | `string`  | 선택      | 없음   | 정렬 기준 (`created_at`)    |
| `order`   | `string`  | 선택      | `asc`  | 정렬 순서 (`asc` 또는 `desc`) |

</details>

<details>
<summary>게시글 작성 API</summary>

### endpoint: /posts  
### method: POST  
### request body:   

| 이름           | 타입       | 필수 여부 | 기본값 | 설명       |
|--------------|----------|-------|-----|----------|
| `title`      | `string` | 필수    | 없음  | 게시글 제목   |
| `content`    | `string` | 선택    | 없음  | 작성 내용    |
| `password`   | `string` | 필수    | 없음  | 회원 비밀번호  |

</details>

<details>
<summary>선택한 게시글 조회 API</summary>

### endpoint: /posts/{postId}  
### method: GET
### path parameter:

| 이름           | 타입        | 필수 여부 | 기본값 | 설명     |
|--------------|-----------|-------|-----|--------|
| `postId`      | `integer` | 필수    | 없음  | 게시글 ID |

</details>

<details>
<summary>선택한 게시글 수정 API</summary>

### endpoint: /posts/{postId}  
### method: PUT
### path parameter:

| 이름           | 타입        | 필수 여부 | 기본값 | 설명     |
|--------------|-----------|-------|-----|--------|
| `postId`      | `integer` | 필수    | 없음  | 게시글 ID |

### request body:  

| 이름           | 타입       | 필수 여부 | 기본값 | 설명       |
|--------------|----------|-------|-----|----------|
| `title`      | `string` | 필수    | 없음  | 게시글 제목   |
| `content`    | `string` | 선택    | 없음  | 작성 내용    |
| `password`   | `string` | 필수    | 없음  | 회원 비밀번호  |

</details>

<details>
<summary>선택한 게시글 삭제 API</summary>

### endpoint: /posts/{postId}  
### method: DELETE
### path parameter:

| 이름           | 타입        | 필수 여부 | 기본값 | 설명     |
|--------------|-----------|-------|-----|--------|
| `postId`      | `integer` | 필수    | 없음  | 게시글 ID |

### request body:

| 이름           | 타입       | 필수 여부 | 기본값 | 설명       |
|--------------|----------|-------|-----|----------|
| `password`   | `string` | 필수    | 없음  | 회원 비밀번호  |

</details>