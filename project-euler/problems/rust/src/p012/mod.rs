pub mod p012 {
    use rust_math_tools::num_divisors;

    pub fn run() {
        println!("Problem 12");
        let mut trinum_num: u64 = 1; // nth trinum
        let mut trinum_sum: u64 = 1; // actual triangle number
        while num_divisors(trinum_sum) <= 500 {
            trinum_num += 1;
            trinum_sum += trinum_num;
        }
        println!("{} {}", trinum_num, trinum_sum);
    }
    // Answer: 76576500
}
