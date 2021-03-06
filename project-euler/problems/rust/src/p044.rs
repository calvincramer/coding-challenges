use integer_sqrt::IntegerSquareRoot;

// TODO: parallelize

fn get_pn(n: u64) -> u64 {
    (n * (3 * n - 1)) / 2
}

fn is_pn(n: u64) -> bool {
    let temp = 1 + 24 * n;
    let sqrt = temp.integer_sqrt();
    if sqrt * sqrt != temp {
        return false;
    }
    (sqrt + 1) % 6 == 0
}

pub struct P044 {}
impl crate::Problem for P044 {
    fn solve(&self, verbose: bool) -> String {
        let mut min_diff = 999_999_999_999;
        let mut pentags = vec![];

        // Don't proper way to get upper bound, so get found it out by running
        for n in 1..2_200 {
            let current_pn = get_pn(n);
            // Check against others
            for other in &pentags {
                if is_pn(current_pn + other) && is_pn(current_pn - other) {
                    if current_pn - other < min_diff {
                        min_diff = current_pn - other;
                        if verbose {
                            println!("{} -> {} {} n= {}", min_diff, current_pn, other, n);
                        }
                    }
                }
            }
            pentags.push(get_pn(n));
        }

        min_diff.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        44
    }
    fn answer_desc(&self) -> String {
        "Min difference".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("5482660".to_string())
    }
}
