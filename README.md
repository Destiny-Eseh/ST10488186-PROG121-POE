# ST10488186-PROG121-POE

This project is part of my PROG121 Practical. 
It is a Java program that allows users to register, log in, and send messages.

Part 1: User Registration
- Users can create an account by entering a username, password, and phone number.
- The program checks if the details are valid before saving.
- After registration, users can log in using their details.

Part 2: Messaging
- After logging in, users can send messages.
- Each message has:
  - Message ID (max 10 characters)
  - Recipient number (starts with 0 and max 10 digits)
  - Message text (max 250 characters)
- Valid messages are stored in a JSON file called messages.json.

Testing
- JUnit tests were created to check user registration, login, and message validation.
- Test files: UserRegistrationTest.java, MessageTest.java, and MessageManagementTest.java

Part 3: Message Management
- After writing a message, users can choose to:
  - Store the message
  - Discard the message
  - Send the message
- Incorrect message formats do not crash the program.
- Users are allowed to try again until the message is valid.
- JSON storage includes attribution: “ChatGPT: Message is stored as JSON File”.

