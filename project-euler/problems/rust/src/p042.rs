use integer_sqrt::IntegerSquareRoot;
use rust_math_tools::read_all_lines;

fn is_trinum(x: u64) -> bool {
    let num = 1 + (8 * x);
    let num_sqrt = num.integer_sqrt();
    if num_sqrt.pow(2) != num {
        return false;
    }
    let top = num_sqrt - 1;
    if top % 2 != 0 {
        return false;
    }
    true
}

pub struct P042 {}
impl crate::Problem for P042 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // Read words:
        let x = read_all_lines("src/input_files/p042_words.txt".to_string());
        let words: Vec<&str> = (&x[0]).split(',').collect();
        let mut words: Vec<String> = words.iter().map(|s| s.to_string()).collect();
        // Strip off quotation marks around each name
        for i in 0..words.len() {
            words[i] = words[i].as_str()[1..words[i].len() - 1].to_string();
        }

        // Convert words to values
        let values: Vec<u64> = words
            .iter()
            .map(|s| s.as_bytes().iter().map(|c| (*c - 64) as u64).sum::<u64>())
            .collect();

        let mut total = 0;
        for n in values {
            if is_trinum(n) {
                total += 1;
            }
        }

        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        42
    }
    fn answer_desc(&self) -> String {
        "Num tri words".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("162".to_string())
    }
}
