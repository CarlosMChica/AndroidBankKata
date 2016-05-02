package me.panavtec.androidbankkata.account;

import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;

public class BankAccount {

  public BankAccount(TransactionRepository repository, StatementFormatter formatter, Clock clock) {

  }

  public void attach(StatementView view) {
  }

  public void deposit(int amount) {
  }

  public void withdraw(int amount) {
  }

  public void showStatement() {
  }
}
