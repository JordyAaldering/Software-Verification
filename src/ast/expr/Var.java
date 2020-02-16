package ast.expr;

import ast.type.IType;
import ast.type.TypeScheme;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class Var implements IExp {
    private final String varId;

    public Var(String id) {
        this.varId = id;
    }
    
    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        TypeScheme varIType = env.get(this);
        return sigma.unify(varIType);
    }

    @Override
    public int hashCode() {
        return varId.hashCode();
    }

    @Override
    public boolean equals(Object oVar) {
        return oVar instanceof Var && varId.equals(((Var) oVar).varId);
    }
}
