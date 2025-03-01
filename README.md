

# CS 816 - Software Production Engineering Mini Project Report  
## Scientific Calculator with DevOps  
**Roll Number: [Insert Your Roll Number, e.g., MT2020xx]**  
**Submitted by: [Your Name]**  

---

## What and Why of DevOps?

### What is DevOps?
DevOps is a transformative software development methodology that bridges the gap between development (Dev) and operations (Ops) teams, fostering a culture of collaboration, automation, and continuous improvement. Emerging from the need to accelerate software delivery while maintaining high quality, DevOps integrates practices such as Continuous Integration (CI), Continuous Deployment (CD), and Infrastructure as Code (IaC). It leverages a combination of tools, processes, and cultural philosophies to automate the software lifecycle—from code writing and testing to deployment and monitoring. At its core, DevOps aims to break down traditional silos, enabling teams to deliver features, fixes, and updates rapidly and reliably in response to evolving user needs. This approach is particularly valuable in modern software engineering, where agility and scalability are paramount.

### Why DevOps?
The adoption of DevOps is driven by the demand for faster and more reliable software delivery in an increasingly competitive technological landscape. By automating repetitive tasks such as building, testing, and deploying applications, DevOps reduces human error and accelerates time-to-market. It enhances collaboration between developers and operations teams, ensuring that infrastructure and application changes are seamlessly aligned. For this project, implementing DevOps provides a practical framework to containerize a scientific calculator, automate its build and integration process, and manage its local execution efficiently. This not only simulates a real-world production environment but also equips the developer with hands-on experience in industry-standard practices, preparing them for complex software production scenarios.

---

## Tools Used

### Overview of Tools
This project utilizes a carefully selected set of DevOps tools to create a streamlined workflow for developing, containerizing, and managing a scientific calculator application. Each tool serves a specific purpose, contributing to an automated and repeatable process. Below is a detailed breakdown of the tools employed:

- **Source Control Management: GitHub**  
  GitHub is a widely-used platform for version control and collaborative code management, built on Git. It allows multiple developers to work on the same codebase simultaneously, track changes, and maintain a history of revisions. For this project, GitHub serves as the central repository to store the Java calculator code, configuration files, and pipeline scripts, enabling version tracking and integration with Jenkins.

- **Continuous Integration: Jenkins**  
  Jenkins is an open-source automation server that facilitates Continuous Integration and Continuous Deployment. It automates the process of building, testing, and deploying code by executing predefined pipelines triggered by code changes. In this project, Jenkins orchestrates the workflow, from checking out code to running Ansible playbooks, with added support for GitHub webhooks and Ngrok for accessibility. An attempt was made to configure email notifications, but this feature remains incomplete.

- **Containerization: Docker**  
  Docker is a platform that enables developers to package applications and their dependencies into lightweight, portable containers. These containers ensure consistency across different environments, from development to production. Here, Docker is used to containerize the Java-based calculator, bundling it with Ubuntu and OpenJDK 17 for local execution.

- **Configuration Management: Ansible**  
  Ansible is an open-source configuration management and orchestration tool that automates the provisioning and management of infrastructure. It uses simple YAML-based playbooks to define tasks, making it ideal for deploying and managing Docker containers. In this project, Ansible configures and runs the calculator container on the local machine.

- **Additional Tools: Ngrok and GitHub Webhooks**  
  - **Ngrok**: A reverse proxy tool that creates secure tunnels to localhost, allowing external access to the Jenkins server running on a local machine. This is crucial for webhook integration.  
  - **GitHub Webhooks**: Automated triggers that notify Jenkins of code pushes, enabling GitSCM polling to start pipeline runs seamlessly.

These tools collectively form a robust DevOps pipeline tailored to the project’s scope, focusing on local development and automation.

---

## Step-by-Step Explanation

### 1. Source Control Management (GitHub)
**Brief**: Source Control Management (SCM) is the foundation of any DevOps pipeline, ensuring that code is safely stored, versioned, and accessible. GitHub, a cloud-based SCM platform, was chosen for its robust features, including pull requests, issue tracking, and integration with CI/CD tools like Jenkins.

