package me.panavtec.androidbankkata.recyclerview;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerItemViewAssertion<A> implements ViewAssertion {

  private int position;
  private A item;
  private RecyclerViewInteraction.ItemViewAssertion<A> itemViewAssertion;
  private String name;

  public RecyclerItemViewAssertion(int position, A item,
      RecyclerViewInteraction.ItemViewAssertion<A> itemViewAssertion) {
    this.position = position;
    this.item = item;
    this.itemViewAssertion = itemViewAssertion;
  }

  public RecyclerItemViewAssertion(int position, A item,
      RecyclerViewInteraction.ItemViewAssertion<A> itemViewAssertion, String name) {
    this.position = position;
    this.item = item;
    this.itemViewAssertion = itemViewAssertion;
    this.name = name;
  }

  @Override public final void check(View view, NoMatchingViewException e) {
    RecyclerView recyclerView = (RecyclerView) view;
    RecyclerView.ViewHolder viewHolderForPosition =
        recyclerView.findViewHolderForLayoutPosition(position);
    if (viewHolderForPosition == null) {
      throw (new PerformException.Builder()).withActionDescription(toString())
          .withViewDescription(HumanReadables.describe(view))
          .withCause(new IllegalStateException("No view holder at position: " + position))
          .build();
    } else {
      View viewAtPosition = viewHolderForPosition.itemView;
      itemViewAssertion.check(item, viewAtPosition, e);
    }
  }

  @Override public String toString() {
    return name != null ? name : super.toString();
  }
}
