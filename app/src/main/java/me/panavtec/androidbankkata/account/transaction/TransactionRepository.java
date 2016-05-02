package me.panavtec.androidbankkata.account.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

  private final ArrayList<Transaction> transactions = new ArrayList<>();

  public void store(Transaction transaction) {
    transactions.add(transaction);
  }

  public List<Transaction> transactions() {
    return Collections.unmodifiableList(transactions);
  }
}
