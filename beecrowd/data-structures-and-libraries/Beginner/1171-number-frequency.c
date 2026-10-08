#include <stdio.h>
#include <stdlib.h>

int compareNumbers(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;

    if (x < y) return -1;
    if (x > y) return 1;
    return 0;
}

int main() {
    int N;
    scanf("%d", &N);

    int *numbers = (int *)malloc(N * sizeof(int));

    if (numbers == NULL) {
        return 1;
    }

    for (int i = 0; i < N; i++) {
        scanf("%d", &numbers[i]);
    }

    qsort(numbers, N, sizeof(int), compareNumbers);

    int count = 1;

    for (int i = 1; i < N; i++) {
        if (numbers[i] == numbers[i - 1]) {
            count++;
        } else {
            printf("%d aparece %d vez(es)\n", numbers[i - 1], count);
            count = 1;
        }
    }

    printf("%d aparece %d vez(es)\n", numbers[N - 1], count);

    free(numbers);

    return 0;
}