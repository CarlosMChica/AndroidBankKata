package me.panavtec.androidbankkata.account.statement;

import java.util.List;
import me.panavtec.androidbankkata.ViewStatementLine;

public interface StatementView {
  void show(List<ViewStatementLine> lines);
}
