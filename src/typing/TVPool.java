package typing;

import ast.type.TypeVar;

public class TVPool {
    private int nextVarId = 0;
    
    public TypeVar freshTV() {
        return new TypeVar(nextVarId++);
    }
}
