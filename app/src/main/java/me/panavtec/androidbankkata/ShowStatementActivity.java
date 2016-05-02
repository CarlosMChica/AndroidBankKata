package me.panavtec.androidbankkata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import me.panavtec.androidbankkata.account.BankAccount;
import me.panavtec.androidbankkata.account.statement.StatementView;

public class ShowStatementActivity extends AppCompatActivity implements StatementView {

  private RecyclerView recyclerView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
    initAccount().showStatement();
  }

  private BankAccount initAccount() {
    BankAccount account = ShowStatementActivityServiceLocator.getInstance().getAccount();
    account.attach(this);
    return account;
  }

  private void initView() {
    setContentView(R.layout.activity_show_statement);

    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override public void show(List<ViewStatementLine> lines) {
    throw new UnsupportedOperationException();
  }
}
