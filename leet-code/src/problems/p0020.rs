//[COMPLETED]

struct Solution;

impl Solution {
    pub fn is_valid(s: String) -> bool {
        let (mut paren, mut curly, mut square) = (0i32, 0i32, 0i32);
        if s.len() == 0 { return true; }
        let mut stack: Vec<char> = Vec::new();
        for c in s.chars() {
//            println!("{}", c);
            match c {
                // Open
                '(' => { paren += 1;  stack.push(c); },
                '[' => { square += 1; stack.push(c); },
                '{' => { curly += 1;  stack.push(c); },
                // Close
                ')' => {
                    match stack.pop() {
                        Some(p) => { if p != '(' { return false; } },
                        None => { return false}
                    }
                    paren -= 1;
                },
                ']' => {
                    match stack.pop() {
                        Some(p) => { if p != '[' { return false; } },
                        None => { return false}
                    }
                    square -= 1;
                },
                '}' => {
                    match stack.pop() {
                        Some(p) => { if p != '{' { return false; } },
                        None => { return false}
                    }
                    curly -= 1;
                },
                _ => return false,  // Unrecognized character
            }
        }
        paren == 0 && curly == 0 && square == 0
    }
}

fn main() {
    println!("{}", Solution::is_valid("[()({}{}[]())]".to_string()));
}


// Bad try, need a stack
//pub fn is_valid(s: String) -> bool {
//    let (mut paren, mut curly, mut square) = (0i32, 0i32, 0i32);
//    if s.len() == 0 { return true; }
//    let mut last_open: char = ' ';  // Fails if first character is closing
//    for c in s.chars() {
//        println!("{}", c);
//        match c {
//            // Open
//            '(' => { paren += 1;  last_open = c},
//            '[' => { square += 1; last_open = c},
//            '{' => { curly += 1;  last_open = c},
//            // Close
//            ')' => {
//                if last_open != '(' { return false }
//                paren -= 1;
//            },
//            ']' => {
//                if last_open != '[' { return false }
//                square -= 1;
//            },
//            '}' => {
//                if last_open != '{' { return false }
//                curly -= 1;
//            },
//            _ => return false,
//        }
//    }
//    paren == 0 && curly == 0 && square == 0
//}