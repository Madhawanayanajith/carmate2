package com.example.carmate;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMax implements InputFilter {

    private int max;

    public InputFilterMax(int max) {
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            // Combine the current text and the new input, then parse it to an integer
            String inputStr = dest.toString().substring(0, dstart) + source.toString() + dest.toString().substring(dend);
            int input = Integer.parseInt(inputStr);
            if (isInRange(input))
                return null;
        } catch (NumberFormatException e) {
            // If it's not a number, don't modify the input
        }
        // If the input is out of range, return an empty string to prevent the change
        return "";
    }

    private boolean isInRange(int c) {
        // Check if the parsed number is less than or equal to max
        return c <= max;
    }
}
