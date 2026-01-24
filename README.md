# Article API – Request Documentation

## Overview
The **Article API** is a RESTful service built with Spring Boot that provides CRUD and search operations for managing articles. It supports keyword-based search, filtered search, date-range queries, and standard create/update/delete operations.

**Base URL**
```
/article_api
```

**Content-Type**
```
application/json
```

**CORS**
- Allowed Origins: `*`

---

## Article Object Schema

```json
{
  "id": 1,
  "title": "Spring Boot Guide",
  "author": "John Doe",
  "content": "Article content here",
  "tags": "spring,java,backend",
  "publishDate": "2024-01-01T00:00:00.000+00:00",
  "updateDate": "2024-01-02T00:00:00.000+00:00"
}
```

| Field | Type | Description |
|------|------|-------------|
| id | Integer | Auto-generated article ID |
| title | String | Article title |
| author | String | Author name |
| content | String | Full article content |
| tags | String | Comma-separated tags |
| publishDate | Date | Article publish date |
| updateDate | Date | Last update date |

---

## Endpoints

### 1. Search Articles
**GET** `/search`

Search articles using keyword, filters, or date ranges.

#### Query Parameters
| Name | Required | Default | Description |
|----|----|----|----|
| keyword | No | "" | Search keyword |
| filter | No | `none` | Search filter (`none`, `title`, `author`, `PublishDate`, `UpdateDate`) |

#### Request Body (Optional – for date filters)
```json
{
  "startDate": "2024-01-01",
  "endDate": "2024-01-31"
}
```

#### Example Request
```
GET /article_api/search?keyword=spring&filter=title
```

#### Success Response
- **200 OK** – List of matching articles

#### Error Responses
- **400 Bad Request** – Invalid filter
- **500 Internal Server Error** – Server failure

---

### 2. Get All Articles or Single Article
**GET** `/article`

#### Query Parameters
| Name | Required | Default | Description |
|----|----|----|----|
| id | No | 0 | Article ID (0 returns all articles) |

#### Example Requests
- Get all articles
```
GET /article_api/article
```

- Get article by ID
```
GET /article_api/article?id=5
```

#### Success Responses
- **200 OK** – Article or list of articles

#### Error Responses
- **404 Not Found** – Article not found

---

### 3. Add Articles (Bulk Insert)
**POST** `/article`

Adds one or more articles in bulk.

#### Request Body
```json
[
  {
    "title": "Java Basics",
    "author": "Alice",
    "content": "Intro to Java",
    "tags": "java,programming"
  }
]
```

#### Success Response
- **200 OK** – Articles added successfully

#### Error Response
- **208 Already Reported** – Articles already exist

---

### 4. Update Article
**PUT** `/article`

Updates an existing article.

#### Request Body
```json
{
  "id": 3,
  "title": "Updated Title",
  "author": "Bob",
  "content": "Updated content",
  "tags": "update,java"
}
```

#### Success Response
- **200 OK** – Article updated

#### Error Response
- **404 Not Found** – Article does not exist

---

### 5. Delete Article
**DELETE** `/article`

Deletes an article by ID.

#### Query Parameters
| Name | Required | Description |
|----|----|----|
| id | Yes | Article ID |

#### Example Request
```
DELETE /article_api/article?id=3
```

#### Success Response
- **200 OK** – Article deleted

#### Error Response
- **404 Not Found** – Article not found

---

## HTTP Status Codes Used
| Code | Meaning |
|----|----|
| 200 | Success |
| 400 | Bad request |
| 404 | Resource not found |
| 208 | Already reported |
| 500 | Internal server error |

---

## Notes
- Date-based search requires request body even for GET requests.
- Bulk insert is supported via POST.
- Designed for educational and CRUD-based backend use cases.

---

**Author:** SkyLightLabs
**Framework:** Spring Boot
**Database:** JPA / Hibernate / MySQL

