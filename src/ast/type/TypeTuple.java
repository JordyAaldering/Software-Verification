package ast.type;

import typing.Subst;
import typing.UnificationException;
import java.util.List;

public class TypeTuple implements IType {
    private IType ttFst, ttSnd;

    public TypeTuple(IType arg, IType res) {
        this.ttFst = arg;
        this.ttSnd = res;
    }
    
    public IType substitute(Subst subst) {
        return new TypeTuple(ttFst.substitute(subst), ttSnd.substitute(subst));
    } 

    @Override
    public boolean containsTV(TypeVar var) {
        return ttFst.containsTV(var) || ttSnd.containsTV(var);
    }

    @Override
    public void collectTVs(List<TypeVar> typeVars) {
        ttFst.collectTVs(typeVars);
        ttSnd.collectTVs(typeVars);
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
    public Subst unify(TypeTuple ta) throws UnificationException {
        Subst subst = ttFst.unify(ta.ttFst);
        return ttSnd.substitute(subst).unify(ta.ttSnd.substitute(subst)).compose(subst);
    }

    @Override
    public Subst unify(TypeArrow ta) throws UnificationException {
        throw new UnificationException(this, ta);
    }

    @Override
    public Subst unify(TypeConst tc) throws UnificationException {
        throw new UnificationException(this, tc);
    }

    @Override
    public Subst unify(TypeVar tv) throws UnificationException {
        if (!containsTV(tv)) {
            return new Subst(tv, this);
        } else {
            throw new UnificationException(this, tv);
        }
    }
    
    public String toString() {
        return String.format("(%s, %s)", ttFst, ttSnd);
    }
}
