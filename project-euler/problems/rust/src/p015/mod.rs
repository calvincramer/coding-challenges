use num_bigint::BigUint;
use num_traits::One;
use std::ops::Div;

pub struct P015 {}
impl crate::Problem for P015 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut fac40: BigUint = One::one();
        for n in 2u64..=40 {
            fac40 = fac40 * BigUint::from(n);
        }
        let mut fac20: BigUint = One::one();
        for n in 2u64..=20 {
            fac20 = fac20 * BigUint::from(n);
        }
        (15, "Answer".to_string(), fac40.div(fac20.pow(2)).to_string())
        // Answer: 137846528820
    }
}
