#include <stdio.h>

#define MAX 105
#define MAX_MOVIMENTOS 50005

int main() {
    int N, M, S;

    while (scanf("%d %d %d", &N, &M, &S) == 3) {
        if (N == 0 && M == 0 && S == 0) {
            break;
        }

        char arena[MAX][MAX];
        char movimentos[MAX_MOVIMENTOS];

        int linha = 0, coluna = 0;
        int direcao = 0;
        int figurinhas = 0;

        int dl[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};

        for (int i = 0; i < N; i++) {
            scanf("%s", arena[i]);

            for (int j = 0; j < M; j++) {
                if (arena[i][j] == 'N') {
                    linha = i;
                    coluna = j;
                    direcao = 0;
                    arena[i][j] = '.';
                } else if (arena[i][j] == 'L') {
                    linha = i;
                    coluna = j;
                    direcao = 1;
                    arena[i][j] = '.';
                } else if (arena[i][j] == 'S') {
                    linha = i;
                    coluna = j;
                    direcao = 2;
                    arena[i][j] = '.';
                } else if (arena[i][j] == 'O') {
                    linha = i;
                    coluna = j;
                    direcao = 3;
                    arena[i][j] = '.';
                }
            }
        }

        scanf("%s", movimentos);

        for (int i = 0; i < S; i++) {
            if (movimentos[i] == 'D') {
                direcao = (direcao + 1) % 4;
            } else if (movimentos[i] == 'E') {
                direcao = (direcao + 3) % 4;
            } else if (movimentos[i] == 'F') {
                int novaLinha = linha + dl[direcao];
                int novaColuna = coluna + dc[direcao];

                if (novaLinha >= 0 && novaLinha < N &&
                    novaColuna >= 0 && novaColuna < M &&
                    arena[novaLinha][novaColuna] != '#') {

                    linha = novaLinha;
                    coluna = novaColuna;

                    if (arena[linha][coluna] == '*') {
                        figurinhas++;
                        arena[linha][coluna] = '.';
                    }
                }
            }
        }

        printf("%d\n", figurinhas);
    }

    return 0;
}