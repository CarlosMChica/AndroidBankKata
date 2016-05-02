package me.panavtec.androidbankkata.account.transaction;

import java.util.Date;

public abstract class Transaction {

  private final int amount;
  private final Date date;

  public Transaction(int amount, Date date) {
    this.amount = amount;
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public Date getDate() {
    return date;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Transaction that = (Transaction) o;

    return amount == that.amount && date != null ? date.equals(that.date) : that.date == null;
  }

  @Override public int hashCode() {
    int result = amount;
    result = 31 * result + (date != null ? date.hashCode() : 0);
    return result;
  }

  @Override public String toString() {
    return "Transaction{" +
        "amount=" + amount +
        ", date=" + date +
        '}';
  }
}
