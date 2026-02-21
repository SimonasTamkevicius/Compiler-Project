package absyn;

public interface AbsynVisitor {

  void visit(DecList decList, int level);
  void visit(VarDecList varDecList, int level);
  void visit(ExpList expList, int level);

  void visit(NameTy nameTy, int level);

  void visit(SimpleVar simpleVar, int level);
  void visit(IndexVar indexVar, int level);

  void visit(NilExp nilExp, int level);
  void visit(IntExp intExp, int level);
  void visit(BoolExp boolExp, int level);
  void visit(VarExp varExp, int level);
  void visit(CallExp callExp, int level);
  void visit(OpExp opExp, int level);
  void visit(AssignExp assignExp, int level);
  void visit(IfExp ifExp, int level);
  void visit(WhileExp whileExp, int level);
  void visit(ReturnExp returnExp, int level);
  void visit(CompoundExp compoundExp, int level);

  void visit(FunctionDec functionDec, int level);
  void visit(SimpleDec simpleDec, int level);
  void visit(ArrayDec arrayDec, int level);
}