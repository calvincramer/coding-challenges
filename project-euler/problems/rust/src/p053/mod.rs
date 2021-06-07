use rust_math_tools::ncr;
use num_bigint::BigUint;

pub struct P053 {}
impl crate::Problem for P053 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let one_mill = BigUint::from(1_000_000u64);
        let mut total = 0;
        for n in 1..=100 {
            for r in 0..=n {
                if ncr(n, r) > one_mill {
                    total += 1;
                }
            }
        }
        total.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 53 }
    fn answer_desc(&self) -> String { "Num > one million".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("4075".to_string()) }
}
