use num_bigint::BigUint;
use num_traits::One;

pub struct P065 {}
impl crate::Problem for P065 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut nums = [1u64; 100];
        let mut k = 2;

        // Fill out nums
        nums[0] = 2;
        for i in (2..nums.len()).step_by(3) {
            nums[i] = k;
            k += 2;
        }

        // Calculate the 100th estimation of e
        let start = 99;
        let mut efrac =
            rust_math_tools::Fraction::<BigUint>::new(BigUint::one(), BigUint::from(nums[start]));
        for i in (0..start).rev() {
            efrac.numerator = (&efrac.denominator * nums[i]) + &efrac.numerator;
            efrac.invert_clone();
        }
        efrac.invert_clone();

        let sum: u64 = efrac
            .numerator
            .to_string()
            .as_bytes()
            .iter()
            .map(|c| (*c as u64) - 48)
            .sum();
        sum.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        65
    }
    fn answer_desc(&self) -> String {
        "Sum digits".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("272".to_string())
    }
}
