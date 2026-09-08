# API Design

## Base Path

All initial APIs will be versioned under:

```text
/api/v1
```

## Authentication

```http
POST /api/v1/auth/register
POST /api/v1/auth/login
POST /api/v1/auth/refresh
POST /api/v1/auth/logout
```

## User

```http
GET /api/v1/users/me
```

## Documents

```http
POST   /api/v1/documents
GET    /api/v1/documents
GET    /api/v1/documents/{documentId}
DELETE /api/v1/documents/{documentId}
```

## Question Answering

```http
POST /api/v1/documents/{documentId}/questions
```

Example request:

```json
{
  "question": "What is dependency injection?"
}
```

Example response:

```json
{
  "answer": "Dependency injection is...",
  "sources": [
    {
      "page": 12,
      "snippet": "..."
    }
  ]
}
```

## Conversations

```http
GET    /api/v1/conversations
GET    /api/v1/conversations/{conversationId}
DELETE /api/v1/conversations/{conversationId}
```

## API Conventions

- Use DTOs instead of exposing JPA entities directly.
- Validate incoming requests.
- Return appropriate HTTP status codes.
- Use a consistent error-response structure.
- Paginate endpoints returning collections.
- Enforce authentication, authorization, and resource ownership server-side.