use num_bigint::BigUint;

pub struct P016 {}
impl crate::Problem for P016 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let two = BigUint::parse_bytes(b"2", 10).unwrap();
        let big = two.pow(1000);
        if verbose {
            println!("{}", big);
        }
        (16, "Sum".to_string(), big.to_string().as_bytes().iter().map(|&c| (c as u64) - 0x30).sum::<u64>().to_string())
        // Answer: 1366
    }
}
