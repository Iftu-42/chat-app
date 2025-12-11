# Olive Chat App 🌿

A simple **Java GUI Chat Application** using **Java Sockets** and **Swing GUI**.  
Allows multiple clients to chat in real-time with an **olive-green themed interface**.

---

## Features

- Multi-client chat using Java Sockets  
- GUI built with Java Swing  
- Send/receive messages in real-time  
- Olive-green theme  
- Easy to run for class demo

---

## How to Run

### 1. Run Server
```bash
javac ChatServer.java
java ChatServer
````

* Server listens on port **1234**

### 2. Run Client

```bash
javac ChatClient.java
java ChatClient
```

* Click **Connect** to join
* Open multiple clients to test chat

---

## How It Works

* **Server** manages clients with threads
* **Clients** send messages → server broadcasts to all clients
* GUI provides message area + input field + send button

---

## Folder Structure

```
javapro/
│── ChatServer.java
│── ChatClient.java
│── README.md
```


---

## Author

**Zuhal** – [GitHub: Iftu-42](https://github.com/Iftu-42)

💚
