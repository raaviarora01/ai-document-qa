# Architecture Decisions

This document records important architectural decisions made during development and the reasoning behind them.

---

## ADR-001: Modular Monolith

**Decision:** Build the backend as a modular Spring Boot monolith.

**Why:** The current application does not require independently deployed services. A modular monolith provides clear boundaries while keeping development, testing, debugging, and deployment simpler.

**Trade-off:** Individual modules cannot initially be deployed or scaled independently.

---

## ADR-002: PostgreSQL for Relational Data

**Decision:** Use PostgreSQL for structured application data.

**Why:** Users, documents, conversations, messages, and roles have clear relationships and benefit from transactions, constraints, and indexing.

**Trade-off:** PostgreSQL is not intended to be our primary document or vector storage system.

---

## ADR-003: Qdrant for Vector Search

**Decision:** Use Qdrant to store embeddings and perform semantic similarity search.

**Why:** Qdrant is purpose-built for vector search and can be run locally during development.

**Trade-off:** It introduces another datastore that must be configured and maintained.

---

## ADR-004: S3 for Document Storage

**Decision:** Store original PDFs in Amazon S3 in the production architecture.

**Why:** Documents should not depend on the local filesystem of an EC2 instance or application container.

**Trade-off:** Adds an external service and network dependency.

---

## ADR-005: Asynchronous Document Processing

**Decision:** Process uploaded documents asynchronously.

**Why:** Text extraction, chunking, and embedding generation may take significant time and should not block the upload HTTP request.

**Trade-off:** Requires processing states, background execution, and failure handling.

---

## ADR-006: JWT Authentication

**Decision:** Use Spring Security with short-lived JWT access tokens and refresh tokens.

**Why:** JWT allows the backend API to authenticate requests without maintaining server-side sessions.

**Trade-off:** Token expiration, refresh, revocation, and secure storage require additional handling.

---

## ADR-007: Package by Feature

**Decision:** Organize backend code by business capability rather than global controller/service/repository packages.

Example:

```text
auth/
document/
rag/
conversation/
security/
```

**Why:** Related functionality stays together and module boundaries remain easier to understand as the project grows.

**Trade-off:** Some shared infrastructure requires careful placement to avoid unnecessary duplication.