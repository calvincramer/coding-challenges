/// Credit: http://www.programminglogic.com/integer-partition-algorithm/
/// I have similar solution in problem 76, but a little weirder
#[allow(dead_code)]
fn try1() -> String {
    const TARGET: usize = 60_000;
    let mut table = vec![vec![0u32; TARGET + 1]; TARGET + 1];

    println!("hey");

    for i in 0..=TARGET {
        table[i][0] = 0;
    }
    for i in 1..=TARGET {
        table[0][i] = 1;
    }

    for n in 1..=TARGET {
        for x in 1..=n {
            table[n][x] = (table[n][x - 1] + table[n - x][x]) % 1_000_000;
        }
        let total = table[n][n];
        for x in (n + 1)..=TARGET {
            table[n][x] = total;
        }
        if total % 1_000_000 == 0 {
            return n.to_string();
        }
    }
    "Answer not found".to_string()
}

/// Use Euler's partitioning formula
/// https://en.wikipedia.org/wiki/Partition_(number_theory)
/// https://en.wikipedia.org/wiki/Pentagonal_number_theorem
fn try2() -> String {
    const SIZE: usize = 400; // Found amount of pentagonal numbers by trial and error
    const MOD: i64 = 1_000_000;

    let pentags = {
        let mut temp = vec![0];
        for n in 1u64..((SIZE / 2) as u64) {
            temp.push(n * (3 * n - 1) / 2);
            temp.push(n * (3 * n + 1) / 2); // negative n, distributed
        }
        temp
    };
    let mut partitions: Vec<i64> = vec![1, 1];
    while partitions[partitions.len() - 1] % MOD != 0 {
        let next_n = partitions.len();
        let mut next = 0;
        let mut i = 1;
        while next_n > pentags[i] as usize {
            next += partitions[next_n - pentags[i] as usize]
                * (if ((i - 1) / 2) % 2 == 0 { 1 } else { -1 });
            i += 1;
        }
        partitions.push(next % MOD);
    }

    (partitions.len() - 2).to_string()
}

pub struct P078 {}
impl crate::Problem for P078 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // try1()
        try2()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        78
    }
    fn answer_desc(&self) -> String {
        "Least n".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("55374".to_string())
    }
}
