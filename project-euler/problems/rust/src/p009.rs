pub struct P009 {}
impl crate::Problem for P009 {
    fn solve(&self, verbose: bool) -> String {
        for a in 1i64..1000 {
            for b in a + 1..1000 {
                let c: i64 = 1000 - a - b;   // Only one option for c
                if a.pow(2) + b.pow(2) == c.pow(2) {
                    if verbose {
                        println!("a={} b={} c={}", a, b, c);
                        println!("a+b+c={}", a + b + c);
                    }
                    return (a * b * c).to_string();
                    // Supposedly there is one one of this triplet, but continue on just in case
                }
            }
        }
        "ERROR".to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 9 }
    fn answer_desc(&self) -> String { "a*b*c".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("31875000".to_string()) }
}