**Setup and Configuration**:  
- A new GitHub repository was created at `https://github.com/Naval-Bisht/Mini-Project-Calc.git` to house the project files.  
- The repository was initialized with a `main` branch to serve as the primary development branch.  
- Project files, including the Java source code (`Sinc_calc.java`), compiled JAR (`Sinc_calc.jar`), `Dockerfile`, `Jenkinsfile`, `deploy.yml`, and `inventory`, were committed to the repository.  
- GitHub webhooks were configured to notify Jenkins of code pushes, enabling automated pipeline triggers.

**Commands Used**:  
- `git init` – Initialized a new Git repository.  
- `git add .` – Staged all files for commit.  
- `git commit -m "Initial commit"` – Committed the staged files with a message.  
- `git push origin main` – Pushed the local repository to the remote GitHub repository.

**Steps Done**:  
- Created the repository structure and uploaded the initial codebase.  
- Set up webhook integration with Jenkins to automate pipeline execution upon code changes.  
- Regularly updated the repository with new commits to reflect progress.

**[Space for Screenshot]: Insert screenshot of GitHub repository (e.g., repo page showing files, commit history, and webhook settings)**

---

### 2. Development and Build of Java Calculator
**Brief**: The development phase involves writing the Java source code for the scientific calculator, compiling it into bytecode, and packaging it into a JAR file for use in the Docker container. This step forms the foundation of the application, implementing the required mathematical operations.

**Setup and Configuration**:  
- A Java file named `Sinc_calc.java` was created using a text editor (e.g., VS Code or Notepad++).  
- The code implements a menu-driven calculator with operations: square root (√x), factorial (x!), natural logarithm (ln(x)), and power function (x^b), using the `Scanner` class for user input and `Math` class for calculations.  
- Compilation was performed using the `javac` command, and the resulting class file was packaged into `Sinc_calc.jar` using the `jar` command.  
- The JAR file was verified to ensure it runs correctly before integration into the Docker workflow.

**Commands Used**:  
- `javac Sinc_calc.java` – Compiled the Java source code into `Sinc_calc.class`.  
- `jar cf Sinc_calc.jar Sinc_calc.class` – Created a JAR file from the compiled class.

**Steps Done**:  
- Wrote the Java code for the calculator with the following functionality:  
  - **Square Root**: Computes √x, with error handling for negative inputs.  
  - **Factorial**: Calculates x! for non-negative integers using a loop.  
  - **Natural Logarithm**: Computes ln(x) using `Math.log`, with error handling for non-positive inputs.  
  - **Power Function**: Raises x to the power b using `Math.pow`.  
- Compiled the code and packaged it into `Sinc_calc.jar`.  
- Tested the JAR file locally by running `java -jar Sinc_calc.jar` to confirm all operations work as expected.

**Java Code (`Sinc_calc.java`)**:  
```
import java.util.Scanner;

public class Sinc_calc {
   public Sinc_calc() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);

      while(true) {
         while(true) {
            System.out.println("\nScientific Calculator - Choose an operation:");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (x!)");
            System.out.println("3. Natural Logarithm (ln(x))");
            System.out.println("4. Power Function (x^b)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int var2 = var1.nextInt();
            switch (var2) {
               case 1:
                  System.out.print("Enter a number (x): ");
                  double var3 = var1.nextDouble();
                  if (var3 < 0.0) {
                     System.out.println("Error: Square root of a negative number is not real.");
                  } else {
                     System.out.println("Result: √" + var3 + " = " + Math.sqrt(var3));
                  }
                  break;
               case 2:
                  System.out.print("Enter a number (x): ");
                  int var5 = var1.nextInt();
                  if (var5 < 0) {
                     System.out.println("Error: Factorial of a negative number is not defined.");
                  } else {
                     System.out.println("Result: " + var5 + "! = " + factorial(var5));
                  }
                  break;
               case 3:
                  System.out.print("Enter a number (x): ");
                  double var6 = var1.nextDouble();
                  if (var6 <= 0.0) {
                     System.out.println("Error: Logarithm is not defined for zero or negative values.");
                  } else {
                     System.out.println("Result: ln(" + var6 + ") = " + Math.log(var6));
                  }
                  break;
               case 4:
                  System.out.print("Enter base (x): ");
                  double var8 = var1.nextDouble();
                  System.out.print("Enter exponent (b): ");
                  double var10 = var1.nextDouble();
                  System.out.println("Result: " + var8 + "^" + var10 + " = " + Math.pow(var8, var10));
                  break;
               case 5:
                  System.out.println("Exiting the calculator. Goodbye!");
                  var1.close();
                  System.exit(0);
               default:
                  System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
         }
      }
   }

   public static long factorial(int var0) {
      long var1 = 1L;

      for(int var3 = 1; var3 <= var0; ++var3) {
         var1 *= (long)var3;
      }

      return var1;
   }
}
```

