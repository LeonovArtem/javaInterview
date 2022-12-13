package codeExample.OOП;

public class СтатическоеСвязывание {
    public static void main(String[] args) {
        A b = new B();

        // Раннее статическое
        b.staticPrintClassName();

        // Позднее динамическое связывание
        b.printClassName();
    }
}


class A {
    public void printClassName() {
        System.out.println(A.class);
    }

    public static void staticPrintClassName() {
        System.out.println(A.class);
    }
}

class B extends A {
    @Override
    public void printClassName() {
        System.out.println(B.class);
    }

    public static void staticPrintClassName() {
        System.out.println(B.class);
    }
}