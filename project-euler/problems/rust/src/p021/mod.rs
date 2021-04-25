use rust_math_tools::divisors_proper;

pub struct P021 {}
impl crate::Problem for P021 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let divisors: Vec<u64> = (0..10000)
            .map(|n| divisors_proper(n).iter().sum())
            .collect();
        let mut amicables: Vec<u64> = vec![];
        for n in 1..10000 {
            let other = divisors[n];
            if n == other as usize {
                continue;
            }
            if other >= divisors.len() as u64 {
                continue;
            }
            if n == divisors[other as usize] as usize {
                amicables.push(n as u64);
            }
        }
        (21, "Answer".to_string(), amicables.iter().sum::<u64>().to_string())
        // Answer: 31626
    }
}
