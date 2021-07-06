use rust_math_tools::num_to_string;

fn count_chars(s: &String) -> u32 {
    s.chars()
        .map(char::is_alphabetic)
        .map(|b| b as u32)
        .sum::<u32>()
}

pub struct P017 {}
impl crate::Problem for P017 {
    fn solve(&self, verbose: bool) -> String {
        let mut total: u32 = 0;
        for n in 1..=1000 {
            let s = num_to_string(n);
            total += count_chars(&s);
            if verbose {
                println!("{} -> {} -> {}", n, s, count_chars(&s));
            }
        }
        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        17
    }
    fn answer_desc(&self) -> String {
        "Total".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("21124".to_string())
    }
}
