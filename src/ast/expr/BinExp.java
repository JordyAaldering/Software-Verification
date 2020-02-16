package ast.expr;

import ast.type.IType;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class BinExp implements IExp {
    private BinOp binExpOp;
    private IExp binOpLeft, binOpRight;

    public BinExp(BinOp binExpOp, IExp binOpLeft, IExp binOpRight) {
        this.binExpOp   = binExpOp;
        this.binOpLeft  = binOpLeft;
        this.binOpRight = binOpRight;
    }    

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        IType[] bot  = binExpOp.getType(tvPool);
        Subst subst1 = binOpLeft.inferType(env, bot[0], tvPool);
        Subst subst2 = binOpRight.inferType(env.substitute(subst1), bot[1].substitute(subst1), tvPool).compose(subst1);
        return sigma.substitute(subst2).unify(bot[2].substitute(subst2)).compose(subst2);
    }
}
