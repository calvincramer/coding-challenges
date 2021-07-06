use integer_sqrt::IntegerSquareRoot;
use rust_math_tools;
use rust_math_tools::PrimeTest;
use std::cmp;

pub struct P003 {}
impl crate::Problem for P003 {
    fn solve(&self, verbose: bool) -> String {
        let num: u64 = 600851475143;
        let mut num_sqrt = num.integer_sqrt();
        let mut largest_prime_factor: u64 = 1;

        if num_sqrt.pow(2) == num {
            num_sqrt += 1;
        }

        for div in (3..=num_sqrt + 1).step_by(2) {
            if num % div == 0 {
                // Is a factor
                if verbose {
                    println!("Factor: {}", div);
                }
                // Is a prime factor?
                if div.is_prime() {
                    largest_prime_factor = cmp::max(largest_prime_factor, div)
                }
            }
        }
        largest_prime_factor.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        3
    }
    fn answer_desc(&self) -> String {
        "Largest prime factor".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("6857".to_string())
    }
}
