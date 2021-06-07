use rust_math_tools::totient;
use rayon::prelude::*;

pub struct P072 {}
impl crate::Problem for P072 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        (2u64..=1_000_000).into_par_iter().map(totient).sum::<u64>().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 72 }
    fn answer_desc(&self) -> String { "num elements".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("303963552391".to_string()) }
}
