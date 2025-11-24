package com.rem.banking.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.rem.banking.model.TransactionData;

/**
 * Core validation engine for the Banking Application.
 * <p>
 * This class implements the "Equivalence Class Partitioning" strategy defined in Sprint 1.
 * It validates inputs against business rules and external configurations.
 * </p>
 */
public class InputValidator {

    private String allowedRegions;

    /**
     * Initializes the validator and loads dynamic configuration.
     * <p>
     * Attempts to read 'application.properties' to fetch the allowed Branch Regions.
     * This simulates an Environment Variable approach, allowing business rules
     * to change without recompiling the source code.
     * </p>
     */
    public InputValidator() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("LOG: Configuration file not found. Using default values.");
                allowedRegions = "N,S,E,O";
            } else {
                prop.load(input);
                allowedRegions = prop.getProperty("bank.valid.regions");
            }
        } catch (Exception ex) {
            System.err.println("LOG: Error loading configuration. Defaulting to standard regions.");
            allowedRegions = "N,S,E,O";
        }
    }

    /**
     * Validates a TransactionData object against all defined business rules.
     *
     * @param data The transaction data object to validate.
     * @return A List of error strings. If the list is empty, the transaction is valid.
     */
    public List<String> validate(TransactionData data) {
        List<String> errors = new ArrayList<>();

        // 1. BANK CODE VALIDATION
        // Rule: Must be exactly 3 numeric digits.
        if (data.getBankCode() == null || !data.getBankCode().matches("\\d{3}")) {
            errors.add("Bank Code must be exactly 3 digits.");
        }

        // 2. BRANCH CODE VALIDATION (Dynamic Business Rule)
        // Rule: Must start with a valid Region letter followed by 3 digits (e.g., N001).
        String regionRegex = "[" + allowedRegions.replace(",", "") + "]\\d{3}";
        if (data.getBranchCode() == null || !data.getBranchCode().matches(regionRegex)) {
            errors.add("Invalid Branch Code. Format must be Region (" + allowedRegions + ") + 3 Digits.");
        }

        // 3. ACCOUNT NUMBER VALIDATION
        // Rule: Must be exactly 10 numeric digits.
        if (data.getAccountNumber() == null || !data.getAccountNumber().matches("\\d{10}")) {
            errors.add("Account Number must be exactly 10 digits.");
        }

        // 4. PERSONAL KEY VALIDATION (Enhanced Security)
        // Rule: Must be 6 digits AND pass heuristic security checks.
        if (data.getPersonalKey() == null || !data.getPersonalKey().matches("\\d{6}")) {
            errors.add("Personal Key must be exactly 6 digits.");
        } else {
            // Integration with SecurityService to detect fraud patterns
            if (SecurityService.isWeakPin(data.getPersonalKey())) {
                errors.add("Security Alert: Weak PIN detected (Sequence or Repeated numbers).");
            }
        }

        // 5. ORDER VALUE VALIDATION
        // Rule: Must be "CHECK" or "STMT" (Case Insensitive).
        if (data.getOrderValue() == null) {
            errors.add("Order Value is required.");
        } else {
            String order = data.getOrderValue().toUpperCase();
            if (!order.equals("CHECK") && !order.equals("STMT")) {
                errors.add("Invalid Order. Allowed values: 'CHECK' or 'STMT'.");
            }
        }

        return errors;
    }
}