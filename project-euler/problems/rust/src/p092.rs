use rayon::prelude::*;
use rust_math_tools::square_digits;

const STOP: u64 = 10_000_000;

#[allow(dead_code)]
fn loops_89_eventually(start: u64) -> bool {
    let mut trav = start;
    let mut chain = vec![trav];
    loop {
        let new_term = square_digits(trav);
        match chain.contains(&new_term) {
            true => break,
            false => {
                chain.push(new_term);
                trav = new_term;
            }
        }
    }
    chain.contains(&89)
}

fn loops_89_eventually_faster(mut num: u64) -> bool {
    loop {
        num = square_digits(num);
        match num {
            1 => return false,
            89 => return true,
            _ => continue,
        }
    }
}

#[allow(dead_code)]
fn try1() -> String {
    (1u64..STOP)
        .into_par_iter()
        .map(|n| if loops_89_eventually(n) { 1 } else { 0 })
        .sum::<u64>()
        .to_string()
}

fn try2() -> String {
    (1u64..STOP)
        .into_par_iter()
        .map(|n| if loops_89_eventually_faster(n) { 1 } else { 0 })
        .sum::<u64>()
        .to_string()
}

pub struct P092 {}
impl crate::Problem for P092 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // try1()
        try2()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        92
    }
    fn answer_desc(&self) -> String {
        "Square digit 89".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("8581146".to_string())
    }
}
