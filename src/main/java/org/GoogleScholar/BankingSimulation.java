package org.GoogleScholar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BankingSimulation - Sprint 3
 * Level: C2 Proficient Strategy Implementation
 *
 * This simulation validates banking transaction inputs based on complex business rules
 * and security patterns defined in the Equivalence Class Partitioning strategy.
 */
public class BankingSimulation {

    // --- INNER CLASSES ---

    // Data Transfer Object for Input
    static class InputData {
        String bankCode;
        String branchCode;
        String accountNumber;
        String personalKey;
        String orderValue;

        InputData(String bankCode, String branchCode, String accountNumber,
                  String personalKey, String orderValue) {
            this.bankCode = bankCode;
            this.branchCode = branchCode;
            this.accountNumber = accountNumber;
            this.personalKey = personalKey;
            this.orderValue = orderValue;
        }
    }

    // Validation Result Object
    static class ValidationResult {
        boolean isValid = true;
        List<String> errors = new ArrayList<>();
        String successMessage;

        void addError(String message) {
            errors.add(message);
            isValid = false;
        }
    }

    // --- MAIN EXECUTION ---

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   BANKING APP VALIDATION ENGINE (SPRINT 3)   ");
        System.out.println("=================================================\n");

        // 1. Run Automated Scenarios (Aligned with Sprint 2 Test Cases)
        runPredefinedScenarios();

        // 2. Interactive Mode for Exploratory Testing
        runInteractiveMode();
    }

    // --- SCENARIO MANAGEMENT ---

    private static void runPredefinedScenarios() {
        System.out.println(">>> Executing Representative Test Cases (from Sprint 2)...\n");

        // TC-01: Happy Path (Valid Checkbook Request)
        // Uses valid Region format for Branch and strong PIN
        InputData tc01 = new InputData("001", "N001", "1234567890", "951753", "CHECK");
        runScenario("TC-01: Valid Transaction (Checkbook)", tc01);

        // TC-05: Business Logic Error (Invalid Branch Region)
        // Tests the 'Z' region which doesn't exist in our business rules
        InputData tc05 = new InputData("001", "Z001", "1234567890", "951753", "CHECK");
        runScenario("TC-05: Invalid Branch Region (Logic Error)", tc05);

        // TC-03: Security Risk (Weak PIN)
        // Tests the innovative security validation for sequential patterns
        InputData tc03 = new InputData("001", "N001", "1234567890", "123456", "CHECK");
        runScenario("TC-03: Security Alert (Weak PIN Sequence)", tc03);

        System.out.println(">>> Automated scenarios completed.\n");
    }

    private static void runScenario(String label, InputData input) {
        System.out.println("--- Scenario: " + label + " ---");
        System.out.printf("Input: [Bank:%s] [Branch:%s] [Acc:%s] [Key:****] [Order:%s]%n",
                input.bankCode, input.branchCode, input.accountNumber, input.orderValue);

        ValidationResult result = validate(input);

        if (result.isValid) {
            System.out.println("[RESULT]: ✅ SUCCESS");
            System.out.println("System Message: " + result.successMessage);
        } else {
            System.out.println("[RESULT]: ❌ REJECTED");
            System.out.println("Errors found:");
            for (String err : result.errors) {
                System.out.println(" - " + err);
            }
        }
        System.out.println();
    }

    // --- VALIDATION LOGIC (THE CORE STRATEGY) ---

    private static ValidationResult validate(InputData input) {
        ValidationResult result = new ValidationResult();

        // 1. BANK CODE VALIDATION
        // Rule: 3 digits, numeric.
        if (input.bankCode == null || !input.bankCode.matches("\\d{3}")) {
            result.addError("Bank Code must be exactly 3 digits (Numeric).");
        }

        // 2. BRANCH CODE VALIDATION (C2 BUSINESS LOGIC)
        // Rule: Must start with Region (N,S,E,O) followed by 3 digits.
        // Innovation: We don't just accept any 4 chars; we validate structure.
        if (input.branchCode == null || !input.branchCode.matches("[NSEO]\\d{3}")) {
            result.addError("Invalid Branch Code. Format must be Region (N,S,E,O) + 3 Digits (e.g., N001).");
        }

        // 3. ACCOUNT NUMBER VALIDATION
        // Rule: 10 digits, numeric.
        if (input.accountNumber == null || !input.accountNumber.matches("\\d{10}")) {
            result.addError("Account Number must be exactly 10 digits.");
        }

        // 4. PERSONAL KEY VALIDATION (C2 SECURITY STRATEGY)
        // Rule: 6 Digits, No Sequences, No Repeats.
        if (input.personalKey == null || !input.personalKey.matches("\\d{6}")) {
            result.addError("Personal Key must be exactly 6 digits.");
        } else {
            // Advanced Security Checks
            if (isSequence(input.personalKey)) {
                result.addError("Security Alert: PIN cannot be a sequence (e.g., 123456).");
            }
            if (isRepeated(input.personalKey)) {
                result.addError("Security Alert: PIN cannot be repeated numbers (e.g., 111111).");
            }
        }

        // 5. ORDER VALUE VALIDATION (C2 USABILITY)
        // Rule: 'CHECK' or 'STMT' (Case insensitive).
        if (input.orderValue == null) {
            result.addError("Order Value is required.");
        } else {
            String order = input.orderValue.toUpperCase();
            if (order.equals("CHECK")) {
                result.successMessage = "Checkbook request processed successfully.";
            } else if (order.equals("STMT")) {
                result.successMessage = "Monthly Statement generated successfully.";
            } else {
                result.addError("Invalid Order. Allowed values: 'CHECK' or 'STMT'.");
            }
        }

        return result;
    }

    // --- HELPER METHODS FOR SECURITY ---

    private static boolean isRepeated(String pin) {
        // Checks if all characters are the same (e.g. 888888)
        return pin.chars().distinct().count() == 1;
    }

    private static boolean isSequence(String pin) {
        // Checks for ascending sequence (123456)
        String forward = "0123456789";
        String reverse = "9876543210";
        return forward.contains(pin) || reverse.contains(pin);
    }

    // --- INTERACTIVE MODE ---

    private static void runInteractiveMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(">>> Entering Interactive Mode. Type 'EXIT' in Bank Code to quit.");

        while (true) {
            System.out.print("\nEnter Bank Code (3 digits): ");
            String bank = scanner.nextLine().trim();
            if (bank.equalsIgnoreCase("EXIT")) break;

            System.out.print("Enter Branch Code (N/S/E/O + 3 digits): ");
            String branch = scanner.nextLine().trim();

            System.out.print("Enter Account Number (10 digits): ");
            String acc = scanner.nextLine().trim();

            System.out.print("Enter Personal Key (6 digits): ");
            String key = scanner.nextLine().trim();

            System.out.print("Enter Order Value (CHECK/STMT): ");
            String order = scanner.nextLine().trim();

            runScenario("Manual User Test", new InputData(bank, branch, acc, key, order));
        }
        scanner.close();
        System.out.println("Simulation Terminated.");
    }
}