**[Space for Screenshot]: Insert screenshot of Java code in editor, compilation output, and JAR file creation output**

---

### 3. Build
**Brief**: The build process involves preparing the compiled Java code for containerization by ensuring the JAR file is correctly integrated. This step bridges the development phase with the Dockerization process.

**Setup and Configuration**:  
- The compiled `Sinc_calc.class` was packaged into `Sinc_calc.jar` using the `jar` command.  
- The JAR file was copied into the project directory for inclusion in the Docker container via the `Dockerfile`.

**Commands Used**:  
- `jar cf Sinc_calc.jar Sinc_calc.class` – Created the JAR file (repeated here for context within the build step).

**Steps Done**:  
- Verified the `Sinc_calc.jar` file was successfully created.  
- Ensured the JAR was placed in the project directory for Docker integration.

**[Space for Screenshot]: Insert screenshot of JAR file in directory and verification output**

---

### 4. Continuous Integration (Jenkins)
**Brief**: Continuous Integration (CI) ensures that code changes are automatically built, tested, and validated. Jenkins, a powerful CI/CD tool, was configured to automate the pipeline for this project, integrating with GitHub and managing Docker and Ansible tasks. An attempt was made to implement email notifications, but this feature is not yet functional.

**Setup and Configuration**:  
- Jenkins was installed on a local machine, accessible via a web interface (e.g., `http://localhost:8080`).  
- A pipeline job was created, linked to the GitHub repository `https://github.com/Naval-Bisht/Mini-Project-Calc.git`.  
- The `DockerHubCred` secret was configured in Jenkins to authenticate with Docker Hub for image pushing.  
- An attempt was made to configure SMTP settings for email notifications using `smtp.gmail.com` on port 465 with SSL and credentials (e.g., `navalbisht444@gmail.com` and an App Password). However, the email feature remains incomplete due to configuration issues, as indicated by the pipeline output (`Not sent to the following valid addresses`).  
- Ngrok was used to expose the Jenkins server publicly, and GitHub webhooks were set up for GitSCM polling.

**Commands Used**:  
- N/A (configured via Jenkins web UI and Ngrok command line).

**Steps Done**:  
- Defined a `Jenkinsfile` to automate the pipeline with stages for Checkout, Build Docker Image, Push Docker Images, and Run Ansible Playbook.  
- Integrated GitHub webhooks to trigger builds on code pushes.  
- Used Ngrok to make Jenkins accessible for webhook communication.  
- Attempted to set up email notifications in the `post` section, but this requires further troubleshooting (e.g., verifying credentials, restarting Jenkins).

