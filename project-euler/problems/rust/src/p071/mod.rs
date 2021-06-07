pub struct P071 {}
impl crate::Problem for P071 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        (((3.0f64 / 7.0f64) * 1_000_000f64) as u64 - 1).to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 71 }
    fn answer_desc(&self) -> String { "numerator".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("428570".to_string()) }
}
