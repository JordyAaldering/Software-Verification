package ast.expr;

import ast.type.IType;
import ast.type.TypeConst;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class IntExp implements IExp {
    private int IntVal;

    public IntExp(int IntVal) {
        this.IntVal = IntVal;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        return sigma.unify(TypeConst.IntType);
    }
}
