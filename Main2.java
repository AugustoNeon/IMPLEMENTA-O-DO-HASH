import java.util.Random;

public class Main2 {
    static int[] tabela = new int[100];
    static int[] colisoes = new int[100];
    static boolean[] ocupado = new boolean[100];
    static int ocupadas = 0;
    static int totalColisoes = 0;
    static int passos = 0;

    static void inserir(int valor) {
        int posicao = valor % 100;
        int colidiu = 0;

        while (ocupado[posicao]) {
            colidiu++;
            totalColisoes++;
            posicao = (posicao + 1) % 100;
        }

        tabela[posicao] = valor;
        colisoes[posicao] = colidiu;
        ocupado[posicao] = true;
        ocupadas++;
    }

    static int maisColidiu(int ex1, int ex2) {
        int melhor = -1;
        for (int i = 0; i < 100; i++) {
            if (ocupado[i] && i != ex1 && i != ex2) {
                if (melhor == -1 || colisoes[i] > colisoes[melhor]) {
                    melhor = i;
                }
            }
        }
        return melhor;
    }

    static int buscar(int valor) {
        int posicao = valor % 100;
        passos = 1;
        while (ocupado[posicao] && passos <= 100) {
            if (tabela[posicao] == valor) {
                return posicao;
            }
            posicao = (posicao + 1) % 100;
            passos++;
        }
        return -1;
    }

    static void imprimirLivres() {
        System.out.print("Posicoes livres: ");
        for (int i = 0; i < 100; i++) {
            if (!ocupado[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    static void testarBusca(int valor) {
        int posicao = buscar(valor);
        if (posicao == -1) {
            System.out.println(valor + " nao encontrado");
        } else {
            System.out.println(valor + " encontrado na posicao " + posicao + " (" + passos + " acessos)");
        }
    }

    public static void main(String[] args) {
        Random random = new Random(1090);
        int naoInseridos = 0;

        for (int i = 0; i < 100; i++) {
            int valor = random.nextInt(100001);
            if (ocupadas < 90) {
                inserir(valor);
            } else {
                naoInseridos++;
            }
        }

        int t1 = maisColidiu(-1, -1);
        int t2 = maisColidiu(t1, -1);
        int t3 = maisColidiu(t1, t2);

        System.out.println("Inseridos: " + ocupadas);
        System.out.println("Nao inseridos: " + naoInseridos);
        System.out.println("Colisoes: " + totalColisoes);
        System.out.println("Mais colidiram: " + tabela[t1] + " (" + colisoes[t1] + "), "
                + tabela[t2] + " (" + colisoes[t2] + "), "
                + tabela[t3] + " (" + colisoes[t3] + ")");
        imprimirLivres();

        System.out.println("Busca:");
        testarBusca(tabela[t1]);
        testarBusca(tabela[t2]);
        testarBusca(tabela[t3]);
    }
}
