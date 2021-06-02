use rust_math_tools::totient;
use rayon::prelude::*;

pub struct P072 {}
impl crate::Problem for P072 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        (72, "num elements".to_string(), (2u64..=1_000_000).into_par_iter().map(totient).sum::<u64>().to_string())
        // Answer: 303963552391
    }
}
