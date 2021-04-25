use rust_math_tools::{num_digits, PrimeTest};

/// 1234 -> [1234, 2341, 3412, 4123] (not in any specific order though)
fn cycle_num(mut num: u64) -> Vec<u64> {
    let mut results = vec![];
    let num_digits = num_digits(num) as u32;
    let last_digit_base: u64 = 10u64.pow(num_digits - 1);
    for _ in 0..num_digits {
        // Cycle largest digit to smallest digit
        results.push(num);
        num = ((num % 10) * last_digit_base) + (num / 10);
    }
    results
}

pub struct P035 {}
impl crate::Problem for P035 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let prime_lkup: Vec<bool> = (0u64..1_000_000).map(|x| x.is_prime()).collect();
        let mut total_count = 0;
        for n in 0..1_000_000 {
            let temp = cycle_num(n);
            if temp.iter().all(|n| prime_lkup[*n as usize]) {
                if verbose {
                    println!("{}", n);
                }
                total_count += 1;
            }
        }
        (35, "Total count".to_string(), total_count.to_string())
        // Answer: 55
    }
}
