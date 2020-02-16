package ast.expr;

import ast.type.IType;
import ast.type.TypeArrow;
import ast.type.TypeVar;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class App implements IExp {
    private IExp appFun, appArg;

    public App (IExp fun, IExp arg) {
        this.appFun = fun;
        this.appArg = arg;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        TypeVar alpha = tvPool.freshTV();
        Subst subst = appFun.inferType(env, new TypeArrow(alpha, sigma), tvPool);
        return appArg.inferType(env.substitute(subst), alpha.substitute(subst), tvPool).compose(subst);
    }
}
