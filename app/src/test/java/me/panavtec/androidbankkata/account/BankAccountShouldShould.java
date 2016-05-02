package me.panavtec.androidbankkata.account;

import java.util.Date;
import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.account.transaction.Transaction;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class BankAccountShouldShould {

  @Mock TransactionRepository repository;
  @Mock StatementFormatter formatter;
  @Mock StatementView view;
  @Mock Clock clock;

  @Test public void store_a_transaction() {
    BankAccount account = givenAccountWithMockedClock();

    account.deposit(anAmount());

    verifyStore(aTransactionWith(anAmount()));
  }

  private BankAccount givenAccountWithMockedClock() {
    when(clock.today()).thenReturn(date());
    return givenAccount();
  }

  private Date date() {
    return new Date();
  }

  private BankAccount givenAccount() {
    BankAccount account = new BankAccount(repository, formatter, clock);
    account.attach(view);
    return account;
  }

  private int anAmount() {
    return 100;
  }

  private Transaction aTransactionWith(int anyAmount) {
    return new Transaction(anyAmount, date());
  }

  private void verifyStore(Transaction transaction) {
    verify(repository).store(transaction);
  }
}