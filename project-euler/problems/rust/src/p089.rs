use rayon::prelude::*;
use rust_math_tools::read_all_lines;
use rust_math_tools::reduce_roman;

pub struct P089 {}
impl crate::Problem for P089 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let romans = read_all_lines("src/input_files/p089_roman.txt".to_string());

        // Iterative
        // let mut total_chars = 0;
        // let mut total_chars_minimal = 0;
        // for r_str in romans {
        //     let r_str_min = reduce_roman(&r_str);
        //     total_chars += r_str.len();
        //     total_chars_minimal += r_str_min.len();
        // }
        // (total_chars - total_chars_minimal).to_string()

        // Parallel
        romans
            .into_par_iter()
            .map(|r_str| r_str.len() - reduce_roman(&r_str).len())
            .sum::<usize>()
            .to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        89
    }
    fn answer_desc(&self) -> String {
        "Num chars saved".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("743".to_string())
    }
}
