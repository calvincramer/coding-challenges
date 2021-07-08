use rayon::prelude::*;
use rust_math_tools::permute_once;
use std::collections::HashSet;

fn calculate(digits: &Vec<usize>, ops: &Vec<usize>, ops_order: &Vec<usize>) -> Option<u64> {
    let mut temp_answers = vec![0.0; digits.len()];
    for i in 0..digits.len() {
        temp_answers[i] = digits[i] as f64;
    }
    let mut ops_order_copy = ops_order.clone();
    let mut ops_copy = ops.clone();

    let mut ops_order_i = 0;
    // for ops_i in &ops_order_copy {
    while ops_order_i < ops_order_copy.len() {
        let ops_i = ops_order_copy[ops_order_i];
        let op = ops_copy[ops_i];
        let operand_1 = temp_answers[ops_i];
        let operand_2 = temp_answers[ops_i + 1];
        let temp_ans = match op {
            0 => operand_1 + operand_2,
            1 => operand_1 - operand_2,
            2 => operand_1 * operand_2,
            3 => {
                if operand_2 == 0.0 {
                    return None;
                }
                operand_1 / operand_2
            }
            _ => panic!(),
        };
        temp_answers[ops_i] = temp_ans;
        temp_answers.remove(ops_i + 1);
        ops_copy.remove(ops_i);
        // Update ops index order to index smaller digits vector
        for i in 0..ops_order_copy.len() {
            if ops_order_copy[i] > ops_i {
                ops_order_copy[i] -= 1;
            }
        }
        ops_order_i += 1;
    }

    // Answer is positive and an integer?
    let ans = temp_answers[0];
    if ans > 0.0 && (ans - ((ans as u64) as f64)).abs() < 1e-6 {
        Some((ans + 0.5) as u64)
    } else {
        None
    }
}

#[rustfmt::skip]
fn count_consecutive_possibilities(orig_digits: &Vec<usize>) -> u64 {
    let mut set = HashSet::<u64>::new();
    let mut indices = vec![0, 1, 2, 3];
    // Permute over digits
    loop {
        let digits = vec![
            orig_digits[indices[0]],
            orig_digits[indices[1]],
            orig_digits[indices[2]],
            orig_digits[indices[3]],
        ];
        // All possibilities of operations (can use same operation multiple times)
        // 0=add, 1=minus, 2=multiply, 3=divide
        for ops_num in 0..64 {
            let ops = vec![ops_num % 4, (ops_num / 4) % 4, (ops_num / 16) % 4];
            // Go over possibilities for grouping / order that ops are applied
            let mut ops_order = vec![0, 1, 2];
            loop {
                if let Some(ans) = calculate(&digits, &ops, &ops_order) {
                    set.insert(ans);
                }
                if !permute_once(&mut ops_order[..]) { break; }
            }
        }
        if !permute_once(&mut indices) { break; }
    }
    // Search for longest consecutive 1..n present
    for n in 1u64..=(set.len() as u64) {
        if !set.contains(&n) {
            return n-1;
        }
    }
    panic!();
}

pub struct P093 {}
impl crate::Problem for P093 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut digits_perms = vec![];
        for d1 in 1..=9 {
            for d2 in (d1 + 1)..=9 {
                for d3 in (d2 + 1)..=9 {
                    for d4 in (d3 + 1)..=9 {
                        digits_perms.push(vec![d1, d2, d3, d4]);
                    }
                }
            }
        }

        let (max_poss, max_nums) = digits_perms
            .into_par_iter()
            .map(|digits| (count_consecutive_possibilities(&digits), digits))
            .max_by_key(|x| x.0)
            .unwrap();
        format!(
            "{}{}{}{}",
            max_nums[0], max_nums[1], max_nums[2], max_nums[3]
        )
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        93
    }
    fn answer_desc(&self) -> String {
        "Arith consecutive".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("1258".to_string())
    }
}
