<img src="https://github.com/user-attachments/assets/b55dab32-f1a9-42ca-bdc2-3ae0364208a9" width="100" height="100"> 

# LAMB DB 
### **Layered, Atomic, Map-Based Database.** 

**Lamb DB** is a foundational database management system (DBMS) implemented in Java. It provides a simple key-value storage mechanism, offering a glimpse into the inner workings of high-end DBMS systems. Lamb DB leverages Java's file I/O, buffer, and stream functionalities to efficiently store and retrieve data.

### Table of Contents

* [Project Overview](#project-overview)
* [Key Features](#key-features)
* [Technologies Used](#technologies-used)
* [Implementation Details](#implementation-details)
    * [Data Structure](#data-structure)
    * [File I/O](#file-io)
    * [Persistence](#persistence)
    * [Querying](#querying)
* [Conclusion](#conclusion)
* [Getting Started](#getting-started)
* [Contributing](#contributing)
* [License](#license)

### Key Features

* **Key-Value Storage:** Stores data in a key-value format, allowing for quick and efficient retrieval.
* **File-Based Persistence:** Persists data to disk for long-term storage.
* **Java 8+ Compatibility:** Utilizes Java 8+ features for improved performance and readability.
* **Basic Querying:** Provides basic querying capabilities, including insert, select, update, and delete operations.

### Technologies Used

* Java: Core programming language for the project.
* File I/O: For reading and writing data to files.
* Buffers and Streams: For efficient data handling and processing.
* Data Structures: HashMap and other custom data structures for storing key-value pairs.

### Implementation Details


**Database Service:**

* Manages overall database operations.
* Coordinates document and entry services.

**Document Service:**

* Manages documents within a database.
* Handles document creation, retrieval, update, and deletion.

**Entry Service:**

* Manages entries within a document.
* Handles entry creation, retrieval, update, and deletion.
* Stores entries in a HashMap within a document object.
* Uses `JSONParser` for parsing and serialization.

**Data Structures:**

* **HashMap:** Stores entries within a document, keyed by entry key.
* **JSON Object:** Represents documents and entries.

**File I/O:**

* Stores database and document data in JSON files.
* Uses `JSONParser` for parsing and serialization.
* Handles file creation, reading, writing, and closing.

**Querying:**

* Provides basic CRUD operations for documents and entries.
* Retrieves entries based on their keys.

**Additional Considerations:**

* **Error Handling:** Handles exceptions like file I/O errors, parsing errors, and invalid data.
* **Concurrency:** Ensures thread safety and prevents data corruption in a multi-threaded environment.

### Conclusion

Lamb DB, a foundational database management system implemented in Java, effectively demonstrates the core concepts of key-value storage. By utilizing a B-Tree data structure and efficient file I/O operations, Lamb DB provides a robust and scalable solution for managing key-value pairs.

While the prototype implementation focuses on essential features like CRUD operations and persistence, there is room for further enhancements. Incorporating features such as indexing, transactions, and security can significantly improve the database's capabilities and make it suitable for more complex applications.

Overall, Lamb DB serves as a valuable learning tool for understanding the fundamentals of database systems and provides a solid foundation for building more advanced database solutions.


## Getting Started

**Prerequisites:**

* Java 8+ installed on your system.

**Installation:**

1. **Clone the repository:**
   
   ```bash
   git clone [https://github.com/awkward-student/lamb_db.git](https://github.com/awkward-student/lamb_db.git)
   ```
2. **Clean Install Maven**
3. **Good to go**

**Usage:**

1. **Use the pre_build release**
  
2. **Run the lamb_db.jar file**
   
   ```java
   java -jar lamb_db.jar
   ```

## Contributing

To make contributions to Lamb DB, please follow these guidelines:

1. **Fork the repository:** Create a fork of the Lamb DB repository on GitHub. This will allow you to make your changes independently.
2. **Create a new branch:** Create a new branch in your forked repository to isolate your changes. This helps keep your main branch clean and allows you to easily switch between different features or bug fixes.
3. **Make your changes:** Implement your desired changes or bug fixes in your new branch. Make sure to follow the project's coding style and conventions.
4. **Test your changes:** Thoroughly test your changes to ensure they work as expected and don't introduce any new   
 bugs.
5. **Submit a pull request:** Once you're satisfied with your changes, submit a pull request to the main branch of the original repository. Provide a clear description of your changes and address any feedback or suggestions from reviewers.

**Additional tips:**

* **Follow the project's coding style:** Adhere to the project's established coding conventions and formatting guidelines.
* **Write clear and concise commit messages:** Use informative commit messages that describe the changes you've made.
* **Be patient:** The review process may take some time, so be patient and responsive to feedback.

### License

Lamb DB is licensed under the [MIT License](https://opensource.org/licenses/MIT).


## CLI Media / Interface 

1. Intro and help text:
<img src="https://github.com/user-attachments/assets/010a6181-a400-49e4-be0b-7db428af7817">
<br>

2. System and DB level command usage:
<img src="https://github.com/user-attachments/assets/e2f9991c-c0ab-421f-ae70-320c34a2ddae" width="450" height="250">
<br>

3. DB and Document / Collection level command usage:
<img src="https://github.com/user-attachments/assets/7dd73a78-7c1b-4bb4-9e3a-e0b74bf49bfd" width="450" height="200">
<br>

4. The select query and system exit:
<img src="https://github.com/user-attachments/assets/ace522c0-4c09-4451-9388-dc6050e005a4" width="400" height="200">
<br>



![]()

**A Prototype! ♻️** - Lamb DB can be further enhanced by implementing features like secondary indexing, transactions, and security measures. These additions will improve its capabilities, making it suitable for more complex use cases and ensuring data integrity and protection. Additionally, exploring query optimization techniques, data export/import options, and replication/high availability mechanisms can further enhance Lamb DB's performance and reliability.
