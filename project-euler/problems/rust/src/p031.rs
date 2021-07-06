pub struct P031 {}
impl crate::Problem for P031 {
    #[rustfmt::skip]
    fn solve(&self, verbose: bool) -> String {
        let mut ways = 0;
        for tp1 in (0..=200).step_by(1) {       //one pence coin
        for tp2 in (0..=200).step_by(2) {       //two pence coin
            if tp1 + tp2 > 200 { break; }
        for tp5 in (0..=200).step_by(5) {       //five pence coin
            if tp1 + tp2 + tp5 > 200 { break; }
        for tp10 in (0..=200).step_by(10) {     //ten pence coin
            if tp1 + tp2 + tp5 + tp10 > 200 { break; }
        for tp20 in (0..=200).step_by(20) {     //twenty pence coin
            if tp1 + tp2 + tp5 + tp10 + tp20 > 200 { break; }
        for tp50 in (0..=200).step_by(50) {     //fifty pence coin
            if tp1 + tp2 + tp5 + tp10 + tp20 + tp50 > 200 { break; }
        for tp100 in (0..=200).step_by(100) {   //one pound coin
            if tp1 + tp2 + tp5 + tp10 + tp20 + tp50 + tp100 > 200 { break; }
        for tp200 in (0..=200).step_by(200) {   //two pound coin
            if tp1 + tp2 + tp5 + tp10 + tp20 + tp50 + tp100 + tp200 == 200 {
                if verbose {
                    println!(
                        "{} {} {} {} {} {} {} {}",
                        tp1, tp2, tp5, tp10, tp20, tp50, tp100, tp200
                    );
                }
                ways += 1;
            }
        }}}}}}}}
        ways.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        31
    }
    fn answer_desc(&self) -> String {
        "Total ways".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("73682".to_string())
    }
}
