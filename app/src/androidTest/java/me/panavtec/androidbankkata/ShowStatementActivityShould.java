package me.panavtec.androidbankkata;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.junit.Rule;
import org.junit.Test;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static me.panavtec.androidbankkata.R.id.recyclerView;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class ShowStatementActivityShould {

  @Rule public ActivityTestRule<ShowStatementActivity> rule =
      new ActivityTestRule<>(ShowStatementActivity.class, true, false);

  @Test public void have_a_list_with_vertical_orientation() {
    whenLaunchActivity();

    assertThat(recyclerView(), instanceOf(RecyclerView.class));
    assertThat(layoutManager(), instanceOf(LinearLayoutManager.class));
    assertThat(layoutManager().getOrientation(), is(VERTICAL));
  }

  private LinearLayoutManager layoutManager() {
    return (LinearLayoutManager) recyclerView().getLayoutManager();
  }

  private RecyclerView recyclerView() {
    return (RecyclerView) rule.getActivity().findViewById(recyclerView);
  }

  private void whenLaunchActivity() {
    rule.launchActivity(new Intent());
  }
}
