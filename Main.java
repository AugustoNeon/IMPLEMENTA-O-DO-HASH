import java.util.Random;

class No {
    int valor;
    No proximo;

    No(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class Main {
    static No[] tabela = new No[100];
    static int ultimo1 = -1;
    static int ultimo2 = -1;
    static int ultimo3 = -1;

    static void inserir(int valor) {
        int posicao = valor % 100;
        No novo = new No(valor);

        if (tabela[posicao] == null) {
            tabela[posicao] = novo;
        } else {
            if (valor < tabela[posicao].valor) {
                novo.proximo = tabela[posicao];
                tabela[posicao] = novo;
            } else {
                No atual = tabela[posicao];
                while (atual.proximo != null && atual.proximo.valor < valor) {
                    atual = atual.proximo;
                }
                novo.proximo = atual.proximo;
                atual.proximo = novo;
            }

            ultimo1 = ultimo2;
            ultimo2 = ultimo3;
            ultimo3 = valor;
        }
    }

    static int tamanho(No inicio) {
        int cont = 0;
        No atual = inicio;
        while (atual != null) {
            cont++;
            atual = atual.proximo;
        }
        return cont;
    }

    static int posicaoMaiorLista() {
        int maior = 0;
        int posicao = 0;
        for (int i = 0; i < 100; i++) {
            int t = tamanho(tabela[i]);
            if (t > maior) {
                maior = t;
                posicao = i;
            }
        }
        return posicao;
    }

    static int contarListas() {
        int cont = 0;
        for (int i = 0; i < 100; i++) {
            if (tamanho(tabela[i]) > 1) {
                cont++;
            }
        }
        return cont;
    }

    static int contarVazias() {
        int cont = 0;
        for (int i = 0; i < 100; i++) {
            if (tabela[i] == null) {
                cont++;
            }
        }
        return cont;
    }

    static void imprimirLista(int posicao) {
        No atual = tabela[posicao];
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Random random = new Random(1090);

        for (int i = 0; i < 1000; i++) {
            inserir(random.nextInt(100001));
        }

        int maior = posicaoMaiorLista();
        System.out.println("Maior lista (posicao " + maior + "):");
        imprimirLista(maior);
        System.out.println("Tamanho: " + tamanho(tabela[maior]));
        System.out.println("Listas: " + contarListas());
        System.out.println("Vazias: " + contarVazias());
        System.out.println("Ultimos 3: " + ultimo1 + " " + ultimo2 + " " + ultimo3);
    }
}
