use rust_math_tools::PrimeTest;
use itertools::Itertools;

pub struct P041 {}
impl crate::Problem for P041 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut largest = 0;
        // Generate all pandigital combinations
        let mut digit_vec = vec![];

        for digit in 1..=9 {
            digit_vec.push(digit);
            for pandigital_vec in digit_vec.iter().permutations(digit_vec.len()) {
                // Speed up: can tell if not prime early.
                match pandigital_vec[pandigital_vec.len() - 1] {
                    0 | 2 | 4 | 5 | 6 | 8 => continue,
                    _ => (),
                }
                // vector of digits to number
                let n = pandigital_vec.iter().map(|n| n.to_string()).join("").parse::<u64>().unwrap();
                if ! n.is_prime() { continue; }
                largest = std::cmp::max(largest, n);
            }
        }

        (41, "Largest".to_string(), largest.to_string())
        // Answer: 7652413
    }
}