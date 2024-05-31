package fr.univrouen.xmlProject.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Component
public class Constants {

    private final Dotenv dotenv;

    @Autowired
    public Constants(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String getTokenPrefix() {
        return dotenv.get("TOKEN_PREFIX");
    }

    public String getSigningKey() {
        return dotenv.get("SIGNING_KEY");
    }


    public String getHeaderString() {
        return dotenv.get("HEADER_STRING");
    }

    public String getContactUsMail() {
        return dotenv.get("MAIL_CONTACT_US");
    }

    /**
     * Retrieves the validity duration of the access token from the environment variables.
     * The value is retrieved as a string from the environment using dotenv, as it might be
     * an expression such as "9 * 60 * 60" representing 9 hours in seconds. This method
     * evaluates the expression to obtain the actual numeric value of the duration.
     * If an error occurs during the evaluation, -1 is returned.
     *
     * @return The validity duration of the access token in seconds, or -1 if an error occurs.
     */
    public long getAccessTokenValiditySeconds() {
        String expression = dotenv.get("ACCESS_TOKEN_VALIDITY_SECONDS");
        return evaluateExpression(expression);
    }

    /**
     * Evaluates a mathematical expression represented as a JavaScript string and returns the result as a long integer.
     *
     * @param expression The mathematical expression to evaluate.
     * @return The result of the evaluation as a long integer.
     * @throws IllegalArgumentException if the expression is invalid or the result is not a number.
     */
    private static long evaluateExpression(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Object result = engine.eval(expression);
            if (result instanceof Number) {
                return ((Number) result).longValue();
            } else {
                throw new IllegalArgumentException("Invalid expression result: " + result);
            }
        } catch (ScriptException e) {
            // If the expression is invalid, handle the exception
            throw new IllegalArgumentException("Invalid expression: " + expression, e);
        }
    }
}