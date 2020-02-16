package ast.expr;

import ast.type.IType;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public interface IExp {
    Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException;
}
