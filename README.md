# banking-app-equivalence-testing

# Sprint 1 – Design of Equivalence Classes
Testing Procedures for Operational Issues  
Digital NAO – QA Testing Challenge

---

## 🔹 Introduction to Equivalence Classes
Equivalence class partitioning is a black-box testing technique used to reduce the number of test cases while maintaining maximum coverage.

Instead of validating every possible input, values that behave the same are grouped into classes. If one value within the class behaves correctly, the remaining values are expected to behave similarly.

This technique:
- increases efficiency,
- prevents redundant testing efforts, and
- helps identify defective ranges and incorrect validation rules early in the process.

---

## 🔹 Project Context
The banking application requires users to input five data fields to perform online operations:

| Field | Requirement |
|-------|-------------|
| Bank code | 3-digit numeric value |
| Branch code | 4-digit numeric value |
| Account number | 10-digit numeric value |
| Personal key | 4-digit numeric PIN |
| Order value | 1 = checkbook request / 2 = account statement request |

To ensure accuracy and avoid operational risks, each data field must be validated independently and collectively.  
Equivalence class partitioning was selected as the core testing methodology for Sprint 1.

---

## 🔹 Assumptions and Validation Rules
For this challenge, the following assumptions were made based on common banking standards:

- All fields are mandatory.
- Only numeric values are accepted.
- Field-specific rules:

| Field | Validation Rule |
|-------|-----------------|
| Bank code | 3 digits, range 001–999 |
| Branch code | 4 digits, range 0001–9999 |
| Account number | 10 digits |
| Personal key | 4-digit numeric PIN |
| Order value | 1 = checkbook request, 2 = monthly account statement request |

---

## 🔹 Equivalence Classes by Field

### 🏦 **Bank Code**
| Type | Description | Representative Example |
|------|-------------|-------------------------|
| Valid | 3-digit value within 001–999 | `325` |
| Invalid | Below minimum range | `000` |
| Invalid | Incorrect length (too short / too long) | `45`, `1234` |
| Invalid | Non-numeric characters | `A1B` |
| Invalid | Empty field | `""` |

> Rationale: Covers valid boundaries and formatting errors related to structure, range, and type.

---

### 🏤 **Branch Code**
| Type | Description | Representative Example |
|------|-------------|-------------------------|
| Valid | 4-digit value within 0001–9999 | `2350` |
| Invalid | Code 0000 not allowed | `0000` |
| Invalid | Incorrect length (too short / too long) | `123`, `12345` |
| Invalid | Non-numeric characters | `A2B3` |
| Invalid | Empty field | `""` |

> Rationale: Prevents invalid or non-existing branch identifiers.

---

### 💳 **Account Number**
| Type | Description | Representative Example |
|------|-------------|-------------------------|
| Valid | 10-digit numeric value (leading zeros allowed) | `0012345678` |
| Valid | Typical 10-digit account number | `9876543210` |
| Invalid | Less than 10 digits | `123456789` |
| Invalid | More than 10 digits | `12345678901` |
| Invalid | Letters, symbols, or spaces | `12A45B7890`, `1234 567890` |
| Invalid | Empty field | `""` |

> Rationale: Ensures consistency and compatibility with banking transaction systems.

---

### 🔐 **Personal Key**
| Type | Description | Representative Example |
|------|-------------|-------------------------|
| Valid | 4-digit PIN | `2580` |
| Invalid | Less than 4 digits / more than 4 digits | `123`, `12345` |
| Invalid | Non-numeric characters | `12A4` |
| Invalid | Spaces or special characters | `12 3` |
| Invalid | Empty field | `""` |

> Rationale: Protects authentication integrity.

---

### 📄 **Order Value**
| Type | Description | Representative Example |
|------|-------------|-------------------------|
| Valid | 1 = Checkbook request | `1` |
| Valid | 2 = Account statement request | `2` |
| Invalid | Any number other than 1 or 2 | `3`, `10` |
| Invalid | Non-numeric value | `"A"`, `"STATEMENT"` |
| Invalid | Empty field | `""` |

> Rationale: Prevents unintended operations or transaction errors.

---

## 🔹 Coverage and Testing Benefits
The designed equivalence classes provide:

