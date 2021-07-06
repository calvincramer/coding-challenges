pub struct P006 {}
impl crate::Problem for P006 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let sum_sq: i64 = (1..=100).sum::<i64>().pow(2);
        let sq_sum: i64 = (1..=100).map(|x: i64| x.pow(2)).sum();
        (sum_sq - sq_sum).to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        6
    }
    fn answer_desc(&self) -> String {
        "Difference".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("25164150".to_string())
    }
}
