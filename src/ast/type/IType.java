package ast.type;

import typing.IUnify;
import typing.Subst;
import java.util.List;

public interface IType extends IUnify {
    IType substitute(Subst subst);
    
    boolean containsTV(TypeVar var);
    void collectTVs(List<TypeVar> typeVars);
}
