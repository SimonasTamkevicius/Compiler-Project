package absyn;

public class FunctionDec extends Dec {
  public NameTy result;
  public String func;
  public VarDecList params; // may be null (e.g., void)
  public Exp body;          // compound stmt (or NilExp for prototype if you want)

  public FunctionDec(int row, int col, NameTy result, String func, VarDecList params, Exp body) {
    this.row = row;
    this.col = col;
    this.result = result;
    this.func = func;
    this.params = params;
    this.body = body;
  }

  @Override
  public void accept(AbsynVisitor visitor, int level) {
    visitor.visit(this, level);
  }
}