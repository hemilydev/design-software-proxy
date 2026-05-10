# Padrão Proxy – Sistema Bancário

> Atividade Prática — Padrão de Projeto Proxy (Gang of Four)  
> Disciplina: Design de Software  
> Prof. Welington Julio Dias Rodrigues  

---

## Sobre a Atividade

Implementação do **Padrão Estrutural Proxy** aplicado a um sistema bancário simplificado.

O `ContaProxy` atua como intermediário entre o cliente e a `ContaCorrente`, adicionando controle de autenticação, log e limite diário de saques — sem modificar a classe original.

> *"Um substituto ou representante de outro objeto, controlando o acesso a ele."*  
> — Gang of Four (GoF), 1994

---

## Estrutura do Projeto

```
atividade-proxy/
├── Conta.java          # Interface (Subject) — contrato comum
├── ContaCorrente.java  # RealSubject — executa o saque real
├── ContaProxy.java     # Proxy — controle de acesso, log e lazy init
└── Main.java           # Cliente — testa os dois cenários
```

---

## Diagrama de Classes

```
«interface»
Conta
+ sacar(double valor): void
        ▲                    ▲
        │                    │
ContaCorrente           ContaProxy
─────────────           ──────────────────────────
+ sacar(double)         - real: ContaCorrente
                        - autenticado: boolean
                        - limiteDiario: double
                        - totalSacadoHoje: double
                        - quantidadeSaques: int
                        ──────────────────────────
                        + sacar(double valor)
```

---

## Funcionalidades do Proxy

| Recurso | Descrição |
| --- | --- |
| **Verificação de autenticação** | Bloqueia o saque se `autenticado` for `false` |
| **Lazy Initialization** | `ContaCorrente` só é instanciada no primeiro saque autorizado |
| **Log com horário** | Registra cada saque com `LocalTime.now()` |
| **Limite diário** | Impede saques que ultrapassem o valor acumulado no dia |
| **Delegação** | Após os controles, delega a operação real à `ContaCorrente` |

---

## Saída Esperada

```
=== USUÁRIO NÃO AUTENTICADO ===
Acesso NEGADO. Usuário não autenticado.

=== USUÁRIO AUTENTICADO ===
ContaCorrente criada.
[10:32:45.123] Saque solicitado: R$ 300.0
Sacando R$ 300.0
Quantidade de saques hoje: 1
Total sacado hoje: R$ 300.0

[10:32:45.124] Saque solicitado: R$ 500.0
Sacando R$ 500.0
Quantidade de saques hoje: 2
Total sacado hoje: R$ 800.0

Limite diário excedido.
```

---

## Conceitos Aplicados

| Conceito | Onde aparece |
| --- | --- |
| **Proxy de Proteção** | Verificação de `autenticado` antes de qualquer operação |
| **Proxy Virtual** | Lazy initialization — `ContaCorrente` criada só quando necessário |
| **Proxy de Log** | Registro de horário e valor a cada saque via `LocalTime.now()` |
| **Princípio OCP** | `ContaCorrente` não foi modificada em nenhum momento |
| **Transparência** | O cliente usa a interface `Conta`, sem saber se é proxy ou real |

---

## Como Executar

```bash
# Compilar
javac *.java

# Executar
java Main
```

---

## Identificação

**Hemily Ramos**                        
Análise e Desenvolvimento de Sistemas — Escola Politécnica e de Artes da PUC Goiás  
Design de Software — Prof. Welington Julio Dias Rodrigues — Maio de 2026  
