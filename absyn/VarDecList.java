package absyn;

public class VarDecList extends Absyn {
  public VarDec head;
  public VarDecList tail;

  public VarDecList(int row, int col, VarDec head, VarDecList tail) {
    this.row = row;
    this.col = col;
    this.head = head;
    this.tail = tail;
  }

  @Override
  public void accept(AbsynVisitor visitor, int level) {
    visitor.visit(this, level);
  }
}