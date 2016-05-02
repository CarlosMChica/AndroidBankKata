package me.panavtec.androidbankkata.account;

import java.util.List;
import me.panavtec.androidbankkata.ViewStatementLine;
import me.panavtec.androidbankkata.account.statement.Statement;
import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.account.transaction.Deposit;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;
import me.panavtec.androidbankkata.account.transaction.Withdrawal;

public class BankAccount {

  private final TransactionRepository repository;
  private final StatementFormatter formatter;
  private final Clock clock;
  private StatementView view;

  public BankAccount(TransactionRepository repository, StatementFormatter formatter, Clock clock) {
    this.repository = repository;
    this.formatter = formatter;
    this.clock = clock;
  }

  public void attach(StatementView view) {
    this.view = view;
  }

  public void deposit(int amount) {
    repository.store(new Deposit(amount, clock.today()));
  }

  public void withdraw(int amount) {
    repository.store(new Withdrawal(amount, clock.today()));
  }

  public void showStatement() {
    view.show(statementLines());
  }

  private List<ViewStatementLine> statementLines() {
    return formatter.format(new Statement(repository.transactions()));
  }
}
