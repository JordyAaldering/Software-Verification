package ast.type;

import typing.Subst;
import typing.UnificationException;
import java.util.List;

public enum TypeConst implements IType {
    IntType, BoolType;

    @Override
    public IType substitute(Subst subst) {
        return this;
    }
    
    @Override
    public Subst unify(IType t) throws UnificationException {
        return t.unify(this);
    }

    @Override
    public Subst unify(TypeScheme ts) throws UnificationException {
        return null;
    }

    @Override
    public Subst unify(TypeArrow ta) throws UnificationException {
        throw new UnificationException(this, ta);
    }

    @Override
    public Subst unify(TypeTuple tp) throws UnificationException {
        throw new UnificationException(this, tp);
    }
    
    @Override
    public Subst unify(TypeConst tc) throws UnificationException {
        if (this == tc) {
            return new Subst();
        }
        else {
            throw new UnificationException(this, tc);
        }
    }
    
    @Override
    public Subst unify(TypeVar tv) {
        return new Subst(tv, this);
    }

    @Override
    public boolean containsTV(TypeVar var) {
        return false;
    }

    @Override
    public void collectTVs(List<TypeVar> typeVars) {}
}
