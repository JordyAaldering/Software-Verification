package ast.expr;

import ast.type.IType;
import ast.type.TypeConst;
import ast.type.TypeTuple;
import ast.type.TypeVar;
import typing.TVPool;

public enum UnaryOp {    
    opNot {
        @Override
        public IType[] getType(TVPool tvPool){
            return unaOpType(TypeConst.BoolType, TypeConst.BoolType);
        }
    },
    opFst {
        @Override
        public IType[] getType(TVPool tvPool) {
            TypeVar fst = tvPool.freshTV();
            return unaOpType(new TypeTuple(fst, tvPool.freshTV()), fst);
        }
    },
    opSnd {
        @Override
        public IType[] getType(TVPool tvPool) {
            TypeVar snd = tvPool.freshTV();
            return unaOpType(new TypeTuple(tvPool.freshTV(), snd), snd);
        }
    };

    public static IType[] unaOpType(IType argType, IType resType) {
        return new IType[] {argType, resType};
    }

    abstract public IType[] getType(TVPool tvPool);
}
