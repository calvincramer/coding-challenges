use rust_math_tools::Fraction;
use num_bigint::BigInt;

fn a_has_more_digits(a: &BigInt, b: &BigInt) -> bool {
    if a <= b {
        return false
    }
    return a.to_string().len() > b.to_string().len();
}

pub struct P057 {}
impl crate::Problem for P057 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut frac = Fraction::new(BigInt::from(3), BigInt::from(3));
        let mut total = 0;
        for _ in 2..=1_000 {
            if a_has_more_digits(&frac.numerator, &frac.denominator) {
                total += 1;
            }
            let new_top = &frac.numerator + 2*&frac.denominator;
            let new_bottom = frac.numerator + frac.denominator;
            frac.numerator = new_top;
            frac.denominator = new_bottom;
            frac.reduce();
        }
        (57, "Answer".to_string(), total.to_string())
        // Answer: 153
    }
}
