Banking Application Simulation – Sprint 3
========================================

1. Overview
-----------
This Java program simulates a simple online banking application used to validate
input data for banking operations. The script was developed as part of the
“Testing Procedures for Operational Issues” challenge (Sprint 3).

The simulation validates the following input fields:
- Bank code (3 digits, range 001–999, not 000)
- Branch code (4 digits, range 0001–9999, not 0000)
- Account number (10 digits)
- Personal key (4-digit PIN)
- Order value (1 = checkbook request, 2 = monthly account statement request)

The validation rules are based on the equivalence classes and test cases
designed in Sprints 1 and 2.

2. Files
--------
- BankingSimulation.java   -> Main Java file that contains the full simulation.
- README_Sprint3.txt       -> This documentation file.

3. How to compile and run
-------------------------
Prerequisites:
- Java Development Kit (JDK) version 8 or higher installed.
- A terminal or command prompt.

Steps:
1) Compile the Java file:

   javac BankingSimulation.java

2) Run the program:

   java BankingSimulation

4. Program behavior
-------------------
When executed, the program:

1) Runs three predefined test scenarios based on Sprint 2:
   - TC01: Valid transaction (checkbook request).
   - TC02: Boundary values (account statement request).
   - TC03: Invalid transaction (bank code = 000).

   For each scenario, the program:
   - Prints the input values.
   - Validates each field using the equivalence class rules.
   - Displays “SUCCESS” with a description of the operation,
     or “ERROR” with a list of detailed error messages.

2) Enables an interactive mode where a user can manually type values
   for bank code, branch code, account number, personal key, and order value.
   - The user can test different combinations of valid and invalid data.
   - Typing 'exit' as the bank code ends the interactive mode.

5. Validation rules implemented
-------------------------------
- Bank code:
  * Required, exactly 3 digits.
  * Only numeric characters.
  * Must not be "000".

- Branch code:
  * Required, exactly 4 digits.
  * Only numeric characters.
  * Must not be "0000".

- Account number:
  * Required, exactly 10 digits.
  * Only numeric characters.

- Personal key:
  * Required, exactly 4 digits.
  * Only numeric characters.

- Order value:
  * Required, numeric.
  * Must be 1 (checkbook) or 2 (account statement).

6. Main findings and best practices
-----------------------------------
- Applying equivalence class partitioning in the implementation phase
  helps keep the validation logic simple, readable, and aligned with
  the test design from previous Sprints.
- Separating validation logic into a dedicated method (validate)
  improves maintainability and makes it easier to relate each rule
  to specific equivalence classes.
- Providing clear and specific error messages (per field) improves
  user experience and supports faster resolution of operational issues.
- Using predefined scenarios and interactive input allows both
  regression testing (repeatable cases) and exploratory testing
  (new input combinations).

7. Third-party review
---------------------
This script and the README are designed so that any third party with basic
Java knowledge can:
- Compile and run the program using the provided instructions.
- Understand the validation logic and adapt it if the business rules change.
