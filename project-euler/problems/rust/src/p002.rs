pub struct P002 {}
impl crate::Problem for P002 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut num1: u64 = 1;
        let mut num2: u64 = 2;
        let mut sum: u64 = 0;

        while num1 <= 4000000 {
            if num1 % 2 == 0 {
                sum += num1;
            }
            let next = num1 + num2;
            num1 = num2;
            num2 = next;
        }
        sum.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        2
    }
    fn answer_desc(&self) -> String {
        "Sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("4613732".to_string())
    }
}
