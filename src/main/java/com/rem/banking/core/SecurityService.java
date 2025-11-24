package com.rem.banking.core;

/**
 * Service responsible for advanced security validations on sensitive data.
 * <p>
 * Unlike basic validation (which checks data types), this service applies
 * heuristic analysis to detect weak security patterns, such as sequential
 * or repeated numbers in PINs, preventing potential brute-force attacks.
 * </p>
 */
public class SecurityService {
    
    /**
     * Analyzes a Personal Key (PIN) to determine if it is cryptographically weak.
     *
     * @param pin The 6-digit PIN to evaluate.
     * @return {@code true} if the PIN is weak (sequential or repeated); 
     * {@code false} if the PIN meets security standards.
     */
    public static boolean isWeakPin(String pin) {
        // Fail-safe: null or incorrect length is handled by InputValidator, 
        // but we double-check here to ensure logic integrity.
        if (pin == null || pin.length() != 6) return false; 
        return isSequence(pin) || isRepeated(pin);
    }

    /**
     * Checks if the PIN consists of a single repeated digit (e.g., "111111").
     *
     * @param pin The PIN to check.
     * @return {@code true} if all digits are identical.
     */
    private static boolean isRepeated(String pin) {
        return pin.chars().distinct().count() == 1;
    }

    /**
     * Checks if the PIN follows a predictable numerical sequence.
     * <p>
     * Detects both ascending (e.g., "123456") and descending (e.g., "987654") patterns.
     * </p>
     *
     * @param pin The PIN to check.
     * @return {@code true} if the PIN is a sequence.
     */
    private static boolean isSequence(String pin) {
        String forward = "0123456789";
        String reverse = "9876543210";
        return forward.contains(pin) || reverse.contains(pin);
    }
}