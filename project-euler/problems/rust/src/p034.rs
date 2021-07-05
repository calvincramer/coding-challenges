use rayon::prelude::*;

static FAC_LKUP: [u64; 10] = [1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880];

fn is_curious(mut n: u64) -> bool {
    let orig = n;
    let mut temp_sum = 0;
    while n != 0 {
        temp_sum += FAC_LKUP[(n % 10) as usize];
        n /= 10;
    }
    temp_sum == orig
}

pub struct P034 {}
impl crate::Problem for P034 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // Sum of factorial of each digit for 9,999,999 = 2,540,160, and 9! is 362,880, so this a max bound.
        let sum = (3u64..=9_999_999).into_par_iter().filter(|n| is_curious(*n)).sum::<u64>();
        sum.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 34 }
    fn answer_desc(&self) -> String { "Total".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("40730".to_string()) }
}
