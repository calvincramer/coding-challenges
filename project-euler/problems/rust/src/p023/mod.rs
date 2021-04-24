pub mod p023 {
    use rust_math_tools::{divisors_sum_type, DivSum};

    pub fn run() {
        println!("Problem 23");
        let mut abundant_nums: Vec<u64> = (1..=28123)
            .map(|n| (n, divisors_sum_type(n)))
            .filter(|(_, t)| t == &DivSum::Abundant)
            .map(|(n, _)| n)
            .collect();
        abundant_nums.sort();

        let mut nums: Vec<u64> = (0..=30000).collect();
        for i1 in 0..abundant_nums.len() {
            for i2 in 0..abundant_nums.len() {
                let temp = abundant_nums[i1] + abundant_nums[i2];
                if temp < nums.len() as u64 {
                    nums[temp as usize] = 0;
                }
            }
        }
        let total: u64 = nums.iter().sum();
        println!("{:?}", total);
    }
    // Answer: 4179871
}
