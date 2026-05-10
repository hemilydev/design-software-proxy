public class Main {

    public static void main(String[] args) {

        System.out.println(
            "=== USUÁRIO NÃO AUTENTICADO ==="
        );

        Conta conta1 =
            new ContaProxy(false, 1000);

        conta1.sacar(100);

        System.out.println();

        System.out.println(
            "=== USUÁRIO AUTENTICADO ==="
        );

        Conta conta2 =
            new ContaProxy(true, 1000);

        conta2.sacar(300);

        System.out.println();

        conta2.sacar(500);

        System.out.println();

        conta2.sacar(300);
    }
}