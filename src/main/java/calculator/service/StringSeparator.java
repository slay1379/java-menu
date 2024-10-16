package calculator.service;

import calculator.domain.Calculator;

public class StringSeparator {

    private Calculator calculator;

    public StringSeparator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void divideSeparator(String str) {
        String number = "";
        String divide = "";
        for (int i = 0; i < str.length(); i++) {
            char nowChar = str.charAt(i);
            if (isNumber(nowChar)) {
                if (!divide.isEmpty()) {
                    if (isWrongInput(divide)) {
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    } else {
                        String separator = extractionCustomSeparator(divide);
                        calculator.addSeparator(separator);
                        divide = "";
                    }
                }
                number += nowChar;
            } else {
                if (isSeparator(nowChar)) {
                    if (!number.isEmpty()) {
                        calculator.addNumber(Integer.parseInt(number));
                        number = "";
                    }
                } else {
                    divide += nowChar;
                }
            }
        }
        if (!number.isEmpty()) {
            calculator.addNumber(Integer.parseInt(number));
        }
    }

    private boolean isNumber(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }

    private boolean isWrongInput(String divide) {
        if (divide == null || divide.length() < 5) {
            return true;
        }
        if (!divide.startsWith("//") || !divide.contains("\\n")) {
            return true;
        }
        return false;
    }

    public String extractionCustomSeparator(String divide) {
        return divide.substring(2, divide.length() - 2);
    }

    private boolean isSeparator(char ch) {
        if (calculator.getSeparators().contains(String.valueOf(ch))) {
            return true;
        }
        return false;
    }

}
