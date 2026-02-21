package absyn;

public class DecList extends Absyn {
  public Dec head;
  public DecList tail;

  public DecList(int row, int col, Dec head, DecList tail) {
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