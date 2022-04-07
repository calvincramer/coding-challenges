// See the python version for more explanation and comments

#include <cstdint>  /* fixed-width int types */
#include <iostream> /* printing */
#include <chrono>   /* timing */
#include <vector>
#include <string>
#include <cmath>

using std::string;

typedef uint8_t u8;
typedef uint64_t u64;
typedef double f64;
typedef std::chrono::time_point<std::chrono::high_resolution_clock> tp;
typedef u8 SJU;

const SJU SPLIT = 0;
const SJU JOIN = 1;
const SJU UNDECIDED = 2;

tp time() {
    return std::chrono::high_resolution_clock::now();
}

f64 duration_from(tp start) {
    auto duration = std::chrono::duration_cast<std::chrono::microseconds>(time() - start);
    return duration.count() * 1.0 / 1000000;
}


class Splitter {
public:
    u64 target;
    std::vector<u64> nums;
    std::vector<u64> sjs;   /* split joins array */

    bool split(u64);
private:
    u64 calculate_total(SJU);
    bool trav(u64);
};

bool Splitter::split(u64 n) {
    target = n;
    u64 n_sqr = n * n;
    // Create nums with digits
    nums.clear();
    u64 temp_n = n_sqr;
    while (temp_n > 0) {
        nums.insert(nums.begin(), temp_n % 10);
        temp_n /= 10;
    }
    // Create split join array
    sjs.clear();
    for (int i = 1; i <= nums.size() - 1; i++) {
        sjs.push_back(UNDECIDED);
    }
    return trav(0);
}

u64 Splitter::calculate_total(SJU undecided_use) {
    u64 value = 0;
    int i = 0;
    while (i < nums.size()) {
        u64 temp_sum = nums[i];
        if (i == sjs.size()) {
            value += temp_sum;
            break;
        }
        if (sjs[i] == JOIN || (sjs[i] == UNDECIDED && undecided_use == JOIN)) {
            int k = i;
            while (k < this->sjs.size() && (sjs[k] == JOIN || (sjs[k] == UNDECIDED && undecided_use == JOIN))) {
                k += 1;
                temp_sum = (temp_sum * 10) + this->nums[k];
            }
            i = k + 1;
        } else {
            i += 1;
        }
        value += temp_sum;
    }
    return value;
}

bool Splitter::trav(u64 sj_idx) {
    if (sj_idx + 1 == nums.size()) {
        return target == this->calculate_total(UNDECIDED);
    }
    if (this->calculate_total(SPLIT) > target) {
        sjs[sj_idx] = UNDECIDED;
        return false;
    }
    if (this->calculate_total(JOIN) < target) {
        sjs[sj_idx] = UNDECIDED;
        return false;
    }
    // left
    sjs[sj_idx] = JOIN;
    if (trav(sj_idx + 1)) {
        return true;
    }
    // right
    sjs[sj_idx] = SPLIT;
    if (trav(sj_idx + 1)) {
        return true;
    }
    sjs[sj_idx] = UNDECIDED;
    return false;
}


void T(u64 N, string msg, string real_answer) {
    tp start = time();
    u64 total = 0;
    Splitter splitter;
    u64 end = u64(sqrt(N));

    for (u64 n = 2; n <= end; n++) {
        if (splitter.split(n)) {
            total += n*n;
        }
    }

    f64 duration = duration_from(start);

    using std::cout;
    cout
        << msg << " T(" << N << ") = " << total
        << "\t real answer = " << real_answer
        << "\t total time = " << duration << "s\n";
}

u64 int_pow(u64 a, u64 b) {
    u64 ans = a;
    while (b > 1) {
        ans *= a;
        b -= 1;
    }
    return ans;
}


int main() {
    T(int_pow(10, 4), "10^4", "41333");
    T(int_pow(10, 5), "10^5", "?");
    T(int_pow(10, 6), "10^6", "?");
    T(int_pow(10, 7), "10^7", "?");
    T(int_pow(10, 8), "10^8", "?");
    T(int_pow(10, 9), "10^9", "?");
    T(int_pow(10, 12), "10^12", "128088830547982");
    return 0;
}
