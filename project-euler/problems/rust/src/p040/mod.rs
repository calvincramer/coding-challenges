pub struct P040 {}
impl crate::Problem for P040 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut string = String::new();
        let mut n = 1;
        while string.len() <= 1_000_000 {
            string = string + &n.to_string();
            n += 1;
        }
        let char_arr = string.as_bytes();
        let mut prod = 1u64;
        for i in [1usize, 10, 100, 1_000, 10_000, 100_000, 1_000_000].iter() {
            prod *= (char_arr[*i - 1] as u64) - 48;
        }

        (40, "Product".to_string(), prod.to_string())
        // Answer: 210
    }
}