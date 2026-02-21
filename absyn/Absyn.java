package absyn;

public abstract class Absyn {
  public int row;
  public int col;

  public abstract void accept(AbsynVisitor visitor, int level);
}