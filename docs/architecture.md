# Architecture

## Overview

AI Document Q&A is a production-oriented application that allows authenticated users to upload PDF documents and ask questions about their content using Retrieval-Augmented Generation (RAG).

## Architectural Style

The backend will initially be built as a **modular monolith** using Spring Boot, organized by business features such as:

- Authentication
- Users
- Documents
- RAG
- Conversations

A modular monolith keeps development and deployment simple while maintaining clear module boundaries. Microservices can be considered later if independent scaling becomes necessary.

## High-Level Architecture

```text
React Frontend
      |
      v
Spring Boot API
      |
      +---- PostgreSQL
      |
      +---- S3
      |
      +---- Qdrant
      |
      +---- Gemini
```

### Responsibilities

- **Spring Boot:** REST APIs, business logic, security, RAG orchestration
- **PostgreSQL:** Users, roles, document metadata, conversations, messages
- **S3:** Original uploaded documents in production
- **Qdrant:** Document chunks, embeddings, and vector similarity search
- **Gemini:** AI-powered answer generation

## RAG Flow

### Document Ingestion

```text
PDF
 ↓
Text Extraction
 ↓
Chunking
 ↓
Embedding Generation
 ↓
Qdrant
```

### Question Answering

```text
Question
 ↓
Question Embedding
 ↓
Vector Similarity Search
 ↓
Relevant Document Chunks
 ↓
Prompt + Context
 ↓
Gemini
 ↓
Answer + Sources
```

## Document Processing

Document ingestion will be asynchronous because extraction and embedding generation may take significant time.

```text
Upload
 ↓
Validate & Store
 ↓
PROCESSING
 ↓
Background Processing
 ↓
READY / FAILED
```

Initially, Spring asynchronous processing will be used. A message queue such as AWS SQS may be introduced later if required.