/*
	Merge sort algorithm

	Author: Apostolos Sinioris
*/

#include <stdio.h>

void merge(int a[], int l, int m, int r);
void mergesort(int a[], int l, int r);

void mergesort(int a[], int l, int r) {
	if (l < r) {
		int m = (l + r) / 2;

		mergesort(a, l, m);
		mergesort(a, m + 1, r);
		merge(a, l, m, r);
	}
}

void merge(int a[], int l, int m, int r) {
	int i, j, k;
	int n1 = m - l + 1;
	int n2 = r - m;
	int* L = (int*)malloc(n1 * sizeof(int));
	int* R = (int*)malloc(n2 * sizeof(int));

	for (i = 0; i < n1; i++)
		L[i] = a[l + i];
	for (j = 0; j < n2; j++)
		R[j] = a[m + 1 + j];

	i = 0;
	j = 0;
	k = l;
	while (i < n1 && j < n2) {
		if (L[i] <= R[j]) {
			a[k] = L[i];
			i++;
		}
		else {
			a[k] = R[j];
			j++;
		}
		k++;
	}
	while (i < n1) {
		a[k] = L[i];
		i++;
		k++;
	}
	while (j < n2) {
		a[k] = R[j];
		j++;
		k++;
	}

	free(L);
	free(R);
}

int main() {
	int n;

	printf("Enter the number of elements: ");
	scanf("%d", &n);

	int* a = (int*)malloc(n * sizeof(int));
	for (int i = 0; i < n; i++) {
		printf("Enter element %d: ", i + 1);
		scanf("%d", &a[i]);
	}

	mergesort(a, 0, n - 1);
	printf("\nSorted array: ");
	for(int i = 0; i < n; i++)
		printf("%d ", a[i]);

	free(a);

	return 0;
}
