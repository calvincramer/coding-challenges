pub struct P006 {}
impl crate::Problem for P006 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let sum_sq: i64 = (1..=100).sum::<i64>().pow(2);
        let sq_sum: i64 = (1..=100).map(|x: i64| x.pow(2)).sum();
        (6, "Difference".to_string(), (sum_sq - sq_sum).to_string())
        // Answer: 25164150
    }
}