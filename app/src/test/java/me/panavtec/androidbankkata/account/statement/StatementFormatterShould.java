package me.panavtec.androidbankkata.account.statement;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import me.panavtec.androidbankkata.ViewStatementLine;
import me.panavtec.androidbankkata.account.transaction.Deposit;
import me.panavtec.androidbankkata.account.transaction.Withdrawal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static java.util.Calendar.JANUARY;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class) public class StatementFormatterShould {

  @Mock StatementView view;
  @Mock Statement statement;

  @Test
  public void format_statement_containing_all_transactions_in_reverse_chronologically_order() {
    StatementFormatter formatter = givenStatementFormatter();
    Statement statement = givenAnStatement();

    List<ViewStatementLine> lines = formatter.format(statement);

    assertThat(lines, is(linesInReverseOrder()));
  }

  private List<ViewStatementLine> linesInReverseOrder() {
    return asList(new ViewStatementLine("14/01/2012", "-500", "2500"),
        new ViewStatementLine("13/01/2012", "2000", "3000"),
        new ViewStatementLine("10/01/2012", "1000", "1000"));
  }

  private Statement givenAnStatement() {
    given(statement.lines()).willReturn(lines());
    return statement;
  }

  private List<StatementLine> lines() {
    return asList(new StatementLine(new Deposit(1000, date(2012, JANUARY, 10)), 1000),
        new StatementLine(new Deposit(2000, date(2012, JANUARY, 13)), 3000),
        new StatementLine(new Withdrawal(500, date(2012, JANUARY, 14)), 2500));
  }

  private StatementFormatter givenStatementFormatter() {
    return new StatementFormatter();
  }

  private static Date date(int year, int month, int day) {
    return new GregorianCalendar(year, month, day).getTime();
  }
}
