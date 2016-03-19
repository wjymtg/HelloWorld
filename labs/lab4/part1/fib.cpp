#include "fib.h"

int Fibonacci::get(int n) {
	if (n <= 0) { 
    	return 0;
	}
	if (n == 1 || n == 2) {
		return 1;
	}
	return get(n - 1) + get(n - 2);	
}
