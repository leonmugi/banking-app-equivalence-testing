package com.rem.banking;

import java.util.List;

import com.rem.banking.core.InputValidator;
import com.rem.banking.model.TransactionData;

/**
 * Main Entry Point for the Banking Simulation (Sprint 3).
 * <p>
 * This class orchestrates the simulation by creating representative test cases
 * (Valid, Boundary, Invalid) and passing them through the {@link InputValidator}.
 * It demonstrates the integration of the modular architecture.
 * </p>
 * * @author [Your Name]
 * @version 2.0-C2
 */
public class BankingApp {

    /**
     * The main method that executes the simulation.
     * @param args Command line arguments (not used in this simulation).
     */
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   BANKING APP VALIDATION ENGINE (SPRINT 3)   ");
        System.out.println("   Modular Architecture - C2 Level Strategy   ");
        System.out.println("=================================================\n");
        
        // Initialize the validator (loads config internally)
        InputValidator validator = new InputValidator();
        
        // --- SCENARIO 1: HAPPY PATH ---
        // Description: A completely valid transaction request for a checkbook.
        System.out.println(">>> Executing Scenario 1: Valid Transaction (Happy Path)");
        TransactionData validTransaction = new TransactionData(
            "001",          // Bank Code
            "N001",         // Branch Code (Valid Region 'N')
            "1234567890",   // Account (10 digits)
            "951753",       // PIN (Strong)
            "CHECK"         // Order
        );
        processTransaction(validator, validTransaction);
        
        // --- SCENARIO 2: BUSINESS LOGIC ERROR ---
        // Description: A branch code with an invalid region 'Z' (Simulating logic error).
        System.out.println(">>> Executing Scenario 2: Invalid Branch Region (Logic Error)");
        TransactionData invalidRegion = new TransactionData(
            "001", "Z999", "1234567890", "951753", "CHECK"
        );
        processTransaction(validator, invalidRegion);

        // --- SCENARIO 3: SECURITY ALERT ---
        // Description: A valid format PIN, but rejected due to weak security pattern (Sequence).
        System.out.println(">>> Executing Scenario 3: Weak PIN (Security Alert)");
        TransactionData weakPin = new TransactionData(
            "001", "N001", "1234567890", "123456", "CHECK"
        );
        processTransaction(validator, weakPin);
    }

    /**
     * Helper method to process and print results for a transaction.
     * * @param validator The validator instance to use.
     * @param data      The transaction data to validate.
     */
    private static void processTransaction(InputValidator validator, TransactionData data) {
        System.out.printf("   Input Data: [Bank:%s] [Branch:%s] [Acc:%s] [Order:%s]%n", 
            data.getBankCode(), data.getBranchCode(), data.getAccountNumber(), data.getOrderValue());

        List<String> results = validator.validate(data);
        
        if(results.isEmpty()) {
            System.out.println("   [RESULT]: ✅ TRANSACTION APPROVED\n");
        } else {
            System.out.println("   [RESULT]: ❌ TRANSACTION REJECTED");
            System.out.println("   Errors found:");
            for (String error : results) {
                System.out.println("    - " + error);
            }
            System.out.println();
        }
    }
}