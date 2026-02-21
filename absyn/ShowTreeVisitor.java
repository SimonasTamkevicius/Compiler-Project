package absyn;

public class ShowTreeVisitor implements AbsynVisitor {

  private void indent(int level) {
    for (int i = 0; i < level; i++) System.out.print("  ");
  }

  private String tyName(int typ) {
    return switch (typ) {
      case NameTy.BOOL -> "bool";
      case NameTy.INT  -> "int";
      case NameTy.VOID -> "void";
      default -> "<?>"; // should never happen
    };
  }

  private String opName(int op) {
    return switch (op) {
      case OpExp.PLUS   -> "+";
      case OpExp.MINUS  -> "-";
      case OpExp.UMINUS -> "UMINUS";
      case OpExp.MUL    -> "*";
      case OpExp.DIV    -> "/";

      case OpExp.EQ -> "==";
      case OpExp.NE -> "!=";
      case OpExp.LT -> "<";
      case OpExp.LE -> "<=";
      case OpExp.GT -> ">";
      case OpExp.GE -> ">=";

      case OpExp.NOT -> "~";
      case OpExp.AND -> "&&";
      case OpExp.OR  -> "||";
      default -> "<?op?>";
    };
  }

  // ---------------- Lists ----------------

  @Override
  public void visit(DecList decList, int level) {
    for (DecList cur = decList; cur != null; cur = cur.tail) {
      if (cur.head != null) cur.head.accept(this, level);
    }
  }

  @Override
  public void visit(VarDecList varDecList, int level) {
    for (VarDecList cur = varDecList; cur != null; cur = cur.tail) {
      if (cur.head != null) cur.head.accept(this, level);
    }
  }

  @Override
  public void visit(ExpList expList, int level) {
    for (ExpList cur = expList; cur != null; cur = cur.tail) {
      if (cur.head != null) cur.head.accept(this, level);
    }
  }

  // ---------------- Types ----------------

  @Override
  public void visit(NameTy nameTy, int level) {
    indent(level);
    System.out.println("NameTy: " + tyName(nameTy.typ));
  }

  // ---------------- Vars ----------------

  @Override
  public void visit(SimpleVar simpleVar, int level) {
    indent(level);
    System.out.println("SimpleVar: " + simpleVar.name);
  }

  @Override
  public void visit(IndexVar indexVar, int level) {
    indent(level);
    System.out.println("IndexVar: " + indexVar.name);
    indent(level + 1);
    System.out.println("index:");
    if (indexVar.index != null) indexVar.index.accept(this, level + 2);
  }

  // ---------------- Exps ----------------

  @Override
  public void visit(NilExp nilExp, int level) {
    indent(level);
    System.out.println("NilExp");
  }

  @Override
  public void visit(IntExp intExp, int level) {
    indent(level);
    System.out.println("IntExp: " + intExp.value);
  }

  @Override
  public void visit(BoolExp boolExp, int level) {
    indent(level);
    System.out.println("BoolExp: " + boolExp.value);
  }

  @Override
  public void visit(VarExp varExp, int level) {
    indent(level);
    System.out.println("VarExp");
    if (varExp.variable != null) varExp.variable.accept(this, level + 1);
  }

  @Override
  public void visit(CallExp callExp, int level) {
    indent(level);
    System.out.println("CallExp: " + callExp.func);
    indent(level + 1);
    System.out.println("args:");
    if (callExp.args != null) callExp.args.accept(this, level + 2);
    else {
      indent(level + 2);
      System.out.println("(none)");
    }
  }

  @Override
  public void visit(OpExp opExp, int level) {
    indent(level);
    System.out.println("OpExp: " + opName(opExp.op));

    indent(level + 1);
    System.out.println("left:");
    if (opExp.left != null) opExp.left.accept(this, level + 2);
    else {
      indent(level + 2);
      System.out.println("(null)");
    }

    indent(level + 1);
    System.out.println("right:");
    if (opExp.right != null) opExp.right.accept(this, level + 2);
  }

  @Override
  public void visit(AssignExp assignExp, int level) {
    indent(level);
    System.out.println("AssignExp");

    indent(level + 1);
    System.out.println("lhs:");
    if (assignExp.lhs != null) assignExp.lhs.accept(this, level + 2);

    indent(level + 1);
    System.out.println("rhs:");
    if (assignExp.rhs != null) assignExp.rhs.accept(this, level + 2);
  }

  @Override
  public void visit(IfExp ifExp, int level) {
    indent(level);
    System.out.println("IfExp");

    indent(level + 1);
    System.out.println("test:");
    if (ifExp.test != null) ifExp.test.accept(this, level + 2);

    indent(level + 1);
    System.out.println("then:");
    if (ifExp.thenpart != null) ifExp.thenpart.accept(this, level + 2);

    indent(level + 1);
    System.out.println("else:");
    if (ifExp.elsepart != null) ifExp.elsepart.accept(this, level + 2);
  }

  @Override
  public void visit(WhileExp whileExp, int level) {
    indent(level);
    System.out.println("WhileExp");

    indent(level + 1);
    System.out.println("test:");
    if (whileExp.test != null) whileExp.test.accept(this, level + 2);

    indent(level + 1);
    System.out.println("body:");
    if (whileExp.body != null) whileExp.body.accept(this, level + 2);
  }

  @Override
  public void visit(ReturnExp returnExp, int level) {
    indent(level);
    System.out.println("ReturnExp");
    indent(level + 1);
    System.out.println("exp:");
    if (returnExp.exp != null) returnExp.exp.accept(this, level + 2);
  }

  @Override
  public void visit(CompoundExp compoundExp, int level) {
    indent(level);
    System.out.println("CompoundExp");

    indent(level + 1);
    System.out.println("decs:");
    if (compoundExp.decs != null) compoundExp.decs.accept(this, level + 2);
    else {
      indent(level + 2);
      System.out.println("(none)");
    }

    indent(level + 1);
    System.out.println("exps:");
    if (compoundExp.exps != null) compoundExp.exps.accept(this, level + 2);
    else {
      indent(level + 2);
      System.out.println("(none)");
    }
  }

  // ---------------- Decls ----------------

  @Override
  public void visit(FunctionDec functionDec, int level) {
    indent(level);
    System.out.println("FunctionDec: " + functionDec.func);

    indent(level + 1);
    System.out.println("result:");
    if (functionDec.result != null) functionDec.result.accept(this, level + 2);

    indent(level + 1);
    System.out.println("params:");
    if (functionDec.params != null) functionDec.params.accept(this, level + 2);
    else {
      indent(level + 2);
      System.out.println("(void)");
    }

    indent(level + 1);
    System.out.println("body:");
    if (functionDec.body != null) functionDec.body.accept(this, level + 2);
  }

  @Override
  public void visit(SimpleDec simpleDec, int level) {
    indent(level);
    System.out.println("SimpleDec: " + simpleDec.name);

    indent(level + 1);
    System.out.println("type:");
    if (simpleDec.typ != null) simpleDec.typ.accept(this, level + 2);
  }

  @Override
  public void visit(ArrayDec arrayDec, int level) {
    indent(level);
    System.out.println("ArrayDec: " + arrayDec.name + "[" + arrayDec.size + "]");

    indent(level + 1);
    System.out.println("type:");
    if (arrayDec.typ != null) arrayDec.typ.accept(this, level + 2);
  }
}