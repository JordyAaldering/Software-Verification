package ast.expr;

import ast.type.IType;
import ast.type.TypeScheme;
import ast.type.TypeVar;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;
import java.util.Collections;
import java.util.LinkedList;

public class Let implements IExp {
    private Var x;
    private IExp e1, e2;

    public Let(Var x, IExp e1, IExp e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        TypeVar alpha = tvPool.freshTV();
        TypeScheme typeScheme = new TypeScheme(alpha);
        env.add(x, typeScheme);
        Subst subst = e1.inferType(env, alpha, tvPool);
        typeScheme.collectFVs(new LinkedList<>(Collections.singleton(alpha)));
        env.add(x, typeScheme);
        return e2.inferType(env.substitute(subst), sigma.substitute(subst), tvPool).compose(subst);
    }
}
