#include <stdio.h>
#include <string.h>

#define MAX_LINE 10005
#define MAX_WORDS 1000

int main() {
    int N;
    scanf("%d", &N);
    getchar();

    for (int i = 0; i < N; i++) {
        char line[MAX_LINE];

        if (fgets(line, sizeof(line), stdin) == NULL) {
            break;
        }

        line[strcspn(line, "\n")] = '\0';

        char *words[MAX_WORDS];
        int wordCount = 0;

        char *token = strtok(line, " ");

        while (token != NULL && wordCount < MAX_WORDS) {
            words[wordCount++] = token;
            token = strtok(NULL, " ");
        }

        // Bubble Sort: palavras maiores primeiro
        for (int j = 0; j < wordCount - 1; j++) {
            for (int k = 0; k < wordCount - j - 1; k++) {
                if (strlen(words[k]) < strlen(words[k + 1])) {
                    char *temp = words[k];
                    words[k] = words[k + 1];
                    words[k + 1] = temp;
                }
            }
        }

        // Imprime as palavras ordenadas
        for (int j = 0; j < wordCount; j++) {
            if (j > 0) {
                printf(" ");
            }

            printf("%s", words[j]);
        }

        printf("\n");
    }

    return 0;
}