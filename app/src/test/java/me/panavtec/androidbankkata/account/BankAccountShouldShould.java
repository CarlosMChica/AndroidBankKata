package me.panavtec.androidbankkata.account;

import java.util.Date;
import java.util.List;
import me.panavtec.androidbankkata.ViewStatementLine;
import me.panavtec.androidbankkata.account.statement.Statement;
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

import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class BankAccountShouldShould {

  private static final Date ANY_DATE = new Date();

  @Mock TransactionRepository repository;
  @Mock StatementFormatter formatter;
  @Mock StatementView view;
  @Mock Clock clock;

  @Test public void store_a_deposit_transaction() {
    BankAccount account = givenAccountWithMockedClock();

    account.deposit(anAmount());

    verifyStore(aDepositWith(anAmount()));
  }

  @Test public void store_a_withdraw_transaction() {
    BankAccount account = givenAccountWithMockedClock();

    account.withdraw(anAmount());

    verifyStore(aWithdrawalWith(anAmount()));
  }

  @Test public void show_statement_containing_transactions() {
    BankAccount account = givenAccountWithTransactions();

    account.showStatement();

    verify(formatter).format(aStatement());
    verify(view).show(statementLines());
  }

  private BankAccount givenAccountWithTransactions() {
    given(repository.transactions()).willReturn(transactions());
    return givenAccount();
  }

  private List<ViewStatementLine> statementLines() {
    return emptyList();
  }

  private List<Transaction> transactions() {
    return emptyList();
  }

  private Statement aStatement() {
    return new Statement(transactions());
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