#include <stdio.h>
#include <stdlib.h>
#define MAX 1000

int main() {
	int n; 

	while(scanf("%d", &n) == 1 && n != 0) {
		int target[MAX];

		
		while (1) {
		scanf("%d", &target[0]);

		if (target[0] == 0) {
			break;
		}

		for(int i = 1; i < n; i++) {
			scanf("%d", &target[i]);
		}


		int stack[MAX];
		int top = -1; 
		int nextWagon = 1;
		int possible = 1;

		for (int i = 0; i < n; i++) {
			int desired = target[i];

			while (nextWagon <= n && (top == -1 || stack[top] != desired)) {
				top++;
				stack[top] = nextWagon;
				nextWagon++;
			}

			if (top >= 0 && stack[top] == desired) {
				top--;
			} else {
				possible = 0; 
				break;
			}

		    }
		    if (possible) {
			    printf("Yes\n");
	            } else {
			    printf("No\n");
                    } 

		} 

		printf("\n");
	}

	return 0; 

} 
