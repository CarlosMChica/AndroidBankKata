package me.panavtec.androidbankkata.account.statement;

import java.util.Date;
import me.panavtec.androidbankkata.account.transaction.Transaction;

public class StatementLine {

  private final Transaction transaction;
  private final int runningBalance;

  StatementLine(Transaction transaction, int runningBalance) {
    this.transaction = transaction;
    this.runningBalance = runningBalance;
  }

  public Date date() {
    return transaction.getDate();
  }

  public int amount() {
    return transaction.getAmount();
  }

  public int runningBalance() {
    return runningBalance;
  }

  @Override public String toString() {
    return "StatementLine{" +
        "transaction=" + transaction +
        ", runningBalance=" + runningBalance +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StatementLine that = (StatementLine) o;

    return runningBalance == that.runningBalance && transaction != null ? transaction.equals(
        that.transaction) : that.transaction == null;
  }

  @Override public int hashCode() {
    int result = transaction != null ? transaction.hashCode() : 0;
    result = 31 * result + runningBalance;
    return result;
  }
}
