package com.example.carmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;


public class CalculatorFragment extends Fragment {

    EditText editTextLoanAmount, editTextInterestRate, editTextLoanTerm;
    Button buttonCalculate;
    TextView textViewMonthlyPayment,textViewTotalInterest, textViewTotalCost;

    public CalculatorFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextLoanAmount = view.findViewById(R.id.editTextLoanAmount);
        editTextInterestRate = view.findViewById(R.id.editTextInterestRate);
        editTextLoanTerm = view.findViewById(R.id.editTextLoanTerm);
        buttonCalculate = view.findViewById(R.id.buttonCalculate);
        textViewMonthlyPayment = view.findViewById(R.id.textViewMonthlyPayment);
        textViewTotalInterest = view.findViewById(R.id.textViewTotalInterest);
        textViewTotalCost = view.findViewById(R.id.textViewTotalCost);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextLoanAmount.getText().toString().isEmpty() ||
                        editTextInterestRate.getText().toString().isEmpty() ||
                        editTextLoanTerm.getText().toString().isEmpty()) {
                    // If any of the input fields are empty, show a toast message
                    Toast.makeText(getContext(), "Please enter all values", Toast.LENGTH_SHORT).show();
                } else {
                    calculateLoanDetails();
                }
            }
        });
    }
    private void calculateLoanDetails() {
        double loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
        double interestRate = Double.parseDouble(editTextInterestRate.getText().toString());
        double loanTerm = Double.parseDouble(editTextLoanTerm.getText().toString());

        // Convert interest rate from percentage to decimal
        interestRate /= 100.0;

        // Convert loan term from years to months
        loanTerm *= 12;

        // Calculate monthly interest rate
        double monthlyInterestRate = interestRate / 12.0;

        // Calculate monthly payment
        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));

        // Calculate total interest paid
        double totalInterest = monthlyPayment * loanTerm - loanAmount;

        // Calculate total cost of the loan
        double totalCost = loanAmount + totalInterest;

        // Format the result
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedMonthlyPayment = decimalFormat.format(monthlyPayment);
        String formattedTotalInterest = decimalFormat.format(totalInterest);
        String formattedTotalCost = decimalFormat.format(totalCost);

        textViewMonthlyPayment.setText("Monthly Payment: Rs " + formattedMonthlyPayment);
        textViewTotalInterest.setText("Total Interest Paid: Rs " + formattedTotalInterest);
        textViewTotalCost.setText("Total Cost of the Loan: Rs " + formattedTotalCost);
    }
}
