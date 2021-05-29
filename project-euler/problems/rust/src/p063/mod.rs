use rust_math_tools::num_digits_128;
use std::cmp::Ordering;

pub struct P063 {}
impl crate::Problem for P063 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        use Ordering::*;
        let mut count = 0;
        for a in 1..=9u128 {
            let mut n = 0u32;
            loop {
                n += 1;
                let num = a.pow(n);
                let n_digits = num_digits_128(num) as u32;
                match n.cmp(&n_digits) {
                    Less => continue,
                    Equal => {
                        if verbose {
                            println!("{} = {}^{}", num, a, n);
                        }
                        count += 1
                    },
                    Greater => break,
                }
            }
        }
        (63, "Amount".to_string(), count.to_string())
        // 49
    }
}
