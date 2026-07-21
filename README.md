# Spring Boot Docker Host Network Demo

A simple Spring Boot application demonstrating how to run a container with **host network** mode (`--network host`).

## Overview

This demo shows the difference between:
- Default **bridge** network mode (isolated container networking)
- **Host** network mode (shares host's network stack directly)

## Features

- Spring Boot REST endpoint at `/api/system/info`
- Returns host system information (hostname, IP, CPU, memory)
- Docker configuration for host networking

## Prerequisites

- Docker installed
- Java 17+ (for local development)
- Maven (for building)

## How to Build & Run

### Step 1: Build the Docker Image

```bash
docker build -t spring-host-demo .
```

### Step 2: Run using Host Network (`--network host`)

> **Note**: We do **NOT** use `-p 8080:8080` when using host network.

```bash
docker run -d \
  --name my-spring-host-app \
  --network host \
  spring-host-demo
```

## Verification & Testing

Since the container shares your host machine's network stack directly, you can access the application on `localhost`:

```bash
curl http://localhost:8080/api/system/info
```

### Expected Response

```json
{
  "hostName": "your-actual-laptop-or-server-name",
  "hostAddress/IP": "192.168.1.15",
  "availableProcessors": 8,
  "freeMemoryMB": 120,
  "totalMemoryMB": 256
}
```

## Key Difference

| Network Mode     | IP Address Shown          | Port Publishing Needed | Access Method          |
|------------------|---------------------------|------------------------|------------------------|
| **Default (bridge)** | Docker internal IP (e.g. `172.17.0.2`) | Yes (`-p 8080:8080`) | `localhost:8080` or container IP |
| **Host**         | **Real host IP**          | **No**                 | `localhost:8080` directly |

## Stop & Cleanup

```bash
# Stop the container
docker stop my-spring-host-app

# Remove the container
docker rm my-spring-host-app

# Remove the image (optional)
docker rmi spring-host-demo
```

## Additional Notes

- Host network mode is **Linux-only** (not supported on Docker Desktop for Mac/Windows natively).
- Great for performance-critical applications or when you need real host networking.
- **Security consideration**: Container has full access to host's network interfaces.

---

**Made with ❤️ for Docker + Spring Boot enthusiasts**
