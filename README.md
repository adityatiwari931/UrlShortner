URL Hashing System


Overview
The URL Hashing System is designed to shorten long URLs while preserving query parameters and allowing click tracking. The system provides an easy-to-use API for generating and resolving shortened URLs. This application is built using Java and Spring Boot, with URL hashing handled via SHA-256, and data persistence managed using MySQL.

The system ensures privacy by using hashed URLs that do not reveal any identifiable data and includes click tracking to monitor how often a short URL has been used. It is scalable and can be deployed on various platforms, including AWS using serverless technologies like AWS Lambda and API Gateway.

Architecture
The system follows a RESTful API architecture with the following core components:

API Layer (Spring Boot):

The application exposes two main RESTful endpoints:
POST /shorten: Generates a shortened URL from the long URL.
GET /resolve/{hash}: Resolves a shortened URL back to the original long URL.
This layer is responsible for receiving and responding to API requests, ensuring that the service can handle client requests effectively.

Service Layer:
Contains the core business logic.
Handles URL hashing (via SHA-256), stores URL mappings in the database, and retrieves them for resolving.
Tracks clicks whenever a shortened URL is accessed.
Encapsulates logic for URL shortening and resolving, making the system modular and maintainable.
Database Layer (MySQL):

Stores URL mappings between shortened (hashed) URLs and the original long URLs.
Records the number of times each shortened URL has been accessed (click tracking).
Uses Spring Data JPA to abstract database interaction, making database management easier and supporting scalability.
Hashing Mechanism:

Uses SHA-256 hashing to generate a fixed-length, privacy-aware shortened URL from the long URL.
The hash is truncated to 8 characters to keep the shortened URL compact while maintaining sufficient uniqueness.
Hashing ensures that the shortened URL does not contain any user-identifiable data, making it privacy-preserving.
Click Tracking:

Each time a shortened URL is resolved, the system increments the click count in the database.
This helps track how many times a URL has been used, providing insights into the effectiveness of marketing campaigns.
Flow of Operations
URL Shortening Process:

A client sends a POST request with the original long URL to the /shorten endpoint.
The system hashes the long URL using SHA-256, truncates it to 8 characters, and stores the mapping in the database.
The server responds with the shortened URL in the format: http://your-domain.com/{hash}.
URL Resolving Process:

A client requests the shortened URL by sending a GET request to the /resolve/{hash} endpoint.
The system looks up the original long URL from the database based on the hash.
If found, the click count is incremented, and the server returns the original long URL.
If the URL is not found, the system returns a 404 error.
Key Design Decisions
Privacy via Hashing: We opted for SHA-256 hashing because it is secure, fast, and widely supported. By using hashing, we ensure that no sensitive information (like query parameters) is exposed in the shortened URL.

Stateless API: The API is designed to be stateless, meaning each request is self-contained and does not rely on previous interactions. This helps with scalability, as instances of the API can be easily replicated.

Database Choice (MySQL): MySQL was chosen for its reliability and familiarity in handling relational data. It allows efficient querying and indexing, which is essential for fast lookups of hashed URLs.

Click Tracking: We included click tracking to provide useful metrics, such as how many times a particular URL was accessed. This is useful for marketing and analytics purposes.

Modular Structure: The service layer encapsulates the business logic, making the application easier to maintain and extend. If future requirements arise (e.g., support for custom aliases), changes can be made in the service layer without affecting other components.

Scalability Considerations: The architecture is scalable both horizontally and vertically. By using Spring Boot, the system can easily be deployed across multiple servers or containers. Additionally, by integrating with AWS services (such as DynamoDB or Lambda), the system can be made fully serverless, which increases scalability and reduces operational overhead.

Technology Stack
Java: Core programming language used for implementing the application logic.
Spring Boot: Framework used to build the RESTful API, handle dependency injection, and manage application configuration.
MySQL: Relational database used to store URL mappings and click counts.
Spring Data JPA: Simplifies database interactions by providing an abstraction layer for working with MySQL.
SHA-256 Hashing: Used to create privacy-preserving short URLs.
JUnit: For unit testing the service and controller layers to ensure reliability.
Postman: For testing the REST APIs during development.
Key Features
URL Shortening:

Converts long URLs into shorter, more manageable URLs.
Preserves the integrity of query parameters.
Click Tracking:

Tracks how often each shortened URL is accessed.
Privacy-Preserving:

Uses hashing to avoid exposing sensitive query parameters in the shortened URLs.
REST API:

Easy-to-use endpoints for shortening and resolving URLs.
Scalability:

The architecture supports scaling up through database replication and distributed server instances.
Future-proofed to be deployed on serverless platforms like AWS Lambda.
How It Works
Shorten a URL:
To shorten a URL, send a POST request to the /shorten endpoint with the long URL in the request body. The system hashes the URL, stores it in the database, and returns a shortened URL.

**Example:**

bash
Copy code
POST /shorten
Body: https://example.com?utm_source=newsletter&utm_medium=email
Response: http://your-domain.com/abcd1234
Resolve a URL:
To resolve a shortened URL, send a GET request to the /resolve/{hash} endpoint. The system looks up the original long URL based on the hash, increments the click count, and redirects the user to the original URL.

**Example:**

bash
Copy code
GET /resolve/abcd1234
Response: https://example.com?utm_source=newsletter&utm_medium=email
Future Enhancements
Custom Aliases: Allow users to create custom short URLs rather than auto-generated ones.
Analytics Dashboard: Provide a dashboard to show click statistics and other analytics.
Expiration Dates: Implement expiration for short URLs (e.g., single-use URLs or URLs that expire after a certain period).
Conclusion
This URL Hashing System provides a scalable and privacy-preserving solution for shortening URLs. The modular structure, click tracking, and secure hashing make it a powerful tool for marketing campaigns or any application where link shortening is needed. With future enhancements like custom aliases and analytics, the system can be extended to meet evolving requirements.

By following this architecture and reasoning, your README.md file will give a clear and thorough understanding of the project to anyone reading it. You can tailor the domain name and specifics depending on your actual setup.
