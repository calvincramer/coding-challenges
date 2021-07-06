use self::rust_math_tools::PrimeTest;
use rust_math_tools;

pub struct P007 {}
impl crate::Problem for P007 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut num: u64 = 13;
        let mut prime_num: u32 = 6; // Sixth prime is 13
        while prime_num < 10001 {
            num += 2;
            if num.is_prime() {
                prime_num += 1;
            }
        }
        num.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        7
    }
    fn answer_desc(&self) -> String {
        "Num".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("104743".to_string())
    }
}
