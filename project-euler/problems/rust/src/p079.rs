/// Algorithm:
/// Take out the number the only occurs on the left most. This number must be the next
/// number in the passcode. Repeat until nothing left
/// This method only works if the characters in the passcode are unique

use rust_math_tools::read_all_lines;
use itertools::Itertools;
use std::collections::HashSet;

pub struct P079 {}
impl crate::Problem for P079 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {

        let lines = read_all_lines("src/input_files/p079_keylog.txt".to_string());
        let mut nums: Vec<Vec<u8>> = lines.iter()
            .map(|s| s.as_bytes()
                .iter()
                .map(|c| c - 0x30)
                .collect_vec())
            .collect_vec(); // 0x30 = ascii '0'
        let mut passcode = Vec::new();

        while !nums.is_empty() {
            // Find leftmost number that only occurs in leftmost
            let first_nums: HashSet<u8> = nums.iter().map(|v| v[0]).collect();
            for first_n in first_nums {
                let mut first_n_add_to_pass = true;
                for v in &nums {
                    if let Some(i) = v.iter().position(|x| *x == first_n) {
                        if i != 0 {
                            first_n_add_to_pass = false;
                            break;
                        }
                    }
                }
                if first_n_add_to_pass {
                    passcode.push(first_n);
                    // Remove first_n from nums
                    let mut i = 0;
                    while i < nums.len() {
                        if nums[i][0] == first_n {
                            nums[i].remove(0);
                        }
                        if nums[i].len() == 0 {
                            nums.remove(i);
                        } else {
                            i += 1;
                        }
                    }
                }
            }

        }

        passcode.iter().map(|n| n.to_string()).collect::<String>()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 79 }
    fn answer_desc(&self) -> String { "Passcode".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("73162890".to_string()) }
}
