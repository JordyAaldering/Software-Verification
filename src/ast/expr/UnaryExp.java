package ast.expr;

import ast.type.IType;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class UnaryExp implements IExp {
    private UnaryOp unaOp;
    private IExp unaArg;
    
    public UnaryExp(UnaryOp unaOp, IExp unaArg) {
        this.unaOp  = unaOp;
        this.unaArg = unaArg;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        IType[] uot = unaOp.getType(tvPool);
        Subst subst = unaArg.inferType( env, uot[0], tvPool);
        return sigma.unify(uot[1].substitute(subst)).compose(subst);
    }
}
