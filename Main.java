import java.io.*;
import absyn.*;
import java_cup.runtime.Symbol;

class Main {
  static public void main(String argv[]) {
    try {
      parser p = new parser(new Lexer(new FileReader(argv[0])));
      DecList result = (DecList)(p.parse().value);

      System.out.println("Parse finished.");

      if (result != null) {
        System.out.println("Abstract Syntax Tree:");
        AbsynVisitor visitor = new ShowTreeVisitor();
        result.accept(visitor, 0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}