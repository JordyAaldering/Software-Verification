package main;

import ast.expr.IExp;
import ast.type.TypeVar;
import examples.IExample;
import typing.Environ;
import typing.Subst;
import typing.TVPool;
import typing.UnificationException;

class Main {
    private static void _typeTest(IExp prog) throws UnificationException {
        TVPool tvPool = new TVPool();
        TypeVar var   = tvPool.freshTV();
        Subst subst   = prog.inferType(new Environ(), var, tvPool);
        System.out.println(var.substitute(subst));
    }

    private static void typeTest() {
        try {
            _typeTest(IExample.prog1);
            _typeTest(IExample.prog2);
            _typeTest(IExample.prog3);
            _typeTest(IExample.prog4);
            _typeTest(IExample.prog5);
            _typeTest(IExample.prog6);
            _typeTest(IExample.prog7);
            _typeTest(IExample.prog8);
        }
        catch (UnificationException ex) {
            System.out.println(ex);
        }
    }

    private static void unifyTest() {
        System.out.println(IExample.type1);
        System.out.println(IExample.type2);

        try {
            Subst subst = IExample.type1.unify(IExample.type2);
            System.out.println(IExample.type1.substitute(subst));
            System.out.println(IExample.type2.substitute(subst));

            subst = IExample.type3.unify(IExample.type4);
            System.out.println(IExample.type3.substitute(subst));
            System.out.println(IExample.type4.substitute(subst));
        }
        catch (UnificationException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        typeTest();
        unifyTest();
    }
}
