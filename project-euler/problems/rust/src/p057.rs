use num_bigint::BigInt;
use rust_math_tools::Fraction;

fn a_has_more_digits(a: &BigInt, b: &BigInt) -> bool {
    if a <= b {
        return false;
    }
    return a.to_string().len() > b.to_string().len();
}

pub struct P057 {}
impl crate::Problem for P057 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut frac = Fraction::new(BigInt::from(3), BigInt::from(3));
        let mut total = 0;
        for _ in 2..=1_000 {
            if a_has_more_digits(&frac.numerator, &frac.denominator) {
                total += 1;
            }
            let new_top = &frac.numerator + 2 * &frac.denominator;
            let new_bottom = frac.numerator + frac.denominator;
            frac.numerator = new_top;
            frac.denominator = new_bottom;
            frac.reduce();
        }
        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        57
    }
    fn answer_desc(&self) -> String {
        "Answer".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("153".to_string())
    }
}
