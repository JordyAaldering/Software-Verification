package ast.expr;

import ast.type.IType;
import ast.type.TypeConst;
import ast.type.TypeTuple;
import ast.type.TypeVar;
import typing.TVPool;

public enum BinOp {    
    opPlus {
        @Override
        public IType[] getType(TVPool tvPool){
            return binOpType(TypeConst.IntType, TypeConst.IntType, TypeConst.IntType);
        }
    },
    opMin {
        @Override
        public IType[] getType(TVPool tvPool){
            return binOpType(TypeConst.IntType, TypeConst.IntType, TypeConst.IntType);
        }
    },
    opMul {
        @Override
        public IType[] getType(TVPool tvPool){
            return binOpType(TypeConst.IntType, TypeConst.IntType, TypeConst.IntType);
        }
    },
    opDiv {
        @Override
        public IType[] getType(TVPool tvPool){
            return binOpType(TypeConst.IntType, TypeConst.IntType, TypeConst.IntType);
        }
    },
    opEq {
        @Override
        public IType[] getType(TVPool tvPool){
            return binOpType(TypeConst.IntType, TypeConst.IntType, TypeConst.BoolType);
        }
    },
    opLt {
        @Override
        public IType[] getType(TVPool tvPool){
            return binOpType(TypeConst.IntType, TypeConst.IntType, TypeConst.BoolType);
        }
    },
    opTuple {
        @Override
        public IType[] getType(TVPool tvPool) {
            TypeVar fst = tvPool.freshTV();
            TypeVar snd = tvPool.freshTV();
            return binOpType(fst, snd, new TypeTuple(fst, snd));
        }
    };

    public static IType[] binOpType(IType arg1Type, IType arg2Type, IType resType) {
        return new IType[] {arg1Type, arg2Type, resType};
    }

    abstract public IType[] getType(TVPool tvPool);
}
