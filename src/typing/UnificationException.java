package typing;

import ast.type.IType;

public class UnificationException extends Exception {
    public UnificationException(IType type1, IType type2) {
        super (String.format("Cannot unify %s with %s", type1, type2));
    }    
}
