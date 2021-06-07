use rust_math_tools::collatz_steps;

pub struct P014 {}
impl crate::Problem for P014 {
    fn solve(&self, verbose: bool) -> String {
        let res = (1..1_000_000)
            .map(|x| (x, collatz_steps(x)))
            .max_by_key(|x| x.1);
        return match res {
            Some(x) => {
                if verbose {
                    println!("{} steps {} times", x.0, x.1);
                }
                x.0.to_string()
            },
            None => "No result".to_string(),
        }
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 14 }
    fn answer_desc(&self) -> String { "Steps".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("837799".to_string()) }
}
