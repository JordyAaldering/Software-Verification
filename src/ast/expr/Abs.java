package ast.expr;

import ast.type.IType;
import ast.type.TypeArrow;
import ast.type.TypeScheme;
import ast.type.TypeVar;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

public class Abs implements IExp {
    private Var  absVar;
    private IExp absBody;

    public Abs(String var, IExp body) {
        this.absVar  = new Var(var);
        this.absBody = body;
    }

    @Override
    public Subst inferType(Environ env, IType sigma, TVPool tvPool) throws UnificationException {
        TypeVar alpha = tvPool.freshTV();
        TypeVar beta  = tvPool.freshTV();
        TypeScheme typeScheme = new TypeScheme(alpha);
        TypeScheme savedIType = env.add(absVar, typeScheme);
        Subst subst = absBody.inferType(env, beta, tvPool);
        env.remove(absVar, savedIType);
        IType ta = new TypeArrow(alpha.substitute(subst), beta.substitute(subst));
        return sigma.substitute(subst).unify(ta).compose(subst);
    }
}
