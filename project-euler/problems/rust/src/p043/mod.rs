pub struct P043 {}
impl crate::Problem for P043 {
    #[allow(unused_variables)]
    #[rustfmt::skip]
    fn solve(&self, verbose: bool) -> String {
        let mut sum: u64 = 0;

        for d1 in 1..10 {
        for d2 in 0..10 {
            if d2 == d1 { continue; }
        for d3 in 0..10 {
            if d3 == d1 || d3 == d2 { continue; }
        for d4 in (0..10).step_by(2) {
            if d4 == d3 || d4 == d2 || d4 == d1 { continue; }
            // d4 is mod 2
        for d5 in 0..10 {
            if d5 == d4 || d5 == d3 || d5 == d2 || d5 == d1 { continue; }
            if (100*d3 + 10*d4 + d5) % 3 != 0 { continue; }
        for d6 in (0..10).step_by(5) {
            if d6 == d5 || d6 == d4 || d6 == d3 || d6 == d2 || d6 == d1 { continue; }
            // d6 is mod 5
        for d7 in 0..10 {
            if d7 == d6 || d7 == d5 || d7 == d4 || d7 == d3 || d7 == d2 || d7 == d1 { continue; }
            if (100*d5 + 10*d6 + d7) % 7 != 0 { continue; }
        for d8 in 0..10 {
            if d8 == d7 || d8 == d6 || d8 == d5 || d8 == d4 || d8 == d3 || d8 == d2 || d8 == d1 { continue; }
            if (100*d6 + 10*d7 + d8) % 11 != 0 { continue; }
        for d9 in 0..10 {
            if d9 == d8 || d9 == d7 || d9 == d6 || d9 == d5 || d9 == d4 || d9 == d3 || d9 == d2 || d9 == d1 { continue; }
            if (100*d7 + 10*d8 + d9) % 13 != 0 { continue; }
        for d10 in 0..10 {
            if d10 == d9 || d10 == d8 || d10 == d7 || d10 == d6 || d10 == d5 || d10 == d4 || d10 == d3 || d10 == d2 || d10 == d1 { continue; }
            if (100*d8 + 10*d9 + d10) % 17 != 0 { continue; }
            // Got an answer:
            if verbose {
                println!("{}{}{}{}{}{}{}{}{}{}", d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);
            }
            sum = sum + 10u64.pow(9)*d1
                      + 10u64.pow(8)*d2
                      + 10u64.pow(7)*d3
                      + 10u64.pow(6)*d4
                      + 10u64.pow(5)*d5
                      + 10u64.pow(4)*d6
                      + 10u64.pow(3)*d7
                      + 10u64.pow(2)*d8
                      + 10u64.pow(1)*d9
                      + 10u64.pow(0)*d10;
        }}}}}}}}}}

        sum.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 43 }
    fn answer_desc(&self) -> String { "Sum".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("16695334890".to_string()) }
}