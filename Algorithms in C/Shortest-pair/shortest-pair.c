/*
 
	* Shortest Pair algorithm using recursion
 
	* Author: Apostolos Sinioris
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct Point {
	int x, y;
};

float distance(struct Point *arr, int i, int j) {
	return (float) sqrt(pow(arr[i].x - arr[j].x, 2) + pow(arr[i].y - arr[j].y, 2));
}

float bruteForce(struct Point *arr, int n) {
	int i;
	float min_d = 0;

	for (i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			float d = distance(arr, i, j);
			if (i == 0 && j == 1)
				min_d = d;
			else if (d < min_d)
				min_d = d;
		}
	}
	return min_d;
}

float stripNearest(struct Point *strip, int size, float d) {
	float min_d = d;

	for (int i = 0; i < size; i++) {
		for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min_d; j++) {
			float d = distance(strip, i, j);
			if (d < min_d)
				min_d = d;
		}
	}
	return min_d;
}

float nearestFunc(struct Point *arr, int n) {
	if(n <= 3)
		return bruteForce(arr, n);
	int mid = n / 2;
	struct Point midPoint = arr[mid];

	float dl = nearestFunc(arr, mid);
	float dr = nearestFunc(arr + mid, n - mid);
	float d = min(dl, dr);

	struct Point* strip = (struct Point*)malloc(n * sizeof(struct Point));
	int j = 0;

	for(int i = 0; i < n; i++) {
		if(abs(arr[i].x - midPoint.x) < d) {
			strip[j] = arr[i];
			j++;
		}
	}
	float min_d = min(d, stripNearest(strip, j, d));

	free(strip);

	return min_d;
}

int main() {
	int n;

	printf("Insert the number of the elements: ");
	scanf("%d", &n);

	struct Point* arr = (struct Point*)malloc(n * sizeof(struct Point));

	printf("Insert the elements: \n");
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &arr[i].x, &arr[i].y);
	}

	printf("The shortest pair is: %f\n", nearestFunc(arr, n));

	return 0;
}