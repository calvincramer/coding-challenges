use rust_math_tools::get_primes_under;

pub struct P077 {}
impl crate::Problem for P077 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        const SIZE: usize = 101;
        const TOTAL_WAYS: usize = 5_000;

        let primes = get_primes_under(SIZE as u64);
        // Fast prime lookup one-hot encode each number as prime or not:
        let mut prime_lkup = vec![0; (primes[primes.len() - 1] + 1) as usize];
        for p in &primes {
            prime_lkup[*p as usize] = 1;
        }

        let mut table = [[0; SIZE]; SIZE];

        for n in 4..SIZE {
            let mut x = 0;
            while primes[x] < n as u64 {
                let d = n - primes[x] as usize;
                table[n][x] = (if x > 0 { table[n][x - 1] } else { 0 })
                    + table[d][x]
                    + (if 2 * primes[x] as u64 >= n as u64 {
                        prime_lkup[d]
                    } else {
                        0
                    });
                x += 1;
            }
            // Fill in rest of row with sum way
            let total = table[n][x - 1];
            while x < SIZE {
                table[n][x] = total;
                x += 1;
            }
            // Stop?
            if total > TOTAL_WAYS {
                return n.to_string();
            }
        }
        "table too small".to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        77
    }
    fn answer_desc(&self) -> String {
        "Value".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("71".to_string())
    }
}
