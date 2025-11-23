import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingSimulation {

    // Clase para agrupar los datos de entrada
    static class InputData {
        String bankCode;
        String branchCode;
        String accountNumber;
        String personalKey;
        String orderValue; // lo guardamos como String y luego validamos

        InputData(String bankCode, String branchCode, String accountNumber,
                  String personalKey, String orderValue) {
            this.bankCode = bankCode;
            this.branchCode = branchCode;
            this.accountNumber = accountNumber;
            this.personalKey = personalKey;
            this.orderValue = orderValue;
        }
    }

    // Clase para el resultado de la validación
    static class ValidationResult {
        boolean isValid;
        List<String> errors = new ArrayList<>();
        String operationDescription;

        void addError(String message) {
            errors.add(message);
            isValid = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Banking Application Simulation (Sprint 3) ===\n");

        // 1) Ejecutar al menos 3 casos de prueba representativos
        runPredefinedScenarios();

        // 2) Modo interactivo opcional para que el usuario ingrese datos
        runInteractiveMode();
    }

    // Ejecuta los escenarios predefinidos (vinculados a Sprint 2)
    private static void runPredefinedScenarios() {
        System.out.println(">>> Running predefined test scenarios ...\n");

        // Scenario 1: Valid case (similar to TC01)
        InputData tc01 = new InputData(
                "325",          // bankCode (valid)
                "2350",         // branchCode (valid)
                "0012345678",   // accountNumber (valid)
                "2580",         // personalKey (valid)
                "1"             // orderValue = checkbook
        );
        runScenario("TC01 - Valid transaction (checkbook request)", tc01);

        // Scenario 2: Boundary case (similar to TC02)
        InputData tc02 = new InputData(
                "001",          // lower boundary for bankCode
                "0001",         // lower boundary for branchCode
                "9876543210",   // typical 10-digit account number
                "0000",         // lower boundary for personalKey
                "2"             // orderValue = account statement
        );
        runScenario("TC02 - Boundary values (account statement request)", tc02);

        // Scenario 3: Invalid case (similar to TC03)
        InputData tc03 = new InputData(
                "000",          // invalid bankCode (below minimum)
                "2350",
                "0012345678",
                "2580",
                "1"
        );
        runScenario("TC03 - Invalid bank code (000)", tc03);

        System.out.println(">>> End of predefined scenarios.\n");
    }

    // Ejecuta un escenario y muestra los resultados
    private static void runScenario(String label, InputData input) {
        System.out.println("Scenario: " + label);
        System.out.println("Input:");
        System.out.println("  Bank code      : " + input.bankCode);
        System.out.println("  Branch code    : " + input.branchCode);
        System.out.println("  Account number : " + input.accountNumber);
        System.out.println("  Personal key   : " + input.personalKey);
        System.out.println("  Order value    : " + input.orderValue);

        ValidationResult result = validate(input);

        if (result.isValid) {
            System.out.println("Result: SUCCESS");
            System.out.println("  Operation: " + result.operationDescription);
            System.out.println("  Message  : Transaction registered successfully.\n");
        } else {
            System.out.println("Result: ERROR");
            for (String error : result.errors) {
                System.out.println("  - " + error);
            }
            System.out.println();
        }
    }

    // Valida los datos de entrada basado en las equivalence classes de Sprint 1
    private static ValidationResult validate(InputData input) {
        ValidationResult result = new ValidationResult();
        result.isValid = true; // se vuelve false si hay al menos un error

        // Validación de bank code: 3 dígitos, 001–999 (no 000)
        if (input.bankCode == null || input.bankCode.isEmpty()) {
            result.addError("Bank code is required.");
        } else if (!isNumeric(input.bankCode)) {
            result.addError("Bank code must contain only numeric characters.");
        } else if (input.bankCode.length() != 3) {
            result.addError("Bank code must have exactly 3 digits.");
        } else if ("000".equals(input.bankCode)) {
            result.addError("Bank code cannot be 000.");
        }

        // Validación de branch code: 4 dígitos, 0001–9999 (no 0000)
        if (input.branchCode == null || input.branchCode.isEmpty()) {
            result.addError("Branch code is required.");
        } else if (!isNumeric(input.branchCode)) {
            result.addError("Branch code must contain only numeric characters.");
        } else if (input.branchCode.length() != 4) {
            result.addError("Branch code must have exactly 4 digits.");
        } else if ("0000".equals(input.branchCode)) {
            result.addError("Branch code cannot be 0000.");
        }

        // Validación de account number: 10 dígitos
        if (input.accountNumber == null || input.accountNumber.isEmpty()) {
            result.addError("Account number is required.");
        } else if (!isNumeric(input.accountNumber)) {
            result.addError("Account number must contain only numeric characters.");
        } else if (input.accountNumber.length() != 10) {
            result.addError("Account number must have exactly 10 digits.");
        }

        // Validación de personal key: 4 dígitos
        if (input.personalKey == null || input.personalKey.isEmpty()) {
            result.addError("Personal key is required.");
        } else if (!isNumeric(input.personalKey)) {
            result.addError("Personal key must contain only numeric characters.");
        } else if (input.personalKey.length() != 4) {
            result.addError("Personal key must have exactly 4 digits.");
        }

        // Validación de order value: solo 1 o 2
        if (input.orderValue == null || input.orderValue.isEmpty()) {
            result.addError("Order value is required.");
        } else if (!isNumeric(input.orderValue)) {
            result.addError("Order value must be numeric (1 = checkbook, 2 = account statement).");
        } else {
            int value = Integer.parseInt(input.orderValue);
            if (value == 1) {
                result.operationDescription = "Checkbook request";
            } else if (value == 2) {
                result.operationDescription = "Monthly account statement request";
            } else {
                result.addError("Order value must be 1 (checkbook) or 2 (account statement).");
            }
        }

        return result;
    }

    // Modo interactivo para simular entradas del usuario
    private static void runInteractiveMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(">>> Interactive mode: enter your own data");
        System.out.println("Type 'exit' as bank code to finish.\n");

        while (true) {
            System.out.print("Bank code (3 digits, or 'exit'): ");
            String bankCode = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(bankCode)) {
                System.out.println("Exiting interactive mode.");
                break;
            }

            System.out.print("Branch code (4 digits): ");
            String branchCode = scanner.nextLine().trim();

            System.out.print("Account number (10 digits): ");
            String accountNumber = scanner.nextLine().trim();

            System.out.print("Personal key (4 digits): ");
            String personalKey = scanner.nextLine().trim();

            System.out.print("Order value (1 = checkbook, 2 = account statement): ");
            String orderValue = scanner.nextLine().trim();

            InputData userInput = new InputData(bankCode, branchCode, accountNumber, personalKey, orderValue);
            runScenario("Interactive input", userInput);
        }

        scanner.close();
    }

    // Ayuda para verificar si una cadena es numérica
    private static boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (char c : value.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
