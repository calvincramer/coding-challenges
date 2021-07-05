use rayon::prelude::*;
use rust_math_tools::{num_digits, PrimeTest};

/// 1234 -> [1234, 2341, 3412, 4123] (not in any specific order though)
fn cycle_num(mut num: u64) -> Vec<u64> {
    let mut results = vec![];
    let num_digits = num_digits(num) as u32;
    let last_digit_base: u64 = 10u64.pow(num_digits - 1);
    for _ in 0..num_digits {
        // Cycle largest digit to smallest digit
        results.push(num);
        num = ((num % 10) * last_digit_base) + (num / 10);
    }
    results
}

pub struct P035 {}
impl crate::Problem for P035 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let prime_lkup: Vec<bool> = (0u64..1_000_000).into_par_iter().map(|x| x.is_prime()).collect();
        let total_count = (0..1_000_000).into_par_iter()
            .filter(|n| cycle_num(*n)
                .iter()
                .all(|n2| prime_lkup[*n2 as usize])
            )
            .count();

        total_count.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 35 }
    fn answer_desc(&self) -> String { "Total count".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("55".to_string()) }
}
