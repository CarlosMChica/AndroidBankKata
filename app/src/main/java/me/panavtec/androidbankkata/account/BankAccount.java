package me.panavtec.androidbankkata.account;

import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.account.transaction.Deposit;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;
import me.panavtec.androidbankkata.account.transaction.Withdrawal;

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
    repository.store(new Deposit(amount, clock.today()));
  }

  public void withdraw(int amount) {
    repository.store(new Withdrawal(amount, clock.today()));
  }

  public void showStatement() {
  }
}
