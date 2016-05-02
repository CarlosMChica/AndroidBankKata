package me.panavtec.androidbankkata.account.transaction;

import java.util.Date;
import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionRepositoryShouldShould {

  @Test public void store_transactions() {
    TransactionRepository repository = new TransactionRepository();
    Deposit deposit = new Deposit(anAmount(), aDate());

    repository.store(deposit);

    assertThat(repository.transactions(), hasItem(deposit));
  }

  private Date aDate() {
    return null;
  }

  private int anAmount() {
    return 0;
  }
}