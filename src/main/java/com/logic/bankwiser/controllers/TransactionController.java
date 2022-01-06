package com.logic.bankwiser.controllers;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.transactions.Transaction;
import com.logic.bankwiser.utils.Input;
import com.logic.bankwiser.utils.MathUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kevin Collins
 * @author Mathias Hallander
 */

public class TransactionController {

    private final Storage storage;

    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    public String transferMoney(String senderBankAccountID, String receiverBankAccountID, BigDecimal moneyTransferred, String note, LocalDateTime transactionDate) {
        StringBuilder sb = new StringBuilder();
        BankAccount senderBankAccount = storage.getBankAccount(senderBankAccountID);
        BankAccount receiverBankAccount = storage.getBankAccount(receiverBankAccountID);

        if (senderBankAccount == null) {
            sb.append("Cannot transfer: {sender} was not found.");
        } else if (receiverBankAccount == null) {
            sb.append("Cannot transfer: {receiver} was not found.");
        } else if (moneyTransferred.compareTo(BigDecimal.ZERO) == 0) {
            sb.append("Cannot transfer: You need to enter an amount to send.");
        } else {
            senderBankAccount.processPaymentRequest(false, moneyTransferred);
            BigDecimal senderNewBalance = senderBankAccount.getBalance();
            receiverBankAccount.processPaymentRequest(true, moneyTransferred);
            BigDecimal receiverNewBalance = receiverBankAccount.getBalance();
            senderBankAccount.addTransaction(new Transaction(generateTransactionID(receiverBankAccountID), receiverBankAccountID, moneyTransferred.negate(), note, transactionDate, senderNewBalance));
            receiverBankAccount.addTransaction(new Transaction(generateTransactionID(senderBankAccountID), senderBankAccountID, moneyTransferred, note, transactionDate, receiverNewBalance));

            sb.append("Successfully sent ").append(moneyTransferred).append(" SEK from ").append(senderBankAccountID);
            sb.append(" to ").append(receiverBankAccountID).append(". ");
            sb.append("Note: ").append(note).append(".");
        }
        return sb.toString();
    }

    /**
     * Acquires BankAccount from method to guarantee unique id generation
     *
     * @return guaranteed unique id
     */
    public String generateTransactionID(String bankAccountID) {
        StringBuilder stringBuilder = new StringBuilder();
        BankAccount bankAccount = storage.getBankAccount(bankAccountID);

        bankAccount.getTransactionMap().keySet().forEach((String transactionID) -> stringBuilder.append(transactionID).append(','));

        return MathUtils.generateUniqueID(stringBuilder.toString());
    }

    public String viewTransactionHistory(String bankAccountID) {
        StringBuilder sb = new StringBuilder();
        List<Transaction> transactionList = storage.getBankAccount(bankAccountID).getTransactionMap().values().stream().toList();

        if (transactionList.isEmpty()) {
            sb.append("No previous transaction history.");
        } else {
            for (Transaction transaction : transactionList) {
                sb.append(transaction).append(Input.EOL);
            }
        }

        return sb.toString();
    }

}
