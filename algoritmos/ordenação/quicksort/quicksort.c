#include <stdio.h>

void quicksort(int array[], int esq, int dir) {
    int i = esq;
    int j = dir;
    int pivo = array[(esq + dir) / 2];

    while (i <= j) {
        while (array[i] < pivo) {
            i++;
        }

        while (array[j] > pivo) {
            j--;
        }

        if (i <= j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            i++;
            j--;
        }
    }

    if (esq < j) {
        quicksort(array, esq, j);
    }

    if (i < dir) {
        quicksort(array, i, dir);
    }
}

int main() {
    int array[] = {8, 3, 1, 7, 0, 10, 2};
    int n = sizeof(array) / sizeof(array[0]);

    quicksort(array, 0, n - 1);

    for (int i = 0; i < n; i++) {
        printf("%d ", array[i]);
    }

    printf("\n");

    return 0;
}