use rayon::prelude::*;

fn digit_factorial_sum(mut num: u64) -> u64 {
    let facs = [1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880];
    let mut new = 0;
    while num > 0 {
        new += facs[(num % 10) as usize];
        num /= 10;
    }
    new
}

fn digit_factorial_length(start: u64) -> usize {
    let mut seen = Vec::new();
    let mut trav = start;
    while ! seen.contains(&trav) {
        seen.push(trav);
        trav = digit_factorial_sum(trav);
    }
    seen.len()
}

pub struct P074 {}
impl crate::Problem for P074 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let total = (0..1_000_000).into_par_iter().filter(|n| digit_factorial_length(*n) == 60).count();
        (74, "num chains 60".to_string(), total.to_string())
        // Answer: 402
    }
}
