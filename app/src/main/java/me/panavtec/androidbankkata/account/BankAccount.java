package me.panavtec.androidbankkata.account;

import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.account.transaction.Transaction;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;

public class BankAccount {

  private TransactionRepository repository;
  private Clock clock;

  public BankAccount(TransactionRepository repository, StatementFormatter formatter, Clock clock) {
    this.repository = repository;
    this.clock = clock;
  }

  public void attach(StatementView view) {
  }

  public void deposit(int amount) {
    repository.store(new Transaction(amount, clock.today()));
  }

  public void withdraw(int amount) {
    repository.store(new Transaction(-amount, clock.today()));
  }

  public void showStatement() {
  }
}
