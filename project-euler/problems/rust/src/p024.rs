use rust_math_tools::permute_once;

pub struct P024 {}
impl crate::Problem for P024 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut arr = [0u64, 1, 2, 3, 4, 5, 6, 7, 8, 9];
        let mut permutation_num = 1u64;
        loop {
            if permutation_num == 1_000_000 {
                return arr
                    .iter()
                    .map(|d| d.to_string())
                    .collect::<Vec<String>>()
                    .join("");
            }
            if permute_once(&mut arr) == false {
                break;
            }
            permutation_num += 1;
        }
        String::from("Error")
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        24
    }
    fn answer_desc(&self) -> String {
        "Answer".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("2783915460".to_string())
    }
}