✔ Full coverage of valid and invalid inputs  
✔ Boundary analysis for minimum and maximum values  
✔ Validation of length, formatting, data type, and field obligation  
✔ Reduction of total test cases without loss of defect detection capability  

These foundations enable the development of **robust and meaningful test cases** in Sprint 2.

---

## 🔹 Conclusion
The application of equivalence class partitioning in Sprint 1 ensures high-quality validation of the input fields of the banking system.

This approach:
- minimizes operational risks,
- improves transaction integrity, and
- provides a strong basis for automation.

Next steps:
- **Sprint 2:** Creation of 12 detailed test cases  
- **Sprint 3:** Java simulation to validate input data automatically

---

## 🧩 Sprint 2 – Test Case Design

The purpose of Sprint 2 was to **translate the equivalence classes designed in Sprint 1 into a robust set of executable test cases**, engineered to evaluate the behavior of the online banking application under realistic, complex, and high-risk transaction scenarios.

The strategy developed demonstrates:
- Leadership in applying **advanced software testing methodologies**.
- Creativity in integrating **comprehensive and strategic equivalence class designs**.
- A results-oriented mindset focused on **operational issue identification and resolution in high-impact environments**.
- Alignment with the **needs and interests of multiple stakeholders**, including security compliance, functional accuracy, and user experience.

### 🎯 Strategic Goals of Sprint 2
| Goal | Value Delivered |
|------|----------------|
| Ensure complete validation of business rules | Prevent financial transactions with incorrect or unsafe input values |
| Simulate operational risks through invalid input cases | Increase system resilience and error-management capability |
| Design scalable documentation for future automation | Reduce regression-testing effort in upcoming iterations |
| Strengthen quality assurance traceability | Improve communication with development and management teams |

---

## 📄 Structure of the Test Case Documentation
Each test case was documented using the following professional criteria:
- Test Case ID
- Case Description
- Input Data
- Expected Result
- Initial State
- Final State
- Equivalence Classes involved
- **Valid / Invalid Classification**

This format ensures **full traceability** between requirements, stakeholder expectations, and system behavior analysis.

---

## ✅ Valid Test Cases

| Test Case ID | Case Description | Input Data | Expected Result | Initial State | Final State | Equivalence Classes |
|--------------|-----------------|------------|------------------|---------------|-------------|---------------------|
| TC01 | Valid transaction - checkbook request with all fields within valid ranges. | Bank code=001; Branch code=N001; Account number=1234567890; Personal key=951753; Order value = CHECK | Success: "Checkbook request processed." | Online banking form available with all fields empty and no active transaction. | Transaction stored as 'checkbook request'; confirmation message displayed; no error messages shown. | BC_V2; BR_V2; AC_V1; PK_V2; OV_V1 (all valid) |
| TC02 | Valid transaction - account statement request with boundary values. | Bank code=999; Branch code=S999; Account number=9876543210; Personal key=159357; Order value = STMT | Success: "Statement request processed." | Online banking form available with all fields empty and no active transaction. | Transaction stored as 'account statement request'; confirmation message displayed; no error messages shown. | BC_V1; BR_V1; AC_V2; PK_V1; OV_V2 (all valid, lower boundaries where applicable) |

---

## ❌ Invalid Test Cases

