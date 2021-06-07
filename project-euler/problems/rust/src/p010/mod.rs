use rust_math_tools::PrimeTest;
use rayon::prelude::*;

pub struct P010 {}
impl crate::Problem for P010 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // Iterative
        // let mut sum: u64 = 0;
        // for n in 2u64..2_000_000 {
        //     if n.is_prime() {
        //         sum += n;
        //     }
        // }

        // Parallel
        (2u64..2_000_000).into_par_iter().filter(|n| n.is_prime()).sum::<u64>().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 10 }
    fn answer_desc(&self) -> String { "Sum".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("142913828922".to_string()) }
}
