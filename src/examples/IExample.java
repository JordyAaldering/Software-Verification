package examples;

import ast.expr.*;
import ast.type.IType;
import ast.type.TypeArrow;
import ast.type.TypeVar;

public interface IExample {
    IExp prog1 = new Abs("x", new Abs ("y", new App (new Var ("x"),new Var ("y"))));
    IExp prog2 = new Abs("x", new Abs ("y", new Abs ("z", new App (new App (new Var ("x"), new Var ("z")), new App (new Var ("y"), new Var ("z"))))));
    IExp prog3 = new Abs("x", new App (new Abs ("x", new Var ("x")), new Var ("x")));
    IExp prog4 = new Abs("x", new BinExp (BinOp.opLt, new Var ("x"), new Var ("x")));
    IExp prog5 = new Abs("z", new Abs("y", new If(new BinExp(BinOp.opLt, new Var("y"), new Var("z")), new Var("y"), new Var("z"))));
    IExp prog6 = new Abs("x", new Abs("y", (new UnaryExp(UnaryOp.opFst, new BinExp(BinOp.opTuple, new Var("x"), new Var("y"))))));
    IExp prog7 = new Abs("x", new Abs("y", (new UnaryExp(UnaryOp.opSnd, new BinExp(BinOp.opTuple, new Var("x"), new Var("y"))))));
    IExp prog8 = new Abs("x", new Abs("y", new BinExp(BinOp.opTuple, new Var("x"), new Var("y"))));

    IType type1 = new TypeArrow(new TypeVar(0), new TypeArrow(new TypeVar(0), new TypeVar(1)));
    IType type2 = new TypeArrow(new TypeArrow(new TypeVar(1), new TypeVar(1)), new TypeVar(2));
    IType type3 = new TypeArrow(new TypeVar(0), new TypeVar(1));
    IType type4 = new TypeVar(0);
}
