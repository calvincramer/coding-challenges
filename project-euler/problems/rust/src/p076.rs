pub struct P076 {}
impl crate::Problem for P076 {
    fn solve(&self, verbose: bool) -> String {
        const TARGET: usize = 100;
        let mut table = [[0; TARGET + 1]; TARGET + 1];

        // table at [n,x] holds total number of ways to add two numbers up n, with numbers up to x
        // example: [6,3] = 3 -> 3+1+1+1, 3+2+1, 3+3 (so this doesn't include 4 and 5 in sums)
        table[1][0] = 0; // 1 has 0 ways for two numbers to add to 1
        for i in 1..=TARGET {
            table[1][i] = 1;
        }

        for n in 2..=TARGET {
            for x in 1..n {
                table[n][x] = table[n][x - 1] + table[n - x][x];
            }
            // Fill in rest of row with sum ways for n plus one
            let to_fill = table[n][n - 1] + 1;
            for x in n..=TARGET {
                table[n][x] = to_fill;
            }
        }

        if verbose {
            for row in table.iter() {
                println!("{:?}", row);
            }
        }

        let total = table[TARGET][TARGET - 1];
        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        76
    }
    fn answer_desc(&self) -> String {
        "Num ways".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("190569291".to_string())
    }
}
