use rust_math_tools::num_to_string;

fn count_chars(s: &String) -> u32 {
    s.chars()
        .map(char::is_alphabetic)
        .map(|b| b as u32)
        .sum::<u32>()
}

pub struct P017 {}
impl crate::Problem for P017 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut total: u32 = 0;
        for n in 1..=1000 {
            let s = num_to_string(n);
            total += count_chars(&s);
            if verbose {
                println!("{} -> {} -> {}", n, s, count_chars(&s));
            }
        }
        (17, "Total".to_string(), total.to_string())
        // Answer: 21124
    }
}
