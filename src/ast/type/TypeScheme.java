package ast.type;

import typing.Subst;
import typing.TVPool;
import java.util.LinkedList;
import java.util.List;

public class TypeScheme {
    private List<TypeVar> quantTVs;
    private IType schemeType;

    public TypeScheme(List<TypeVar> quantTVs, IType schemeType) {
        this.quantTVs   = quantTVs;
        this.schemeType = schemeType;
    }

    public TypeScheme(IType schemeType) {
        this.schemeType = schemeType;
        this.quantTVs   = new LinkedList<>();
    }
    
    public void collectFVs(List<TypeVar> typeVars) {
        List<TypeVar> fvs = new LinkedList<>();
        schemeType.collectTVs(fvs);
        fvs.removeAll(quantTVs);
        typeVars.addAll(fvs);
    }
    
    public IType instantiateType(TVPool tvPool) {
        Subst subst = new Subst(quantTVs, tvPool);
        return schemeType.substitute(subst);
    }
    
    public TypeScheme substitute(Subst subst) {
        Subst substCopy = subst.copy(quantTVs);
        return new TypeScheme(quantTVs, schemeType.substitute(substCopy));
    }
}
