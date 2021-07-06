use num_bigint::BigUint;

pub struct P048 {}
impl crate::Problem for P048 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut sum = BigUint::from(0u32);
        for n in 1u32..=1000 {
            sum = sum + BigUint::from(n).pow(n);
        }
        let s = sum.to_string();
        s[s.len() - 10..].to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        48
    }
    fn answer_desc(&self) -> String {
        "Last 10 digits".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("9110846700".to_string())
    }
}
