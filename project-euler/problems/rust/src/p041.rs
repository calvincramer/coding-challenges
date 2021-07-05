use rayon::prelude::*;
use rust_math_tools::PrimeTest;
use itertools::Itertools;

// TODO: parallelize

pub struct P041 {}
impl crate::Problem for P041 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // Generate all pandigital combinations (slowest part of this problem)
        let mut digit_vec = vec![];
        let mut pandigitals = vec![];

        for digit in 1..=9 {
            digit_vec.push(digit);
            for pandigital_vec in digit_vec.iter().permutations(digit_vec.len()) {
                // Speed up: can tell if not prime early.
                match pandigital_vec[pandigital_vec.len() - 1] {
                    0 | 2 | 4 | 5 | 6 | 8 => continue,
                    _ => (),
                }
                // vector of digits to number
                pandigitals.push(pandigital_vec.iter().map(|n| n.to_string()).join("").parse::<u64>().unwrap());
            }
        }

        let largest = pandigitals.par_iter().filter(|n| n.is_prime()).max().unwrap();

        largest.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 41 }
    fn answer_desc(&self) -> String { "Largest".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("7652413".to_string()) }
}
