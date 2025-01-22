import java.util.Stack;

public class MathEvaluator {

    // Metodo per valutare l'espressione e restituire un double
    public double eval(String expression) {
        try {
            return evaluateExpression(expression);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN; // Restituisce NaN in caso di errore
        }
    }

    // Metodo per valutare l'espressione
    private double evaluateExpression(String expression) {
        char[] tokens = expression.toCharArray();

        // Stack per i numeri
        Stack<Double> valori = new Stack<>();

        // Stack per gli operatori
        Stack<Character> operatori = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // Salta gli spazi bianchi
            if (tokens[i] == ' ') {
                continue;
            }

            // Se il token è un numero, spingilo nello stack dei valori
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                // Gestisce i numeri con più cifre
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                valori.push(Double.parseDouble(sbuf.toString()));
                i--;
            } else if (tokens[i] == '(') {
                // Se il token è '(', spingilo nello stack degli operatori
                operatori.push(tokens[i]);
            } else if (tokens[i] == ')') {
                // Se il token è ')', risolvi l'espressione all'interno delle parentesi
                while (operatori.peek() != '(') {
                    valori.push(applyOp(operatori.pop(), valori.pop(), valori.pop()));
                }
                operatori.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // Se il token è un operatore, risolvi l'espressione precedente
                while (!operatori.empty() && hasPrecedence(tokens[i], operatori.peek())) {
                    valori.push(applyOp(operatori.pop(), valori.pop(), valori.pop()));
                }
                // Spingi l'operatore corrente nello stack degli operatori
                operatori.push(tokens[i]);
            }
        }

        // Completare la risoluzione dell'espressione
        while (!operatori.empty()) {
            valori.push(applyOp(operatori.pop(), valori.pop(), valori.pop()));
        }

        // Il risultato finale è nello stack dei valori
        return valori.pop();
    }

    // Metodo per applicare un operatore sui due operandi
    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Divisione per zero non permessa");
                }
                return a / b;
        }
        return 0;
    }

    // Metodo per controllare la precedenza degli operatori
    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }
}
