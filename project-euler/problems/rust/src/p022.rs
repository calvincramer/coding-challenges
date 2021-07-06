use rust_math_tools::read_all_lines;

pub struct P022 {}
impl crate::Problem for P022 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let x = read_all_lines(String::from("src/input_files/p022_names.txt"));
        let names: Vec<&str> = (&x[0]).split(',').collect();
        let mut names: Vec<String> = names.iter().map(|s| s.to_string()).collect();
        // Strip off quotation marks around each name
        for i in 0..names.len() {
            names[i] = names[i].as_str()[1..names[i].len() - 1].to_string();
        }
        names.sort(); // Sort
                      // Get total score
        let a_val = 64; // 'A' - a_val = 1
        let scores: Vec<u64> = names
            .iter()
            .map(|s| {
                s.as_bytes()
                    .iter()
                    .map(|c| c - a_val)
                    .map(|n| n as u64)
                    .sum()
            })
            .collect();
        let final_score = (0u64..scores.len() as u64)
            .map(|i: u64| scores[i as usize] * (i + 1))
            .sum::<u64>();
        final_score.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        22
    }
    fn answer_desc(&self) -> String {
        "Score".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("871198282".to_string())
    }
}
