#include <stdio.h>
#include <stdlib.h>

int compareEven(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;

    if (x < y) return -1;
    if (x > y) return 1;
    return 0;
}

int compareOdd(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;

    if (x > y) return -1;
    if (x < y) return 1;
    return 0;
}

int main() {
    int N;
    scanf("%d", &N);

    int *evenNumbers = (int *)malloc(N * sizeof(int));
    int *oddNumbers = (int *)malloc(N * sizeof(int));

    if (evenNumbers == NULL || oddNumbers == NULL) {
        free(evenNumbers);
        free(oddNumbers);
        return 1;
    }

    int evenCount = 0;
    int oddCount = 0;

    for (int i = 0; i < N; i++) {
        int number;
        scanf("%d", &number);

        if (number % 2 == 0) {
            evenNumbers[evenCount++] = number;
        } else {
            oddNumbers[oddCount++] = number;
        }
    }

    qsort(evenNumbers, evenCount, sizeof(int), compareEven);
    qsort(oddNumbers, oddCount, sizeof(int), compareOdd);

    for (int i = 0; i < evenCount; i++) {
        printf("%d\n", evenNumbers[i]);
    }

    for (int i = 0; i < oddCount; i++) {
        printf("%d\n", oddNumbers[i]);
    }

    free(evenNumbers);
    free(oddNumbers);

    return 0;
}