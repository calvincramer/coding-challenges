pub mod p030 {
    pub fn run() {
        println!("Problem 30");
        let sum_digits_pow_5 = |num: &u64| -> u64 {
            let mut sum = 0;
            let mut n: u64 = *num;
            while n > 0 {
                sum += (n % 10).pow(5);
                n /= 10;
            }
            return sum;
        };
        let results: Vec<u64> = (2u64..10_000_000).filter(|n: &u64| *n == sum_digits_pow_5(n)).collect();
        println!("{:?}", results);
        println!("sum={}", results.iter().sum::<u64>());
    }
    // Answer: 443839
}
