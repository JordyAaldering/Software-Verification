package ast.type;

import typing.Subst;
import typing.UnificationException;
import java.util.List;

public class TypeArrow implements IType {
    private IType taArg, taRes;

    public TypeArrow(IType arg, IType res) {
        this.taArg = arg;
        this.taRes = res;
    }
    
    public IType substitute(Subst subst) {
        return new TypeArrow(taArg.substitute(subst), taRes.substitute(subst));
    } 

    @Override
    public boolean containsTV(TypeVar var) {
        return taArg.containsTV(var) || taRes.containsTV(var);
    }

    @Override
    public void collectTVs(List<TypeVar> typeVars) {
        taArg.collectTVs(typeVars);
        taRes.collectTVs(typeVars);
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
        Subst subst = taArg.unify(ta.taArg);
        return taRes.substitute(subst).unify(ta.taRes.substitute(subst)).compose(subst);
    }

    @Override
    public Subst unify(TypeConst tc) throws UnificationException {
        throw new UnificationException(this, tc);
    }

    @Override
    public Subst unify(TypeVar tv) throws UnificationException {
        if (containsTV(tv)) {
            throw new UnificationException( this, tv ); 
        } else {
            return new Subst(tv, this);
        }
    }
    
    @Override
    public Subst unify(TypeTuple tp) throws UnificationException {
        throw new UnificationException(this, tp);
    }

    @Override
    public String toString() {
        return String.format("(%s) -> (%s)", taArg, taRes);
    }
}
