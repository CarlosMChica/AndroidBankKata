package me.panavtec.androidbankkata.account.statement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import me.panavtec.androidbankkata.ViewStatementLine;

import static java.lang.String.valueOf;
import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

public class StatementFormatter {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
  private static final Comparator<StatementLine> STATEMENT_LINE_DATE_COMPARATOR =
      new Comparator<StatementLine>() {
        @Override public int compare(StatementLine lhs, StatementLine rhs) {
          return lhs.date().compareTo(rhs.date());
        }
      };

  public List<ViewStatementLine> format(Statement statement) {
    return statementLinesFrom(statement);
  }

  private List<ViewStatementLine> statementLinesFrom(Statement statement) {
    List<StatementLine> orderedLines = new ArrayList<>(statement.lines());
    sort(orderedLines, reverseOrder(STATEMENT_LINE_DATE_COMPARATOR));
    List<ViewStatementLine> viewStatementLines = new ArrayList<>();
    for (StatementLine statementLine : orderedLines) {
      viewStatementLines.add(new ViewStatementLine(DATE_FORMAT.format(statementLine.date()),
          valueOf(statementLine.amount()), valueOf(statementLine.runningBalance())));
    }
    return viewStatementLines;
  }
}
