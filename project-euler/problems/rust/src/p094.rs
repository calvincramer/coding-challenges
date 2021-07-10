use rayon::prelude::*;
use rust_math_tools::IsSquare;

fn almost_equilateral_has_integral_area(a: u128, c: u128) -> bool {
    let rhs = c.pow(2) * (4 * a.pow(2) - c.pow(2));
    match rhs % 16 == 0 {
        false => false,
        true => (rhs / 16).is_square(),
    }
}

pub struct P094 {}
impl crate::Problem for P094 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        const BILLION: u64 = 1_000_000_000;
        const STOP: u64 = BILLION / 3; // 333_333_333
        const START: u64 = 3;
        let sum_perim = (START..STOP)
            .into_par_iter()
            .map(|a64| {
                if a64 % 2 == 0 {
                    return 0;
                }
                let a = a64 as u128;
                let mut sum = 0;
                if almost_equilateral_has_integral_area(a, a + 1) {
                    if verbose {
                        println!("{} {} {}", a, a, a + 1);
                    }
                    sum += 3 * a + 1;
                }
                if almost_equilateral_has_integral_area(a, a - 1) {
                    if verbose {
                        println!("{} {} {}", a, a, a - 1);
                    }
                    sum += 3 * a - 1;
                }
                sum
            })
            .sum::<u128>();
        sum_perim.to_string()
    }
    fn is_slow(&self) -> bool {
        true
    }
    fn problem_num(&self) -> i32 {
        94
    }
    fn answer_desc(&self) -> String {
        "Sum perimeters".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("518408346".to_string())
    }
}
