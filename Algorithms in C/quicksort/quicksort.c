/*
	* Quick Sort algorithm 
	
	* Author: Apostolos Sinioris
*/

#include <stdio.h>
#include <stdlib.h>

void quicksort(int* arr, int low, int high) {
	int j;

	if (low < high) {
		j = partition(arr, low, high);
		quicksort(arr, low, j - 1);
		quicksort(arr, j + 1, high);
	}
}

int partition(int *arr, int l, int r) {
	int pivot, i, j, t;
	pivot = arr[l];
	i = l;
	j = r + 1;

	while (1) {
		do ++i;
		while (arr[i] <= pivot && i < j);
		do --j;
		while (arr[j] > pivot);
		if (i >= j) break;
		t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	t = arr[l];
	arr[l] = arr[j];
	arr[j] = t;

	return j;
}

int main() {
	int n;

	printf("Enter the number of elements: ");
	scanf("%d", &n);

	int* arr = (int*)malloc(n * sizeof(int));

	printf("Enter the elements \n");

	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}
	quicksort(arr, 0, n - 1);

	printf("\nSorted array: ");
	for (int i = 0; i < n; i++) {
		printf("%d ", arr[i]);
	}

	free(arr);

	return 0;
}