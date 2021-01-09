//[COMPLETED]

struct Solution;

#[allow(dead_code)]
#[derive(Copy, Clone)]
enum Roman {
    I = 1,
    V = 5,
    X = 10,
    L = 50,
    C = 100,
    D = 500,
    M = 1000,
}

impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        use self::Roman::*;
        let mut roman_tok: Vec<Roman> = Vec::new();
        for c in s.chars() {
            roman_tok.push(match c {
                'I' => I, 'V' => V, 'X' => X, 'L' => L, 'C' => C, 'D' => D, 'M' => M,
                _ => panic!("Invalid character"),
            });
        }
        let mut val: u32 = 0;
        let mut skip = false;
        for (i, tok) in roman_tok.iter().enumerate() {
            if skip {
                skip = false;
                continue;
            }
            let tok_val = *tok as u32;
            if i == roman_tok.len() - 1 {
                val += tok_val;
                continue;
            }
            let next_tok = &roman_tok[i+1];
            val += match tok {
                I => {
                    skip = true;
                    match next_tok { V => 4, X => 9,  _ => { skip = false; tok_val } }
                },
                X => {
                    skip = true;
                    match next_tok { L => 40, C => 90, _ => { skip = false; tok_val } }
                }
                C => {
                    skip = true;
                    match next_tok { D => 400, M => 900, _ => { skip = false; tok_val } }

                }
                _ => tok_val,
            };
        }
        val as i32
    }
}

fn main() {
    println!("{} expected 1994", Solution::roman_to_int("MCMXCIV".to_string()));
    println!("{} expected 621", Solution::roman_to_int("DCXXI".to_string()));
}
