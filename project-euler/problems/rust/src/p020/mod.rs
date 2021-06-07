use rust_math_tools::factorial;

pub struct P020 {}
impl crate::Problem for P020 {
    fn solve(&self, verbose: bool) -> String {
        let fac100 = factorial(100);
        if verbose {
            println!("{}", fac100);
        }
        fac100.to_string().chars().map(|c| c.to_digit(10).unwrap()).sum::<u32>().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 20 }
    fn answer_desc(&self) -> String { "Answer".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("648".to_string()) }
}
