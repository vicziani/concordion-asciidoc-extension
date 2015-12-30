package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttributeParser {

    public static final char SPACE = ' ';
    public static final char COMMA = ',';

    private enum State {OUT, IN_QUOTES_STARTED, STARTED}

    private static final char QUOTE = '\"';

    private String text;

    private State state = State.OUT;

    private String part = "";

    private int i;

    private List<String> values;

    public AttributeParser(Map<String, Object> attributes) {
        text = (String) attributes.get("text");
        state = State.OUT;
        part = "";
        values = new ArrayList<>();
        for (i = 0; i < text.length(); i++) {
            if (isQuote(text.charAt(i))) {
                handleQuote();
            }
            else if (isSpace(text.charAt(i))) {
                handleSpace();
            }
            else if (isComma(text.charAt(i))) {
                handleComma();
            }
            else {
                handleChar();
            }
        }
        handleEnd();
    }

    private void handleEnd() {
        switch (state) {
            case OUT:
            case STARTED:
                values.add(part.trim());
                break;
            case IN_QUOTES_STARTED:
                throw new IllegalArgumentException(String.format("No closing quote in %s", text));
            default:
                throw new IllegalStateException("Invalid state: " + state);
        }
    }

    private void handleChar() {
        switch (state) {
            case OUT:
                part += text.charAt(i);
                state = State.STARTED;
                break;
            case STARTED:
            case IN_QUOTES_STARTED:
                part += text.charAt(i);
                break;
            default:
                throw new IllegalStateException("Invalid state: " + state);
        }
    }

    private void handleComma() {
        switch (state) {
            case OUT:
                break;
            case STARTED:
                values.add(part.trim());
                part = "";
                state = State.OUT;
                break;
            case IN_QUOTES_STARTED:
                part += text.charAt(i);
                break;
            default:
                throw new IllegalStateException("Invalid state: " + state);
        }
    }

    private void handleSpace() {
        switch (state) {
            case OUT:
                break;
            case IN_QUOTES_STARTED:
            case STARTED:
                part += text.charAt(i);
                break;
            default:
                throw new IllegalStateException("Invalid state: " + state);
        }
    }

    private void handleQuote() {
        switch (state) {
            case OUT:
                state = State.IN_QUOTES_STARTED;
                break;
            case IN_QUOTES_STARTED:
                values.add(part);
                part = "";
                state = State.OUT;
                break;
            case STARTED:
                throw new IllegalArgumentException(String.format("Illegal quote is %s at index %d", text, i));
            default:
                throw new IllegalStateException("Invalid state: " + state);
        }
    }

    private boolean isQuote(char c) {
        return c == QUOTE;
    }

    private boolean isSpace(char c) {
        return c == SPACE;
    }

    private boolean isComma(char c) {
        return c == COMMA;
    }

    public String getValueAt(int i) {
        return values.get(i);
    }

    public int getSize() {
        return values.size();
    }
}
