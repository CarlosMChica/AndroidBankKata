package me.panavtec.androidbankkata;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import me.panavtec.androidbankkata.account.BankAccount;
import me.panavtec.androidbankkata.account.Clock;
import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;
import me.panavtec.androidbankkata.recyclerview.RecyclerViewInteraction;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.Arrays.asList;
import static java.util.GregorianCalendar.JANUARY;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Given a client makes:
 * A deposit of 1000 on 10-01-2012
 * And a deposit of 2000 on 13-01-2012
 * And a withdrawal of 500 on 14-01-2012
 * <p/>
 * When she shows her bank statement
 * <p/>
 * Then she would see:
 * 14/01/2012 | -500   | 2500.00
 * 13/01/2012 | 2000   | 3000.00
 * 10/01/2012 | 1000   | 1000.00
 */
public class ShowStatementFeature {

  @Rule public ActivityTestRule<ShowStatementActivity> rule =
      new ActivityTestRule<>(ShowStatementActivity.class, true, false);

  @Test public void should_show_statement_with_transactions_in_reverse_order() {
    BankAccount account = givenAccountWithTransactions();

    whenLaunchActivityWith(account);

    assertThatStatementContains(linesInReverseOrder());
  }

  private BankAccount givenAccountWithTransactions() {
    BankAccount account =
        new BankAccount(new TransactionRepository(), new StatementFormatter(), givenClock());

    account.deposit(1000);
    account.deposit(2000);
    account.withdraw(500);
    return account;
  }

  private Clock givenClock() {
    Clock clock = mock(Clock.class);
    given(clock.today()).willReturn(date(2012, JANUARY, 10), date(2012, JANUARY, 13),
        date(2012, JANUARY, 14));
    return clock;
  }

  private void whenLaunchActivityWith(BankAccount account) {
    setTestDependencies(account);
    rule.launchActivity(new Intent());
  }

  private void setTestDependencies(BankAccount account) {
    ShowStatementActivityServiceLocator.setInstance(
        new TestShowStatementActivityServiceLocator(account));
  }

  private void assertThatStatementContains(List<ViewStatementLine> lines) {
    RecyclerViewInteraction.<ViewStatementLine>onRecyclerView(withId(R.id.recyclerView)).withItems(
        lines).check(new RecyclerViewInteraction.ItemViewAssertion<ViewStatementLine>() {
      @Override public void check(ViewStatementLine line, View view, NoMatchingViewException e) {
        matches(withText(line.date)).check(view.findViewById(R.id.dateTextView), e);
        matches(withText(line.amount)).check(view.findViewById(R.id.amountTextView), e);
        matches(withText(line.runningBalance)).check(view.findViewById(R.id.runningBalanceTextView),
            e);
      }
    });
  }

  private static Date date(int year, int month, int day) {
    return new GregorianCalendar(year, month, day).getTime();
  }

  private List<ViewStatementLine> linesInReverseOrder() {
    return asList(new ViewStatementLine("14/01/2012", "-500", "2500"),
        new ViewStatementLine("13/01/2012", "2000", "3000"),
        new ViewStatementLine("10/01/2012", "1000", "1000"));
  }
}
