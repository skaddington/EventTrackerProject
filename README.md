# EventTrackerProject

### Week 12 Homework

## Overview

Full-Stack Java Spring project backed by a MySQL db that implementsa basic REST API.

This app allows the user to track their fishing trip catches, and list, create, update and delete their records.

## DB

Screenshot of ER Diagram, desc of entities

## REST API

| HTTP Verb | URI                  | Request Body | Response Body | Purpose |
|-----------|----------------------|--------------|---------------|---------|
| GET       | `/api/v1/books`      |              | Collection of representations of all _book_ resources | **List** or **collection** endpoint |
| GET       | `/api/v1/books/17`   |              | Representation of _book_ `17` | **Retrieve** endpoint |
| POST      | `/api/v1/books`      | Representation of a new _book_ resource | Description of the result of the operation | **Create** endpoint |
| PUT       | `/api/v1/books/17`   | Representation of a new version of _book_ `17` | | **Replace** endpoint |
| PATCH     | `/api/v1/books/17`   | Description of changes to make to _book_ `17` | | **Update** endpoint |
| DELETE    | `/api/v1/books/17`   |              | | **Delete** route |
| GET       | `/api/v1`            |              | Description of the API and its endpoints | **Index** endpoint |

### Tech
- blah
- blah

### Lessons Learned
- todo
- todo

## JavaScript/AJAX Front End

### Tech
- blah

### Lessons Learned
- todo

## Angular

### Tech
- blah

### Lessons Learned
- todo
