package absyn;

public class NameTy extends Absyn {
  public static final int BOOL = 0;
  public static final int INT  = 1;
  public static final int VOID = 2;

  public int typ;

  public NameTy(int row, int col, int typ) {
    this.row = row;
    this.col = col;
    this.typ = typ;
  }

  @Override
  public void accept(AbsynVisitor visitor, int level) {
    visitor.visit(this, level);
  }
}