**Jenkinsfile**:  
```
pipeline {
    agent any
    triggers { 
        githubPush() 
    }
    environment {
        DOCKER_IMAGE_NAME = 'sinc_calc'
        GITHUB_REPO_URL = 'https://github.com/Naval-Bisht/Mini-Project-Calc.git'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE_NAME}", '.')
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh 'docker tag sinc_calc navalbisht444/test:latest'
                        sh 'docker push navalbisht444/test'
                    }
                }
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                script {
                    ansiblePlaybook(
                        playbook: 'deploy.yml',
                        inventory: 'inventory'
                    )
                }
            }
        }
    }
    

    post {
        always {
            emailext (
                subject: "Build ${currentBuild.fullDisplayName} - ${currentBuild.result}",
                body: """Build status: ${currentBuild.result}
                         Check details here: ${env.BUILD_URL}""",
                to: "navalbisht444@gmail.com"
            )
        }
    }
}
```

**[Space for Screenshot]: Insert screenshot of Jenkins pipeline configuration, DockerHubCred setup, Ngrok dashboard, webhook settings in GitHub, and attempted SMTP configuration (e.g., Extended E-mail Notification settings)]**

---

### 5. Containerization (Docker)
**Brief**: Containerization with Docker packages the calculator application and its dependencies into a portable container, ensuring consistency across environments. This project uses Docker to run the Java application locally within an Ubuntu-based container.

**Setup and Configuration**:  
- A `Dockerfile` was created, starting with `ubuntu:latest` as the base image.  
- OpenJDK 17 was installed to support Java execution, and `Sinc_calc.jar` was copied into the `/app` directory.  
- Port 8080 was exposed (though not actively used in this local setup), and the container was configured to run the JAR file.  
- The container was tested locally using `docker exec` to verify the calculator’s functionality.

**Commands Used**:  
- `docker build -t sinc_calc .` – Built the Docker image.  
- `docker run -it -d --name sinc_calc navalbisht444/test /bin/bash` – Ran the container in detached mode.  
- `docker exec -it sinc_calc java -cp Sinc_calc.jar Sinc_calc` – Executed the calculator inside the container.

**Steps Done**:  
- Developed and tested the `Dockerfile` to containerize the application.  
- Built and ran the `sinc_calc` image locally.  
- Pushed the image to Docker Hub as `navalbisht444/test:latest` for storage (though not deployed).  
- Confirmed the calculator works by running it within the container.

**Dockerfile**:  
```
# Use Ubuntu as the base image
FROM ubuntu:latest

# Install OpenJDK (Java Runtime Environment)
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY Sinc_calc.jar /app/sinc_calc.jar

# Expose a port if needed (Optional)
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "/app/Sinc_calc.jar"]
```

**[Space for Screenshot]: Insert screenshot of Docker build output, container running, and `docker exec` command output showing calculator execution**

---

### 6. Configuration Management (Ansible)
**Brief**: Ansible automates the configuration and management of the Docker container on the local machine, ensuring repeatable deployment steps. It is used here to pull and run the container without external deployment.

**Setup and Configuration**:  
- A local user `naval1` was created with SSH access on the localhost.  
- An `inventory` file was configured to define the Ansible target as `localhost` with the `naval1` user and password authentication.  
- The `deploy.yml` playbook was written to manage the Docker container lifecycle.

**Commands Used**:  
- N/A (executed via Ansible playbook through Jenkins)

**Steps Done**:  
- Developed the `deploy.yml` playbook to stop, remove, pull, and run the Docker container.  
- Configured the `inventory` file for local execution.  
- Integrated the playbook into the Jenkins pipeline for automated execution.  
- Verified the container runs successfully on the local machine.

**Ansible Playbook (`deploy.yml`)**:  
```
- name: Pull Docker Image and Run Container
  hosts: localhost
  remote_user: naval1
  become: false
  tasks:
    - name: Stop and Remove Existing sinc_calc Container (if exists)
      shell: |
        docker stop sinc_calc || true
        docker rm sinc_calc || true
      ignore_errors: yes

    - name: Remove Existing Docker Image (if exists)
      shell: docker rmi -f navalbisht444/test || true
      ignore_errors: yes

    - name: Pull Docker Image from Docker Hub
      docker_image:
        name: "navalbisht444/test"
        source: pull
      register: docker_pull_result

    - name: Display Docker Pull Result
      debug:
        var: docker_pull_result
        
    - name: Start Docker Service
      service:
        name: docker
        state: started

    - name: Run sinc_calc Container
      shell: docker run -it -d --name sinc_calc navalbisht444/test /bin/bash
```

