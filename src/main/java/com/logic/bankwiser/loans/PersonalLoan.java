package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;


public class PersonalLoan extends Loan {

    private final String PERSONAL_REASONS;
    private final double PERSONAL_LOAN_INTEREST_RATE = 0.07;

    public PersonalLoan(BankAccount bankAccount, String loanID, double loanAmount, int loanDuration, String personalReasons) {
        super(bankAccount, loanID, loanAmount, loanDuration);
        this.PERSONAL_REASONS = personalReasons;
        super.setInterestRate(PERSONAL_LOAN_INTEREST_RATE);
    }

    public double getPersonalLoanInterestRate() {
        return PERSONAL_LOAN_INTEREST_RATE;
    }

    public String getPersonalReasons() {
        return PERSONAL_REASONS;
    }
}
