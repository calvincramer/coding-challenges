use rust_math_tools::Fraction;

pub struct P033 {}
impl crate::Problem for P033 {
    #[rustfmt::skip]
    fn solve(&self, verbose: bool) -> String {
        let mut frac = Fraction { numerator: 1, denominator: 1 };

        for numerator in 10..=99 {
        for denominator in (numerator+1)..=99 {
            let num_10s = numerator / 10;
            let num_1s = numerator % 10;
            let den_10s = denominator / 10;
            let den_1s = denominator % 10;


            if num_1s != den_10s { continue; }

            let val_orig = (numerator as f64) / (denominator as f64);
            let val_other = (num_10s as f64) / (den_1s as f64);

            if (val_orig - val_other).abs() < 0.000_000_1 {
                if verbose {
                    println!("{} / {}", numerator, denominator);
                }
                frac.numerator *= numerator;
                frac.denominator *= denominator;
            }
        }}

        if verbose {
            println!("\nfrac: {}", frac);
        }
        frac.reduce();
        if verbose {
            println!("frac: {}", frac);
        }
        frac.denominator.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 33 }
    fn answer_desc(&self) -> String { "denom".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("100".to_string()) }
}
