package me.panavtec.androidbankkata;

import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;
import me.panavtec.androidbankkata.account.BankAccount;
import me.panavtec.androidbankkata.account.statement.StatementView;
import me.panavtec.androidbankkata.recyclerview.RecyclerViewInteraction;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static java.util.Arrays.asList;
import static me.panavtec.androidbankkata.R.id.recyclerView;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ShowStatementActivityShould {

  @Rule public ActivityTestRule<ShowStatementActivity> rule =
      new ActivityTestRule<>(ShowStatementActivity.class, true, false);

  @Test public void have_a_list_with_vertical_orientation() {
    whenLaunchActivity();

    assertThat(recyclerView(), instanceOf(RecyclerView.class));
    assertThat(layoutManager(), instanceOf(LinearLayoutManager.class));
    assertThat(layoutManager().getOrientation(), is(VERTICAL));
  }

  @Test public void show_statement() {
    BankAccount account = givenAnAccount();

    whenLaunchActivityWith(account);

    verify(account).attach(statementView());
    verify(account).showStatement();
  }

  @Test public void show_view_statement_lines() {
    whenLaunchActivity();
    final List<ViewStatementLine> lines = lines();

    final ShowStatementActivity activity = rule.getActivity();
    activity.runOnUiThread(new Runnable() {
      @Override public void run() {
        activity.show(lines);
      }
    });

    assertThatStatementContains(lines);
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

  private List<ViewStatementLine> lines() {
    return asList(new ViewStatementLine("14/01/2012", "-500", "2500"),
        new ViewStatementLine("13/01/2012", "2000", "3000"),
        new ViewStatementLine("10/01/2012", "1000", "1000"));
  }

  private StatementView statementView() {
    return activity();
  }

  private ShowStatementActivity activity() {
    return rule.getActivity();
  }

  private BankAccount givenAnAccount() {
    return Mockito.mock(BankAccount.class);
  }

  private LinearLayoutManager layoutManager() {
    return (LinearLayoutManager) recyclerView().getLayoutManager();
  }

  private RecyclerView recyclerView() {
    return (RecyclerView) activity().findViewById(recyclerView);
  }

  private void whenLaunchActivity() {
    rule.launchActivity(new Intent());
  }

  private void whenLaunchActivityWith(BankAccount account) {
    setTestDependencies(account);
    whenLaunchActivity();
  }

  private void setTestDependencies(BankAccount account) {
    ShowStatementActivityServiceLocator.setInstance(
        new TestShowStatementActivityServiceLocator(account));
  }
}
