package me.panavtec.androidbankkata.account.transaction;

import java.util.Date;
import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionRepositoryShouldShould {

  @Test public void store_transactions() {
    TransactionRepository repository = givenTransactionRepository();

    repository.store(aTransaction());

    assertThat(transactionsOf(repository), has(aTransaction()));
  }

  private List<Transaction> transactionsOf(TransactionRepository repository) {
    return repository.transactions();
  }

  private Transaction aTransaction() {
    return new Deposit(anAmount(), aDate());
  }

  private Date aDate() {
    return null;
  }

  private int anAmount() {
    return 0;
  }

  private Matcher<Iterable<? super Transaction>> has(Transaction transaction) {
    return hasItem(transaction);
  }

  private TransactionRepository givenTransactionRepository() {
    return new TransactionRepository();
  }
}