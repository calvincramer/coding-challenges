pub struct P071 {}
impl crate::Problem for P071 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let a = ((3.0f64 / 7.0f64) * 1_000_000f64) as u64 - 1;
        (71, "numerator".to_string(), a.to_string())
        // Answer: 428570
    }
}
