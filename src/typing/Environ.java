package typing;

import ast.expr.Var;
import ast.type.TypeScheme;
import java.util.HashMap;
import java.util.Map;

public class Environ {
    private final Map<Var, TypeScheme> environ = new HashMap<>();
    
    public TypeScheme add(Var var, TypeScheme newType) {
        return environ.put(var, newType);
    }
    
    public TypeScheme get(Var var) {
        return environ.get(var);
    }

    public void remove(Var var, TypeScheme prevType) {
        if (var == null) {
            environ.remove(null);
        } else {
            environ.replace(var, prevType);
        }
    }
    
    public Environ substitute(Subst subst) {
        Environ substEnv = new Environ();
        environ.forEach((tv, t) -> substEnv.add(tv, t.substitute(subst)));
        return substEnv;
    }
}
