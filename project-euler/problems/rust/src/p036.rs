use rayon::prelude::*;
use rust_math_tools::Palindrome;

pub struct P036 {}
impl crate::Problem for P036 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let sum: u64 = (0u64..1_000_000).into_par_iter()
            .filter(|n| n.is_palindrome() && format!("{:b}", n).is_palindrome())
            .sum();

        sum.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 36 }
    fn answer_desc(&self) -> String { "Sum".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("872187".to_string()) }
}
