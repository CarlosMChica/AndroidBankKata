package me.panavtec.androidbankkata.account.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import me.panavtec.androidbankkata.account.transaction.Transaction;

import static java.util.Collections.unmodifiableList;

public class Statement {

  private final List<Transaction> transactions;

  public Statement(List<Transaction> transactions) {
    this.transactions = unmodifiableList(transactions);
  }

  public List<StatementLine> lines() {
    AtomicInteger currentBalance = new AtomicInteger(0);
    ArrayList<StatementLine> lines = new ArrayList<>();
    for (Transaction transaction : transactions) {
      lines.add(line(transaction, currentBalance));
    }

    return lines;
  }

  private StatementLine line(Transaction transaction, AtomicInteger runningBalance) {
    return new StatementLine(transaction, nextRunningBalance(runningBalance, transaction));
  }

  private int nextRunningBalance(AtomicInteger runningBalance, Transaction transaction) {
    return runningBalance.addAndGet(transaction.getAmount());
  }

  @Override public String toString() {
    return "Statement{" +
        "transactions=" + transactions +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Statement statement = (Statement) o;

    return transactions != null
        ? transactions.equals(statement.transactions)
        : statement.transactions == null;
  }

  @Override public int hashCode() {
    return transactions != null ? transactions.hashCode() : 0;
  }
}
