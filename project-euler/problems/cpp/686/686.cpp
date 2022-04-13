#include <iostream>
#include <cmath>    /* log2l */
#include <cstdint>  /* fixed-width int types */

typedef double f64;
typedef long double f128;   // 16 bytes on my machine
typedef uint64_t u64;


int main() {
    const f128 L = 123.0;
    const f128 log2_L = log2l(L);
    const f128 log2_Lp1 = log2l(L+1);
    const f128 log2_10 = log2l(10.0);

    f128 low = log2_L;
    f128 high = log2_Lp1;

    u64 count = 0;
    const u64 target_count = 678910;

    while (true) {
        // Increment low and high
        low += log2_10;
        high += log2_10;

        // See if low and high go over unit boundary
        // if (high - ((u64)low) >= 1.0) {
        if ((u64)low != (u64)high) {
            count += 1;
            u64 pow_2_val = (u64) high;
            if (count == target_count) {
                std::cout << "!!! " << count << ": " << pow_2_val << "\n";
                break;
            } else {
                std::cout << count << ": " << pow_2_val << "\n";
            }
        }
    }


    return 0;
}