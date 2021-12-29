package com.logic.bankwiser.bank_accounts;

import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.loans.Loan;
import com.logic.bankwiser.transactions.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Collins
 */

public class BankAccount {

    private final String BANK_ACCOUNT_ID;
    private String bankAccountName;
    private BigDecimal balance;
    private List<Transaction> transactionList;
    private List<Loan> loanList;
    private List<DebitCard> cardList;

    /**
     * Constructor for the BankAccount class.
     * @param bankAccountID     The bank account's ID is generated by the system
     * @param bankAccountName   The bank account's name is specified by the user
     * @throws Exception        If invalid input is provided
     */
    public BankAccount(String bankAccountID, String bankAccountName) throws Exception {
        if (bankAccountName.length() <= 30) {
            this.BANK_ACCOUNT_ID = bankAccountID;
            this.bankAccountName = bankAccountName;
            this.balance = new BigDecimal("0.00");
            this.transactionList = new ArrayList<>();
            this.loanList = new ArrayList<>();
            this.cardList = new ArrayList<>();
        } else {
            throw new Exception("Cannot create a new account: The account name" +
                    bankAccountName + " is only " + bankAccountName.length() + " long.");
        }
    }

    public String getBankAccountID() {
        return BANK_ACCOUNT_ID;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public List<DebitCard> getCardList() {
        return cardList;
    }

    public int getNumOfTransactions(){
        return transactionList.size();
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    /**
     * This method is used by the transaction controller to increase/decrease the bank account's balance.
     * @param increaseBalance   If true, increases balance; if false, decreases balance
     * @param amountOfMoney     The amount of money to be added or subtracted from balance.
     */
    public void processPaymentRequest(boolean increaseBalance, BigDecimal amountOfMoney) {
        if (increaseBalance) {
            balance = balance.add(amountOfMoney);
        } else {
            balance = balance.subtract(amountOfMoney);
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void addCard(DebitCard card) {
        cardList.add(card);
    }

}
