package typing;

import ast.type.IType;
import ast.type.TypeVar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subst {
    private final Map<TypeVar, IType> tvMap;
    
    public Subst() {
        tvMap = new HashMap<>();
    }
    
    public Subst(TypeVar tv, IType substIType) {
        tvMap = new HashMap<>();
        tvMap.put(tv, substIType);
    }
    
    public Subst(List<TypeVar> tvs, TVPool tvPool) {
        tvMap = new HashMap<>();
        tvs.forEach((tv) -> tvMap.put(tv, tvPool.freshTV()));
    }

    public Subst compose(Subst subst) {
        Subst compSubst = new Subst();
        subst.tvMap.forEach((tv,tvt) -> compSubst.tvMap.put( tv, tvt.substitute(this)));        
        compSubst.tvMap.putAll( tvMap );
        return compSubst;
    }
    
    public IType get(TypeVar tv) {
        IType substIType = tvMap.get(tv);
        return substIType == null ? tv : substIType;
    }

    public Subst copy (List<TypeVar> qtvs) {
        Subst copySubst = new Subst();
        tvMap.forEach((tv,tvt) -> copySubst.tvMap.put(tv, qtvs.contains(tv) ? tv : tvt));
        return copySubst;
    }
}
