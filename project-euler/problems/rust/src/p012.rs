use rust_math_tools::num_divisors;

pub struct P012 {}
impl crate::Problem for P012 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut trinum_num: u64 = 1; // nth trinum
        let mut trinum_sum: u64 = 1; // actual triangle number
        while num_divisors(trinum_sum) <= 500 {
            trinum_num += 1;
            trinum_sum += trinum_num;
        }
        trinum_sum.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        12
    }
    fn answer_desc(&self) -> String {
        "Sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("76576500".to_string())
    }
}
