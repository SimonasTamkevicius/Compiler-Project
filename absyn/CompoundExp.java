package absyn;

public class CompoundExp extends Exp {
  public VarDecList decs; // may be null for empty
  public ExpList exps;    // may be null for empty

  public CompoundExp(int row, int col, VarDecList decs, ExpList exps) {
    this.row = row;
    this.col = col;
    this.decs = decs;
    this.exps = exps;
  }

  @Override
  public void accept(AbsynVisitor visitor, int level) {
    visitor.visit(this, level);
  }
}