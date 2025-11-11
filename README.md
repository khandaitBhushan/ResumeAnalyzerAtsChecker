

## ✅ README.md — Resume Analyzer & ATS Checker

### 🧠 **Resume Analyzer & ATS Checker**

A full-stack application that analyzes resumes using AI and checks ATS (Applicant Tracking System) compatibility.

This project has:

| Layer      | Tech                  |
| ---------- | --------------------- |
| UI         | HTML, CSS, JavaScript |
| Backend    | Spring Boot (Java)    |
| AI Model   | OpenAI API            |
| Deployment | Docker + Render       |
| API Format | REST APIs             |

---

## 🚀 **Live Deployed Backend**

API Base URL:

```
https://resumeanalyzeratschecker.onrender.com
```

### 📡 API Endpoints

| Feature           | Method | Endpoint                |
| ----------------- | ------ | ----------------------- |
| Resume Analyzer   | POST   | `/api/resume/analyzer`  |
| ATS Score Checker | POST   | `/api/resume/ats-check` |

---

## 🎯 **Features**

✔ Analyze resume content & extract insights
✔ ATS score + suggestions for improvements
✔ Clean UI with copy-paste resume input
✔ Secure API using OpenAI Key in environment variables

---

## 🛠️ **Frontend Setup (UI)**

UI Folder contains simple `index.html`, `style.css`, and `script.js`

### 🔗 Update Backend URL in Frontend (Optional)

```js
const BASE_URL = "https://resumeanalyzeratschecker.onrender.com";
```

### 🧪 Run Frontend Locally

Just open `index.html` in browser.

---

## ⚙️ **Backend Setup (Spring Boot)**

### 📁 Project Config

```
spring.application.name=ResumeAnalyzer
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.web.cors.allowed-origin-patterns=*
```

### 🏃 Run Backend Locally

```bash
mvn spring-boot:run
```

---

## 🐳 **Docker Setup**

### Build Docker Image

```bash
docker build -t resume-ats-backend .
```

### Run Container

```bash
docker run -p 8080:8080 -e OPENAI_API_KEY=your_key_here resume-ats-backend
```

---

## 🌐 **Deploy on Render (Docker Deployment)**

### 1️⃣ Push Code to GitHub

Make sure `Dockerfile` exists.

### 2️⃣ Create New Web Service in Render

Choose **Deploy Docker Image from Repo**

### 3️⃣ Set Environment Variable

| Key              | Value           |
| ---------------- | --------------- |
| `OPENAI_API_KEY` | your-openai-key |

### 4️⃣ Deploy 🎯

---

## 🔑 **How to Change OpenAI API Key (Important)**

If your OpenAI free credits expire or want to update key:

### ✅ Steps

1. Go to **Render Dashboard**
2. Open your deployed service
3. Go to **Environment → Environment Variables**
4. Update value of:

   ```
   OPENAI_API_KEY
   ```
5. Click **Save**
6. Re-deploy / Restart service

> **Note:**
> If error occurs due to key exhaustion, the site will stop AI responses.

---

## 🔄 **Redeploy Backend with New Key**

If you want to fully redeploy:

```
Stop Service → Edit Repo (if needed) → Set Key → Deploy Again
```

---

## 🧪 Test After Deployment

Use any of below tools:

### ✅ Curl Test

```bash
curl -X POST https://resumeanalyzeratschecker.onrender.com/api/resume/analyzer \
-H "Content-Type: application/json" \
-d '{"resumeText":"Java developer with Spring experience"}'
```

---

## 🎨 UI Screenshots (Add Yours Below)

> *Add screenshots folder and link here*

---

## 📎 To-Do Improvements

* Upload resume PDF feature
* Store results (MySQL)
* Add JWT Auth
* Use GPT-4o Mini to reduce cost

---

## 🤝 Contributing

Feel free to fork, improve and create PRs.

---

## 🧑‍💻 Author

**Bhushan Khandait**

---

## ⭐ Support

If you like the project, star ⭐ the repo!

---

