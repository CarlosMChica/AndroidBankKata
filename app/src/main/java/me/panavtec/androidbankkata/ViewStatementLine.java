package me.panavtec.androidbankkata;

public class ViewStatementLine {
  public String date;
  public String amount;
  public String runningBalance;

  public ViewStatementLine(String date, String amount, String runningBalance) {
    this.date = date;
    this.amount = amount;
    this.runningBalance = runningBalance;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ViewStatementLine that = (ViewStatementLine) o;

    if (date != null ? !date.equals(that.date) : that.date != null) {
      return false;
    }
    if (amount != null ? !amount.equals(that.amount) : that.amount != null) {
      return false;
    }
    return runningBalance != null
        ? runningBalance.equals(that.runningBalance)
        : that.runningBalance == null;
  }

  @Override public int hashCode() {
    int result = date != null ? date.hashCode() : 0;
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    result = 31 * result + (runningBalance != null ? runningBalance.hashCode() : 0);
    return result;
  }

  @Override public String toString() {
    return "ViewStatementLine{" +
        "date='" + date + '\'' +
        ", amount='" + amount + '\'' +
        ", runningBalance='" + runningBalance + '\'' +
        '}';
  }
}