**Inventory File**:  
```
ansible_host=localhost
ansible_user=naval1 
ansible_ssh_pass=naval1 
ansible_ssh_common_args='-o StrictHostKeyChecking=no'
```

**[Space for Screenshot]: Insert screenshot of Ansible playbook execution output and Docker container status**

---

### 7. Ngrok and GitHub Webhooks
**Brief**: Ngrok and GitHub webhooks enhance the pipeline by providing external access to Jenkins and automating build triggers, respectively. This setup is essential for local development with remote integration.

**Setup and Configuration**:  
- Ngrok was installed and configured to create a secure tunnel to the local Jenkins server (e.g., on port 8080), generating a public URL.  
- A GitHub webhook was set up in the repository settings to send POST requests to the Ngrok URL, triggering Jenkins builds on code pushes.

**Commands Used**:  
- `ngrok http 8080` – Started Ngrok to expose the Jenkins port.

**Steps Done**:  
- Configured Ngrok to make the local Jenkins instance accessible externally.  
- Set up and tested the GitHub webhook to ensure pipeline automation.  
- Monitored webhook delivery status to confirm successful integration.

**[Space for Screenshot]: Insert screenshot of Ngrok dashboard showing the public URL and GitHub webhook configuration page**

---

## Links
- **GitHub Repository**: `https://github.com/Naval-Bisht/Mini-Project-Calc.git`  
- **Docker Hub**: `https://hub.docker.com/r/navalbisht444/test`

---

## Scripts
- **Jenkinsfile**: [Provided above]  
- **Dockerfile**: [Provided above]  
- **Ansible Playbook (`deploy.yml`)**: [Provided above]  
- **Inventory File**: [Provided above]

---

## Scientific Calculator Application
**Brief**: The calculator is implemented in Java and packaged as `Sinc_calc.jar`, designed to run inside a Docker container. The application is confirmed to be working, supporting square root, factorial, natural logarithm, and power functions.

**Steps Done**:  
- Developed and compiled the Java calculator (`Sinc_calc.java`) into `Sinc_calc.jar`.  
- Integrated the JAR into the Docker container and executed it locally using `docker exec -it sinc_calc java -cp Sinc_calc.jar Sinc_calc`.  
- Verified the calculator’s functionality on the local machine, confirming all required operations.

**Screenshot**:  
**[Space for Screenshot]: Insert screenshot of calculator application output (e.g., menu and sample operations like square root, factorial, etc.)]**

---

## Submission Instructions
- Create a zipped folder named `[Your Roll Number].zip` (e.g., `MT2020xx.zip`).  
- Include:  
  - All screenshot images.  
  - This report as `[Your Roll Number].pdf` (e.g., `MT2020xx.pdf`), converted from the Word document.

---

### Final Notes
- The Java calculator is confirmed working and includes the required operations (square root, factorial, natural logarithm, and power functions).  
- Testing, Maven, and deployment are intentionally omitted, focusing on local execution with Docker, Ansible, and Jenkins.  
- The email notification in the `post` section was attempted but is not functional; further troubleshooting is needed (e.g., verifying SMTP credentials, restarting Jenkins).  
- Please provide screenshots for each step to fully document the process, ensuring a naive person can replicate it.

---

### How to Proceed
1. Copy the above text into a new Microsoft Word document.
2. Format the document (e.g., use Heading 1 for the title, Heading 2 for section titles, font size 12, 1-inch margins).
3. Insert screenshots in the **[Space for Screenshot]** sections.
4. Save as `[Your Roll Number].docx` (e.g., `MT2020xx.docx`).
5. Convert to PDF (e.g., `MT2020xx.pdf`) and zip with screenshot images into `[Your Roll Number].zip` (e.g., `MT2020xx.zip`).

If you need help with Word formatting, additional details, or have more screenshots to share, let me know, and I’ll assist further!