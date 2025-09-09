# XFLIX-master
This project involves automating the XFlix video sharing platform to verify URLs, retrieve details of uploaded videos, verify video upload functionality, and check data persistence.

![demo](https://github.com/user-attachments/assets/ff1d4f61-1d25-4557-a619-b53d4ba6be29)

---

## 🚀 Scope of Work

The automation suite covers the following test cases:

- ✅ **Verify Homepage URL** – Ensure that the XFlix homepage loads correctly.  
- 🔍 **Verify Search Functionality** – Confirm that users can search and retrieve relevant videos.  
- 🎚️ **Verify Filter Functionality** – Validate filters (e.g., genre, upload date) return the right results.  
- ⬆️ **Verify Upload Video** – Automate the process of uploading a video and check persistence.  
- 👍 **Verify Like Counter** – Ensure that likes increment/decrement correctly and persist after refresh.  

---

## 🛠️ Skills & Tools Used

- **Selenium WebDriver** – Browser automation for UI testing.  
- **Java** – Core programming language for writing test scripts.  
- **XPath** – Locators for DOM element identification.  
- **Gradle & Shell Scripts** – Build automation and test execution.  

## ▶️ Getting Started

### Prerequisites
- Install **Java 11+**
- Install **Gradle**
- Install **ChromeDriver** (or the driver matching your browser)
- Clone this repository:
  ```bash
  git clone https://github.com/manasajayasri/XFLIX-master.git
  cd XFLIX-master
    ```

### Running Tests
  ```bash
./gradlew clean test
  ```
Or run via shell script:
  ```bash
sh scripts/run_assessment.sh
  ```

