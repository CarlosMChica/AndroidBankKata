package me.panavtec.androidbankkata;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import me.panavtec.androidbankkata.account.BankAccount;
import me.panavtec.androidbankkata.account.statement.StatementView;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
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
