pub struct P040 {}
impl crate::Problem for P040 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
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

        prod.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        40
    }
    fn answer_desc(&self) -> String {
        "Product".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("210".to_string())
    }
}
