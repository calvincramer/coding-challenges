use bigdecimal::BigDecimal;
use rust_math_tools::IsSquare;

pub struct P080 {}
impl crate::Problem for P080 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        const START_N: u64 = 1;
        const END_N: u64 = 100; // 100
        const PREC: u64 = 200;

        let mut total: u64 = 0;

        for n in START_N..=END_N {
            if n.is_square() {
                continue;
            }
            let big_n = BigDecimal::from(n).with_prec(PREC);
            // TODO sqrt has 100 digit precision limit (including leading digits), so missing the last digit, and thus not get right answer
            let sqrt = big_n.sqrt().unwrap().with_prec(PREC);
            println!("{}", sqrt);
            total += sqrt.to_string().as_bytes()[2..]
                .iter()
                .map(|c| (c - 0x30) as u64)
                .sum::<u64>();
        }

        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        80
    }
    fn answer_desc(&self) -> String {
        "Sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::AnswerUnknown
    }
}