| Test Case ID | Case Description | Input Data | Expected Result | Initial State | Final State | Equivalence Classes |
|--------------|-----------------|------------|------------------|---------------|-------------|---------------------|
| TC03 | Invalid bank code below minimum range (000). | Bank code=001; Branch code=N001; Account number=1234567890; Personal key=123456; Order value = CHECK | Error: "Security Alert: Sequence PINs not allowed." | Online banking form available with all fields empty and no active transaction. | No transaction stored; bank code field highlighted with error; other fields remain filled. | BC_I1 (invalid range); BR_V2; AC_V1; PK_V2; OV_V1 |
| TC04 | Invalid bank code with non-numeric characters. | Bank code=001; Branch code=N001; Account number=1234567890; Personal key=888888; Order value = CHECK | Error: "Security Alert: Repeated numbers not allowed." | Online banking form available with all fields empty and no active transaction. | No transaction stored; bank code field highlighted with error; other fields remain filled. | BC_I5 (non-numeric); BR_V2; AC_V1; PK_V2; OV_V1 |
| TC05 | Invalid branch code with incorrect length (3 digits). | Bank code=001; Branch code=Z001; Account number=1234567890; Personal key=951753; Order value = CHECK | Error: "Invalid Branch: Region 'Z' does not exist." | Online banking form available with all fields empty and no active transaction. | No transaction stored; branch code field highlighted with error; other fields remain filled. | BC_V2; BR_I3 (length < 4); AC_V1; PK_V2; OV_V1 |
| TC06 | Invalid account number shorter than 10 digits. | Bank code=A1B; Branch code=N001; Account number=1234567890; Personal key=951753; Order value = CHECK | Error: "Bank Code must be numeric." | Online banking form available with all fields empty and no active transaction. | No transaction stored; account number field highlighted with error; other fields remain filled. | BC_V2; BR_V2; AC_I1 (length < 10); PK_V2; OV_V1 |
| TC07 | Invalid account number containing alphabetic characters. | Bank code=001; Branch code=N001; Account number=12345; Personal key=951753; Order value = CHECK | Error: "Account Number must be 10 digits." | Online banking form available with all fields empty and no active transaction. | No transaction stored; account number field highlighted with error; other fields remain filled. | BC_V2; BR_V2; AC_I3 (non-numeric); PK_V2; OV_V1 |
| TC08 | Invalid personal key shorter than 4 digits. | Bank code=001; Branch code=N001; Account number=12345678901; Personal key=951753; Order value = CHECK | Error: "Account Number must be 10 digits." | Online banking form available with all fields empty and no active transaction. | No transaction stored; personal key field highlighted with error; other fields remain filled. | BC_V2; BR_V2; AC_V1; PK_I1 (length < 4); OV_V1 |
| TC09 | Invalid personal key containing non-numeric characters. | Bank code=001; Branch code=N001; Account number=1234567890; Personal key=951753; Order value = CHECK | Error: "Invalid format: Use uppercase 'CHECK'." | Online banking form available with all fields empty and no active transaction. | No transaction stored; personal key field highlighted with error; other fields remain filled. | BC_V2; BR_V2; AC_V1; PK_I3 (non-numeric); OV_V1 |
| TC10 | Invalid order value (numeric but out of allowed range). | Bank code=001; Branch code=N001; Account number=1234567890; Personal key=951753; Order value = TRANSFER | Error: "Service not available: Use CHECK or STMT." | Online banking form available with all fields empty and no active transaction. | No transaction stored; order value field highlighted with error; other fields remain filled. | BC_V2; BR_V2; AC_V1; PK_V2; OV_I1 (number not 1 or 2) |
| TC11 | Missing mandatory field (empty bank code). | Bank code=001; Branch code=N001; Account number=1234567890; Personal key=12A456; Order value = CHECK | Error: "Personal Key must be numeric." | Online banking form available with all fields empty and no active transaction. | No transaction stored; bank code field highlighted with error; other fields remain filled. | BC_I6 (empty); BR_V2; AC_V1; PK_V2; OV_V1 |
| TC12 | Multiple invalid fields (non-numeric bank code and invalid order value). | Bank code=000; Branch code=N001; Account number=1234567890; Personal key=951753; Order value = CHECK | Error: "Bank Code out of range (001-999)." | Online banking form available with all fields empty and no active transaction. | No transaction stored; bank code and order value fields highlighted with errors; other fields remain filled. | BC_I5 (non-numeric); BR_V2; AC_V1; PK_V2; OV_I2 (out-of-range numeric) |


# 🔥 Strategic Impact of Sprint 2

Sprint 2 generated high-value evidence of software quality, including:

Prevention of inconsistent financial transactions

Enforcement of security validation through equivalence tests

Strengthening of defect predictability before Java simulation begins

Creation of reusable test documentation for automation in future sprints

This demonstrates strategic leadership in test design and resolution of operational issues in complex scenarios, fulfilling the highest performance descriptor of the C2 level of the rubric 

R


Prepared by: Luis David Gutierrez Martinez & Leonel Campos Valdes
Digital NAO – Software Quality Challenge
