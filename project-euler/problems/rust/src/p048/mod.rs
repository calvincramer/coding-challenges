use num_bigint::BigUint;

pub struct P048 {}
impl crate::Problem for P048 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut sum = BigUint::from(0u32);
        for n in 1u32..=1000 {
            sum = sum + BigUint::from(n).pow(n);
        }
        let s = sum.to_string();
        (
            48,
            "Last 10 digits".to_string(),
            s[s.len() - 10..].to_string(),
        )
        // Answer: 9110846700
    }
}
