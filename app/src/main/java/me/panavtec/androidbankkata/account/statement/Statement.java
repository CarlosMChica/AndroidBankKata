package me.panavtec.androidbankkata.account.statement;

import java.util.List;
import me.panavtec.androidbankkata.account.transaction.Transaction;

public class Statement {
  private List<Transaction> transactions;

  public Statement(List<Transaction> transactions) {
    this.transactions = transactions;
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
