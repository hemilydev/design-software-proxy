public class ContaCorrente implements Conta {

    @Override
    public void sacar(double valor) {

        System.out.println(
            "Sacando R$ " + valor
        );

    }
}