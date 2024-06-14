#include <stdio.h>
#include <stdlib.h>

#define INTERVAL 10000

double pi() {
	int circle_points, square_points;
	double x, y, r, pi;

	circle_points = square_points = 0;

	for (int i = 0; i < INTERVAL; i++) {
		x = (double) rand() / RAND_MAX;
		y = (double) rand() / RAND_MAX;	

		r = x * x + y * y;
		if(r <= 1) circle_points++;
		square_points++;
	}
	pi = (4.0 * circle_points) / square_points;

	return pi;
}

int main() {
	printf("Approximate value of pi: %.5f\n", pi());
	return 0;
}