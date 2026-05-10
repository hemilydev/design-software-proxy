import java.time.LocalTime;

public class ContaProxy implements Conta {

    private ContaCorrente real;

    private boolean autenticado;

    private double limiteDiario;

    private double totalSacadoHoje;

    private int quantidadeSaques;

    public ContaProxy(
            boolean autenticado,
            double limiteDiario) {

        this.autenticado = autenticado;
        this.limiteDiario = limiteDiario;
    }

    @Override
    public void sacar(double valor) {

        // Verifica autenticação
        if (!autenticado) {

            System.out.println(
                "Acesso NEGADO. Usuário não autenticado."
            );

            return;
        }

        // Verifica limite diário
        if ((totalSacadoHoje + valor)
                > limiteDiario) {

            System.out.println(
                "Limite diário excedido."
            );

            return;
        }

        // Lazy Initialization
        if (real == null) {

            real = new ContaCorrente();

            System.out.println(
                "ContaCorrente criada."
            );
        }

        // Log
        System.out.println(
            "[" + LocalTime.now() + "] "
            + "Saque solicitado: R$ "
            + valor
        );

        quantidadeSaques++;

        totalSacadoHoje += valor;

        // Delegação
        real.sacar(valor);

        // Informações extras
        System.out.println(
            "Quantidade de saques hoje: "
            + quantidadeSaques
        );

        System.out.println(
            "Total sacado hoje: R$ "
            + totalSacadoHoje
        );
    }
}