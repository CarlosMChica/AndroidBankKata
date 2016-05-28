package me.panavtec.androidbankkata;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

class StatementLineAdapter extends RecyclerView.Adapter<StatementLineViewHolder> {

  private final List<ViewStatementLine> lines;

  public StatementLineAdapter(List<ViewStatementLine> lines) {
    this.lines = lines;
  }

  @Override public StatementLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new StatementLineViewHolder(parent);
  }

  @Override public void onBindViewHolder(StatementLineViewHolder holder, int position) {
    holder.onBind(lines.get(position));
  }

  @Override public int getIt

  emCount() {
    return lines.size();
  }
}
