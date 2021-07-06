use rayon::prelude::*;
use rust_math_tools::gcf;

pub struct P073 {}
impl crate::Problem for P073 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let third = 1.0f64 / 3.0f64;
        let half = 1.0f64 / 2.0f64;

        // Serial
        // let mut total = 0;
        // for d in 2..=12_000 {
        //     let start = (d / 3) - 1;
        //     let end = d / 2;
        //     for n in start..=end {
        //         if gcf(n, d) == 1 && (n as f64) / (d as f64) > third && (n as f64) / (d as f64) < half {
        //             total += 1;
        //         }
        //     }
        // }

        // Parallel
        let total: u64 = (2..=12_000)
            .into_par_iter()
            .map(|d| {
                let mut temp_total = 0;
                let start = (d / 3) - 1;
                let end = d / 2;
                for n in start..=end {
                    if gcf(&n, &d) == 1
                        && (n as f64) / (d as f64) > third
                        && (n as f64) / (d as f64) < half
                    {
                        temp_total += 1;
                    }
                }
                temp_total
            })
            .sum();

        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        73
    }
    fn answer_desc(&self) -> String {
        "num fractions".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("7295372".to_string())
    }
}
