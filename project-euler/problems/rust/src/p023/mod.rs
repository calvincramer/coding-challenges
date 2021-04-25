use rust_math_tools::{divisors_sum_type, DivSum};

pub struct P023 {}
impl crate::Problem for P023 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
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
        (23, "Sum".to_string(), total.to_string())
        // Answer: 4179871
    }
}
