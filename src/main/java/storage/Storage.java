package storage;

import bank_accounts.BankAccount;
import loans.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.math.BigDecimal;
import java.util.Random;

public class Storage {

    private final List<Integer> caseIDList;
    private final List<Loan> loanList;
    private final LinkedHashMap<Integer, BankAccount> bankAccountMap;

    public Storage() {
        caseIDList = new ArrayList<>();
        loanList = new ArrayList<>();
        bankAccountMap = new LinkedHashMap();
    }

    public LinkedHashMap<Integer, BankAccount> getBankAccountMap() {
        return bankAccountMap;
    }

    public void addCaseID(int caseID) {
        caseIDList.add(caseID);
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void addBankAccount(int bankAccountID, BankAccount bankAccount) {
        bankAccountMap.put(bankAccountID, bankAccount);
    }

}
