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
Prepared by: Luis David Gutierrez Martinez & Leonel Campos Valdes
Digital NAO – Software Quality Challenge
