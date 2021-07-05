use rayon::prelude::*;
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
    fn solve(&self, verbose: bool) -> String {
        let primes: Vec<u64> = (0u64..1_000_000).into_par_iter().filter(|n| n.is_prime()).collect();
        let max = primes.par_iter().map(|p| (longest_prime_sum(*p, &primes), p)).max_by_key(|x| x.0);
        let max_prime = max.unwrap().1;
        max_prime.to_string()
    }
    fn is_slow(&self) -> bool { true }
    fn problem_num(&self) -> i32 { 50 }
    fn answer_desc(&self) -> String { "Prime".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("997651".to_string()) }
}
