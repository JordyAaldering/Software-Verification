package ast.expr;

import ast.type.IType;
import ast.type.TypeConst;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class If implements IExp {
    private IExp ifCond, ifThen, ifElse;
    
    public If(IExp ce, IExp te, IExp ee) {
        this.ifCond = ce;
        this.ifThen = te;
        this.ifElse = ee;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        Subst subst1 = ifThen.inferType(env, sigma, tvPool);
        Subst subst  = ifElse.inferType(env.substitute(subst1), sigma.substitute(subst1), tvPool).compose(subst1);
        return ifCond.inferType(env.substitute(subst), TypeConst.BoolType, tvPool).compose(subst);
    }
}
