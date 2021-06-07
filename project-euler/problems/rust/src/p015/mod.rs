use num_bigint::BigUint;
use num_traits::One;
use std::ops::Div;

pub struct P015 {}
impl crate::Problem for P015 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut fac40: BigUint = One::one();
        for n in 2u64..=40 {
            fac40 = fac40 * BigUint::from(n);
        }
        let mut fac20: BigUint = One::one();
        for n in 2u64..=20 {
            fac20 = fac20 * BigUint::from(n);
        }
        fac40.div(fac20.pow(2)).to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 15 }
    fn answer_desc(&self) -> String { "Answer".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("137846528820".to_string()) }
}
