package ast.expr;

import ast.type.IType;
import ast.type.TypeConst;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class BoolExp implements IExp {
    private boolean BoolVal;

    public BoolExp(boolean BoolVal) {
        this.BoolVal = BoolVal;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        return sigma.unify(TypeConst.BoolType);
    }
}
