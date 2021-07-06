use rayon::prelude::*;
use rust_math_tools::{divisors_sum_type, DivSum};

// TODO: parallelize

pub struct P023 {}
impl crate::Problem for P023 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let abundant_nums: Vec<u64> = (1..=28123)
            .into_par_iter()
            .map(|n| (n, divisors_sum_type(n)))
            .filter(|(_, t)| t == &DivSum::Abundant)
            .map(|(n, _)| n)
            .collect();

        let mut nums: Vec<u64> = (0..=30000).collect();

        for i1 in 0..abundant_nums.len() {
            for i2 in 0..abundant_nums.len() {
                let temp = abundant_nums[i1] + abundant_nums[i2];
                if temp < nums.len() as u64 {
                    nums[temp as usize] = 0;
                }
            }
        }
        let total: u64 = nums.par_iter().sum();
        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        23
    }
    fn answer_desc(&self) -> String {
        "Sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("4179871".to_string())
    }
}
