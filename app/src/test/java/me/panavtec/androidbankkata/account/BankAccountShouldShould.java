package me.panavtec.androidbankkata.account;

import java.util.Date;
import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.account.transaction.Deposit;
import me.panavtec.androidbankkata.account.transaction.Transaction;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;
import me.panavtec.androidbankkata.account.transaction.Withdrawal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class BankAccountShouldShould {

  private static final Date ANY_DATE = new Date();

  @Mock TransactionRepository repository;
  @Mock StatementFormatter formatter;
  @Mock StatementView view;
  @Mock Clock clock;

  @Test public void store_a_transaction() {
    BankAccount account = givenAccountWithMockedClock();

    account.deposit(anAmount());

    verifyStore(aDepositWith(anAmount()));
  }

  @Test public void store_a_withdraw_transaction() {
    BankAccount account = givenAccountWithMockedClock();

    account.withdraw(anAmount());

    verifyStore(aWithdrawalWith(anAmount()));
  }

  private Deposit aDepositWith(int amount) {
    return new Deposit(amount, date());
  }

  private BankAccount givenAccountWithMockedClock() {
    when(clock.today()).thenReturn(date());
    return givenAccount();
  }

  private Date date() {
    return ANY_DATE;
  }

  private BankAccount givenAccount() {
    BankAccount account = new BankAccount(repository, formatter, clock);
    account.attach(view);
    return account;
  }

  private int anAmount() {
    return 100;
  }

  private Withdrawal aWithdrawalWith(int amount) {
    return new Withdrawal(amount, date());
  }

  private void verifyStore(Transaction transaction) {
    verify(repository).store(transaction);
  }
}