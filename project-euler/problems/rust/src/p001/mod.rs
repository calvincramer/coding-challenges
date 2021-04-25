pub struct P001 {}
impl crate::Problem for P001 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut total: u32 = 0;
        for i in 0..1000 {
            if i % 3 == 0 || i % 5 == 0 {
                total += i;
            }
        }
        (1, "Total".to_string(), total.to_string())
        // Answer: 233168
    }
}
