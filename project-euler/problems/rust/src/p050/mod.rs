use rust_math_tools::PrimeTest;

/// Gets the size of the longest number of consecutive elements sum together to equal target
/// Assumes primes is increasing
fn longest_prime_sum(target: u64, primes: &Vec<u64>) -> usize {
    let mut longest_streak = 0;
    let mut sum = 0;
    let mut i_left: usize = 0;
    let mut i_right: usize = 0;
    // Grow initially
    while sum < target && i_right < primes.len() {
        sum += primes[i_right];
        i_right += 1;
    }
    i_right -= 1;
    if sum == target {
        longest_streak = std::cmp::max(longest_streak, i_right - i_left + 1);
    }

    loop {
        // Take off of left until sum <= target
        while sum > target {
            sum -= primes[i_left];
            i_left += 1;
        }
        if sum == target {
            longest_streak = std::cmp::max(longest_streak, i_right - i_left + 1);
        }
        // Add on right
        i_right += 1;
        if ! (i_right < primes.len() && primes[i_right] < target) { break; }
        sum += primes[i_right];
    }

    longest_streak
}

pub struct P050 {}
impl crate::Problem for P050 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let primes: Vec<u64> = (0u64..1_000_000).filter(|n| n.is_prime()).collect();
        let mut largest_consecutive = 0;
        let mut largest_prime = 0;
        for p in &primes {
            let consecutive = longest_prime_sum(*p, &primes);
            if consecutive > largest_consecutive {
                largest_consecutive = consecutive;
                largest_prime = *p;
            }
        }
        (50, "Prime".to_string(), largest_prime.to_string())
        // Answer: 997651
    }
}
