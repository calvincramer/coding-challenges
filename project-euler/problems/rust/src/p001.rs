pub struct P001 {}
impl crate::Problem for P001 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut total: u32 = 0;
        for i in 0..1000 {
            if i % 3 == 0 || i % 5 == 0 {
                total += i;
            }
        }
        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        1
    }
    fn answer_desc(&self) -> String {
        "Total".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("233168".to_string())
    }
}
