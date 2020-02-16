package typing;

import ast.type.*;

public interface IUnify {
    Subst unify(IType t) throws UnificationException;
    Subst unify(TypeScheme ts) throws UnificationException;

    Subst unify(TypeArrow ta) throws UnificationException;
    Subst unify(TypeTuple tp) throws UnificationException;
    Subst unify(TypeConst tc) throws UnificationException;
    Subst unify(TypeVar ta)   throws UnificationException;
}