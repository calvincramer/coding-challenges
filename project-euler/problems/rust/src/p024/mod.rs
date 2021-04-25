use rust_math_tools::permute_once;

pub struct P024 {}
impl crate::Problem for P024 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut arr = [0u64, 1, 2, 3, 4, 5, 6, 7, 8, 9];
        let mut permutation_num = 1u64;
        loop {
            if permutation_num == 1_000_000 {
                return (24, "Answer".to_string(), arr.iter().map(|d| d.to_string()).collect::<Vec<String>>().join(""))
            }
            if permute_once(&mut arr) == false {
                break;
            }
            permutation_num += 1;
        }
        (24, "Answer".to_string(), String::from("Error"))
        // Answer: 2783915460
    }
}
