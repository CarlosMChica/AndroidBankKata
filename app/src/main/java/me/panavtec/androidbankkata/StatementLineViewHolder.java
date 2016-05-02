package me.panavtec.androidbankkata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

class StatementLineViewHolder extends RecyclerView.ViewHolder {

  private final TextView dateTextView;
  private final TextView amountTextView;
  private final TextView runningBalanceTextView;

  public StatementLineViewHolder(ViewGroup parent) {
    super(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction, parent, false));
    dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
    amountTextView = (TextView) itemView.findViewById(R.id.amountTextView);
    runningBalanceTextView = (TextView) itemView.findViewById(R.id.runningBalanceTextView);
  }

  public void onBind(ViewStatementLine line) {
    dateTextView.setText(line.date);
    amountTextView.setText(line.amount);
    runningBalanceTextView.setText(line.runningBalance);
  }
}
