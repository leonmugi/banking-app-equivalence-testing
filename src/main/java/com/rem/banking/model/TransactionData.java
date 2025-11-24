package com.rem.banking.model;

/**
 * Represents the Data Transfer Object (DTO) for a banking transaction.
 * <p>
 * This class encapsulates the raw input data provided by the user or system
 * before validation. It adheres to the "Separation of Concerns" principle,
 * keeping the data structure distinct from the validation logic.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0 (Sprint 3)
 */
public class TransactionData {

    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String personalKey;
    private String orderValue;

    /**
     * Constructs a new TransactionData object with the specified raw inputs.
     *
     * @param bankCode      The 3-digit code identifying the bank.
     * @param branchCode    The alphanumeric code identifying the branch (Region + digits).
     * @param accountNumber The 10-digit customer account number.
     * @param personalKey   The 6-digit security PIN.
     * @param orderValue    The operation type (CHECK or STMT).
     */
    public TransactionData(String bankCode, String branchCode, String accountNumber, 
                           String personalKey, String orderValue) {
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.personalKey = personalKey;
        this.orderValue = orderValue;
    }

    /**
     * Retrieves the bank code.
     * @return The bank code string.
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Retrieves the branch code.
     * @return The branch code string.
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * Retrieves the account number.
     * @return The account number string.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Retrieves the personal security key (PIN).
     * @return The personal key string.
     */
    public String getPersonalKey() {
        return personalKey;
    }

    /**
     * Retrieves the order value (operation command).
     * @return The order value string.
     */
    public String getOrderValue() {
        return orderValue;
    }
}