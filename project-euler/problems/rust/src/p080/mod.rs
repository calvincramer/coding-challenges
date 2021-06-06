use rust_math_tools::is_square;
use bigdecimal::BigDecimal;

pub struct P080 {}
impl crate::Problem for P080 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        const START_N: u64 = 1;
        const END_N: u64 = 100;   // 100
        const PREC: u64 = 200;

        let mut total: u64 = 0;

        for n in START_N..=END_N {
            if is_square(&n) { continue; }
            let big_n = BigDecimal::from(n).with_prec(PREC);
            // TODO sqrt has 100 digit precision limit (including leading digits), so missing the last digit, and thus not get right answer
            let sqrt = big_n.sqrt().unwrap().with_prec(PREC);
            println!("{}", sqrt);
            total += sqrt.to_string().as_bytes()[2..].iter().map(|c| (c - 0x30) as u64).sum::<u64>();
        }

        (80, "Sum".to_string(), total.to_string())
        // Answer: ???
    }
}
