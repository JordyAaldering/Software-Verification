package ast.type;

import typing.Subst;
import typing.UnificationException;
import java.util.List;

public class TypeVar implements IType {
    private final int tvId;

    public TypeVar(int index) {
        this.tvId = index;
    }

    @Override
    public IType substitute(Subst subst) {
        return subst.get(this);
    } 

    @Override
    public boolean containsTV(TypeVar tv) {
        return tvId == tv.tvId;
    }

    @Override
    public void collectTVs(List<TypeVar> typeVars) {
        if (!typeVars.contains(this)) {
            typeVars.add(this);
        }
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
        if (ta.containsTV(this)) {
            throw new UnificationException(this, ta);
        } else {
            return new Subst(this, ta);
        }
    }

    @Override
    public Subst unify(TypeTuple tp) throws UnificationException {
        if (!tp.containsTV(this)) {
            return new Subst(this, tp);
        } else {
            throw new UnificationException(this, tp);
        }
    }

    @Override
    public Subst unify(TypeConst tc) throws UnificationException {
        return new Subst(this, tc);
    }

    @Override
    public Subst unify(TypeVar tv) throws UnificationException {
        return (tvId == tv.tvId) ? new Subst() : new Subst(this, tv);
    }
    
    @Override
    public String toString() {
        return Integer.toString(tvId);
    }

    @Override
    public boolean equals(Object otv) {
        return otv instanceof TypeVar && tvId == ((TypeVar) otv).tvId;
    }

    @Override
    public int hashCode() {
        return tvId; 
    }
}
