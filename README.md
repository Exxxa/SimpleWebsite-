# Individual Project Setup Guide

Welcome to the INF4051 Individual Project! This guide will assist you in setting up your project repository on GitLab, creating a branch, generating a Spring Boot project, and pushing it to your branch.

## Step 1: Clone the Project Repository

Open a terminal or command prompt and perform the following steps:
1. Navigate to the directory where you want to place the project.
2. Clone the repository:
```bash
git clone https://gitlab.esiea.fr/inf4051-students-2024/individual-project.git
```
3. Change into the newly created directory:
```bash
cd individual-project
```

## Step 2: Create Your Branch

Create a new branch using your surname and name. Replace `Surname` and `Name` with your actual surname and name:
```bash
git checkout -b Surname_Name
```


## Step 3: Generate Your Spring Boot Project

1. Go to [Spring Initializer](https://start.spring.io/).
2. Choose the desired settings for your project (e.g., Maven/Gradle, Java version, dependencies).
3. Click on "Generate" to download the project zip file.

## Step 4: Extract and Integrate the Spring Boot Project

After downloading the Spring Boot project, follow these steps:
1. Extract the downloaded zip file.
2. Copy the extracted content into the `individual-project` directory.
3. Add the files to your Git repository:
```bash
git add .
```
4. Commit the changes:
```bash
git commit -m "Initial project setup"
```

## Step 5: Push Your Branch to GitLab

Push your branch to the GitLab repository:
```bash
git push origin Surname_Name
```
Replace `Surname_Name` with the branch name you created earlier.

## Conclusion

Congratulations! You have successfully set up your individual project